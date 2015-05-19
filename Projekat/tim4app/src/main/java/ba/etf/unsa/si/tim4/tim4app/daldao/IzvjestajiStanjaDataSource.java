package ba.etf.unsa.si.tim4.tim4app.daldao;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.logging.Level;

import ba.etf.unsa.si.tim4.tim4app.classes.IzvjestajStanja;
import ba.etf.unsa.si.tim4.tim4app.classes.PlinskaBoca;
import ba.etf.unsa.si.tim4.tim4app.classes.PlinskiRezervoar;

import com.mysql.jdbc.PreparedStatement;

public class IzvjestajiStanjaDataSource {

	private DatabaseUtils dbUtils;
	
	public IzvjestajiStanjaDataSource()
	{
		dbUtils = new DatabaseUtils();
		dbUtils.getConnection();
	}
	
	public void insert(IzvjestajStanja toInsert)
	{
		String query = "INSERT INTO izvjestaji_stanja_skladiste(tip_izvjestaja, broj_izvjestaja"
				+ ", datum_izvjestaja) "
				+ "VALUES(?, ?, ?, ?)";
		PreparedStatement ps = dbUtils.getPreparedStatement(query);
		if(ps == null) return;
		try{
			ps.setString(1, toInsert.getTipIzvjestaja());
			ps.setString(2, toInsert.getBrojIzvjestaja());
			ps.setDate(3, dbUtils.getSqlDate(toInsert.getDatumIzvjestaja()));
			ps.execute();
			dbUtils.closeCurrentConnection();
		}
		catch(Exception e)
		{
			dbUtils.printExceptionMessage(e.getMessage(), "izvjestajStanja insert");
			dbUtils.logException(Level.SEVERE, e.getMessage(), e);
			dbUtils.closeCurrentConnection();
		}
	}
	
	public void update(IzvjestajStanja report)
	{
		String query = "UPDATE izvjestaji_stanja_skladiste SET tip_izvjestaja= ?, broj_izvjestaja= ?"
				+ ", datum_izvjestaja=? WHERE id= ?";
		PreparedStatement ps = dbUtils.getPreparedStatement(query);
		if(ps == null) return;
		try{
			ps.setString(1, report.getTipIzvjestaja());
			ps.setString(2, report.getBrojIzvjestaja());
			ps.setDate(3, (Date) report.getDatumIzvjestaja());
			ps.setInt(5, report.getId());
			ps.execute();
			dbUtils.closeCurrentConnection();
		}
		catch(Exception e)
		{
			dbUtils.printExceptionMessage(e.getMessage(), "izvjestajStanja update");
			dbUtils.logException(Level.SEVERE, e.getMessage(), e);
			dbUtils.closeCurrentConnection();
		}
	}
	
	public void delete(int id)
	{
		String query = "DELETE FROM izvjestaji_stanja_skladiste WHERE id= ?";
		PreparedStatement ps = dbUtils.getPreparedStatement(query);
		if(ps == null) return;
		try{
			ps.setInt(1, id);
			ps.execute();
			dbUtils.closeCurrentConnection();
		}
		catch(Exception e)
		{
			dbUtils.printExceptionMessage(e.getMessage(), "izvjestajStanja delete");
			dbUtils.logException(Level.SEVERE, e.getMessage(), e);
			dbUtils.closeCurrentConnection();
		}
	}
	
	public LinkedList<PlinskiRezervoar> getStavkeIzvjestajaRezervoari(int izvjestaj_stanja)
	{
		LinkedList<PlinskiRezervoar> toRet = new LinkedList<PlinskiRezervoar>();
		String query = "SELECT rezervoar FROM izvjestaji_stanja_stavke WHERE izvjestaj=?";
		PlinskiRezervoarDataSource prds = new PlinskiRezervoarDataSource();
		PreparedStatement ps = dbUtils.getPreparedStatement(query);
		if(ps == null) return toRet;
		try
		{
			ps.setInt(1, izvjestaj_stanja);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				toRet.add(prds.getRezervoarById(rs.getInt(1)));
			}
			dbUtils.closeCurrentConnection();
			return toRet;
		}
		catch(Exception e)
		{
			dbUtils.printExceptionMessage(e.getMessage(), "GetStavkeIzvjestajaRezervoari()");
			dbUtils.logException(Level.SEVERE, e.getMessage(), e);
			dbUtils.closeCurrentConnection();
			return toRet;
		}
	}
	
	public LinkedList<PlinskaBoca> getStavkeIzvjestajaBoce(int izvjestaj_stanja)
	{
		LinkedList<PlinskaBoca> toRet = new LinkedList<PlinskaBoca>();
		String query = "SELECT kapacitet, kolicina FROM izvjestaji_stanja_stavke WHERE izvjestaj=? AND kapacitet IS NOT NULL AND kolicina IS NOT NULL";
		PreparedStatement ps = dbUtils.getPreparedStatement(query);
		if(ps == null) return toRet;
		try
		{
			ps.setInt(1, izvjestaj_stanja);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				int kapacitet = rs.getInt(1);
				int kolicina = rs.getInt(2);
				PlinskaBoca pb = new PlinskaBoca(kapacitet, kolicina);
				toRet.add(pb);
			}
			dbUtils.closeCurrentConnection();
			return toRet;
		}
		catch(Exception e)
		{
			dbUtils.printExceptionMessage(e.getMessage(), "GetStavkeIzvjestajaRezervoari()");
			dbUtils.logException(Level.SEVERE, e.getMessage(), e);
			dbUtils.closeCurrentConnection();
			return toRet;
		}
	}
}
