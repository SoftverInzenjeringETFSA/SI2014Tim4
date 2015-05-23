package ba.etf.unsa.si.tim4.tim4app;

import junit.framework.TestCase;
import ba.etf.unsa.si.tim4.tim4app.daldao.KorisnikDataSource;
import ba.etf.unsa.si.tim4.tim4app.classes.Korisnik;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

// Hocemo li praviti ovakve testove?? :) 

public class KorisnikTestClass extends TestCase {
	
	
	public void testDodavanjeKorisnika()
	{
		SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
		Date d = null;
		try 
		{
			d = dateformat.parse("12/12/2012");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Korisnik k = new Korisnik("Korisnik","username","lozinka","Elma","Gazetic","12345","Stup","061999999",d);
		assertEquals(k.getIme(),"Elma");
		assertEquals(k.getPrezime(),"Gazetic");
		assertEquals(k.getUsername(),"username");
		assertEquals(k.getPassword(),"lozinka");
		assertEquals(k.getDatumZaposlenja(),d);
		assertEquals(k.getBrojLicneKarte(),"12345");
	}
	
	public void testKorisnikDalDao_UsernameUnique()
	{
		SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
		Date d = null;
		try 
		{
			d = dateformat.parse("12/12/2012");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Korisnik k = new Korisnik("Korisnik","username","lozinka","Elma","Gazetic","12345","Stup","061999999",d);
		assertEquals(k.getIme(),"Elma");
		assertEquals(k.getPrezime(),"Gazetic");
		assertEquals(k.getUsername(),"username");
		assertEquals(k.getPassword(),"lozinka");
		assertEquals(k.getDatumZaposlenja(),d);
		assertEquals(k.getBrojLicneKarte(),"12345");
		
		KorisnikDataSource kds = new KorisnikDataSource();
		
		kds.insert(k);
		kds.insert(k);
		
		assertFalse(kds.isUsernameUnique("username"));
	}
	
	public void testKorisnikDalDao_Count()
	{
		SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
		Date d = null;
		try 
		{
			d = dateformat.parse("12/12/2012");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Korisnik k = new Korisnik("Korisnik","username","lozinka","Elma","Gazetic","12345","Stup","061999999",d);
		assertEquals(k.getIme(),"Elma");
		assertEquals(k.getPrezime(),"Gazetic");
		assertEquals(k.getUsername(),"username");
		assertEquals(k.getPassword(),"lozinka");
		assertEquals(k.getDatumZaposlenja(),d);
		assertEquals(k.getBrojLicneKarte(),"12345");
		
		KorisnikDataSource kds = new KorisnikDataSource();
		int prosliBroj = kds.getCount("username", "lozinka");
		
		kds.insert(k);
		kds.insert(k);
		
		int trenutniBroj = kds.getCount("username", "lozinka");
		
		int brojKorisnika = trenutniBroj - prosliBroj;
		
		assertEquals(brojKorisnika,2);
	}
	
	


}
