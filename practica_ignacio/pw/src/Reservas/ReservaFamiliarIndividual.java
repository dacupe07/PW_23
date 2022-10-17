package Reservas;

/**
 * Clase que representa una reserva de familiar individual
 * @author Ignacio Garcia Cabezuelo */
public class ReservaFamiliarIndividual extends Reservas {
	
	private int numeroAdultos;
	
	private int numeroNinos;
	
	public ReservaFamiliarIndividual() {
		
	}
	
	public int getNumeroadultos() {
		return(this.numeroAdultos);
	}
	
	public void setNumeroadultos(int numeroAdultos_) {
		this.numeroAdultos=numeroAdultos_;
	}
	
	public int getNumeroNinos() {
		return(this.numeroNinos);
	}
	
	public void setNumeroNinos(int numerosNinos_) {
		this.numeroNinos=numerosNinos_;
	}

	public String toString() {
		return "ReservaFamiliarIndividual [Numeroadultos=" + getNumeroadultos() + ", Numero Ninos="
				+ getNumeroNinos() + ", Correo=" + getCorreo() + ", Fecha reserva=" + getFechareserva()
				+ ", Duracion=" + getDuracion() + ", Pista=" + getPista() + ", Precio=" + getPrecio()
				+ ", Descuento=" + getDescuento() + "]";
	}
	
	
}
