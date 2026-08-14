<%-- 
    Document   : index
    Created on : May 18, 2026, 7:20:07 AM
    Author     : tungln
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${sessionScope.user!=null}">
    <c:redirect url="dashboard.jsp"/>
</c:if>
    
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="MainController" method="POST">
            <input type="hidden" name="action" value="login"/>
            Username: <input type="text" name="userID" required/>
            </br>
            Password: <input type="password" name="password" required/>
            </br>
            <input type="submit" value="Login" />
        </form>
        <span style="color: red">${error}</span>
    </body>
</html>
