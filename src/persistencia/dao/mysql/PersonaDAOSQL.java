package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.PersonaDAO;
import util.Fechas;
import dto.LocalidadDTO;
import dto.PersonaDTO;
import dto.TipoContactoDTO;

public class PersonaDAOSQL implements PersonaDAO {
	private static final String insert = "INSERT INTO personas(idPersona, nombre, telefono, email, fechanacimiento, calle, altura, piso, depto, idLocalidad, idTipoContacto) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String update = "UPDATE personas SET nombre = ?, telefono = ?, email = ?, fechanacimiento = ?, calle = ?, altura = ?, piso = ?, depto = ?, idLocalidad = ?, idTipoContacto = ? WHERE idPersona = ?";
	private static final String delete = "DELETE FROM personas WHERE idPersona = ?";
	// private static final String readall = "SELECT * FROM personas";
	// private static final String getById = "SELECT * FROM personas WHERE
	// idPersona
	// = ?";
	private static final Conexion conexion = Conexion.getConexion();
	private static final String readAllWithJoins = "SELECT p.idPersona, p.nombre, p.telefono, p.email, p.fechanacimiento, p.calle, p.altura, p.piso, p.depto, "
			+ " l.idLocalidad, l.nombre as localidadNombre, t.idTipoContacto, t.nombre as TipoContactoNombre "
			+ "FROM personas as p INNER JOIN localidades as l ON p.idLocalidad = l.idLocalidad "
			+ "INNER JOIN tiposContacto as t ON p.idTipoContacto = t.idTipoContacto";
	private static final String getByIdWithJoins = "SELECT p.idPersona, p.nombre, p.telefono, p.email, p.fechanacimiento, p.calle, p.altura, p.piso, p.depto, "
			+ " l.idLocalidad, l.nombre as localidadNombre, t.idTipoContacto, t.nombre as TipoContactoNombre "
			+ "FROM personas as p INNER JOIN localidades as l ON p.idLocalidad = l.idLocalidad "
			+ "INNER JOIN tiposContacto as t ON p.idTipoContacto = t.idTipoContacto " + "where idPersona = ?";

	public boolean insert(PersonaDTO persona) {
		PreparedStatement statement;
		try {
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setInt(1, persona.getIdPersona());
			statement.setString(2, persona.getNombre());
			statement.setString(3, persona.getTelefono());
			statement.setString(4, persona.getEmail());
			statement.setString(5, Fechas.Fecha_a_String_MySQL(persona.getFechaNacimiento()));
			statement.setString(6, persona.getCalle());
			statement.setInt(7, persona.getAltura());
			statement.setString(8, persona.getPiso());
			statement.setString(9, persona.getDepto());
			statement.setInt(10, persona.getLocalidad().getIdLocalidad());
			statement.setInt(11, persona.getTipoContacto().getIdTipoContacto());
			if (statement.executeUpdate() > 0) // Si se ejecutó devuelvo true
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean update(PersonaDTO persona) {
		PreparedStatement statement;
		try {
			statement = conexion.getSQLConexion().prepareStatement(update);
			statement.setInt(11, persona.getIdPersona());
			statement.setString(1, persona.getNombre());
			statement.setString(2, persona.getTelefono());
			statement.setString(3, persona.getEmail());
			statement.setString(4, Fechas.Fecha_a_String_MySQL(persona.getFechaNacimiento()));
			statement.setString(5, persona.getCalle());
			statement.setInt(6, persona.getAltura());
			statement.setString(7, persona.getPiso());
			statement.setString(8, persona.getDepto());
			statement.setInt(9, persona.getLocalidad().getIdLocalidad());
			statement.setInt(10, persona.getTipoContacto().getIdTipoContacto());

			if (statement.executeUpdate() > 0) // Si se ejecut� devuelvo true
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean delete(PersonaDTO persona_a_eliminar) {
		PreparedStatement statement;
		int chequeoUpdate = 0;
		try {
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setString(1, Integer.toString(persona_a_eliminar.getIdPersona()));
			chequeoUpdate = statement.executeUpdate();
			if (chequeoUpdate > 0) // Si se ejecut� devuelvo true
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<PersonaDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<PersonaDTO> personas = new ArrayList<PersonaDTO>();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readAllWithJoins);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				LocalidadDTO localidad = new LocalidadDTO(resultSet.getInt("idLocalidad"),
						resultSet.getString("localidadNombre"));
				TipoContactoDTO tipoContacto = new TipoContactoDTO(resultSet.getInt("idTipoContacto"),
						resultSet.getString("tipoContactoNombre"));
				personas.add(new PersonaDTO(resultSet.getInt("idPersona"), resultSet.getString("Nombre"),
						resultSet.getString("Telefono"), resultSet.getString("Email"),
						resultSet.getDate("FechaNacimiento"), resultSet.getString("Calle"), resultSet.getInt("Altura"),
						resultSet.getString("Piso"), resultSet.getString("Depto"), localidad, tipoContacto));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return personas;
	}

	public PersonaDTO getById(PersonaDTO persona_a_obtener) {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		PersonaDTO persona = null;
		try {
			statement = conexion.getSQLConexion().prepareStatement(getByIdWithJoins);
			statement.setInt(1, persona_a_obtener.getIdPersona());
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				LocalidadDTO localidad = new LocalidadDTO(resultSet.getInt("idLocalidad"),
						resultSet.getString("localidadNombre"));
				TipoContactoDTO tipoContacto = new TipoContactoDTO(resultSet.getInt("idTipoContacto"),
						resultSet.getString("tipoContactoNombre"));
				persona = new PersonaDTO(resultSet.getInt("idPersona"), resultSet.getString("Nombre"),
						resultSet.getString("Telefono"), resultSet.getString("Email"),
						resultSet.getDate("FechaNacimiento"), resultSet.getString("Calle"), resultSet.getInt("Altura"),
						resultSet.getString("Piso"), resultSet.getString("Depto"), localidad, tipoContacto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return persona;
	}

}
