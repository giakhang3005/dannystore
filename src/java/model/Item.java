/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author nguye
 */
public class Item {
    private String id;
    private Products product;
    private int quantity;
    
    public Item(Products product, int quantity) {
        this.id = product.getId();
        this.product = product;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public Products getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Item{" + "id=" + id + ", product=" + product + ", quantity=" + quantity + '}';
    }
    
}
