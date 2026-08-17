/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.UserDAO;
import model.UserDTO;

/**
 *
 * @author Le Nhat Tung
 */
public class LoginController extends HttpServlet {

    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAO();
    }

    public UserDTO checkLogin(String username, String password) {
        UserDTO user = userDAO.searchByID(username);

        if (user == null || !user.getPassword().equals(password)) {
            return null;
        }

        return user;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        // De lay du lieu su dung request.getParameter
        String username = request.getParameter("txtUsername").trim();
        String password = request.getParameter("txtPassword").trim();

        UserDTO user = checkLogin(username, password);
        String url = "login.jsp";
        if (user != null) {
            if (user.isStatus()) { // status is true
                url = "welcome.jsp";
                
                HttpSession session = request.getSession();              
                session.setAttribute("loggedUser", user);
                //session.setMaxInactiveInterval(15);
                
            } else { // status is false
                url = "error.jsp";
                request.setAttribute("errorMessage", "Your account is locked!");
            }
        } else {
            request.setAttribute("errorMessage", "Invalid username or password!");
            url = "login.jsp";
        }
        // Chuyen trang
        request.getRequestDispatcher(url).forward(request, response);
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
