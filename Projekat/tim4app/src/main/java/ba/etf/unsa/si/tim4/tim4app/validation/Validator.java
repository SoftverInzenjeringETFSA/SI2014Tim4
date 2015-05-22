package ba.etf.unsa.si.tim4.tim4app.validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Validator {
	
	private static final String ONLY_NUMBERS_REGEX = "[0-9]+";
	private static final String ONLY_NUMBERS_NOZERO_REGEX = "[1-9]+";
	private static final String ONLY_LETTERS_REGEX = "[A-Z]+";
	private static final String POZIVNI_BROJ_REGEX = "03[0-9]+";
	private static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	public Validator() { }
	
	public String validateSerijskiBroj(String serijskiBroj)
	{
		String errorMessage = "";
		if(serijskiBroj.equals(""))	errorMessage = "Morate unijeti serijski broj!";
		else if(!serijskiBroj.matches(ONLY_NUMBERS_REGEX)) errorMessage = "Serijski broj se mora sastojati samo od brojeva!";
		else if(serijskiBroj.length() != 6) errorMessage = "Serijski broj mora imati 6 cifara!";
		return errorMessage;
	}
	
	public String validateQuantity(String quantityExpression, int maxQuantity)
	{
		String errorMessage = "";
		int quantity = 0;
		boolean isNumber = true;
		if(quantityExpression.equals("")) return "Morate unijeti količinu!";
		for(char c : quantityExpression.toCharArray())
		{
			if(!Character.isDigit(c)) isNumber = false;
		}
		if(!isNumber) return "Količina mora biti broj!";
		quantity = Integer.parseInt(quantityExpression);
		if(quantity < 0) errorMessage = "Količina ne može biti negativna!";
		else if(quantity > maxQuantity) errorMessage = "Količina ne može biti veća od maksimalne!";
		return errorMessage;
	}
	
	public String validateOnlyLetters(String expression, String field)
	{
		String errorMessage = "";
		for(char c : expression.toCharArray())
		{
			if(!Character.isLetter(c) && c != ' ' && c != '.') errorMessage = field + " se mora sastojati samo od razmaka i slova!";
		}
		return errorMessage;
	}
	
	public String validatePDVBroj(String pdv)
	{
		String errorMessage = "";
		if(pdv.equals(""))	errorMessage = "Morate unijeti PDV broj!";
		else if(!pdv.matches(ONLY_NUMBERS_REGEX)) errorMessage = "PDV broj se mora sastojati samo od brojeva!";
		else if(pdv.length() != 17) errorMessage = "PDV broj mora imati 17 cifara!";
		return errorMessage;
	}
	
	public String validateAdresa(String adresa)
	{
		String errorMessage = "";
		if(adresa.equals("")) errorMessage = "Morate unijeti adresu";
		return errorMessage;
	}
	
	public String validateTelefon(String telefon)
	{
		String errorMessage = "";
		if(telefon.equals(""))	errorMessage = "Morate unijeti telefon!";
		else if(!telefon.matches(ONLY_NUMBERS_REGEX)) errorMessage = "Telefon se mora sastojati samo od brojeva!";
		else if(telefon.length() != 9) errorMessage = "Telefon mora imati 9 cifara!";
		else if(telefon.substring(0, 3) != "061" && telefon.substring(0, 3) != "062" && telefon.substring(0, 3) != "063"
		&& telefon.substring(0, 3) != "065" && !telefon.substring(0, 3).matches(POZIVNI_BROJ_REGEX)) errorMessage = "Pozivni broj(3 cifre) mora biti validan!";
		return errorMessage;
	}
	
	public String validateEmail(String email)
	{
		String errorMessage = "";
		if(email.equals(""))	errorMessage = "Morate unijeti e - mail!";
		else if(!email.matches(EMAIL_REGEX)) errorMessage = "E - mail mora biti u odgovarajućem formatu!";
		return errorMessage;
	}
	
	public String validateJMB(String jmb)
	{
		String errorMessage = "";
		if(jmb.equals("")) errorMessage = "Morate unijeti JMB!";
		else if(!jmb.matches(ONLY_NUMBERS_REGEX)) errorMessage = "JMB se mora sastojati samo od brojeva!";
		else if(jmb.length() != 13) errorMessage = "JMB mora imati 13 cifara!";
		else if(!validateStringDate(jmb.substring(0, 5))) errorMessage = "Prvih 6 cifara JMB mora činiti validan datum!";
		return errorMessage;
	}
	
	public String validateBrojLicneKarte(String brojLK)
	{
		String errorMessage = "";
		if(brojLK.equals("")) errorMessage = "Morate unijeti broj lične karte!";
		else if(!brojLK.substring(0, 1).matches(ONLY_NUMBERS_NOZERO_REGEX)) errorMessage = "Prva dva znaka moraju biti brojevi!";
		else if(!brojLK.substring(2, 4).matches(ONLY_LETTERS_REGEX)) errorMessage = "4, 5, 6 znak moraju biti slova!";
		else if(!brojLK.substring(5, 8).matches(ONLY_NUMBERS_REGEX)) errorMessage = "7, 8, 9 znak moraju biti brojevi!";
		else if(brojLK.length() != 9) errorMessage = "Lična karta mora imati 9 cifara!";
		return errorMessage;
	}
	
	public boolean validateStringDate(String inDate)
	{
		if (inDate == null)
		      return false;
		    SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
		    if (inDate.trim().length() != dateFormat.toPattern().length())
		      return false;
		    dateFormat.setLenient(false);
		    try {
		      //parse the inDate parameter
		      dateFormat.parse(inDate.trim());
		    }
		    catch (ParseException pe) {
		      return false;
		    }
		    return true;
	}
}
