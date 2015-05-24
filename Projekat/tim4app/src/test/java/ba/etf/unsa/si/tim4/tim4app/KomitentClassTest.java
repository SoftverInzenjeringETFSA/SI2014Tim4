package ba.etf.unsa.si.tim4.tim4app;

import java.util.LinkedList;

import junit.framework.TestCase;
import ba.etf.unsa.si.tim4.tim4app.daldao.KomitentDataSource;
import ba.etf.unsa.si.tim4.tim4app.classes.FizickiKomitent;
import ba.etf.unsa.si.tim4.tim4app.classes.Komitent;
import ba.etf.unsa.si.tim4.tim4app.classes.PravniKomitent;

public class KomitentClassTest extends TestCase {
	
	public void testKomitentDalDao_KomitentByJMB()
	{
		FizickiKomitent k;
		KomitentDataSource kds= new KomitentDataSource();
		k = new FizickiKomitent("Elma","Gazetic","0308964384007","12xxx6789","Fizicko lice","Sarajevo","062444444","egazetic1@etf.unsa.ba");
		
		kds.insert(k);
		
		int id = kds.getIdByjmb(k.getJMB());


		FizickiKomitent k1 = (FizickiKomitent) kds.getKomitentByJMB(k.getJMB());
	    assertEquals(k.getJMB(),k1.getJMB());
		kds.delete(id);
	}
	
	public void testKomitentDalDao_KomitentById()
	{
		FizickiKomitent k;
		KomitentDataSource kds= new KomitentDataSource();
		k = new FizickiKomitent("Elma","Gazetic","0308964384007","12xxx6789","Fizicko lice","Sarajevo","062444444","egazetic1@etf.unsa.ba");
		
		kds.insert(k);
		int id = kds.getIdByjmb(k.getJMB());
		assertTrue(id > 0);
		kds.delete(id);
	}
	
	public void testKomitentDalDao_KomitentByLicnaKarta()
	{
		FizickiKomitent k;
		KomitentDataSource kds = new KomitentDataSource();
		k = new FizickiKomitent("Elma","Gazetic","0308964384007","12xxx6789","Fizicko lice","Sarajevo","062444444","egazetic1@etf.unsa.ba");
	
		kds.insert(k);
		FizickiKomitent k1 = (FizickiKomitent) kds.getKomitentByLicnaKarta(k.getBrojLicneKarte());
		
		assertEquals(k1.getBrojLicneKarte(),k.getBrojLicneKarte());
		int id = kds.getIdByjmb(k.getJMB());
		kds.delete(id);
	}
	
	public void testKomitentDalDao_GetAll()
	{
		FizickiKomitent k,k1;
		KomitentDataSource kds = new KomitentDataSource();
		k = new FizickiKomitent("Elma","Gazetic","0308964384007","12xxx6789","Fizicko lice","Sarajevo","062444444","egazetic1@etf.unsa.ba");
		k1 = new FizickiKomitent("Demir","Gazetic","1304005111059","12xxx6789","Fizicko lice","Sarajevo","062444446","dgazetic1@etf.unsa.ba");
		
		LinkedList<Komitent> komitenti = kds.getAll();
		
		kds.insert(k);
		kds.insert(k1);
		
		LinkedList<Komitent> komitenti2 = kds.getAll();
		assertEquals(komitenti.size(),komitenti2.size() -2 );
		
		int id = kds.getIdByjmb(k.getJMB());
		kds.delete(id);
		id = kds.getIdByjmb(k1.getJMB());
		kds.delete(id);

	}
	
	public void testKomitentDalDao_KomitentByPDVBroj()
	{
		PravniKomitent pk;
		KomitentDataSource kds = new KomitentDataSource();
		 pk = new PravniKomitent(333,"Pravno lice", "Grbavica","061666666","email@gmail.com",
				"Firma doo","123123123");
		
		kds.insert(pk);
		
		PravniKomitent pravni = (PravniKomitent) kds.getKomitentByPDVBroj(pk.getPDVbroj());
		assertEquals(pk.getNazivFirme(),pravni.getNazivFirme());
		int id = kds.getIdByNazivFirme(pk.getNazivFirme());
		kds.delete(id);
	}
	
	public void testKomitentDalDao_Insert()
	{
		FizickiKomitent k,k1;
		KomitentDataSource kds = new KomitentDataSource();
		k = new FizickiKomitent("Elma","Gazetic","0308964384007","12xxx6789","Fizicko lice","Sarajevo","062444444","egazetic1@etf.unsa.ba");
		k1 = new FizickiKomitent("Demir","Gazetic","1304005111059","12xxx6789","Fizicko lice","Sarajevo","062444446","dgazetic1@etf.unsa.ba");
		
		LinkedList<Komitent> beforekomitenti = kds.getAll();
		kds.insert(k);
		kds.insert(k1);
		LinkedList<Komitent> komitenti = kds.getAll();
		
		assertEquals(komitenti.size(),beforekomitenti.size() + 2);
		
		int id = kds.getIdByjmb(k.getJMB());
		kds.delete(id);
		id = kds.getIdByjmb(k1.getJMB());
		kds.delete(id);
	}
	
	
}
