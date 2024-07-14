package dao;

import context.DBContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Item;
import model.Product;

public class ProductDAO extends DBContext {

    public ProductDAO() {

    }

    public List<Product> getAllProducts() {
        String sql = "SELECT * FROM Products";
        List<Product> products = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Product product = extractProductFromResultSet(rs);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
    public List<Product> getAllProductsAvail() {
        String sql = "SELECT * FROM Products Where Quantity>0 ";
        List<Product> products = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Product product = extractProductFromResultSet(rs);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public Product getProductById(int productId) {
        String sql = "SELECT * FROM Products WHERE ProductID = ?";
        Product product = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, productId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                product = extractProductFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    public Product getProductByName(String name) {
        String sql = "SELECT * FROM Products WHERE ProductName = ?";
        Product product = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                product = extractProductFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    public void addProduct(Product product) {
        String sql = "INSERT INTO Products (ProductName, CategoryID, Size, Price, Quantity, QuantitySold, image, image2, image3, image4, description, Discount, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            setProductPreparedStatement(product, preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean deleteProduct(int productId) {
        String sql = "DELETE FROM Products WHERE ProductID = ?";
        boolean rowDeleted = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, productId);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowDeleted;
    }

    public boolean updateProduct(Product product) {
        String sql = "UPDATE Products SET ProductName = ?, CategoryID = ?, Size = ?, Price = ?, Quantity = ?, QuantitySold = ?, image = ?, image2 = ?, image3 = ?, image4 = ?, description = ?, Discount = ?, status = ? WHERE ProductID = ?";
        boolean rowUpdated = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            setProductPreparedStatement(product, preparedStatement);
            preparedStatement.setInt(14, product.getProductID());
            rowUpdated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }

    public List<Product> getProductsByCategoryid(int cid) {
        String sql = "SELECT * FROM Products WHERE CategoryID = ? AND Quantity>0" ;
        List<Product> products = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, cid);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Product product = extractProductFromResultSet(rs);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public List<Product> searchByName(String text) {
        String sql = "SELECT * FROM Products WHERE ProductName LIKE ?";
        List<Product> products = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, "%" + text + "%");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Product product = extractProductFromResultSet(rs);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public List<Product> get12Last() {
        List<Product> list = new ArrayList<>();
        String sql = "select top 12 * from Products WHERE Quantity>0 order by [ProductID] desc";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                list.add(extractProductFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public List<Product> getListByPage(List<Product> list, int start, int end) {
        ArrayList<Product> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }

    public List<Product> getTop4BestSelling() {
        List<Product> listProduct = new ArrayList<>();

        String sql = "SELECT TOP 4 * FROM Products Where Quantity>0 ORDER BY [QuantitySold] DESC";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                listProduct.add(extractProductFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listProduct;
    }

    public List<Product> getTop10BestSelling() {
        List<Product> listProduct = new ArrayList<>();

        String sql = "SELECT TOP 10 * FROM Products ORDER BY [QuantitySold] DESC";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                listProduct.add(extractProductFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listProduct;
    }

    public List<Product> getRelatedProduct(Product product) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT TOP 4 * FROM [Products] WHERE [CategoryID] = ? AND [ProductID] != ? AND Quantity>0 ORDER BY [ProductID] DESC";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, product.getCategoryID());
            statement.setInt(2, product.getProductID());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                list.add(extractProductFromResultSet(rs));
            }
        } catch (Exception e) {
            // Handle exception, e.g., log it
        }
        return list;
    }

    public void updateProductQuantities(int productID, int quantity, int quantitySold) {
        String sql = "UPDATE Products SET Quantity = ?, QuantitySold = ? WHERE ProductID = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, quantity);
            statement.setInt(2, quantitySold);
            statement.setInt(3, productID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void adjustQuantities(Item item) {
        String sql = "UPDATE Products SET Quantity = Quantity - ?, QuantitySold = QuantitySold + ? WHERE ProductID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, item.getQuantity());
            statement.setInt(2, item.getQuantity());
            statement.setInt(3, item.getProduct().getProductID());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Product extractProductFromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("ProductID");
        String productName = rs.getString("ProductName");
        int categoryID = rs.getInt("CategoryID");
        String size = rs.getString("Size");
        double price = rs.getDouble("Price");
        int quantity = rs.getInt("Quantity");
        int quantitySold = rs.getInt("QuantitySold");
        String image = rs.getString("image");
        String image2 = rs.getString("image2");
        String image3 = rs.getString("image3");
        String image4 = rs.getString("image4");
        String description = rs.getString("description");
        float discount = rs.getFloat("Discount");
        boolean status = rs.getBoolean("status");

        return new Product(id, productName, categoryID, size, price, quantity, quantitySold, image, image2, image3, image4, description, discount, status);
    }

    private void setProductPreparedStatement(Product product, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, product.getProductName());
        preparedStatement.setInt(2, product.getCategoryID());
        preparedStatement.setString(3, product.getSize());
        preparedStatement.setDouble(4, product.getPrice());
        preparedStatement.setInt(5, product.getQuantity());
        preparedStatement.setInt(6, product.getQuantitySold());
        preparedStatement.setString(7, product.getImage());
        preparedStatement.setString(8, product.getImage2());
        preparedStatement.setString(9, product.getImage3());
        preparedStatement.setString(10, product.getImage4());
        preparedStatement.setString(11, product.getDescription());
        preparedStatement.setFloat(12, product.getDiscount());
        preparedStatement.setBoolean(13, product.isStatus());
    }

    public static void main(String[] args) {
        ProductDAO dao = new ProductDAO();
        List<Product> list = dao.getAllProducts();
        for (Product p : list) {
            System.out.println(p);
        }
    }
}
