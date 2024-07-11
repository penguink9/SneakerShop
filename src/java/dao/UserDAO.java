package dao;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.*;
import java.util.List;
import model.User;
import context.DBContext;

public class UserDAO extends DBContext{

    public List<User> getAllUsers() {
        String sql="SELECT * FROM Users";
        List<User> users = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("UserID");
                String userName = rs.getString("UserName");
                String fullName = rs.getString("FullName");
                String password = rs.getString("Password");
                int roleID = rs.getInt("RoleID");
                String imageURL = rs.getString("ImageURL");
                String email = rs.getString("Email");
                Date birthDay = rs.getDate("BirthDay");
                String address = rs.getString("Address");
                String phone = rs.getString("Phone");
                users.add(new User(id, userName, fullName, password, roleID, imageURL, email, birthDay, address, phone));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public User getUserByUsername(String userName) {
        String sql="SELECT * FROM Users WHERE UserName = ?";
        User user = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, userName);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("UserID");
                String fullName = rs.getString("FullName");
                String password = rs.getString("Password");
                int roleID = rs.getInt("RoleID");
                String imageURL = rs.getString("ImageURL");
                String email = rs.getString("Email");
                Date birthDay = rs.getDate("BirthDay");
                String address = rs.getString("Address");
                String phone = rs.getString("Phone");
                user = new User(id, userName, fullName, password, roleID, imageURL, email, birthDay, address, phone);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void addUser(User user) {
        String sql="INSERT INTO Users (UserName, FullName, Password, RoleID, ImageURL, Email, BirthDay, Address, Phone) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getFullName());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setInt(4, user.getRoleID());
            preparedStatement.setString(5, user.getImageURL());
            preparedStatement.setString(6, user.getEmail());
            preparedStatement.setDate(7, (Date) user.getBirthDay());
            preparedStatement.setString(8, user.getAddress());
            preparedStatement.setString(9, user.getPhone());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean deleteUser(String userName) {
        String sql="DELETE FROM Users WHERE UserName = ?;";
        boolean rowDeleted = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, userName);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowDeleted;
    }

    public boolean checkUser(String userName, String password) {
        String sql="SELECT * FROM Users WHERE UserName = ? AND Password = ?";
        boolean userExists = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            userExists = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userExists;
    }
    public boolean updateUser(User user) {
        String sql="UPDATE Users SET FullName = ?, Password = ?, RoleID = ?, ImageURL = ?, Email = ?, BirthDay = ?, Address = ?, Phone = ? WHERE UserName = ?";
        boolean rowUpdated = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getFullName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, user.getRoleID());
            preparedStatement.setString(4, user.getImageURL());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setDate(6, (Date) user.getBirthDay());
            preparedStatement.setString(7, user.getAddress());
            preparedStatement.setString(8, user.getPhone());
            preparedStatement.setString(9, user.getUserName());
            rowUpdated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }
    
    public static void main(String[] args) {
        UserDAO dao= new UserDAO();
        List<User> list= dao.getAllUsers();
        for(User u:list) {
            System.out.println(u);
        }
    }
}
