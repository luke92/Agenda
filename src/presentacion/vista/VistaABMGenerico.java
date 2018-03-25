package presentacion.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dto.ABM;
import dto.Accion;
import dto.LocalidadDTO;
import dto.TipoContactoDTO;
import persistencia.dao.mysql.LocalidadDAOSQL;
import persistencia.dao.mysql.TipoContactoDAOSQL;
import presentacion.controlador.Controlador;

public class VistaABMGenerico implements ActionListener {
	private JFrame frame;
	private JTable tabla;
	private JButton btnAgregar;
	private JButton btnEditar;
	private JButton btnBorrar;
	private DefaultTableModel model;
	private List<LocalidadDTO> localidades_en_tabla;
	private List<TipoContactoDTO> tiposContacto_en_tabla;
	private VentanaABMGenerico ventana;
	private String[] nombreColumnas = { "Descripcion" };
	private Controlador controlador;
	private ABM tipoABM;
	
	public VistaABMGenerico(Controlador padre, ABM abm) 
	{
		super();
		this.tipoABM = abm;
		initialize();
		localidades_en_tabla = null;
		tiposContacto_en_tabla = null;
		this.getBtnAgregar().addActionListener(this);
		this.getBtnBorrar().addActionListener(this);
		this.getBtnEditar().addActionListener(this);
		llenarTabla();
		controlador = padre;
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 340, 300);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 320, 262);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JScrollPane sp = new JScrollPane();
		sp.setBounds(10, 11, 300, 182);
		panel.add(sp);

		model = new DefaultTableModel(null, nombreColumnas);
		tabla = new JTable(model);

		tabla.getColumnModel().getColumn(0).setPreferredWidth(130);
		tabla.getColumnModel().getColumn(0).setResizable(false);

		sp.setViewportView(tabla);

		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(10, 228, 89, 23);
		panel.add(btnAgregar);

		btnEditar = new JButton("Editar");
		btnEditar.setBounds(109, 228, 89, 23);
		panel.add(btnEditar);

		btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(208, 228, 89, 23);
		panel.add(btnBorrar);
		
		frame.setTitle("ABM " + this.tipoABM);
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

	public DefaultTableModel getModel() {
		return model;
	}

	public JTable getTabla() {
		return tabla;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	private void llenarTabla() 
	{
		this.getModel().setRowCount(0); // Para vaciar la tabla
		this.getModel().setColumnCount(0);
		this.getModel().setColumnIdentifiers(this.getNombreColumnas());
		
		if(this.tipoABM == ABM.Localidades)
		{
			this.localidades_en_tabla = new LocalidadDAOSQL().readAll();
			for (int i = 0; i < this.localidades_en_tabla.size(); i++) 
			{
				Object[] fila = { this.localidades_en_tabla.get(i).getNombre() };
				this.getModel().addRow(fila);
			}
		}
		else
		{
			this.tiposContacto_en_tabla = new TipoContactoDAOSQL().readAll();
			for (int i = 0; i < this.tiposContacto_en_tabla.size(); i++) 
			{
				Object[] fila = { this.tiposContacto_en_tabla.get(i).getNombre() };
				this.getModel().addRow(fila);
			}
		}
	}

	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == this.getBtnAgregar()) 
		{
			if(this.tipoABM == ABM.Localidades)
				this.ventana = new VentanaABMGenerico(this, Accion.Agregar, new LocalidadDTO(0,""));
			else
				this.ventana = new VentanaABMGenerico(this, Accion.Agregar, new TipoContactoDTO(0,""));
				
		} 
		
		else if (e.getSource() == this.getBtnEditar()) 
		{
			int[] filas_seleccionadas = this.getTabla().getSelectedRows();
			if(this.tipoABM == ABM.Localidades)
			{
				if (filas_seleccionadas.length == 1) 
				{
					LocalidadDTO localidad_a_obtener = new LocalidadDAOSQL()
							.getById(this.localidades_en_tabla.get(filas_seleccionadas[0]));
					this.ventana = new VentanaABMGenerico(this, Accion.Editar, localidad_a_obtener);
				}
				else JOptionPane.showMessageDialog(null, "Seleccione una sola localidad");
			}
			else
			{
				if (filas_seleccionadas.length == 1) 
				{
					TipoContactoDTO tipoContacto_a_obtener = new TipoContactoDAOSQL()
							.getById(this.tiposContacto_en_tabla.get(filas_seleccionadas[0]));
					this.ventana = new VentanaABMGenerico(this, Accion.Editar, tipoContacto_a_obtener);
				}
				else JOptionPane.showMessageDialog(null, "Seleccione un tipo de contacto");
			}
		} 
		
		else if (e.getSource() == this.getBtnBorrar()) 
		{
			int[] filas_seleccionadas = this.getTabla().getSelectedRows();
			String mensajeConfirmar = "";
			if(this.tipoABM == ABM.Localidades)
			{
				if(filas_seleccionadas.length > 0)
				{
					if(filas_seleccionadas.length == 1) mensajeConfirmar = "la localidad seleccionada?";
					else mensajeConfirmar = "las localidades seleccionadas?";
					int dialogResult = JOptionPane.showConfirmDialog (null, "Desea borrar " + mensajeConfirmar,"Aviso",JOptionPane.YES_NO_OPTION);
					if(dialogResult == JOptionPane.YES_OPTION)
					
					for (int fila : filas_seleccionadas) 
					{
						boolean borrado = new LocalidadDAOSQL().delete(this.localidades_en_tabla.get(fila)); 
						if(!borrado)
						{
							JOptionPane.showMessageDialog(null, "Hay al menos un contacto viviendo en " + this.localidades_en_tabla.get(fila).getNombre(), "No se pudo borrar la localidad", JOptionPane.ERROR_MESSAGE);
						}
					}
					this.llenarTabla();
				}
				else JOptionPane.showMessageDialog(null, "Debe seleccionar al menos una localidad", "Aviso", JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				if(filas_seleccionadas.length > 0)
				{
					if(filas_seleccionadas.length == 1) mensajeConfirmar = "el tipo de contacto seleccionado?";
					else mensajeConfirmar = "los tipos de contacto seleccionados?";
					int dialogResult = JOptionPane.showConfirmDialog (null, "Desea borrar " + mensajeConfirmar,"Aviso",JOptionPane.YES_NO_OPTION);
					if(dialogResult == JOptionPane.YES_OPTION)
					
					for (int fila : filas_seleccionadas) 
					{
						boolean borrado = new TipoContactoDAOSQL().delete(this.tiposContacto_en_tabla.get(fila)); 
						if(!borrado)
						{
							JOptionPane.showMessageDialog(null, "Hay al menos un contacto catalogado como " + this.tiposContacto_en_tabla.get(fila).getNombre(), "No se pudo borrar el tipo de contacto", JOptionPane.ERROR_MESSAGE);
						}
					}
					this.llenarTabla();
				}
				else JOptionPane.showMessageDialog(null, "Debe seleccionar al menos un tipo de contacto", "Aviso", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
		else if (e.getSource() == this.ventana.getBtnAgregarABM()) 
		{
			if(this.ventana.datosCorrectos())
			{
				if(this.tipoABM == ABM.Localidades)
				{
					LocalidadDTO nuevaLocalidad = this.ventana.getDatosLocalidad();
					new LocalidadDAOSQL().insert(nuevaLocalidad);
				}
				else
				{
					TipoContactoDTO nuevoTipo = this.ventana.getDatosTipoContacto();
					new TipoContactoDAOSQL().insert(nuevoTipo);
				}
				this.llenarTabla();
				this.ventana.dispose();
			}
		}
		
		else if (e.getSource() == this.ventana.getBtnEditarABM()) 
		{
			if(this.ventana.datosCorrectos())
			{	
				if(this.tipoABM == ABM.Localidades)
				{
					LocalidadDTO editarLocalidad = this.ventana.getDatosLocalidad();
					new LocalidadDAOSQL().update(editarLocalidad);
				}
				else
				{
					TipoContactoDTO editarTipo = this.ventana.getDatosTipoContacto();
					new TipoContactoDAOSQL().update(editarTipo);
					
				}
				this.llenarTabla();
				controlador.inicializar();
				this.ventana.dispose();
			}
		}
	}
}
