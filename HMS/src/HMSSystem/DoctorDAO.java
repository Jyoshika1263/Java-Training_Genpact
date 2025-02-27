package HMSSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {
	public void addDoctor(Doctor doctor) throws SQLException {
        String query = "INSERT INTO doctor (name, specialty,contact) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement psmt = conn.prepareStatement(query)) {
            psmt.setString(1, doctor.getName());
            psmt.setString(2, doctor.getSpeciality());
            psmt.setString(3, doctor.getContact());
            psmt.executeUpdate();
            System.out.println("Doctor added successfully!");
        }
    }
    public List<Doctor> getAllDoctors() throws SQLException {
        List<Doctor> doctors = new ArrayList<>();
        String query = "SELECT * FROM doctor";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) { 
            while (rs.next()) {
                Doctor doctor = new Doctor(
                    rs.getString("name"),
                    rs.getString("specialty"),
                    rs.getString("contact")
                );
                doctors.add(doctor);
            }
        }
        return doctors;
    }
    public void updateDoctor(int id, Doctor doctor) throws SQLException {
        String query = "UPDATE doctor SET name = ?, specialty = ?, contact = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement psmt = conn.prepareStatement(query)) {
 
            psmt.setString(1, doctor.getName());
            psmt.setString(2, doctor.getSpeciality());
            psmt.setString(3, doctor.getContact());
            psmt.setInt(4, id);
            int rowsAffected = psmt.executeUpdate();
 
            if (rowsAffected > 0) {
                System.out.println("Doctor updated successfully!");
            } else {
                System.out.println("Failed to update doctor.");
            }
        }
    } 
    public void deleteDoctor(int id) throws SQLException {
        String query = "DELETE FROM doctor WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement psmt = conn.prepareStatement(query)) {
 
            psmt.setInt(1, id);
            int rowsAffected = psmt.executeUpdate();
 
            if (rowsAffected > 0) {
                System.out.println("Doctor deleted successfully!");
            } else {
                System.out.println("Failed to delete doctor.");
            }
        }
    }
}
