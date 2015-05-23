package ba.etf.unsa.si.tim4.tim4app.daldao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import ba.etf.unsa.si.tim4.tim4app.classes.Promjena;

import com.mysql.jdbc.PreparedStatement;

public class PromjeneDataSource {

private DatabaseUtils dbUtils;
	
	public PromjeneDataSource()
	{
		dbUtils = new DatabaseUtils();
		dbUtils.getConnection();
	}
	
	public void insert(Promjena p)
	{
		String query = "INSERT INTO promjene_na_rezervoarima(serijski_broj, datum_promjene,"
				+ "tip_promjene, opis_promjene) VALUES(?, ?, ?, ?)";
		PreparedStatement ps = dbUtils.getPreparedStatement(query);
		if(ps == null) return;
		try{
			ps.setString(1, p.getSerijskiBroj());
			ps.setDate(2, dbUtils.getSqlDate(p.getDatumPromjene()));
			ps.setInt(3, p.getTipPromjene());
			ps.setString(4, p.getOpisPromjene());
			ps.execute();
			dbUtils.closeCurrentConnection();
		}
		catch(SQLException e)
		{
			dbUtils.printExceptionMessage(e.getMessage(), "promjena insert");
			dbUtils.logException(Level.SEVERE, e.getMessage(), e);
			dbUtils.closeCurrentConnection();
		}
	}
	
	public void update(Promjena p)
	{
		String query = "UPDATE promjene_na_rezervoarima SET serijski_broj=?, datum_promjene=?"
				+ ", tip_promjene=?, opis_promjene=? WHERE id=?";
		PreparedStatement ps = dbUtils.getPreparedStatement(query);
		if(ps == null) return;
		try
		{
			ps.setString(1, p.getSerijskiBroj());
			ps.setDate(2, dbUtils.getSqlDate(p.getDatumPromjene()));
			ps.setInt(3, p.getTipPromjene());
			ps.setString(4, p.getOpisPromjene());
			ps.setInt(5, p.getId());
			ps.execute();
			dbUtils.closeCurrentConnection();
		}
		catch(SQLException e)
		{
			dbUtils.printExceptionMessage(e.getMessage(), "promjene update");
			dbUtils.logException(Level.SEVERE, e.getMessage(), e);
			dbUtils.closeCurrentConnection();
		}
	}
	
	public void delete(int id)
	{
		String query = "DELETE FROM promjene_na_rezervoarima WHERE id=?";
		PreparedStatement ps = dbUtils.getPreparedStatement(query);
		if(ps == null) return;
		try{
			ps.setInt(1, id);
			ps.execute();
			dbUtils.closeCurrentConnection();
		}
		catch(Exception e)
		{
			dbUtils.printExceptionMessage(e.getMessage(), "promjene delete");
			dbUtils.logException(Level.SEVERE, e.getMessage(), e);
			dbUtils.closeCurrentConnection();
		}
	}
	
	public int getTipPromjeneFromSifarnik(String promjena)
	{
		String query = "SELECT sifra_promjene FROM sifarnik_promjena WHERE naziv_promjene=?";
		PreparedStatement ps = dbUtils.getPreparedStatement(query);
		if(ps == null) return -1;
		try{
			ps.setString(1, promjena);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				int id = rs.getInt(1);
				return id;
			}
			dbUtils.closeCurrentConnection();
			return -1;
		}
		catch(Exception e)
		{
			dbUtils.printExceptionMessage(e.getMessage(), "promjene delete");
			dbUtils.logException(Level.SEVERE, e.getMessage(), e);
			dbUtils.closeCurrentConnection();
			return -1;
		}
	}
}
