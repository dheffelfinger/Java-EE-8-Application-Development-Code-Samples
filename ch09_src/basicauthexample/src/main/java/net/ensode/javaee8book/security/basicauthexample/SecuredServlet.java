package net.ensode.javaee8book.security.basicauthexample;

import java.io.IOException;
import javax.annotation.security.DeclareRoles;
import javax.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@BasicAuthenticationMechanismDefinition(
        realmName = "Book Realm"
)
@WebServlet(name = "SecuredServlet", urlPatterns = {"/securedServlet"})
@DeclareRoles({"user", "admin"})
@ServletSecurity(
        @HttpConstraint(rolesAllowed = "admin"))
public class SecuredServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getOutputStream().print("Congratulations, login successful.");
    }
}
