package MovieTicketBooking;
 
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Scanner;
 
public class TicketCancellationProcedure {
 
    public static void createStoredProcedures() throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            System.out.println("Database Connection Failed");
            return;
        }
 
        String GetAllBookingsProcedure =
        		"CREATE PROCEDURE GetAllBookingsByUsername(IN user_name VARCHAR(255)) " 
        				+ "BEGIN " 
        				+  "    SELECT booking_id, show_id, seats_booked, booking_date " 
        				+  "    FROM booking " 
        				+  "    WHERE username = user_name; " 
        				+   "END; ";
 
        String createCancelBookingProcedure = "CREATE PROCEDURE CancelBooking(IN bookingId INT) " 
                		+  "BEGIN " 
                		+   "    DECLARE showId INT; " 
                		+    "    DECLARE seatsBooked INT; " 
                +     "SELECT show_id, seats_booked INTO showId, seatsBooked FROM booking WHERE booking_id = bookingId; " 
                +  "    UPDATE shows SET available_seats = available_seats + seatsBooked WHERE show_id = showId; " 
                +   "    DELETE FROM booking WHERE booking_id = bookingId; " 
                +  "END; ";
 
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(GetAllBookingsProcedure);
            stmt.execute(createCancelBookingProcedure);
            System.out.println("Stored procedures created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 
    public static void cancelTicket() throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            System.out.println("Database Connection Failed");
            return;
        }
        Scanner scanner = new Scanner(System.in);
 
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
 
        try (CallableStatement csmt = conn.prepareCall("{call GetAllBookingsByUsername(?)}")) {
            csmt.setString(1, username);
 
            ResultSet rs = csmt.executeQuery();
 
            System.out.println("Your bookings:");
            while (rs.next()) {
                int bookingId = rs.getInt("booking_id");
                int showId = rs.getInt("show_id");
                int seatsBooked = rs.getInt("seats_booked");
                Timestamp bookingDate = rs.getTimestamp("booking_date");
 
                System.out.println("Booking ID: " + bookingId + ", Show ID: " + showId + ", Seats Booked: " + seatsBooked + ", Booking Date: " + bookingDate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
 
        System.out.print("Enter the booking ID you want to cancel: ");
        int bookingId = scanner.nextInt();
 
        try (CallableStatement csmt = conn.prepareCall("{call CancelBooking(?)}")) {
            csmt.setInt(1, bookingId);
            csmt.execute();
 
            System.out.println("Booking cancelled successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            scanner.close(); 
        }
    }
 
    public static void main(String[] args) throws SQLException {
        createStoredProcedures();
        cancelTicket();
    }
}
