package FactoriaReservas;

import Reservas.ReservaAdultosIndividual;
import Reservas.ReservaFamiliarIndividual;
import Reservas.ReservaInfantilIndividual;
import Reservas.Reservas;

/**
 * Clase que representa la factoria de reservas individuales
 * @author Ignacio Garcia Cabezuelo
 *
 */
public class FactoriaReservasIndividual extends FactoriaAbstractaReservas{
	@Override
	public  Reservas getReserva(String tipo) {
		
		if(tipo.equalsIgnoreCase("ReservaInfantil")) {
			return new ReservaInfantilIndividual();
		}
		else if(tipo.equalsIgnoreCase("ReservaFamiliar")) {
			return new ReservaFamiliarIndividual();
		}
		else if(tipo.equalsIgnoreCase("ReservaAdultos")) {
			return new ReservaAdultosIndividual();
		}
		return null;
	}
		
}
