package ba.etf.unsa.si.tim4.tim4app.daldao;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

/* Testna klasa za provjeru da li konekcija na bazu radi */
public  class TestDBClass {
	
	String connectionString = "jdbc:mysql://localhost/tim4";
	
	public TestDBClass(){
		
	}
	
public void printToConsoleFromDBRegular(){
	try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection c = (Connection) DriverManager.getConnection(connectionString, "EtfSI2014", "2014SIEtf");
		try {
			PreparedStatement ps = (PreparedStatement)c.prepareStatement("SELECT ime, prezime FROM testna_tabela WHERE ime=?");
			// Unos broja racuna
			System.out.println("Unesite broj racuna:");
			Scanner sc = new Scanner(System.in);
			String imeToFind = sc.next();
			ps.setString(1, imeToFind);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
					String ime = rs.getString(1);
					String prezime = rs.getString(2);
					System.out.println (ime +" " + prezime);
			}
			sc.close();
		} catch (Exception e) {
		System.out.println("Greska pri radu sa bazom: "+e.getMessage());
		} finally {
		c.close();
		}
		} catch (Exception e) {
		System.out.println("Greska pri radu sa bazom: " + e.getMessage());
		}
	}

public void printToConsoleFromDBHibernate(){
	Session session = HibernateUtil.getSessionFactory().openSession();
	dodajOsobu(session);
	nadjiOsobu(session);
	session.close();

}

private void nadjiOsobu(Session session) {
	// TODO Auto-generated method stub
	Transaction t = session.beginTransaction();
	System.out.println("Unesite id osobe: ");
	Scanner sc = new Scanner(System.in);
	long id = 2;
	OsobaTest ot = (OsobaTest)session.get(OsobaTest.class, id);
	if( ot == null )
	{
		System.out.println("Nema te osobe!");
		
	}
	else 
	{
		System.out.println("Osoba je: " + ot.getIme() + " " + ot.getPrezime());
	}
	sc.close();
}


private void dodajOsobu(Session session) {
	Transaction t = session.beginTransaction();
	OsobaTest ot = new OsobaTest();
	ot.setIme("Eldar");
	ot.setPrezime("Granulo");
	Long id = (Long)session.save(ot);
	System.out.println("Dodana osoba sa id " + id);
	t.commit();
}

}

