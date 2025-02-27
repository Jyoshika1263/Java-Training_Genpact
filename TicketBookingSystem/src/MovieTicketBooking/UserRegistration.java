package MovieTicketBooking;

import java.sql.*;
import java.util.Scanner;
 
public class UserRegistration {
 
    public static void registerUser() throws SQLException {
        Scanner scanner = new Scanner(System.in);
 
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();
        System.out.println("Is this user an admin? (true/false):");
        boolean isAdmin = scanner.nextBoolean();
 
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            System.out.println("Database Connection Failed");
            return;
        }
 
        String query = "INSERT INTO users (username, pwd, admin) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setBoolean(3, isAdmin);
            pstmt.executeUpdate();
            System.out.println("User registered successfully!");
        }
    }
 
    public static void main(String[] args) throws SQLException {
        registerUser();
    }
}




















//public class UserRegistration {

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}
//
//}
