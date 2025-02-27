package MovieTicketBooking;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.SQLException;


public class StoredProcedure {

	public static void storedProcedure() throws SQLException  {
        Connection conn = DatabaseConnection.getConnection();

        if (conn == null) {
            System.out.println("Database connection failed");
            return;
        }
        Scanner scanner = new Scanner(System.in);
//        System.out.print("Enter your movie Id: ");
//        int movieId = scanner.nextInt();
        
        String procedure = "CREATE PROCEDURE Test3(IN movieId INT, OUT movietitle VARCHAR(255),OUT genre1 VARCHAR(255))"
                + "BEGIN "
                + "SELECT title,genre INTO movietitle,genre1 FROM movies"
                + "WHERE movie_id = movieId; "
                + "END;";
        Statement stmt = conn.createStatement();
      stmt.execute(procedure);
      System.out.println("Stored procedure created successfully.");
        
    }
	public static void ViewUserBookings() throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            System.out.println("Database Connection Failed");
            return;
        }
 
        String procedure = "CREATE PROCEDURE GetMovieDetailsByUsername1(IN username VARCHAR(255))"
                         + "BEGIN "
                         + "select * from movies join shows on movies.movie_id = shows.movie_id join booking on booking.show_id = shows.show_id where username = username;"
                         + "END;";
        Statement stmt = conn.createStatement();
        // Execute the stored procedure creation
        stmt.execute(procedure);
        System.out.println("View Bookings procedure created successfully.");
    }
 
    public static void main(String[] args) throws SQLException {
    	ViewUserBookings();
    }
}






















//public class StoredProcedure {
//
//    public static void main(String[] args) throws SQLException {
//        Connection conn = DatabaseConnection.getConnection();
//
//        if (conn == null) {
//            System.out.println("Database connection failed");
//            return;
//        }
////        Scanner scanner = new Scanner(System.in);
////        System.out.print("Enter your movie Id: ");
////        String movieId = scanner.nextLine();
//        String procedure = "CREATE PROCEDURE test(IN movieId INT, OUT title VARCHAR(255))"
//        		+ "BEGIN"
//        		+ "SELECT title into movietitle FROM movies WHERE movie_id = movieId;"
//        		+ "END;";
//
//        Statement stmt = conn.createStatement();
//        stmt.execute(procedure);
//        System.out.println("Stored procedure created successfully.");
//       
//    }
//}

















//public class StoredProcedure {
//	Connection conn=DatabaseConnection.getConnection();
//	if(conn==null)
//	{
//		System.out.println("Database connection failed");
//		return;
//	}
//	//create procedure
//	//create procedure name(IN movie_id,OUT title VARCHAR(25))
//	//
//	//begin
//	//logical queries
//	//end;
//	
//	//statement smt=
//	//creating ->input movie_id->based on movie_id show title as output
//	String procedure="create procedure name(IN movie_id INT,OUT title VARCHAR(25))"
//			          +"begin"
//			           +""
//			          +"END;"
//	Statement smt=conn.createStatement();
//	smt.execute(procedure);
//	system.out.println("Executed");
//
//}
