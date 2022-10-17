package Reservas;

/**
 * Clase que representa una reserva de adultos individual
 * @author Ignacio Garcia Cabezuelo */
public class ReservaAdultosIndividual extends Reservas{
	
	private int numeroAdultos;
	
	public ReservaAdultosIndividual() {
		
	}
	
	public int getNumeroadultos() {
		return(this.numeroAdultos);
	}
	
	public void setNumeroadultos(int numeroAdultos_) {
		this.numeroAdultos=numeroAdultos_;
	}
	
	public String toString() {
		return "ReservaAdultosIndividual [Numero adultos=" + getNumeroadultos() + ", Correo=" + getCorreo()
				+ ", Fecha reserva=" + getFechareserva() + ", Duracion=" + getDuracion() + ", Pista ="
				+ getPista() + ", Precio=" + getPrecio() + ", Descuento=" + getDescuento() + "]";
	}
	
}
