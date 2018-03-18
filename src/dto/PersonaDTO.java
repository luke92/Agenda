package dto;

import java.util.GregorianCalendar;

public class PersonaDTO 
{
	private int idPersona;
	private String nombre;
	private String telefono;
	private String email;
	private GregorianCalendar fechaNacimiento;

	public PersonaDTO(int idPersona, String nombre, String telefono, String email, GregorianCalendar fechaNacimiento)
	{
		this.idPersona = idPersona;
		this.nombre = nombre;
		this.telefono = telefono;
		this.setEmail(email);
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
}
