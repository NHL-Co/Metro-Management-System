package Models;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductModel {
    private final Connection conn = DBConnection.getInstance().getConnection();

    public boolean createTable() {
        String query = "CREATE TABLE IF NOT EXISTS product (" +
                "product_id INT PRIMARY KEY AUTO_INCREMENT, " +
                "vendor_id INT, " +
                "name VARCHAR(100), " +
                "category VARCHAR(100), " +
                "original_price DOUBLE, " +
                "sale_price DOUBLE, " +
                "price_by_unit DOUBLE, " +
                "price_by_carton DOUBLE, " +
                "FOREIGN KEY (vendor_id) REFERENCES vendor(vendor_id) ON DELETE SET NULL)";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addProduct(Product product) {
        String query = "INSERT INTO product (vendor_id, name, category, original_price, sale_price, price_by_unit, price_by_carton) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, product.getVendorId());
            pstmt.setString(2, product.getName());
            pstmt.setString(3, product.getCategory());
            pstmt.setDouble(4, product.getOriginalPrice());
            pstmt.setDouble(5, product.getSalePrice());
            pstmt.setDouble(6, product.getPriceByUnit());
            pstmt.setDouble(7, product.getPriceByCarton());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteProduct(int productId) {
        String query = "DELETE FROM product WHERE product_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, productId);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateProduct(Product product) {
        String query = "UPDATE product SET vendor_id = ?, name = ?, category = ?, original_price = ?, sale_price = ?, price_by_unit = ?, price_by_carton = ? WHERE product_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, product.getVendorId());
            pstmt.setString(2, product.getName());
            pstmt.setString(3, product.getCategory());
            pstmt.setDouble(4, product.getOriginalPrice());
            pstmt.setDouble(5, product.getSalePrice());
            pstmt.setDouble(6, product.getPriceByUnit());
            pstmt.setDouble(7, product.getPriceByCarton());
            pstmt.setInt(8, product.getProductId());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Product> searchProduct(String productName) {
        List<Product> productList = new ArrayList<>().reversed();
        String query = "SELECT * FROM product WHERE name LIKE ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, "%" + productName + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                productList.add(new Product(
                        rs.getInt("product_id"),
                        rs.getInt("vendor_id"),
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getDouble("original_price"),
                        rs.getDouble("sale_price"),
                        rs.getDouble("price_by_unit"),
                        rs.getDouble("price_by_carton")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }
}

