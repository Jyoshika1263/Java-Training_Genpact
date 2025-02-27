package MovieTicketBooking;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseMetaDataApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try(Connection conn = DatabaseConnection.getConnection()){
			DatabaseMetaData dbMd=conn.getMetaData();
			//fetch db info
			System.out.println("Database Product name :"+dbMd.getDatabaseProductName());
			System.out.println("Database Product version :"+dbMd.getDatabaseProductVersion());
			System.out.println("JDBC Driver name :"+dbMd.getDriverName());
			System.out.println("Jdbc Driver version :"+dbMd.getDriverVersion());
			System.out.println("Database urtl :"+dbMd.getURL());
			System.out.println("Database Username :"+dbMd.getUserName());
			
			//retrieve tables in db
			ResultSet rs=dbMd.getTables("movie_booking", null, "%", new String[] {"TABLE"});
			while(rs.next()) {
				System.out.println((rs.getString("TABLE_NAME")));
			}
			ResultSet rs2=dbMd.getColumns(null,null,"shows","%");
			while(rs2.next()) {
				System.out.println((rs2.getString("COLUMN_NAME")));
				System.out.println((rs2.getString("TYPE_NAME")));
				//primary key->check for shows tables
				//support transaction
				//get the metadata
				System.out.println(dbMd.supportsTransactions());
			}
			ResultSet rs3 = dbMd.getPrimaryKeys(null, null, "Shows");
            while (rs3.next()) {
                System.out.println("Primary Key: " + rs3.getString("COLUMN_NAME"));
            }
		
	    }
	    catch(SQLException e) {
		   e.printStackTrace();
	    }	
	}
}
//HMS:Console based applications->web based applications
//Requirements
//->features and functionalities
//    ->patient mgmt->add new patient details,view patient information,update,delete
//    ->Doctor mgmt->CRUD->Add,view,update,delete
//    ->Appointment schedule->book an appointment,view,cancel
//    ->staff->add,view,update,delete
//Fs->Core Java,MySQL,
//Design
//->Database design->patient table,doctor,appointment,staff
//Functional req : 
//	->menu driven system
//	->JDBC
//	->Exceptions
//	->OOD->OO Design->classes->represents entities are tables,methods
//                       patient class->nmae,address,age,------
//                           (data access objects)DAOClass ->methods->add,view,update,delete
//                           addP(Patient p)->interact with db to perform crud operations
//                           menu based

//database design