package MovieTicketBooking;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilterWriter;
import java.sql.Connection;

import javax.sql.rowset.RowSetProvider;
import javax.sql.rowset.WebRowSet;

public class WebRowSetApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//webrowset
		//support xml format
		//data-fetched movies ->write to some xml file
		//read data from xml file
		Connection conn=DatabaseConnection.getConnection();
		try {
			//create wen row set
			WebRowSet wrs=RowSetProvider.newFactory().createWebRowSet();
			wrs.setCommand("select * from movies");
			wrs.execute(conn);
			//close the conn
			conn.close();
			System.out.println("conncetion closed");
			//save data to xml
			FileWriter writer = new FileWriter("movies.xml");
			wrs.writeXml(writer);
			writer.close();
			//read data from xml
			WebRowSet new_wrs=RowSetProvider.newFactory().createWebRowSet();
			FileReader reader=new FileReader("movies.xml");
			new_wrs.readXml(reader);
			reader.close();
			while(new_wrs.next()) {
				//System.out.println("ID :"+new_wrs.getInt("movie_id")+"Title :"+new_wrs.getString("title"));
				 int movieId = new_wrs.getInt("movie_id");
	                String title = new_wrs.getString("title");
	                String genre = new_wrs.getString("genre");
	                int duration = new_wrs.getInt("duration");

	                System.out.println("Movie ID: " + movieId);
	                System.out.println("Title: " + title);
	                System.out.println("Genre: " + genre);
	                System.out.println("Duration: " + duration + " minutes");
	                System.out.println("-------------------------");
			}
      	}
		catch(Exception e) {
			e.printStackTrace();
		}
		//JoinRowset
		//->join multiple rowSet based on common column -> combining->in memory
		//movies,shows->common col->movie_id
		//title,genre,duration,show_id,show_time,seats->
		//we will not write join query
		//cachedRowset->fetch movies data->execute(conn)
		//second cachedRowSet->fetch shows data->execute(conn)
		//close the conn
		//create JoinRowSet->
		//movies.setMatchColumn("movie_id)
		//shows.setMatchColumn("movie_id")
		//joinrs.addRowSet(movies)
		//joinrs.addRowSet(shows)
		//print(Joined data)
		//while(joinrs.next()){
		  //joinrs.getInt()
		

	}

}
