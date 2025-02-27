package HMSSystem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
 
public class StaffDAO {
    public void addStaff(Staff staff) {
        String query = "INSERT INTO Staff (name,contact_number) VALUES (?, ?);";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement psmt = conn.prepareStatement(query)) {
            psmt.setString(1, staff.getName());
            psmt.setString(2, staff.getContactNumber());
            psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Staff> getAllStaff() throws SQLException {
        List<Staff> staffList = new ArrayList<>();
        String query = "SELECT * FROM Staff;";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement smt = conn.createStatement();
             ResultSet rs = smt.executeQuery(query)) {
            while (rs.next()) {
                Staff staff = new Staff(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("contact_number")
                );
                staffList.add(staff);
            }
            return staffList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
    public void updateStaff(int id, Staff staff) {
        String query = "UPDATE Staff SET name = ?, contact_number = ? WHERE id = ?;";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement psmt = conn.prepareStatement(query)) {
            psmt.setString(1, staff.getName());
            psmt.setString(2, staff.getContactNumber());
            psmt.setInt(3, id);
            psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteStaff(int id) {
        String query = "DELETE FROM Staff WHERE id = ?;";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement psmt = conn.prepareStatement(query)) {
            psmt.setInt(1, id);
            psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}




























//public class StaffDAO {
//
//}
