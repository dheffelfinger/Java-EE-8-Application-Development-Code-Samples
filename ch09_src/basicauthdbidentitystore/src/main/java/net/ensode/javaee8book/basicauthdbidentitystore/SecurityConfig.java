package net.ensode.javaee8book.basicauthdbidentitystore;

import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

@DatabaseIdentityStoreDefinition(
        dataSourceLookup = "java:app/jdbc/userAuth2",
        callerQuery = "select password from users where USERNAME = ?",
        groupsQuery = "select g.GROUP_NAME from "
        + "USER_GROUPS ug, users u, "
        + "GROUPS g where u.USERNAME=? "
        + "and ug.USER_ID = u.user_id "
        + "and g.GROUP_ID= ug.GROUP_ID",
        hashAlgorithm = Pbkdf2PasswordHash.class,
        hashAlgorithmParameters = {
            "Pbkdf2PasswordHash.Iterations=3072",
            "Pbkdf2PasswordHash.Algorithm=PBKDF2WithHmacSHA512",
            "Pbkdf2PasswordHash.SaltSizeBytes=64"
        }
)
@ApplicationScoped
public class SecurityConfig {

}
