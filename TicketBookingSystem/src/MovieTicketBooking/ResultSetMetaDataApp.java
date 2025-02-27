package MovieTicketBooking;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSetMetaDataApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try(Connection conn = DatabaseConnection.getConnection();
				Statement smt = conn.createStatement();
				ResultSet rs = smt.executeQuery("Select * from shows")){
			        ResultSetMetaData rsmd=rs.getMetaData();
			        int colcount=rsmd.getColumnCount();
			        System.out.println(colcount);
			        for(int i=1;i<=colcount;i++) {
			        	System.out.println("column : "+i);
			        	System.out.println("column name : "+rsmd.getColumnName(i));
			        	System.out.println("column label: "+rsmd.getColumnLabel(i));
			        	System.out.println("column type : "+rsmd.getColumnTypeName(i));
			        	System.out.println("Column Type Class : " + rsmd.getColumnClassName(i));
			        	System.out.println("column isnullable  : "+rsmd.isNullable(i));
			        	System.out.println("column isAutoIncrement : "+rsmd.isAutoIncrement(i));
			        	System.out.println("column precision : "+rsmd.getPrecision(i));
			        	System.out.println("column Column display size : "+rsmd.getColumnDisplaySize(i));
			        	System.out.println("column table name : "+rsmd.getTableName(i));
			        	System.out.println("column scheme name : "+rsmd.getSchemaName(i));
			        	//getColumnTypeClass
			        	//isNullable
			        	//isAutoIncremernet
			        	//getPrecision
			        	//getColDisplaySize
			        	//getTableName
			        	//getSchemeName
		        }
		}
	   catch(SQLException e) {
		   e.printStackTrace();
	   }

	}

}
