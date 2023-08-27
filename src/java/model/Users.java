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
public class Users {

    private String username, name, email;
    private int role;
    private String cart;
    private double balance;
    private String password;

    public Users(String username, String name, String email, int role, String cart, double balance, String password) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.role = role;
        this.cart = cart;
        this.balance = balance;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getRole() {
        return role;
    }

    public String getCart() {
        return cart;
    }

    public double getBalance() {
        return balance;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Users{" + "username=" + username + ", name=" + name + ", email=" + email + ", role=" + role + ", cart=" + cart + ", balance=" + balance + ", password=" + password + '}';
    }
}
