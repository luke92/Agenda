package persistencia.dao.interfaz;

import java.util.List;

import dto.PersonaDTO;

public interface PersonaDAO 
{
	
	public boolean update(PersonaDTO persona_a_editar);
	
	public boolean insert(PersonaDTO persona);

	public boolean delete(PersonaDTO persona_a_eliminar);
	
	public PersonaDTO getById(PersonaDTO persona_a_obtener);
	
	public List<PersonaDTO> readAll();
}
