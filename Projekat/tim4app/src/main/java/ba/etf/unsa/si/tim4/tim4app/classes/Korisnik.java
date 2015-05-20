package ba.etf.unsa.si.tim4.tim4app.classes;

public class Korisnik {
	
	   private int mId;
	   private String mTipKorisnika;
	   private String mUsername;
	   private String mPassword;
	   private String mIme;
	   private String mPrezime;
	   private String mBrojLicneKarte;
	   private String mAdresa;
	   private String mBrojTelefona;
	   private String mDatumZaposlenja;
	   
  
	public Korisnik(String mTipKorisnika, String mUsername, String mPassword,
			String mIme, String mPrezime, String mBrojLicneKarte,
			String mAdresa, String mBrojTelefona, String mDatumZaposlenja) {

		this.mTipKorisnika = mTipKorisnika;
		this.mUsername = mUsername;
		this.mPassword = mPassword;
		this.mIme = mIme;
		this.mPrezime = mPrezime;
		this.mBrojLicneKarte = mBrojLicneKarte;
		this.mAdresa = mAdresa;
		this.mBrojTelefona = mBrojTelefona;
		this.mDatumZaposlenja = mDatumZaposlenja;
	}
	
	public int getId() {
		return mId;
	}
	public void setId(int mId) {
		this.mId = mId;
	}
	public String getTipKorisnika() {
		return mTipKorisnika;
	}
	public void setTipKorisnika(String mTipKorisnika) {
		this.mTipKorisnika = mTipKorisnika;
	}
	public String getUsername() {
		return mUsername;
	}
	public void setUsername(String mUsername) {
		this.mUsername = mUsername;
	}
	public String getPassword() {
		return mPassword;
	}
	public void setPassword(String mPassword) {
		this.mPassword = mPassword;
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
	public String getBrojLicneKarte() {
		return mBrojLicneKarte;
	}
	public void setBrojLicneKarte(String mBrojLicneKarte) {
		this.mBrojLicneKarte = mBrojLicneKarte;
	}
	public String getAdresa() {
		return mAdresa;
	}
	public void setAdresa(String mAdresa) {
		this.mAdresa = mAdresa;
	}
	public String getBrojTelefona() {
		return mBrojTelefona;
	}
	public void setBrojTelefona(String mBrojTelefona) {
		this.mBrojTelefona = mBrojTelefona;
	}
	public String getDatumZaposlenja() {
		return mDatumZaposlenja;
	}
	public void setDatumZaposlenja(String mDatumZaposlenja) {
		this.mDatumZaposlenja = mDatumZaposlenja;
	}
}