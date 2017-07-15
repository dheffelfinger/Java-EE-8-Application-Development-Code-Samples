package net.ensode.javaee8book.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

@WebListener()
public class HttpRequestListener implements ServletRequestListener {

  @Override
  public void requestInitialized(ServletRequestEvent servletRequestEvent) {
    ServletContext servletContext = servletRequestEvent.getServletContext();
    servletContext.log("New request initialized");
  }

  @Override
  public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
    ServletContext servletContext = servletRequestEvent.getServletContext();
    servletContext.log("Request destroyed");
  }
}
