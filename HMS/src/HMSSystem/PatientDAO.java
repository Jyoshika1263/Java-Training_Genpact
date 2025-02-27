package HMSSystem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class PatientDAO {
 
    public void addPatient(Patient patient) throws SQLException {
        String query = "INSERT INTO patient (first_name, last_name, age, gender, contact_number) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement psmt = conn.prepareStatement(query)) {
 
            psmt.setString(1, patient.getFirst_name());
            psmt.setString(2, patient.getLast_name());
            psmt.setInt(3, patient.getAge());
            psmt.setString(4, patient.getGender());
            psmt.setString(5, patient.getContact_number());
            psmt.executeUpdate();
            System.out.println("Patient added successfully!");
        }
    }
 
    public List<Patient> getAllPatients() throws SQLException {
        List<Patient> patients = new ArrayList<>();
        String query = "SELECT * FROM patient";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
 
            while (rs.next()) {
                Patient patient = new Patient(
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getInt("age"),
                    rs.getString("gender"),
                    rs.getString("contact_number")
                );
                patients.add(patient);
            }
        }
        return patients;
    }

    public void updatePatient(int id, Patient patient) throws SQLException {
        String query = "UPDATE patient SET first_name = ?, last_name = ?, age = ?, gender = ?, contact_number = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement psmt = conn.prepareStatement(query)) {
 
            psmt.setString(1, patient.getFirst_name());
            psmt.setString(2, patient.getLast_name());
            psmt.setInt(3, patient.getAge());
            psmt.setString(4, patient.getGender());
            psmt.setString(5, patient.getContact_number());
            psmt.setInt(6, id);
            int rowsAffected = psmt.executeUpdate();
 
            if (rowsAffected > 0) {
                System.out.println("Patient updated successfully!");
            } else {
                System.out.println("Failed to update patient.");
            }
        }
    }
 
    public void deletePatient(int id) throws SQLException {
        String query = "DELETE FROM patient WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement psmt = conn.prepareStatement(query)) {
 
            psmt.setInt(1, id);
            int rowsAffected = psmt.executeUpdate();
 
            if (rowsAffected > 0) {
                System.out.println("Patient deleted successfully!");
            } else {
                System.out.println("Failed to delete patient.");
            }
        }
    }
}




















//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//
//public class PatientDAO { //db
//     //methods for patient mgmt
//	//1.add a patient
//	public void addPatient(Patient patient) throws SQLException {
//		String query="Insert into Patient (first_name,last_name,age,gender,contact_number) values (?,?,?,?,?)";
//		try(Connection conn=DatabaseConnection.getConnection();
//			PreparedStatement psmt=conn.prepareStatement(query)){
////			psmt.setInt(1, patient.getId());
//			psmt.setString(1, patient.getFname());
//			psmt.setString(2, patient.getLname());
//			psmt.setInt(3, patient.getAge());
//			psmt.setString(4, patient.getGender());
//			psmt.setString(5, patient.getContactNumber());
//		}
//	}
//	//create method to view all patients
//	public List<Patient> getAllPatients() throws SQLException {
//		List<Patient> patients =new ArrayList<>();
//		String query="Select * from Patient (first_name,last_name,age,gender,contact_number) values (?,?,?,?,?)";
//		try(Connection conn=DatabaseConnection.getConnection();
//				Statement smt=conn.createStatement();
//				ResultSet rs=smt.executeQuery(query)) {
//				while(rs.next()) {
//					Patient patient=new Patient(
//							
//							rs.getString("fname"),
//							rs.getString("lname"),
//							rs.getInt("age"),
//							rs.getString("gender"),
//							rs.getString("contact_number")
//							);
//					patients.add(patient);
//				}
//				return patients;
//		}
//	}
//	//update patient record method
//	public void updatePatient(int id, Patient patient) throws SQLException {
//        String query = "UPDATE patient SET first_name = ?, last_name = ?, age = ?, gender = ?, contact_number = ? WHERE id = ?";
//        try (Connection conn = DatabaseConnection.getConnection();
//             PreparedStatement psmt = conn.prepareStatement(query)) {
////        	psmt.setInt(1, id);
//            psmt.setString(1, patient.getFname());
//            psmt.setString(2, patient.getLname());
//            psmt.setInt(3, patient.getAge());
//            psmt.setString(4, patient.getGender());
//            psmt.setString(5, patient.getContactNumber());
//            
//            int rowsAffected = psmt.executeUpdate();
// 
//            if (rowsAffected > 0) {
//                System.out.println("Patient updated successfully!");
//            } else {
//                System.out.println("Failed to update patient.");
//            }
//        }
//    }
//    public void deletePatient(int id) throws SQLException {
//        String query = "DELETE FROM patient WHERE id = ?";
//        try (Connection conn = DatabaseConnection.getConnection();
//             PreparedStatement psmt = conn.prepareStatement(query)) {
// 
//            psmt.setInt(1, id);
//            int rowsAffected = psmt.executeUpdate();
// 
//            if (rowsAffected > 0) {
//                System.out.println("Patient deleted successfully!");
//            } else {
//                System.out.println("Failed to delete patient.");
//            }
//        }
//    }
//}
//	//delete patient record method

