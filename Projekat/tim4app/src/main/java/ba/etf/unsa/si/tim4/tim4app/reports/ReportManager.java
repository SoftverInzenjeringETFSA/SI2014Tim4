package ba.etf.unsa.si.tim4.tim4app.reports;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import net.sf.jasperreports.engine.JREmptyDataSource;
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

import com.mysql.jdbc.Connection;

public class ReportManager {
	
	private boolean isSkladisteEmpty = false;
	
	public ReportManager() 
	{
		SkladisteDataSource sd = new SkladisteDataSource();
		isSkladisteEmpty = sd.getCount() == 0 ? true : false;
	}
	
	// metoda prima put do .jrxml filea
	// izvjestaji bez parametara
	private void printParameterlessReport(String reportPath, String reportName)
	{
		DatabaseUtils dbUtils = new DatabaseUtils();
		try
		{
			HashMap hm = new HashMap();	
			Date currentDate = new Date();
			JasperReport jasperReport = JasperCompileManager.compileReport(reportPath);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, hm, dbUtils.getConnection());
			String date = (new SimpleDateFormat("dd-MM-yyyy")).format(currentDate);
			JasperExportManager.exportReportToPdfFile(jasperPrint,
	                  "C://Users//Granulo//" + reportName + "-" + date + ".pdf");
			JasperViewer.viewReport(jasperPrint, false);
		}
		catch(Exception e)
		{
			dbUtils.printExceptionMessage(e.getMessage(), "printParameterlessReport");
			e.printStackTrace();
			dbUtils.logException(Level.SEVERE, e.getMessage(), e);
		}
	}
	
	// metoda prima put do .jrxml filea
	// konekciju na bazu i HashMap koji sadrzi parametre izvjestaja
	private void printParameterReport(String reportPath, Object parameter)
	{
		DatabaseUtils dbUtils = new DatabaseUtils();
		if(reportPath.equals("Reports/izvjestajZaPojedinacniPlinskiRezervoar.jrxml"))
		{
			String serijskiBroj = (String) parameter;
			String reportName = "izvjestajZaPojedinacniPlinskiRezervoar" + serijskiBroj;
			Map parametersMap = new HashMap();
			parametersMap.put("P_SERIJSKI_BROJ", serijskiBroj);
			Date currentDate = new Date();
			try{
			JasperReport jasperReport = JasperCompileManager.compileReport(reportPath);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametersMap, dbUtils.getConnection());
			String date = (new SimpleDateFormat("dd-MM-yyyy")).format(currentDate);
			JasperExportManager.exportReportToPdfFile(jasperPrint,
	                  "C://Users//Granulo//" + reportName + "-" + date + ".pdf");
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
		else if(reportPath.equals("Reports/izvjestajTrenutnogStanjaZaKomitenta.jrxml"))
		{
			Komitent k = (Komitent) parameter;
			PravniKomitent pk = null;
			FizickiKomitent fk = null;
			if(k.getTipKomitenta().equals("Pravno lice"))
			{
				pk = (PravniKomitent) k;
			}
			else
			{
				fk = (FizickiKomitent) k;
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
			pathToPrint = "Reports/izvjestajOStanjuNaSkladistuPlinskeBoce.jrxml";
			reportName = "IzvjestajOStanjuNaSkladistuPlinskeBoce";
			reportCase = 0;
			break;
		case 1:
			pathToPrint = "Reports/izvjestajOStanjuNaSkladistuPlinskiRezervoari.jrxml";
			reportName = "IzvjestajOStanjuNaSkladistuPlinskiRezervoari";
			reportCase = 1;
			break;
		case 2:
			pathToPrint = "Reports/izvjestajZaPojedinacniPlinskiRezervoar.jrxml";
			reportCase = 2;
			break;
		case 3:
			pathToPrint = "Reports/izvjestajTrenutnogStanjaZaKomitenta.jrxml";
			reportCase = 3;
			break;
		case -1:
			return;
		}
		if(pathToPrint != "" && reportName != "") printParameterlessReport(pathToPrint, reportName);
		else if(pathToPrint != "" && reportName == "")
		{
			if(reportCase == 2 || reportCase == 3) printParameterReport(pathToPrint, parameter);
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
	
}
