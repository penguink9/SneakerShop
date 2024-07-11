
package dao;
import context.DBContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Category;
public class CategoryDAO extends DBContext {
    public CategoryDAO() {
    }

    public List<Category> getAllCategories() {
        String sql = "SELECT * FROM Categories";
        List<Category> categories = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("CategoryID");
                String name = rs.getString("CategoryName");
                categories.add(new Category(id, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    public Category getCategoryById(int categoryId) {
        String sql = "SELECT * FROM Categories WHERE CategoryID = ?";
        Category category = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, categoryId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("CategoryID");
                String name = rs.getString("CategoryName");
                category = new Category(id, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }
}
