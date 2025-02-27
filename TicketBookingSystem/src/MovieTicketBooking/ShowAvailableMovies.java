package MovieTicketBooking;

import java.security.Timestamp;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ShowAvailableMovies extends MovieTicketBookingApp{
		 
		public static void display() 
		{
		Connection conn=DatabaseConnection.getConnection();
		if(conn==null)
		{
			System.out.println("Database connection failed");
			return;
		}
		String query = "select * from movies inner join shows on movies.movie_id=shows.show_id";
		try(Statement smt = conn.createStatement())
		{
			ResultSet rs=smt.executeQuery(query);
			while(rs.next())
			{
				int movieId=rs.getInt("movie_id");
				String title=rs.getString("title");
				String genre=rs.getString("genre");
				int duration = rs.getInt("duration");
				java.sql.Timestamp showtime = rs.getTimestamp("show_time");
				int available_seats = rs.getInt("available_seats");
				System.out.println("Movie id : "+movieId);
				System.out.println("Title : "+title);
				System.out.println("Genre : "+genre);
				System.out.println("Duration : "+duration);
				System.out.println("showtime : "+showtime);
				System.out.println("available seats : "+available_seats);
				System.out.println();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	//conn
//	//display movies
//	//display shows
//	//select -> statement ->
//	//create method1->display movies
//	public static void display() 
//	{
//		Connection conn=databaseConnection.getConnection();
//	    if(conn==null)
//	    {
//		System.out.println("Database connection failed");
//		return;
//	    }
////	String query="select * from movies;"; //combine with shows,join
////	//movieid,title,showid,showtime,availableseats
//	  
//	    String query = "select * from movies inner join shows on movies.movie_id=shows.show_id";
//	try(Statement smt=conn.createStatement()){
//		ResultSet rs=smt.executeQuery(query);//9 results-9 rows-initially pointing to 1st row
//		while(rs.next()) {
//			
//			int movieId=rs.getInt("movie_id");
//			String title=rs.getString("title");
//			String genre=rs.getString("genre");
//			int duration = rs.getInt("duration");
//			Timestamp showtime = rs.getTimestamp("show_time");
//			int available_seats = rs.getInt("available_seats");
//			System.out.println("Movie id : "+movieId);
//			System.out.println("Title : "+title);
//			System.out.println("Genre : "+genre);
//			System.out.println("Duration : "+duration);
//			System.out.println("showtime : "+showtime);
//			System.out.println("available seats : "+available_seats);
//			System.out.println();
////			int movieId=rs.getInt("movie_id");
////			String title=rs.getString("title");
////			int showId = rs.getInt("show_id");
////            String showTime = rs.getString("showtime");
////            int availableSeats = rs.getInt("availableseats");
////			//3 other
////			//display
////			//print(Movie Id:"+movie_id)
////			//System.out.println("msg");
////            System.out.println("Movie Id: " + movieId);
////            System.out.println("Title: " + title);
////            System.out.println("Show Id: " + showId);
////            System.out.println("Show Time: " + showTime);
////            System.out.println("Available Seats: " + availableSeats);
////            System.out.println("---------------------------");
//		}
//	}
//	catch(SQLException e) {
//		e.printStackTrace();
//	}
//	}
//}
