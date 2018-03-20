package presentacion.vista;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dto.LocalidadDTO;
import persistencia.dao.mysql.LocalidadDAOImpl;

public class VistaLocalidades 
{
	private JFrame frame;
	private JTable tablaLocalidades;
	private JButton btnAgregar;
	private JButton btnEditar;
	private JButton btnBorrar;
	private DefaultTableModel modelLocalidades;
	private List<LocalidadDTO> localidades_en_tabla;
	private  String[] nombreColumnas = {"Descripcion"};

	public VistaLocalidades() 
	{
		super();
		initialize();
		localidades_en_tabla = null;
		llenarTabla();
	}


	private void initialize() 
	{
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
		
		modelLocalidades = new DefaultTableModel(null,nombreColumnas);
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
	
	public void show()
	{
		this.frame.setVisible(true);
	}
	
	public JButton getBtnAgregar() 
	{
		return btnAgregar;
	}
	
	public JButton getBtnEditar()
	{
		return btnEditar;
	}

	public JButton getBtnBorrar() 
	{
		return btnBorrar;
	}
	
	public DefaultTableModel getModelLocalidades() 
	{
		return modelLocalidades;
	}
	
	public JTable getTablaPersonas()
	{
		return tablaLocalidades;
	}

	public String[] getNombreColumnas() 
	{
		return nombreColumnas;
	}
	
	private void llenarTabla()
	{
		this.getModelLocalidades().setRowCount(0); //Para vaciar la tabla
		this.getModelLocalidades().setColumnCount(0);
		this.getModelLocalidades().setColumnIdentifiers(this.getNombreColumnas());
		
		this.localidades_en_tabla = new LocalidadDAOImpl().readAll();
		for (int i = 0; i < this.localidades_en_tabla.size(); i ++)
		{
			Object[] fila = 
			{
				this.localidades_en_tabla.get(i).getNombre(), 
				
			};
			this.getModelLocalidades().addRow(fila);
		}
	}
}
