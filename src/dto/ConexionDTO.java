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
	
	
	
	
}
