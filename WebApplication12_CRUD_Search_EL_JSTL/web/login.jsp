<%--
Document   : index
Created on : Aug 11, 2026, 7:15:15 AM
Author     : Le Nhat Tung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="jakarta.tags.core" prefix="c"%>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>

    <c:if test="${not empty sessionScope.loggedUser}">
        <c:redirect url="welcome.jsp"/>
    </c:if>

    <h4>Login page</h4>

    <form action="MainController" method="POST">
        <input type="hidden" name="action" value="login"/>

        <table>
            <tr>
                <td>Username</td>
                <td>
                    <input type="text"
                           name="txtUsername"
                           required/>
                </td>
            </tr>

            <tr>
                <td>Password</td>
                <td>
                    <input type="password"
                           name="txtPassword"
                           required/>
                </td>
            </tr>

            <tr>
                <td colspan="2">
                    <input type="submit" value="Login"/>
                </td>
            </tr>
        </table>
    </form>

    <c:if test="${not empty errorMessage}">
        <span style="color: red">
            ${errorMessage}
        </span>
    </c:if>

</body>
</html>
