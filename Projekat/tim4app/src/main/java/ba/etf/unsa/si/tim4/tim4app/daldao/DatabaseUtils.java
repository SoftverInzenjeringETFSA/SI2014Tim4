package ba.etf.unsa.si.tim4.tim4app.daldao;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import ba.etf.unsa.si.tim4.tim4app.reports.PreferenceManager;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class DatabaseUtils {
	
	private Connection currentConnection;
	
	public DatabaseUtils(){ }
	
	public Connection getConnection()
	{
		try{
			Class.forName("com.mysql.jdbc.Driver");
			if( currentConnection == null || currentConnection.isClosed())
			{
				PreferenceManager pm = new PreferenceManager();
				String password = pm.getDBPassword();
				String username = pm.getDBUser();
				String connectionString = pm.getDBConnectionString();
				currentConnection = (Connection) DriverManager.getConnection(connectionString, username, password);
			}
			return currentConnection;
		}
		catch(Exception e){ 
			System.out.println(e.getMessage() + " metoda getConnection()");
			logException(Level.SEVERE, e.getMessage(), e);
			return null;
		}
	}
	
	
	public PreparedStatement getPreparedStatement(String query)
	{
		
		if(currentConnection != null)
		{
			try
			{
				if(currentConnection.isClosed()) currentConnection = getConnection();
			}
			catch(Exception e)
			{
				printExceptionMessage(e.getMessage(), "getPreparedStatement()");
				logException(Level.SEVERE, e.getMessage(), e);
				return null;
			}
			try
			{
				PreparedStatement ps = (PreparedStatement) currentConnection.prepareStatement(query);
				return ps;
			}
			catch(SQLException e)
			{
				printExceptionMessage(e.getMessage(), "getPreparedStatement()");
				logException(Level.SEVERE, e.getMessage(), e);
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
				logException(Level.SEVERE, e.getMessage(), e);
			}
		}
	}
	
	public void printExceptionMessage(String message, String methodName)
	{
		System.out.println(message + " metoda " + methodName);
	}
	
	public void logException(Level level, String message, Throwable t)
	{
		Logger l = Logger.getAnonymousLogger();
		l.log(level, message, t);
	}
	
	public java.sql.Date getSqlDate(java.util.Date date)
	{
		return new java.sql.Date(date.getTime());
	}
	
}
