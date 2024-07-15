package dao;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.*;
import java.util.List;
import model.User;
import context.DBContext;

public class UserDAO extends DBContext {

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM Users";
        List<User> users = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String userName = rs.getString("UserName");
                String fullName = rs.getString("FullName");
                String password = rs.getString("Password");
                int roleID = rs.getInt("RoleID");
                String imageURL = rs.getString("ImageURL");
                String email = rs.getString("Email");
                String birthDay = rs.getString("BirthDay");
                String address = rs.getString("Address");
                String phone = rs.getString("Phone");
                users.add(new User(userName, fullName, password, roleID, imageURL, email, birthDay, address, phone));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public User getUserByUsername(String userName) {
        String sql = "SELECT * FROM Users WHERE UserName = ?";
        User user = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, userName);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String fullName = rs.getString("FullName");
                String password = rs.getString("Password");
                int roleID = rs.getInt("RoleID");
                String imageURL = rs.getString("ImageURL");
                String email = rs.getString("Email");
                String birthDay = rs.getString("BirthDay");
                String address = rs.getString("Address");
                String phone = rs.getString("Phone");
                user = new User(userName, fullName, password, roleID, imageURL, email, birthDay, address, phone);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void addUser(User user) {
        String sql = "INSERT INTO Users (UserName, FullName, Password, RoleID, ImageURL, Email, BirthDay, Address, Phone) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getFullName());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setInt(4, user.getRoleID());
            preparedStatement.setString(5, user.getImageURL());
            preparedStatement.setString(6, user.getEmail());
            preparedStatement.setString(7, user.getBirthDay());
            preparedStatement.setString(8, user.getAddress());
            preparedStatement.setString(9, user.getPhone());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean deleteUser(String userName) {
        String sql = "DELETE FROM Users WHERE UserName = ?;";
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
        String sql = "SELECT * FROM Users WHERE UserName = ? AND Password = ?";
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

    public void changePassword(String username, String password) {
        String sql = "Update Users set Password = ? where Username = ? ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, password);
            st.setString(2, username);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void update(String name, String address, String phone, String email, String dob, String userName, String img) {
        String sql = "UPDATE [dbo].[Users] SET \n";
        if (name != null) {
            sql += " [FullName] = " + "?";
        }
        if (address != null) {
            sql += ", [Address] =" + "?";
        }
        sql += ", [Phone] =" + "?";
        sql += ", [Email] =" + "?";
        sql += ", [BirthDay] =" + "?";
        sql += ", [ImageURL] =" + "?";
        sql += " WHERE UserName = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            st.setString(2, address);
            st.setString(3, phone);
            st.setString(4, email);
            st.setString(5, dob);
            st.setString(6, img);
            st.setString(7, userName);

            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public int getNumberUsers() {
        try {
            String sql = "SELECT COUNT(*) FROM Users";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int number = rs.getInt(1);
                return number;
            }
        } catch (Exception e) {
        }
        return 1;
    }

    public boolean checkUserNameDuplicate(String username) {
        String sql = "SELECT 1 FROM Users WHERE userName = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, username);
            try (ResultSet rs = st.executeQuery()) {
                // If there's a result, it means the username exists
                return rs.next();
            }
        } catch (SQLException e) {
            System.out.println(e);
            return false; // Optionally, you might want to handle this differently
        }
    }
    public void updateUser(User u) {
        String sql = "UPDATE Users SET FullName = ?, Password = ?, RoleID = ?, ImageURL = ?, Email = ?, BirthDay = ?, Address = ?, Phone = ? WHERE UserName = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, u.getFullName());
            preparedStatement.setString(2, u.getPassword());
            preparedStatement.setInt(3, u.getRoleID());
            preparedStatement.setString(4, u.getImageURL());
            preparedStatement.setString(5, u.getEmail());
            preparedStatement.setString(6, u.getBirthDay());
            preparedStatement.setString(7, u.getAddress());
            preparedStatement.setString(8, u.getPhone());
            preparedStatement.setString(9, u.getUserName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        UserDAO dao = new UserDAO();
        List<User> list = dao.getAllUsers();
    }
}
