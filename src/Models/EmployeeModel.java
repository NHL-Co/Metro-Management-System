package Models;
import java.sql.*;

public class EmployeeModel {
    private final Connection conn = DBConnection.getInstance().getConnection();

    public boolean createTable() {
        String query = """
                CREATE TABLE IF NOT EXISTS employees (
                    emp_no INT AUTO_INCREMENT PRIMARY KEY,
                    name VARCHAR(55) NOT NULL,
                    email VARCHAR(55) NOT NULL,
                    password VARCHAR(55) NOT NULL,
                    branch_code VARCHAR(6),
                    emp_type VARCHAR(1) NOT NULL,
                    salary DOUBLE NOT NULL,
                    FOREIGN KEY (branch_code) REFERENCES branch(branch_code)
                );
                """;
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addEmployee(Employee employee) {
        String query = "INSERT INTO employees (name, email, password, branch_code, emp_type, salary) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, employee.getName());
            pstmt.setString(2, employee.getEmail());
            pstmt.setString(3, employee.getPassword());
            pstmt.setString(4, employee.getBranchCode());
            pstmt.setString(5, employee.getEmpType());
            pstmt.setDouble(6, employee.getSalary());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteEmployee(int empNo) {
        String query = "DELETE FROM employees WHERE emp_no = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, empNo);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateEmployee(Employee employee) {
        String query = "UPDATE employees SET name = ?, email = ?, password = ?, branch_code = ?, emp_type = ?, salary = ? WHERE emp_no = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, employee.getName());
            pstmt.setString(2, employee.getEmail());
            pstmt.setString(3, employee.getPassword());
            pstmt.setString(4, employee.getBranchCode());
            pstmt.setString(5, employee.getEmpType());
            pstmt.setDouble(6, employee.getSalary());
            pstmt.setInt(7, employee.getEmpNo());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean searchEmployee(int empNo) {
        String query = "SELECT * FROM employees WHERE emp_no = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, empNo);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

