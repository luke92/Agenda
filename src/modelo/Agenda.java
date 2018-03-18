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
	
	public void editarPersona(PersonaDTO persona_a_editar)
	{
		persona.update(persona_a_editar);
	}
	
	public List<PersonaDTO> obtenerPersonas()
	{
		return persona.readAll();		
	}
	
	public PersonaDTO obtenerPersona(PersonaDTO persona_a_obtener)
	{
		return persona.getById(persona_a_obtener);
	}
	
}
