package Models;

import java.sql.*;

public class BillModel {
    private final Connection conn = DBConnection.getInstance().getConnection();

    public boolean createTable() {
        String query = "CREATE TABLE IF NOT EXISTS bill (" +
                "bill_id INT PRIMARY KEY AUTO_INCREMENT, " +
                "date DATE, " +
                "cash TINYINT(1), " +
                "total_amount DOUBLE, " +
                "tax DOUBLE, " +
                "net_amount DOUBLE)";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addBill(Bill bill) {
        String query = "INSERT INTO bill (date, cash, total_amount, tax, net_amount) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, bill.getDate());
            stmt.setBoolean(2, bill.isCash());
            stmt.setDouble(3, bill.getTotalAmount());
            stmt.setDouble(4, bill.getTax());
            stmt.setDouble(5, bill.getNetAmount());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteBill(Bill bill) {
        String query = "DELETE FROM bill WHERE bill_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, bill.getBillId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateBill(Bill bill) {
        String query = "UPDATE bill SET date = ?, cash = ?, total_amount = ?, tax = ?, net_amount = ? WHERE bill_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, bill.getDate());
            stmt.setBoolean(2, bill.isCash());
            stmt.setDouble(3, bill.getTotalAmount());
            stmt.setDouble(4, bill.getTax());
            stmt.setDouble(5, bill.getNetAmount());
            stmt.setInt(6, bill.getBillId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean searchBill(Bill bill) {
        String query = "SELECT * FROM bill WHERE bill_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, bill.getBillId());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("Bill Found: ID = " + rs.getInt("bill_id"));
                return true;
            } else {
                System.out.println("Bill not found.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
