package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.TipoContactoDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.TipoContactoDAO;

public class TipoContactoDAOSQL implements TipoContactoDAO {
	private static final String insert = "INSERT INTO tiposContacto (idTipoContacto, nombre) VALUES(?, ?)";
	private static final String update = "UPDATE tiposContacto SET nombre = ? WHERE idTipoContacto = ?";
	private static final String delete = "DELETE FROM tiposContacto WHERE idTipoContacto = ?";
	private static final String readall = "SELECT * FROM tiposContacto";
	private static final String getById = "SELECT * FROM tiposContacto WHERE idTipoContacto = ?";
	private static final String count = "SELECT COUNT(nombre) as cantidad FROM tiposContacto";
	private static final Conexion conexion = Conexion.getConexion();

	public boolean update(TipoContactoDTO tipoContacto_a_editar) {
		PreparedStatement statement;
		try {
			statement = conexion.getSQLConexion().prepareStatement(update);
			statement.setInt(2, tipoContacto_a_editar.getIdTipoContacto());
			statement.setString(1, tipoContacto_a_editar.getNombre());

			if (statement.executeUpdate() > 0) // Si se ejecutó devuelvo true
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean insert(TipoContactoDTO tipoContacto) {
		PreparedStatement statement;
		try {
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setInt(1, tipoContacto.getIdTipoContacto());
			statement.setString(2, tipoContacto.getNombre());

			if (statement.executeUpdate() > 0) // Si se ejecutó devuelvo true
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(TipoContactoDTO tipoContacto_a_eliminar) {
		PreparedStatement statement;
		int chequeoUpdate = 0;
		try {
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setInt(1, tipoContacto_a_eliminar.getIdTipoContacto());
			chequeoUpdate = statement.executeUpdate();
			if (chequeoUpdate > 0) // Si se ejecut� devuelvo true
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public TipoContactoDTO getById(TipoContactoDTO tipoContacto_a_obtener) {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		TipoContactoDTO tipoContacto = null;
		try {
			statement = conexion.getSQLConexion().prepareStatement(getById);
			statement.setInt(1, tipoContacto_a_obtener.getIdTipoContacto());
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				tipoContacto = new TipoContactoDTO(resultSet.getInt("idTipoContacto"), resultSet.getString("nombre"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tipoContacto;
	}

	@Override
	public List<TipoContactoDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<TipoContactoDTO> tipoContactos = new ArrayList<TipoContactoDTO>();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				TipoContactoDTO tipoContacto = new TipoContactoDTO(resultSet.getInt("idTipoContacto"),
						resultSet.getString("nombre"));
				tipoContactos.add(tipoContacto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tipoContactos;
	}
	
	public int cantidad()
	{
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		int cantidad = 0;
		try {
			statement = conexion.getSQLConexion().prepareStatement(count);
			resultSet = statement.executeQuery();

			while (resultSet.next()) 
			{
				cantidad = resultSet.getInt("cantidad");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cantidad;
	}

}
