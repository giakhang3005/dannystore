/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import sideFunction.Checker;
import dal.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Users;

/**
 *
 * @author nguye
 */
@WebServlet(name = "editUsers", urlPatterns = {"/editUsers"})
public class editUsers extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet editUsers</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet editUsers at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        DAO d = new DAO();
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        Users u = d.getUsersByUsername(username);
        Users checkRole = d.getUsersByUsername(username);
        if (checkRole.getRole() == 0) {
            session.setAttribute("msgu", "");
            session.setAttribute("erru", "You can not edit Admin " + username + " profile!");
            response.sendRedirect("usersAdmin");
        } else {
            request.setAttribute("account", u);
            request.getRequestDispatcher("editUsers.jsp").forward(request, response);
        }
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
        DAO d = new DAO();
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String role_raw = request.getParameter("role");
        String cart = request.getParameter("cart");
        String balance_raw = request.getParameter("balance");
        String password = request.getParameter("password");
        String msg = "";
        int role;
        double balance;
        Checker checker = new Checker();

        try {
            role = Integer.parseInt(role_raw);
            balance = Double.parseDouble(balance_raw);
            if (checker.checkEmail(email)) {
                d.updateUsers(username, name, email, role, cart, balance, password);
                msg = "Updated user " + username + " successfully!";
                session.setAttribute("msgu", msg);
                session.setAttribute("erru", "");
            } else {
                //the email input is not an email, set new error message
                Users u = d.getUsersByUsername(username);
                session.setAttribute("msgu", msg);
                session.setAttribute("", "");
                request.setAttribute("account", u);
                request.setAttribute("errMsg", email + " is not an email!");
                request.getRequestDispatcher("editUsers.jsp").forward(request, response);
            }

        } catch (NumberFormatException e) {
            System.out.println(e);
        }
        response.sendRedirect("usersAdmin");
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
