
package model;

import java.util.Date;

public class User {
    private int userID;
    private String userName;
    private String fullName;
    private String password;
    private int roleID;
    private String imageURL;
    private String email;
    private Date birthDay;
    private String address;
    private String phone;

    public User(int userID, String userName, String fullName, String password, int roleID, String imageURL, String email, Date birthDay, String address, String phone) {
        this.userID = userID;
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

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
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

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
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

    @Override
    public String toString() {
        return "User{" + "userID=" + userID + ", userName=" + userName + ", fullName=" + fullName + ", password=" + password + ", roleID=" + roleID + ", imageURL=" + imageURL + ", email=" + email + ", birthDay=" + birthDay + ", address=" + address + ", phone=" + phone + '}';
    }
}
