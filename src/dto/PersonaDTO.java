package dto;

import java.util.Calendar;
import java.util.Date;

public class PersonaDTO 
{
	private int idPersona;
	private String nombre;
	private String telefono;
	private String email;
	private Calendar fechaNacimiento;

	public PersonaDTO(int idPersona, String nombre, String telefono, String email, Calendar fechaNacimiento)
	{
		this.idPersona = idPersona;
		this.nombre = nombre;
		this.telefono = telefono;
		this.email = email;
		this.fechaNacimiento = fechaNacimiento;
	}
	
	public PersonaDTO(int idPersona, String nombre, String telefono, String email, Date fechaNacimiento)
	{
		this.idPersona = idPersona;
		this.nombre = nombre;
		this.telefono = telefono;
		this.email = email;
		Calendar cal = Calendar.getInstance();
		cal.setTime(fechaNacimiento);
		this.fechaNacimiento = cal;
	}
	
	public int getIdPersona() 
	{
		return this.idPersona;
	}

	public void setIdPersona(int idPersona) 
	{
		this.idPersona = idPersona;
	}

	public String getNombre() 
	{
		return this.nombre;
	}

	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}

	public String getTelefono() 
	{
		return this.telefono;
	}

	public void setTelefono(String telefono) 
	{
		this.telefono = telefono;
	}

	public String getEmail() 
	{
		return this.email;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}

	public Calendar getFechaNacimiento() 
	{
		return fechaNacimiento;
	}
	
	public void setFechaNacimiento(Calendar fechaNacimiento) 
	{
		this.fechaNacimiento = fechaNacimiento;
	}
}
