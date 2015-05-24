package ba.etf.unsa.si.tim4.tim4app;

import ba.etf.unsa.si.tim4.tim4app.validation.Validator;
import junit.framework.TestCase;

public class ValidatorClassTest extends TestCase {

	public ValidatorClassTest(String name) {
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
	
	public void testValidatePDVbroj_NijeUnesen()
	{
		Validator validator = new Validator();
		String input ="";
		String errorMessage = validator.validatePDVBroj(input);
		assertEquals("Morate unijeti PDV broj!",errorMessage);
	}
	
	public void testValidatePDVbroj_SamoBrojevi()
	{
		Validator validator = new Validator();
		String input ="12345657vvvvvvv";
		String errorMessage = validator.validatePDVBroj(input);
		assertEquals("PDV broj se mora sastojati samo od brojeva!",errorMessage);
	}
	
	public void testValidatePDVbroj_SedamnaestCifara()
	{
		Validator validator = new Validator();
		String input ="12345657";
		String errorMessage = validator.validatePDVBroj(input);
		assertEquals("PDV broj mora imati 17 cifara!",errorMessage);
	}
	
	public void testValidateAdresa_NijeUneseno()
	{
		Validator validator = new Validator();
		String input = "";
		String errorMessage = validator.validateAdresa(input);
		assertEquals("Morate unijeti adresu",errorMessage);
	}
	
	public void testValidateTelefon_NijeUneseno()
	{
		Validator validator = new Validator();
		String input = "";
		String errorMessage = validator.validateTelefon(input);
		assertEquals("Morate unijeti telefon!",errorMessage);
	}
	
	public void testValidateTelefon_SamoBrojevi()
	{
		Validator validator = new Validator();
		String input = "12xxxxxx";
		String errorMessage = validator.validateTelefon(input);
		assertEquals("Telefon se mora sastojati samo od brojeva!",errorMessage);
	}
	
	public void testValidateTelefon_DevetCifara()
	{
		Validator validator = new Validator();
		String input = "1234567";
		String errorMessage = validator.validateTelefon(input);
		assertEquals("Telefon mora imati 9 cifara!",errorMessage);
	}
	
	public void testValidateTelefon_PozivniBroj()
	{
		Validator validator = new Validator();
		String input = "123123123";
		String errorMessage = validator.validateTelefon(input);
		assertEquals("Pozivni broj(3 cifre) mora biti validan!",errorMessage);
	}
	
	public void testValidateEmail_NijeUneseno()
	{
		Validator validator = new Validator();
		String input = "";
		String errorMessage = validator.validateEmail(input);
		assertEquals("Morate unijeti e - mail!",errorMessage);
	}
	
	public void testValidateEmail_Format()
	{
		Validator validator = new Validator();
		String input = "xxxxxx";
		String errorMessage = validator.validateEmail(input);
		assertEquals("E - mail mora biti u odgovarajućem formatu!",errorMessage);
	}
	
	public void testValidateJMB_NijeUneseno()
	{
		Validator validator = new Validator();
		String input = "";
		String errorMessage = validator.validateJMB(input);
		assertEquals("Morate unijeti JMB!",errorMessage);
	}
	
	public void testValidateJMB_SamoBrojevi()
	{
		Validator validator = new Validator();
		String input = "454xxxxxxx";
		String errorMessage = validator.validateJMB(input);
		assertEquals("JMB se mora sastojati samo od brojeva!",errorMessage);
	}
	
	public void testValidateJMB_TrinaestCifara()
	{
		Validator validator = new Validator();
		String input = "454554";
		String errorMessage = validator.validateJMB(input);
		assertEquals("JMB mora imati 13 cifara!",errorMessage);
	}
	
	public void testValidateJMB_ValidanDatum()
	{
		Validator validator = new Validator();
		String input = "9876991324342";
		String errorMessage = validator.validateJMB(input);
		assertEquals("Prvih 6 cifara JMB mora činiti validan datum!",errorMessage);
	}
	
	public void testValidateBrojLicneKarte_NijeUneseno()
	{
		Validator validator = new Validator();
		String input = "";
		String errorMessage = validator.validateBrojLicneKarte(input);
		assertEquals("Morate unijeti broj lične karte!",errorMessage);
	}
	
	public void testValidateBrojLicneKarte_PrvaDvaBrojevi()
	{
		Validator validator = new Validator();
		String input = "xx23xxx";
		String errorMessage = validator.validateBrojLicneKarte(input);
		assertEquals("Prva dva znaka moraju biti brojevi!",errorMessage);
	}
	
	public void testValidateBrojLicneKarte_TriSlova()
	{
		Validator validator = new Validator();
		String input = "123123123";
		String errorMessage = validator.validateBrojLicneKarte(input);
		assertEquals("4, 5, 6 znak moraju biti slova!",errorMessage);
	}
	
	public void testValidateBrojLicneKarte_DevetCifara()
	{
		Validator validator = new Validator();
		String input = "12xxx878934";
		String errorMessage = validator.validateBrojLicneKarte(input);
		assertEquals("Lična karta mora imati 9 cifara!",errorMessage);
	}
	
	public void testValidateOnlyLetters_UneseniBrojevi()
	{
		Validator validator = new Validator();
		String input = "12xxx123123123";
		String field = "field";
		String errorMessage = validator.validateOnlyLetters(input, field);
		assertEquals(field + " se mora sastojati samo od razmaka i slova!",errorMessage);
	}
	
	public void testValidateOnlyLetters_SamoSlova()
	{
		Validator validator = new Validator();
		String input = "jkkassssss";
		String field = "field";
		String errorMessage = validator.validateOnlyLetters(input, field);
		assertEquals("",errorMessage);
	}
	
	public void testValidateOnlyNumbers_NijeUneseno()
	{
		Validator validator = new Validator();
		String input = "";
		String field = "field";
		String errorMessage = validator.validateOnlyNumbers(input, field);
		assertEquals("Morate unijeti " + field + "!",errorMessage);
	}
	
	public void testValidateOnlyNumbers_UnesenaSlova()
	{
		Validator validator = new Validator();
		String input = "123nnnn";
		String field = "field";
		String errorMessage = validator.validateOnlyNumbers(input, field);
		assertEquals(field + " mora biti broj!",errorMessage);
	}
	
	public void testValidateOnlyNumbers_PrekoracenjeMakismuma()
	{
		Validator validator = new Validator();
		String input = "123444444";
		String field = "field";
		String errorMessage = validator.validateOnlyNumbers(input, field);
		assertEquals(field + " mora biti veći od nule i manji od 500 l(kg)",errorMessage);
	}
	
	

}
