package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import modelo.Agenda;
import presentacion.reportes.ReporteAgenda;
import presentacion.vista.VentanaPersona;
import presentacion.vista.Vista;
import presentacion.vista.VistaLocalidades;
import presentacion.vista.VistaTiposContacto;
import util.ExpReg;
import util.Fechas;
import dto.PersonaDTO;

public class Controlador implements ActionListener {
	private Vista vista;
	private List<PersonaDTO> personas_en_tabla;
	private VentanaPersona ventanaPersona;
	private VistaLocalidades vistaLocalidades;
	private VistaTiposContacto vistaTiposContacto;
	private Agenda agenda;

	public Controlador(Vista vista, Agenda agenda) {
		this.vista = vista;
		this.vista.getBtnAgregar().addActionListener(this);
		this.vista.getBtnEditar().addActionListener(this);
		this.vista.getBtnBorrar().addActionListener(this);
		this.vista.getBtnReporte().addActionListener(this);
		this.vista.getBtnABMLocalidades().addActionListener(this);
		this.vista.getBtnABMTiposContacto().addActionListener(this);
		this.agenda = agenda;
		this.personas_en_tabla = null;
	}

	public void inicializar() {
		this.llenarTabla();
	}

	private void llenarTabla() {
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
			ReporteAgenda reporte = new ReporteAgenda(agenda.obtenerPersonas());
			reporte.mostrar();
		}

		else if (e.getSource() == this.vista.getBtnABMLocalidades()) 
		{
			// Verificar si se abrio alguna vez la ventana de abm localidades
			if (this.vistaLocalidades == null)
				this.vistaLocalidades = new VistaLocalidades();
			this.vistaLocalidades.show();
		}

		else if (e.getSource() == this.vista.getBtnABMTiposContacto()) 
		{
			// Verificar si se abrio alguna vez la ventana de abm localidades
			if (this.vistaTiposContacto == null)
				this.vistaTiposContacto = new VistaTiposContacto();
			this.vistaTiposContacto.show();
		}

		else if (e.getSource() == this.ventanaPersona.getBtnAgregarPersona()) 
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
		
		else if (e.getSource() == this.ventanaPersona.getBtnEditarPersona()) 
		{
			PersonaDTO editarPersona = this.ventanaPersona.getDatosPersona();
			if (!ExpReg.mailValido(editarPersona.getEmail()))
				JOptionPane.showMessageDialog(null, "Coloque un mail valido");
			else {
				this.agenda.editarPersona(editarPersona);
				this.llenarTabla();
				this.ventanaPersona.dispose();
				// Se habilita abrir la ventana de agregar persona luego de que
				// la misma se cierra
				this.ventanaPersona = null;
			}
		}

		// Evitar abrir multiples instancias del boton agregar.
		if (this.ventanaPersona != null) 
		{
			this.ventanaPersona.addWindowListener(new java.awt.event.WindowAdapter() {
				@Override
				public void windowClosing(java.awt.event.WindowEvent windowEvent) {
					// Se habilita abrir la ventana de agregar persona luego de
					// que la misma se cierra
					ventanaPersona = null;
				}
			});
		}

	}
	
	private void actionBtnAgregar()
	{
		// Verificar si se abrio alguna vez la ventana para agregar persona
		if (this.ventanaPersona == null)
			this.ventanaPersona = new VentanaPersona(this, "Agregar", null);
		else
			// Si la ventana esta atras y aprieto el boton Agregar se
			// muestra la ventana.
			this.ventanaPersona.toFront();
	}
	
	private void actionBtnEditar()
	{
		int[] filas_seleccionadas = this.vista.getTablaPersonas().getSelectedRows();
		if (filas_seleccionadas.length == 1) 
		{
			if (this.ventanaPersona == null) 
			{
				PersonaDTO persona_a_obtener = this.agenda.obtenerPersona(this.personas_en_tabla.get(filas_seleccionadas[0]));
				this.ventanaPersona = new VentanaPersona(this, "Editar", persona_a_obtener);
			} 
			else this.ventanaPersona.toFront();
		}
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
			
			
		} 
		else this.ventanaPersona.toFront();
	}
}
