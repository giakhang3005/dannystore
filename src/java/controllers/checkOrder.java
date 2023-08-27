/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dal.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Item;
import model.Orders;
import model.Users;
import sideFunction.RandomMethod;
import sideFunction.cartMethod;

/**
 *
 * @author nguye
 */
@WebServlet(name = "checkOrder", urlPatterns = {"/checkOrder"})
public class checkOrder extends HttpServlet {

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
            List<Item> cart = (List<Item>) session.getAttribute("cart");
            //get user balance
            Users u = (Users) session.getAttribute("account");
            double balance = u.getBalance();
            
            //get total price
            int TotalMoney = (int) session.getAttribute("totalMoney");
            if(balance < TotalMoney) {
                request.setAttribute("err", "You don't have enough money to order!");
                request.getRequestDispatcher("checkout").forward(request, response);
            } else {
                DAO d = new DAO();
                String username = u.getUsername();
                balance = balance - TotalMoney;
                d.setBalance(balance, username);
                
                u = d.getUsersByUsername(username);
                session.setAttribute("account", u);
                
                d.updateCartToDB(username, null);
                session.setAttribute("cart", null);
                session.setAttribute("cartNumber", 0);
                
                
                //create ORDER
                RandomMethod rm = new RandomMethod();
                
                String OID = rm.generateRandomString();
                while(d.getOrderByOID(OID) != null) {
                    OID = rm.generateRandomString();
                }
                
                Date date = new Date();
                String orderDate = date.toString();
                cartMethod cm = new cartMethod();
                String cartToString = cm.splitCart(cart);
                
                Orders o = new Orders(OID, username, cartToString, orderDate, TotalMoney);
                System.out.println(o);
                d.addOrders(o);
                
                request.getRequestDispatcher("receipt?searchOID=" + OID).forward(request, response);
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
