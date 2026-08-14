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
import javax.servlet.http.HttpSession;
import model.UserDAO;
import model.UserDTO;

/**
 *
 * @author tungln
 */
public class LoginController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String url = "";
        HttpSession session = request.getSession();
        RequestDispatcher rd = request.getRequestDispatcher(url);
        if (session.getAttribute("user")==null) {
            // Neu chua dang nhap
            // request.getParameter => lay ra bien tu form hoac url
            String userID = request.getParameter("userID");
            String password = request.getParameter("password");

            // Kiem tra dang nhap
            UserDAO udao = new UserDAO();
            UserDTO udto = udao.searchByID(userID);
            if (udao.checkLogin(userID, password)) {
                url = "dashboard.jsp";
                // session.setAttribute("userName", udto.getFullName());

                session.setAttribute("user", udto);
            } else {
                // Tinh huong 2 bi khoa tai khoan
                if (!udto.isStatus()) {
                    // Chuyen trang 403
                    url = "403.jsp";
                } else {
                    // Sai ten mat khau
                    url = "login.jsp";
                    request.setAttribute("error", "Invalid userID or password!");
                }
            }
            
        }else{
            // Ðã dang nhap roi
             url = "dashboard.jsp";
        }
        // Chuyen trang 

        rd.forward(request, response);
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
