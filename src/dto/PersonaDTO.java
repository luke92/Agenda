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
	private String calle;
	private int altura;
	private int piso;
	private String depto;
	private LocalidadDTO localidad;
	private TipoContactoDTO tipoContacto;
	
	public PersonaDTO(int idPersona, String nombre, String telefono, String email, Calendar fechaNacimiento,
			String calle, int altura, int piso, String depto, LocalidadDTO localidad, TipoContactoDTO tipoContacto) 
	{
		this.idPersona = idPersona;
		this.nombre = nombre;
		this.telefono = telefono;
		this.email = email;
		this.fechaNacimiento = fechaNacimiento;
		this.calle = calle;
		this.altura = altura;
		this.piso = piso;
		this.depto = depto;
		this.localidad = localidad;
		this.tipoContacto = tipoContacto;
	}

	public PersonaDTO(int idPersona, String nombre, String telefono, String email, Date fechaNacimiento,
			String calle, int altura, int piso, String depto, LocalidadDTO localidad, TipoContactoDTO tipoContacto)
	{
		this.idPersona = idPersona;
		this.nombre = nombre;
		this.telefono = telefono;
		this.email = email;
		Calendar cal = Calendar.getInstance();
		cal.setTime(fechaNacimiento);
		this.fechaNacimiento = cal;
		this.calle = calle;
		this.altura = altura;
		this.piso = piso;
		this.depto = depto;
		this.localidad = localidad;
		this.tipoContacto = tipoContacto;
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

	public String getCalle() 
	{
		return calle;
	}

	public void setCalle(String calle) 
	{
		this.calle = calle;
	}

	public int getAltura() 
	{
		return altura;
	}

	public void setAltura(int altura) 
	{
		this.altura = altura;
	}

	public int getPiso() 
	{
		return piso;
	}

	public void setPiso(int piso) 
	{
		this.piso = piso;
	}

	public String getDepto() 
	{
		return depto;
	}

	public void setDepto(String depto) 
	{
		this.depto = depto;
	}

	public LocalidadDTO getLocalidad() 
	{
		return localidad;
	}

	public void setLocalidad(LocalidadDTO localidad) 
	{
		this.localidad = localidad;
	}

	public TipoContactoDTO getTipoContacto() 
	{
		return tipoContacto;
	}

	public void setTipoContacto(TipoContactoDTO tipoContacto) 
	{
		this.tipoContacto = tipoContacto;
	}
	
	public String getDomicilioCompleto()
	{
		return getCalle() + " " + getAltura() + " (" + getPiso() + " " + getDepto() +"), " + getLocalidad().getNombre(); 
	}
	
}
