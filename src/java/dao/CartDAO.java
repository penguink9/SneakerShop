package dao;

import context.DBContext;
import java.beans.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Cart;
import model.Item;
import model.Product;

public class CartDAO extends DBContext {

    public List<Cart> getAllCarts() {
        List<Cart> carts = new ArrayList<>();
        String sql = "SELECT * FROM Cart";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int cartID = rs.getInt("CartID");
                String userName = rs.getString("UserName");

                Cart cart = new Cart(userName);

                String itemSql = "SELECT * FROM CartItem WHERE CartID = ?";
                try (PreparedStatement itemStmt = connection.prepareStatement(itemSql)) {
                    itemStmt.setInt(1, cartID);
                    ResultSet itemRs = itemStmt.executeQuery();
                    List<Item> items = new ArrayList<>();
                    while (itemRs.next()) {
                        int productID = itemRs.getInt("ProductID");
                        float quantity = itemRs.getFloat("Quantity");

                        ProductDAO productDAO = new ProductDAO();
                        Product product = productDAO.getProductById(productID);

                        Item item = new Item(product, (int) quantity);
                        items.add(item);
                    }
                    cart.setListItems(items);
                }

                carts.add(cart);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carts;
    }

    public void addCart(Cart cart) {
        try {
            // Add to Cart table
            String sql1 = "INSERT INTO Cart (UserName) VALUES (?)";
            PreparedStatement st1 = connection.prepareStatement(sql1);
            st1.setString(1, cart.getUsername());
            st1.executeUpdate();
            ResultSet generatedKeys = st1.getGeneratedKeys();
            int cartID = 0;
            if (generatedKeys.next()) {
                cartID = generatedKeys.getInt(1);
            }

            // Add to CartItem table
            String sql2 = "INSERT INTO CartItem (CartID, ProductID, Quantity) VALUES (?, ?, ?)";
            for (Item item : cart.getListItems()) {
                PreparedStatement st2 = connection.prepareStatement(sql2);
                st2.setInt(1, cartID);
                st2.setInt(2, item.getProduct().getProductID());
                st2.setFloat(3, item.getQuantity());
                st2.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Cart getCartByUsername(String username) {
        Cart cart = null;
        String sql = "SELECT * FROM Cart WHERE UserName = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                int cartID = rs.getInt("CartID");
                cart = new Cart(username);

                String itemSql = "SELECT * FROM CartItem WHERE CartID = ?";
                try (PreparedStatement itemStmt = connection.prepareStatement(itemSql)) {
                    itemStmt.setInt(1, cartID);
                    ResultSet itemRs = itemStmt.executeQuery();
                    List<Item> items = new ArrayList<>();
                    while (itemRs.next()) {
                        int productID = itemRs.getInt("ProductID");
                        float quantity = itemRs.getFloat("Quantity");

                        ProductDAO productDAO = new ProductDAO();
                        Product product = productDAO.getProductById(productID);

                        Item item = new Item(product, (int) quantity);
                        items.add(item);
                    }
                    cart.setListItems(items);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cart;
    }

    public void updateCart(Cart cart) {
        String username = cart.getUsername();
        List<Item> items = cart.getListItems();

        try {
            // Check if the user has a cart
            String cartSql = "SELECT CartID FROM Cart WHERE UserName = ?";
            int cartID = 0;
            try (PreparedStatement cartStmt = connection.prepareStatement(cartSql)) {
                cartStmt.setString(1, username);
                ResultSet cartRs = cartStmt.executeQuery();
                if (cartRs.next()) {
                    cartID = cartRs.getInt("CartID");
                } else {
                    // If the cart does not exist, handle it as necessary (e.g., throw an exception or create a new cart)
                    throw new SQLException("Cart does not exist for the user: " + username);
                }
            }

            // Remove all items from the cart
            String deleteItemsSql = "DELETE FROM CartItem WHERE CartID = ?";
            try (PreparedStatement deleteItemsStmt = connection.prepareStatement(deleteItemsSql)) {
                deleteItemsStmt.setInt(1, cartID);
                deleteItemsStmt.executeUpdate();
            }

            // Insert new items into the cart
            String insertItemSql = "INSERT INTO CartItem (CartID, ProductID, Quantity) VALUES (?, ?, ?)";
            try (PreparedStatement insertItemStmt = connection.prepareStatement(insertItemSql)) {
                for (Item item : items) {
                    insertItemStmt.setInt(1, cartID);
                    insertItemStmt.setInt(2, item.getProduct().getProductID());
                    insertItemStmt.setFloat(3, item.getQuantity());
                    insertItemStmt.addBatch();
                }
                insertItemStmt.executeBatch();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
