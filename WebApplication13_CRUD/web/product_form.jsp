<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="hasProduct" value="${not empty PRODUCT}" />
<c:set var="isUpdate" value="${not empty UPDATE}"/>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>
            ${isUpdate ? 'Update Product' : 'Add Product'}
        </title>
    </head>

    <body>

        <h1>
            ${isUpdate ? 'Update Product' : 'Add Product'}
        </h1>

        <%-- Lỗi chung từ Controller/DAO --%>
        <c:if test="${not empty ERROR}">
            <p style="color: red;">
                ${ERROR}
            </p>
        </c:if>

        <form action="${isUpdate ? 'UpdateProductController' : 'AddProductController'}"
              method="post">

            <%-- ================= PRODUCT ID ================= --%>

            <p>
                <label>Product ID:</label>

                <input type="text"
                       name="productID"
                       value="${hasProduct ? PRODUCT.productID : param.productID}"
                       ${isUpdate ? 'readonly' : ''}
                       required>

                <c:if test="${not empty productIDError}">
                    <br>
                    <span style="color: red;">
                        ${productIDError}
                    </span>
                </c:if>
            </p>


            <%-- ================= PRODUCT NAME ================= --%>

            <p>
                <label>Product Name:</label>

                <input type="text"
                       name="productName"
                       value="${hasProduct ? PRODUCT.productName : param.productName}"
                       >

                <c:if test="${not empty productNameError}">
                    <br>
                    <span style="color: red;">
                        ${productNameError}
                    </span>
                </c:if>
            </p>


            <%-- ================= DESCRIPTION ================= --%>

            <p>
                <label>Description:</label>
                <br>

                <textarea name="description"
                          rows="5"
                          cols="40"
                          >${hasProduct ? PRODUCT.description : param.description}</textarea>

                <c:if test="${not empty descriptionError}">
                    <br>
                    <span style="color: red;">
                        ${descriptionError}
                    </span>
                </c:if>
            </p>


            <%-- ================= PRICE ================= --%>

            <p>
                <label>Price:</label>

                <input type="number"
                       name="price"
                       value="${hasProduct ? PRODUCT.price : param.price}"
                       min="0"
                       step="0.01"
                       >

                <c:if test="${not empty priceError}">
                    <br>
                    <span style="color: red;">
                        ${priceError}
                    </span>
                </c:if>
            </p>


            <%-- ================= QUANTITY ================= --%>

            <p>
                <label>Quantity:</label>

                <input type="number"
                       name="quantity"
                       value="${hasProduct ? PRODUCT.quantity : param.quantity}"
                       min="0"
                       >

                <c:if test="${not empty quantityError}">
                    <br>
                    <span style="color: red;">
                        ${quantityError}
                    </span>
                </c:if>
            </p>


            <%-- ================= IMAGE ================= --%>

            <p>
                <label>Image:</label>

                <input type="text"
                       name="image"
                       value="${hasProduct ? PRODUCT.image : param.image}">

                <c:if test="${not empty imageError}">
                    <br>
                    <span style="color: red;">
                        ${imageError}
                    </span>
                </c:if>
            </p>


            <%-- ================= STATUS ================= --%>

            <p>
                <label>Status:</label>

                <input type="radio"
                       name="status"
                       value="true"
                       ${hasProduct
                           ? (PRODUCT.status ? 'checked' : '')
                           : 'checked'}>
                Active

                <input type="radio"
                       name="status"
                       value="false"
                       ${hasProduct
                           ? (PRODUCT.status ? '' : 'checked')
                           : ''}>
                Inactive

                <c:if test="${not empty statusError}">
                    <br>
                    <span style="color: red;">
                        ${statusError}
                    </span>
                </c:if>
            </p>


            <%-- ================= BUTTON ================= --%>

            <button type="submit">
                ${isUpdate ? 'Update' : 'Add'}
            </button>

            <a href="search.jsp">
                Cancel
            </a>

        </form>

    </body>
</html>