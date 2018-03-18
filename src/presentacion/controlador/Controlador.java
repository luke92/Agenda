package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import modelo.Agenda;
import presentacion.reportes.ReporteAgenda;
import presentacion.vista.VentanaPersona;
import presentacion.vista.Vista;
import dto.PersonaDTO;

public class Controlador implements ActionListener
{
		private Vista vista;
		private List<PersonaDTO> personas_en_tabla;
		private VentanaPersona ventanaPersona; 
		private Agenda agenda;
		
		public Controlador(Vista vista, Agenda agenda)
		{
			this.vista = vista;
			this.vista.getBtnAgregar().addActionListener(this);
			this.vista.getBtnEditar().addActionListener(this);
			this.vista.getBtnBorrar().addActionListener(this);
			this.vista.getBtnReporte().addActionListener(this);
			this.agenda = agenda;
			this.personas_en_tabla = null;
		}
		
		public void inicializar()
		{
			this.llenarTabla();
		}
		
		private void llenarTabla()
		{
			this.vista.getModelPersonas().setRowCount(0); //Para vaciar la tabla
			this.vista.getModelPersonas().setColumnCount(0);
			this.vista.getModelPersonas().setColumnIdentifiers(this.vista.getNombreColumnas());
			
			this.personas_en_tabla = agenda.obtenerPersonas();
			for (int i = 0; i < this.personas_en_tabla.size(); i ++)
			{
				Object[] fila = {this.personas_en_tabla.get(i).getNombre(), this.personas_en_tabla.get(i).getTelefono()};
				this.vista.getModelPersonas().addRow(fila);
			}
			this.vista.show();
		}
		
		public void actionPerformed(ActionEvent e) 
		{
			if(e.getSource() == this.vista.getBtnAgregar())
			{
				if(this.ventanaPersona == null) //Verificar si se abrio alguna vez la ventana para agregar persona
					this.ventanaPersona = new VentanaPersona(this,"Agregar",null);
				else
					this.ventanaPersona.toFront(); //Si la ventana esta atras y aprieto el boton Agregar se muestra la ventana.
			}
			else if(e.getSource() == this.vista.getBtnEditar())
			{
				int[] filas_seleccionadas = this.vista.getTablaPersonas().getSelectedRows();
				if(filas_seleccionadas.length == 1)
				{					
						if(this.ventanaPersona == null)
						{
							PersonaDTO persona_a_obtener = this.agenda.obtenerPersona(this.personas_en_tabla.get(filas_seleccionadas[0]));
							this.ventanaPersona = new VentanaPersona(this,"Editar",persona_a_obtener);
						}
						else
							this.ventanaPersona.toFront();
				}
			}
			else if(e.getSource() == this.vista.getBtnBorrar())
			{
				if(this.ventanaPersona == null)
				{
					int[] filas_seleccionadas = this.vista.getTablaPersonas().getSelectedRows();
					for (int fila:filas_seleccionadas)
					{
						this.agenda.borrarPersona(this.personas_en_tabla.get(fila));
					}
					
					this.llenarTabla();
				}
				else
					this.ventanaPersona.toFront();
			}
			else if(e.getSource() == this.vista.getBtnReporte())
			{				
				ReporteAgenda reporte = new ReporteAgenda(agenda.obtenerPersonas());
				reporte.mostrar();				
			}
			else if(e.getSource() == this.ventanaPersona.getBtnAgregarPersona())
			{
				PersonaDTO nuevaPersona = new PersonaDTO(0,this.ventanaPersona.getTxtNombre().getText(), ventanaPersona.getTxtTelefono().getText());
				this.agenda.agregarPersona(nuevaPersona);
				this.llenarTabla();
				this.ventanaPersona.dispose();
				this.ventanaPersona = null; //Se habilita abrir la ventana de agregar persona luego de que la misma se cierra
			}
			else if(e.getSource() == this.ventanaPersona.getBtnEditarPersona())
			{
				PersonaDTO editarPersona = new PersonaDTO(this.ventanaPersona.getIdPersona(),this.ventanaPersona.getTxtNombre().getText(), ventanaPersona.getTxtTelefono().getText());
				this.agenda.editarPersona(editarPersona);
				this.llenarTabla();
				this.ventanaPersona.dispose();
				this.ventanaPersona = null; //Se habilita abrir la ventana de agregar persona luego de que la misma se cierra
			}
			
			//Evitar abrir multiples instancias del boton agregar.
			if(this.ventanaPersona != null)
			{
				this.ventanaPersona.addWindowListener(new java.awt.event.WindowAdapter()
				{
				    @Override
				    public void windowClosing(java.awt.event.WindowEvent windowEvent) 
				    {
				        ventanaPersona = null; //Se habilita abrir la ventana de agregar persona luego de que la misma se cierra
				    }
				});
			}
		}
}
