package ba.etf.unsa.si.tim4.tim4app.daldao;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.logging.Level;
import com.mysql.jdbc.PreparedStatement;

import ba.etf.unsa.si.tim4.tim4app.classes.Izvjestaj;

public class IzvjestajiDataSource {

	private DatabaseUtils dbUtils;
	
	public IzvjestajiDataSource()
	{
		dbUtils = new DatabaseUtils();
		dbUtils.getConnection();
	}
	
	public void insert(Izvjestaj toInsert)
	{
		String query = "INSERT INTO izvjestaji(tip_izvjestaja, broj_izvjestaja"
				+ ", datum_izvjestaja, parametar_izvjestaja) "
				+ "VALUES(?, ?, ?, ?)";
		PreparedStatement ps = dbUtils.getPreparedStatement(query);
		if(ps == null) return;
		try{
			ps.setString(1, toInsert.getTipIzvjestaja());
			ps.setString(2, toInsert.getBrojIzvjestaja());
			ps.setDate(3, dbUtils.getSqlDate(toInsert.getDatumIzvjestaja()));
			ps.setString(4, toInsert.getParametarIzvjestaja());
			ps.execute();
			dbUtils.closeCurrentConnection();
		}
		catch(Exception e)
		{
			dbUtils.printExceptionMessage(e.getMessage(), "izvjestaj insert");
			dbUtils.logException(Level.SEVERE, e.getMessage(), e);
			dbUtils.closeCurrentConnection();
		}
	}
	
	public void update(Izvjestaj report)
	{
		String query = "UPDATE izvjestaji SET tip_izvjestaja= ?, broj_izvjestaja= ?"
				+ ", datum_izvjestaja=?, parametar_izvjestaja=? WHERE id= ?";
		PreparedStatement ps = dbUtils.getPreparedStatement(query);
		if(ps == null) return;
		try{
			ps.setString(1, report.getTipIzvjestaja());
			ps.setString(2, report.getBrojIzvjestaja());
			ps.setDate(3, (Date) report.getDatumIzvjestaja());
			ps.setString(4, report.getParametarIzvjestaja());
			ps.setInt(5, report.getId());
			ps.execute();
		}
		catch(Exception e)
		{
			dbUtils.printExceptionMessage(e.getMessage(), "izvjestaj update");
			dbUtils.logException(Level.SEVERE, e.getMessage(), e);
			dbUtils.closeCurrentConnection();
		}
	}
	
	public void delete(int id)
	{
		String query = "DELETE FROM izvjestaji WHERE id= ?";
		PreparedStatement ps = dbUtils.getPreparedStatement(query);
		if(ps == null) return;
		try{
			ps.setInt(1, id);
			ps.execute();
		}
		catch(Exception e)
		{
			dbUtils.printExceptionMessage(e.getMessage(), "izvjestaj delete");
			dbUtils.logException(Level.SEVERE, e.getMessage(), e);
			dbUtils.closeCurrentConnection();
		}
	}
	
	public int getMaxId()
	{
		String query = "SELECT max(id) FROM izvjestaji";
		PreparedStatement ps = dbUtils.getPreparedStatement(query);
		if(ps == null) return -1;
		try
		{
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				int id = rs.getInt(1);
				return id;
			}
			return -1;
		}
		catch(Exception e)
		{
			dbUtils.printExceptionMessage(e.getMessage(), "getMaxId()");
			dbUtils.logException(Level.SEVERE, e.getMessage(), e);
			dbUtils.closeCurrentConnection();
			return -1;
		}
	}
	
}
