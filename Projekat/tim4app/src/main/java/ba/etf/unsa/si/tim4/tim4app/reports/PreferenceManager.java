package ba.etf.unsa.si.tim4.tim4app.reports;

import java.util.prefs.Preferences;

import javax.swing.JFileChooser;

import ba.etf.unsa.si.tim4.tim4app.daldao.DatabaseUtils;

public class PreferenceManager {
	
	private String pathToFolder = "";
	private static final String REPORT_FOLDER_PATH_KEY = "path_to_folder";
	private static final String NO_PATH_CHOSEN = "no_path_chosen";
	private static final String DB_PASSWORD_KEY = "db_password";
	private static final String NO_PASSWORD = "2014SIEtf";
	
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
	
	public void setDBPassword(String password)
	{
		Preferences prefs = Preferences.userNodeForPackage(DatabaseUtils.class);
		prefs.put(DB_PASSWORD_KEY, password);
	}
	
	public String getDBPassword()
	{
		Preferences prefs = Preferences.userNodeForPackage(DatabaseUtils.class);
		return prefs.get(DB_PASSWORD_KEY, NO_PASSWORD);
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
