/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;
import java.util.List;

/**
 *
 * @author nguye
 */
public class Orders {
    private String OID, username;
    private String orderCart;
    private String orderDate;
    private double totalMoney;

    public Orders(String OID, String username, String orderCart, String orderDate, double totalMoney) {
        this.OID = OID;
        this.username = username;
        this.orderCart = orderCart;
        this.orderDate = orderDate;
        this.totalMoney = totalMoney;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    
    public String getOID() {
        return OID;
    }

    public String getUsername() {
        return username;
    }

    public String getOrderCart() {
        return orderCart;
    }

    public String getOrderDate() {
        return orderDate;
    }

    @Override
    public String toString() {
        return "Orders{" + "OID=" + OID + ", username=" + username + ", orderCart=" + orderCart + ", orderDate=" + orderDate + '}';
    }
    
    
}
