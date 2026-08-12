<%-- 
    Document   : user.jsp
    Created on : Aug 12, 2026, 9:19:21 AM
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
            
            if(user ==null){
                response.sendRedirect("login.jsp");
            }
        %>
        <h1>Trang chu cua nguoi dung!</h1>
    </body>
</html>
