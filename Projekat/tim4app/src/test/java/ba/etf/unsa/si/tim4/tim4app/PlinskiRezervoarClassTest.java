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
	
	public void testPlinskiRezervoar_GetAll()
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
		
			PlinskiRezervoar prvi = new PlinskiRezervoar("666555", 350, 400, 1, "nadzemni",datum, "Visoko","Skladiste");
			 
			 PlinskiRezervoar drugi = new PlinskiRezervoar("555666", 350, 400, 1, "nadzemni",datum, "Visoko","Skladiste");
			 
		LinkedList<PlinskiRezervoar> lpr = prds.getAll();
	
		prds.insert(prvi);
		prds.insert(drugi);
		
		LinkedList<PlinskiRezervoar> lpr2 = prds.getAll();
		
		assertEquals(lpr.size()+2,lpr2.size());

		int id = prds.getId(prvi.getSerijskiBroj());
		prds.delete(id);
		id = prds.getId(drugi.getSerijskiBroj());
		prds.delete(id);
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
		 prds = new PlinskiRezervoarDataSource();
			
			SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
			Date datum = null;
			try 
			{
			datum = dateformat.parse("13/10/2014");
			} catch (ParseException e) {
			e.printStackTrace();
			}
		
		  pr = new PlinskiRezervoar("666555", 350, 400, 1, "nadzemni",datum, "Grbavica 2","iznajmljen");
			 
          prds.insert(pr);

          int id = prds.getId(pr.getSerijskiBroj());
          PlinskiRezervoar plinski = prds.getRezervoarById(id);
          
          assertEquals(pr.getSerijskiBroj(),plinski.getSerijskiBroj());

          prds.delete(id);
	}
	
	public void testPlinskiRezervoarDalDao_Update()
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
		
			 pr = new PlinskiRezervoar("666555", 350,
				400, 1, "nadzemni",datum, "Grbavica 3","iznajmljen");
			 
			 pr2 = new PlinskiRezervoar("555666", 350,
					400, 1, "nadzemni",datum, "Grbavica 32","iznajmljen");
		prds.insert(pr);
		pr.setLokacija("Stup");
		prds.update(pr);

		int id = prds.getId(pr.getSerijskiBroj());
		assertEquals("Stup", prds.getRezervoarById(id).getLokacija());

		prds.delete(id);
	}
	
	public void testPlinskiRezervoarDalDao_UpdateStatus()
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
		
			 pr = new PlinskiRezervoar("666555", 350,
				400, 1, "nadzemni",datum, "Grbavica 4","iznajmljen");
			 
			 pr2 = new PlinskiRezervoar("555666", 350,
					400, 1, "nadzemni",datum, "Grbavica 23","iznajmljen");
		prds.insert(pr);

		int id = prds.getId(pr.getSerijskiBroj());
		prds.updateStatus(id, "Stup", "prodan");
		

		assertEquals("Stup", prds.getRezervoarById(id).getLokacija());
		assertEquals("prodan",prds.getRezervoarById(id).getTrenutniStatus());

		prds.delete(id);
	}
	

	public void testPlinskiRezervoarDalDao_GetAllNoStatus()
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
		
			 pr = new PlinskiRezervoar("666555", 350,
				400, 1, "nadzemni",datum, "Grbavica 5","iznajmljen");
			 
			 pr2 = new PlinskiRezervoar("555666", 350,
					400, 1, "nadzemni",datum, "Grbavica 43","iznajmljen");
		LinkedList<PlinskiRezervoar> lpr = prds.getAllNoStatus();
		prds.insert(pr);
		prds.insert(pr2);
		
		LinkedList<PlinskiRezervoar> lpr2 = prds.getAllNoStatus();
		
		assertEquals(lpr.size(),lpr2.size() -2);

		int id = prds.getId(pr.getSerijskiBroj());
		prds.delete(id);
		id = prds.getId(pr2.getSerijskiBroj());
		prds.delete(id);
	}
	
	public void testPlinskiRezervoarDalDao_GetCount()
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
		
			 pr = new PlinskiRezervoar("666555", 350,
				400, 1, "nadzemni",datum, "Grbavica 6","iznajmljen");
			 
			 pr2 = new PlinskiRezervoar("555666", 350,
					400, 1, "nadzemni",datum, "Grbavica 56","iznajmljen");
		int br = prds.getCount();
		
		prds.insert(pr);
		prds.insert(pr2);
		
		int br2 = prds.getCount();
		
		assertEquals(br,br2-2);
		
		int id = prds.getId(pr.getSerijskiBroj());
		prds.delete(id);
		id = prds.getId(pr2.getSerijskiBroj());
		prds.delete(id);
	}
	
	public void testplinskiRezervoarDalDao_IsUniqueSerijskiBroj()
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
		
			 pr = new PlinskiRezervoar("333444", 350,
				400, 1, "nadzemni",datum, "Grbavica 7","iznajmljen");

		prds.insert(pr);
		prds.insert(pr);
		
		int br = prds.isUniqueSerijskiBroj(pr.getSerijskiBroj()); 
		assertEquals(1,br);
		
		int id = prds.getId(pr.getSerijskiBroj());
		prds.delete(id);


		
	}
}
