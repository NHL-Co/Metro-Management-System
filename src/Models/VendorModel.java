package Models;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import utilities.*;
public class VendorModel {
    private final Connection conn = DBConnection.getInstance().getConnection();

    public void createTable() {
        String query = "CREATE TABLE IF NOT EXISTS vendor (" +
                "vendor_id INT PRIMARY KEY AUTO_INCREMENT, " +
                "name VARCHAR(100), " +
                "cnic CHAR(13), " +
                "phone_number CHAR(11))";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean addVendor(Vendor vendor) {
        String query = "INSERT INTO vendor (name, cnic, phone_number) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, vendor.getName());
            pstmt.setString(2, vendor.getCnic());
            pstmt.setString(3, vendor.getPhoneNumber());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteVendor(int vendorId) {
        String query = "DELETE FROM vendor WHERE vendor_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, vendorId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateVendor(Vendor vendor) {
        String query = "UPDATE vendor SET name = ?, cnic = ?, phone_number = ? WHERE vendor_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, vendor.getName());
            pstmt.setString(2, vendor.getCnic());
            pstmt.setString(3, vendor.getPhoneNumber());
            pstmt.setInt(4, vendor.getVendorId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Vendor> searchVendor(String name) {
        ArrayList<Vendor> vendorList = new ArrayList<>();
        String query = "SELECT * FROM vendor WHERE name LIKE ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, "%" + name + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                vendorList.add(new Vendor(
                        rs.getInt("vendor_id"),
                        rs.getString("name"),
                        rs.getString("cnic"),
                        rs.getString("phone_number")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vendorList;
    }

}
