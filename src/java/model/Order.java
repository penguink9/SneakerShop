
package model;

import java.util.Date;
public class Order {
    private int orderID;
    private Date date;
    private String userName;
    private String deliveryAddress;
    private double totalMoney;
    private boolean status;

    public Order(int orderID, Date date, String userName, String deliveryAddress, double totalMoney, boolean status) {
        this.orderID = orderID;
        this.date = date;
        this.userName = userName;
        this.deliveryAddress = deliveryAddress;
        this.totalMoney = totalMoney;
        this.status = status;
    }

    // Getters and Setters
    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
