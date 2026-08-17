<%-- 
    Document   : index
    Created on : Aug 11, 2026, 7:15:15 AM
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
            UserDTO user = (userObject != null) ? (UserDTO) userObject : null;

            if (user != null) {
                response.sendRedirect("welcome.jsp");
            }
        %>
        <h4>Login page</h4>
        <form action="MainController" method="POST"  >
            <input type="hidden" name="action" value="login"/>
            <table>
                <tr>
                    <td>Username</td>
                    <td><input type="text" name="txtUsername" required/></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="password" name="txtPassword" required/></td>
                </tr>
                <tr>
                    <td colspan="2"><input type="submit" value="Login"/></td>
                </tr>
            </table>
        </form>    

        <%
            Object errorMessage = request.getAttribute("errorMessage");
        %>

        <span style="color: red">
            <%= errorMessage != null ? errorMessage.toString() : ""%>
        </span>

    </body>
</html>
