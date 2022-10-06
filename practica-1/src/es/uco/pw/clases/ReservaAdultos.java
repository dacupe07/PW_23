package es.uco.pw.clases;

public class ReservaAdultos extends Reserva{
	public int nAdultos = 1;
	
	public ReservaAdultos() {
		
	}

	/**
	 * @return the nAdultos
	 */
	public int getnAdultos() {
		return nAdultos;
	}

	/**
	 * @param nAdultos the nAdultos to set
	 */
	public void setnAdultos(int nAdultos) {
		this.nAdultos = nAdultos;
	}
	
	
}
