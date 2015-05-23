package ba.etf.unsa.si.tim4.tim4app.classes;

import java.util.Date;

public class Promjena {

	private int mId;
	private String mSerijskiBroj;
	private Date mDatumPromjene;
	private int mTipPromjene;
	private String mOpisPromjene;
	
	public Promjena(String mSerijskiBroj, Date mDatumPromjene,
			int mTipPromjene, String mOpisPromjene) {
		
		this.mSerijskiBroj = mSerijskiBroj;
		this.mDatumPromjene = mDatumPromjene;
		this.mTipPromjene = mTipPromjene;
		this.mOpisPromjene = mOpisPromjene;
	}

	public Promjena(int mId, String mSerijskiBroj, Date mDatumPromjene,
			int mTipPromjene, String mOpisPromjene) {
		
		this.mId = mId;
		this.mSerijskiBroj = mSerijskiBroj;
		this.mDatumPromjene = mDatumPromjene;
		this.mTipPromjene = mTipPromjene;
		this.mOpisPromjene = mOpisPromjene;
	}

	public int getId() {
		return mId;
	}

	public void setId(int mId) {
		this.mId = mId;
	}

	public String getSerijskiBroj() {
		return mSerijskiBroj;
	}

	public void setSerijskiBroj(String mSerijskiBroj) {
		this.mSerijskiBroj = mSerijskiBroj;
	}

	public Date getDatumPromjene() {
		return mDatumPromjene;
	}

	public void setDatumPromjene(Date mDatumPromjene) {
		this.mDatumPromjene = mDatumPromjene;
	}

	public int getTipPromjene() {
		return mTipPromjene;
	}

	public void setTipPromjene(int mTipPromjene) {
		this.mTipPromjene = mTipPromjene;
	}

	public String getOpisPromjene() {
		return mOpisPromjene;
	}

	public void setOpisPromjene(String mOpisPromjene) {
		this.mOpisPromjene = mOpisPromjene;
	}
	
	
}
