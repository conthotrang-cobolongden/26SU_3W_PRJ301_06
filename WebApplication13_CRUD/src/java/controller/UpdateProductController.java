package controller;

import java.io.IOException;
import java.time.LocalDateTime;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ProductDAO;
import model.ProductDTO;

@WebServlet(name = "UpdateProductController",
            urlPatterns = {"/UpdateProductController"})
public class UpdateProductController extends HttpServlet {

    private ProductDAO productDAO;

    @Override
    public void init() {
        productDAO = new ProductDAO();
    }

    // =====================================================
    // DO GET
    // search.jsp -> UpdateProductController?productId=P001
    // =====================================================

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // 1. Lấy productID từ URL
        String productID = request.getParameter("productId");

        // 2. Kiểm tra productID
        if (productID == null || productID.trim().isEmpty()) {

            request.setAttribute(
                    "ERROR",
                    "Product ID is required."
            );

            request.getRequestDispatcher("search.jsp")
                    .forward(request, response);

            return;
        }

        // 3. Tìm product trong database
        ProductDTO product =
                productDAO.searchByID(productID.trim());

        // 4. Không tìm thấy product
        if (product == null) {

            request.setAttribute(
                    "ERROR",
                    "Product not found."
            );

            request.getRequestDispatcher("search.jsp")
                    .forward(request, response);

            return;
        }

        // 5. Đánh dấu đây là UPDATE
        request.setAttribute("UPDATE", true);

        // 6. Đưa product sang product_form.jsp
        request.setAttribute("PRODUCT", product);

        // 7. Hiển thị form
        request.getRequestDispatcher("product_form.jsp")
                .forward(request, response);
    }


    // =====================================================
    // DO POST
    // product_form.jsp -> UpdateProductController
    // =====================================================

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // =================================================
        // 1. Lấy dữ liệu từ form
        // =================================================

        String productID =
                request.getParameter("productID");

        String productName =
                request.getParameter("productName");

        String description =
                request.getParameter("description");

        String priceString =
                request.getParameter("price");

        String quantityString =
                request.getParameter("quantity");

        String image =
                request.getParameter("image");

        String statusString =
                request.getParameter("status");


        // =================================================
        // 2. Validate
        // =================================================

        boolean hasError = false;


        // ---------------- PRODUCT ID ----------------

        if (productID == null ||
            productID.trim().isEmpty()) {

            request.setAttribute(
                    "productIDError",
                    "Product ID is required."
            );

            hasError = true;
        }


        // ---------------- PRODUCT NAME ----------------

        if (productName == null ||
            productName.trim().isEmpty()) {

            request.setAttribute(
                    "productNameError",
                    "Product name is required."
            );

            hasError = true;
        }


        // ---------------- DESCRIPTION ----------------

        if (description == null ||
            description.trim().isEmpty()) {

            request.setAttribute(
                    "descriptionError",
                    "Description is required."
            );

            hasError = true;
        }


        // ---------------- PRICE ----------------

        double price = 0;

        if (priceString == null ||
            priceString.trim().isEmpty()) {

            request.setAttribute(
                    "priceError",
                    "Price is required."
            );

            hasError = true;

        } else {

            try {

                price = Double.parseDouble(
                        priceString.trim()
                );

                if (price < 0) {

                    request.setAttribute(
                            "priceError",
                            "Price cannot be negative."
                    );

                    hasError = true;
                }

            } catch (NumberFormatException e) {

                request.setAttribute(
                        "priceError",
                        "Price must be a valid number."
                );

                hasError = true;
            }
        }


        // ---------------- QUANTITY ----------------

        int quantity = 0;

        if (quantityString == null ||
            quantityString.trim().isEmpty()) {

            request.setAttribute(
                    "quantityError",
                    "Quantity is required."
            );

            hasError = true;

        } else {

            try {

                quantity = Integer.parseInt(
                        quantityString.trim()
                );

                if (quantity < 0) {

                    request.setAttribute(
                            "quantityError",
                            "Quantity cannot be negative."
                    );

                    hasError = true;
                }

            } catch (NumberFormatException e) {

                request.setAttribute(
                        "quantityError",
                        "Quantity must be a valid integer."
                );

                hasError = true;
            }
        }


        // ---------------- STATUS ----------------

        boolean status =
                Boolean.parseBoolean(statusString);


        // =================================================
        // 3. Nếu validate lỗi
        // =================================================

        if (hasError) {

            ProductDTO product = new ProductDTO(
                    productID,
                    productName,
                    description,
                    price,
                    quantity,
                    image,
                    status,
                    LocalDateTime.now()
            );

            request.setAttribute(
                    "PRODUCT",
                    product
            );

            request.setAttribute(
                    "UPDATE",
                    true
            );

            request.getRequestDispatcher(
                    "product_form.jsp"
            ).forward(request, response);

            return;
        }


        // =================================================
        // 4. Tạo ProductDTO
        // =================================================

        ProductDTO product = new ProductDTO(
                productID.trim(),
                productName.trim(),
                description.trim(),
                price,
                quantity,
                image,
                status,
                LocalDateTime.now()
        );


        // =================================================
        // 5. Update database
        // =================================================

        boolean result =
                productDAO.update(product);


        // =================================================
        // 6. Update thành công
        // =================================================

        if (result) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/search.jsp"
            );

        } else {

            // =================================================
            // 7. Update thất bại
            // =================================================

            request.setAttribute(
                    "ERROR",
                    "Unable to update product."
            );

            request.setAttribute(
                    "PRODUCT",
                    product
            );

            request.setAttribute(
                    "UPDATE",
                    true
            );

            request.getRequestDispatcher(
                    "product_form.jsp"
            ).forward(request, response);
        }
    }


    @Override
    public String getServletInfo() {
        return "Update Product Controller";
    }
}