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
        return false;
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

    // New Method: Fetch Vendor Names
    public String[] getVendorNames() {
        List<String> vendorNames = new ArrayList<>();
        String query = "SELECT name FROM vendor";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                vendorNames.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vendorNames.toArray(new String[0]);
    }

    public int getVendorIdByName(String vendorName) {
        String query = "SELECT vendor_id FROM vendor WHERE name = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, vendorName);  // Use the vendor name to fetch vendor_id
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("vendor_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;  // Return -1 if vendor not found
    }
}


