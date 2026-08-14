/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.UserDAO;
import model.UserDTO;

/**
 *
 * @author Le Nhat Tung
 */
public class UpdateUserController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void viewUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String userID = request.getParameter("userID");
        UserDAO dao = new UserDAO();
        UserDTO user = dao.searchByID(userID);
        String url = "";
        if (user != null) {
            request.setAttribute("userInput", user);
            request.setAttribute("update", true);
            url = "user-form.jsp";
        } else {
            url = "e403.jsp";
        }
        // Chuyen trang
        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);
    }

    public void saveUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String userID = request.getParameter("userID");
        String fullName = request.getParameter("fullName");
        String password = request.getParameter("password");
        String roleID = request.getParameter("roleID");
        boolean status = request.getParameter("status") != null;

        UserDAO dao = new UserDAO();

        boolean checkError = false;

        // Check user name is empty
        if (fullName.isEmpty()) {
            request.setAttribute("fullName_error", "Fullname can not be empty");
            checkError = true;
        }

        // Check user name is empty
        if (password.length() < 8) {
            request.setAttribute("password_error", "Password must have at least 8 characters");
            checkError = true;
        }

        if (roleID == null || roleID.trim().isEmpty()) {
            request.setAttribute("roleID_error", "Role must be selected");
            checkError = true;
        }

        if (!status) {
            request.setAttribute("status_error", "status must be selected");
            checkError = true;
        }

        String url = "";
        UserDTO user = new UserDTO(userID, fullName, password, roleID, status);
        request.setAttribute("userInput", user);
        request.setAttribute("update", true);
        if (checkError) {
            url = "user-form.jsp";
        } else {
            if (dao.update(user)) {
                // Them thanh cong
                url = "SearchUserController";
            } else {
                request.setAttribute("error", "Updating a user failed");
            }
        }
        // Chuyen trang
        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String url = "login.jsp";
        String action = request.getParameter("action");
        if (action != null) {
            if (action.equals("UpdateUser_viewUser")) {
                viewUser(request, response);
            } else if (action.equals("UpdateUser_saveUser")) {
                saveUser(request, response);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
