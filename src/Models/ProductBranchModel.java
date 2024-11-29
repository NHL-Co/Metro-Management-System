package Models;
import java.sql.*;
import utilities.*;

public class ProductBranchModel {
    private final Connection conn = DBConnection.getInstance().getConnection();

    public boolean createTable() {
        String query = "CREATE TABLE IF NOT EXISTS product_branch (" +
                "product_id INT, " +
                "branch_code VARCHAR(50), " +
                "quantity INT, " +
                "PRIMARY KEY (product_id, branch_code))";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addProductBranch(ProductBranch pb) {
        String query = "INSERT INTO product_branch (product_id, branch_code, quantity) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, pb.getProductId());
            stmt.setString(2, pb.getBranchCode());
            stmt.setInt(3, pb.getQuantity());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteProductBranch(ProductBranch pb) {
        String query = "DELETE FROM product_branch WHERE product_id = ? AND branch_code = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, pb.getProductId());
            stmt.setString(2, pb.getBranchCode());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateProductBranch(ProductBranch pb) {
        String query = "UPDATE product_branch SET quantity = ? WHERE product_id = ? AND branch_code = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, pb.getQuantity());
            stmt.setInt(2, pb.getProductId());
            stmt.setString(3, pb.getBranchCode());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean searchProductBranch(ProductBranch pb) {
        String query = "SELECT * FROM product_branch WHERE product_id = ? AND branch_code = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, pb.getProductId());
            stmt.setString(2, pb.getBranchCode());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("ProductBranch Found: Quantity = " + rs.getInt("quantity"));
                return true;
            } else {
                System.out.println("ProductBranch not found.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
