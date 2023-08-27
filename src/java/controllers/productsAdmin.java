/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dal.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Categories;
import model.Products;

/**
 *
 * @author nguye
 */
@WebServlet(name = "productsAdmin", urlPatterns = {"/productsAdmin"})
public class productsAdmin extends HttpServlet {

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
        String category = request.getParameter("category");
        List<Products> list;
        int cid;
        if (category == "" || category == null) {
            list = d.getAllProducts();
            request.setAttribute("option", "All");
            
        } else {
            cid = Integer.parseInt(category);
            list = d.getProductsByCid(cid);
        }

        List<Categories> clist = d.getAllCategories();
        request.setAttribute("category", clist);
        request.setAttribute("productsList", list);
//        System.out.println(list);
        request.getRequestDispatcher("productsAdmin.jsp").forward(request, response);
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
        String cid_raw = request.getParameter("categoryKey");
        List<Products> list = null;
        String catName;
        int cid;
        try {
            //get products list user search
            cid = Integer.parseInt(cid_raw);
            if(cid == 0) {
                list = d.getAllProducts();
                catName = "All";
            } else {
                list = d.getProductsByCid(cid);
                Categories cat = d.getCategoriesByCid(cid);
                catName = cat.getName();
            }
            request.setAttribute("productsList", list);
            request.setAttribute("option", catName);
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
        //return list category
        List<Categories> clist = d.getAllCategories();
        request.setAttribute("category", clist);
        request.getRequestDispatcher("productsAdmin.jsp").forward(request, response);
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
