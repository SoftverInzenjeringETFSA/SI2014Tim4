package ba.etf.unsa.si.tim4.tim4app.classes;

public class FizickiKomitent extends Komitent {

	private String mIme;
	private String mPrezime;
	private String mJMB;
	private String mBrojLicneKarte;
	
	
	public FizickiKomitent(String mIme, String mPrezime, String mJMB, String mBrojLicneKarte, int id, String tipKomitenta, String adresa, String brojTelefona, String eMail) {
		super(id, tipKomitenta, adresa, brojTelefona, eMail);
		this.mIme = mIme;
		this.mPrezime = mPrezime;
		this.mJMB = mJMB;
		this.mBrojLicneKarte = mBrojLicneKarte;
	}
	
	public String getIme() {
		return mIme;
	}
	public void setIme(String mIme) {
		this.mIme = mIme;
	}
	public String getPrezime() {
		return mPrezime;
	}
	public void setPrezime(String mPrezime) {
		this.mPrezime = mPrezime;
	}
	public String getJMB() {
		return mJMB;
	}
	public void setJMB(String mJMB) {
		this.mJMB = mJMB;
	}
	public String getBrojLicneKarte() {
		return mBrojLicneKarte;
	}
	public void setBrojLicneKarte(String mBrojLicneKarte) {
		this.mBrojLicneKarte = mBrojLicneKarte;
	}
	
	@Override
	public String toString() {
		return mIme + " " + mPrezime;
	}
}
