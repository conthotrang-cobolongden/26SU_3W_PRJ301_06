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
        <%
            Object userObject = session.getAttribute("loggedUser");
            UserDTO user = (userObject!=null) ? (UserDTO)userObject: null;
        %>
        <h1>Welcome, <%= user!=null? user.getFullName() : "" %> !</h1>
        <a href="LogoutController">Logout</a>
    </body>
</html>
