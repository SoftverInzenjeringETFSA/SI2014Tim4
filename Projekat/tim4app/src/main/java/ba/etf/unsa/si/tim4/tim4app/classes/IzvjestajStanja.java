package ba.etf.unsa.si.tim4.tim4app.classes;

import java.util.Date;
import java.util.LinkedList;

public class IzvjestajStanja {

	private int mId;
	private String mBrojIzvjestaja;
	private Date mDatumIzvjestaja;
	private String mTipIzvjestaja;
	private LinkedList<PlinskiRezervoar> mPlinskiRezervoari;
	private LinkedList<PlinskaBoca> mPlinskaBoce;
	
	public IzvjestajStanja(String mBrojIzvjestaja, Date mDatumIzvjestaja, String mTipIzvjestaja) 
	{
		this.mBrojIzvjestaja = mBrojIzvjestaja;
		this.mDatumIzvjestaja = mDatumIzvjestaja;
		this.mTipIzvjestaja = mTipIzvjestaja;
	}

	public int getId() {
		return mId;
	}

	public void setId(int mId) {
		this.mId = mId;
	}

	public String getBrojIzvjestaja() {
		return mBrojIzvjestaja;
	}

	public void setBrojIzvjestaja(String mBrojIzvjestaja) {
		this.mBrojIzvjestaja = mBrojIzvjestaja;
	}

	public Date getDatumIzvjestaja() {
		return mDatumIzvjestaja;
	}

	public void setDatumIzvjestaja(Date mDatumIzvjestaja) {
		this.mDatumIzvjestaja = mDatumIzvjestaja;
	}

	public String getTipIzvjestaja() {
		return mTipIzvjestaja;
	}

	public void setTipIzvjestaja(String mTipIzvjestaja) {
		this.mTipIzvjestaja = mTipIzvjestaja;
	}

	public LinkedList<PlinskiRezervoar> getPlinskiRezervoari() {
		return mPlinskiRezervoari;
	}

	public void setPlinskiRezervoari(
			LinkedList<PlinskiRezervoar> mPlinskiRezervoari) {
		this.mPlinskiRezervoari = mPlinskiRezervoari;
	}

	public LinkedList<PlinskaBoca> getPlinskaBoce() {
		return mPlinskaBoce;
	}

	public void setPlinskaBoce(LinkedList<PlinskaBoca> mPlinskaBoca) {
		this.mPlinskaBoce = mPlinskaBoca;
	}
}
