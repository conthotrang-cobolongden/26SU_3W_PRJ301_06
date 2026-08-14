<%-- 
    Document   : searchUser.jsp
    Created on : Jun 1, 2026, 8:09:44 AM
    Author     : Le Nhat Tung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="dashboard.jsp" />
        <form action="MainController">
            <input type="hidden" name="action" value="searchUser"/>
            <input type="text" name="keywords" value="${keywords}" />
            <input type="submit" value="Search" />
        </form>
        <hr/>
        <c:if test="${not empty list}">
            <table border="1">
                <thead>
                    <tr>
                        <th>User ID</th>
                        <th>Full Name</th>
                        <th>Password</th>
                        <th>Role ID</th>
                        <th>Status</th>
                        <th>Action</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="u" items="${list}">
                        <tr>
                            <td>${u.userID}</td>
                            <td>${u.fullName}</td>
                            <td>${u.password}</td>
                            <td>${u.roleID}</td>
                            <td>${u.status}</td>
                            <td>
                                <a href="MainController?userID=${u.userID}&action=UpdateUser_viewUser">Update</a>
                            </td>
                            <td>
                                <c:if test="${u.status eq true}">
                                    <a href="DeleteUserController?userID=${u.userID}&action=DeleteUser">Delete</a>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>


        <c:if test="${empty list}">
            <p>No user found!</p>
        </c:if>
    </body>
</html>
