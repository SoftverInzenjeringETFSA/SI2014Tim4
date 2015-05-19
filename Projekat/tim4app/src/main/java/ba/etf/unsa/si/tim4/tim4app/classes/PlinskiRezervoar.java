package ba.etf.unsa.si.tim4.tim4app.classes;

import java.util.Date;

public class PlinskiRezervoar {

	private int mId;
	private String mSerijskiBroj;
	private int mKapacitet;
	private int mTezina;
	private boolean mNapunjenost;
	private String mTipRezervoara;
	private Date mDatumZadnjegBazdarenja;
	private String mLokacija;
	private String mTrenutniStatus;
	
	public PlinskiRezervoar(String mSerijskiBroj, int mKapacitet,
			int mTezina, boolean mNapunjenost, String mTipRezervoara,
			Date mDatumZadnjegBazdarenja, String mLokacija,
			String mTrenutniStatus) {
		this.mSerijskiBroj = mSerijskiBroj;
		this.mKapacitet = mKapacitet;
		this.mTezina = mTezina;
		this.mNapunjenost = mNapunjenost;
		this.mTipRezervoara = mTipRezervoara;
		this.mDatumZadnjegBazdarenja = mDatumZadnjegBazdarenja;
		this.mLokacija = mLokacija;
		this.mTrenutniStatus = mTrenutniStatus;
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
	public int getKapacitet() {
		return mKapacitet;
	}
	public void setKapacitet(int mKapacitet) {
		this.mKapacitet = mKapacitet;
	}
	public int getTezina() {
		return mTezina;
	}
	public void setTezina(int mTezina) {
		this.mTezina = mTezina;
	}
	public boolean isNapunjenost() {
		return mNapunjenost;
	}
	public void setNapunjenost(boolean mNapunjenost) {
		this.mNapunjenost = mNapunjenost;
	}
	public String getTipRezervoara() {
		return mTipRezervoara;
	}
	public void setTipRezervoara(String mTipRezervoara) {
		this.mTipRezervoara = mTipRezervoara;
	}
	public Date getDatumZadnjegBazdarenja() {
		return mDatumZadnjegBazdarenja;
	}
	public void setDatumZadnjegBazdarenja(Date mDatumZadnjegBazdarenja) {
		this.mDatumZadnjegBazdarenja = mDatumZadnjegBazdarenja;
	}
	public String getLokacija() {
		return mLokacija;
	}
	public void setLokacija(String mLokacija) {
		this.mLokacija = mLokacija;
	}
	public String getTrenutniStatus() {
		return mTrenutniStatus;
	}
	public void setTrenutniStatus(String mTrenutniStatus) {
		this.mTrenutniStatus = mTrenutniStatus;
	}
	
	
}
