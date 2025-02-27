package MovieTicketBooking;

import java.sql.*;
import java.util.Scanner;
 
public class AddShow {
 
    public static void addNewShow() throws SQLException {
        Scanner scanner = new Scanner(System.in);
 
        System.out.println("Enter the Movie ID:");
        int movieId = scanner.nextInt();
        scanner.nextLine(); // consume newline
 
        // Check if the movie ID exists in the movies table
        if (!doesMovieIdExist(movieId)) {
            System.out.println("Movie ID does not exist. Please add the movie first.");
            return;
        }
 
        System.out.println("Enter the Show Date (YYYY-MM-DD):");
        String showDate = scanner.nextLine();
        System.out.println("Enter the Show Time (HH:MM):");
        String showTime = scanner.nextLine();
        System.out.println("Enter the number of available seats:");
        int availableSeats = scanner.nextInt();
 
        String fullShowTime = showDate + " " + showTime + ":00"; // Combine date and time
 
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            System.out.println("Database Connection Failed");
            return;
        }
 
        String query = "INSERT INTO Shows (movie_id, show_time, available_seats) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, movieId);
            pstmt.setString(2, fullShowTime);
            pstmt.setInt(3, availableSeats);
            pstmt.executeUpdate();
            System.out.println("Show added successfully!");
        }
    }
 
    private static boolean doesMovieIdExist(int movieId) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            System.out.println("Database Connection Failed");
            return false;
        }
 
        String query = "SELECT * FROM movies WHERE Movie_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, movieId);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        }
    }
 
    public static void main(String[] args) throws SQLException {
        addNewShow();
    }
}






























//public class AddShow {
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}
//
//}
