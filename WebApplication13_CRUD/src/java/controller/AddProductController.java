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

@WebServlet(name = "AddProductController", urlPatterns = {"/AddProductController"})
public class AddProductController extends HttpServlet {

    private ProductDAO productDAO;

    @Override
    public void init() {
        productDAO = new ProductDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("product_form.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // =========================
        // 1. Lấy dữ liệu từ form
        // =========================

        String productID = request.getParameter("productID");
        String productName = request.getParameter("productName");
        String description = request.getParameter("description");
        String priceString = request.getParameter("price");
        String quantityString = request.getParameter("quantity");
        String image = request.getParameter("image");
        String statusString = request.getParameter("status");

        // =========================
        // 2. Validate
        // =========================

        boolean hasError = false;

        // ----- Product ID -----

        if (productID == null || productID.trim().isEmpty()) {

            request.setAttribute(
                    "productIDError",
                    "Product ID is required."
            );

            hasError = true;

        } else if (productDAO.searchByID(productID.trim()) != null) {

            request.setAttribute(
                    "productIDError",
                    "Product ID already exists."
            );

            hasError = true;
        }

        // ----- Product Name -----

        if (productName == null || productName.trim().isEmpty()) {

            request.setAttribute(
                    "productNameError",
                    "Product name is required."
            );

            hasError = true;
        }

        // ----- Description -----

        if (description == null || description.trim().isEmpty()) {

            request.setAttribute(
                    "descriptionError",
                    "Description is required."
            );

            hasError = true;
        }

        // ----- Price -----

        double price = 0;

        if (priceString == null || priceString.trim().isEmpty()) {

            request.setAttribute(
                    "priceError",
                    "Price is required."
            );

            hasError = true;

        } else {

            try {

                price = Double.parseDouble(priceString);

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

        // ----- Quantity -----

        int quantity = 0;

        if (quantityString == null || quantityString.trim().isEmpty()) {

            request.setAttribute(
                    "quantityError",
                    "Quantity is required."
            );

            hasError = true;

        } else {

            try {

                quantity = Integer.parseInt(quantityString);

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

        // =========================
        // 3. Nếu có lỗi
        // =========================

        if (hasError) {
            ProductDTO PRODUCT = new ProductDTO(productID, productName, description, price, quantity, image, hasError, LocalDateTime.now());
            request.setAttribute("PRODUCT", PRODUCT);
            request.getRequestDispatcher(
                    "product_form.jsp"
            ).forward(request, response);

            return;
        }

        // =========================
        // 4. Tạo ProductDTO
        // =========================

        boolean status = Boolean.parseBoolean(statusString);

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

        // =========================
        // 5. Add vào database
        // =========================

        boolean result = productDAO.add(product);

        if (result) {

            response.sendRedirect(
                    request.getContextPath() + "/search.jsp"
            );

        } else {

            request.setAttribute(
                    "ERROR",
                    "Unable to add product."
            );

            request.getRequestDispatcher(
                    "product_form.jsp"
            ).forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Add Product Controller";
    }
}