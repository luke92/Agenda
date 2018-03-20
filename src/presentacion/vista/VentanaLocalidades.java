package presentacion.vista;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dto.LocalidadDTO;

public class VentanaLocalidades extends JFrame 
{
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private int idLocalidad;
	private JTextField txtNombre;
	private JButton btnAgregarLocalidad;
	private JButton btnEditarLocalidad;
	private ActionListener controlador;
	
	
	public VentanaLocalidades(ActionListener controlador, String accion, LocalidadDTO localidad) 
	{
		super();
		this.controlador = controlador;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 350, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 340, 90);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 11, 113, 14);
		panel.add(lblNombre);
		
		idLocalidad = 0;
		
		txtNombre = new JTextField();
		txtNombre.setBounds(133, 8, 164, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		if(accion == "Agregar")
			inicializarAgregar(panel);
		else inicializarEditar(panel,localidad);
		
		this.setVisible(true);
	}
	
	private void inicializarAgregar(JPanel panel)
	{	
		this.setTitle("Agregar Localidad");
		
		btnAgregarLocalidad = new JButton("Agregar");
		btnAgregarLocalidad.addActionListener(this.controlador);
		btnAgregarLocalidad.setBounds(208, 60, 89, 23);
		
		panel.add(btnAgregarLocalidad);
	}
	
	private void inicializarEditar(JPanel panel, LocalidadDTO localidad)
	{
		this.setTitle("Editar Localidad");
		
		idLocalidad = localidad.getIdLocalidad();
		txtNombre.setText(localidad.getNombre());
		btnEditarLocalidad = new JButton("Actualizar");
		btnEditarLocalidad.addActionListener(this.controlador);
		btnEditarLocalidad.setBounds(208, 60, 95, 23);
		panel.add(btnEditarLocalidad);
	}
	
	public int getIdLocalidad()
	{
		return idLocalidad;
	}
	
	public JTextField getTxtNombre() 
	{
		return txtNombre;
	}

	public JButton getBtnAgregarLocalidad() 
	{
		return btnAgregarLocalidad;
	}
	
	public JButton getBtnEditarLocalidad() 
	{
		return btnEditarLocalidad;
	}
	
	public LocalidadDTO getDatosLocalidad()
	{
		LocalidadDTO tipoContacto = new LocalidadDTO
				(idLocalidad,this.getTxtNombre().getText());
		return tipoContacto;
	}
}
