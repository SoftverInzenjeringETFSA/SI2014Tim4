package ba.etf.unsa.si.tim4.tim4app.classes;

public class PravniKomitent extends Komitent {

	private String mNazivFirme;
	private String mPDVbroj;
	
	public PravniKomitent(int id, String tipKomitenta, String adresa,
			String brojTelefona, String eMail, String mNazivFirme,
			String mPDVbroj) 
	{
		super(id, tipKomitenta, adresa, brojTelefona, eMail);
		this.mNazivFirme = mNazivFirme;
		this.mPDVbroj = mPDVbroj;
	}

	public String getNazivFirme() {
		return mNazivFirme;
	}
	public void setNazivFirme(String mNazivFirme) {
		this.mNazivFirme = mNazivFirme;
	}
	public String getPDVbroj() {
		return mPDVbroj;
	}
	public void setPDVbroj(String mPDVbroj) {
		this.mPDVbroj = mPDVbroj;
	}

	@Override
	public String toString() {
		return mNazivFirme;
	}
}
