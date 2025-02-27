package HMSSystem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
 
public class AppointmentDAO {
	private boolean isPatientRegistered(int patientId) throws SQLException {
		String query = "SELECT * from Patient where id = ?;";
		try(Connection conn = DatabaseConnection.getConnection();
				PreparedStatement psmt = conn.prepareStatement(query)) {
			psmt.setInt(1, patientId);
			ResultSet rs = psmt.executeQuery();
			if(rs.next() && rs.getInt(1)>0) {
				return true;
			}
		}
		return false;
	}

 
    private boolean isDoctorRegistered(int doctorId) throws SQLException {
        String query = "SELECT COUNT(*) FROM Doctor WHERE id = ?;";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement psmt = conn.prepareStatement(query)) {
            psmt.setInt(1, doctorId);
            ResultSet rs = psmt.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    return true;
                }
        }
        return false;
    }
 
    public void addAppointment(Appointment appointment) throws SQLException {
        if (!isPatientRegistered(appointment.getPatientId()) || !isDoctorRegistered(appointment.getDoctorId())) {
            System.out.println("Patient ID or Doctor ID not registered.");
            return;
        }

 
        String query = "INSERT INTO Appointment (patient_id, doctor_id, appointment_date) VALUES (?, ?, ?);";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement psmt = conn.prepareStatement(query)) {
            psmt.setInt(1, appointment.getPatientId());
            psmt.setInt(2, appointment.getDoctorId());
            psmt.setString(3, appointment.getAppointmentDate());
            psmt.executeUpdate();
            System.out.println("Appointment added successfully!");
        }
    }
    public Appointment getAppointmentById(int id) throws SQLException {
        String query = "SELECT * FROM Appointment WHERE id = ?;";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement psmt = conn.prepareStatement(query)) {
            psmt.setInt(1, id);
            ResultSet rs = psmt.executeQuery();
            if (rs.next()) {
                return new Appointment(
                        rs.getInt("id"),
                        rs.getInt("patient_id"),
                        rs.getInt("doctor_id"),
                        rs.getString("appointment_date")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return null;
    }
 
    public List<Appointment> getAllAppointments() throws SQLException {
        List<Appointment> appointments = new ArrayList<>();
        String query = "SELECT * FROM Appointment;";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement smt = conn.createStatement();
             ResultSet rs = smt.executeQuery(query)) {
            while (rs.next()) {
                Appointment appointment = new Appointment(
                        rs.getInt("id"),
                        rs.getInt("patient_id"),
                        rs.getInt("doctor_id"),
                        rs.getString("appointment_date")
                );
                appointments.add(appointment);
            }
            return appointments;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
 
 
    public void updateAppointment(int id, Appointment appointment) throws SQLException {
        if (!isPatientRegistered(appointment.getPatientId())) {
            System.out.println("Patient ID not registered.");
            return;
        }
        if (!isDoctorRegistered(appointment.getDoctorId())) {
            System.out.println("Doctor ID not registered.");
            return;
        }
 
        String query = "UPDATE Appointment SET patient_id = ?, doctor_id = ?, appointment_date = ? WHERE id = ?;";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement psmt = conn.prepareStatement(query)) {
            psmt.setInt(1, appointment.getPatientId());
            psmt.setInt(2, appointment.getDoctorId());
            psmt.setString(3, appointment.getAppointmentDate());
            psmt.setInt(4, id);
            psmt.executeUpdate();
            System.out.println("Appointment updated successfully!");
        }
    }
 
 
    public void deleteAppointment(int id) {
        String query = "DELETE FROM Appointment WHERE id = ?;";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement psmt = conn.prepareStatement(query)) {
            psmt.setInt(1, id);
            psmt.executeUpdate();
            System.out.println("Appointment deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}






























//
//    // Book an appointment
//    public void bookAppointment(Appointment appointment) throws SQLException {
//        String query = "INSERT INTO appointments (appointment_date, reason) VALUES (?, ?)";
//        try (Connection conn = DatabaseConnection.getConnection();
//                PreparedStatement psmt = conn.prepareStatement(query)) {
//                psmt.setString(1, appointment.getAppointmentDate());
//                psmt.setString(2, appointment.getReason());
//                psmt.executeUpdate();
//        }
//    }
//    public List<Patient> getAllPatients() throws SQLException {
//        List<Patient> patients = new ArrayList<>();
//        String query = "SELECT * FROM patient";
//        try (Connection conn = DatabaseConnection.getConnection();
//             Statement stmt = conn.createStatement();
//             ResultSet rs = stmt.executeQuery(query)) {
// 
//            while (rs.next()) {
//                Patient patient = new Patient(
//                    rs.getString("first_name"),
//                    rs.getString("last_name"),
//                    rs.getInt("age"),
//                    rs.getString("gender"),
//                    rs.getString("contact_number")
//                );
//                patients.add(patient);
//            }
//        }
//        return patients;
//    } 
//    
//    // View all appointments
//    public List<Appointment> viewAppointments() throws SQLException {
//        List<Appointment> appointments = new ArrayList<>();
//        String query = "SELECT * FROM appointment";
//        try (Connection conn = DatabaseConnection.getConnection();
//                Statement stmt = conn.createStatement();
//                ResultSet rs = stmt.executeQuery(query)) {
//            while (rs.next()) {
//            	Appointment appointment = new Appointment(
//                        rs.getInt("patient_id"),
//                        rs.getInt("doctor_id"),
//                        rs.getString("appointment_date"),
//                        rs.getString("reason")
//                    );
//                appointments.add(appointment);
//            }
//        }
//        return appointments;
//    }
//
//    // Cancel an appointment
//    public void cancelAppointment(int id) throws SQLException {
//        String query = "DELETE FROM appointment WHERE id = ?";
//        try (Connection conn = DatabaseConnection.getConnection();
//            PreparedStatement psmt = conn.prepareStatement(query)) {
//            psmt.setInt(1, id);
//            psmt.executeUpdate();
//        }
//    }
//   
//    // Check if patient is registered
//    public boolean isPatientRegistered(int patientId) throws SQLException {
//        String query = "SELECT COUNT(*) FROM patients WHERE id = ?";
//        try (Connection conn = DatabaseConnection.getConnection();
//            PreparedStatement psmt = conn.prepareStatement(query)) {
//            psmt.setInt(1, patientId);
//            try (ResultSet rs = psmt.executeQuery()) {
//                if (rs.next()) {
//                    return rs.getInt(1) > 0;
//                }
//            }
//        }
//        return false;
//    }
//
//    // Check if doctor exists
//    public boolean isDoctorExists(int doctorId) throws SQLException {
//        String query = "SELECT COUNT(*) FROM doctors WHERE id = ?";
//        try (Connection conn = DatabaseConnection.getConnection();
//                PreparedStatement psmt = conn.prepareStatement(query)) {
//            psmt.setInt(1, doctorId);
//            try (ResultSet rs = psmt.executeQuery()) {
//                if (rs.next()) {
//                    return rs.getInt(1) > 0;
//                }
//            }
//        }
//        return false;
//    }

