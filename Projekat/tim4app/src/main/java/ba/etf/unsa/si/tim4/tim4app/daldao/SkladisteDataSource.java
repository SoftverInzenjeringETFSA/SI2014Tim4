package ba.etf.unsa.si.tim4.tim4app.daldao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;

import ba.etf.unsa.si.tim4.tim4app.classes.PlinskaBoca;

import com.mysql.jdbc.PreparedStatement;

public class SkladisteDataSource {
	
	private DatabaseUtils dbUtils;
	
	public SkladisteDataSource()
	{
		dbUtils = new DatabaseUtils();
		dbUtils.getConnection();
	}
	
	public void insert(PlinskaBoca pb)
	{
		String query = "INSERT INTO skladiste_plinskih_boca(kapacitet, kolicina, cijena) VALUES(?, ?, ?)";
		PreparedStatement ps = dbUtils.getPreparedStatement(query);
		if(ps == null) return;
		try{
			ps.setInt(1, pb.getKapacitet());
			ps.setInt(2, pb.getKolicina());
			ps.setDouble(3, pb.getCijena());
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
	
	public void update(PlinskaBoca pb)
	{
		String query = "UPDATE skladiste_plinskih_boca SET kapacitet=?, kolicina=?, cijena=? WHERE id=?";
		PreparedStatement ps = dbUtils.getPreparedStatement(query);
		if(ps == null) return;
		try
		{
			ps.setInt(1, pb.getKapacitet());
			ps.setInt(2, pb.getKolicina());
			ps.setDouble(3, pb.getCijena());
			ps.setInt(4, pb.getId());
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
		String query = "DELETE FROM skladiste_plinskih_boca WHERE id=?";
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
	
	public LinkedList<PlinskaBoca> getAll()
	{
		String query = "SELECT id, kapacitet, kolicina, cijena FROM skladiste_plinskih_boca";
		PreparedStatement ps = dbUtils.getPreparedStatement(query);
		LinkedList<PlinskaBoca> toRet = new LinkedList<PlinskaBoca>();
		if(ps == null) return null;
		try
		{
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				int id = rs.getInt(1);
				int kapacitet = rs.getInt(2);
				int kolicina = rs.getInt(3);
				double cijena = rs.getDouble(4);
				PlinskaBoca pb = new PlinskaBoca(kapacitet, cijena, kolicina);
				pb.setId(id);
				toRet.add(pb);
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
	
	public PlinskaBoca getById(int id)
	{
		String query = "SELECT id, kapacitet, kolicina, cijena FROM skladiste_plinskih_boca WHERE id=?";
		PreparedStatement ps = dbUtils.getPreparedStatement(query);
		PlinskaBoca toRet = null;
		if(ps == null) return null;
		try
		{
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				int idValue = rs.getInt(1);
				int kapacitet = rs.getInt(2);
				int kolicina = rs.getInt(3);
				double cijena = rs.getDouble(4);
				PlinskaBoca pb = new PlinskaBoca(kapacitet, cijena, kolicina);
				pb.setId(idValue);
				toRet = pb;
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
		String query = "SELECT count(*) FROM skladiste_plinskih_boca";
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
