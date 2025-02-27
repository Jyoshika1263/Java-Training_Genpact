package MovieTicketBooking;
 
import java.sql.*;
import java.util.*;
import java.util.Date;
 
public class TicketTesting {

	public static void Testing1() {
		Connection conn = null;
        CallableStatement cstmt = null;
        try {
            conn = DatabaseConnection.getConnection();
            if (conn == null) {
                System.out.println("Database Connection Failed");
                return;
            }
 
            // Calling the stored procedure
            cstmt = conn.prepareCall("{CALL GetMovieTitleAndGenre3(?,?,?)}");
 
            // Set the input parameter
            System.out.print("Enter your movie Id: ");
            Scanner scanner = new Scanner(System.in);
            int movieId = scanner.nextInt();
            cstmt.setInt(1, movieId);
 
            // Register the output parameter
            cstmt.registerOutParameter(2, Types.VARCHAR);
            cstmt.registerOutParameter(3, Types.VARCHAR);
 
            // Execute the stored procedure
            cstmt.execute();
 
            // Retrieve the output parameter value
            String title = cstmt.getString(2);
            String genre = cstmt.getString(3);
            System.out.println("Movie Title: " + title);
            System.out.println("Movie Genre: " + genre);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (cstmt != null) cstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	}


	public static void Testing2() {
		Connection conn = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        try {
            conn = DatabaseConnection.getConnection();
            if (conn == null) {
                System.out.println("Database Connection Failed");
                return;
            }
 
            // Calling the stored procedure
            cstmt = conn.prepareCall("{CALL GetMovieDetailsByUsername3(?)}");
 
            // Set the input parameter
            System.out.print("Enter your username: ");
            Scanner scanner = new Scanner(System.in);
            String username = scanner.nextLine();
            cstmt.setString(1, username);
 
            // Execute the stored procedure
            rs = cstmt.executeQuery();
 
            // Retrieve and print the result set
            while (rs.next()) {
                String name = rs.getString("username");
                String title = rs.getString("title");
                String genre = rs.getString("genre");
                int seatsBooked = rs.getInt("seats_booked");
                Time showTime = rs.getTime("show_time");
                Date bookingDate = rs.getDate("booking_date");
                // Print the columns
                System.out.println("=============================================================================================");
                System.out.println("Username: " + name);
                System.out.println("Movie Title: " + title);
                System.out.println("Movie Genre: " + genre);
                System.out.println("Seats Booked: " + seatsBooked);
                System.out.println("Show Time: " + showTime);
                System.out.println("Booking Date: " + bookingDate);
                System.out.println("=============================================================================================");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (cstmt != null) cstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	}
    public static void main(String[] args) {
        //Testing1();
        Testing2();
    }
}
