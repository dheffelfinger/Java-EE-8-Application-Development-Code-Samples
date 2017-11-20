package net.ensode.javaee8book.httpauthdbidentitystore;

import java.io.IOException;
import javax.annotation.security.DeclareRoles;
import javax.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@CustomFormAuthenticationMechanismDefinition(
    loginToContinue = @LoginToContinue(
        loginPage="/faces/login.xhtml",
        errorPage=""
    )
)

@DatabaseIdentityStoreDefinition(
        dataSourceLookup = "java:global/authDS",
        callerQuery = "select password from users where USERNAME = ?",
        groupsQuery = "select g.GROUP_NAME from USER_GROUPS ug, users u, GROUPS g where ug.USER_ID = u.user_id and g.GROUP_ID= ug.GROUP_ID and u.USERNAME=?",
        hashAlgorithm = Pbkdf2PasswordHash.class,
        hashAlgorithmParameters = {
            "Pbkdf2PasswordHash.Iterations=3072",
            "Pbkdf2PasswordHash.Algorithm=PBKDF2WithHmacSHA512",
            "Pbkdf2PasswordHash.SaltSizeBytes=64"
        }
)
@DeclareRoles({"user", "admin"})
@WebServlet("/securedServlet")
@ServletSecurity(
        @HttpConstraint(rolesAllowed = {"admin"}))
public class SecuredServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().write("Congratulations, login successful.");
    }
}
