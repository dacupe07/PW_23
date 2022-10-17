package Usuarios;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Clase que representa un usuario
 * @author Ignacio Garcia Cabezuelo */
public class Usuario {
	
	private String nombre;
	
	private String apellidos;
	
	private Date fechaNacimiento;
	
	private Date fechainscripcion;
	
	private String correo;
	
	/**
	 * Constructor vacio de la clase
	 */
	public Usuario() {
		
	}
	
	/** 
	 * Constructor de la clase
	 * @param nombre_ String que sera el nombre del usuario
	 * @param apellidos_ String que sera los apellidos del usuario
	 * @param fechaNacimiento_ Date que sera la fecha de nacimiento del usuario
	 * @param fechainscripcion_ Date que sera la fecha de inscripcion
	 * @param correo_ Correo que identificara al usuario
	 */
	public Usuario(String nombre_,String apellidos_,Date fechaNacimiento_, Date fechainscripcion_, String correo_) {
		
		this.nombre=nombre_;
		this.apellidos=apellidos_;
		this.fechaNacimiento=fechaNacimiento_;
		this.fechainscripcion=fechainscripcion_;
		this.correo=correo_;
	}
	
	/**
	 * Metodo get del nombre
	 * @return Un string con el nombre del usuario
	 */
	public String getNombre() {
		return(this.nombre);
	}
	
	/**
	 * Metodo get los apellidos
	 * @return Un String con los apellidos del usuario
	 */
	public String getApellidos() {
		return(this.apellidos);
	}
	
	/**
	 * Metodo get de la fecha de nacimiento
	 * @return Un Date de la fecha de nacimiento
	 */
	public Date getFechaNacimiento() {
		return(this.fechaNacimiento);
	}
	
	/**
	 * Metodo get de la fecha inscripcion
	 * @return Un Date de la fecha de inscripcion
	 */
	public Date getFechainscripcion() {
		return(this.fechainscripcion);
	}
	
	/**
	 * Metodo get del correo
	 * @return Un string con el correo del usuario
	 */
	public String getCorreo() {
		return(this.correo);
	}
	
	/**
	 * Metodo set del nombre
	 * @param nombre_ String que va a ser el nombre del usuario
	 */
	public void setNombre(String nombre_) {
		this.nombre=nombre_;
	}
	
	/**
	 * Metodo set de los apellidos
	 * @param apellidos_ String que va a ser los apellidos del usuario
	 */
	public void setApellidos(String apellidos_) {
		this.apellidos=apellidos_;
	}
	
	/**
	 * Metodo set de la fecha nacimiento
	 * @param fechaNacimiento_ Date que va a ser la fecha nacimineto del usuario
	 */
	public void setFechaNacimiento(Date fechaNacimiento_) {
		this.fechaNacimiento=fechaNacimiento_;
	}
	
	/**
	 * Metodo set para la fecha de inscripcion
	 * @param fechainscripcion_ Date que va a ser la fecha de inscripcion 
	 */
	public void setFechainscripcion(Date fechainscripcion_) {
		this.fechainscripcion=fechainscripcion_;
	}
	
	/**
	 * Metodo set del correo 
	 * @param correo_ String que va ser el correo del usuario
	 */
	public void setCorreo(String correo_) {
		this.correo=correo_;
	}
	
	/**
	 * Convierte la informacion de la clase en un string
	 * @return Un string con toda la informacion de la clase
	 */
	public String toString() {
		String mensaje="Nombre: "+this.nombre+", Apellidos: "+this.apellidos+", Fecha Nacimiento: "+this.fechaNacimiento+", Fecha Inscripcion: "+this.fechainscripcion+", Correo:"+this.correo;
        return mensaje;
	}
	
	/**
	 * Calcula la antiguedad en años del usuario
	 * @return La antiguedad en años del usuarios
	 */
	public int calcularAntiguedad() {
		Date date1 = new Date();

        SimpleDateFormat getYearFormat = new SimpleDateFormat("yyyy");
        String currentYear = getYearFormat.format(date1);
        String inscriptionYear= getYearFormat.format(this.fechainscripcion);
        
        int antiguedad=Integer.parseInt(currentYear)-Integer.parseInt(inscriptionYear);
        return(antiguedad);
	}
	
	/**
	 * Calcula la edad del usuario 
	 * @return La edad del usuario
	 */
	public int calcularEdad() {
		Date date1 = new Date();

        SimpleDateFormat getYearFormat = new SimpleDateFormat("yyyy");
        String currentYear = getYearFormat.format(date1);
        String inscriptionYear= getYearFormat.format(this.fechaNacimiento);
        
        int edad=Integer.parseInt(currentYear)-Integer.parseInt(inscriptionYear);
        return(edad);
	}
	
}
