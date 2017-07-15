package net.ensode.javaeebook.webfragment;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebFragmentServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    response.getOutputStream().print("<p>\n");
    response.getOutputStream().print("Using a web framework has never being this easy!");
    response.getOutputStream().print("</p>\n");
  }
}
