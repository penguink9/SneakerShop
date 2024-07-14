
package model;

import dao.OrderDAO;

public class User {
    private String userName;
    private String fullName;
    private String password;
    private int roleID;
    private String imageURL;
    private String email;
    private String birthDay;
    private String address;
    private String phone;

    public User(String userName, String fullName, String password, int roleID, String imageURL, String email, String birthDay, String address, String phone) {
        this.userName = userName;
        this.fullName = fullName;
        this.password = password;
        this.roleID = roleID;
        this.imageURL = imageURL;
        this.email = email;
        this.birthDay = birthDay;
        this.address = address;
        this.phone = phone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public double totalSpend() {
        OrderDAO oDAO= new OrderDAO();
        return oDAO.getTotalSpendByUserName(userName);
    }
    @Override
    public String toString() {
        return "User{ userName=" + userName + ", fullName=" + fullName + ", password=" + password + ", roleID=" + roleID + ", imageURL=" + imageURL + ", email=" + email + ", birthDay=" + birthDay + ", address=" + address + ", phone=" + phone + '}';
    }
}
