package es.uco.pw.clases;

public class BonoReservaCreator extends ReservaCreator{
	
	@Override
	public ReservaAdultos createReservaAdultos() {
		ReservaAdultos reserva = new ReservaAdultos();
		
		return reserva;
	}
	
	@Override
	public ReservaFamiliar createReservaFamiliar() {
		ReservaFamiliar reserva = new ReservaFamiliar();
		
		return reserva;
	}
	
	@Override
	public ReservaInfantil createReservaInfantil() {
		ReservaInfantil reserva = new ReservaInfantil();
		
		return reserva;
	}
	
}
