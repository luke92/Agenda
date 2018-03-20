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

import dto.TipoContactoDTO;
import persistencia.dao.mysql.TipoContactoDAOImpl;

public class VistaTiposContacto implements ActionListener
{
	private JFrame frame;
	private JTable tablaTiposContacto;
	private JButton btnAgregar;
	private JButton btnEditar;
	private JButton btnBorrar;
	private DefaultTableModel modelTiposContacto;
	private List<TipoContactoDTO> tiposContacto_en_tabla;
	private VentanaTiposContacto ventanaTiposContacto;
	private  String[] nombreColumnas = {"Descripcion"};

	public VistaTiposContacto() 
	{
		super();
		initialize();
		tiposContacto_en_tabla = null;
		this.getBtnAgregar().addActionListener(this);
		this.getBtnBorrar().addActionListener(this);
		this.getBtnEditar().addActionListener(this);
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
	
	public JTable getTablaTiposContacto()
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

	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == this.getBtnAgregar())
		{
			this.ventanaTiposContacto = new VentanaTiposContacto(this,"Agregar",null);
		}
		else if(e.getSource() == this.getBtnEditar())
		{
			int[] filas_seleccionadas = this.getTablaTiposContacto().getSelectedRows();
			if(filas_seleccionadas.length == 1)
			{					
					TipoContactoDTO tipoContacto_a_obtener = new TipoContactoDAOImpl().getById(this.tiposContacto_en_tabla.get(filas_seleccionadas[0]));
					this.ventanaTiposContacto = new VentanaTiposContacto(this,"Editar",tipoContacto_a_obtener);
			}
		}
		else if(e.getSource() == this.getBtnBorrar())
		{
			int[] filas_seleccionadas = this.getTablaTiposContacto().getSelectedRows();
			for (int fila:filas_seleccionadas)
			{
				new TipoContactoDAOImpl().delete(this.tiposContacto_en_tabla.get(fila));
			}
				
			this.llenarTabla();

		}
		else if(e.getSource() == this.ventanaTiposContacto.getBtnAgregarTipoContacto())
		{
			TipoContactoDTO nuevoTipoContacto = this.ventanaTiposContacto.getDatosTipoContacto();
			new TipoContactoDAOImpl().insert(nuevoTipoContacto);
			this.llenarTabla();
			this.ventanaTiposContacto.dispose();
		}
		else if(e.getSource() == this.ventanaTiposContacto.getBtnEditarTipoContacto())
		{
			TipoContactoDTO editarTipoContacto = this.ventanaTiposContacto.getDatosTipoContacto();
			new TipoContactoDAOImpl().update(editarTipoContacto);
			this.llenarTabla();
			this.ventanaTiposContacto.dispose();
		}
		
	}
}
