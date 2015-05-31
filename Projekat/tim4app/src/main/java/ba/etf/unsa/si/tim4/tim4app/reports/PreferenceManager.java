package ba.etf.unsa.si.tim4.tim4app.reports;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;

import javax.swing.JFileChooser;

import ba.etf.unsa.si.tim4.tim4app.daldao.DatabaseUtils;

public class PreferenceManager {
	
	private String pathToFolder = "";
	private static final String REPORT_FOLDER_PATH_KEY = "path_to_folder";
	private static final String NO_PATH_CHOSEN = "no_path_chosen";
	private static final String DB_P_KEY = "db_p";
	private static final String DB_USER_KEY = "db_user_key";
	private static final String DB_USER_NO = "no_user";
	private static final String DB_CONNECTION_STRING = "db_connection_string";
	private static final String NO_DB_CONNECTION_STRING = "no_db_connection_string";
	private static final String NO_P = "2014SIEtf";
	private static final String DB_PROPERTIES_FILE = "db.properties";
	
	public PreferenceManager() { }
	
	public String getChosenPath()
	{
		String preferencePath = getPreferencedFolderPath();
		if(preferencePath.equals(NO_PATH_CHOSEN))
		{
			pathToFolder = chooseFolderPath();
			savePreferences(pathToFolder);
		}
		else pathToFolder = preferencePath;
		return pathToFolder;
	}
	
	public void setDBPassword()
	{
		Preferences prefs = Preferences.userNodeForPackage(DatabaseUtils.class);
		String password = "";
		try {
			InputStream is = new FileInputStream(DB_PROPERTIES_FILE);
			Properties properties = new Properties();
			properties.load(is);
			is.close();
			password = properties.getProperty("password");
		} catch (Exception e) {
			Logger l = Logger.getAnonymousLogger();
			l.log(Level.SEVERE, e.getMessage(), e);
		}
		prefs.put(DB_P_KEY, password);
	}
	
	public void setDBUsername()
	{
		Preferences prefs = Preferences.userNodeForPackage(DatabaseUtils.class);
		String username = "";
		try {
			InputStream is = new FileInputStream(DB_PROPERTIES_FILE);
			Properties properties = new Properties();
			properties.load(is);
			is.close();
			username = properties.getProperty("username");
		} catch (Exception e) {
			Logger l = Logger.getAnonymousLogger();
			l.log(Level.SEVERE, e.getMessage(), e);
		}
		prefs.put(DB_USER_KEY, username);
	}
	
	public void setDBConnectionString()
	{
		Preferences prefs = Preferences.userNodeForPackage(DatabaseUtils.class);
		String url = "";
		try {
			InputStream is = new FileInputStream(DB_PROPERTIES_FILE);
			Properties properties = new Properties();
			properties.load(is);
			is.close();
			url = properties.getProperty("url");
		} catch (Exception e) {
			Logger l = Logger.getAnonymousLogger();
			l.log(Level.SEVERE, e.getMessage(), e);
		}
		prefs.put(DB_CONNECTION_STRING, url);
	}
	
	public String getDBPassword()
	{
		Preferences prefs = Preferences.userNodeForPackage(DatabaseUtils.class);
		return prefs.get(DB_P_KEY, NO_P);
	}
	
	public String getDBConnectionString()
	{
		Preferences prefs = Preferences.userNodeForPackage(DatabaseUtils.class);
		return prefs.get(DB_CONNECTION_STRING, NO_DB_CONNECTION_STRING);
	}
	
	public String getDBUser()
	{
		Preferences prefs = Preferences.userNodeForPackage(DatabaseUtils.class);
		return prefs.get(DB_USER_KEY, DB_USER_NO);
	}
	
	
	
	private void savePreferences(String folderPath)
	{
		Preferences prefs = Preferences.userNodeForPackage(ReportManager.class);
		String newValue = folderPath;
		prefs.put(REPORT_FOLDER_PATH_KEY, newValue);
	}
	
	private String getPreferencedFolderPath()
	{
		Preferences prefs = Preferences.userNodeForPackage(ReportManager.class);
		String propertyValue = prefs.get(REPORT_FOLDER_PATH_KEY, NO_PATH_CHOSEN); 
		return propertyValue;
	}
	
	private String chooseFolderPath()
	{
		String chosenPath = "";
		JFileChooser j = new JFileChooser();
		j.setDialogTitle("Izaberite direktorij za čuvanje izvještaja");
		j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		j.showSaveDialog(null);
		chosenPath = j.getSelectedFile().getPath();
		System.out.println(chosenPath);
		return chosenPath;
	}
}
