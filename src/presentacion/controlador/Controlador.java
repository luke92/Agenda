package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import modelo.Agenda;
import persistencia.conexion.Conexion;
import persistencia.dao.mysql.LocalidadDAOSQL;
import persistencia.dao.mysql.TipoContactoDAOSQL;
import presentacion.reportes.ReporteAgenda;
import presentacion.vista.VentanaConexion;
import presentacion.vista.VentanaPersona;
import presentacion.vista.Vista;
import presentacion.vista.VistaABMGenerico;
import util.Fechas;
import dto.ABM;
import dto.Accion;
import dto.PersonaDTO;

public class Controlador implements ActionListener {
	public Vista vista;
	private List<PersonaDTO> personas_en_tabla;
	private VentanaPersona ventanaPersona;
	private VistaABMGenerico vistaABMLocalidades;
	private VistaABMGenerico vistaABMTipoContacto;
	private VentanaConexion ventanaConexion;
	private Agenda agenda;

	public Controlador(Vista vista, Agenda agenda) 
	{
		this.vista = vista;
		this.vista.getBtnAgregar().addActionListener(this);
		this.vista.getBtnEditar().addActionListener(this);
		this.vista.getBtnBorrar().addActionListener(this);
		this.vista.getBtnReporte().addActionListener(this);
		this.vista.getBtnABMLocalidades().addActionListener(this);
		this.vista.getBtnABMTiposContacto().addActionListener(this);
		this.vista.getBtnConexion().addActionListener(this);
		this.agenda = agenda;
		this.personas_en_tabla = null;

	}

	public void inicializar() 
	{
		if(!Conexion.conexionEstablecida)
		{
			this.ventanaConexion = new VentanaConexion(this);
		}
		else
			this.llenarTabla();
	}

	private void llenarTabla() 
	{
		this.vista.getModelPersonas().setRowCount(0); // Para vaciar la tabla
		this.vista.getModelPersonas().setColumnCount(0);
		this.vista.getModelPersonas().setColumnIdentifiers(this.vista.getNombreColumnas());

		this.personas_en_tabla = agenda.obtenerPersonas();
		for (int i = 0; i < this.personas_en_tabla.size(); i++) {
			Object[] fila = { this.personas_en_tabla.get(i).getNombre(), this.personas_en_tabla.get(i).getTelefono(),
					this.personas_en_tabla.get(i).getEmail(),
					Fechas.Fecha_a_String(this.personas_en_tabla.get(i).getFechaNacimiento()),
					this.personas_en_tabla.get(i).getTipoContacto().getNombre(),
					this.personas_en_tabla.get(i).getDomicilioCompleto() };
			this.vista.getModelPersonas().addRow(fila);
		}
		this.vista.show();
	}

	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == this.vista.getBtnAgregar()) 
		{
			actionBtnAgregar();
		} 
		
		else if (e.getSource() == this.vista.getBtnEditar()) 
		{
			actionBtnEditar();
		} 
		
		else if (e.getSource() == this.vista.getBtnBorrar()) 
		{
			actionBtnBorrar();
		} 
		
		else if (e.getSource() == this.vista.getBtnReporte()) 
		{
			actionBtnReporte();
		}

		else if (e.getSource() == this.vista.getBtnABMLocalidades()) 
		{
			// Verificar si se abrio alguna vez la ventana de abm localidades
			if (this.vistaABMLocalidades == null)
				this.vistaABMLocalidades = new VistaABMGenerico(this, ABM.Localidades);
			this.vistaABMLocalidades.show();
		}

		else if (e.getSource() == this.vista.getBtnABMTiposContacto()) 
		{
			// Verificar si se abrio alguna vez la ventana de abm localidades
			if (this.vistaABMTipoContacto == null)
				this.vistaABMTipoContacto = new VistaABMGenerico(this,ABM.TiposContacto);
			this.vistaABMTipoContacto.show();
		}
		
		else if (e.getSource() == this.vista.getBtnConexion())
		{
			if(this.ventanaConexion == null)
				this.ventanaConexion = new VentanaConexion(this);
			else this.ventanaConexion.show();
		}

		else if (e.getSource() == this.ventanaPersona.getBtnAgregarPersona()) 
		{
			actionBtnAgregarPersona();
		} 
		
		else if (e.getSource() == this.ventanaPersona.getBtnEditarPersona()) 
		{
			actionBtnEditarPersona();
		}

		// Evitar abrir multiples instancias del boton agregar.
		if (this.ventanaPersona != null) 
		{
			this.ventanaPersona.addWindowListener(new java.awt.event.WindowAdapter() 
			{
				@Override
				public void windowClosing(java.awt.event.WindowEvent windowEvent) 
				{
					// Se habilita abrir la ventana de agregar persona luego de
					// que la misma se cierra
					ventanaPersona = null;
				}
			});
		}
		
		if (this.ventanaConexion != null)
		{
			this.ventanaConexion.getFrame().addWindowListener(new java.awt.event.WindowAdapter() 
			{
				@Override
				public void windowClosing(java.awt.event.WindowEvent windowEvent) 
				{
					// Se habilita abrir la ventana de agregar persona luego de
					// que la misma se cierra
					ventanaConexion = null;
				}
			});
		}
	}
	
	private void actionBtnAgregar()
	{
		String error = "";
		if(new LocalidadDAOSQL().cantidad() == 0)
		{
			error += "Debe agregarse una Localidad\n";
		}
		
		if(new TipoContactoDAOSQL().cantidad() == 0)
		{
			error += "Debe agregarse un Tipo de contacto\n";
		}
		
		if(error != "")
			JOptionPane.showMessageDialog(null, error);
		else
		{
			// Verificar si se abrio alguna vez la ventana para agregar persona
			if (this.ventanaPersona == null)
			{
				this.ventanaPersona = new VentanaPersona(this, Accion.Agregar, null);
			}
			else
				// Si la ventana esta atras y aprieto el boton Agregar se
				// muestra la ventana.
				this.ventanaPersona.toFront();
		}
	}
	
	private void actionBtnEditar()
	{
		int[] filas_seleccionadas = this.vista.getTablaPersonas().getSelectedRows();
		if (filas_seleccionadas.length == 1) 
		{
			if (this.ventanaPersona == null) 
			{
				PersonaDTO persona_a_obtener = this.agenda.obtenerPersona(this.personas_en_tabla.get(filas_seleccionadas[0]));
				this.ventanaPersona = new VentanaPersona(this, Accion.Editar, persona_a_obtener);
			} 
			else this.ventanaPersona.toFront();
		}
		else JOptionPane.showMessageDialog(null, "Seleccione un solo registro");
	}
	
	private void actionBtnBorrar()
	{
		if (this.ventanaPersona == null) 
		{
			int[] filas_seleccionadas = this.vista.getTablaPersonas().getSelectedRows();
			if(filas_seleccionadas.length > 0)
			{
				String mensaje = "";
				if(filas_seleccionadas.length == 1) mensaje = "el contacto seleccionado?";
				else mensaje = "los contactos seleccionados?";
				int dialogResult = JOptionPane.showConfirmDialog (null, "Desea borrar " + mensaje,"Aviso",JOptionPane.YES_NO_OPTION);
				if(dialogResult == JOptionPane.YES_OPTION)
				{
					for (int fila : filas_seleccionadas) 
					{
						this.agenda.borrarPersona(this.personas_en_tabla.get(fila));	
					}
					this.llenarTabla();
				}
				
			}
			else JOptionPane.showMessageDialog(null, "Debe seleccionar al menos un registro");
		} 
		else this.ventanaPersona.toFront();
	}
	
	private void actionBtnAgregarPersona()
	{
		if(this.ventanaPersona.datosEstanCorrectos())
		{
			PersonaDTO nuevaPersona = this.ventanaPersona.getDatosPersona();
			this.agenda.agregarPersona(nuevaPersona);
			this.llenarTabla();
			this.ventanaPersona.dispose();
			// Se habilita abrir la ventana de agregar persona luego de que
			// la misma se cierra
			this.ventanaPersona = null;
		}
	}
	
	private void actionBtnEditarPersona()
	{
		if(this.ventanaPersona.datosEstanCorrectos())
		{
			PersonaDTO editarPersona = this.ventanaPersona.getDatosPersona();
			this.agenda.editarPersona(editarPersona);
			this.llenarTabla();
			this.ventanaPersona.dispose();
			// Se habilita abrir la ventana de agregar persona luego de que
			// la misma se cierra
			this.ventanaPersona = null;
		}
	}
	
	private void actionBtnReporte()
	{
		int filas = this.vista.getTablaPersonas().getRowCount();
		if(filas == 0)
		{
			JOptionPane.showMessageDialog(null, "Debe haber al menos un registro");
		}
		else
		{
			ReporteAgenda reporte = new ReporteAgenda(agenda.obtenerPersonas());
			reporte.mostrar();
		}
	}
}
