package net.ensode.javaee8book.multiplevaluefields;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/multiplevaluefieldhandlerservlet"})
public class MultipleValueFieldHandlerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String[] selectedOptions = request.getParameterValues("options");

        response.setContentType("text/html");

        try {
            PrintWriter printWriter = response.getWriter();

            printWriter.println("<p>");
            printWriter.print("The following options were selected:");
            printWriter.println("<br/>");

            if (selectedOptions != null) {
                for (String option : selectedOptions) {
                    printWriter.print(option);
                    printWriter.println("<br/>");
                }
            } else {
                printWriter.println("None");
            }
            printWriter.println("</p>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
