package MovieTicketBooking;
 
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
import javax.sql.rowset.FilteredRowSet;
import javax.sql.rowset.RowSetProvider;
 
public class FilterApplication {
	public static void main(String[] args) {
	try(Connection conn = DatabaseConnection.getConnection();
			Statement smt = conn.createStatement();
			ResultSet rs = smt.executeQuery("Select * from shows")){
		//crt filterrowset
		FilteredRowSet frs = RowSetProvider.newFactory().createFilteredRowSet();
		frs.populate(rs);
		//apply filter
		frs.setFilter(new AvailableShowsSeatsFilter(90));
		while(frs.next()) {
			//showid,movieid,showtime,seats
			System.out.println("Show ID :"+frs.getInt("show_id"));
			System.out.println("Movie ID :"+frs.getInt("movie_id"));
			System.out.println("Show Time :"+frs.getString("show_time"));
			System.out.println("Available Seats :"+frs.getInt("available_seats"));
		}
	}
	catch(SQLException e) {
		e.printStackTrace();
	}
	}			
}






























//
//public class FilterApplication {
//
//	public static void main(String[] args) throws SQLException {
//		// TODO Auto-generated method stub
//        try(Connection conn=DatabaseConnection.getConnection()) {
//        	Statement smt=conn.createStatement();
//        	ResultSet rs=smt.executeQuery("Select * from shows");
//        	FilteredRowSet frs=RowSetProvider.newFactory().createFilteredRowSet();
//        	frs.populate(rs);
//        	//apply filter
//        	frs.setFilter(new AvailableShowsSeatsFilter(50));
//        	while(frs.next()) {
//        		//show_id,movie_id,show_time,avaialble_seats
//        		int showId = frs.getInt("Show_id");
//                int movieId = frs.getInt("movie_id");
// 
//                String showTime = frs.getString("show_time");
//                int seats = frs.getInt("available_seats");
// 
//                System.out.println("Show Id: " + showId + " Movie Id: " +  movieId + " Seats: " + seats + " Show Time: " + showTime);
//        	}
//        	
//        }
//        catch(SQLException e) {
//        	e.printStackTrace();
//        }
//	}
//
//}
