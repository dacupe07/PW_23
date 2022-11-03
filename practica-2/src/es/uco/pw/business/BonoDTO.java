package es.uco.pw.business;

import java.time.LocalDate;

public class BonoDTO {
	
	protected int id_bono;
    protected String correo_usuario;
    protected int num_sesiones;
    protected String tipo_bono;
    protected LocalDate caducidad;
    protected double desc;
    
    
    
    
	/**
	 * @param id_bono
	 * @param correo_usuario
	 * @param num_sesiones
	 * @param tipo_bono
	 * @param caducidad
	 * @param desc
	 */
	public BonoDTO(int id_bono, String correo_usuario, int num_sesiones, String tipo_bono, LocalDate caducidad, double desc) {
		this.id_bono = id_bono;
		this.correo_usuario = correo_usuario;
		this.num_sesiones = num_sesiones;
		this.tipo_bono = tipo_bono;
		this.caducidad = caducidad;
		this.desc = desc;
	}
	/**
	 * @return the id_bono
	 */
	public int getId_bono() {
		return id_bono;
	}
	/**
	 * @param id_bono the id_bono to set
	 */
	public void setId_bono(int id_bono) {
		this.id_bono = id_bono;
	}
	/**
	 * @return the correo_usuario
	 */
	public String getCorreo_usuario() {
		return correo_usuario;
	}
	/**
	 * @param correo_usuario the correo_usuario to set
	 */
	public void setCorreo_usuario(String correo_usuario) {
		this.correo_usuario = correo_usuario;
	}
	/**
	 * @return the num_sesiones
	 */
	public int getNum_sesiones() {
		return num_sesiones;
	}
	/**
	 * @param num_sesiones the num_sesiones to set
	 */
	public void setNum_sesiones(int num_sesiones) {
		this.num_sesiones = num_sesiones;
	}
	/**
	 * @return the tipo_bono
	 */
	public String getTipo_bono() {
		return tipo_bono;
	}
	/**
	 * @param tipo_bono the tipo_bono to set
	 */
	public void setTipo_bono(String tipo_bono) {
		this.tipo_bono = tipo_bono;
	}
	/**
	 * @return the caducidad
	 */
	public LocalDate getCaducidad() {
		return caducidad;
	}
	/**
	 * @param caducidad the caducidad to set
	 */
	public void setCaducidad(LocalDate caducidad) {
		this.caducidad = caducidad;
	}
	/**
	 * @return the desc
	 */
	public double getDesc() {
		return desc;
	}
	/**
	 * @param desc the desc to set
	 */
	public void setDesc(double desc) {
		this.desc = desc;
	}
	
	public String toStringBono()
    {
        String info = "\nID: " + this.id_bono + ". Usuario: " + this.correo_usuario + ". Numero de Sesiones: " + this.num_sesiones + ". Tipo de bono: " + this.tipo_bono + ". Caducidad: " + this.caducidad + ". Descuento:" + this.desc;
        return info;
    }
    
    
}
