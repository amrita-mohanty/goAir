package goair.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection 
{
	public String connectionDriver = "org.sqlite.JDBC";
	public String connectionParam = "jdbc:sqlite:"+"/Users/amrita/airline.db";

	public Connection createDbConnection()
	{
		Connection connection = null;  
		try 
		{  
			Class.forName(connectionDriver);  
			connection = DriverManager.getConnection(connectionParam);  
		} 
		catch (Exception e) 
		{  
			System.out.println("Unable to close the DB Connection ...");
			e.printStackTrace();  
		}

		return connection;
	}  

	//Close the db-connection when the application is closed
	public void closeDBConnection(Connection conn)
	{
		try 
		{  
			if(conn != null)
			{
				conn.close();
			}
		} 
		catch (Exception e) 
		{  
			System.out.println("Unable to close the DB Connection ...");
			e.printStackTrace();  
		}  
	}
} //class
