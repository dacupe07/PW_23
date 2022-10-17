package FactoriaReservas;

import Reservas.Reservas;

/**
 * Clase abstracta que representa la factoria abstracta de reservas
 * @author Ignacio Garcia Cabezuelo
 *
 */
public abstract class FactoriaAbstractaReservas {
	
	abstract Reservas getReserva(String tipo);
}
