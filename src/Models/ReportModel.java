package Models;
import java.sql.*;

public class ReportModel {

    private final Connection conn = DBConnection.getInstance().getConnection();

    public boolean createTable() {
        String query = "CREATE TABLE IF NOT EXISTS report (" +
                "reportid INT AUTO_INCREMENT PRIMARY KEY, " +
                "branchCode VARCHAR(50), " +
                "rangeTag VARCHAR(50), " +
                "startDate DATE, " +
                "endDate DATE, " +
                "sales DOUBLE, " +
                "remainingStock INT, " +
                "profit DOUBLE)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addReport(Report report) {
        String query = "INSERT INTO report (branchCode, rangeTag, startDate, endDate, sales, remainingStock, profit) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, report.getBranchCode());
            stmt.setString(2, report.getRangeTag());
            stmt.setDate(3, report.getStartDate());
            stmt.setDate(4, report.getEndDate());
            stmt.setDouble(5, report.getSales());
            stmt.setInt(6, report.getRemainingStock());
            stmt.setDouble(7, report.getProfit());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteReport(int reportId) {
        String query = "DELETE FROM report WHERE reportid = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, reportId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateReport(Report report) {
        String query = "UPDATE report SET branchCode = ?, rangeTag = ?, startDate = ?, endDate = ?, " +
                "sales = ?, remainingStock = ?, profit = ? WHERE reportid = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, report.getBranchCode());
            stmt.setString(2, report.getRangeTag());
            stmt.setDate(3, report.getStartDate());
            stmt.setDate(4, report.getEndDate());
            stmt.setDouble(5, report.getSales());
            stmt.setInt(6, report.getRemainingStock());
            stmt.setDouble(7, report.getProfit());
            stmt.setInt(8, report.getReportId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Report searchReport(int reportId) {
        String query = "SELECT * FROM report WHERE reportid = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, reportId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Report(
                        rs.getInt("reportid"),
                        rs.getString("branchCode"),
                        rs.getString("rangeTag"),
                        rs.getDate("startDate"),
                        rs.getDate("endDate"),
                        rs.getDouble("sales"),
                        rs.getInt("remainingStock"),
                        rs.getDouble("profit")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
