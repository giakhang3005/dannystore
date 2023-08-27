/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dal.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;
import java.util.Locale.Category;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Categories;
import model.Products;

/**
 *
 * @author nguye
 */
public class searchProducts extends HttpServlet {

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
            out.println("<title>Servlet searchProducts</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet searchProducts at " + request.getContextPath() + "</h1>");
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
        List<Categories> CList = d.getAllCategories();
        request.setAttribute("category", CList);

        String cid_raw = request.getParameter("categoryKey");
        String describe = request.getParameter("describe");
        String minPrice_raw = request.getParameter("minPrice");
        String maxPrice_raw = request.getParameter("maxPrice");
        int cid, minPrice, maxPrice;
        
        if (cid_raw == "" || cid_raw == null) {
            cid_raw = "0";
        }
        if (describe == "" || describe == null) {
            describe = null;
        }
        if (minPrice_raw == "" || minPrice_raw == null) {
            minPrice_raw = "-1";
        }
        if (maxPrice_raw == "" || maxPrice_raw == null) {
            maxPrice_raw = "-1";
        }
        try {
            //get products list user search
            cid = Integer.parseInt(cid_raw);
            minPrice = Integer.parseInt(minPrice_raw);
            maxPrice = Integer.parseInt(maxPrice_raw);

            List<Products> list = d.searchProduct(describe, minPrice, maxPrice, cid);
            request.setAttribute("productsList", list);
            
            //get option user select
            String option = "All";
            System.out.println(cid);
            if (cid != 0) {
                Categories c = d.getCategoriesByCid(cid);
                option = c.getName();
            }

            request.setAttribute("option", option);
//            System.out.println(list);
        } catch (NumberFormatException e) {
            System.out.println(e);
        }

        request.getRequestDispatcher("products.jsp").forward(request, response);
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
