package presentacion.vista;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dto.Accion;
import dto.LocalidadDTO;
import dto.TipoContactoDTO;
import util.ExpReg;

@SuppressWarnings("serial")
public class VentanaABMGenerico extends JFrame {
	private JPanel contentPane;
	private int id;
	private JTextField txtNombre;
	private JButton btnAgregarABM;
	private JButton btnEditarABM;
	private ActionListener controlador;
	private JPanel panel;
	private String nombreABM;
	
	public VentanaABMGenerico(ActionListener controlador, Accion accion, LocalidadDTO localidad) 
	{
		super();
		this.controlador = controlador;
		inicializar();
		if (accion == Accion.Agregar)
		{
			this.setTitle("Agregar Localidad");
			inicializarAgregar(panel);
		}
			
		else
			inicializarEditar(panel, localidad);
		this.setVisible(true);
	}
	
	public VentanaABMGenerico(ActionListener controlador, Accion accion, TipoContactoDTO tipoContacto) 
	{
		super();
		this.controlador = controlador;
		inicializar();
		if (accion == Accion.Agregar)
		{
			inicializarAgregar(panel);
			this.setTitle("Agregar Tipo Contacto");
			nombreABM = "";
		}
		
		else
		{
			inicializarEditar(panel, tipoContacto);
			nombreABM = txtNombre.getText();
		}
		this.setVisible(true);
	}
	
	private void inicializar()
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 350, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setBounds(10, 11, 340, 90);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 11, 113, 14);
		panel.add(lblNombre);

		id = 0;

		txtNombre = new JTextField();
		txtNombre.setBounds(133, 8, 164, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);

	}
	
	private void inicializarAgregar(JPanel panel) 
	{
		btnAgregarABM = new JButton("Agregar");
		btnAgregarABM.addActionListener(this.controlador);
		btnAgregarABM.setBounds(208, 60, 89, 23);
		panel.add(btnAgregarABM);
	}

	private void inicializarEditar(JPanel panel, LocalidadDTO localidad) 
	{
		this.setTitle("Editar Localidad");
		id = localidad.getIdLocalidad();
		txtNombre.setText(localidad.getNombre());
		btnEditarABM = new JButton("Actualizar");
		btnEditarABM.addActionListener(this.controlador);
		btnEditarABM.setBounds(208, 60, 95, 23);
		panel.add(btnEditarABM);
		
	}
	
	private void inicializarEditar(JPanel panel, TipoContactoDTO tipoContacto) 
	{
		this.setTitle("Editar Tipo Contacto");
		id = tipoContacto.getIdTipoContacto();
		txtNombre.setText(tipoContacto.getNombre());
		btnEditarABM = new JButton("Actualizar");
		btnEditarABM.addActionListener(this.controlador);
		btnEditarABM.setBounds(208, 60, 95, 23);
		panel.add(btnEditarABM);
	}
	
	public int getId() 
	{
		return id;
	}

	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public JButton getBtnAgregarABM() 
	{
		return btnAgregarABM;
	}

	public JButton getBtnEditarABM() {
		return btnEditarABM;
	}

	public LocalidadDTO getDatosLocalidad() 
	{
		LocalidadDTO localidad = new LocalidadDTO(id, this.getTxtNombre().getText());
		return localidad;
	}
	
	public TipoContactoDTO getDatosTipoContacto()
	{
		TipoContactoDTO tipoContacto = new TipoContactoDTO(id, this.getTxtNombre().getText());
		return tipoContacto;
	}
	
	public boolean datosCorrectos()
	{
		String error = "";
		txtNombre.setText(txtNombre.getText().trim());
		
		if(!ExpReg.contieneLetrasNumerosyEspacios(txtNombre.getText()))
			error += "-Debe ingresar un nombre valido";
		
		if(txtNombre.getText().length() > 45)
			error += "-El nombre no puede tener mas de 45 caracteres";
		
		if(!txtNombre.getText().isEmpty())
				if(nombreABM.equals(txtNombre.getText()))
					error += "El nombre es igual al anterior";
		
		if(error != "")
		{
			JOptionPane.showMessageDialog(null, error);
			return false;
		}
		else
			return true;
		
	}
}
