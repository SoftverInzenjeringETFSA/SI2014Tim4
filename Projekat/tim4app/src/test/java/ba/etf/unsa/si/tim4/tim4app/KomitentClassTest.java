package ba.etf.unsa.si.tim4.tim4app;

import java.util.LinkedList;

import junit.framework.TestCase;
import ba.etf.unsa.si.tim4.tim4app.daldao.KomitentDataSource;
import ba.etf.unsa.si.tim4.tim4app.classes.FizickiKomitent;
import ba.etf.unsa.si.tim4.tim4app.classes.Komitent;

public class KomitentClassTest extends TestCase {
	
	FizickiKomitent k1,k2,k3;
	KomitentDataSource kds;
	protected void setUp()
	{
		kds = new KomitentDataSource();
		k1 = new FizickiKomitent("Elma","Gazetic","0308964384007","12xxx6789","Fizicko lice","Sarajevo","062444444","egazetic1@etf.unsa.ba");
		k2 = new FizickiKomitent("Demir","Gazetic","1304005111059","12xxx6789","Fizicko lice","Sarajevo","062444446","dgazetic1@etf.unsa.ba");
		k3 = new FizickiKomitent("Semir","Gazetic","0508964384007","12xxx6789","Fizicko lice","Sarajevo","062444443","sgazetic1@etf.unsa.ba");
		
	}
	
	public void testKomitentDalDao_GetAll()
	{
		/*int prosliBrojKomitenata = 
		kds.insert(k1);
		kds.insert(k2);
		LinkedList<Komitent> listaKomitenata = kds.getAll();
		assertEquals(listaKomitenata.size(),2);*/
	}

}
