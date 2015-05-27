package ba.etf.unsa.si.tim4.tim4app.validation;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Validator implements Serializable {

	private static final long serialVersionUID = 7766987411442122500L;
	private static final String ONLY_NUMBERS_REGEX = "[0-9]+";
	private static final String ONLY_NUMBERS_NOZERO_REGEX = "[1-9]+";
	private static final String ONLY_LETTERS_REGEX = "[a-zA-Z]+";
	private static final String POZIVNI_BROJ_REGEX = "03[0-9]+";
	private static final String MOBITEL_REGEX = "06[0-3]+";
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
		if(expression.equals("")) errorMessage = "Morate unijeti " + field +" !";
		else
		{
			boolean onlySpaces = true;
			for(char x: expression.toCharArray())
			{
				if(x != ' ') onlySpaces = false;
			}
			if(onlySpaces) errorMessage = "Morate unijeti " + field + " !";
			else
			{
				for(char c : expression.toCharArray())
				{
					if(!Character.isLetter(c) && c != ' ' && c != '.') errorMessage = field + " se mora sastojati samo od razmaka i slova!";
				}
			}
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
	
	public String validateNazivFirme(String naziv)
	{
		String errorMessage = "";
		if(naziv.equals("")) errorMessage = "Morate unijeti naziv firme!";
		else
		{
			boolean onlySpaces = true;
			for(char x: naziv.toCharArray())
			{
				if(x != ' ') onlySpaces = false;
			}
			if(onlySpaces) errorMessage = "Morate unijeti naziv firme!";
		}
		return errorMessage;
	}
	
	public String validateTelefon(String telefon)
	{
		String errorMessage = "";
		final String haloPozivni = "064";
		final String mobisPozivni = "065";
		if(telefon.equals(""))	errorMessage = "Morate unijeti telefon!";
		else if(!telefon.matches(ONLY_NUMBERS_REGEX)) errorMessage = "Telefon se mora sastojati samo od brojeva!";
		else if(telefon.length() != 9 && telefon.length() != 10) errorMessage = "Telefon mora imati 9 ili 10 cifara!";
		else
		{
			String pozivni = telefon.substring(0, 3);
			if(telefon.length() == 9)
			{
				if(pozivni.equals(haloPozivni)) errorMessage = "Pozivni broj(3 cifre) mora biti validan!";
				else if(!pozivni.matches(MOBITEL_REGEX) && !pozivni.matches(POZIVNI_BROJ_REGEX) && !pozivni.equals(mobisPozivni)) errorMessage = "Pozivni broj(3 cifre) mora biti validan!";
			}
			else if(telefon.length() == 10 && !pozivni.equals(haloPozivni)) errorMessage = "Pozivni broj(3 cifre) mora biti validan!";
		}	
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
		else if(!validateStringDate(jmb.substring(0, 7))) errorMessage = "Prvih 6 cifara JMB mora činiti validan datum!";
		return errorMessage;
	}
	
	public String validateBrojLicneKarte(String brojLK)
	{
		String errorMessage = "";
		if(brojLK.equals("")) errorMessage = "Morate unijeti broj lične karte!";
		else if(brojLK.length() != 9) errorMessage = "Lična karta mora imati 9 znakova!";
		else if(!brojLK.substring(0, 2).matches(ONLY_NUMBERS_REGEX)) errorMessage = "Prva dva znaka moraju biti brojevi!";
		else if(!brojLK.substring(2, 5).matches(ONLY_LETTERS_REGEX)) errorMessage = "3, 4, 5 znak moraju biti slova!";
		else if(!brojLK.substring(5, 9).matches(ONLY_NUMBERS_REGEX)) errorMessage = "6, 7, 8, 9 znak moraju biti brojevi!";
		return errorMessage;
	}
	
	public String validateOnlyNumbers(String expression, String field)
	{
		String errorMessage = "";
		if(expression.equals("")) errorMessage = "Morate unijeti " + field + "!";
		else if(!expression.matches(ONLY_NUMBERS_REGEX)) errorMessage = field + " mora biti broj!";
		else if(Integer.valueOf(expression) < 0 || Integer.valueOf(expression) > 500) errorMessage = field + " mora biti veći od nule i manji od 500 l(kg)";
		return errorMessage;
	}
	
	public boolean validateStringDate(String inDate)
	{
		if (inDate == null)
		      return false;
		    SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyy");
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
