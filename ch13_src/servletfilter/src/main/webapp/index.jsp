<%-- 
    Document   : index
    Created on : May 30, 2017, 8:40:04 PM
    Author     : heffel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
  </head>
  <body>
      <!--  URL below should work for most application servers running on our local workstation, 
      but in some instances may need to be modified, consult your application server documentation for details. -->
    <% response.sendRedirect("http://localhost:8080/servletfilter/InitParamsServlet"); %>
  </body>
</html>
