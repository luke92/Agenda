package presentacion.vista;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import dto.TipoContactoDTO;
import persistencia.dao.mysql.TipoContactoDAOSQL;

@SuppressWarnings("serial")
public class ComboBoxTiposContacto extends JComboBox<TipoContactoDTO> {

	public ComboBoxTiposContacto() {
		super();
		DefaultComboBoxModel<TipoContactoDTO> value = new DefaultComboBoxModel<TipoContactoDTO>();
		this.setModel(value);
		for (TipoContactoDTO tipoContacto : new TipoContactoDAOSQL().readAll()) {
			value.addElement(tipoContacto);
		}
	}

	public TipoContactoDTO getTipoContacto() {
		return (TipoContactoDTO) this.getSelectedItem();
	}
}
