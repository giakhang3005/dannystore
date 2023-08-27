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
@WebServlet(name = "addProducts", urlPatterns = {"/addProducts"})
public class addProducts extends HttpServlet {

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
            out.println("<title>Servlet addProducts</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet addProducts at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("addProducts.jsp").forward(request, response);
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
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String describe = request.getParameter("describe");
        String quantity_raw = request.getParameter("quantity");
        String price_raw = request.getParameter("price");
        String image = request.getParameter("image");
        String[] cid_raw = request.getParameterValues("category");
        int quantity, price, cid;
        String msg = "";
        
        //check if id existed
        Products pSearch = d.getProductsById(id);
        if (pSearch == null) {
            try {
                System.out.println(quantity_raw);
                System.out.println(price_raw);
                System.out.println(cid_raw[0]);
                quantity = Integer.parseInt(quantity_raw);
                price = Integer.parseInt(price_raw);
                cid = Integer.parseInt(cid_raw[0]);
                
                //add to db
                Products p = d.addProducts(id, name, describe, quantity, price, image, cid);
                Categories c = d.getCategoriesByCid(cid);
                msg = "Added Product with ID = " + id + ", Name = " + name + " to category " + c.getName() + " successfully!";
                session.setAttribute("msg", msg);
                session.setAttribute("err", "");
            } catch (NumberFormatException e) {
                System.out.println(e);
            }

        } else {
            msg = "Unable to add Product! Product with ID = " + id + " aldready existed!";
            session.setAttribute("msg", "");
            session.setAttribute("err", msg);
        }

        response.sendRedirect("productsAdmin");

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
