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
		FizickiKomitent k,k1;
		PravniKomitent pk;
		KomitentDataSource kds;
		 pk = new PravniKomitent(333,"Pravno lice", "Grbavica","061666666","email@gmail.com",
				"Firma doo","123123123");
		kds = new KomitentDataSource();
		k = new FizickiKomitent("Elma","Gazetic","0308964384007","12xxx6789","Fizicko lice","Sarajevo","062444444","egazetic1@etf.unsa.ba");
		k1 = new FizickiKomitent("Demir","Gazetic","1304005111059","12xxx6789","Fizicko lice","Sarajevo","062444446","dgazetic1@etf.unsa.ba");
		k.setId(666);
		k1.setId(77);
		kds.insert(k);
		
		assertEquals(k,kds.getKomitentByJMB("0308964384007"));
		//kds.delete(666);
	}
	
	public void testKomitentDalDao_KomitentById()
	{
		FizickiKomitent k,k1;
		PravniKomitent pk;
		KomitentDataSource kds;
		 pk = new PravniKomitent(333,"Pravno lice", "Grbavica","061666666","email@gmail.com",
				"Firma doo","123123123");
		kds = new KomitentDataSource();
		k = new FizickiKomitent("Elma","Gazetic","0308964384007","12xxx6789","Fizicko lice","Sarajevo","062444444","egazetic1@etf.unsa.ba");
		k1 = new FizickiKomitent("Demir","Gazetic","1304005111059","12xxx6789","Fizicko lice","Sarajevo","062444446","dgazetic1@etf.unsa.ba");
		k.setId(666);
		k1.setId(77);
		kds.insert(k);
		
		assertEquals(k,kds.getKomitentById(666));
		kds.delete(666);
	}
	
	public void testKomitentDalDao_KomitentByLicnaKarta()
	{
		FizickiKomitent k,k1;
		PravniKomitent pk;
		KomitentDataSource kds;
		 pk = new PravniKomitent(333,"Pravno lice", "Grbavica","061666666","email@gmail.com",
				"Firma doo","123123123");
		kds = new KomitentDataSource();
		k = new FizickiKomitent("Elma","Gazetic","0308964384007","12xxx6789","Fizicko lice","Sarajevo","062444444","egazetic1@etf.unsa.ba");
		k1 = new FizickiKomitent("Demir","Gazetic","1304005111059","12xxx6789","Fizicko lice","Sarajevo","062444446","dgazetic1@etf.unsa.ba");
		k.setId(666);
		k1.setId(77);
		kds.insert(k);
		
		assertEquals(k,kds.getKomitentByLicnaKarta("12xxx6789"));
		kds.delete(666);
	}
	
	public void testKomitentDalDao_GetAll()
	{
		FizickiKomitent k,k1;
		PravniKomitent pk;
		KomitentDataSource kds;
		 pk = new PravniKomitent(333,"Pravno lice", "Grbavica","061666666","email@gmail.com",
				"Firma doo","123123123");
		kds = new KomitentDataSource();
		k = new FizickiKomitent("Elma","Gazetic","0308964384007","12xxx6789","Fizicko lice","Sarajevo","062444444","egazetic1@etf.unsa.ba");
		k1 = new FizickiKomitent("Demir","Gazetic","1304005111059","12xxx6789","Fizicko lice","Sarajevo","062444446","dgazetic1@etf.unsa.ba");
		k.setId(666);
		k1.setId(77);
		LinkedList<Komitent> komitenti = kds.getAll();
		assertNotNull(komitenti.size());
		kds.insert(k);
		kds.insert(k1);
		LinkedList<Komitent> komitenti2 = kds.getAll();
		assertEquals(komitenti.size(),komitenti2.size() -2 );
		assertTrue(kds.getAll().contains(k));
		kds.delete(666);
		kds.delete(77);
	}
	
	public void testKomitentDalDao_KomitentByPDVBroj()
	{
		FizickiKomitent k,k1;
		PravniKomitent pk;
		KomitentDataSource kds;
		 pk = new PravniKomitent(333,"Pravno lice", "Grbavica","061666666","email@gmail.com",
				"Firma doo","123123123");
		kds = new KomitentDataSource();
		k = new FizickiKomitent("Elma","Gazetic","0308964384007","12xxx6789","Fizicko lice","Sarajevo","062444444","egazetic1@etf.unsa.ba");
		k1 = new FizickiKomitent("Demir","Gazetic","1304005111059","12xxx6789","Fizicko lice","Sarajevo","062444446","dgazetic1@etf.unsa.ba");
		k.setId(666);
		k1.setId(77);
		kds.insert(pk);
		
		assertEquals(pk,kds.getKomitentByPDVBroj("123123123"));
		kds.delete(333);
	}
	
	public void testKomitentDalDao_Update()
	{
		FizickiKomitent k,k1;
		PravniKomitent pk;
		KomitentDataSource kds;
		 pk = new PravniKomitent(333,"Pravno lice", "Grbavica","061666666","email@gmail.com",
				"Firma doo","123123123");
		kds = new KomitentDataSource();
		k = new FizickiKomitent("Elma","Gazetic","0308964384007","12xxx6789","Fizicko lice","Sarajevo","062444444","egazetic1@etf.unsa.ba");
		k1 = new FizickiKomitent("Demir","Gazetic","1304005111059","12xxx6789","Fizicko lice","Sarajevo","062444446","dgazetic1@etf.unsa.ba");
		k.setId(666);
		k1.setId(77);
		kds.insert(pk);
		pk.setAdresa("Malta");
		kds.update(pk);
		assertEquals("Malta",kds.getKomitentByPDVBroj("123123123").getAdresa());
		kds.delete(333);
	}
	
	public void testKomitentDalDao_Insert()
	{
		FizickiKomitent k,k1;
		PravniKomitent pk;
		KomitentDataSource kds;
		 pk = new PravniKomitent(333,"Pravno lice", "Grbavica","061666666","email@gmail.com",
				"Firma doo","123123123");
		kds = new KomitentDataSource();
		k = new FizickiKomitent("Elma","Gazetic","0308964384007","12xxx6789","Fizicko lice","Sarajevo","062444444","egazetic1@etf.unsa.ba");
		k1 = new FizickiKomitent("Demir","Gazetic","1304005111059","12xxx6789","Fizicko lice","Sarajevo","062444446","dgazetic1@etf.unsa.ba");
		k.setId(666);
		k1.setId(77);
		kds.insert(k);
		kds.insert(k1);
		LinkedList<Komitent> komitenti = kds.getAll();
		assertTrue(komitenti.contains(k));
		assertTrue(komitenti.contains(k1));
		kds.delete(666);
		kds.delete(77);
	}
}
