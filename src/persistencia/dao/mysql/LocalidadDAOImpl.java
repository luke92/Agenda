package persistencia.dao.mysql;

import java.util.List;

import dto.LocalidadDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.LocalidadDAO;

public class LocalidadDAOImpl implements LocalidadDAO
{
	
	private static final String insert = "INSERT INTO localidades(idLocalidad, nombre) VALUES(?, ?)";
	private static final String update = "UPDATE localidades SET nombre = ? WHERE idLocalidad = ?";
	private static final String delete = "DELETE FROM localidades WHERE idLocalidad = ?";
	private static final String readall = "SELECT * FROM localidades";
	private static final String getById = "SELECT * FROM localidades WHERE idLocalidad = ?";
	private static final Conexion conexion = Conexion.getConexion();
	
	public boolean update(LocalidadDTO localidad_a_editar) {
		
		return false;
	}

	
	public boolean insert(LocalidadDTO localidad) {
		
		return false;
	}

	
	public boolean delete(LocalidadDTO localidad_a_eliminar) {
		
		return false;
	}

	
	public LocalidadDTO getById(LocalidadDTO localidad_a_obtener) {
		
		return null;
	}

	@Override
	public List<LocalidadDTO> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
