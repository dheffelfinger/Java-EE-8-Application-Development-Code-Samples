package net.ensode.javaee8book.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.PushBuilder;

/**
 *
 * @author davidheffelfinger
 */
@WebServlet(name = "ServletPushDemoServlet", urlPatterns = {"/ServletPushDemoServlet"})
public class ServletPushDemoServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PushBuilder pushBuilder = request.newPushBuilder();

        if (pushBuilder != null) {
            //We know the browser is going to need the image
            //so we push it before it even requests it.
            //We could do the same for Javascript files, CSS, etc.
            pushBuilder.path("images/david_heffelfinger.png").
                    addHeader("content-type", "image/png").
                    push();

            response.sendRedirect("response.html");
        } else {
            PrintWriter writer = response.getWriter();
            writer.write("<html>");
            writer.write("<body>");
            writer.write("Could not get an instance of PushBuilder, are you using https?<br/>");
            writer.write("One of the following URLs should work on most Java EE 8 application servers under their default configurations<br/><br/>");
            writer.write("<a href='https://localhost:8181/servlet4http2push'>https://localhost:8181/servlet4http2push</a>(GlassFish)<br/><br/>");
            writer.write("<a href='https://localhost:8443/servlet4http2push'>https://localhost:8443/servlet4http2push</a>(JBoss, WildFly or Tomee)<br/><br/>");
            writer.write("<a href='https://localhost:9443/servlet4http2push'>https://localhost:9443/servlet4http2push</a>(Websphere Liberty Profile)<br/><br/>");
            writer.write("</body>");
            writer.write("</html>");

        }

    }
}
