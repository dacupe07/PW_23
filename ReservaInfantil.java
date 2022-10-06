package es.uco.pw.clases;

public class ReservaInfantil extends Reserva{
	public int nNiños;
	
	
	public ReservaInfantil() {
		
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
	
	public String toString() {
		
		String info = super.toString();
		
		if(this.nNiños!= 0) {
			info += " Adultos: " + this.nNiños;
		}
		return info;
	}
	
}
