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
    <% request.getRequestDispatcher("InitParamsServlet").forward(request, response); %>
  </body>
</html>
