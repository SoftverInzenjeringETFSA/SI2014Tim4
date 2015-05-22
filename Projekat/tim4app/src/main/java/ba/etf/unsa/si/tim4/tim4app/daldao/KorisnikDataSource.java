package ba.etf.unsa.si.tim4.tim4app.daldao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;

import ba.etf.unsa.si.tim4.tim4app.classes.FizickiKomitent;
import ba.etf.unsa.si.tim4.tim4app.classes.Komitent;
import ba.etf.unsa.si.tim4.tim4app.classes.Korisnik;
import ba.etf.unsa.si.tim4.tim4app.classes.PravniKomitent;

import com.mysql.jdbc.PreparedStatement;

public class KorisnikDataSource {
	
	private DatabaseUtils dbUtils;

	public KorisnikDataSource()
	{
		dbUtils = new DatabaseUtils();
		dbUtils.getConnection();
	}
	
	public void insert(Korisnik k)
	{
		String query = "INSERT INTO korisnici(tip, username, lozinka, ime, prezime, broj_licne_karte, adresa,"
				+ "broj_telefona, datum_zaposljavanja) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = dbUtils.getPreparedStatement(query);
		if(ps == null) return;
		try{
			ps.setString(1, k.getTipKorisnika());
			ps.setString(2, k.getUsername());
			ps.setString(3, k.getPassword());
			ps.setString(4, k.getIme());
			ps.setString(5, k.getPrezime());
			ps.setString(6, k.getBrojLicneKarte());
			ps.setString(7, k.getAdresa());
			ps.setDate(8, dbUtils.getSqlDate(k.getDatumZaposlenja()));
			ps.execute();
			dbUtils.closeCurrentConnection();
		}
		catch(Exception e)
		{
			dbUtils.printExceptionMessage(e.getMessage(), "korisnik insert");
			dbUtils.logException(Level.SEVERE, e.getMessage(), e);
			dbUtils.closeCurrentConnection();
		}
	}
	
	public void update(Korisnik k)
	{
		String query = "UPDATE korisnici SET tip=?, username=?, lozinka=?, ime=?, prezime=?, broj_licne_karte=?, adresa=?,"
				+ "broj_telefona=?, datum_zaposljavanja=? WHERE id=?";
		PreparedStatement ps = dbUtils.getPreparedStatement(query);
		if(ps == null) return;
		try{
			ps.setString(1, k.getTipKorisnika());
			ps.setString(2, k.getUsername());
			ps.setString(3, k.getPassword());
			ps.setString(4, k.getIme());
			ps.setString(5, k.getPrezime());
			ps.setString(6, k.getBrojLicneKarte());
			ps.setString(7, k.getAdresa());
			ps.setDate(8, dbUtils.getSqlDate(k.getDatumZaposlenja()));
			ps.setInt(9, k.getId());
			ps.execute();
			dbUtils.closeCurrentConnection();
		}
		catch(Exception e)
		{
			dbUtils.printExceptionMessage(e.getMessage(), "korisnik update");
			dbUtils.logException(Level.SEVERE, e.getMessage(), e);
			dbUtils.closeCurrentConnection();
		}
	}
	
	public void delete(int id)
	{
		String query = "DELETE FROM korisnici WHERE id=?";
		PreparedStatement ps = dbUtils.getPreparedStatement(query);
		if(ps == null) return;
		try{
			ps.setInt(1, id);
			ps.execute();
			dbUtils.closeCurrentConnection();
		}
		catch(Exception e)
		{
			dbUtils.printExceptionMessage(e.getMessage(), "korisnik delete");
			dbUtils.logException(Level.SEVERE, e.getMessage(), e);
			dbUtils.closeCurrentConnection();
		}
	}
	
	public Korisnik getKorisnikById(int id)
	{
		String query = "SELECT tip, username, lozinka, ime, prezime, broj_licne_karte, adresa,"
				+ "broj_telefona, datum_zaposljavanja FROM korisnici WHERE id=?";
		PreparedStatement ps = dbUtils.getPreparedStatement(query);
		if(ps == null) return null;
		Korisnik toRet = null;
		try {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
					String tip = rs.getString(1);
					String username = rs.getString(2);
					String lozinka = rs.getString(3);
					String ime = rs.getString(4);
					String prezime = rs.getString(5);
					String brojLicneKarte = rs.getString(6);
					String adresa = rs.getString(7);
					String telefon = rs.getString(8);
					Date datum = rs.getDate(9);
					toRet = new Korisnik(tip, username, lozinka, ime, prezime, brojLicneKarte, adresa, telefon, datum);
			}
					dbUtils.closeCurrentConnection();
					return toRet;
		} catch (SQLException e) {
			dbUtils.printExceptionMessage(e.getMessage(), "getKomitentById()");
			dbUtils.logException(Level.SEVERE, e.getMessage(), e);
			dbUtils.closeCurrentConnection();
			return null;
		}
	}
	
	public int getCount(String username, String password)
	{
		String query = "SELECT count(*) FROM korisnici WHERE username=? AND lozinka=?";
		PreparedStatement ps = dbUtils.getPreparedStatement(query);
		if(ps == null) return -1;
		try {
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
				while(rs.next()){
					int count = rs.getInt(1);
					return count;
				}
			dbUtils.closeCurrentConnection();
			return -1;
		} catch (SQLException e) {
			dbUtils.printExceptionMessage(e.getMessage(), "getAll()");
			dbUtils.logException(Level.SEVERE, e.getMessage(), e);
			dbUtils.closeCurrentConnection();
			return -1;
		}
	}
	

	public LinkedList<Korisnik> getAll()
	{
		LinkedList<Korisnik> toRet = new LinkedList<Korisnik>();
		String query = "SELECT tip, username, lozinka, ime, prezime, broj_licne_karte, adresa,"
				+ "broj_telefona, datum_zaposljavanja, id FROM korisnici";
		PreparedStatement ps = dbUtils.getPreparedStatement(query);
		if(ps == null) return null;
		try {
			ResultSet rs = ps.executeQuery();
				while(rs.next()){
					String tip = rs.getString(1);
					String username = rs.getString(2);
					String lozinka = rs.getString(3);
					String ime = rs.getString(4);
					String prezime = rs.getString(5);
					String brojLicneKarte = rs.getString(6);
					String adresa = rs.getString(7);
					String telefon = rs.getString(8);
					Date datum = rs.getDate(9);
					int id = rs.getInt(10);
					Korisnik k = new Korisnik(id, tip, username, lozinka, ime, prezime, brojLicneKarte, adresa, telefon, datum);
					toRet.add(k);
				}
			dbUtils.closeCurrentConnection();
			return toRet;
		} catch (SQLException e) {
			dbUtils.printExceptionMessage(e.getMessage(), "getAll()");
			dbUtils.logException(Level.SEVERE, e.getMessage(), e);
			dbUtils.closeCurrentConnection();
			return null;
		}
	}
}
