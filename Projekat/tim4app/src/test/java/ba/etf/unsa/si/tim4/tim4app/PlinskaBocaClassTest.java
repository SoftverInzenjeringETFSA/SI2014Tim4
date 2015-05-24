package ba.etf.unsa.si.tim4.tim4app;

import junit.framework.TestCase;
import ba.etf.unsa.si.tim4.tim4app.classes.PlinskaBoca;



public class PlinskaBocaClassTest extends TestCase {

	public void testPlinskaBoca_Konstruktor()
	{
		PlinskaBoca pb=new PlinskaBoca(5,20,10);
		assertEquals(5, pb.getKapacitet());
		
		pb.setKapacitet(10);
		assertEquals(10,pb.getKapacitet());
		
		pb.setCijena(30);
		assertEquals(30.,pb.getCijena());
		
		pb.setKolicina(50);
		assertEquals(50,pb.getKolicina());
		
		pb.setId(4);
		assertEquals(4, pb.getId());
	}

}
