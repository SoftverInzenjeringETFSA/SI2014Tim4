package ba.etf.unsa.si.tim4.tim4app.daldao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Level;
import ba.etf.unsa.si.tim4.tim4app.classes.PlinskiRezervoar;

import com.mysql.jdbc.PreparedStatement;

public class PlinskiRezervoarDataSource {
	
private DatabaseUtils dbUtils;
	
	public PlinskiRezervoarDataSource()
	{
		dbUtils = new DatabaseUtils();
		dbUtils.getConnection();
	}
	
	public void insert(PlinskiRezervoar pr)
	{
		String query = "INSERT INTO plinski_rezervoari(serijski_broj, kapacitet, tezina, napunjenost, tip"
				+ ", datum_zadnjeg_bazdarenja, lokacija, trenutni_status) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = dbUtils.getPreparedStatement(query);
		if(ps == null) return;
		try{
			ps.setString(1, pr.getSerijskiBroj());
			ps.setInt(2, pr.getKapacitet());
			ps.setInt(3, pr.getTezina());
			ps.setInt(4, pr.getNapunjenost());
			ps.setString(5, pr.getTipRezervoara());
			ps.setDate(6, dbUtils.getSqlDate(pr.getDatumZadnjegBazdarenja()));
			ps.setString(7, pr.getLokacija());
			ps.setString(8, pr.getTrenutniStatus());
			ps.execute();
			dbUtils.closeCurrentConnection();
		}
		catch(SQLException e)
		{
			dbUtils.printExceptionMessage(e.getMessage(), "skladiste plinskih boca insert");
			dbUtils.logException(Level.SEVERE, e.getMessage(), e);
			dbUtils.closeCurrentConnection();
		}
	}
	
	public void update(PlinskiRezervoar pr)
	{
		String query = "UPDATE plinski_rezervoari SET serijski_broj=?, kapacitet=?, tezina=?, napunjenost=?, tip=?"
				+ ", datum_zadnjeg_bazdarenja=?, lokacija=?, trenutni_status=?";
		PreparedStatement ps = dbUtils.getPreparedStatement(query);
		if(ps == null) return;
		try
		{
			ps.setString(1, pr.getSerijskiBroj());
			ps.setInt(2, pr.getKapacitet());
			ps.setInt(3, pr.getTezina());
			ps.setInt(4, pr.getNapunjenost());
			ps.setString(5, pr.getTipRezervoara());
			ps.setDate(6, dbUtils.getSqlDate(pr.getDatumZadnjegBazdarenja()));
			ps.setString(7, pr.getLokacija());
			ps.setString(8, pr.getTrenutniStatus());
			ps.execute();
			dbUtils.closeCurrentConnection();
		}
		catch(SQLException e)
		{
			dbUtils.printExceptionMessage(e.getMessage(), "skladiste plinskih boca update");
			dbUtils.logException(Level.SEVERE, e.getMessage(), e);
			dbUtils.closeCurrentConnection();
		}
	}
	
	public void delete(int id)
	{
		String query = "DELETE FROM plinski_rezervoari WHERE id=?";
		PreparedStatement ps = dbUtils.getPreparedStatement(query);
		if(ps == null) return;
		try{
			ps.setInt(1, id);
			ps.execute();
			dbUtils.closeCurrentConnection();
		}
		catch(Exception e)
		{
			dbUtils.printExceptionMessage(e.getMessage(), "skladiste_plinskih_boca delete");
			dbUtils.logException(Level.SEVERE, e.getMessage(), e);
			dbUtils.closeCurrentConnection();
		}
	}
	
	public PlinskiRezervoar getRezervoarById(int id)
	{
		String query = "SELECT id, serijski_broj, kapacitet, tezina, napunjenost, tip"
				+ ", datum_zadnjeg_bazdarenja, lokacija, trenutni_status FROM plinski_rezervoari WHERE id=?";
		PreparedStatement ps = dbUtils.getPreparedStatement(query);
		PlinskiRezervoar toRet = null;
		if(ps == null) return null;
		try
		{
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				int idValue = rs.getInt(1);
				String serijskiBroj = rs.getString(2);
				int kapacitet = rs.getInt(3);
				int tezina = rs.getInt(4);
				int napunjenost = rs.getInt(5);
				String tip = rs.getString(6);
				Date datumBazdarenja = rs.getDate(7);
				String lokacija = rs.getString(8);
				String trenutniStatus = rs.getString(9);
				PlinskiRezervoar pr = new PlinskiRezervoar(serijskiBroj, kapacitet, tezina, napunjenost,
										  tip, datumBazdarenja, lokacija, trenutniStatus);
				pr.setId(idValue);
				toRet = pr;
				dbUtils.closeCurrentConnection();
			}
			dbUtils.closeCurrentConnection();
			return toRet;
		}
		catch(SQLException e)
		{
			dbUtils.printExceptionMessage(e.getMessage(), "skladiste plinskih boca getAll()");
			dbUtils.logException(Level.SEVERE, e.getMessage(), e);
			dbUtils.closeCurrentConnection();
			return null;
		}
	}
	
	public LinkedList<PlinskiRezervoar> getAll()
	{
		String query = "SELECT id, serijski_broj, kapacitet, tezina, napunjenost, tip"
				+ ", datum_zadnjeg_bazdarenja, lokacija, trenutni_status FROM plinski_rezervoari";
		PreparedStatement ps = dbUtils.getPreparedStatement(query);
		LinkedList<PlinskiRezervoar> toRet = new LinkedList<PlinskiRezervoar>();
		if(ps == null) return null;
		try
		{
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				int id = rs.getInt(1);
				String serijskiBroj = rs.getString(2);
				int kapacitet = rs.getInt(3);
				int tezina = rs.getInt(4);
				int napunjenost = rs.getInt(5);
				String tip = rs.getString(6);
				Date datumBazdarenja = rs.getDate(7);
				String lokacija = rs.getString(8);
				String trenutniStatus = rs.getString(9);
				PlinskiRezervoar pr = new PlinskiRezervoar(serijskiBroj, kapacitet, tezina, napunjenost,
										  tip, datumBazdarenja, lokacija, trenutniStatus);
				pr.setId(id);
				toRet.add(pr);
			}
			dbUtils.closeCurrentConnection();
			return toRet;
		}
		catch(SQLException e)
		{
			dbUtils.printExceptionMessage(e.getMessage(), "skladiste plinskih boca getAll()");
			dbUtils.logException(Level.SEVERE, e.getMessage(), e);
			dbUtils.closeCurrentConnection();
			return null;
		}
	}
	
	public int getCount()
	{
		String query = "SELECT count(*) FROM plinski_rezervoari";
		PreparedStatement ps = dbUtils.getPreparedStatement(query);
		if(ps == null) return -1;
		try
		{
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				int count = rs.getInt(1);
				dbUtils.closeCurrentConnection();
				return count;
			}
			dbUtils.closeCurrentConnection();
			return -1;
		}
		catch(Exception e)
		{
			dbUtils.printExceptionMessage(e.getMessage(), "getCount()");
			dbUtils.logException(Level.SEVERE, e.getMessage(), e);
			dbUtils.closeCurrentConnection();
			return -1;
		}
	}
}