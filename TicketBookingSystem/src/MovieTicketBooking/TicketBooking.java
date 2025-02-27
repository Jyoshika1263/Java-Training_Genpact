package MovieTicketBooking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class TicketBooking {
    public static void bookTicket() throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            System.out.println("Database Connection Failed");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        ShowAvailableMovies.display();
        System.out.print("Enter the show_id you want to book: ");
        int showId = scanner.nextInt();

        String checkSeatsQuery = "SELECT available_seats FROM shows WHERE show_id = ?;";
        int availableSeats = 0;
        try (PreparedStatement pstmt = conn.prepareStatement(checkSeatsQuery)) {
            pstmt.setInt(1, showId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                availableSeats = rs.getInt("available_seats");
            } else {
                System.out.println("Show not found.");
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        System.out.print("Enter the number of seats you want to book: ");
        int numberOfSeats = scanner.nextInt();

        if (availableSeats >= numberOfSeats) {
            String bookTicketQuery = "INSERT INTO booking (username, show_id, seats_booked) VALUES (?, ?, ?);";
            try (PreparedStatement pstmt = conn.prepareStatement(bookTicketQuery)) {
                pstmt.setString(1, username);
                pstmt.setInt(2, showId);
                pstmt.setInt(3, numberOfSeats);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                return;
            }

            String updateSeatsQuery = "UPDATE shows SET available_seats = available_seats - ? WHERE show_id = ?;";
            try (PreparedStatement pstmt = conn.prepareStatement(updateSeatsQuery)) {
                pstmt.setInt(1, numberOfSeats);
                pstmt.setInt(2, showId);
                int rowsUpdated = pstmt.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Ticket booked successfully!");
                } else {
                    System.out.println("Failed to update available seats.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Not enough seats available.");
        }
    }
}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//	Connection conn=DatabaseConnection.getConnection();
//	public void bookTicket() {
//		
//		if(conn==null)
//		{
//			System.out.println("Database connection failed");
//			return;
//		}
//		
//        Scanner sc = new Scanner(System.in);
//        System.out.print("Enter your username: ");
//        String username = sc.nextLine();
//        t.showAvailableMovies();
//        System.out.print("Enter the show ID you want to book: ");
//        int showId = sc.nextInt();
//        int availableSeats = checkSeats(showId);
//        if (availableSeats == -1) {
//            System.out.println("Invalid show ID.");
//            return;
//        }
//        System.out.print("Enter the number of seats to book: ");
//        int noOfSeats = sc.nextInt();
//        if (availableSeats >= noOfSeats) {
//            bookSeats(username, showId, noOfSeats);
//            updateSeats(showId, availableSeats - noOfSeats);
//        } else {
//            System.out.println("Not enough available seats.");
//        }
//    }
//    private int checkSeats(int showId) {
//        String query = "SELECT available_seats FROM shows WHERE show_id = ?";
//        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
//            pstmt.setInt(1, showId);
//            ResultSet rs = pstmt.executeQuery();
//            if (rs.next()) {
//                return rs.getInt("available_seats");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return -1;
//    }
//
//    private void bookSeats(String username, int showId, int noOfSeats) {
//        String query = "INSERT INTO booking (username, show_id, seats_booked) VALUES (?, ?, ?)";
//        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
//            pstmt.setString(1, username);
//            pstmt.setInt(2, showId);
//            pstmt.setInt(3, noOfSeats);
//            pstmt.executeUpdate();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void updateSeats(int showId, int newSeatCount) {
//        String query = "UPDATE shows SET available_seats = ? WHERE show_id = ?";
//        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
//            pstmt.setInt(1, newSeatCount);
//            pstmt.setInt(2, showId);
//            pstmt.executeUpdate();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void main(String[] args) {
//        // Assuming you have a valid connection object 'conn'
//        // Initialize your connection here
//        TicketBooking t = new TicketBooking();
//        t.bookTicket();
//    }
//}
//	
//	
	
	
	
	
	
	
	
	
	
	
	
	



//	bookticketing-> step 1:scanner->username  
//    step 2:showavailableMovies()->all movies
//    step 3:ask user to choose show id->show id->1
//    step 4:check available seats->sql query select prepstatement->pass show id store it in some var->checkSeats
//    step 5:ask user->how many seats to be booked->noOfSeats
//           if->checkSeats>noOfSeats:
//    step 6:insert into booking table->username,show id,seats->prepared statement
//    step 7:update shows table->reduce seats by noOfseats
//            else print("---------")

