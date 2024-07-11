
package dao;

import context.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Order;
import model.Cart;
import model.User;

public class OrderDAO extends DBContext{
    
    public int getNumberOrders() {
        try {
            String sql = "SELECT COUNT(*) FROM Orders";
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
    public void addOrder(User user, Cart cart) {
//        ProductDAO productDAO = new ProductDAO();
//        LocalDate curDate = LocalDate.now();
//        String date = curDate.toString();
//        try {
//            // Add to Orders table
//            String sql1 = "INSERT INTO Orders (Date, UserName, DeliveryAddress, TotalMoney, status) VALUES (?, ?, ?, ?, ?)";
//            PreparedStatement st1 = connection.prepareStatement(sql1);
//            st1.setString(1, date);
//            st1.setString(2, user.getUserName());
//            st1.setString(3, user.getAddress());
//            st1.setDouble(4, cart.getTotalMoney());
//            st1.setBoolean(5, false);
//            st1.executeUpdate();
//
//            // Get the OrderID of the newly created order
//            String sql2 = "SELECT TOP 1 OrderID FROM Orders ORDER BY OrderID DESC";
//            PreparedStatement st2 = connection.prepareStatement(sql2);
//            ResultSet rs = st2.executeQuery();
//
//            // Add details to OrderDetails table
//            if (rs.next()) {
//                int orderID = rs.getInt(1);
//                for (Item item : cart.getListItems()) {
//                    String sql3 = "INSERT INTO OrderDetails (OrderID, ProductID, Quantity, Price, Discount) VALUES (?, ?, ?, ?, ?)";
//                    PreparedStatement st3 = connection.prepareStatement(sql3);
//                    st3.setInt(1, orderID);
//                    st3.setInt(2, item.getProduct().getProductID());
//                    st3.setFloat(3, item.getQuantity());
//                    st3.setDouble(4, item.getProduct().getPrice());
//                    st3.setFloat(5, item.getProduct().getDiscount());
//                    st3.executeUpdate();
//
//                    // Update product quantity
//                    productDAO.updateValueProduct(item.getProduct(), item.getProduct().getProductID());
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    private Order extractOrderFromResultSet(ResultSet rs) throws SQLException {
        int orderID = rs.getInt("OrderID");
        Date date = rs.getDate("Date");
        String userName = rs.getString("UserName");
        String deliveryAddress = rs.getString("DeliveryAddress");
        double totalMoney = rs.getDouble("TotalMoney");
        boolean status = rs.getBoolean("status");

        return new Order(orderID, date, userName, deliveryAddress, totalMoney, status);
    }
}
