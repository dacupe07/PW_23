package Pistas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;
import java.util.StringTokenizer;

import Kart.Kart;
import Kart.estado;

/**
 * Clase que sirve para gestionar las pistas y karts
 * @author Ignacio Garcia Cabezuelo */
public class GestorPistas {
	
	private ArrayList<Kart> lista_karts;
	
	private ArrayList<Pista> lista_pistas;
	
	public GestorPistas() {
		
		this.lista_karts= new ArrayList<Kart>();
		this.lista_pistas=new ArrayList<Pista>();
		
	}
	
	/**
	 * Carga los karts desde un fichero en la lista de karts
	 * @param prop Archivo properties que tendra la ruta del fichero que contiene los karts
	 */
	public void cargarKarts(Properties prop) {
		
		this.lista_karts = new ArrayList<Kart>();
		
		String nombreFichero = prop.getProperty("ficheroKart");
		
		BufferedReader br = null; // Variable para leer el fichero
		
		try {
			
			// Abrimos el fichero de texto
			
			 br = new BufferedReader(new FileReader(nombreFichero));
			
			String linea = br.readLine(); // Leemos la primera linea del fichero
			
			// Repetimos mientras no llegue al final del fichero
			
			while(linea != null) {
				
				// Creamos un kart vacio
				
				Kart kart_ = new Kart();
				
				/*
				 *  Leemos la linea actual
				 *  Dividimos la cadena en partes en funcion del limitador <,>
				 */
				
				StringTokenizer separador = new StringTokenizer(linea,","); 
				
				// Recorremos la cadena de tokens para extraer los elementos
				
				ArrayList<String> partes = new ArrayList<String>();
				
				while(separador.hasMoreTokens()) {
					// Almacenamos cada elemento de la linea
					partes.add(separador.nextToken());
				}
				
				// Obtenemos los elementos de la linea
				
				for(int i=0; i<partes.size(); i++) {
					
					// Guardamos el identificador
					
					if(i==0) {
						kart_.setIdentificador(Integer.parseInt(partes.get(i)));
					}
					
					// Guardamos el tipo
					
					else if(i == 1) {
						kart_.setTipo(Boolean.parseBoolean(partes.get(i)));
					}
					
					// Guardamos el estado
					
					else if(i== 2) {
						kart_.setEstado(estado.valueOf(partes.get(i)));
					}
					
				}
				
				// Almacenamos los datos del kart en la lista
				
				this.lista_karts.add(kart_);
				
				// Leemos la siguiente linea
				
				linea = br.readLine();
			}
			
		}catch(Exception ex) {
			System.out.println("No se ha podido abrir el fichero: " + nombreFichero);
		}
		finally {
            try {
                if(br != null)
                    br.close();
            }
            catch (Exception e) {
                System.out.println("Error al cerrar el fichero");
                System.out.println(e.getMessage());
            }
        }
	}
	
	/**
	 * Carga las pistas desde un fichero en la lista de pistas
	 * @param prop Archivo properties que contiene la ruta del archivo que contiene las pistas
	 */
	public void cargarPistas(Properties prop) {
		
		this.lista_pistas = new ArrayList<Pista>();
		
		String nombreFichero = prop.getProperty("ficheroPista");
		
		BufferedReader br = null; // Variable para leer el fichero
		
		try {
			
			// Abrimos el fichero de texto
			
			 br = new BufferedReader(new FileReader(nombreFichero));
			
			String linea = br.readLine(); // Leemos la primera linea del fichero
			
			// Repetimos mientras no llegue al final del fichero
			
			while(linea != null) {
				
				// Creamos una pista vacia
				
				Pista pista_ = new Pista();
				
				/*
				 *  Leemos la linea actual
				 *  Dividimos la cadena en partes en funcion del limitador <,>
				 */
				
				StringTokenizer separador = new StringTokenizer(linea,","); 
				
				// Recorremos la cadena de tokens para extraer los elementos
				
				ArrayList<String> partes = new ArrayList<String>();
				
				while(separador.hasMoreTokens()) {
					// Almacenamos cada elemento de la linea
					partes.add(separador.nextToken());
				}
				
				// Obtenemos los elementos de la linea
				
				for(int i=0; i<partes.size(); i++) {
					
					// Guardamos el nombre de la pista
					
					if(i==0) {
						pista_.setNombre(partes.get(i));
					}
					
					// Guardamos la disponibilidad de la pista
					
					else if(i == 1) {
						pista_.setDisponible(Boolean.parseBoolean(partes.get(i)));
					}
					
					// Guardamos la dificultad de la pista
					
					else if(i== 2) {
						pista_.setDificultad(Dificultad.valueOf(partes.get(i)));
					}
					
					// Guardamos el maximo de karts
					else if(i== 3) {
						pista_.setMaxkarts(Integer.parseInt(partes.get(i)));
					}
					
					//Guardamos la lista de karts asociados
					else if(i== 4) {
						ArrayList<Kart> listakart = new ArrayList<Kart>(); // Lista de karts vacia
						
						Kart kart_ = new Kart();
						
						StringTokenizer st2 = new StringTokenizer(partes.get(i),";"); // Segundo delimitador para separar los elementos de la lista de evaluaciones de la critica
						
						ArrayList<String> linea2 = new ArrayList<String>(); // Linea que separa por el delimitador <;>
						
						while(st2.hasMoreTokens()) {
							// Almacenamos cada elemento de la linea
							linea2.add(st2.nextToken());
						}
						
						// Recorremos la nueva linea
						
						ArrayList<String> linea3 = new ArrayList<String>();
						
						for(int j=0; j < linea2.size(); j++) {
							StringTokenizer st3 = new StringTokenizer(linea2.get(j),":");
							while(st3.hasMoreTokens()) {
								linea3.add(st3.nextToken());
							}
							kart_.setIdentificador(Integer.parseInt(linea3.get(0)));
							kart_.setTipo(Boolean.parseBoolean(linea3.get(1)));
							kart_.setEstado(estado.valueOf(linea3.get(2)));
							listakart.add(kart_);
						}
						pista_.setKart_asociados(listakart);
					}
					
				}
				
				// Almacenamos los datos de la pista en la lista
				
				this.lista_pistas.add(pista_);
				
				// Leemos la siguiente linea
				
				linea = br.readLine();
			}
			
		}catch(Exception ex) {
			System.out.println("No se ha podido abrir el fichero: " + nombreFichero);
		}
		finally {
            try {
                if(br != null)
                    br.close();
            }
            catch (Exception e) {
                System.out.println("Error al cerrar el fichero");
                System.out.println(e.getMessage());
            }
        }
	}
	
	/**
	 * Crea un kart nuevo
	 * @param entrada Buffer de entrada
	 * @return Devuelve 0 si no se ha podido crear o 1 si tiene exito
	 */
	public int crearKarts(Scanner entrada) {
		Kart kart_ = new Kart();
		
		// Obtenemos el identificador del kart
		
		System.out.print("Introduce el identificador del kart: ");
		
		kart_.setIdentificador(Integer.parseInt(entrada.nextLine()));
		
		// Comprobamos si ek kart ya esta registrado
		
		if(comprobacionKart(kart_.getIdentificador()) == true) {
			System.out.println("Este Kart ya existe");
			return 0; // No se crea el kart
		}
		
		else {
			
			// Almacenamos el nombre
			
			System.out.print("Introduce el tipo : ");
			
			kart_.setTipo(Boolean.parseBoolean(entrada.nextLine()));
			
			//Almacenamos el estado
			System.out.print("Introduce el estado : ");
			
			kart_.setEstado(estado.valueOf(entrada.nextLine()));
			
			// Anadimos el kart a la lista
			
			this.lista_karts.add(kart_);
			
			return 1; // Se ha anadido el kart a la lista
			
		}
		
	}
	
	/**
	 * Crea una pista nueva
	 * @param entrada Buffer de entrada
	 * @return Devuelve 0 si no se ha podido crear o 1 si tiene exito
	 */
	public int crearPistas(Scanner entrada) {
		Pista pista_ = new Pista();
		
		// Obtenemos el nombre de la pista
		
		System.out.print("Introduce el nombre de la pista: ");
		
		pista_.setNombre(entrada.nextLine());
		
		// Comprobamos si la pista ya existe
		
		if(comprobacionPista(pista_.getNombre()) == true) {
			System.out.println("Esta pista ya existe");
			return 0; // No se crea
		}
		
		else {
			
			// Almacenamos la disponibilidad
			
			System.out.print("Introduce la disponibilidad : ");
			
			pista_.setDisponible(Boolean.parseBoolean(entrada.nextLine()));
			
			//Almacenamos la dificultad
			System.out.print("Introduce la dificultad : ");
			
			pista_.setDificultad(Dificultad.valueOf(entrada.nextLine()));
			
			//Almacenamos el MaxKarts
			System.out.print("Introduce los numeros maximos de karts : ");
			
			pista_.setMaxkarts(Integer.parseInt(entrada.nextLine()));
			
			// Anadimos la pista a la lista
			
			this.lista_pistas.add(pista_);
			
			return 1; // Se ha anadido la pista a la lista con exito
			
		}
		
	}
	
	/**
	 * Comprueba si un kart existe ya
	 * @param identificador_ Identificador del kart
	 * @return True si existe ya ,false si no existe
	 */
	public boolean comprobacionKart( int identificador_) {
		for(int i=0; i < this.lista_karts.size(); i++) {
			
			if(this.lista_karts.get(i).getIdentificador()==identificador_) {
				return true; 
			}
		}
		return false; 
	}
	
	/**
	 * Comprueba si una pista existe ya
	 * @param nombre_ Nombre de la pista
	 * @return True si ya existe, false si no existe
	 */
	public boolean comprobacionPista(String nombre_) {
		for(int i=0; i < this.lista_pistas.size(); i++) {
			
			if(this.lista_pistas.get(i).getNombre().equals(nombre_)) {
				return true; 
			}
		}
		return false; 
	}
	
	/**
	 * Registra todos los karts, es decir, vuelca el contenido de la lista en el fichero
	 * @param prop Fichero properties donde estara la ruta del fichero en el que vamos a escribir
	 */
	public void RegistroKart(Properties prop) {
		
		FileWriter fichero = null;
		
		try {
			
			// Abrimos el fichero de karts
			
			fichero = new FileWriter(prop.getProperty("ficheroKart"));
			
			BufferedWriter escritura = new BufferedWriter(fichero);
			
			// Escribimos linea a linea en el fichero
			
			for(int i=0; i < this.lista_karts.size(); i++) {
				
				// Identificador del kart
				
				escritura.write(this.lista_karts.get(i).getIdentificador() + ",");
				
				// Tipo del kart
				
				escritura.write(this.lista_karts.get(i).getTipo() + ",");
				
				// Estado del kart
				
				escritura.write(this.lista_karts.get(i).getEstado() + ",");
				
				escritura.newLine(); // Escribimos una nueva linea
			}
			escritura.close(); // Cerramos el fichero
			
		}catch(Exception ex) {
			System.out.println("Se ha producido un error al abrir el fichero: " + fichero);
			return;
		}
		
	}
	
	/**
	 * Registra todas las pistas, es decir, vuelca el contenido de la lista en el fichero
	 * @param prop Fichero properties donde estara la ruta del fichero en el que vamos a escribir
	 */
	public void RegistroPistas(Properties prop) {
		FileWriter fichero = null;
		
		try {
			
			// Abrimos el fichero de pistas
			
			fichero = new FileWriter(prop.getProperty("ficheroPista"));
			
			BufferedWriter escritura = new BufferedWriter(fichero);
			
			// Escribimos linea a linea en el fichero
			
			for(int i=0; i < this.lista_pistas.size(); i++) {
				
				// Nombre de la pista
				
				escritura.write(this.lista_pistas.get(i).getNombre() + ",");
				
				// Disponibilidad de la pista
				
				escritura.write(this.lista_pistas.get(i).getDisponible() + ",");
				
				// Dificultad de la pista
				
				escritura.write(this.lista_pistas.get(i).getDificultad() + ",");
				
				// Numero maximo de karts
				
				escritura.write(this.lista_pistas.get(i).getMaxkarts()+",");
				
				// Lista de karts asociados
				
				ArrayList<Kart> lista = this.lista_pistas.get(i).getKart_asociados();
				
				if(lista.isEmpty() == false) { 
					
					for(int j=0; j <= lista.size(); j++) {
						
						
						if(j == lista.size()) {
							escritura.write(",");
						}
						
						 
						
						else {
							
							escritura.write(lista.get(j).getIdentificador() + ":" + lista.get(j).getTipo() + ":" + lista.get(j).getEstado() + ";");
						}

					}
				}
				
				escritura.newLine(); // Escribimos una nueva linea
			}
			escritura.close(); // Cerramos el fichero
			
		}catch(Exception ex) {
			System.out.println("Se ha producido un error al abrir el fichero: " + fichero);
			return;
		}
		
	}
	
	/**
	 * Muestra por pantalla las pistas que estan en mantenimiento
	 */
	public void ListarPistasMantenimiento() {
		for(int i=0; i < this.lista_pistas.size(); i++) {
			
			if(!this.lista_pistas.get(i).getDisponible()) {
				
				System.out.println(this.lista_pistas.get(i).toString());
			}
		}
	}
	
	/**
	 * Devuelve una lista que contiene el conjunto de pistas que estén libres (no reservadas ni en mantenimiento) y tengan al menos ese número de karts asociados
	 * @param nkart Numero minimo de karts que tiene que tener asociados
	 * @param dificultad_ Dificultad de la pista
	 * @return Una lista de las pistas libres
	 */
	public ArrayList<Pista> Pistaslibres(int nkart, Dificultad dificultad_){
		ArrayList<Pista> Pistaslibres= new ArrayList<Pista>();
		for(int i=0; i < this.lista_pistas.size(); i++) {
			
			if(this.lista_pistas.get(i).getDificultad()==dificultad_) {
				
				if(this.lista_pistas.get(i).getKart_asociados().size()>=nkart) {
					
					Pistaslibres.add(this.lista_pistas.get(i));
				}
			}
		}
		
		return(Pistaslibres);
	} 
}


