package dto;

public class TipoContactoDTO 
{
	private int idTipoContacto;
	private String nombre;
	
	public TipoContactoDTO(int idTipoContacto, String nombre) 
	{
		this.idTipoContacto = idTipoContacto;
		this.nombre = nombre;
	}

	public int getIdTipoContacto() 
	{
		return idTipoContacto;
	}

	public void setIdTipoContacto(int idTipoContacto) 
	{
		this.idTipoContacto = idTipoContacto;
	}

	public String getNombre() 
	{
		return nombre;
	}

	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}
		
}
