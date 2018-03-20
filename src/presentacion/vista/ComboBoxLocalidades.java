package presentacion.vista;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import dto.LocalidadDTO;
import persistencia.dao.mysql.LocalidadDAOImpl;

public class ComboBoxLocalidades extends JComboBox<LocalidadDTO>
{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ComboBoxLocalidades()
	{
		super();
		DefaultComboBoxModel<LocalidadDTO> value = new DefaultComboBoxModel<LocalidadDTO>();
		this.setModel(value);
		for(LocalidadDTO localidad : new LocalidadDAOImpl().readAll())
		{
			value.addElement(localidad);
		}
	}
	
	public LocalidadDTO getLocalidad()
	{
		return (LocalidadDTO) this.getSelectedItem();
	}
}
