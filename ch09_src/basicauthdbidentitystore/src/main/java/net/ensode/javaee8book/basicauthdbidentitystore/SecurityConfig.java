package net.ensode.javaee8book.basicauthdbidentitystore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.annotation.sql.DataSourceDefinition;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;
import javax.sql.DataSource;

@DataSourceDefinition(
        // global to circumvent https://java.net/jira/browse/GLASSFISH-21447
        name = "java:global/MyDS2",
        className = "org.h2.jdbcx.JdbcDataSource",
        url = "jdbc:h2:~/test2;DB_CLOSE_ON_EXIT=FALSE"
)
@ApplicationScoped
public class SecurityConfig {

    @Resource(lookup = "java:global/MyDS2")
    private DataSource dataSource;

    @Inject
    private Pbkdf2PasswordHash passwordHash;

    @PostConstruct
    public void init() {
        try {
            Map<String, String> parameters = new HashMap<>();
            parameters.put("Pbkdf2PasswordHash.Iterations", "3072");
            parameters.put("Pbkdf2PasswordHash.Algorithm", "PBKDF2WithHmacSHA512");
            parameters.put("Pbkdf2PasswordHash.SaltSizeBytes", "64");
            passwordHash.initialize(parameters);

            executeUpdate(dataSource, "DROP TABLE IF EXISTS GROUPS");
            executeUpdate(dataSource, "DROP TABLE IF EXISTS USERS");
            executeUpdate(dataSource, "DROP TABLE IF EXISTS USER_GROUPS");

            executeUpdate(dataSource, "CREATE TABLE IF NOT EXISTS USERS\n"
                    + "(\n"
                    + "   USER_ID INTEGER PRIMARY KEY not null,\n"
                    + "   USERNAME VARCHAR(10) not null,\n"
                    + "   FIRST_NAME VARCHAR(15),\n"
                    + "   MIDDLE_NAME VARCHAR(15),\n"
                    + "   LAST_NAME VARCHAR(20),\n"
                    + "   PASSWORD CHAR(160) not null\n"
                    + ")");

            executeUpdate(dataSource, "CREATE TABLE IF NOT EXISTS GROUPS\n"
                    + "(\n"
                    + "   GROUP_ID INTEGER PRIMARY KEY not null,\n"
                    + "   GROUP_NAME VARCHAR(20) unique not null,\n"
                    + "   GROUP_DESC VARCHAR(200)\n"
                    + ")");
            executeUpdate(dataSource, "CREATE TABLE IF NOT EXISTS USER_GROUPS\n"
                    + "(\n"
                    + "   USER_ID INTEGER not null,\n"
                    + "   GROUP_ID INTEGER not null,\n"
                    + "   PRIMARY KEY (USER_ID,GROUP_ID)\n"
                    + ")");

            executeUpdate(dataSource, "INSERT INTO GROUPS (GROUP_ID,GROUP_NAME,GROUP_DESC) VALUES (1,'admin','Administrators')");
            executeUpdate(dataSource, "INSERT INTO GROUPS (GROUP_ID,GROUP_NAME,GROUP_DESC) VALUES (2,'user','Regular users')");

            executeUpdate(dataSource, "INSERT INTO USERS (USER_ID,USERNAME,FIRST_NAME,MIDDLE_NAME,LAST_NAME,PASSWORD) VALUES (1,'david','David','Raymond','Heffelfinger','" + passwordHash.generate("aaa".toCharArray()) + "')");

            executeUpdate(dataSource, "INSERT INTO USER_GROUPS (USER_ID,GROUP_ID) VALUES (1,1)");
            executeUpdate(dataSource, "INSERT INTO USER_GROUPS (USER_ID,GROUP_ID) VALUES (1,2)");
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    private void executeUpdate(DataSource dataSource, String query) {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @PreDestroy
    public void destroy() {
        try {
            executeUpdate(dataSource, "DROP TABLE IF EXISTS USERS");
            executeUpdate(dataSource, "DROP TABLE IF EXISTS GROUPS");
            executeUpdate(dataSource, "DROP TABLE IF EXISTS USER_GROUPS");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
