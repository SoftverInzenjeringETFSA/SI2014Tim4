package ba.etf.unsa.si.tim4.tim4app;

import junit.framework.TestCase;
import ba.etf.unsa.si.tim4.tim4app.daldao.IzvjestajiStanjaDataSource;
import ba.etf.unsa.si.tim4.tim4app.classes.IzvjestajStanja;
import ba.etf.unsa.si.tim4.tim4app.classes.PlinskaBoca;
import ba.etf.unsa.si.tim4.tim4app.classes.PlinskiRezervoar;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;


public class IzvjestajStanjaClassTest extends TestCase {
	
	
		
	
	public void testIzvjestajStanja_Konstruktor()
	{
		PlinskiRezervoar pr;
		PlinskaBoca pb;
		LinkedList<PlinskaBoca> pbl = new LinkedList<PlinskaBoca>();
		LinkedList<PlinskiRezervoar> prl = new LinkedList<PlinskiRezervoar>();
		Date d;
		IzvjestajStanja is;
		IzvjestajiStanjaDataSource isds;
			isds = new 	IzvjestajiStanjaDataSource();
			SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
			d = null;
			try 
			{
				d = dateformat.parse("12/12/2012");
			} catch (ParseException e) {
				e.printStackTrace();
			}
		
			
			pr = new PlinskiRezervoar(333,"666555", 350,
					400, 1, "nadzemni",d, "Grbavica 45","iznajmljen");
			pb =new PlinskaBoca(5,20,10);
			prl.add(pr);
			pbl.add(pb);
			is = new IzvjestajStanja("6655",d,"tip");
			is.setId(345);
			is.setPlinskaBoce(pbl);
			is.setPlinskiRezervoari(prl);
		
		IzvjestajStanja is1 = new IzvjestajStanja("6655",d,"tip");
		
		assertEquals("6655",is1.getBrojIzvjestaja());
		assertEquals(d,is1.getDatumIzvjestaja());
		assertEquals("tip",is1.getTipIzvjestaja());
		
		is1.setId(123);
		assertEquals(123,is1.getId());
		is1.setPlinskaBoce(pbl);
		assertEquals(pbl,is.getPlinskaBoce());
		is1.setPlinskiRezervoari(prl);
		assertEquals(prl,is.getPlinskiRezervoari());
	}
	
	

}
