package Usuarios;

import java.util.ArrayList;
import java.io.*;
import java.util.*;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Clase para gestionar los usuarios
 * @author Ignacio Garcia Cabezuelo */
public class GestorUsuario {
	
	private ArrayList<Usuario> lista_usuarios;
	
	/**
	 * Constructor de la clase
	 */
	public GestorUsuario() {
		this.lista_usuarios=new ArrayList<Usuario>();
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
	 * Modifica la informacion de un usuario
	 * @param usuario_ Usuario con toda la informacion nueva
	 */
	public void modificarInformacion(Usuario usuario_) {
		
		for(int i=0; i < this.lista_usuarios.size(); i++) {
			
			if(this.lista_usuarios.get(i).getCorreo().equals(usuario_.getCorreo())) {
				
				this.lista_usuarios.remove(this.lista_usuarios.get(i));
				this.lista_usuarios.add(usuario_);
			}
		}
	}
	
	/**
	 * Muestra por pantalla todos los usuarios
	 */
	public void listarUsuarios() {
		
		for(int i=0; i < this.lista_usuarios.size(); i++) {
		System.out.println(this.lista_usuarios.get(i).toString());
	
		}
	}
	
	/**
	 * Crea un usuario
	 * @param entrada Buffer de entrada
	 * @return Devuelve 0 si no tiene exito y 1 si lo tiene
	 */
	public int crearUsuarios(Scanner entrada) {
		
		// Creamos un usuario
		
		Usuario usuario_ = new Usuario();
		
		// Obtenemos el correo
		
		System.out.print("Introduce el correo del usuario: ");
		
		usuario_.setCorreo(entrada.nextLine());
		
		// Comprobamos si el usuario ya esta registrado
		
		if(comprobacionUsuario(usuario_.getCorreo()) == true) {
			System.out.println("Este Usuario ya existe");
			return 0; // No se crea
		}
		
		else {
			
			// Almacenamos el nombre
			
			System.out.print("Introduce el nombre : ");
			
			usuario_.setNombre(entrada.nextLine());
			
			// Almacenamos los apellidos
			System.out.print("Introduce los apellidos : ");
			
			usuario_.setApellidos(entrada.nextLine());
			
			// Almacenamos la fecha de nacimiento
			System.out.print("Introduce la fecha de nacimiento : ");
	        String fecha = entrada.nextLine();

	        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

	        Date testDate = null;

	        String date = fecha;

	        try{

	            testDate = df.parse(date);

	            usuario_.setFechaNacimiento(testDate);

	        } catch (Exception e){ System.out.println("invalid format"); return 0;}

	        
	     // Almacenamos la fecha de inscripcion
	     			System.out.print("Introduce la fecha de inscripcion : ");
	     	        String fecha_ = entrada.nextLine();

	     	        SimpleDateFormat df_ = new SimpleDateFormat("dd/MM/yyyy");

	     	        Date testDate_ = null;

	     	        String date_ = fecha_;

	     	        try{

	     	            testDate_ = df_.parse(date_);

	     	            usuario_.setFechainscripcion(testDate_);

	     	        } catch (Exception e){ System.out.println("invalid format"); return 0;}
	     	 
			
			// Anadimos el usuario a la lista
			
			this.lista_usuarios.add(usuario_);
			
			return 1; // Se ha anadido el usuario a la lista de usuarios
			
		}
	}

	/**
	 * Comprueba si un usuario ya existe
	 * @param correo_ Correo del usuario
	 * @return Devuelve true si existe y false si no existe
	 */
	public boolean comprobacionUsuario(String correo_) {
		
		for(int i=0; i < this.lista_usuarios.size(); i++) {
			
			if(this.lista_usuarios.get(i).getCorreo().equals(correo_)) {
				return true; 
			}
		}
		return false; 
	}
	
	/**
	 * Registra la informacion de los usuarios, vuelca la lista en un archivo
	 * @param prop Archivo properties que contiene la ruta del archivo a escribir
	 */
	public void RegistroUsuario(Properties prop) {
		
		FileWriter fichero = null;
		
		try {
			
			// Abrimos el fichero de usuarios
			
			fichero = new FileWriter(prop.getProperty("ficheroUsuario"));
			
			BufferedWriter escritura = new BufferedWriter(fichero);
			
			// Escribimos linea a linea en el fichero
			
			for(int i=0; i < this.lista_usuarios.size(); i++) {
				
				// Nombre del usuario
				
				escritura.write(this.lista_usuarios.get(i).getNombre() + ",");
				
				// Apellidos
				
				escritura.write(this.lista_usuarios.get(i).getApellidos() + ",");
				
				// Fecha Nacimiento
				
				escritura.write(this.lista_usuarios.get(i).getFechaNacimiento() + ",");
				
				// Fecha de inscripcion
				
				escritura.write(this.lista_usuarios.get(i).getFechainscripcion()+",");
				
				// Correo del usuario
				
				escritura.write(this.lista_usuarios.get(i).getCorreo()+",");
				
				
				escritura.newLine(); // Escribimos una nueva linea
			}
			escritura.close(); // Cerramos el fichero
			
		}catch(Exception ex) {
			System.out.println("Se ha producido un error al abrir el fichero: " + fichero);
			return;
		}
		
	}

	
}
