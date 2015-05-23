package ba.etf.unsa.si.tim4.tim4app;

import junit.framework.TestCase;
import ba.etf.unsa.si.tim4.tim4app.daldao.KorisnikDataSource;
import ba.etf.unsa.si.tim4.tim4app.classes.Korisnik;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;


public class KorisnikClassTest extends TestCase {
	
	Korisnik k1,k2,k3,a1;
	Date d;
	KorisnikDataSource kds;
	
	protected void setUp()
	{
		SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
		d = null;
		try 
		{
			d = dateformat.parse("12/12/2012");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		kds = new KorisnikDataSource();
		k1 = new Korisnik("Korisnik","username1","lozinka","Elma","Gazetic","12xxx6789","Stup","061999999",d);
		k2 = new Korisnik("Korisnik","username2","lozinka1","Elma","Gazetic","12xxx6789","Stup","061999999",d);
		k3 = new Korisnik("Korisnik","username3","lozinka2","Elma","Gazetic","12xxx6788","Stup","061999999",d);
		a1 = new Korisnik("admin","admin","lozinka","Elma","Gazetic","12xxx6788","Stup","061999999",d);
	}
	
	public void testDodavanjeKorisnika()
	{
		assertEquals(k1.getIme(),"Elma");
		assertEquals(k1.getPrezime(),"Gazetic");
		assertEquals(k1.getUsername(),"username1");
		assertEquals(k1.getPassword(),"lozinka");
		assertEquals(k1.getDatumZaposlenja(),d);
		assertEquals(k1.getBrojLicneKarte(),"12xxx6789");
	}
	
	public void testKorisnikDalDao_UsernameUnique()
	{
		
		kds.insert(k2);
		
		assertFalse(kds.isUsernameUnique("username2"));
		
		String id = kds.getIdByUsername(k2.getUsername(), k2.getPassword());
		kds.delete(Integer.parseInt(id));
		
	}
	
	public void testKorisnikDalDao_Count()
	{
		int prosliBroj = kds.getCount("username1", "lozinka");
		
		kds.insert(k1);
		
		if(kds.isUsernameUnique("username1"))
		kds.insert(k1);
		
		int trenutniBroj = kds.getCount("username1", "lozinka");
		
		int brojKorisnika = trenutniBroj - prosliBroj;
		
		assertEquals(brojKorisnika,1);
		
		String id = kds.getIdByUsername(k1.getUsername(), k1.getPassword());
		kds.delete(Integer.parseInt(id));
	}
	
	
	public void testKorisnikDalDao_GetAll()
	{
		LinkedList<Korisnik> proslaListaKorisnika = kds.getAll();
		
		kds.insert(k1);
		kds.insert(k2);
		kds.insert(k3);

		LinkedList<Korisnik> listaKorisnika = kds.getAll();
		
		assertEquals(listaKorisnika.size(),proslaListaKorisnika.size() + 3);
		
		String id = kds.getIdByUsername(k1.getUsername(), k1.getPassword());
		kds.delete(Integer.parseInt(id));
		
		id = kds.getIdByUsername(k2.getUsername(), k2.getPassword());
		kds.delete(Integer.parseInt(id));
		
		id = kds.getIdByUsername(k3.getUsername(), k3.getPassword());
		kds.delete(Integer.parseInt(id));
	}
	
	public void testKorisnikDalDao_GetTip()
	{
		kds.insert(a1);

		String Tip = kds.getTip(a1.getUsername(), a1.getPassword());
		assertEquals("admin",Tip);
		
		String id = kds.getIdByUsername(a1.getUsername(), a1.getPassword());
		kds.delete(Integer.parseInt(id));
	}
	
}
