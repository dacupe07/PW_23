package Reservas;
import java.util.Date;

/**
 * Clase abstracta de Reservas 
 * @author Ignacio Garcia Cabezuelo */
public class Reservas {
	
	private String correo;
	
	private Date fechareserva;
	
	private int duracion;
	
	private String pista;
	
	private float precio;
	
	private float descuento;
	
	public Reservas() {}
	
	/**
	 * Metodo get del correo
	 * @return El correo del usuario que ha hecho la reserva
	 */
	public String getCorreo() {
		return(this.correo);
	}
	
	/**
	 * Metodo get de la fecha de reserva
	 * @return La fecha y hora de la reserva
	 */
	public Date getFechareserva() {
		return(this.fechareserva);
	}
	
	/**
	 * Metodo get de la duracion
	 * @return La duracion de la reserva en minutos
	 */
	public int getDuracion() {
		return(this.duracion);
	}
	
	/**
	 * Metodo get de la pista
	 * @return Nombre de la pista reservada
	 */
	public String getPista() {
		return(this.pista);
	}
	
	/**
	 * Metodo get del precio
	 * @return Devuelve el precio en euros
	 */
	public float getPrecio() {
		return(this.precio);
	}
	
	/**
	 * Metodo get del descuento
	 * @return Devuelve el descuento en euros
	 */
	public float getDescuento() {
		return(this.descuento);
	}
	
	/**
	 * Metodo set para el correo
	 * @param correo_ Correo del usuario que va a realizar la reserva
	 */
	public void  setCorreo(String correo_) {
		this.correo=correo_;
	}
	
	/**
	 * Metodo set para la fecha de reserva
	 * @param fechareserva_ Date con formato en fecha y hora en la que se va a realizar la reserva
	 */
	public void  setFechareserva(Date fechareserva_) {
		this.fechareserva=fechareserva_;
	}
	
	/**
	 * Metodo set para la duracion
	 * @param duracion_ Duracion en minutos de la reserva
	 */
	public void  setDuracion(int duracion_) {
		this.duracion=duracion_;
	}
	
	/**
	 * Metodo set de la pista
	 * @param pista_ Nombre de la pista a reservar
	 */
	public void setPista(String pista_) {
		this.pista=pista_;
	}
	
	/**
	 * Metodo set del precio
	 * @param precio_ Precio en euros de la reserva
	 */
	public void setPrecio(float precio_) {
		this.precio=precio_;
	}
	
	/**
	 * Metodo set del descuento
	 * @param descuento_ Descuento en euros de la reserva
	 */
	public void setDescuento(float descuento_) {
		this.descuento=descuento_;
	}
}
