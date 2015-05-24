package ba.etf.unsa.si.tim4.tim4app.classes;

import java.io.Serializable;
import java.util.LinkedList;

public class Skladiste implements Serializable {

	private static final long serialVersionUID = -1882846992209756551L;
	private int mPetoLitarskeBoce = 0;
	private int mDesetLitarskeBoce = 0;
	private int mPetnaestLitarskeBoce = 0;
	private LinkedList<PlinskiRezervoar> mPlinskiRezervoari = new LinkedList<PlinskiRezervoar>();
	
	public Skladiste() { }
	
	public void addPetLitarske(int quantity)
	{
		mPetoLitarskeBoce += quantity;
	}
	
	public boolean isEmpty()
	{
		return (mPetoLitarskeBoce == 0 && mDesetLitarskeBoce == 0 && mPetnaestLitarskeBoce == 0 && mPlinskiRezervoari.size() == 0);
	}
	
	public void addDesetLitarske(int quantity)
	{
		mDesetLitarskeBoce += quantity;
	}
	
	public void addPetnaestLitarske(int quantity)
	{
		mPetnaestLitarskeBoce += quantity;
	}
	
	public boolean addPlinskiRezervoar(PlinskiRezervoar pr)
	{
		if(mPlinskiRezervoari.contains(pr)) return false;
		mPlinskiRezervoari.add(pr);
		return true;
	}
	
	public int getPetLitarske()
	{
		return mPetoLitarskeBoce;
	}
	
	public int getDesetLitarske()
	{
		return mDesetLitarskeBoce;
	}
	
	public int getPetnaestLitarske()
	{
		return mPetnaestLitarskeBoce;
	}
	
	public LinkedList<PlinskiRezervoar> getPlinskiRezervoari() {
		return mPlinskiRezervoari;
	}

	public void setPlinskiRezervoari(
			LinkedList<PlinskiRezervoar> mPlinskiRezervoari) {
		this.mPlinskiRezervoari = mPlinskiRezervoari;
	}

	public void clearSkladiste()
	{
		mPetoLitarskeBoce = 0;
		mDesetLitarskeBoce = 0;
		mPetnaestLitarskeBoce = 0;
		for(int i = 0; i < mPlinskiRezervoari.size(); i++)
		{
			mPlinskiRezervoari.remove();
		}
	}
}
