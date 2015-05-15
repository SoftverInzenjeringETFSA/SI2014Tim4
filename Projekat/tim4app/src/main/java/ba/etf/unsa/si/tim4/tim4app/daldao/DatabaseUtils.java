package ba.etf.unsa.si.tim4.tim4app.daldao;

import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class DatabaseUtils {
	
	private static final String connectionString = "jdbc:mysql://localhost/tim4";
	private static final String password = "2014SIEtf";
	private static final String username = "EtfSI2014";
	private Connection currentConnection;
	
	public DatabaseUtils(){ }
	
	public Connection getConnection()
	{
		try{
			Class.forName("com.mysql.jdbc.Driver");
			currentConnection = (Connection) DriverManager.getConnection(connectionString, username, password);
			return currentConnection;
		}
		catch(Exception e){ 
			System.out.println(e.getMessage() + " metoda getConnection()");
			return null;
		}
	}
	
	
	public PreparedStatement getPreparedStatement(String query)
	{
		
		if(currentConnection != null)
		{
			try
			{
				PreparedStatement ps = (PreparedStatement) currentConnection.prepareStatement(query);
				return ps;
			}
			catch(SQLException e)
			{
				printExceptionMessage(e.getMessage(), "getPreparedStatement()");
				return null;
			}
		}
		else{ return null; }
	}
	
	public void closeCurrentConnection()
	{
		if(currentConnection != null)
		{
			try {
				if(!currentConnection.isClosed()) currentConnection.close();
			} catch (SQLException e) {
				printExceptionMessage(e.getMessage(), "closeCurrentConnection()");
			}
		}
	}
	
	public void printExceptionMessage(String message, String methodName)
	{
		System.out.println(message + " metoda " + methodName);
	}
	
	
	
}
