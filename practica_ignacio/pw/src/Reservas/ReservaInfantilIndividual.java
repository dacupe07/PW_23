package Reservas;

/**
 * Clase que representa una reserva infantil individual
 * @author Ignacio Garcia Cabezuelo */
public class ReservaInfantilIndividual extends Reservas {
		
		private int numeroNinos;
		
		public ReservaInfantilIndividual() {}
		
		public int getNumeroNinos() {
			return(this.numeroNinos);
		}
		
		public void setNumeroNinos(int numerosNinos_) {
			this.numeroNinos=numerosNinos_;
		}

		public String toString() {
			return "ReservaInfantilIndividual [Numero Ninos=" + getNumeroNinos() + ", Correo=" + getCorreo()
					+ ", Fecha reserva=" + getFechareserva() + ", Duracion=" + getDuracion() + ", Pista="
					+ getPista() + ", Precio=" + getPrecio() + ", Descuento=" + getDescuento() + "]";
		}
		
		
		
}
