package net.ensode.javaee8book.simpleapp;

import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

@WebFilter(initParams = {
  @WebInitParam(name = "filterparam1", value = "filtervalue1")},
        urlPatterns = {"/InitParamsServlet"})
public class SimpleFilter implements Filter {

  private FilterConfig filterConfig;

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    this.filterConfig = filterConfig;
  }

  @Override
  public void doFilter(ServletRequest servletRequest,
          ServletResponse servletResponse, FilterChain filterChain) throws
          IOException, ServletException {
    ServletContext servletContext = filterConfig.getServletContext();
    servletContext.log("Entering doFilter()");
    servletContext.log("initialization parameters: ");
    Enumeration<String> initParameterNames = filterConfig.
            getInitParameterNames();
    String parameterName;
    String parameterValue;

    while (initParameterNames.hasMoreElements()) {
      parameterName = initParameterNames.nextElement();
      parameterValue = filterConfig.getInitParameter(parameterName);
      servletContext.log(parameterName + " = " + parameterValue);
    }

    servletContext.log("Invoking servlet...");
    filterChain.doFilter(servletRequest, servletResponse);
    servletContext.log("Back from servlet invocation");

  }

  @Override
  public void destroy() {
    filterConfig = null;
  }
}
