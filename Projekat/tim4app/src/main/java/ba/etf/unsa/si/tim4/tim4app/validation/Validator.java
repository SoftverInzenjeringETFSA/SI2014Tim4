package ba.etf.unsa.si.tim4.tim4app.validation;

public class Validator {
	
	private static final String ONLY_NUMBERS_REGEX = "[0-9]+";
	
	public Validator() { }
	
	public String validateSerijskiBroj(String serijskiBroj)
	{
		String errorMessage = "";
		if(serijskiBroj.equals(""))	errorMessage = "Morate unijeti serijski broj!";
		else if(!serijskiBroj.matches(ONLY_NUMBERS_REGEX)) errorMessage = "Serijski broj se mora sastojati samo od brojeva!";
		else if(serijskiBroj.length() != 6) errorMessage = "Serijski broj mora imati 6 cifara!";
		return errorMessage;
	}
	
}
