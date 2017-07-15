package net.ensode.javaee8book.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

@WebListener()
public class RequestAttributeListener implements ServletRequestListener {

  @Override
  public void requestInitialized(ServletRequestEvent servletRequestEvent) {
    ServletContext servletContext = servletRequestEvent.getServletContext();
    String clientIpAddress = servletRequestEvent.getServletRequest().
            getRemoteAddr();
    servletContext.log("New request initialized for client at IP address: "
            + clientIpAddress);
  }

  @Override
  public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
    ServletContext servletContext = servletRequestEvent.getServletContext();
    String clientIpAddress = servletRequestEvent.getServletRequest().
            getRemoteAddr();
    servletContext.log("Request destroyed for client at IP address: "
            + clientIpAddress);
  }
}
