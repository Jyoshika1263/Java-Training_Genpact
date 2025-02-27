package MovieTicketBooking;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;

public class RowSetApplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        //JDBC ROW SET
		try {
			Connection conn=DatabaseConnection.getConnection();
			//create a jdbc row set
			JdbcRowSet rowSet=RowSetProvider.newFactory().createJdbcRowSet();
			rowSet.setUrl("jdbc:mysql://localhost:3306/movie_booking");
			rowSet.setUsername("root");
			rowSet.setPassword("Genpact@123456789");
//			//set sql query-setCommand("-----")
//			rowSet.setCommand("select * from movies");
//			//execute the query-not disconnecting
//			rowSet.execute();
//			//iterate over the result
//			while(rowSet.next()) {
//				System.out.println("ID :"+rowSet.getInt("movie_id"));
//			}
//			while(rowSet.previous()) {
//				System.out.println("ID :"+rowSet.getInt("movie_id"));
//			}
//			//close
//			rowSet.close();
			CachedRowSet ct=RowSetProvider.newFactory().createCachedRowSet();
			
			ct.setCommand("select * from movies");
			ct.execute(conn);
			
            while(ct.next()) {
            	if(ct.getInt("movie_id")==3) {
            		ct.updateString("title", "Fighter32");
            		ct.updateRow();
            	}
            }
            conn.setAutoCommit(false);
            ct.acceptChanges(conn);
            conn.setAutoCommit(true);
            //establish connection
//            conn=DatabaseConnection.getConnection();
//            ct.acceptChanges(conn);
            conn.close();
            while(ct.next()) {
            	System.out.println("ID :"+ct.getInt("movie_id"));
            	System.out.println("ID :"+ct.getString("title"));
            }	
            ct.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

}





















//conn.close();
// Print the results from the cached row set
//while (ct.next()) {
//    int movieId = ct.getInt("movie_id");
//    String title = ct.getString("title");
//    String genre = ct.getString("genre");
//    int duration = ct.getInt("duration");
//
//    System.out.println("Movie ID: " + movieId);
//    System.out.println("Title: " + title);
//    System.out.println("Genre: " + genre);
//    System.out.println("Duration: " + duration + " minutes");
//    System.out.println("-------------------------");
//}
//close the connection
//print the results
//some updates
//processing-offline
