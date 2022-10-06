package es.uco.pw.clases;

public abstract class ReservaCreator {
	
	/** 
	 * Crea una reserva para solo adultos
	 * @return una reserva de pista para adultos
	 *  */
	public abstract ReservaAdultos createReservaAdultos();
	
	/** 
	 * Crea una reserva para adultos y niños 
	 * @return una reserva de pista familiar
	 *  */
	public abstract ReservaFamiliar createReservaFamiliar();
	
	/** 
	 * Crea una reserva para solo niños
	 * @return una reserva de pista infantil
	 *  */
	public abstract ReservaInfantil createReservaInfantil();
		
}
