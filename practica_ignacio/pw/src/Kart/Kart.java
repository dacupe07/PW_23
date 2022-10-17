package Kart;

/**
 * Clase que representa un kart 
 * @author Ignacio Garcia Cabezuelo */
public class Kart {
	
	private int identficador;
	
	private boolean tipo;
	
	private estado Estado;
	
	/**
	 * Constructor vacio de la clase
	 */
	public Kart () {
		
	}
	
	/**
	 * Constructor de la clase
	 * @param identificador_ Un entero que va a identificar la clase
	 * @param tipo_ Un valor booleano que va ser true si el kart es de adultos
	 * @param Estado_ Un valor enum que indica el estado del kart
	 */
	public Kart (int identificador_,boolean tipo_,estado Estado_) {
		
		this.identficador=identificador_;
		this.tipo=tipo_;
		this.Estado=Estado_;
	}
	
	/**
	 * Metodo get para el identificador
	 * @return El identificador de la clase
	 */
	public int getIdentificador(){ 
		return(this.identficador);
	}
	
	/**
	 * Metodo get para el tipo
	 * @return El tipo de la clase
	 */
	public boolean getTipo(){ 
		return(this.tipo);
	}
	
	/**
	 * Metodo get para el estado
	 * @return
	 */
	public estado getEstado(){ 
		return(this.Estado);
	}
	
	/**
	 * Metodo set para el identficador. Establece un valor entero para el identificador
	 * @param identificador_ Valor entero que va a ser el identificador de la clase
	 */
	public void setIdentificador(int identificador_){ 
		this.identficador=identificador_;
	}
	
	/**
	 * Metodo set para el tipo
	 * @param tipo_ Valor booleano que va a ser el tipo de kart. True si es para adultos o false si es para niños
	 */
	public void setTipo(boolean tipo_){ 
		this.tipo=tipo_;
	}
	
	/**
	 * Metodo set para el estado del kart
	 * @param Estado_ Un valor enum que va a ser el estado del kart.
	 */
	public void setEstado(estado Estado_){ 
		this.Estado=Estado_;
	}
	
	/**
	 * Convierte todo la informacion de la clase a un string
	 * @return Devuelve un string con toda la informacion de la clase
	 */
	public String toString() {
		String mensaje="Identificador: "+this.identficador+", "+"tipo: "+this.tipo+", "+"estado: "+this.Estado;
        return mensaje;
	}
	
}
