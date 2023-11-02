/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modals;

import java.sql.Date;

/**
 *
 * @author admin
 */
public class Order {
    private int orderID;
    private int productID;
    private int userID;
    private int cartID;
    private float totalPrice;
    private Date purchaseDate;
    private int monthRevenue;
    private double totalRevenue;

    public Order() {
    }

    public Order(int orderID, int productID, int userID, int cartID, float totalPrice, Date purchaseDate) {
        this.orderID = orderID;
        this.productID = productID;
        this.userID = userID;
        this.cartID = cartID;
        this.totalPrice = totalPrice;
        this.purchaseDate = purchaseDate;
    }

    public Order(int monthRevenue, double totalRevenue) {
        this.monthRevenue = monthRevenue;
        this.totalRevenue = totalRevenue;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getCartID() {
        return cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public int getMonthRevenue(){
        return monthRevenue;
    }
    
    public void setMonthRevenue (int monthRevenue){
        this.monthRevenue = monthRevenue;
    }
    
    public double getTotalRevenue(){
        return totalRevenue;
    }
    
    public void setTotalRevenue(double totalRevenue){
        this.totalRevenue = totalRevenue;
    }
    
}

