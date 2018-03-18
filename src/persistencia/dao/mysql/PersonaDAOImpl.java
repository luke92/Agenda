package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.PersonaDAO;
import dto.PersonaDTO;

public class PersonaDAOImpl implements PersonaDAO
{
	private static final String insert = "INSERT INTO personas(idPersona, nombre, telefono) VALUES(?, ?, ?)";
	private static final String update = "UPDATE personas SET nombre = ?, telefono = ? WHERE idPersona = ?";
	private static final String delete = "DELETE FROM personas WHERE idPersona = ?";
	private static final String readall = "SELECT * FROM personas";
	private static final String getById = "SELECT * FROM personas WHERE idPersona = ?";
	private static final Conexion conexion = Conexion.getConexion();
	
	public boolean insert(PersonaDTO persona)
	{
		PreparedStatement statement;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setInt(1, persona.getIdPersona());
			statement.setString(2, persona.getNombre());
			statement.setString(3, persona.getTelefono());
			if(statement.executeUpdate() > 0) //Si se ejecutÃ³ devuelvo true
				return true;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally //Se ejecuta siempre
		{
			conexion.cerrarConexion();
		}
		return false;
	}
	
	public boolean update(PersonaDTO persona) 
	{
		PreparedStatement statement;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(update);
			statement.setInt(3, persona.getIdPersona());
			statement.setString(1, persona.getNombre());
			statement.setString(2, persona.getTelefono());
			if(statement.executeUpdate() > 0) //Si se ejecutá devuelvo true
				return true;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally //Se ejecuta siempre
		{
			conexion.cerrarConexion();
		}
		return false;
	}
	
	public boolean delete(PersonaDTO persona_a_eliminar)
	{
		PreparedStatement statement;
		int chequeoUpdate=0;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setString(1, Integer.toString(persona_a_eliminar.getIdPersona()));
			chequeoUpdate = statement.executeUpdate();
			if(chequeoUpdate > 0) //Si se ejecutá devuelvo true
				return true;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally //Se ejecuta siempre
		{
			conexion.cerrarConexion();
		}
		return false;
	}
	
	public List<PersonaDTO> readAll()
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<PersonaDTO> personas = new ArrayList<PersonaDTO>();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			
			while(resultSet.next())
			{
				personas.add(new PersonaDTO(resultSet.getInt("idPersona"), resultSet.getString("Nombre"), resultSet.getString("Telefono"),null,null));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally //Se ejecuta siempre
		{
			conexion.cerrarConexion();
		}
		return personas;
	}

	public PersonaDTO getById(PersonaDTO persona_a_obtener) 
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		PersonaDTO persona = null;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(getById);
			statement.setString(1, Integer.toString(persona_a_obtener.getIdPersona()));
			resultSet = statement.executeQuery();
			
			while(resultSet.next())
			{
				persona = new PersonaDTO(resultSet.getInt("idPersona"), resultSet.getString("Nombre"), resultSet.getString("Telefono"),null,null);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally //Se ejecuta siempre
		{
			conexion.cerrarConexion();
		}
		return persona;
	}
	
}
