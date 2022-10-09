package es.uco.pw.clases;


public class ReservaFamiliar extends Reserva {
	public int nNiños;
	public int nAdultos = 1;
	
	public ReservaFamiliar() {
		
	}

	/**
	 * @return the nNiños
	 */
	public int getnNiños() {
		return nNiños;
	}

	/**
	 * @param nNiños the nNiños to set
	 */
	public void setnNiños(int nNiños) {
		this.nNiños = nNiños;
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
	
	public String toString() {
		
		String info = super.toString();
		if(this.nNiños!= 0) {
			info += " Niños: " + this.nNiños;
		}
		if(this.nAdultos!= 0) {
			info += " Adultos: " + this.nAdultos;
		}
		return info;
	}
}
