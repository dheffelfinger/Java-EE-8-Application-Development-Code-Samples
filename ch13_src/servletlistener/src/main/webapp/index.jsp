<%-- 
    Document   : index
    Created on : May 31, 2017, 8:10:55 PM
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
    <%request.getRequestDispatcher("SimpleServlet").forward(request, response); %>
  </body>
</html>
