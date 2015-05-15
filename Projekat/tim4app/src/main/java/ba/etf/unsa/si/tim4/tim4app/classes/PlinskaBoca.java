package ba.etf.unsa.si.tim4.tim4app.classes;

public class PlinskaBoca {

	private int mKapacitet;
	private double mCijena;
	private int mKolicina;
	private int mId;
	
	public PlinskaBoca(int kapacitet, double cijena, int kolicina)
	{
		this.mKapacitet = kapacitet;
		this.mCijena = cijena;
		this.mKolicina = kolicina;
	}

	public int getKapacitet() {
		return mKapacitet;
	}

	public void setKapacitet(int mKapacitet) {
		this.mKapacitet = mKapacitet;
	}

	public double getCijena() {
		return mCijena;
	}

	public void setCijena(double mCijena) {
		this.mCijena = mCijena;
	}
	
	public int getKolicina() {
		return mKolicina;
	}

	public void setKolicina(int mKolicina) {
		this.mKolicina = mKolicina;
	}
	
	public int getId()
	{
		return mId;
	}
	
	public void setId(int mId)
	{
		this.mId = mId;
	}
}
