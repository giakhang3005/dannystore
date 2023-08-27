/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dal.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Item;
import model.Users;
import sideFunction.cartMethod;

/**
 *
 * @author nguye
 */
@WebServlet(name = "addToCart", urlPatterns = {"/addToCart"})
public class addToCart extends HttpServlet {

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
            HttpSession session = request.getSession();
            int cartNumber = (int) session.getAttribute("cartNumber");
            DAO d = new DAO();
            //get data
            String id = request.getParameter("id");
            List<Item> cart = (List<Item>) session.getAttribute("cart");
            if (cart == null) {
                cart = new ArrayList<>();
            } else {

            }
            boolean cond = true;
            //check duplicate item
            if (cart != null) {
                for (Item icheckdup : cart) {
                    if (icheckdup.getId().equals(id)) {
                        icheckdup.setQuantity(icheckdup.getQuantity() + 1);
                        cond = false;
                        session.setAttribute("cart", cart);
                        break;
                    } else {
                        continue;
                    }
                }
            }
            if (cond) {
                //create new item
                Item i = new Item(d.getProductsById(id), 1);
                //add to cart
                cart.add(i);
                cartNumber++;
                //upload to session
                session.setAttribute("cartNumber", cartNumber);
                session.setAttribute("cart", cart);

            }

            //add cart to DB
            Users u = (Users) session.getAttribute("account");
            d.updateCartToDB(u.getUsername(), cart);

            request.getRequestDispatcher("products").forward(request, response);
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
