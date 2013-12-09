package goair.util;

import java.sql.Connection;
import java.sql.SQLException;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

public final class DbConnection 
{
	private String connectionDriver = "com.mysql.jdbc.Driver";
	private String connectionParam = "jdbc:mysql://localhost/airline";
	private String defaultUsername = "root";
	private String defaultPassword = "";
	
	private BoneCP connectionPool = null;
	
	// create singleton 
	private static final DbConnection INSTANCE = new DbConnection();
    private DbConnection() {
        if (INSTANCE != null) {
            throw new IllegalStateException("Already instantiated");
        } else {
    		try {
    			// load the database driver (make sure this is in your classpath!)
    			Class.forName(connectionDriver);
    		} catch (Exception e) {
    			e.printStackTrace();
    			return;
    		}
    		
    		try {
    			// setup the connection pool
    			BoneCPConfig config = new BoneCPConfig();
    			config.setJdbcUrl(connectionParam); // jdbc url specific to your database, eg jdbc:mysql://127.0.0.1/yourdb
    			config.setUsername(defaultUsername); 
    			config.setPassword(defaultPassword);
    			config.setMinConnectionsPerPartition(5);
    			config.setMaxConnectionsPerPartition(10);
    			config.setPartitionCount(1);
    			connectionPool = new BoneCP(config); // setup the connection pool
    		} catch (SQLException e) {
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
        }
    }
    public static DbConnection getInstance() {
        return INSTANCE;
    }
    
    public Connection getConnection() {
    	 // fetch a connection 
    	try {
			return connectionPool.getConnection();
		} catch (SQLException e) {
			System.out.println("Unable to get the DB Connection from pool...");
			e.printStackTrace();
			return null;
		}
    }
    
	//Close the db-connection when the application is closed
	public void close()
	{
		try 
		{  
			if(connectionPool != null)
			{
				connectionPool.shutdown();
			}
		} 
		catch (Exception e) 
		{  
			System.out.println("Unable to shutdown the DB Connection ...");
			e.printStackTrace();  
		}  
	}
	
//	public static void main (String[] args) {
//		System.out.println("Test Database connection: ");
//		DbConnection dbConnection = new DbConnection();
//		dbConnection.createDbConnection("root","");
//	}
} //class
