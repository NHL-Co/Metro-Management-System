package Models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import utilities.*;

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

    public boolean addProduct(Product product, String vendorName) {
        // Create an instance of VendorModel to access the getVendorIdByName method
        VendorModel vendorModel = new VendorModel();
        int vendorId = vendorModel.getVendorIdByName(vendorName);  // Get vendor_id based on vendor name

        // If vendor ID is not found, return false
        if (vendorId == -1) {
            System.out.println("Vendor not found!");
            return false;
        }

        // Proceed with adding the product using the valid vendor_id
        String query = "INSERT INTO product (vendor_id, name, category, original_price, sale_price, price_by_unit, price_by_carton) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, vendorId);  // Set the valid vendor_id
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
        List<Product> productList = new ArrayList<>();
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

    public Product getProduct(int id) {
        String query = "SELECT * FROM product WHERE product_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Product product = new Product(rs.getInt("product_id"), rs.getInt("vendor_id"),
                        rs.getString("name"), rs.getString("category"), rs.getDouble("original_price"),
                        rs.getDouble("sale_price"), rs.getDouble("price_by_unit"), rs.getDouble("price_by_carton"));
                return product;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
