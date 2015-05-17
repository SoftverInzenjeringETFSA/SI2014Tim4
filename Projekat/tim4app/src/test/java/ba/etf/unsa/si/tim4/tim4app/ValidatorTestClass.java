package ba.etf.unsa.si.tim4.tim4app;

import ba.etf.unsa.si.tim4.tim4app.validation.Validator;
import junit.framework.TestCase;

public class ValidatorTestClass extends TestCase {

	public ValidatorTestClass(String name) {
		super(name);
	}

	public void testValidateSerijskiBroj_NijeUnesen()
	{
		Validator validator = new Validator();
		String input = "";
		String errorMessage = validator.validateSerijskiBroj(input);
		assertEquals("Morate unijeti serijski broj!", errorMessage);
	}
	
	public void testValidateSerijskiBroj_SamoBrojevi()
	{
		Validator validator = new Validator();
		String input = "xxxxx";
		String errorMessage = validator.validateSerijskiBroj(input);
		assertEquals("Serijski broj se mora sastojati samo od brojeva!", errorMessage);
	}
	
	public void testValidateSerijskiBroj_SestCifara()
	{
		Validator validator = new Validator();
		String input = "1234567";
		String errorMessage = validator.validateSerijskiBroj(input);
		assertEquals("Serijski broj mora imati 6 cifara!", errorMessage);
	}
	
	public void testValidateSerijskiBroj_Validan()
	{
		Validator validator = new Validator();
		String input = "123456";
		String errorMessage = validator.validateSerijskiBroj(input);
		assertEquals("", errorMessage);
	}

}
