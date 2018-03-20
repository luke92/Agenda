package presentacion.vista;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dto.TipoContactoDTO;

public class VentanaTiposContacto extends JFrame 
{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private int idTipoContacto;
	private JTextField txtNombre;
	private JButton btnAgregarTipoContacto;
	private JButton btnEditarTipoContacto;
	private ActionListener controlador;
	
	public VentanaTiposContacto(ActionListener controlador, String accion, TipoContactoDTO tipoContacto) 
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
		
		idTipoContacto = 0;
		
		txtNombre = new JTextField();
		txtNombre.setBounds(133, 8, 164, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		if(accion == "Agregar")
			inicializarAgregar(panel);
		else inicializarEditar(panel,tipoContacto);
		
		this.setVisible(true);
	}
	
	private void inicializarAgregar(JPanel panel)
	{	
		this.setTitle("Agregar Tipo Contacto");
		
		btnAgregarTipoContacto = new JButton("Agregar");
		btnAgregarTipoContacto.addActionListener(this.controlador);
		btnAgregarTipoContacto.setBounds(208, 60, 89, 23);
		
		panel.add(btnAgregarTipoContacto);
	}
	
	private void inicializarEditar(JPanel panel, TipoContactoDTO tipoContacto)
	{
		this.setTitle("Editar Tipo Contacto");
		
		idTipoContacto = tipoContacto.getIdTipoContacto();
		txtNombre.setText(tipoContacto.getNombre());
		btnEditarTipoContacto = new JButton("Actualizar");
		btnEditarTipoContacto.addActionListener(this.controlador);
		btnEditarTipoContacto.setBounds(208, 60, 95, 23);
		panel.add(btnEditarTipoContacto);
	}
	
	public int getIdTipoContacto()
	{
		return idTipoContacto;
	}
	
	public JTextField getTxtNombre() 
	{
		return txtNombre;
	}

	public JButton getBtnAgregarTipoContacto() 
	{
		return btnAgregarTipoContacto;
	}
	
	public JButton getBtnEditarTipoContacto() 
	{
		return btnEditarTipoContacto;
	}
	
	public TipoContactoDTO getDatosTipoContacto()
	{
		TipoContactoDTO tipoContacto = new TipoContactoDTO
				(idTipoContacto,this.getTxtNombre().getText());
		return tipoContacto;
	}
}
