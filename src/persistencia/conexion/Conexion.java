package persistencia.conexion;

import java.sql.Connection;
import java.sql.DriverManager;

import dto.ConexionDTO;

public class Conexion 
{
	public static Conexion instancia;
	private Connection conexion;
	public static boolean conexionEstablecida;
	
	public Conexion() 
	{
		ConexionDTO conexionDTO = ConfJson.readJSON();
		establecerParametros(conexionDTO);
	}
	
	private void establecerParametros(ConexionDTO conexionDTO)
	{
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://" + conexionDTO.getServidor() + ":" + conexionDTO.getPuerto() + "/" + conexionDTO.getBaseDatos(), conexionDTO.getUsuario(), conexionDTO.getClave());
			System.out.println("Conexion exitosa");
			conexionEstablecida = true;
		} catch (Exception e) {
			System.out.println("Conexion fallida");
			conexionEstablecida = false;
			System.out.println(e.getMessage());
		}
	}
	
	public Conexion(ConexionDTO conexionDTO)
	{
		establecerParametros(conexionDTO);
	}

	public static Conexion getConexion() {
		if (instancia == null) {
			instancia = new Conexion();
		}
		return instancia;
	}

	public Connection getSQLConexion() {
		return instancia.conexion;
	}

	public static void cerrarConexion() {
		instancia = null;
	}
	
	public static void reconectar()
	{
		cerrarConexion();
		instancia = new Conexion();
	}
	
}
