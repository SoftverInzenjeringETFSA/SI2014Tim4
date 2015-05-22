package ba.etf.unsa.si.tim4.tim4app.daldao;

import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Level;

import org.javatuples.Triplet;

import ba.etf.unsa.si.tim4.tim4app.classes.FakturaProdaje;
import ba.etf.unsa.si.tim4.tim4app.classes.Izvjestaj;
import ba.etf.unsa.si.tim4.tim4app.classes.PlinskaBoca;
import ba.etf.unsa.si.tim4.tim4app.classes.PlinskiRezervoar;

import com.mysql.jdbc.PreparedStatement;

public class FaktureProdajeDataSource {

	private DatabaseUtils dbUtils;
	
	public FaktureProdajeDataSource()
	{
		dbUtils = new DatabaseUtils();
		dbUtils.getConnection();
	}
	
	public void insert(FakturaProdaje faktura)
	{
		String query = "INSERT INTO fakture_prodaja(broj_fakture, komitent) VALUES(?, ?)";
		String getIdQuery = "SELECT max(id) FROM fakture_prodaja";
		String stavkeQuery = "INSERT INTO stavkefakture_prodaja(faktura, rezervoar,"
				+ "kolicina, tip_rezervoara, cijena, kapacitetBoce) VALUES(?, ?, ?, ?, ?, ?)";
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
		LinkedList<Triplet<PlinskaBoca, Date, Double>> boce = new LinkedList<Triplet<PlinskaBoca, Date, Double>>();
		try
		{
			for(int i = 0; i < faktura.getPlinskiRezervoarStavke().size(); i++)
			{
				PlinskiRezervoar currentRezervoar = rezervoari.get(i).getValue0();
				double rezervoarPrice = rezervoari.get(i).getValue2();
				stavkePs.setInt(1, fakturaId);
				stavkePs.setInt(2, currentRezervoar.getId());
				stavkePs.setInt(3, 1);
				stavkePs.setString(4, currentRezervoar.getTipRezervoara());
				stavkePs.setDouble(5, rezervoarPrice);
				stavkePs.setInt(6, currentRezervoar.getKapacitet());
				stavkePs.execute();
			}
			for(int i = 0; i < faktura.getPlinskeBoceStavke().size(); i++)
			{
				PlinskaBoca currentBoca = boce.get(i).getValue0();
				double bocaPrice = boce.get(i).getValue2();
				stavkePs.setInt(1, fakturaId);
				stavkePs.setNull(2, java.sql.Types.INTEGER);
				stavkePs.setInt(3, currentBoca.getKolicina());
				stavkePs.setString(4, "Plinska boca");
				stavkePs.setDouble(5, bocaPrice);
				stavkePs.setInt(6, currentBoca.getKapacitet());
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
	
	private String formBrojFakture()
	{
		String query = "SELECT count(*) FROM fakture_prodaje";
		String queryIznajmi = "SELECT count(*) FROM fakture_iznajmljivanja";
		
		PreparedStatement ps = dbUtils.getPreparedStatement(query);
		PreparedStatement psIznajmi = dbUtils.getPreparedStatement(queryIznajmi);
		if(ps == null || psIznajmi == null) return "";
		try
		{
			ResultSet rsProdaja = ps.executeQuery();
			ResultSet rsIznajmi = psIznajmi.executeQuery();
			int broj = rsProdaja.getInt(1) + rsIznajmi.getInt(2);
			Calendar c = Calendar.getInstance();
			c.setTime(new Date());
			return "F" + broj + "-" + c.YEAR;
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
