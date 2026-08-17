<%-- 
    Document   : search.jsp
    Created on : Aug 17, 2026, 7:30:03 AM
    Author     : Le Nhat Tung
--%>

<%@page import="model.ProductDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <!-- Cach 1 -->
        <jsp:include page="welcome.jsp"/>
        
        <!-- Cach 2 -->
        <!--%@include file="welcome.jsp" %-->
        
        <hr/>
        <form action="MainController" method="post">
            <input type="hidden" name="action" value="searchProduct"/>
            <input type="text" name="txtKeywords" value="<%=request.getAttribute("txtKeywords")!=null? request.getAttribute("txtKeywords"): ""%>"/>
            <input type="submit" value="Search"/>
        </form>
        <table border="1">
            
            
        <%
            ArrayList<ProductDTO> list = null;
            if(request.getAttribute("productList")!=null)
                list = (ArrayList<ProductDTO>) request.getAttribute("productList");
            if(list!=null && list.size()>0){
                %>
                <thead>
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
                </thead>
                <tbody>
                <%
            
                for(ProductDTO p: list){
            
        %>
                    <tr>
                        <td><%=p.getProductID() %></td>
                        <td><%=p.getProductName()%></td>
                        <td><%=p.getDescription()%></td>
                        <td><%=p.getPrice()%></td>
                        <td><%=p.getQuantity()%></td>
                        <td><%=p.getImage()%></td>
                        <td><%=p.isStatus()%></td>
                        <td><%=p.getCreatedAt()%></td>
                        <td></td>
                        <td></td>
                    </tr>
        <%      }
            %>
             </tbody>
            </table>
            <%
            } else{ %>
            No products found matching your search!
        <% } %>
           
    </body>
</html>
