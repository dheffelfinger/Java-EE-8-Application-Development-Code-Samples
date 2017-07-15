package net.ensode.javaee8book.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebListener;

@WebListener()
public class ServletContextListenerImpl implements
        ServletContextListener {

  @Override
  public void contextInitialized(
          ServletContextEvent servletContextEvent) {
    ServletContext servletContext = servletContextEvent.
            getServletContext();
    try {
      ProgrammaticallyConfiguredServlet servlet = servletContext.
              createServlet(ProgrammaticallyConfiguredServlet.class);
      servletContext.addServlet("ProgrammaticallyConfiguredServlet",
              servlet);
      ServletRegistration servletRegistration = servletContext.
              getServletRegistration(
                      "ProgrammaticallyConfiguredServlet");
      servletRegistration.addMapping(
              "/ProgrammaticallyConfiguredServlet");
    } catch (ServletException servletException) {
      servletContext.log(servletException.getMessage());
    }
  }

  @Override
  public void contextDestroyed(
          ServletContextEvent servletContextEvent) {
  }
}
