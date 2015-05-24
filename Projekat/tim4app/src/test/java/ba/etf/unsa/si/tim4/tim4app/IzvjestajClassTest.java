package ba.etf.unsa.si.tim4.tim4app;

import junit.framework.TestCase;
import ba.etf.unsa.si.tim4.tim4app.daldao.IzvjestajiDataSource;
import ba.etf.unsa.si.tim4.tim4app.classes.Izvjestaj;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class IzvjestajClassTest extends TestCase {

		Izvjestaj iz;
		Date datum;
		IzvjestajiDataSource ids;
		protected void SetUp()
		{
			 ids = new IzvjestajiDataSource();
			SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
			datum = null;
			try 
			{
				datum = dateformat.parse("24/05/2015");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			iz = new Izvjestaj("45555", datum, "parametar","Izvjestaj trenutnog stanja za komitenta");
		}
		
		public void testIzvjestaj_Konstruktor()
		{
			
			Izvjestaj iz = new Izvjestaj("45555", datum, "parametar","Izvjestaj trenutnog stanja za komitenta");
			
			assertEquals("45555",iz.getBrojIzvjestaja());
			assertEquals(datum,iz.getDatumIzvjestaja());
			assertEquals("parametar",iz.getParametarIzvjestaja());
			
			iz.setId(222);
			assertEquals(222,iz.getId());
			
		}
		
		public void testIzvjestajiDalDao_Insert()
		{
			int br =  ids.getMaxId();
			iz.setId(br + 1);
			ids.insert(iz);
			
			int br2= ids.getMaxId();
			
			assertEquals(br,br2-1);
			ids.delete(br2);
			
		}
		
		public void testIzvjestajiDalDao_GetMaxId()
		{
			int br =  ids.getMaxId();
			iz.setId(br + 1);
			ids.insert(iz);
			
			int br2= ids.getMaxId();
			
			assertEquals(br,br2-1);
			ids.delete(br2);
		}

}
