package ba.etf.unsa.si.tim4.tim4app;

import junit.framework.TestCase;
import ba.etf.unsa.si.tim4.tim4app.daldao.PlinskiRezervoarDataSource;
import ba.etf.unsa.si.tim4.tim4app.classes.PlinskiRezervoar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

public class PlinskiRezervoarClassTest extends TestCase {

	PlinskiRezervoar pr;
	PlinskiRezervoar pr2;
	PlinskiRezervoarDataSource prds;
	
	protected void SetUp()
	{
		 prds = new PlinskiRezervoarDataSource();
	
		SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
		Date datum = null;
		try 
		{
		datum = dateformat.parse("13/10/2014");
		} catch (ParseException e) {
		e.printStackTrace();
		}
	
		 pr = new PlinskiRezervoar(333,"666555", 350,
			400, 1, "nadzemni",datum, "Grbavica 45","iznajmljen");
		 
		 pr2 = new PlinskiRezervoar(222,"555666", 350,
				400, 1, "nadzemni",datum, "Grbavica 45","iznajmljen");
	}
	
	public void testPlinskiRezervoar_Konstrukor()
	{
		SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
		Date datum = null;
		try 
		{
		datum = dateformat.parse("13/10/2014");
		} catch (ParseException e) {
		e.printStackTrace();
		}
	
		PlinskiRezervoar pr3 = new PlinskiRezervoar(333,"666555", 350,
			400, 1, "nadzemni",datum, "Grbavica 45","iznajmljen");
		assertEquals(333, pr3.getId());
		assertEquals("666555",pr3.getSerijskiBroj());
		assertEquals(350,pr3.getKapacitet());
		assertEquals(400,pr3.getTezina());
		assertEquals("nadzemni",pr3.getTipRezervoara());
		assertEquals("iznajmljen",pr3.getTrenutniStatus());
	}
	
	public void testPlinskiRezervoarDalDao_Insert()
	{
           
          prds.insert(pr);
          
          assertEquals(pr,prds.getRezervoarById(666555));
          prds.delete(333);
	}
	
	public void testPlinskiRezervoarDalDao_Update()
	{
		prds.insert(pr);
		pr.setLokacija("Stup");
		prds.update(pr);
		
		assertEquals("Stup", prds.getRezervoarById(333).getLokacija());
		prds.delete(333);
	}
	
	public void testPlinskiRezervoarDalDao_UpdateStatus()
	{
		prds.insert(pr);
		prds.updateStatus(333, "Stup", "prodan");
		
		assertEquals("Stup", prds.getRezervoarById(333).getLokacija());
		assertEquals("prodan",prds.getRezervoarById(333).getTrenutniStatus());
		prds.delete(333);
	}
	
	public void testPlinskiRezervoar_GetAll()
	{
		LinkedList<PlinskiRezervoar> lpr = prds.getAll();
		prds.insert(pr);
		prds.insert(pr2);
		
		LinkedList<PlinskiRezervoar> lpr2 = prds.getAll();
		
		assertEquals(lpr.size(),lpr2.size());
		
		prds.delete(333);
		prds.delete(222);
	}
	
	public void testPlinskiRezervoar_GetAllNoStatus()
	{
		LinkedList<PlinskiRezervoar> lpr = prds.getAll();
		prds.insert(pr);
		prds.insert(pr2);
		
		LinkedList<PlinskiRezervoar> lpr2 = prds.getAll();
		
		assertEquals(lpr.size(),lpr2.size() -2);
		
		prds.delete(333);
		prds.delete(222);
	}
	
	public void testPlinskiRezervoar_GetCount()
	{
		int br = prds.getCount();
		prds.insert(pr);
		prds.insert(pr2);
		
		int br2 = prds.getCount();
		
		assertEquals(br,br2 -2);
		
		prds.delete(333);
		prds.delete(222);
		
	}
}
