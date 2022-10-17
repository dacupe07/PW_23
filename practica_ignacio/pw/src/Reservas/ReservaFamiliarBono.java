package Reservas;

/**
 * Clase que representa una reserva de Familiar en un bono
 * @author Ignacio Garcia Cabezuelo */
public class ReservaFamiliarBono extends Reservas {
		
	private int numeroAdultos;
	
	private int numeroNinos;
	
	private int identificador_bono;
	
	private int numeroSesion;
	
	public ReservaFamiliarBono() {
		
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
	
	public int getIdentificador_bono() {
		return(this.identificador_bono);
	}
	
	public void setIdentificador_bono(int identficador_bono_) {
		this.identificador_bono=identficador_bono_;
	}
	
	public int getNumerosesion() {
		return(this.numeroSesion);
	}
	
	public void setNumerosesion(int numeroSesion_) {
		this.numeroSesion=numeroSesion_;
	}

	public String toString() {
		return "ReservaFamiliarBono [ Numeroadultos=" + getNumeroadultos() + ", Numero Ninos="
				+ getNumeroNinos() + ", Identificador_bono=" + getIdentificador_bono() + ", Numero sesion="
				+ getNumerosesion() + ", Correo=" + getCorreo() + ", Fecha reserva=" + getFechareserva()
				+ ", Duracion=" + getDuracion() + ", Pista=" + getPista() + ", Precio =" + getPrecio()
				+ ", Descuento=" + getDescuento() + "]";
	}
	
	
}
