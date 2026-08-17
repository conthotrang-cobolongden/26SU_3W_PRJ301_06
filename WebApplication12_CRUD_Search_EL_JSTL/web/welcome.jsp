<%-- 
    Document   : welcome.jsp
    Created on : Aug 11, 2026, 8:53:50 AM
    Author     : Le Nhat Tung
--%>

<%@page import="model.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Welcome, ${sessionScope.loggedUser.fullName}!</h1>
        <a href="search.jsp">Search Product</a>
        <a href="MainController?action=logout">Logout</a>
    </body>
</html>