
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
}
