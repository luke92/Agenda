package presentacion.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dto.ConexionDTO;
import persistencia.conexion.Conexion;
import persistencia.conexion.ConfJson;
import presentacion.controlador.Controlador;
import util.ExpReg;


public class VentanaConexion implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JPanel contentPane;
	private JTextField txtServidor;
	private JTextField txtPuerto;
	private JTextField txtUsuario;
	private JTextField txtContraseña;
	private JTextField txtBaseDatos;
	private JButton btnActualizar;
	private Controlador controlador;
	private JPanel panel;

	public VentanaConexion(Controlador controlador) 
	{
		super();
		this.controlador = controlador;
		inicializar();
		this.getBtnActualizar().addActionListener(this);
		this.frame.setVisible(true);
	}
	
	private void inicializar()
	{
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(100, 100, 350, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setBounds(10, 11, 340, 340);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNombre = new JLabel("Servidor");
		lblNombre.setBounds(10, 11, 113, 14);
		panel.add(lblNombre);

		JLabel lblPuerto = new JLabel("Puerto");
		lblPuerto.setBounds(10, 52, 113, 14);
		panel.add(lblPuerto);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(10, 93, 113, 14);
		panel.add(lblUsuario);
		
		JLabel lblClave = new JLabel("Clave");
		lblClave.setBounds(10, 134, 113, 14);
		panel.add(lblClave);

		JLabel lblBD = new JLabel("Base de Datos");
		lblBD.setBounds(10, 175, 113, 14);
		panel.add(lblBD);
		
		txtServidor = new JTextField();
		txtServidor.setBounds(133, 8, 164, 20);
		panel.add(txtServidor);
		txtServidor.setColumns(10);
		
		txtPuerto = new JTextField();
		txtPuerto.setBounds(133, 49, 164, 20);
		panel.add(txtPuerto);
		txtPuerto.setColumns(10);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(133, 90, 164, 20);
		panel.add(txtUsuario);
		txtServidor.setColumns(10);

		txtContraseña = new JTextField();
		txtContraseña.setBounds(133, 131, 164, 20);
		panel.add(txtContraseña);
		txtContraseña.setColumns(10);

		txtBaseDatos = new JTextField();
		txtBaseDatos.setBounds(133, 172, 164, 20);
		panel.add(txtBaseDatos);
		txtBaseDatos.setColumns(10);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(this.controlador);
		btnActualizar.setBounds(90, 250, 100, 23);
		panel.add(btnActualizar);
		
		this.frame.setTitle("Configurar conexión");
		
		inicializarCampos();
		
	}
	
	private void inicializarCampos()
	{
		ConexionDTO conexionDTO = ConfJson.readJSON();
		if(conexionDTO != null)
		{
			txtServidor.setText(conexionDTO.getServidor());
			txtPuerto.setText(conexionDTO.getPuerto());
			txtUsuario.setText(conexionDTO.getUsuario());
			txtContraseña.setText(conexionDTO.getClave());
			txtBaseDatos.setText(conexionDTO.getBaseDatos());
		}
	}
	
	public JTextField getTxtServidor() {
		return txtServidor;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JTextField getTxtPuerto() {
		return txtPuerto;
	}

	public JTextField getTxtUsuario() {
		return txtUsuario;
	}

	public JTextField getTxtContraseña() {
		return txtContraseña;
	}

	public JTextField getTxtBaseDatos() {
		return txtBaseDatos;
	}

	public JButton getBtnActualizar() 
	{
		return btnActualizar;
	}
	
	public JFrame getFrame()
	{
		return this.frame;
	}
	
	public boolean datosCorrectos()
	{
		String error = "";
		txtServidor.setText(txtServidor.getText().trim());
		txtPuerto.setText(txtPuerto.getText().trim());
		txtUsuario.setText(txtUsuario.getText().trim());
		txtContraseña.setText(txtContraseña.getText().trim());
		txtBaseDatos.setText(txtBaseDatos.getText().trim());
		
		if(txtServidor.getText().isEmpty())
			error += "-Debe ingresar un nombre de servidor o ip valido\n";
		
		if(!ExpReg.contieneSoloNumeros(txtPuerto.getText()))
		{
			error += "-Debe colocar un puerto valido\n";
		}
		
		if(!ExpReg.nombreUsuarioValido(txtUsuario.getText()))
		{
			error += "-Debe colocar un nombre de usuario valido\n";
		}
		
		if(txtContraseña.getText().isEmpty())
		{
			error += "-Debe colocar una contraseña\n";
		}
		
		if(txtBaseDatos.getText().isEmpty())
		{
			error += "-Debe colocar el nombre de la base de datos\n";
		}
		
		if(error != "")
		{
			JOptionPane.showMessageDialog(null, error);
			return false;
		}
		else
			return true;
		
	}
	
	public void show() {
		this.frame.setVisible(true);
	}
	
	public ConexionDTO getDatosConexion() 
	{
		String servidor = this.getTxtServidor().getText();
		String puerto = this.getTxtPuerto().getText();
		String usuario = this.getTxtUsuario().getText();
		String clave = this.getTxtContraseña().getText();
		String baseDatos = this.getTxtBaseDatos().getText();
		
		ConexionDTO conexion = new ConexionDTO(servidor, puerto, usuario, clave, baseDatos);
		
		return conexion;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == this.getBtnActualizar())
		{
			actionBtnActualizarConexion();
		}
	}
	
	@SuppressWarnings("static-access")
	private void actionBtnActualizarConexion()
	{
		if(this.datosCorrectos())
		{
			ConexionDTO conexionDTO = this.getDatosConexion();
			Conexion conexion = new Conexion(conexionDTO);
			if(conexion.conexionEstablecida)
			{
				ConfJson.writeJSON(conexionDTO);
				Conexion.reconectar();
				this.controlador.inicializar();
				this.frame.dispose();
			}
			else
			{
				JOptionPane.showMessageDialog(null, "No se pudo establecer conexion con el servidor", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	

}
