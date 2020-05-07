<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>New Library Frontend</title>
  </head>
  <body>
  Welcome!
  </body>
  <%
    String redirectURL = request.getServletContext().getContextPath() + "/login.jsp";
    response.sendRedirect(redirectURL);
  %>
</html>