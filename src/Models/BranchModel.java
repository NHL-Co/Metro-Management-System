package Models;
import java.sql.*;
import utilities.*;

public class BranchModel {

    private final Connection conn = DBConnection.getInstance().getConnection();

    public boolean createTable() {
        String query = "CREATE TABLE IF NOT EXISTS branch (" +
                "branch_code VARCHAR(6) PRIMARY KEY, " +
                "city VARCHAR(55), " +
                "address VARCHAR(100), " +
                "phone VARCHAR(11), " +
                "n_employees INT)";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addBranch(Branch branch) {
        String query = "INSERT INTO branch (branch_code, city, address, phone, n_employees) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, branch.getBranchCode());
            stmt.setString(2, branch.getCity());
            stmt.setString(3, branch.getAddress());
            stmt.setString(4, branch.getPhone());
            stmt.setInt(5, branch.getNEmployees());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteBranch(Branch branch) {
        String query = "DELETE FROM branch WHERE branch_code = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, branch.getBranchCode());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateBranch(Branch branch) {
        String query = "UPDATE branch SET city=?, address=?, phone=?, n_employees=? WHERE branch_code=?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, branch.getCity());
            stmt.setString(2, branch.getAddress());
            stmt.setString(3, branch.getPhone());
            stmt.setInt(4, branch.getNEmployees());
            stmt.setString(5, branch.getBranchCode());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean searchBranch(Branch branch) {
        String query = "SELECT * FROM branch WHERE branch_code=?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, branch.getBranchCode());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("Branch Found: " + rs.getString("city"));
                return true;
            } else {
                System.out.println("Branch not found.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
