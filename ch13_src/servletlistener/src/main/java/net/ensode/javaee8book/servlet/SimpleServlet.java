package net.ensode.javaee8book.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/SimpleServlet"})
public class SimpleServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request,
          HttpServletResponse response) {
    try {
      request.setAttribute("attribute1", "value1");
      response.setContentType("text/html");
      PrintWriter printWriter = response.getWriter();
      printWriter.println("<p>");
      printWriter.println(
              "Check the logs to see the listener's output");
      printWriter.println("</p>");
      request.setAttribute("attribute1", "value2");
      request.removeAttribute("attribute1");
    } catch (IOException ioException) {
      ioException.printStackTrace();
    }
  }
}
