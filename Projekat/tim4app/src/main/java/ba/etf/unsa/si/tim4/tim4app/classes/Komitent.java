package ba.etf.unsa.si.tim4.tim4app.classes;

public abstract class Komitent {

	protected int mId;
	protected String mTipKomitenta;
	protected String mAdresa;
	protected String mBrojTelefona;
	protected String mEmail;
	
	protected Komitent(int id, String tipKomitenta, String adresa, String brojTelefona, String eMail)
	{
		this.mId = id;
		this.mTipKomitenta = tipKomitenta;
		this.mAdresa = adresa;
		this.mBrojTelefona = brojTelefona;
		this.mEmail = eMail;
	}
	
	public int getId() {
		return mId;
	}
	public void setId(int mId) {
		this.mId = mId;
	}
	public String getTipKomitenta() {
		return mTipKomitenta;
	}
	public void setTipKomitenta(String mTipKomitenta) {
		this.mTipKomitenta = mTipKomitenta;
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
	public String getEmail() {
		return mEmail;
	}
	public void setEmail(String mEmail) {
		this.mEmail = mEmail;
	}

	@Override
	public String toString() {
		if(this.getTipKomitenta().equals("Pravno lice"))
		{
			return ((PravniKomitent)this).toString();
		}
		else return ((FizickiKomitent)this).toString();
	}
	
}
