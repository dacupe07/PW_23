package es.uco.pw.clases;
import java.util.Date;
import java.util.Scanner;

public class Reserva {
	private int idUsuario;
	private Date fecha;
	private Date hora;
	private int duracion;
	private String pista;
	private float precio;
	private float descuentoAntigüedad;
	
	public Reserva() {
		
	}
	
	
	/**
	 * @return the idUsuario
	 */
	public int getIdUsuario() {
		return idUsuario;
	}
	/**
	 * @param idUsuario the idUsuario to set
	 */
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}
	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	/**
	 * @return the hora
	 */
	public Date getHora() {
		return hora;
	}
	/**
	 * @param hora the hora to set
	 */
	public void setHora(Date hora) {
		this.hora = hora;
	}
	/**
	 * @return the duracion
	 */
	public int getDuracion() {
		return duracion;
	}
	/**
	 * @param duracion the duracion to set
	 */
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	/**
	 * @return the pista
	 */
	public String getNombrePista() {
		return pista;
	}
	/**
	 * @param nombrePista the nombrePista to set
	 */
	public void setNombrePista(String pista) {
		this.pista = pista;
	}
	/**
	 * @return the precio
	 */
	public float getPrecio() {
		return precio;
	}
	/**
	 * @param precio the precio to set
	 */
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	/**
	 * @return the descuentoAntigüedad
	 */
	public float getDescuentoAntigüedad() {
		return descuentoAntigüedad;
	}
	/**
	 * @param descuentoAntigüedad the descuentoAntigüedad to set
	 */
	public void setDescuentoAntigüedad(float descuentoAntigüedad) {
		this.descuentoAntigüedad = descuentoAntigüedad;
	}

	public String toString() {
		String info = "\n--- INFORMACION INTRODUCIDA --- \n ID Usuario=" + idUsuario + ", Fecha=" + fecha + ", Hora=" + hora + ", Duracion=" + duracion
				+ ", Pista=" + pista + ", Precio=" + precio + ", Descuento por Antigüedad=" + descuentoAntigüedad + "\n\n";
		return info;
	}
	
	public  void crearReservaBono() {
		int tipo;
		BonoReservaCreator reservaBono = new BonoReservaCreator();
		Scanner lector = new Scanner(System.in);
		
		
		System.out.println("¿Qué tipo de reserva desea realizar? 0.ADULTOS//1.FAMILIAR//2.INFANTIL \n");
		System.out.println(" Introduce el numero perteneciente a la opción deseada: ");
		tipo = lector.nextInt();
		
		switch(tipo) {
		
		case 0:
			reservaBono.createReservaAdultos();
			break;
		case 1:
			reservaBono.createReservaFamiliar();
			break;
		case 2:
			reservaBono.createReservaInfantil();
			break;
		}
		
		
	}
	
	public  void crearReservaIndividual() {
		int tipo;
		IndividualReservaCreator reservaIndividual = new IndividualReservaCreator();
		Scanner lector = new Scanner(System.in);
		
		
		System.out.println("¿Qué tipo de reserva desea realizar? 0.ADULTOS//1.FAMILIAR//2.INFANTIL \n");
		System.out.println(" Introduce el numero perteneciente a la opción deseada: ");
		tipo = lector.nextInt();
		
		switch(tipo) {
		
		case 0:
			reservaIndividual.createReservaAdultos();
			break;
		case 1:
			reservaIndividual.createReservaFamiliar();
			break;
		case 2:
			reservaIndividual.createReservaInfantil();
			break;
		}
		
		
	}
	
	public float calcularPrecio(int duracion) {
		float precio;
		
		if(duracion == 60 ) {
			precio = 20;
		}
		else if(duracion == 90) {
			precio = 30;
		}
		else
		{
			precio = 40;
		}
		
		return precio;
	}
	
	

	
	
	
}

