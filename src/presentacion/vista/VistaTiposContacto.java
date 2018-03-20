package presentacion.vista;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dto.TipoContactoDTO;
import persistencia.dao.mysql.TipoContactoDAOImpl;

public class VistaTiposContacto 
{
	private JFrame frame;
	private JTable tablaTiposContacto;
	private JButton btnAgregar;
	private JButton btnEditar;
	private JButton btnBorrar;
	private DefaultTableModel modelTiposContacto;
	private List<TipoContactoDTO> tiposContacto_en_tabla;
	private  String[] nombreColumnas = {"Descripcion"};

	public VistaTiposContacto() 
	{
		super();
		initialize();
		tiposContacto_en_tabla = null;
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
		
		JScrollPane spTiposContacto = new JScrollPane();
		spTiposContacto.setBounds(10, 11, 300, 182);
		panel.add(spTiposContacto);
		
		modelTiposContacto = new DefaultTableModel(null,nombreColumnas);
		tablaTiposContacto = new JTable(modelTiposContacto);
		
		tablaTiposContacto.getColumnModel().getColumn(0).setPreferredWidth(130);
		tablaTiposContacto.getColumnModel().getColumn(0).setResizable(false);
		
		spTiposContacto.setViewportView(tablaTiposContacto);

		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(10, 228, 89, 23);
		panel.add(btnAgregar);
		
		btnEditar = new JButton("Editar");
		btnEditar.setBounds(109, 228, 89, 23);
		panel.add(btnEditar);
		
		btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(208, 228, 89, 23);
		panel.add(btnBorrar);
		
		frame.setTitle("ABM Tipos de Contacto");
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
	
	public DefaultTableModel getModelTiposContacto() 
	{
		return modelTiposContacto;
	}
	
	public JTable getTablaPersonas()
	{
		return tablaTiposContacto;
	}

	public String[] getNombreColumnas() 
	{
		return nombreColumnas;
	}
	
	private void llenarTabla()
	{
		this.getModelTiposContacto().setRowCount(0); //Para vaciar la tabla
		this.getModelTiposContacto().setColumnCount(0);
		this.getModelTiposContacto().setColumnIdentifiers(this.getNombreColumnas());
		
		this.tiposContacto_en_tabla = new TipoContactoDAOImpl().readAll();
		for (int i = 0; i < this.tiposContacto_en_tabla.size(); i ++)
		{
			Object[] fila = 
			{
				this.tiposContacto_en_tabla.get(i).getNombre(), 
				
			};
			this.getModelTiposContacto().addRow(fila);
		}
	}
}
