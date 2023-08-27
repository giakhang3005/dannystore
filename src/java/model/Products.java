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
public class Products {
    private String id, name, describe;
    private int quantity, price;
    private String img;
    private int cid;

    public Products(String id, String name, String describe, int quantity, int price, String img, int cid) {
        this.id = id;
        this.name = name;
        this.describe = describe;
        this.quantity = quantity;
        this.price = price;
        this.img = img;
        this.cid = cid;
    }

    @Override
    public String toString() {
        return "Products{" + "id=" + id + ", name=" + name + ", describe=" + describe + ", quantity=" + quantity + ", price=" + price + ", img=" + img + ", cid=" + cid + '}';
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescribe() {
        return describe;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }

    public String getImg() {
        return img;
    }

    public int getCid() {
        return cid;
    }
    
    
}
