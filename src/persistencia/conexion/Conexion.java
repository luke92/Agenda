package persistencia.conexion;

import java.sql.Connection;
import java.sql.DriverManager;

import dto.ConexionDTO;

public class Conexion 
{
	public static Conexion instancia;
	private Connection conexion;
	public static ConexionDTO conexionDTO;
	public static boolean conexionExitosa;
	
	public Conexion() 
	{
		conexionDTO = ConfJson.readJSON();
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://" + conexionDTO.getServidor() + ":" + conexionDTO.getPuerto() + "/" + conexionDTO.getBaseDatos(), conexionDTO.getUsuario(), conexionDTO.getClave());
			System.out.println("Conexion exitosa");
			conexionExitosa = true;
		} catch (Exception e) {
			System.out.println("Conexion fallida");
			conexionExitosa = false;
		}
	}

	public static Conexion getConexion() {
		if (instancia == null) {
			instancia = new Conexion();
		}
		return instancia;
	}

	public Connection getSQLConexion() {
		return conexion;
	}

	public void cerrarConexion() {
		instancia = null;
	}
	
	public static void reconectar()
	{
		instancia = null;
		instancia = new Conexion();
	}
}
