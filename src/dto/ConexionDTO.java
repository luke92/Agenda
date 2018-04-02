package dto;

public class ConexionDTO 
{
	private String servidor;
	private String puerto;
	private String usuario;
	private String clave;
	private String baseDatos;
	
	public String getServidor() {
		return servidor;
	}
	public void setServidor(String servidor) {
		this.servidor = servidor;
	}
	public String getPuerto() {
		return puerto;
	}
	public void setPuerto(String puerto) {
		this.puerto = puerto;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getBaseDatos() {
		return baseDatos;
	}
	public void setBaseDatos(String baseDatos) {
		this.baseDatos = baseDatos;
	}
	public ConexionDTO(String servidor, String puerto, String usuario, String clave, String baseDatos) 
	{
		this.servidor = servidor;
		this.puerto = puerto;
		this.usuario = usuario;
		this.clave = clave;
		this.baseDatos = baseDatos;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((baseDatos == null) ? 0 : baseDatos.hashCode());
		result = prime * result + ((clave == null) ? 0 : clave.hashCode());
		result = prime * result + ((puerto == null) ? 0 : puerto.hashCode());
		result = prime * result + ((servidor == null) ? 0 : servidor.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConexionDTO other = (ConexionDTO) obj;
		if (baseDatos == null) {
			if (other.baseDatos != null)
				return false;
		} else if (!baseDatos.equals(other.baseDatos))
			return false;
		if (clave == null) {
			if (other.clave != null)
				return false;
		} else if (!clave.equals(other.clave))
			return false;
		if (puerto == null) {
			if (other.puerto != null)
				return false;
		} else if (!puerto.equals(other.puerto))
			return false;
		if (servidor == null) {
			if (other.servidor != null)
				return false;
		} else if (!servidor.equals(other.servidor))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}
	
	
	
	
}
