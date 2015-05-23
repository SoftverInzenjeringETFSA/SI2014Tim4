package ba.etf.unsa.si.tim4.tim4app;

import junit.framework.TestCase;
import ba.etf.unsa.si.tim4.tim4app.daldao.KomitentDataSource;
import ba.etf.unsa.si.tim4.tim4app.classes.FizickiKomitent;
import ba.etf.unsa.si.tim4.tim4app.classes.Komitent;

import java.util.LinkedList;


public class FizickiKomitentTestClass extends TestCase {


	public void testDodavanjeKomitenta()
	{
		FizickiKomitent k = new FizickiKomitent("ime","prezime","1310993124129","12iii345",3,"fizickolice","grbavica","062111111","email@gmail.com");
		assertEquals(k.getIme(),"ime");
		assertEquals(k.getPrezime(),"prezime");
		assertEquals(k.getJMB(),"1310993124129");
		assertEquals(k.getBrojLicneKarte(),"12iii345");
		assertEquals(k.getId(),3);
		assertEquals(k.getTipKomitenta(),"fizickolice");
		assertEquals(k.getAdresa(),"grbavica");
		assertEquals(k.getBrojTelefona(),"062111111");
		assertEquals(k.getEmail(),"email@gmail.com");
	}
	
	public void testFizickiKomitent_KomitentByJMB()
	{
		FizickiKomitent k = new FizickiKomitent("ime","prezime","1310993124129","12iii345",3,"Fizicko lice","grbavica","062111111","email@gmail.com");
		KomitentDataSource kds= new KomitentDataSource();
		kds.insert(k);
		
		assertEquals(k,kds.getKomitentByJMB("1310993124129"));
		kds.delete(666);
	}
	
	public void testFizickiKomitent_KomitentById()
	{
		FizickiKomitent k = new FizickiKomitent("ime","prezime","1310993124129","12iii345",666,"Fizicko lice","grbavica","062111111","email@gmail.com");
		KomitentDataSource kds= new KomitentDataSource();
		kds.insert(k);
		
		assertEquals(k,kds.getKomitentById(666));
		kds.delete(666);
	}
	
	public void testFizickiKomitent_KomitentByLicnaKarta()
	{
		FizickiKomitent k = new FizickiKomitent("ime","prezime","1310993124129","12iii345",666,"Fizicko lice","grbavica","062111111","email@gmail.com");
		KomitentDataSource kds= new KomitentDataSource();
		kds.insert(k);
		
		assertEquals(k,kds.getKomitentByLicnaKarta("12iii345"));
		kds.delete(666);
	}
	
	public void testFizickiKomitent_GetAll()
	{
		FizickiKomitent k = new FizickiKomitent("ime","prezime","1310993124129","12iii345",666,"Fizicko lice","grbavica","062111111","email@gmail.com");
		KomitentDataSource kds= new KomitentDataSource();
		LinkedList<Komitent> komitenti = kds.getAll();
		assertNotNull(komitenti.size());
		kds.insert(k);
		LinkedList<Komitent> komitenti2 = kds.getAll();
		assertEquals(komitenti.size(),komitenti2.size() -1 );
	}
}
