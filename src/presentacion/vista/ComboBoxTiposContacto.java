package presentacion.vista;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import dto.TipoContactoDTO;
import persistencia.dao.mysql.TipoContactoDAOImpl;

public class ComboBoxTiposContacto extends JComboBox<TipoContactoDTO>
{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ComboBoxTiposContacto()
	{
		super();
		DefaultComboBoxModel<TipoContactoDTO> value = new DefaultComboBoxModel<TipoContactoDTO>();
		this.setModel(value);
		for(TipoContactoDTO tipoContacto : new TipoContactoDAOImpl().readAll())
		{
			value.addElement(tipoContacto);
		}
	}
	
	public TipoContactoDTO getTipoContacto()
	{
		return (TipoContactoDTO) this.getSelectedItem();
	}
}
