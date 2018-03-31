package presentacion.reportes;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import util.Fechas;
import dto.PersonaDTO;
import dto.PersonaReporte;

public class ReporteAgenda {
	private JasperReport reporte;
	private JasperViewer reporteViewer;
	private JasperPrint reporteLleno;

	// Recibe la lista de personas para armar el reporte
	public ReporteAgenda(List<PersonaDTO> personas) 
	{
		// Hardcodeado
		List<PersonaReporte> personasReporte = obtenerListadoPersonasReporte(personas);
		Map<String, Object> parametersMap = new HashMap<String, Object>();
		parametersMap.put("Fecha", new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
		try {

			this.reporte = (JasperReport) JRLoader
					.loadObjectFromFile("reportes" + File.separatorChar + "ReporteAgenda.jasper");
			this.reporteLleno = JasperFillManager.fillReport(this.reporte, parametersMap,
					new JRBeanCollectionDataSource(personasReporte));
		} catch (JRException ex) {
			ex.printStackTrace();
		}
	}

	public void mostrar() {
		this.reporteViewer = new JasperViewer(this.reporteLleno, false);
		this.reporteViewer.setVisible(true);
	}
	
	private List<PersonaReporte> obtenerListadoPersonasReporte(List<PersonaDTO> personasDTO)
	{
		List<PersonaReporte> personas = new ArrayList<PersonaReporte>();
		for(PersonaDTO personaDTO : personasDTO)
		{
			PersonaReporte persona = new PersonaReporte(personaDTO.getNombre(), personaDTO.getTelefono(), personaDTO.getEmail(), Fechas.Fecha_a_String(personaDTO.getFechaNacimiento()), personaDTO.getCalle(), personaDTO.getAltura(), personaDTO.getPiso(), personaDTO.getDepto(), personaDTO.getLocalidad().getNombre(), personaDTO.getTipoContacto().getNombre());
			personas.add(persona);
		}
		return personas;
	}

}