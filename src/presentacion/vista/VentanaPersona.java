package presentacion.vista;


import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.jdatepicker.impl.JDatePickerImpl;

import dto.PersonaDTO;
import presentacion.controlador.Controlador;
import util.Fechas;

public class VentanaPersona extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private int idPersona;
	private JTextField txtNombre;
	private JTextField txtTelefono;
	private JTextField txtEmail;
	private JTextField txtFechaNacimiento;
	private JButton btnAgregarPersona;
	private JButton btnEditarPersona;
	private Controlador controlador;
	private JDatePickerImpl datePicker;
	
	public VentanaPersona(Controlador controlador, String accion, PersonaDTO persona) 
	{
		super();
		this.controlador = controlador;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 350, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 340, 320);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNombreYApellido = new JLabel("Nombre y apellido");
		lblNombreYApellido.setBounds(10, 11, 113, 14);
		panel.add(lblNombreYApellido);
		
		JLabel lblTelfono = new JLabel("Tel\u00E9fono");
		lblTelfono.setBounds(10, 52, 113, 14);
		panel.add(lblTelfono);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 93, 113, 14);
		panel.add(lblEmail);
		
		JLabel lblFechaNacimiento = new JLabel("Cumplea\u00f1os");
		lblFechaNacimiento.setBounds(10, 134, 113, 14);
		panel.add(lblFechaNacimiento);
		
		idPersona = 0;
		
		txtNombre = new JTextField();
		txtNombre.setBounds(133, 8, 164, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(133, 49, 164, 20);
		panel.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(133, 90, 164, 20);
		panel.add(txtEmail);
		txtEmail.setColumns(10);
		
		txtFechaNacimiento = new JTextField();
		txtFechaNacimiento.setBounds(133, 131, 164, 20);
		//panel.add(txtFechaNacimiento);
		txtFechaNacimiento.setColumns(10);
		
		JPanel pnlDatePicker = new JPanel();
		pnlDatePicker.setBounds(90, 125, 250, 40);
		panel.add(pnlDatePicker);
		datePicker = VentanaCalendario.getPickerToday();
		pnlDatePicker.add(datePicker);
		
		if(accion == "Agregar")
			inicializarAgregar(panel);
		else inicializarEditar(panel,persona);
		
		this.setVisible(true);
	}
	
	private void inicializarAgregar(JPanel panel)
	{	
		this.setTitle("Agregar Persona");
		btnAgregarPersona = new JButton("Agregar");
		btnAgregarPersona.addActionListener(this.controlador);
		btnAgregarPersona.setBounds(208, 200, 89, 23);
		panel.add(btnAgregarPersona);
	}
	
	private void inicializarEditar(JPanel panel, PersonaDTO persona)
	{
		this.setTitle("Editar Persona");
		
		idPersona = persona.getIdPersona();
		txtNombre.setText(persona.getNombre());
		txtTelefono.setText(persona.getTelefono());
		
		btnEditarPersona = new JButton("Actualizar");
		btnEditarPersona.addActionListener(this.controlador);
		btnEditarPersona.setBounds(208, 200, 95, 23);
		panel.add(btnEditarPersona);
	}
	
	public int getIdPersona()
	{
		return idPersona;
	}
	
	public JTextField getTxtNombre() 
	{
		return txtNombre;
	}

	public JTextField getTxtTelefono() 
	{
		return txtTelefono;
	}
	
	public JTextField getTxtEmail() 
	{
		return txtEmail;
	}
	
	public JTextField getTxtFechaNacimiento() 
	{
		return txtFechaNacimiento;
	}

	public JButton getBtnAgregarPersona() 
	{
		return btnAgregarPersona;
	}
	
	public JButton getBtnEditarPersona() 
	{
		return btnEditarPersona;
	}
	
	public PersonaDTO getDatosPersona()
	{
		String fecha = datePicker.getJFormattedTextField().getText().trim();
		Calendar cal = Fechas.String_a_Fecha(fecha);
		PersonaDTO persona = new PersonaDTO(idPersona,this.getTxtNombre().getText(), this.getTxtTelefono().getText(),this.getTxtEmail().getText(),cal);
		return persona;
	}
	
}

