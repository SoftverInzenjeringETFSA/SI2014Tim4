package ba.etf.unsa.si.tim4.tim4app;

import junit.framework.TestCase;
import ba.etf.unsa.si.tim4.tim4app.daldao.FaktureIznajmljivanjaDataSource;
import ba.etf.unsa.si.tim4.tim4app.classes.FakturaIznajmljivanje;
import ba.etf.unsa.si.tim4.tim4app.classes.PravniKomitent;
import ba.etf.unsa.si.tim4.tim4app.classes.PlinskaBoca;
import ba.etf.unsa.si.tim4.tim4app.classes.PlinskiRezervoar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import org.javatuples.Triplet;

public class FakturaIznajmljivanjeClassTest extends TestCase { // dodat brisanje da se ne nagomilaju zbog testiranja
	PlinskiRezervoar pr;
	PlinskaBoca pb;
	LinkedList<Triplet<PlinskaBoca, Date, Double>> pbl;
	LinkedList<Triplet<PlinskiRezervoar, Date, Double>> prl;
	PravniKomitent k;
	FakturaIznajmljivanje fi;
	Triplet<PlinskaBoca,Date,Double> tb;
	Triplet<PlinskiRezervoar,Date,Double> tr;
	Date d;
	FaktureIznajmljivanjaDataSource fids;
	
	protected void SetUp()
	{
		fids = new FaktureIznajmljivanjaDataSource();
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
		 fi = new FakturaIznajmljivanje(33,"4455",k,pbl,prl);
		
		
	}
	
	public void testFakturaiznajmljivanje_Konstruktor()
	{
		FakturaIznajmljivanje f = new FakturaIznajmljivanje(33,"4455",k,pbl,prl);
		assertEquals("4455", f.getBrojFakture());
		assertEquals(k,f.getKomitent());
		assertEquals(pbl, f.getPlinskeBoceStavke());
		assertEquals(prl, f.getPlinskiRezervoarStavke());
	}
	
	public void testFakturaiznajmljivanjeDalDao_insert()
	{
		int max = fids.getMaxId();
		
			fi.setId(max +1);
		
			fids.insert(fi);
		
			assertEquals(max +1 ,fids.getMaxId());
	}
	
	public void testFakturaiznajmljivanjeDalDao_GetMaxId()
	{
		int max = fids.getMaxId();
		
		fi.setId(max +1);
		
		fids.insert(fi);
		
		assertEquals(max +1 ,fids.getMaxId());
		fi.setId(33);
	}
	
}
