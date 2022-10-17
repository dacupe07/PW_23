package Pistas;
import java.util.ArrayList;

import Kart.Kart;
import Kart.estado;

/**
 * Clase que representa una Pista
 * @author Ignacio Garcia Cabezuelo */
public class Pista {
	
	private String nombre;
	
	private boolean disponible;
	
	private Dificultad dificultad;
	
	private ArrayList<Kart> karts_asociados;
	
	private int maxkarts;
	
	/**
	 * Constructor vacio de la clase
	 */
	public Pista () {
		
	}
	
	/**
	 * Constructor de la clase
	 * @param nombre_ String que va ser el nombre y va a identficar a la pista
	 * @param disponible_ Valor booleano que indica si esta disponible la pista para reservar, true si esta disponible y false si no lo esta
 	 * @param dificultad_ Valor enum que indica la dificultad de la pista. Puede ser infantil, familiar o adultos.
	 * @param karts_asociados_ Lista con los karts asociados a la pista
	 * @param maxkarts_ Un entero que va indiciar el numero maximo de karts asociados que puede tener la pista
	 */
	public Pista(String nombre_,boolean disponible_,Dificultad dificultad_,ArrayList<Kart> karts_asociados_,int maxkarts_) {
		
		this.nombre=nombre_;
		this.disponible=disponible_;
		this.dificultad=dificultad_;
		this.karts_asociados=karts_asociados_;
		this.maxkarts=maxkarts_;
		
	}
	
	 /**
	  * Metodo get del nombre de la pista 
	  * @return El nombre de la pista
	  */
	public String getNombre() {
		return(this.nombre);
	}
	
	/**
	 * Metodo get de Disponible
	 * @return True si esta disponible o false si no lo esta
	 */
	public boolean getDisponible() {
		return(this.disponible);
	}
	
	/**
	 * Metodo get para la dificultad
	 * @return Un enum que puede ser infantil, familiar o adultos
	 */
	public Dificultad getDificultad() {
		return(this.dificultad);
	}
	
	/**
	 * Metodo get para la lista de karts asociados
	 * @return La lista de karts asociados
	 */
	public ArrayList<Kart> getKart_asociados(){
		return(this.karts_asociados);
	}
	
	/**
	 * Metodo get para maxkarts
	 * @return El numero maximo de karts que puede tener pista
	 */
	public int getMaxkarts() {
		return(this.maxkarts);
	}
	
	/**
	 * Metodo set para el nombre de la pista
	 * @param nombre_ String que va a ser el nombre de la pista
	 */
	public void setNombre(String nombre_) {
		this.nombre=nombre_;
	}
	
	/**
	 * Metodo set para la disponobilidad de la pista
	 * @param disponible_ Valor booleano que va a ser la disponibilidad de la pista
	 */
	public void setDisponible(boolean disponible_) {
		this.disponible=disponible_;
	}
	
	/**
	 * Metodo set para la dificultad 
	 * @param dificultad_ Valor enum que indica la dificultad de la pista
	 */
	public void setDificultad(Dificultad dificultad_) {
		this.dificultad=dificultad_;
	}
	 /**
	  * Metodo set para lista de los karts asociados
	  * @param karts_asociados_ ArrayList de kart que va ser la lista de karts asociados
	  */
	public void setKart_asociados(ArrayList<Kart> karts_asociados_){
		this.karts_asociados=karts_asociados_;
	}
	
	/**
	 * Metodo set para el numero maximo de karts
	 * @param maxkarts_ Entero que va a ser el numero maximo de karts de la pista
	 */
	public void setMaxkarts(int maxkarts_) {
		this.maxkarts=maxkarts_;
	}
	
	/**
	 * Metodo toString que tranforma la informacion de la clase a un string
	 * @return Un string que contiene la informacion de la clase
	 */
	public String toString() {
		String mensaje="Nombre: "+this.nombre+", Disponible: "+this.disponible+", Dificultad: "+this.dificultad+", MaxKarts: "+this.maxkarts+", KartAsociados:";
		for(int i=0; i < this.getKart_asociados().size(); i++) {
			mensaje=mensaje+("Identificador: " + this.getKart_asociados().get(i).getIdentificador() + ", Tipo: " + this.getKart_asociados().get(i).getTipo() +", Estado: "+ this.getKart_asociados().get(i).getEstado());
		}
        return mensaje;
	}
	
	/**
	 * Consulta que karts estan disponible dentro de la lista de karts asociados
	 * @return Un ArrayList con los karts disponibles
	 */
	public ArrayList<Kart> consultarKartsDisponibles(){
		
		ArrayList<Kart> kartsdisponibles= new ArrayList<Kart>();
		for (int i = 0; i < this.karts_asociados.size(); i++){
		    
			if(this.karts_asociados.get(i).getEstado() == estado.Disponible) {
		    	
				kartsdisponibles.add(this.karts_asociados.get(i));
		    }
		}
		
		return(kartsdisponibles);
	}
	
	/**
	 * Asocia un kart a una pista teniendo en cuenta el tipo de kart y la dificultad de la pista
	 * @param kart_ Kart que se va a asociar a pista
	 */
	public void asociarKartAPista(Kart kart_) {
		
		if(kart_.getTipo()) {
			
			if(this.dificultad== Dificultad.Adultos || this.dificultad== Dificultad.Familiar ) {
				
				this.karts_asociados.add(kart_);
			}
		}
		else {
			if(this.dificultad== Dificultad.Infantil || this.dificultad== Dificultad.Familiar ) {
				
				this.karts_asociados.add(kart_);
			}
		}
	}
}
