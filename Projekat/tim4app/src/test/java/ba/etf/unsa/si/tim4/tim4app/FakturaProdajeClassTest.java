package ba.etf.unsa.si.tim4.tim4app;

import junit.framework.TestCase;

import ba.etf.unsa.si.tim4.tim4app.daldao.FaktureProdajeDataSource;
import ba.etf.unsa.si.tim4.tim4app.classes.FakturaProdaje;
import ba.etf.unsa.si.tim4.tim4app.classes.PravniKomitent;
import ba.etf.unsa.si.tim4.tim4app.classes.PlinskaBoca;
import ba.etf.unsa.si.tim4.tim4app.classes.PlinskiRezervoar;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import org.javatuples.Triplet;

public class FakturaProdajeClassTest extends TestCase {


	public void testFakturaProdaje_Konstruktor()
	{
		
		PlinskaBoca pb;
		LinkedList<Triplet<PlinskaBoca, Date, Double>> pbl = new LinkedList<Triplet<PlinskaBoca, Date, Double>>();
		LinkedList<Triplet<PlinskiRezervoar, Date, Double>> prl = new LinkedList<Triplet<PlinskiRezervoar, Date, Double>>();
		PravniKomitent k;
		FakturaProdaje fi;
		Triplet<PlinskaBoca,Date,Double> tb;
		Triplet<PlinskiRezervoar,Date,Double> tr;
		Date d;
		FaktureProdajeDataSource fids;
		fids = new FaktureProdajeDataSource();
		SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
		d = null;
		try 
		{
			d = dateformat.parse("12/12/2012");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		k = new PravniKomitent(333,"Pravni komitent", "Grbavica","061666666","email@gmail.com",
				"Firma doo","123123123");
		
		PlinskiRezervoar pr = new PlinskiRezervoar(333,"666555", 350,
				400, 1, "nadzemni",d, "Grbavica 45","iznajmljen");
		tr = new Triplet<PlinskiRezervoar,Date,Double>(pr,d,50.);
		pb =new PlinskaBoca(5,20,10);
		tb = new Triplet<PlinskaBoca,Date,Double>(pb,d,34.);
		
		prl.add(tr);
		pbl.add(tb);
		 fi = new FakturaProdaje(33,"4455",k,pbl,prl);
		FakturaProdaje f = new FakturaProdaje(33,"4455",k,pbl,prl);
		assertEquals("4455", f.getBrojFakture());
		assertEquals(k,f.getKomitent());
		assertEquals(pbl, f.getPlinskeBoceStavke());
		assertEquals(prl, f.getPlinskiRezervoarStavke());
	}
	
	public void testFakturaProdajeDalDao_insert()
	{
		PlinskiRezervoar pr;
		PlinskaBoca pb;
		LinkedList<Triplet<PlinskaBoca, Date, Double>> pbl = new LinkedList<Triplet<PlinskaBoca, Date, Double>>();
		LinkedList<Triplet<PlinskiRezervoar, Date, Double>> prl = new LinkedList<Triplet<PlinskiRezervoar, Date, Double>>();
		PravniKomitent k;
		FakturaProdaje fi;
		Triplet<PlinskaBoca,Date,Double> tb;
		Triplet<PlinskiRezervoar,Date,Double> tr;
		Date d;
		FaktureProdajeDataSource fids;
		fids = new FaktureProdajeDataSource();
		SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
		d = null;
		try 
		{
			d = dateformat.parse("12/12/2012");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		k = new PravniKomitent(333,"Pravni komitent", "Grbavica","061666666","email@gmail.com",
				"Firma doo","123123123");
		
		pr = new PlinskiRezervoar(333,"666555", 350,
				400, 1, "nadzemni",d, "Grbavica 45","iznajmljen");
		tr = new Triplet<PlinskiRezervoar,Date,Double>(pr,d,50.);
		pb =new PlinskaBoca(5,20,10);
		tb = new Triplet<PlinskaBoca,Date,Double>(pb,d,34.);
		
		prl.add(tr);
		pbl.add(tb);
		 fi = new FakturaProdaje(33,"4455",k,pbl,prl);
		int max = fids.getMaxId();
		
			fids.insert(fi);
		
			assertEquals(max,fids.getMaxId());
	}
	
	public void testFakturaProdajeDalDao_GetMaxId()
	{
		PlinskiRezervoar pr;
		PlinskaBoca pb;
		LinkedList<Triplet<PlinskaBoca, Date, Double>> pbl = new LinkedList<Triplet<PlinskaBoca, Date, Double>>();
		LinkedList<Triplet<PlinskiRezervoar, Date, Double>> prl = new LinkedList<Triplet<PlinskiRezervoar, Date, Double>>();
		PravniKomitent k;
		FakturaProdaje fi;
		Triplet<PlinskaBoca,Date,Double> tb;
		Triplet<PlinskiRezervoar,Date,Double> tr;
		Date d;
		FaktureProdajeDataSource fids;
		fids = new FaktureProdajeDataSource();
		SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
		d = null;
		try 
		{
			d = dateformat.parse("12/12/2012");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		k = new PravniKomitent(333,"Pravni komitent", "Grbavica","061666666","email@gmail.com",
				"Firma doo","123123123");
		
		pr = new PlinskiRezervoar(333,"666555", 350,
				400, 1, "nadzemni",d, "Grbavica 45","iznajmljen");
		tr = new Triplet<PlinskiRezervoar,Date,Double>(pr,d,50.);
		pb =new PlinskaBoca(5,20,10);
		tb = new Triplet<PlinskaBoca,Date,Double>(pb,d,34.);
		
		prl.add(tr);
		pbl.add(tb);
		 fi = new FakturaProdaje(k,pbl,prl);
		int max = fids.getMaxId();
		
		fi.setId(max +1);
		
		fids.insert(fi);
		
		assertEquals(max,fids.getMaxId());
		fi.setId(33);
	}

}
