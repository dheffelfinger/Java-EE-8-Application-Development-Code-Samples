package net.ensode.javaee8book.formhandling;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/formhandlerservlet"})
public class FormHandlerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String enteredValue;

        enteredValue = request.getParameter("enteredValue");

        response.setContentType("text/html");

        PrintWriter printWriter;
        try {
            printWriter = response.getWriter();

            printWriter.println("<p>");
            printWriter.print("You entered: ");
            printWriter.print(enteredValue);
            printWriter.print("</p>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
