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
	
}
