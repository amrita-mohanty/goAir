package goair.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection 
{
	public String connectionDriver = "com.mysql.jdbc.Driver";
	public String connectionParam = "jdbc:mysql://localhost/airline";
	private String defaultUsername = "root";
	private String defaultPassword = "";

	public Connection createDbConnection()
	{
		Connection connection = null;  
		try 
		{  
			Class.forName(connectionDriver);  
			connection = DriverManager.getConnection(connectionParam, defaultUsername,defaultPassword);  
		} 
		catch (Exception e) 
		{  
			System.out.println("Unable to create the DB Connection ...");
			e.printStackTrace();
			System.out.println("*****************------------------\n");
			System.out.println("*****************------------------\n");
			System.out.println("*****************------------------\n");
			System.out.println("*****************------------------\n");
			System.out.println("*****************------------------Please check username and password for your database--------------*****************");
			System.out.println("*****************------------------Current Username : root \n");
			System.out.println("*****************------------------Current Password : root \n");
			System.out.println("*****************------------------\n");
			System.out.println("*****************------------------\n");
			System.out.println("*****************------------------\n");
		}

		return connection;
	}
	
	public Connection createDbConnection(String username, String password)
	{
		Connection connection = null;  
		try 
		{  
			Class.forName(connectionDriver);  
			connection = DriverManager.getConnection(connectionParam, username,password);
			System.out.println("Database connection successfull.");
		} 
		catch (Exception e) 
		{  
			System.out.println("Unable to create the DB Connection ...");
			e.printStackTrace();
			System.out.println("*****************------------------\n");
			System.out.println("*****************------------------\n");
			System.out.println("*****************------------------\n");
			System.out.println("*****************------------------\n");
			System.out.println("*****************------------------Please check username and password for your database--------------*****************");
			System.out.println("*****************------------------Current Username : "+username+"\n");
			System.out.println("*****************------------------Current Password : "+password+"\n");
			System.out.println("*****************------------------\n");
			System.out.println("*****************------------------\n");
			System.out.println("*****************------------------\n");
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
	
	public static void main (String[] args) {
		System.out.println("Test Database connection: ");
		DbConnection dbConnection = new DbConnection();
		dbConnection.createDbConnection("root","");
	}
} //class
