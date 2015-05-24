package ba.etf.unsa.si.tim4.tim4app.daldao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;

import ba.etf.unsa.si.tim4.tim4app.classes.FizickiKomitent;
import ba.etf.unsa.si.tim4.tim4app.classes.Komitent;
import ba.etf.unsa.si.tim4.tim4app.classes.PravniKomitent;

import com.mysql.jdbc.PreparedStatement;

public class KomitentDataSource {
	
	private DatabaseUtils dbUtils;

	public KomitentDataSource()
	{
		dbUtils = new DatabaseUtils();
		dbUtils.getConnection();
	}
	
	public void insert(Komitent k)
	{
		String query = "INSERT INTO komitenti(tip_komitenta, adresa, broj_telefona, email, ime, prezime, JMB, broj_licne_karte, "
				+ "nazivFirme, pdvBroj) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = dbUtils.getPreparedStatement(query);
		if(ps == null) return;
		try{
			ps.setString(1, k.getTipKomitenta());
			ps.setString(2, k.getAdresa());
			ps.setString(3, k.getBrojTelefona());
			ps.setString(4, k.getEmail());
			if(k.getTipKomitenta().equals("Pravno lice"))
			{
				PravniKomitent pk = (PravniKomitent) k;
				ps.setNull(5, java.sql.Types.VARCHAR);
				ps.setNull(6, java.sql.Types.VARCHAR);
				ps.setNull(7, java.sql.Types.VARCHAR);
				ps.setNull(8, java.sql.Types.VARCHAR);
				ps.setString(9, pk.getNazivFirme());
				ps.setString(10, pk.getPDVbroj());
			}
			else
			{
				FizickiKomitent fk = (FizickiKomitent) k;
				ps.setNull(9, java.sql.Types.VARCHAR);
				ps.setNull(10, java.sql.Types.VARCHAR);
				ps.setString(5, fk.getIme());
				ps.setString(6, fk.getPrezime());
				ps.setString(7, fk.getJMB());
				ps.setString(8, fk.getBrojLicneKarte());
			}
			ps.execute();
			dbUtils.closeCurrentConnection();
		}
		catch(Exception e)
		{
			dbUtils.printExceptionMessage(e.getMessage(), "komitent insert");
			dbUtils.logException(Level.SEVERE, e.getMessage(), e);
			dbUtils.closeCurrentConnection();
		}
	}
	
	public void update(Komitent k)
	{
		String query = "UPDATE komitenti SET tip_komitenta=?, adresa=?, broj_telefona=?, email=?, ime=?, prezime=?, JMB=?,"
				+ "broj_licne_karte=?, nazivFirme=?, pdvBroj=? where id=?";
		PreparedStatement ps = dbUtils.getPreparedStatement(query);
		if(ps == null) return;
		try{
			ps.setInt(11, k.getId());
			ps.setString(1, k.getTipKomitenta());
			ps.setString(2, k.getAdresa());
			ps.setString(3, k.getBrojTelefona());
			ps.setString(4, k.getEmail());
			if(k.getTipKomitenta().equals("Pravno lice"))
			{
				PravniKomitent pk = (PravniKomitent) k;
				ps.setNull(5, java.sql.Types.VARCHAR);
				ps.setNull(6, java.sql.Types.VARCHAR);
				ps.setNull(7, java.sql.Types.VARCHAR);
				ps.setNull(8, java.sql.Types.VARCHAR);
				ps.setString(9, pk.getNazivFirme());
				ps.setString(10, pk.getPDVbroj());
			}
			else
			{
				FizickiKomitent fk = (FizickiKomitent) k;
				ps.setNull(9, java.sql.Types.VARCHAR);
				ps.setNull(10, java.sql.Types.VARCHAR);
				ps.setString(5, fk.getIme());
				ps.setString(6, fk.getPrezime());
				ps.setString(7, fk.getJMB());
				ps.setString(8, fk.getBrojLicneKarte());
			}
			ps.execute();
			dbUtils.closeCurrentConnection();
		}
		catch(Exception e)
		{
			dbUtils.printExceptionMessage(e.getMessage(), "komitent update");
			dbUtils.logException(Level.SEVERE, e.getMessage(), e);
			dbUtils.closeCurrentConnection();
		}
	}
	
	public void delete(int id)
	{
		String query = "DELETE FROM komitenti WHERE id=?";
		PreparedStatement ps = dbUtils.getPreparedStatement(query);
		if(ps == null) return;
		try{
			ps.setInt(1, id);
			ps.execute();
			dbUtils.closeCurrentConnection();
		}
		catch(Exception e)
		{
			dbUtils.printExceptionMessage(e.getMessage(), "komitent delete");
			dbUtils.logException(Level.SEVERE, e.getMessage(), e);
			dbUtils.closeCurrentConnection();
		}
	}
	
	public Komitent getKomitentById(int id)
	{
		String query = "SELECT tip_komitenta, adresa, broj_telefona, email, ime, prezime, jmb, broj_licne_karte, nazivFirme, pdvBroj FROM komitenti WHERE id=?";
		PreparedStatement ps = dbUtils.getPreparedStatement(query);
		if(ps == null) return null;
		try {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
					String tipKomitenta = rs.getString(1);
					String adresa = rs.getString(2);
					String brojTelefona = rs.getString(3);
					String eMail = rs.getString(4);
					Komitent toRet = null;
					
					if(tipKomitenta.equals("Pravno lice"))
					{
						String nazivFirme = rs.getString(9);
						String pdvBroj = rs.getString(10);
						toRet = new PravniKomitent(id, tipKomitenta, adresa, brojTelefona, eMail, nazivFirme, pdvBroj);
					}
					else
					{
						String ime = rs.getString(5);
						String prezime = rs.getString(6);
						String jmb = rs.getString(7);
						String brojLicneKarte = rs.getString(8);
						toRet = new FizickiKomitent(ime, prezime, jmb, brojLicneKarte, id, tipKomitenta, adresa, brojTelefona, eMail);
					}
			}
					dbUtils.closeCurrentConnection();
					return null;
		} catch (SQLException e) {
			dbUtils.printExceptionMessage(e.getMessage(), "getKomitentById()");
			dbUtils.logException(Level.SEVERE, e.getMessage(), e);
			dbUtils.closeCurrentConnection();
			return null;
		}
	}
	
	public Komitent getKomitentByJMB(String jmb)
	{
		String query = "SELECT tip_komitenta, adresa, broj_telefona, email, ime, prezime, jmb, broj_licne_karte, nazivFirme, pdvBroj, id FROM komitenti WHERE jmb=?";
		PreparedStatement ps = dbUtils.getPreparedStatement(query);
		if(ps == null) return null;
		try {
			ps.setString(1, jmb);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
					String tipKomitenta = rs.getString(1);
					String adresa = rs.getString(2);
					String brojTelefona = rs.getString(3);
					String eMail = rs.getString(4);
					int id = rs.getInt(11);
					
					if(tipKomitenta.equals("Pravno lice"))
					{
						String nazivFirme = rs.getString(9);
						String pdvBroj = rs.getString(10);
						dbUtils.closeCurrentConnection();
						return new PravniKomitent(id, tipKomitenta, adresa, brojTelefona, eMail, nazivFirme, pdvBroj);
					}
					else
					{
						String ime = rs.getString(5);
						String prezime = rs.getString(6);
						String jmbg = rs.getString(7);
						String brojLicneKarte = rs.getString(8);
						dbUtils.closeCurrentConnection();
						return new FizickiKomitent(ime, prezime, jmbg, brojLicneKarte, id, tipKomitenta, adresa, brojTelefona, eMail);
					}
			}
			return null;
		} catch (SQLException e) {
			dbUtils.printExceptionMessage(e.getMessage(), "getKomitentByJMB()");
			dbUtils.logException(Level.SEVERE, e.getMessage(), e);
			dbUtils.closeCurrentConnection();
			return null;
		}
	}
	
	public Komitent getKomitentByLicnaKarta(String licnaKarta)
	{
		String query = "SELECT tip_komitenta, adresa, broj_telefona, email, ime, prezime, jmb, broj_licne_karte, nazivFirme, pdvBroj, id FROM komitenti WHERE broj_licne_karte=?";
		PreparedStatement ps = dbUtils.getPreparedStatement(query);
		if(ps == null) return null;
		try {
			ps.setString(1, licnaKarta);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
					String tipKomitenta = rs.getString(1);
					String adresa = rs.getString(2);
					String brojTelefona = rs.getString(3);
					String eMail = rs.getString(4);
					int id = rs.getInt(11);
					
					if(tipKomitenta.equals("Pravno lice"))
					{
						String nazivFirme = rs.getString(9);
						String pdvBroj = rs.getString(10);
						return new PravniKomitent(id, tipKomitenta, adresa, brojTelefona, eMail, nazivFirme, pdvBroj);
					}
					else
					{
						String ime = rs.getString(5);
						String prezime = rs.getString(6);
						String jmbg = rs.getString(7);
						String brojLicneKarte = rs.getString(8);
						return new FizickiKomitent(ime, prezime, jmbg, brojLicneKarte, id, tipKomitenta, adresa, brojTelefona, eMail);
					}
			}
			return null;

		} catch (SQLException e) {
			dbUtils.printExceptionMessage(e.getMessage(), "getKomitentByJMB()");
			dbUtils.logException(Level.SEVERE, e.getMessage(), e);
			dbUtils.closeCurrentConnection();
			return null;
		}
	}
	
	public Komitent getKomitentByPDVBroj(String pdvBrojFirme)
	{
		String query = "SELECT tip_komitenta, adresa, broj_telefona, email, ime, prezime, jmb, broj_licne_karte, nazivFirme, pdvBroj, id FROM komitenti WHERE pdvBroj=?";
		PreparedStatement ps = dbUtils.getPreparedStatement(query);
		if(ps == null) return null;
		try {
			ps.setString(1, pdvBrojFirme);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
					String tipKomitenta = rs.getString(1);
					String adresa = rs.getString(2);
					String brojTelefona = rs.getString(3);
					String eMail = rs.getString(4);
					int id = rs.getInt(11);
					
					if(tipKomitenta.equals("Pravno lice"))
					{
						String nazivFirme = rs.getString(9);
						String pdvBroj = rs.getString(10);
						return new PravniKomitent(id, tipKomitenta, adresa, brojTelefona, eMail, nazivFirme, pdvBroj);
					}
					else
					{
						String ime = rs.getString(5);
						String prezime = rs.getString(6);
						String jmbg = rs.getString(7);
						String brojLicneKarte = rs.getString(8);
						return new FizickiKomitent(ime, prezime, jmbg, brojLicneKarte, id, tipKomitenta, adresa, brojTelefona, eMail);
					}
			}
			return null;

		} catch (SQLException e) {
			dbUtils.printExceptionMessage(e.getMessage(), "getKomitentByJMB()");
			dbUtils.logException(Level.SEVERE, e.getMessage(), e);
			dbUtils.closeCurrentConnection();
			return null;
		}
	}
	
	public LinkedList<Komitent> getAll()
	{
		LinkedList<Komitent> toRet = new LinkedList<Komitent>();
		String query = "SELECT tip_komitenta, adresa, broj_telefona, email, ime, prezime, jmb, broj_licne_karte, nazivFirme, pdvBroj, id FROM komitenti";
		PreparedStatement ps = dbUtils.getPreparedStatement(query);
		if(ps == null) return null;
		try {
			ResultSet rs = ps.executeQuery();
				while(rs.next()){
					String tipKomitenta = rs.getString(1);
					String adresa = rs.getString(2);
					String brojTelefona = rs.getString(3);
					String eMail = rs.getString(4);
					int id = rs.getInt(11);
					
					if(tipKomitenta.equals("Pravno lice"))
					{
						String nazivFirme = rs.getString(9);
						String pdvBroj = rs.getString(10);
						toRet.add(new PravniKomitent(id, tipKomitenta, adresa, brojTelefona, eMail, nazivFirme, pdvBroj));
					}
					else
					{
						String ime = rs.getString(5);
						String prezime = rs.getString(6);
						String jmb = rs.getString(7);
						String brojLicneKarte = rs.getString(8);
						toRet.add(new FizickiKomitent(ime, prezime, jmb, brojLicneKarte, id, tipKomitenta, adresa, brojTelefona, eMail));
						
					}
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
	
	public int getIdByjmb(String jmb)
	{
		String query = "SELECT id FROM komitenti WHERE jmb=?";
		PreparedStatement ps = dbUtils.getPreparedStatement(query);
		if(ps == null) return -1;
		try {
			ps.setString(1, jmb);
			ResultSet rs = ps.executeQuery();
				while(rs.next()){
					int id = rs.getInt(1);
					return id;
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
	
	public int getIdByNazivFirme(String naziv)
	{
		String query = "SELECT id FROM komitenti WHERE nazivFime=?";
		PreparedStatement ps = dbUtils.getPreparedStatement(query);
		if(ps == null) return -1;
		try {
			ps.setString(1, naziv);
			ResultSet rs = ps.executeQuery();
				while(rs.next()){
					int id = rs.getInt(1);
					return id;
				}
			dbUtils.closeCurrentConnection();
			return -2;
		} catch (SQLException e) {
			dbUtils.printExceptionMessage(e.getMessage(), "getAll()");
			dbUtils.logException(Level.SEVERE, e.getMessage(), e);
			dbUtils.closeCurrentConnection();
			return -3;
		}
	}
	
}
