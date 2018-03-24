package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class ExpReg 
{
	public static boolean contieneSoloNumeros(String text)
	{
		return Pattern.matches("[0-9]\\d*", text);
	}
	
	public static boolean mailValido(String string) {
		Pattern pattern = Pattern.compile(
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		String email = string;
		Matcher mather = pattern.matcher(email);
		if (mather.find())
			return true;
		else
			return false;
	}
	
	public static boolean telefonoValido(String text)
	{
		return Pattern.matches("^(?:(?:00)?549?)?0?(?:11|[2368]\\d)(?:(?=\\d{0,2}15)\\d{2})??\\d{8}$",text);
	}
	
	public static boolean contieneLetrasyEspacios(String text)
	{
		return Pattern.matches("^[\\w\\.\\-\\s]+$", text);
	}
	
	public static boolean contieneLetrasNumerosyEspacios(String text)
	{
		return Pattern.matches("^[\\w\\d\\-_\\s]+$", text);
	}
}
