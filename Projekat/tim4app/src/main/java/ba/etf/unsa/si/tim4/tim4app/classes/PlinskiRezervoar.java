package ba.etf.unsa.si.tim4.tim4app.classes;

import java.util.Calendar;
import java.util.Date;

public class PlinskiRezervoar {

	private int mId;
	private String mSerijskiBroj;
	private int mKapacitet;
	private int mTezina;
	private int mNapunjenost;
	private String mTipRezervoara;
	private Date mDatumZadnjegBazdarenja;
	private String mLokacija;
	private String mTrenutniStatus;
	
	public static final double RESERVOIR_PRICE_CONSTANT = 10.5;
	
	public PlinskiRezervoar(String mSerijskiBroj, int mKapacitet,
			int mTezina, int mNapunjenost, String mTipRezervoara,
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
	
	public PlinskiRezervoar(int id, String mSerijskiBroj, int mKapacitet,
			int mTezina, int mNapunjenost, String mTipRezervoara,
			Date mDatumZadnjegBazdarenja, String mLokacija,
			String mTrenutniStatus) {
		this.mId = id;
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
	public int getNapunjenost() {
		return mNapunjenost;
	}
	public void setNapunjenost(int mNapunjenost) {
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
	
	public Date getDatumSljedecegBazdarenja()
	{
		   Calendar c = Calendar.getInstance();
		   c.setTime(this.getDatumZadnjegBazdarenja());
		   c.add(Calendar.YEAR, 2);
		   Date datumNext = c.getTime();
		   return datumNext;
	}


	@Override
	public String toString() {
		return mSerijskiBroj;
	}
	
	
}
