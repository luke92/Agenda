package dto;

import java.util.Calendar;
import java.util.Date;

import util.Fechas;

public class PersonaReporte 
{
	private String nombre;
	private String telefono;
	private String email;
	private String fechaNacimiento;
	private String calle;
	private String altura;
	private String piso;
	private String depto;
	private String localidad;
	private String tipoContacto;

	public PersonaReporte(String nombre, String telefono, String email, String fechaNacimiento,
			String calle, String altura, String piso, String depto, String localidad, String tipoContacto) 
	{
		this.nombre = nombre;
		this.telefono = telefono;
		this.email = email;
		this.fechaNacimiento = fechaNacimiento;
		this.calle = calle;
		this.altura = altura;
		this.piso = piso;
		this.depto = depto;
		this.localidad = localidad;
		this.tipoContacto = tipoContacto;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getAltura() {
		return altura;
	}

	public void setAltura(String altura) {
		this.altura = altura;
	}

	public String getPiso() 
	{
		return piso;
	}

	public void setPiso(String piso) {
		this.piso = piso;
	}

	public String getDepto() {
		return depto;
	}

	public void setDepto(String depto) {
		this.depto = depto;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getTipoContacto() {
		return tipoContacto;
	}

	public void setTipoContacto(String tipoContacto) {
		this.tipoContacto = tipoContacto;
	}

	public String getDomicilioCompleto() {
		return getCalle() + " " + getAltura() + " (" + getPiso() + " " + getDepto() + "), "
				+ getLocalidad();
	}
	
	public String getDominioEmail()
	{
		return getEmail().substring(getEmail().indexOf("@")+1, getEmail().length()).toLowerCase();
	}
}
