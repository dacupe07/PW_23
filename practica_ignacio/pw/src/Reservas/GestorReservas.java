package Reservas;

import java.io.*;
import java.util.*;

import FactoriaReservas.FactoriaReservasBono;
import FactoriaReservas.FactoriaReservasIndividual;
import Kart.Kart;
import Kart.estado;
import Pistas.Dificultad;
import Pistas.Pista;
import Usuarios.Usuario;

import java.text.SimpleDateFormat;

/**
 * Clase para gestionar las reservas 
 * @author Ignacio Garcia Cabezuelo */
public class GestorReservas {
	
	private ArrayList<Pista> lista_pistas;
	
	private ArrayList<Usuario> lista_usuarios;
	
	private ArrayList<Reservas> lista_reservas;
	
	/**
	 * Constructor de la clase
	 */
	public GestorReservas() {
		
		this.lista_usuarios= new ArrayList<Usuario>();
		this.lista_pistas=new ArrayList<Pista>();
		this.lista_reservas=new ArrayList<Reservas>();
	}
	
	/**
	 * Carga los usuarios desde un fichero y los mete en la lista
	 * @param prop Archivo properties que contiene la ruta donde esta el archivo del que vamos a leer
	 */	
	public void cargarUsuarios(Properties prop) {
		// Limpiamos la lista de usuarios
		
		this.lista_usuarios = new ArrayList<Usuario>();
		
		String nombreFichero = prop.getProperty("ficheroUsuario");
		
		BufferedReader br = null; // Variable para leer el fichero
		
		try {
			
			// Abrimos el fichero de texto
			
			 br = new BufferedReader(new FileReader(nombreFichero));
			
			String linea = br.readLine(); // Leemos la primera linea del fichero
			
			// Repetimos mientras no llegue al final del fichero
			
			while(linea != null) {
				
				// Creamos un usuario vacio
				
				Usuario usuario_ = new Usuario();
				
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
					
					// Guardamos el nombre del usuario
					
					if(i==0) {
						usuario_.setNombre(partes.get(i));
					}
					
					// Guardamos los apellidos
					
					else if(i == 1) {
						usuario_.setApellidos(partes.get(i));
					}
					
					// Guardamos la fecha de nacimiento
					
					else if(i== 2) {
						SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
						usuario_.setFechaNacimiento(formato.parse(partes.get(i)));
					}
					
					// Guardamos la fecha inscripcion
					
					else if(i == 3) {
						SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
						usuario_.setFechainscripcion(formato.parse(partes.get(i)));
					}
					
					// Guardamos el correo
					
					else if(i == 4) {
						
						usuario_.setCorreo(partes.get(i));
							
					}
					
				}
				
				// Almacenamos los datos del usuario en la lista
				
				this.lista_usuarios.add(usuario_);
				
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
	 * Carga las reservas desde un fichero y las mete en la lista
	 * @param prop Archivo properties que contiene la ruta donde esta el archivo del que vamos a leer
	 */
	public void cargarReservas(Properties prop) {
		// Limpiamos la lista de usuarios
		
		this.lista_reservas = new ArrayList<Reservas>();
		
		String nombreFichero = prop.getProperty("ficheroReserva");
		
		BufferedReader br = null; // Variable para leer el fichero
		
		try {
			
			// Abrimos el fichero de texto
			
			 br = new BufferedReader(new FileReader(nombreFichero));
			
			String linea = br.readLine(); // Leemos la primera linea del fichero
			
			// Repetimos mientras no llegue al final del fichero
			
			while(linea != null) {
				
				// Creamos una reserva vacia
				
				Reservas reserva_=new Reservas();
				
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
					
					// Guardamos el correo del usuario
					
					if(i==0) {
						reserva_.setCorreo(partes.get(i));
					}
					
					// Guardamos la fecha de la reserva
					
					else if(i == 1) {
						
				        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				        reserva_.setFechareserva(formato.parse(partes.get(i)));
					}
					
					// Guardamos la duracion
					
					else if(i== 2) {
						reserva_.setDuracion(Integer.parseInt(partes.get(i)));
					}
					
					// Guardamos la pista
					
					else if(i == 3) {
					
						reserva_.setPista(partes.get(i));
					}
					
					// Guardamos el precio
					
					else if(i == 4) {
						
						reserva_.setPrecio(Float.parseFloat(partes.get(i)));
							
					}
					
					// Guardamos descuento
					else if(i == 5) {
						
						reserva_.setDescuento(Float.parseFloat(partes.get(i)));
							
					}
					
				}
				
				// Almacenamos los datos de la reser en la lista
				
				this.lista_reservas.add(reserva_);
				
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
	 * Metodo para crear una reserva individual
	 * @param entrada Buffer de entrada
	 * @return True si se ha podido hacer la reserva, false en caso contrario
	 */
	public boolean hacerReservaIndividual(Scanner entrada) {
		
		FactoriaReservasIndividual factoria= new FactoriaReservasIndividual();
		
		System.out.print("Introduce el tipo de la reserva");
		
		String tipo=entrada.nextLine();
		Reservas reserva;
		
		if((reserva=factoria.getReserva(tipo))!=null) {
			
			System.out.print("Introduce el correo del usuario que va a hacer la reserva");
			reserva.setCorreo(entrada.nextLine());
			
			if(comprobacionUsuario(reserva.getCorreo()) == false) { return false;}
			
			System.out.print("Introduce la pista a reservar");
			reserva.setPista(entrada.nextLine());
			
			System.out.print("Introduce el numero de participantes");
			int participantes=Integer.parseInt(entrada.nextLine());
			
			if(comprobacionPista(reserva.getPista(),participantes,tipo) == false) {return false;}
			
			System.out.print("Introduce la fecha y hora de la reserva");
			String fecha = entrada.nextLine();

	        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	        Date testDate = null;
	        Date date1 = new Date();
	        String date = fecha;

	        try{

	            testDate = df.parse(date);
	            int dias = (int) ((date1.getTime()-testDate.getTime()) / 86400000);
	            if(dias<1) {return false;}
	            reserva.setFechareserva(testDate);

	        } catch (Exception e){ System.out.println("invalid format");}

	        System.out.print("Introduce la duracion");
	        int duracion=Integer.parseInt(entrada.nextLine());
			reserva.setDuracion(duracion);
			
			if(duracion==60) {reserva.setPrecio(20);}
			if(duracion==90) {reserva.setPrecio(30);}
			if(duracion==60) {reserva.setPrecio(40);}
			
			if(antiguedad(reserva.getCorreo())>2) {reserva.setDescuento((float)(reserva.getPrecio()*0.1));}
			else {reserva.setDescuento(0);}
			this.lista_reservas.add(reserva);
			return true;
		}
		
		else {
			return false;
		}
	}
	
	/**
	 * Metodo para crear una reserva en un bono
	 * @param entrada Buffer de entrada
	 * @return True si se ha podido hacer la reserva, false en caso contrario
	 */
	public boolean hacerReservaBono(Scanner entrada) {
		
		FactoriaReservasBono factoria= new FactoriaReservasBono();
		
		System.out.print("Introduce el tipo de la reserva");
		
		String tipo=entrada.nextLine();
		Reservas reserva;
		
		if((reserva=factoria.getReserva(tipo))!=null) {
			
			System.out.print("Introduce el correo del usuario que va a hacer la reserva");
			reserva.setCorreo(entrada.nextLine());
			
			if(comprobacionUsuario(reserva.getCorreo()) == false) { return false;}
			
			System.out.print("Introduce la pista a reservar");
			reserva.setPista(entrada.nextLine());
			
			System.out.print("Introduce el numero de participantes");
			int participantes=Integer.parseInt(entrada.nextLine());
			
			if(comprobacionPista(reserva.getPista(),participantes,tipo) == false) {return false;}
			
			System.out.print("Introduce la fecha y hora de la reserva");
			String fecha = entrada.nextLine();

	        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	        Date testDate = null;
	        Date date1 = new Date();
	        String date = fecha;

	        try{

	            testDate = df.parse(date);
	            int dias = (int) ((date1.getTime()-testDate.getTime()) / 86400000);
	            if(dias<1) {return false;}
	            reserva.setFechareserva(testDate);

	        } catch (Exception e){ System.out.println("invalid format");}

	        System.out.print("Introduce la duracion");
	        int duracion=Integer.parseInt(entrada.nextLine());
			reserva.setDuracion(duracion);
			
			if(duracion==60) {reserva.setPrecio(20);}
			if(duracion==90) {reserva.setPrecio(30);}
			if(duracion==60) {reserva.setPrecio(40);}
			
			if(antiguedad(reserva.getCorreo())>2) {reserva.setDescuento((float)(reserva.getPrecio()*0.1));}
			else {reserva.setDescuento(0);}
			this.lista_reservas.add(reserva);
			return true;
		}
		
		else {
			return false;
		}
	}
	
	/**
	 * Registra las reservas, vuelca la informacion de la lista en el fichero
	 * @param prop Archivo properties que contiene la ruta del archivo donde vamos a escribir las reservas
	 */
	public void RegistroReservas(Properties prop) {
		
		FileWriter fichero = null;
		
		try {
			
			// Abrimos el fichero de usuarios
			
			fichero = new FileWriter(prop.getProperty("ficheroReserva"));
			
			BufferedWriter escritura = new BufferedWriter(fichero);
			
			// Escribimos linea a linea en el fichero
			
			for(int i=0; i < this.lista_reservas.size(); i++) {
				
				// Correo del usuario que hace la reserva
				
				escritura.write(this.lista_reservas.get(i).getCorreo() + ",");
				
				// Fecha y hora de la reserva
				
				escritura.write(this.lista_reservas.get(i).getFechareserva() + ",");
				
				// Duracion
				
				escritura.write(this.lista_reservas.get(i).getDuracion() + ",");
				
				// Nombre de la pista
				
				escritura.write(this.lista_reservas.get(i).getPista()+",");
				
				// Precio
				
				escritura.write(this.lista_reservas.get(i).getPrecio()+",");
				
				//Descuento
				escritura.write(this.lista_reservas.get(i).getDescuento()+",");
				
				
				escritura.newLine(); // Escribimos una nueva linea
			}
			escritura.close(); // Cerramos el fichero
			
		}catch(Exception ex) {
			System.out.println("Se ha producido un error al abrir el fichero: " + fichero);
			return; }
		}
	
	/**
	 * Comprueba si un usuario puede hacer la reserva
	 * @param correo_ Correo del usuario
	 * @return Devuelve true si puede hacer la reserva y false en caso contrario
	 */
	public boolean comprobacionUsuario(String correo_) {
		
		for(int i=0; i < this.lista_usuarios.size(); i++) {
			
			if(this.lista_usuarios.get(i).getCorreo().equals(correo_)) {
				if(this.lista_usuarios.get(i).calcularEdad()>=18) {
					return true;
				} 
			}
		}
		return false; 
	}
	
	/**
	 * Calcula la antiguedad de un usuario
	 * @param correo_ Correo del usuario
	 * @return Un entero con la antiguedad en años del usuario
	 */
	public int antiguedad(String correo_) {
		
		for(int i=0; i < this.lista_usuarios.size(); i++) {
			
			if(this.lista_usuarios.get(i).getCorreo().equals(correo_)) {
				return(this.lista_usuarios.get(i).calcularAntiguedad()); 
					
				
			}
		}
		return -1;
		 
	}
	
	/**
	 * Comprueba si una pista es valida para hacer la reserva
	 * @param nombre_ Nombre de la pista
	 * @return True si es valida, false si no lo es
	 */
	public boolean comprobacionPista(String pista_, int participantes,String tipo) {
		
		for(int i=0; i < this.lista_pistas.size(); i++) {
			
			if(this.lista_pistas.get(i).getNombre().equals(pista_)) {
				if(this.lista_pistas.get(i).getDisponible()) {
					if(this.lista_pistas.get(i).consultarKartsDisponibles().size()<=participantes) {
						if(this.lista_pistas.get(i).getDificultad()==Dificultad.valueOf(tipo)) {
					return true;} }
				} 
			}
		}
		return false; 
	}
}
