/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dal.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Categories;
import model.Products;

/**
 *
 * @author nguye
 */
@WebServlet(name = "editCategories", urlPatterns = {"/editCategories"})
public class editCategories extends HttpServlet {

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
            out.println("<title>Servlet editProducts</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet editProducts at " + request.getContextPath() + "</h1>");
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
        String idraw = request.getParameter("id");
        int id;
        try {
            id = Integer.parseInt(idraw);
            Categories c = d.getCategoriesByCid(id);
            request.setAttribute("category", c);
        } catch(NumberFormatException e) {
            System.out.println(e);
        }

        request.getRequestDispatcher("editCategories.jsp").forward(request, response);
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
        String id_raw = request.getParameter("id");
        String name = request.getParameter("name");
        int id;
        String msg = "";

        try {
            id = Integer.parseInt(id_raw);

            //update db
            boolean updateStatus = d.updateCategories(id, name);
            if (updateStatus) {
                msg = "Updated Category with ID = " + id + " and Name = " + name + " successfully!";
                session.setAttribute("msg", msg);
                session.setAttribute("err", "");
            } else {
                session.setAttribute("msg", "");
                session.setAttribute("err", "Failed to update Category with ID = " + id + "and Name = " + name + "!");
            }
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
        response.sendRedirect("categoriesAdmin");
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
