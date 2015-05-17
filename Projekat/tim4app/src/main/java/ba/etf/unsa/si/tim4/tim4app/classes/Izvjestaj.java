package ba.etf.unsa.si.tim4.tim4app.classes;

import java.util.Date;

public class Izvjestaj {

	private int mId;
	private String mBrojIzvjestaja;
	private Date mDatumIzvjestaja;
	private String mParametarIzvjestaja;
	private String mTipIzvjestaja;
	
	public Izvjestaj(String mBrojIzvjestaja, Date mDatumIzvjestaja, String mParametarIzvjestaja,
			String mTipIzvjestaja) 
	{
		this.mBrojIzvjestaja = mBrojIzvjestaja;
		this.mDatumIzvjestaja = mDatumIzvjestaja;
		this.mParametarIzvjestaja = mParametarIzvjestaja;
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

	public String getParametarIzvjestaja() {
		return mParametarIzvjestaja;
	}

	public void setParametarIzvjestaja(String mParametarIzvjestaja) {
		this.mParametarIzvjestaja = mParametarIzvjestaja;
	}

	public String getTipIzvjestaja() {
		return mTipIzvjestaja;
	}

	public void setTipIzvjestaja(String mTipIzvjestaja) {
		this.mTipIzvjestaja = mTipIzvjestaja;
	}
}
