package net.ensode.javaee8book.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProgrammaticallyConfiguredServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request,
          HttpServletResponse response)
          throws ServletException, IOException {
    ServletOutputStream outputStream = response.getOutputStream();

    outputStream.println(
            "This message was generated from a servlet that was "
            + "configured programmatically.");
  }
}
