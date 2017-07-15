package net.ensode.javaee8book.formhandling;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/multiplevaluefieldhandlerservlet"})
public class MultipleValueFieldHandlerServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String[] selectedOptions = request.getParameterValues("options");
        ArrayList<String> selectedOptionLabels = null;

        if (selectedOptions != null) {
            selectedOptionLabels = new ArrayList<String>(selectedOptions.length);

            for (String selectedOption : selectedOptions) {
                if (selectedOption.equals("option1")) {
                    selectedOptionLabels.add("Option 1");
                } else if (selectedOption.equals("option2")) {
                    selectedOptionLabels.add("Option 2");
                } else if (selectedOption.equals("option3")) {
                    selectedOptionLabels.add("Option 3");
                }
            }
        }

        request.setAttribute("checkedLabels", selectedOptionLabels);

        try {
            request.getRequestDispatcher("confirmationservlet").forward(request,
                    response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
