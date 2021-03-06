package ba.etf.unsa.si.tim4.tim4app.reports;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.javatuples.Triplet;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import ba.etf.unsa.si.tim4.tim4app.classes.FizickiKomitent;
import ba.etf.unsa.si.tim4.tim4app.classes.Izvjestaj;
import ba.etf.unsa.si.tim4.tim4app.classes.Komitent;
import ba.etf.unsa.si.tim4.tim4app.classes.PravniKomitent;
import ba.etf.unsa.si.tim4.tim4app.daldao.DatabaseUtils;
import ba.etf.unsa.si.tim4.tim4app.daldao.IzvjestajiDataSource;
import ba.etf.unsa.si.tim4.tim4app.daldao.SkladisteDataSource;


public class ReportManager {
	
	private boolean isSkladisteEmpty = false;
	private String pathToFolder = "";
	private PreferenceManager folderManager = new PreferenceManager();
	private static final String LOGO_PATH = "LOGO.jpg";

	
	public ReportManager() 
	{
		SkladisteDataSource sd = new SkladisteDataSource();
		isSkladisteEmpty = sd.getCount() == 0 ? true : false;
		pathToFolder = folderManager.getChosenPath();
	}
	
	// metoda prima put do .jrxml filea
	// izvjestaji bez parametara
	private void printParameterlessReport(String reportPath, String reportName)
	{
		DatabaseUtils dbUtils = new DatabaseUtils();
		try
		{
			HashMap hm = new HashMap();	
			hm.put("P_LOGO_IMAGE", getReportLogoImage());
			Date currentDate = new Date();
			InputStream is = ClassLoader.getSystemResourceAsStream(reportPath);
			JasperReport jasperReport = (JasperReport)JasperCompileManager.compileReport(is);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, hm, dbUtils.getConnection());
			String date = (new SimpleDateFormat("dd-MM-yyyy")).format(currentDate);
			JasperExportManager.exportReportToPdfFile(jasperPrint,
	                  pathToFolder + reportName + "-" + date + ".pdf");
			JasperViewer.viewReport(jasperPrint, false);
		}
		catch(Exception e)
		{
			dbUtils.printExceptionMessage(e.getMessage(), "printParameterlessReport");
			dbUtils.logException(Level.SEVERE, e.getMessage(), e);
		}
	}
	
	// metoda prima put do .jrxml filea
	// konekciju na bazu i HashMap koji sadrzi parametre izvjestaja
	private void printParameterReport(String reportPath, Object parameter)
	{
		DatabaseUtils dbUtils = new DatabaseUtils();
		if(reportPath.equals("izvjestajZaPojedinacniPlinskiRezervoar.jrxml"))
		{
			String serijskiBroj = (String) parameter;
			String reportName = "izvjestajZaPojedinacniPlinskiRezervoar" + serijskiBroj;
			Map parametersMap = new HashMap();
			parametersMap.put("P_SERIJSKI_BROJ", serijskiBroj);
			parametersMap.put("P_LOGO_IMAGE", getReportLogoImage());
			Date currentDate = new Date();
			try{
				InputStream is = ClassLoader.getSystemResourceAsStream(reportPath);
				JasperReport jasperReport = (JasperReport)JasperCompileManager.compileReport(is);
				JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametersMap, dbUtils.getConnection());
				String date = (new SimpleDateFormat("dd-MM-yyyy")).format(currentDate);
				JasperExportManager.exportReportToPdfFile(jasperPrint,
		                  pathToFolder + reportName + "-" + date + ".pdf");
				JasperViewer.viewReport(jasperPrint, false);
				IzvjestajiDataSource ids = new IzvjestajiDataSource();
				int maxId = ids.getMaxId();
				Izvjestaj toInsert = new Izvjestaj(formBrojIzvjestaja(maxId, currentDate), currentDate, serijskiBroj, "Izvjestaj za pojedinacni plinski rezervoar");
				ids.insert(toInsert);
			}
			catch(JRException e)
			{
				dbUtils.printExceptionMessage(e.getMessage(), "printParameterReport case 1");
				dbUtils.logException(Level.SEVERE, e.getMessage(), e);
			}
		}
		else if(reportPath.equals("izvjestajTrenutnogStanjaZaKomitenta.jrxml"))
		{
			Komitent k = (Komitent) parameter;
			PravniKomitent pk = null;
			FizickiKomitent fk = null;
			if(k.getTipKomitenta().equals("Pravno lice")) pk = (PravniKomitent) k;
			else fk = (FizickiKomitent) k;
			String nazivKomitenta = "";
			int idKomitenta = 0;
			idKomitenta = k.getId();
			nazivKomitenta = (pk == null) ? fk.getIme() + " " + fk.getPrezime() : pk.getNazivFirme();
			String reportName = "izvjestajTrenutnogStanjaZaKomitenta-" + nazivKomitenta;
			Map parametersMap = new HashMap();
			System.out.println(idKomitenta);
			parametersMap.put("P_KOMITENT_ID", idKomitenta);
			parametersMap.put("P_LOGO_IMAGE", getReportLogoImage());
			parametersMap.put("P_NAZIV_KOMITENTA", nazivKomitenta);
			Date currentDate = new Date();
			try{
				InputStream is = ClassLoader.getSystemResourceAsStream(reportPath);
				JasperReport jasperReport = (JasperReport)JasperCompileManager.compileReport(is);
				JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametersMap, dbUtils.getConnection());
				String date = (new SimpleDateFormat("dd-MM-yyyy")).format(currentDate);
				JasperExportManager.exportReportToPdfFile(jasperPrint,
		                  pathToFolder + reportName + "-" + date + ".pdf");
				JasperViewer.viewReport(jasperPrint, false);
				IzvjestajiDataSource ids = new IzvjestajiDataSource();
				int maxId = ids.getMaxId();
				Izvjestaj toInsert = new Izvjestaj(formBrojIzvjestaja(maxId, currentDate), currentDate, nazivKomitenta, "Izvjestaj za pojedinacni plinski rezervoar");
				ids.insert(toInsert);
			}
			catch(JRException e)
			{
				dbUtils.printExceptionMessage(e.getMessage(), "printParameterReport case 1");
				dbUtils.logException(Level.SEVERE, e.getMessage(), e);
			}
		}
		else if(reportPath.equals("FakturaProdaje.jrxml"))
		{
			Triplet<String, String, Integer> parameters = (Triplet<String, String, Integer>) parameter;
			String nazivKomitenta = parameters.getValue0();
			String brojFakture = parameters.getValue1();
			int fakturaId = parameters.getValue2();
			String reportName = "FakturaProdajeZaKomitenta-" + nazivKomitenta;
			Map parametersMap = new HashMap();
			parametersMap.put("P_FAKTURA_ID", fakturaId);
			parametersMap.put("P_BROJ_FAKTURE", brojFakture);
			parametersMap.put("P_LOGO_IMAGE", getReportLogoImage());
			parametersMap.put("P_KOMITENT_NAZIV", nazivKomitenta);
			Date currentDate = new Date();
			try{
				InputStream is = ClassLoader.getSystemResourceAsStream(reportPath);
				JasperReport jasperReport = (JasperReport)JasperCompileManager.compileReport(is);
				JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametersMap, dbUtils.getConnection());
				String date = (new SimpleDateFormat("dd-MM-yyyy")).format(currentDate);
				JasperExportManager.exportReportToPdfFile(jasperPrint,
		                  pathToFolder + reportName + "-" + nazivKomitenta + ".pdf");
				JasperViewer.viewReport(jasperPrint, false);
			}
			catch(JRException e)
			{
				dbUtils.printExceptionMessage(e.getMessage(), "printParameterReport case 1");
				dbUtils.logException(Level.SEVERE, e.getMessage(), e);
			}
		}
		else if(reportPath.equals("FakturaIznajmljivanja.jrxml"))
		{
			Triplet<String, String, Integer> parameters = (Triplet<String, String, Integer>) parameter;
			String nazivKomitenta = parameters.getValue0();
			String brojFakture = parameters.getValue1();
			int fakturaId = parameters.getValue2();
			String reportName = "FakturaProdajeZaKomitenta-" + nazivKomitenta;
			Map parametersMap = new HashMap();
			parametersMap.put("P_FAKTURA_ID", fakturaId);
			parametersMap.put("P_BROJ_FAKTURE", brojFakture);
			parametersMap.put("P_LOGO_IMAGE", getReportLogoImage());
			parametersMap.put("P_KOMITENT_NAZIV", nazivKomitenta);
			Date currentDate = new Date();
			try{
				InputStream is = ClassLoader.getSystemResourceAsStream(reportPath);
				JasperReport jasperReport = (JasperReport)JasperCompileManager.compileReport(is);
				JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametersMap, dbUtils.getConnection());
				String date = (new SimpleDateFormat("dd-MM-yyyy")).format(currentDate);
				JasperExportManager.exportReportToPdfFile(jasperPrint,
		                  pathToFolder + reportName + "-" + nazivKomitenta + ".pdf");
				JasperViewer.viewReport(jasperPrint, false);
			}
			catch(JRException e)
			{
				dbUtils.printExceptionMessage(e.getMessage(), "printParameterReport case 1");
				dbUtils.logException(Level.SEVERE, e.getMessage(), e);
			}
		}
		else if(reportPath.equals("IzvjestajOIznajmljenimRezervoarima.jrxml"))
		{
			
			Komitent k = (Komitent) parameter;
			PravniKomitent pk = null;
			FizickiKomitent fk = null;
			if(k.getTipKomitenta().equals("Pravno lice")) pk = (PravniKomitent) k;
			else fk = (FizickiKomitent) k;
			String nazivKomitenta = "";
			int idKomitenta = 0;
			idKomitenta = k.getId();
			nazivKomitenta = (pk == null) ? fk.getIme() + " " + fk.getPrezime() : pk.getNazivFirme();
			String reportName = "izvjestajOIznajmljenimRezervoarima-" + nazivKomitenta;
			Map parametersMap = new HashMap();
			System.out.println(idKomitenta);
			parametersMap.put("P_KOMITENT_ID", idKomitenta);
			parametersMap.put("P_LOGO_IMAGE", getReportLogoImage());
			parametersMap.put("P_NAZIV_KOMITENTA", nazivKomitenta);
			Date currentDate = new Date();
			try{
				InputStream is = ClassLoader.getSystemResourceAsStream(reportPath);
				JasperReport jasperReport = (JasperReport)JasperCompileManager.compileReport(is);
				JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametersMap, dbUtils.getConnection());
				String date = (new SimpleDateFormat("dd-MM-yyyy")).format(currentDate);
				JasperExportManager.exportReportToPdfFile(jasperPrint,
		                  pathToFolder + reportName + "-" + date + ".pdf");
				JasperViewer.viewReport(jasperPrint, false);
			}
			catch(Exception e)
			{
				dbUtils.printExceptionMessage(e.getMessage(), "printParameterReport case 1");
				dbUtils.logException(Level.SEVERE, e.getMessage(), e);
			}
		}
	}
	
	public void printReport(int comboSelectedIndex, Object parameter)
	{
		String pathToPrint = "";
		String reportName = "";
		int reportCase = -1;
		int selectedIndex = comboSelectedIndex;
		switch(selectedIndex)
		{
		case 0:
			pathToPrint = "izvjestajOStanjuNaSkladistuPlinskeBoce.jrxml";
			reportName = "IzvjestajOStanjuNaSkladistuPlinskeBoce";
			reportCase = 0;
			break;
		case 1:
			pathToPrint = "izvjestajOStanjuNaSkladistuPlinskiRezervoari.jrxml";
			reportName = "IzvjestajOStanjuNaSkladistuPlinskiRezervoari";
			reportCase = 1;
			break;
		case 2:
			pathToPrint = "izvjestajZaPojedinacniPlinskiRezervoar.jrxml";
			reportCase = 2;
			break;
		case 3:
			pathToPrint = "izvjestajTrenutnogStanjaZaKomitenta.jrxml";
			reportCase = 3;
			break;
		case 4:
			pathToPrint = "FakturaProdaje.jrxml";
			reportCase = 4;
			break;
		case 5:
			pathToPrint = "FakturaIznajmljivanja.jrxml";
			reportCase = 5;
			break;
		case 6:
			pathToPrint = "IzvjestajOIznajmljenimRezervoarima.jrxml";
			reportCase = 6;
			break;
		case -1:
			return;
		}
		if(pathToPrint != "" && reportName != "") printParameterlessReport(pathToPrint, reportName);
		else if(pathToPrint != "" && reportName == "")
		{
			if(reportCase == 2 || reportCase == 3 || reportCase == 4 || reportCase == 5 || reportCase == 6) printParameterReport(pathToPrint, parameter);
		}
	}
	
	public String formBrojIzvjestaja(int maxId, Date currentDate)
	{
		Calendar c = Calendar.getInstance();
		c.setTime(currentDate);
		int year = c.get(Calendar.YEAR);
		return maxId + "-" + year;
	}
	
	public boolean getSkladisteEmpty()
	{
		return isSkladisteEmpty;
	}
	
	private java.awt.Image getReportLogoImage()
	{
		InputStream is = ClassLoader.getSystemResourceAsStream(LOGO_PATH);
		ImageIcon icon = new ImageIcon();
		try {
			icon.setImage(ImageIO.read(is));
		} catch (IOException e) {
			Logger l = Logger.getAnonymousLogger();
			l.log(Level.SEVERE, e.getMessage(), e);
		}
		return icon.getImage();
	}
	
}
