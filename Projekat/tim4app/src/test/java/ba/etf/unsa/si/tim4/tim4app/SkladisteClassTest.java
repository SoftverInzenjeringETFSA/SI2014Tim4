package ba.etf.unsa.si.tim4.tim4app;

import junit.framework.TestCase;
import ba.etf.unsa.si.tim4.tim4app.daldao.SkladisteDataSource;
import ba.etf.unsa.si.tim4.tim4app.classes.PlinskiRezervoar;
import ba.etf.unsa.si.tim4.tim4app.classes.PlinskaBoca;
import ba.etf.unsa.si.tim4.tim4app.classes.Skladiste;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;


public class SkladisteClassTest extends TestCase {
	
	Date d;
	PlinskaBoca pb;
	PlinskiRezervoar pr;
	LinkedList<PlinskiRezervoar> prl; 
	Skladiste s;
	SkladisteDataSource sds;
	public void SetUp()
	{
		sds = new SkladisteDataSource();
		s = new Skladiste();
		prl = new LinkedList<PlinskiRezervoar>();
		
		SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
		d = null;
		try 
		{
			d = dateformat.parse("12/12/2012");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		pb =new PlinskaBoca(5,20,10);
		pr = new PlinskiRezervoar(333,"666555", 350,
				400, 1, "nadzemni",d, "Grbavica 45","iznajmljen");
		prl.add(pr);
		
		s.addDesetLitarske(10);
		s.addPetLitarske(10);
		s.addPetnaestLitarske(10);
		s.setPlinskiRezervoari(prl);
	}
	
	public void testSkladiste_Konstruktor()
	{
		Skladiste s1 = new Skladiste();
		s1.addDesetLitarske(10);
		s1.addPetLitarske(10);
		s1.addPetnaestLitarske(10);
		
		assertEquals(10,s1.getDesetLitarske());
		assertEquals(10, s1.getPetLitarske());
		assertEquals(10, s1.getPetnaestLitarske());
		
		s1.clearSkladiste();
		
		assertEquals(0,s1.getDesetLitarske());
		assertEquals(0, s1.getPetLitarske());
		assertEquals(0, s1.getPetnaestLitarske());
		
	}
	
	public void testSkladisteDalDao_Insert()
	{
		pb.setId(89);
		sds.insert(pb);
		
		LinkedList<PlinskaBoca> lpb = sds.getAll();
		
		assertTrue(lpb.contains(pb));
		sds.delete(89);
		
	}
	
	public void testSkladisteDalDao_GetBocaById()
	{
		pb.setId(89);
		sds.insert(pb);
		
		assertEquals(pb,sds.getById(89));
		sds.delete(89);

	}
	
	public void testSkladisteDalDao_QuantityByCapacity()
	{
		pb.setId(89);
		pb.setKolicina(15);
		sds.insert(pb);
		
		assertEquals(15,sds.getQuantityByCapacity(5));
		sds.delete(89);

	}
	
	public void testSkladisteDalDao_Update()
	{
		pb.setId(89);
		pb.setKolicina(15);
		sds.insert(pb);
		
		sds.update(5, 15);
		assertEquals(15, sds.getQuantityByCapacity(5));
		sds.delete(89);

	}
	
	public void testSkladisteDalDao_GetCount()
	{
		int br = sds.getCount();
		pb.setId(89);
		pb.setKolicina(10);
		sds.insert(pb);
		
		assertEquals(br ,sds.getCount() -10);
		sds.delete(89);
	}
	
	
	

}
