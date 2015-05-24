package ba.etf.unsa.si.tim4.tim4app;

import junit.framework.TestCase;
import ba.etf.unsa.si.tim4.tim4app.classes.FizickiKomitent;

public class FizickiKomitentClassTest extends TestCase {


	public void testFizickiKomitent_Konstruktor()
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
	
	public void testFizickiKomitent_Set()
	{
		FizickiKomitent k = new FizickiKomitent("ime","prezime","1310993124129","12iii345",3,"fizickolice",
				"grbavica","062111111","email@gmail.com");
		k.setAdresa("Stup");
		assertEquals("Stup",k.getAdresa());
		
		k.setBrojTelefona("061456456");
		assertEquals("061456456",k.getBrojTelefona());
		
		k.setIme("Eldar");
		assertEquals("Eldar",k.getIme());
		
	}
	
	
	
}

