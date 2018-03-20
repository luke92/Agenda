package presentacion.vista;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

public class Vista
{
	private JFrame frame;
	private JTable tablaPersonas;
	private JButton btnAgregar;
	private JButton btnEditar;
	private JButton btnBorrar;
	private JButton btnReporte;
	private JButton btnABMLocalidades;
	private JButton btnABMTiposContacto;
	private DefaultTableModel modelPersonas;
	private  String[] nombreColumnas = {"Nombre y apellido","Tel\u00e9fono","Email","Cumplea\u00f1os","Tipo", "Domicilio"};

	public Vista() 
	{
		super();
		initialize();
	}


	private void initialize() 
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 790, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 780, 262);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane spPersonas = new JScrollPane();
		spPersonas.setBounds(10, 11, 760, 182);
		panel.add(spPersonas);
		
		modelPersonas = new DefaultTableModel(null,nombreColumnas);
		tablaPersonas = new JTable(modelPersonas);
		
		tablaPersonas.getColumnModel().getColumn(0).setPreferredWidth(130);
		tablaPersonas.getColumnModel().getColumn(0).setResizable(false);
		tablaPersonas.getColumnModel().getColumn(1).setPreferredWidth(130);
		tablaPersonas.getColumnModel().getColumn(1).setResizable(false);
		tablaPersonas.getColumnModel().getColumn(2).setPreferredWidth(120);
		tablaPersonas.getColumnModel().getColumn(2).setResizable(false);
		tablaPersonas.getColumnModel().getColumn(3).setPreferredWidth(120);
		tablaPersonas.getColumnModel().getColumn(3).setResizable(false);
		tablaPersonas.getColumnModel().getColumn(4).setPreferredWidth(120);
		tablaPersonas.getColumnModel().getColumn(4).setResizable(false);
		tablaPersonas.getColumnModel().getColumn(5).setPreferredWidth(200);
		tablaPersonas.getColumnModel().getColumn(5).setResizable(false);
		
		spPersonas.setViewportView(tablaPersonas);

		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(10, 228, 89, 23);
		panel.add(btnAgregar);
		
		btnEditar = new JButton("Editar");
		btnEditar.setBounds(109, 228, 89, 23);
		panel.add(btnEditar);
		
		btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(208, 228, 89, 23);
		panel.add(btnBorrar);
		
		btnReporte = new JButton("Reporte");
		btnReporte.setBounds(307, 228, 89, 23);
		panel.add(btnReporte);
		
		btnABMLocalidades = new JButton("ABM Localidades");
		btnABMLocalidades.setBounds(600, 228, 170, 23);
		panel.add(btnABMLocalidades);
		
		btnABMTiposContacto = new JButton("ABM Tipos de Contacto");
		btnABMTiposContacto.setBounds(410, 228, 170, 23);
		panel.add(btnABMTiposContacto);
		
		frame.setTitle("TP Inicial - AGENDA");
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
	
	public JButton getBtnReporte() 
	{
		return btnReporte;
	}
	
	public JButton getBtnABMLocalidades()
	{
		return btnABMLocalidades;
	}
	
	public JButton getBtnABMTiposContacto()
	{
		return btnABMTiposContacto;
	}
	
	public DefaultTableModel getModelPersonas() 
	{
		return modelPersonas;
	}
	
	public JTable getTablaPersonas()
	{
		return tablaPersonas;
	}

	public String[] getNombreColumnas() 
	{
		return nombreColumnas;
	}
}
