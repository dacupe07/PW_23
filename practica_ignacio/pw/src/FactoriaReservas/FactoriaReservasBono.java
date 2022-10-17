package FactoriaReservas;

import Reservas.ReservaAdultosBono;
import Reservas.ReservaFamiliarBono;
import Reservas.ReservaInfantilBono;
import Reservas.Reservas;

/**
 * Clase de la factoria de reservas de un bono
 * @author Ignacio Garcia Cabezuelo
 *
 */
public class FactoriaReservasBono extends FactoriaAbstractaReservas{
	@Override
	public  Reservas getReserva(String tipo) {
		
		if(tipo.equalsIgnoreCase("ReservaInfantil")) {
			return new ReservaInfantilBono();
		}
		else if(tipo.equalsIgnoreCase("ReservaFamiliar")) {
			return new ReservaFamiliarBono();
		}
		else if(tipo.equalsIgnoreCase("ReservaAdultos")) {
			return new ReservaAdultosBono();
		}
		return null;
	}
}
