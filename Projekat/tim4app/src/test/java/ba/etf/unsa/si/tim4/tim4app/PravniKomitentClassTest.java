package ba.etf.unsa.si.tim4.tim4app;

import junit.framework.TestCase;
import ba.etf.unsa.si.tim4.tim4app.classes.PravniKomitent;


public class PravniKomitentClassTest extends TestCase {

	public void testPravniKomitent_Konstruktor()
	{
		PravniKomitent k = new PravniKomitent(333,"Pravni komitent", "Grbavica","061666666","email@gmail.com",
				"Firma doo","123123123");
		
		assertEquals(333, k.getId());
		assertEquals("Pravni komitent",k.getTipKomitenta());
		assertEquals("Grbavica",k.getAdresa());
		assertEquals("061666666",k.getBrojTelefona());
		assertEquals("email@gmail.com",k.getEmail());
		assertEquals("Firma doo",k.getNazivFirme());
		assertEquals("123123123",k.getPDVbroj());
			
	}
	
	public void testPravniKomitent_Set()
	{
		PravniKomitent k = new PravniKomitent(333,"Pravni komitent", "Grbavica","061666666","email@gmail.com",
				"Firma doo","123123123");
		
		k.setAdresa("Stup");
		assertEquals("Stup",k.getAdresa());
		
		k.setBrojTelefona("061456456");
		assertEquals("061456456",k.getBrojTelefona());
		
		k.setNazivFirme("Nova doo");
		assertEquals("Nova doo",k.getNazivFirme());
	}
	

}
