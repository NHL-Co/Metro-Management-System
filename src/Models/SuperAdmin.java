package Models;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SuperAdmin {
    private final Connection conn = DBConnection.getInstance().getConnection();

    public void seedAdmin(String username, String password) {
        String query = "INSERT INTO super_admin (username, password) VALUES (?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Super admin seeded successfully.");
            }
        } catch (SQLException e) {
            System.out.println("Error seeding super admin: " + e.getMessage());
        }
    }
}
