package MovieTicketBooking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Scanner;

//	//fetch query based on 
//     //ask username
//	//relating all three tables, use joins
//	//booking->username,booking id
//	//movies->movie name
//	//->shows->showtime,seats booked->
	
public class BookingConfirmation {
	 
    public static void confirmBooking() throws SQLException {
        Scanner scanner = new Scanner(System.in);
 
       
        System.out.println("Enter your username:");
        String userName = scanner.nextLine();
 
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            System.out.println("Database Connection Failed");
            return;
        }
 

        String query = "SELECT b.booking_id, b.username, m.title, s.show_time, b.seats_booked " +
                       "FROM booking b " +
                       "JOIN shows s ON b.show_id = s.show_id " +
                       "JOIN movies m ON s.movie_id = m.movie_id " +
                       "WHERE b.username = ?";
 
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, userName);
            ResultSet rs = pstmt.executeQuery();
 
            while (rs.next()) {
                int bookingId = rs.getInt("booking_id");
                String movieName = rs.getString("title");
                Timestamp showTime = rs.getTimestamp("show_time");
                int seatsBooked = rs.getInt("seats_booked");
 
                System.out.println("Booking ID: " + bookingId);
                System.out.println("Username: " + userName);
                System.out.println("Movie Name: " + movieName);
                System.out.println("Show Time: " + showTime);
                System.out.println("Seats Booked: " + seatsBooked);
                System.out.println("---------------------------");
            }
        }
    }
//    public static void main(String args[]) throws SQLException
//    {
//    	confirmBooking();
//}
}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
// 
//        // Ask for username
//        System.out.print("Enter your username: ");
//        String username = scanner.nextLine();
// 
//        // Fetch booking confirmation details
//        fetchBookingDetails(username);
//    }
// 
//    private static void fetchBookingDetails(String username) {
//        String query = "SELECT b.username, b.booking_id, m.title AS moviename, s.show_time, b.seats_booked " +
//                       "FROM booking b " +
//                       "JOIN shows s ON b.show_id = s.Show_id " +
//                       "JOIN movies m ON s.movie_id = m.movie_id " +
//                       "WHERE b.username = ?";
// 
//        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_booking", "root", "Genpact@123456789");
//             PreparedStatement pstmt = conn.prepareStatement(query)) {
// 
//            pstmt.setString(1, username);
//            ResultSet rs = pstmt.executeQuery();
// 
//            while (rs.next()) {
//                System.out.println("Username: " + rs.getString("username"));
//                System.out.println("Booking ID: " + rs.getInt("booking_id"));
//                System.out.println("Movie Name: " + rs.getString("moviename"));
//                System.out.println("Showtime: " + rs.getTimestamp("show_time"));
//                System.out.println("Seats Booked: " + rs.getInt("seats_booked"));
//                System.out.println("-----------------------------");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}
