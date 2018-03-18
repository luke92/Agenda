package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public abstract class Fechas 
{
	public static Calendar String_a_Fecha(String fechaString)
	{
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date date = null;
		try 
		{
			date = df.parse(fechaString);
		} 
		catch (ParseException e) 
		{
			e.printStackTrace();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}
	
	public static Calendar String_a_Fecha_MySQL(String fechaString)
	{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try 
		{
			date = df.parse(fechaString);
		} 
		catch (ParseException e) 
		{
			e.printStackTrace();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}
	
	public static String Fecha_a_String(Calendar fecha)
	{
		String fechaString = null;
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		fechaString = format.format(fecha.getTime());
		return fechaString;
	}
	
	public static String Fecha_a_String_MySQL(Calendar fecha)
	{
		String fechaString = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		fechaString = format.format(fecha.getTime());
		return fechaString;
	}
}
