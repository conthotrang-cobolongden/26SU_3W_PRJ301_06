<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Product</title>
    </head>
    <body>
        <!-- Cach 1 -->
        <jsp:include page="welcome.jsp"/>
        <hr/>

        <form action="MainController" method="post">
            <input type="hidden" name="action" value="searchProduct"/>

            <input type="text"
                   name="txtKeywords"
                   value="${txtKeywords}"/>

            <input type="submit" value="Search"/>
        </form>

        <table border="1">
            <c:if test="${not empty productList}">
                <thead>
                    <tr>
                        <th>productID</th>
                        <th>productName</th>
                        <th>description</th>
                        <th>price</th>
                        <th>quantity</th>
                        <th>image</th>
                        <th>status</th>
                        <th>createdAt</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach var="p" items="${productList}">
                        <tr>
                            <td>${p.productID}</td>
                            <td>${p.productName}</td>
                            <td>${p.description}</td>
                            <td>${p.price}</td>
                            <td>${p.quantity}</td>
                            <td>${p.image}</td>
                            <td>${p.status}</td>
                            <td>${p.createdAt}</td>
                            <td></td>
                            <td></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </c:if>

            <c:if test="${empty productList}">
                <tr>
                    <td colspan="10">
                        No products found matching your search!
                    </td>
                </tr>
            </c:if>
        </table>
    </body>
</html>
