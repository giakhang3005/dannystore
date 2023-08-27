/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sideFunction;

import dal.DAO;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.Item;
import model.Products;
import model.Users;

/**
 *
 * @author nguye
 */
public class cartMethod {

    public cartMethod() {
    }

    public String splitCart(List<Item> items) {
        String cartToString = null;
        int count = 0;
        if (items == null) {
            return null;
        } else {
            for (Item i : items) {
                if (items.size() - 1 == count) {
                    System.out.println(i);
                    cartToString += i.getId() + ":" + i.getQuantity();
                    count++;
                } else {
                    cartToString += i.getId() + ":" + i.getQuantity() + "/";
                    count++;
                }
            }
            return cartToString;
        }
    }

    public List<Item> deployCart(String cartInString) {
        List<Item> cart = new ArrayList<>();
        String id, quantityraw;
        int quantity;
        DAO d = new DAO();
        cartInString = cartInString.replace("null", "");
        if (cartInString == null || cartInString == "") {
            cart = null;
        } else {
            //split item using /
            String[] splitItem = cartInString.split("/");
            for (String a : splitItem) {
                String[] splitIdAndQuantity = a.split(":");
                id = splitIdAndQuantity[0];
                try {
                    quantity = Integer.parseInt(splitIdAndQuantity[1]);
                    Item i = new Item(d.getProductsById(id), quantity);
                    cart.add(i);
                } catch (NumberFormatException e) {
                    System.out.println(e);
                }
            }
        }
        return cart;
    }

}
