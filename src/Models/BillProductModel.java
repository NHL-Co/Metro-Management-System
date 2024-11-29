package Models;

import java.sql.*;

public class BillProductModel {
    private final Connection conn = DBConnection.getInstance().getConnection();

    public boolean createTable() {
        String query = "CREATE TABLE IF NOT EXISTS bill_product (" +
                "bill_id INT, " +
                "product_id INT, " +
                "quantity INT, " +
                "PRIMARY KEY (bill_id, product_id))";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addBillProduct(BillProduct bp) {
        String query = "INSERT INTO bill_product (bill_id, product_id, quantity) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, bp.getBillId());
            stmt.setInt(2, bp.getProductId());
            stmt.setInt(3, bp.getQuantity());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteBillProduct(BillProduct bp) {
        String query = "DELETE FROM bill_product WHERE bill_id = ? AND product_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, bp.getBillId());
            stmt.setInt(2, bp.getProductId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateBillProduct(BillProduct bp) {
        String query = "UPDATE bill_product SET quantity = ? WHERE bill_id = ? AND product_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, bp.getQuantity());
            stmt.setInt(2, bp.getBillId());
            stmt.setInt(3, bp.getProductId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean searchBillProduct(BillProduct bp) {
        String query = "SELECT * FROM bill_product WHERE bill_id = ? AND product_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, bp.getBillId());
            stmt.setInt(2, bp.getProductId());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("BillProduct Found: Quantity = " + rs.getInt("quantity"));
                return true;
            } else {
                System.out.println("BillProduct not found.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
