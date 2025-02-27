package MovieTicketBooking;
 
import java.sql.Connection;
import java.sql.SQLException;
 
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.JoinRowSet;
import javax.sql.rowset.RowSetProvider;
 
public class JoinRowSet1 {
 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn = DatabaseConnection.getConnection();
	        try {
	        	 CachedRowSet ct1=RowSetProvider.newFactory().createCachedRowSet();
				ct1.setUrl("jdbc:mysql://localhost:3306/movie_booking");
				ct1.setUsername("root");
			      ct1.setPassword("Genpact@123456789");
		        ct1.setCommand("select * from movies");
			ct1.execute();
			CachedRowSet ct2=RowSetProvider.newFactory().createCachedRowSet();
       	 ct2.setCommand("select * from shows");
			ct2.execute(conn);
			 conn.close();
			 JoinRowSet jrs=RowSetProvider.newFactory().createJoinRowSet();
			 ct1.setMatchColumn("movie_id");
			 ct2.setMatchColumn("movie_id");
			 jrs.addRowSet(ct1);
			 jrs.addRowSet(ct2);
			 while(jrs.next())
			 {
				 System.out.println("ID : "+jrs.getInt("movie_id")+" Title : "+jrs.getString("title")+"   Available Seats : "+jrs.getInt("available_seats")+ "   Show-date : "+jrs.getString("show_time"));
			 }
	        } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


	}
 
}