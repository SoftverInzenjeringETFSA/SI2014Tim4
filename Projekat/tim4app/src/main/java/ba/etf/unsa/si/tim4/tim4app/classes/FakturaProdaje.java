package ba.etf.unsa.si.tim4.tim4app.classes;

import java.util.Date;
import java.util.LinkedList;

import org.javatuples.Triplet;

public class FakturaProdaje {

	private int mId;
	private String mBrojFakture;
	private Komitent mKomitent;
	private LinkedList<Triplet<PlinskaBoca, Date, Double>> mPlinskeBoceStavke;
	private LinkedList<Triplet<PlinskiRezervoar, Date, Double>> mPlinskiRezervoarStavke;

	public FakturaProdaje(
			int mId,
			String mBrojFakture,
			Komitent mKomitent,
			LinkedList<Triplet<PlinskaBoca, Date, Double>> mPlinskeBoceStavke,
			LinkedList<Triplet<PlinskiRezervoar, Date, Double>> mPlinskiRezervoarStavke) {
		this.mId = mId;
		this.mBrojFakture = mBrojFakture;
		this.mKomitent = mKomitent;
		this.mPlinskeBoceStavke = mPlinskeBoceStavke;
		this.mPlinskiRezervoarStavke = mPlinskiRezervoarStavke;
	}
	
	public FakturaProdaje(
			Komitent mKomitent,
			LinkedList<Triplet<PlinskaBoca, Date, Double>> mPlinskeBoceStavke,
			LinkedList<Triplet<PlinskiRezervoar, Date, Double>> mPlinskiRezervoarStavke) {
		this.mKomitent = mKomitent;
		this.mPlinskeBoceStavke = mPlinskeBoceStavke;
		this.mPlinskiRezervoarStavke = mPlinskiRezervoarStavke;
	}

	public int getId() {
		return mId;
	}

	public void setId(int mId) {
		this.mId = mId;
	}

	public String getBrojFakture() {
		return mBrojFakture;
	}

	public void setBrojFakture(String mBrojFakture) {
		this.mBrojFakture = mBrojFakture;
	}

	public Komitent getKomitent() {
		return mKomitent;
	}

	public void setKomitent(Komitent mKomitent) {
		this.mKomitent = mKomitent;
	}

	public LinkedList<Triplet<PlinskaBoca, Date, Double>> getPlinskeBoceStavke() {
		return mPlinskeBoceStavke;
	}

	public void setPlinskeBoceStavke(
			LinkedList<Triplet<PlinskaBoca, Date, Double>> mPlinskeBoceStavke) {
		this.mPlinskeBoceStavke = mPlinskeBoceStavke;
	}

	public LinkedList<Triplet<PlinskiRezervoar, Date, Double>> getPlinskiRezervoarStavke() {
		return mPlinskiRezervoarStavke;
	}

	public void setPlinskiRezervoarStavke(
			LinkedList<Triplet<PlinskiRezervoar, Date, Double>> mPlinskiRezervoarStavke) {
		this.mPlinskiRezervoarStavke = mPlinskiRezervoarStavke;
	}

}
