package ba.etf.unsa.si.tim4.tim4app.daldao;

import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.logging.Level;

import org.javatuples.Triplet;

import ba.etf.unsa.si.tim4.tim4app.classes.FakturaIznajmljivanje;
import ba.etf.unsa.si.tim4.tim4app.classes.FakturaProdaje;
import ba.etf.unsa.si.tim4.tim4app.classes.PlinskaBoca;
import ba.etf.unsa.si.tim4.tim4app.classes.PlinskiRezervoar;

import com.mysql.jdbc.PreparedStatement;

public class FaktureIznajmljivanjaDataSource {

private DatabaseUtils dbUtils;
	
	public FaktureIznajmljivanjaDataSource()
	{
		dbUtils = new DatabaseUtils();
		dbUtils.getConnection();
	}
	
	public void insert(FakturaIznajmljivanje faktura)
	{
		String query = "INSERT INTO fakture_iznajmljivanje(broj_fakture, komitent) VALUES(?, ?)";
		String getIdQuery = "SELECT max(id) FROM fakture_iznajmljivanje";
		String stavkeQuery = "INSERT INTO stavkefakture_iznajmljivanje(faktura, rezervoar,"
				+ "kolicina, tip_rezervoara, cijena, kapacitetBoce, iznajmljeno_do) VALUES(?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = dbUtils.getPreparedStatement(query);
		PreparedStatement stavkePs = dbUtils.getPreparedStatement(stavkeQuery);
		PreparedStatement getIdPs = dbUtils.getPreparedStatement(getIdQuery);
		if(ps == null || stavkePs == null || getIdPs == null) return;
		try{
			
			ps.setString(1, formBrojFakture());
			ps.setInt(2, faktura.getKomitent().getId());
			stavkePs.setInt(1, faktura.getId());
			ps.execute();
			dbUtils.closeCurrentConnection();
		}
		catch(Exception e)
		{
			dbUtils.printExceptionMessage(e.getMessage(), "izvjestaj insert");
			dbUtils.logException(Level.SEVERE, e.getMessage(), e);
			dbUtils.closeCurrentConnection();
		}
		int fakturaId = 0;
		try
		{
			if(getIdPs.isClosed()) getIdPs = dbUtils.getPreparedStatement(getIdQuery);
			ResultSet rs = getIdPs.executeQuery();
			while(rs.next())
			{
				fakturaId = rs.getInt(1);
			}
		}
		catch(Exception e)
		{
			dbUtils.printExceptionMessage(e.getMessage(), "izvjestaj insert");
			dbUtils.logException(Level.SEVERE, e.getMessage(), e);
			dbUtils.closeCurrentConnection();
		}
		
		if(fakturaId == 0) return;
		LinkedList<Triplet<PlinskiRezervoar, Date, Double>> rezervoari = new LinkedList<Triplet<PlinskiRezervoar, Date, Double>>();
		rezervoari = faktura.getPlinskiRezervoarStavke();
		LinkedList<Triplet<PlinskaBoca, Date, Double>> boce = new LinkedList<Triplet<PlinskaBoca, Date, Double>>();
		boce = faktura.getPlinskeBoceStavke();
		try
		{
			for(int i = 0; i < faktura.getPlinskiRezervoarStavke().size(); i++)
			{
				if(stavkePs.isClosed()) stavkePs = dbUtils.getPreparedStatement(stavkeQuery);
				PlinskiRezervoar currentRezervoar = rezervoari.get(i).getValue0();
				Date iznajmljenDate = rezervoari.get(i).getValue1();
				double rezervoarPrice = rezervoari.get(i).getValue2();
				stavkePs.setInt(1, fakturaId);
				stavkePs.setInt(2, currentRezervoar.getId());
				stavkePs.setInt(3, 1);
				stavkePs.setString(4, currentRezervoar.getTipRezervoara());
				stavkePs.setDouble(5, rezervoarPrice);
				stavkePs.setInt(6, currentRezervoar.getKapacitet());
				stavkePs.setDate(7, dbUtils.getSqlDate(iznajmljenDate));
				stavkePs.execute();
			}
			for(int i = 0; i < faktura.getPlinskeBoceStavke().size(); i++)
			{
				if(stavkePs.isClosed()) stavkePs = dbUtils.getPreparedStatement(stavkeQuery);
				PlinskaBoca currentBoca = boce.get(i).getValue0();
				Date iznajmljenDate = boce.get(i).getValue1();
				double bocaPrice = boce.get(i).getValue2();
				stavkePs.setInt(1, fakturaId);
				stavkePs.setNull(2, java.sql.Types.INTEGER);
				stavkePs.setInt(3, currentBoca.getKolicina());
				stavkePs.setString(4, "Plinska boca");
				stavkePs.setDouble(5, bocaPrice);
				stavkePs.setInt(6, currentBoca.getKapacitet());
				stavkePs.setDate(7, dbUtils.getSqlDate(iznajmljenDate));
				stavkePs.execute();
			}
		}
		catch(Exception e)
		{
			dbUtils.printExceptionMessage(e.getMessage(), "izvjestaj insert");
			dbUtils.logException(Level.SEVERE, e.getMessage(), e);
			dbUtils.closeCurrentConnection();
		}
	}
	
	public int getMaxId()
	{
		String query = "SELECT max(id) FROM fakture_iznajmljivanje";
		
		PreparedStatement ps = dbUtils.getPreparedStatement(query);
		if(ps == null) return -1;
		try
		{
			ResultSet rsProdaja = ps.executeQuery();
			while(rsProdaja.next())
			{
				int id = rsProdaja.getInt(1);
				return id;
			}
			return -1;
		}
		catch(Exception e)
		{
			dbUtils.printExceptionMessage(e.getMessage(), "izvjestaj insert");
			dbUtils.logException(Level.SEVERE, e.getMessage(), e);
			dbUtils.closeCurrentConnection();
			return -1;
		}
	}
	
	public String formBrojFakture()
	{
		String query = "SELECT count(*) FROM fakture_prodaja";
		String queryIznajmi = "SELECT count(*) FROM fakture_iznajmljivanje";
		
		PreparedStatement ps = dbUtils.getPreparedStatement(query);
		PreparedStatement psIznajmi = dbUtils.getPreparedStatement(queryIznajmi);
		if(ps == null || psIznajmi == null) return "";
		try
		{
			ResultSet rsProdaja = ps.executeQuery();
			ResultSet rsIznajmi = psIznajmi.executeQuery();
			int brojProdaja = 0;
			int brojIznajmi = 0;
			while(rsProdaja.next()){ brojProdaja = rsProdaja.getInt(1); }
			while(rsIznajmi.next()){ brojIznajmi = rsIznajmi.getInt(1);}
			int broj = brojIznajmi + brojProdaja + 1;
			Calendar date = new GregorianCalendar();
			date.setTime(new Date());
			int year = date.get(Calendar.YEAR);  
			return "F" + broj + "-" + year;
		}
		catch(Exception e)
		{
			dbUtils.printExceptionMessage(e.getMessage(), "izvjestaj insert");
			dbUtils.logException(Level.SEVERE, e.getMessage(), e);
			dbUtils.closeCurrentConnection();
			return "";
		}
	}
}
