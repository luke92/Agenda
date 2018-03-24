package presentacion.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dto.LocalidadDTO;
import persistencia.dao.mysql.LocalidadDAOImpl;

public class VistaLocalidades implements ActionListener {
	private JFrame frame;
	private JTable tablaLocalidades;
	private JButton btnAgregar;
	private JButton btnEditar;
	private JButton btnBorrar;
	private DefaultTableModel modelLocalidades;
	private List<LocalidadDTO> localidades_en_tabla;
	private VentanaLocalidades ventanaLocalidades;
	private String[] nombreColumnas = { "Descripcion" };

	public VistaLocalidades() {
		super();
		initialize();
		localidades_en_tabla = null;
		this.getBtnAgregar().addActionListener(this);
		this.getBtnBorrar().addActionListener(this);
		this.getBtnEditar().addActionListener(this);
		llenarTabla();

	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 340, 300);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 320, 262);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JScrollPane spLocalidades = new JScrollPane();
		spLocalidades.setBounds(10, 11, 300, 182);
		panel.add(spLocalidades);

		modelLocalidades = new DefaultTableModel(null, nombreColumnas);
		tablaLocalidades = new JTable(modelLocalidades);

		tablaLocalidades.getColumnModel().getColumn(0).setPreferredWidth(130);
		tablaLocalidades.getColumnModel().getColumn(0).setResizable(false);

		spLocalidades.setViewportView(tablaLocalidades);

		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(10, 228, 89, 23);
		panel.add(btnAgregar);

		btnEditar = new JButton("Editar");
		btnEditar.setBounds(109, 228, 89, 23);
		panel.add(btnEditar);

		btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(208, 228, 89, 23);
		panel.add(btnBorrar);

		frame.setTitle("ABM Localidades");
	}

	public void show() {
		this.frame.setVisible(true);
	}

	public JButton getBtnAgregar() {
		return btnAgregar;
	}

	public JButton getBtnEditar() {
		return btnEditar;
	}

	public JButton getBtnBorrar() {
		return btnBorrar;
	}

	public DefaultTableModel getModelLocalidades() {
		return modelLocalidades;
	}

	public JTable getTablaLocalidades() {
		return tablaLocalidades;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	private void llenarTabla() {
		this.getModelLocalidades().setRowCount(0); // Para vaciar la tabla
		this.getModelLocalidades().setColumnCount(0);
		this.getModelLocalidades().setColumnIdentifiers(this.getNombreColumnas());

		this.localidades_en_tabla = new LocalidadDAOImpl().readAll();
		for (int i = 0; i < this.localidades_en_tabla.size(); i++) {
			Object[] fila = { this.localidades_en_tabla.get(i).getNombre(),

			};
			this.getModelLocalidades().addRow(fila);
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.getBtnAgregar()) {
			this.ventanaLocalidades = new VentanaLocalidades(this, "Agregar", null);

		} else if (e.getSource() == this.getBtnEditar()) {
			int[] filas_seleccionadas = this.getTablaLocalidades().getSelectedRows();
			if (filas_seleccionadas.length == 1) {
				LocalidadDTO localidad_a_obtener = new LocalidadDAOImpl()
						.getById(this.localidades_en_tabla.get(filas_seleccionadas[0]));
				this.ventanaLocalidades = new VentanaLocalidades(this, "Editar", localidad_a_obtener);
			}
		} else if (e.getSource() == this.getBtnBorrar()) {
			int[] filas_seleccionadas = this.getTablaLocalidades().getSelectedRows();
			for (int fila : filas_seleccionadas) {
				new LocalidadDAOImpl().delete(this.localidades_en_tabla.get(fila));
			}

			this.llenarTabla();

		} else if (e.getSource() == this.ventanaLocalidades.getBtnAgregarLocalidad()) {
			LocalidadDTO nuevaLocalidad = this.ventanaLocalidades.getDatosLocalidad();
			new LocalidadDAOImpl().insert(nuevaLocalidad);
			this.llenarTabla();
			this.ventanaLocalidades.dispose();
		} else if (e.getSource() == this.ventanaLocalidades.getBtnEditarLocalidad()) {
			LocalidadDTO editarLocalidad = this.ventanaLocalidades.getDatosLocalidad();
			new LocalidadDAOImpl().update(editarLocalidad);
			this.llenarTabla();
			this.ventanaLocalidades.dispose();
		}
	}
}
