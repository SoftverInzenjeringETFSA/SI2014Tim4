package ba.etf.unsa.si.tim4.tim4app.daldao;

public class OsobaTest implements java.io.Serializable{
	
	private long id;
	private String ime;
	private String prezime;
	
	public OsobaTest()
	{
		
	}

	public long getId() {
		return id;
	}

	public String getIme() {
		return ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
}
