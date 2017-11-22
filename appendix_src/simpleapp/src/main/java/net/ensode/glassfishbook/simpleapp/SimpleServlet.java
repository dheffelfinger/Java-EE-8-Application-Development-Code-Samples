package net.ensode.glassfishbook.simpleapp;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SimpleServlet", urlPatterns = "/simpleServlet")
public class SimpleServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) {
    try {
      response.setContentType("text/html");
      PrintWriter printWriter = response.getWriter();
      printWriter.println("<h2>");
      printWriter
              .println("If you are reading this, your application server is good to go!");
      printWriter.println("</h2>");
    }
    catch (IOException ioException) {
      ioException.printStackTrace();
    }
  }
}
