<%-- 
    Document   : user-form.jsp
    Created on : May 25, 2026, 8:44:30 AM
    Author     : Le Nhat Tung
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thêm người dùng</title>
    </head>
    <body>
        <c:choose>
            <c:when test="${update eq true}">
                <h2>Update user</h2>
            </c:when>
            <c:otherwise>
                <h2>Add user</h2>
            </c:otherwise>
        </c:choose>

        <form action="MainController" method="post">
            <input type="hidden" name="action" value="${update?'UpdateUser_saveUser':'addUser'}">
            <table>
                <tr>
                    <td>Mã người dùng:</td>
                    <td><input type="text" name="userID" required value="${userInput.userID}"  ${update?'readonly':''}> <span style="color: red">${userID_error}</span> </td>
                </tr>
                <tr>
                    <td>Họ và tên:</td>
                    <td><input type="text" name="fullName" required value="${userInput.fullName}"><span style="color: red">${fullName_error}</span></td>
                </tr>
                <tr>
                    <td>Mật khẩu:</td>
                    <td><input type="password" name="password" required value=""><span style="color: red">${password_error}</span></td>
                </tr>
                <tr>
                    <td>Vai trò:</td>
                    <td>
                        <select name="roleID">
                            <option value="ADMIN" ${userInput.roleID == 'ADMIN' ? 'selected' : ''}>Admin</option>
                            <option value="USER" ${userInput.roleID == 'USER' ? 'selected' : ''}>User</option>
                        </select>
                        <span style="color: red">${roleID_error}</span>
                    </td>
                </tr>
                <tr>
                    <td>Trạng thái:</td>
                    <td>
                        <input type="checkbox" name="status" value="true" ${userInput == null || userInput.status ? 'checked' : ''}> Hoạt động
                        <span style="color: red">${status_error}</span>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="${update?'Update':'Add'}">
                    </td>
                </tr>
            </table>
            <span style="color: red">${error}</span>
        </form>

    </body>
</html>