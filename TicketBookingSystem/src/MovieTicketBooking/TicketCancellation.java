package MovieTicketBooking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class TicketCancellation {
     //username
	//date->curdate comparison->optional
	//update shows table-> available seats should be increased
	//booking->table->delete that booking id row
	public static void cancelTicket() throws SQLException {
        Scanner scanner = new Scanner(System.in);
 
        System.out.println("Enter your username:");
        String userName = scanner.nextLine();
        System.out.println("Enter your booking ID:");
        int bookingId = scanner.nextInt();
 
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            System.out.println("Database Connection Failed");
            return;
        }
      
        String checkBookingQuery = "SELECT show_id, seats_booked FROM booking WHERE booking_id = ? AND username = ?";
        try (PreparedStatement checkStmt = conn.prepareStatement(checkBookingQuery)) {
            checkStmt.setInt(1, bookingId);
            checkStmt.setString(2, userName);
            ResultSet rs = checkStmt.executeQuery();
 
            if (rs.next()) {
                int showId = rs.getInt("show_id");
                int seatsBooked = rs.getInt("seats_booked");
 
               
                String deleteBookingQuery = "DELETE FROM booking WHERE booking_id = ?";
                try (PreparedStatement deleteStmt = conn.prepareStatement(deleteBookingQuery)) {
                    deleteStmt.setInt(1, bookingId);
                    deleteStmt.executeUpdate();
                }    
                String updateSeatsQuery = "UPDATE shows SET available_seats = available_seats + ? WHERE show_id = ?";
                try (PreparedStatement updateStmt = conn.prepareStatement(updateSeatsQuery)) {
                    updateStmt.setInt(1, seatsBooked);
                    updateStmt.setInt(2, showId);
                    updateStmt.executeUpdate();
                }
 
                System.out.println("Booking cancelled successfully!");
            } else {
                System.out.println("No such booking found for the given username and booking ID.");
            }
        }
	}
	public static void main(String args[]) throws SQLException
    {
		cancelTicket();
    }
}
