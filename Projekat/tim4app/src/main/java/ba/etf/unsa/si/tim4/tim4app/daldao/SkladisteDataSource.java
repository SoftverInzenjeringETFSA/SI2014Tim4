package ba.etf.unsa.si.tim4.tim4app.daldao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

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
		}
		catch(SQLException e)
		{
			dbUtils.printExceptionMessage(e.getMessage(), "skladiste plinskih boca insert");
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
		}
		catch(SQLException e)
		{
			dbUtils.printExceptionMessage(e.getMessage(), "skladiste plinskih boca update");
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
		}
		catch(Exception e)
		{
			dbUtils.printExceptionMessage(e.getMessage(), "skladiste_plinskih_boca delete");
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
			return toRet;
		}
		catch(SQLException e)
		{
			dbUtils.printExceptionMessage(e.getMessage(), "skladiste plinskih boca getAll()");
			return null;
		}
	}
}
