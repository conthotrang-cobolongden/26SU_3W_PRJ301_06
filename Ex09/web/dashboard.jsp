<%-- 
    Document   : a.jsp
    Created on : May 18, 2026, 8:09:31 AM
    Author     : tungln
--%>

<%@page import="model.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Welcome ${user.fullName}</h1> 
        <!--
       <a href="LogoutController"> Logout </a> &nbsp;
        -->
        <form action="MainController">
            <input type="hidden" name="action" value="logout"/>
            <input type="submit" value="Logout"/>
        </form>
        <br/>
        <a href="user-form.jsp">Add User</a>
        <a href="searchUser.jsp">Search User</a>
        <hr/>
    </body>
</html>
