package presentacion.vista;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import dto.LocalidadDTO;
import persistencia.dao.mysql.LocalidadDAOSQL;

@SuppressWarnings("serial")
public class ComboBoxLocalidades extends JComboBox<LocalidadDTO> {

	public ComboBoxLocalidades() {
		super();
		DefaultComboBoxModel<LocalidadDTO> value = new DefaultComboBoxModel<LocalidadDTO>();
		this.setModel(value);
		for (LocalidadDTO localidad : new LocalidadDAOSQL().readAll()) {
			value.addElement(localidad);
		}
	}

	public LocalidadDTO getLocalidad() {
		return (LocalidadDTO) this.getSelectedItem();
	}
}
