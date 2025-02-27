package MovieTicketBooking;

import java.util.*;
import java.sql.*;
 
public class StoredProcedureCallable {
    public static void storedProcedure() throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            System.out.println("Database Connection Failed");
            return;
        }
        Scanner scanner = new Scanner(System.in);
 
        String procedure = "CREATE PROCEDURE GetMovieTitleAndGenre3(IN movieId INT, OUT movietitle VARCHAR(255), OUT movieGenre VARCHAR(255)) "
                         + "BEGIN "
                         + "SELECT title, genre INTO movietitle, movieGenre FROM movies "
                         + "WHERE movie_id = movieId; "
                         + "END;";
        Statement stmt = conn.createStatement();
        // Execute the stored procedure creation
        stmt.execute(procedure);
        System.out.println("Stored procedure created successfully.");
    }

    public static void ViewUserBookings() throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            System.out.println("Database Connection Failed");
            return;
        }
 
        String procedure = "CREATE PROCEDURE GetMovieDetailsByUsername3(IN user_name VARCHAR(255))"
                         + "BEGIN "
                         + "select * from movies join shows on movies.movie_id = shows.movie_id join booking on booking.show_id = shows.Show_id where username = user_name;"
                         + "END;";
        Statement stmt = conn.createStatement();
        // Execute the stored procedure creation
        stmt.execute(procedure);
        System.out.println("View Bookings procedure created successfully.");
    }
 
    public static void main(String[] args) throws SQLException {
        //storedProcedure();
    	ViewUserBookings();
    }
}