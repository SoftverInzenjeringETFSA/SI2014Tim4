package ba.etf.unsa.si.tim4.tim4app.classes;

import java.util.Date;

import org.javatuples.Triplet;

public class FakturaIznajmljivanje {

	private int mId;
	private String mBrojFakture;
	private Komitent mKomitent;
	private Triplet<PlinskaBoca, Date, Double> mPlinskeBoceStavke;
	private Triplet<PlinskiRezervoar, Date, Double> mPlinskiRezervoarStavke;

	public FakturaIznajmljivanje(int mId, String mBrojFakture,
			Komitent mKomitent,
			Triplet<PlinskaBoca, Date, Double> mPlinskeBoceStavke,
			Triplet<PlinskiRezervoar, Date, Double> mPlinskiRezervoarStavke) {
		super();
		this.mId = mId;
		this.mBrojFakture = mBrojFakture;
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
	public Triplet<PlinskaBoca, Date, Double> getmPlinskeBoceStavke() {
		return mPlinskeBoceStavke;
	}
	public void setmPlinskeBoceStavke(
			Triplet<PlinskaBoca, Date, Double> mPlinskeBoceStavke) {
		this.mPlinskeBoceStavke = mPlinskeBoceStavke;
	}
	public Triplet<PlinskiRezervoar, Date, Double> getmPlinskiRezervoarStavke() {
		return mPlinskiRezervoarStavke;
	}
	public void setmPlinskiRezervoarStavke(
			Triplet<PlinskiRezervoar, Date, Double> mPlinskiRezervoarStavke) {
		this.mPlinskiRezervoarStavke = mPlinskiRezervoarStavke;
	}

}
