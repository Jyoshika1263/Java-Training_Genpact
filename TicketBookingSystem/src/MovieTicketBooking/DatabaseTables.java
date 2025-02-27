package MovieTicketBooking;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseTables {
 
	public static void createTables() {
		Connection conn= DatabaseConnection.getConnection();
		if(conn == null) {
			System.out.println("Databse Connection Failed");
			return;
		}
		try {
			Statement smt=conn.createStatement();
			String createMoviesTable="Create Table If not exists Movies ("
									+ "movie_id INT Auto_Increment Primary Key,"
									+ "title varchar(60) not null,"
									+ "genre varchar(20),"
									+ "duration int not null)";
			smt.executeUpdate(createMoviesTable);
			System.out.println("Movies Table Created");
			//Shows Table
			String createShowsTable = "Create Table if not exists Shows("
									+ "Show_id INT auto_increment Primary Key,"
									+ "movie_id INT,"
									+ "show_time DATETIME not null,"
									+ "available_seats INT not null,"
									+ "FOREIGN KEY (movie_id) REFERENCES Movies(movie_id))";
			smt.executeUpdate(createShowsTable);
			System.out.println("Shows Table Created");
			//Booking Table
			String createBookingTable = "Create Table if not exists Booking("
					+ "booking_id INT auto_increment Primary Key,"
					+ "username varchar(60) not null,"
					+ "show_id INT not null,"
					+ "seats_booked INT NOT NULL, "
                    + "booking_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, "
                    + "FOREIGN KEY (show_id) REFERENCES Shows(show_id))";
			smt.executeUpdate(createBookingTable);
			System.out.println("Booking table Created");
			String createUsersTable = "CREATE TABLE IF NOT EXISTS Users ("
	                 + "userid INT AUTO_INCREMENT PRIMARY KEY, "
	                 + "username VARCHAR(255) NOT NULL, "
	                 + "pwd VARCHAR(255) NOT NULL, "
	                 + "admin BOOLEAN NOT NULL)";
	         smt.executeUpdate(createUsersTable);
	         System.out.println("Users Table Created Successfully!");
			smt.close();
			conn.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
		public static void main(String[] args) {
			createTables();
		}
	}
 
