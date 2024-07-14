package dao;

import context.DBContext;
import java.beans.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Order;
import model.Cart;
import model.Item;
import model.User;

public class OrderDAO extends DBContext {

    public void addOrder(Order order, List<Item> listItem) {
        String orderSQL = "INSERT INTO Orders (Date, Receiver, Phone, UserName, DeliveryAddress, TotalMoney) VALUES (?, ?, ?, ?, ?, ?)";
        String orderDetailsSQL = "INSERT INTO OrderDetails (OrderID, ProductID, Size, Quantity, Price, Discount) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            connection.setAutoCommit(false);

            // Insert the order
            PreparedStatement orderStatement = connection.prepareStatement(orderSQL, PreparedStatement.RETURN_GENERATED_KEYS);
            orderStatement.setDate(1, new java.sql.Date(order.getDate().getTime()));
            orderStatement.setString(2, order.getReceiver());
            orderStatement.setString(3, order.getPhone());
            orderStatement.setString(4, order.getUserName());
            orderStatement.setString(5, order.getDeliveryAddress());
            orderStatement.setDouble(6, order.getTotalMoney());
            orderStatement.executeUpdate();

            // Get the most recent order ID
            int orderID = -1;
            ResultSet generatedKeys = orderStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                orderID = generatedKeys.getInt(1);
            }

            // Insert order details
            PreparedStatement orderDetailsStatement = connection.prepareStatement(orderDetailsSQL);
            ProductDAO productDAO = new ProductDAO();
            for (Item item : listItem) {
                orderDetailsStatement.setInt(1, orderID);
                orderDetailsStatement.setInt(2, item.getProduct().getProductID());
                orderDetailsStatement.setString(3, item.getSize());
                orderDetailsStatement.setInt(4, item.getQuantity());
                orderDetailsStatement.setDouble(5, item.getPrice());
                orderDetailsStatement.setFloat(6, item.getProduct().getDiscount());
                orderDetailsStatement.addBatch();

                // Update product quantities
                productDAO.adjustQuantities(item);
            }
            orderDetailsStatement.executeBatch();

            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException rollbackException) {
                rollbackException.printStackTrace();
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException autoCommitException) {
                autoCommitException.printStackTrace();
            }
        }
    }

    public List<Order> getAllOrders() {
        String sql = "SELECT * FROM Orders";
        List<Order> orders = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Order order = extractOrderFromResultSet(rs);
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public List<Order> getOrdersByUsername(String username) {
        String sql = "SELECT * FROM Orders WHERE UserName = ?";
        List<Order> orders = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Order order = extractOrderFromResultSet(rs);
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    private Order extractOrderFromResultSet(ResultSet rs) throws SQLException {
        int orderID = rs.getInt("OrderID");
        Date date = rs.getDate("Date");
        String receiver = rs.getString("Receiver");
        String phone = rs.getString("Phone");
        String userName = rs.getString("UserName");
        String deliveryAddress = rs.getString("DeliveryAddress");
        double totalMoney = rs.getDouble("TotalMoney");
        boolean status = rs.getBoolean("status");
        return new Order(orderID, date, receiver, phone, userName, deliveryAddress, totalMoney,status);
    }

    public double sumAllOrders() {
        String sql = "SELECT SUM(TotalMoney) AS totalSum FROM Orders";
        double totalSum = 0.0;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                totalSum = rs.getDouble("totalSum");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalSum;
    }

    public int numberSneakersSold() {
        String sql = "SELECT SUM(Quantity) AS totalQuantity FROM OrderDetails";
        int totalQuantity = 0;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                totalQuantity = rs.getInt("totalQuantity");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalQuantity;
    }

    public int numberSneakersSoldByCategory(int cid) {
        String sql = "SELECT SUM(Quantity) AS totalQuantity FROM OrderDetails WHERE ProductID IN (SELECT ProductID FROM Products WHERE CategoryID = ?)";
        int totalQuantity = 0;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, cid);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                totalQuantity = rs.getInt("totalQuantity");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalQuantity;
    }

    public List<Order> getOrdersByDate(String date) {
        String sql = "SELECT * FROM Orders WHERE Date = ?";
        List<Order> orders = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, date);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Order order = extractOrderFromResultSet(rs);
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
    public List<User> getTop5UserByTotalSpend() {
        List<User> topUsers = new ArrayList<>();
        String sql = "SELECT TOP 5 UserName, SUM(TotalMoney) as TotalSpend " +
                     "FROM Orders " +
                     "GROUP BY UserName " +
                     "ORDER BY TotalSpend DESC";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            UserDAO uDAO= new UserDAO();
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String username = rs.getString("UserName");
                User user= uDAO.getUserByUsername(username);
                topUsers.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return topUsers;
    }

    public double getTotalSpendByUserName(String username) {
        double totalPrice = 0.0;
        String sql = "SELECT SUM(TotalMoney) as TotalSpend FROM Orders WHERE UserName = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                totalPrice = rs.getDouble("TotalSpend");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalPrice;
    }

    // Test main method
    public static void main(String[] args) {
        OrderDAO dao = new OrderDAO();
        List<Order> test = dao.getAllOrders();
        for(Order t: test) {
            System.out.println(t);
        }
    }
}
