package modelo;

import java.util.List;
import dto.PersonaDTO;
import persistencia.dao.interfaz.PersonaDAO;
import persistencia.dao.mysql.PersonaDAOImpl;


public class Agenda 
{
	private PersonaDAO persona;	
	
	public Agenda()
	{
		persona = new PersonaDAOImpl();
	}
	
	public void agregarPersona(PersonaDTO nuevaPersona)
	{
		persona.insert(nuevaPersona);
	}

	public void borrarPersona(PersonaDTO persona_a_eliminar) 
	{
		persona.delete(persona_a_eliminar);
	}
	
	public List<PersonaDTO> obtenerPersonas()
	{
		return persona.readAll();		
	}
	
}
