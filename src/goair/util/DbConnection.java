package goair.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection 
{
	public String connectionDriver = "com.mysql.jdbc.Driver";
	public String connectionParam = "jdbc:mysql://localhost/airline";

	public Connection createDbConnection()
	{
		Connection connection = null;  
		try 
		{  
			Class.forName(connectionDriver);  
			connection = DriverManager.getConnection(connectionParam, "root","root");  
		} 
		catch (Exception e) 
		{  
			System.out.println("Unable to create the DB Connection ...");
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
