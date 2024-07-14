package model;

import java.util.Date;

public class Order {
    private int orderID;
    private Date date;
    private String receiver;
    private String phone;
    private String userName;
    private String deliveryAddress;
    private double totalMoney;
    private boolean status;

    public Order(int orderID, Date date, String receiver, String phone, String userName, String deliveryAddress, double totalMoney,boolean status) {
        this.orderID = orderID;
        this.date = date;
        this.receiver = receiver;
        this.phone = phone;
        this.userName = userName;
        this.deliveryAddress = deliveryAddress;
        this.totalMoney = totalMoney;
        this.status=status;
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

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    @Override
    public String toString() {
        return "Order{" + "orderID=" + orderID + ", date=" + date + ", receiver=" + receiver + ", phone=" + phone + ", userName=" + userName + ", deliveryAddress=" + deliveryAddress + ", totalMoney=" + totalMoney + ", status=" + status + '}';
    }
}