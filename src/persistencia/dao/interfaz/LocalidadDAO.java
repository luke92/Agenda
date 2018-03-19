package persistencia.dao.interfaz;

import java.util.List;

import dto.LocalidadDTO;

public interface LocalidadDAO 
{
	public boolean update(LocalidadDTO localidad_a_editar);
	
	public boolean insert(LocalidadDTO localidad);

	public boolean delete(LocalidadDTO localidad_a_eliminar);
	
	public LocalidadDTO getById(LocalidadDTO localidad_a_obtener);
	
	public List<LocalidadDTO> readAll();
}
