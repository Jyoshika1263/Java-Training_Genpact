package HMSSystem;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	private static final String URL ="jdbc:mysql://localhost:3306/HMS";
	private static final String USER="root";
	private static final String PASSWORD="Genpact@123456789";
	private static Connection connection;
	public static Connection getConnection() {
		//if(connection==null || connection.isClosed()) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver"); 
		
				connection=DriverManager.getConnection(URL,USER,PASSWORD);
				System.out.println("Database connected");
			}
			catch(SQLException | ClassNotFoundException e) {
				e.printStackTrace();	
			}
		//}
		return connection;
	}
	public static void main(String[] args) {
		getConnection();
	}
}


















//db design
//->patient table->id int autoincrement,first name,last name,age,gender,contact number;
//->doctor table->id int auto increment primary key,name,speciality,contact
//->appointment->id int auto primary key,patient id int,doctor id,appointment date DAte,reason,foreign key appointment to patient table, foreign key refer to doctor
//->staff ->id int auto,name,contact_number;