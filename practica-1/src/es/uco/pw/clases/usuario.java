package es.uco.pw.clases;


import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.text.*;
import java.util.*;
import java.io.*;



public class usuario {
	
	private String nombre;
	private LocalDate fecha_nacimiento;
	private LocalDate fecha_inscripcion;
	private String email;
	
	private static usuario Usuario = null;
	
	public usuario() {}
	
	public static usuario getUsuario(String nombre, LocalDate fecha_nacimiento, LocalDate fecha_inscripcion, String email) {
		
		if(Usuario == null) {
			
			Usuario = new usuario(nombre, fecha_nacimiento, fecha_inscripcion, email);
		}
		
		return Usuario;
	}
	
	public usuario(String nombre, LocalDate fecha_nacimiento, LocalDate fecha_inscripcion, String email) {
	
		this.nombre = nombre;
		this.fecha_nacimiento = fecha_nacimiento;
		this.fecha_inscripcion = fecha_inscripcion;
		this.email = email;
	}
	
	//DECLARACIÓN DE GET Y SET	

	/**
	 * Get de la variable nombre
	 * 
	 * @return nombre
	 */
	
	public String getNombre() {
		
		return nombre;
	}
	

	/**
	 * Set de la variable nombre
	 * 
	 * @return nombre
	 */
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
		
	/**
	 * Get de la variable fecha_nacimiento
	 * 
	 * @return fecha_nacimiento
	 */
	
	public LocalDate getFechaNacimiento() {
		
		return fecha_nacimiento;
	}
	

	/**
	 * Set de la variable fecha_nacimiento
	 * 
	 * @return fecha_nacimiento
	 */
	
	public void setFechaNacimiento(String fecha) throws ParseException {		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate ld = LocalDate.parse(fecha, formatter);
		this.fecha_nacimiento = ld;
	}
	
	
	/**
	 * Get de la variable fecha_inscripcion
	 * 
	 * @return fecha_inscripcion
	 */
	
	public LocalDate getFechaInscripcion() {
		
		return fecha_inscripcion;
	}
	

	/**
	 * Set de la variable fecha_inscripcion
	 * 
	 * @return fecha_inscripcion
	 */
	
	public void setFechaInscripcion() throws ParseException {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate localdatenow = LocalDate.now();
		String ld_str = localdatenow.format(formatter);
		LocalDate ld = LocalDate.parse(ld_str, formatter);
		this.fecha_inscripcion = ld;

	}
	
	/**
	 * Get de la variable email
	 * 
	 * @return email
	 */
	
	public String getEmail() {
		
		return email;
	}
	

	/**
	 * Set de la variable email
	 * 
	 * @return email
	 */
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Método toString
	 * 
	 * 
	 * @return fecha_inscripcion
	 */

	public String toString() {
		String info = "\n---INFORMACION INTRODUCIDA --- \nNombre y apellidos: " + this.nombre + "\nFecha de nacimiento: " + this.fecha_nacimiento + "\nFecha de inscripcion: " + this.fecha_inscripcion + "\nEmail: " + this.email + "\n\n";
		
		return info;
	}
	
	/**
	 * Método calcularAntiguedad
	 * 
	 * 
	 * @return fecha_inscripcion
	 */
	
	public long calcularAntiguedad(LocalDate fecha_inscripcion) {
		long antiguedad = ChronoUnit.YEARS.between(fecha_inscripcion, LocalDate.now());
		
		return antiguedad;
		
	}

	// CREACION DE LA FUNCION PARA DAR DE ALTA A UN USUARIO
	
	/**
	 * Esta funcion se encarga de dar de alta a un usuario
	 * 
	 * @throws IOException, FileNotFoundException, ParseException
	 */

	public void darAltaUsuario() throws IOException, FileNotFoundException, ParseException	{
		Scanner lector = new Scanner(System.in);
		Boolean controlador = true;
		
		// 1º Paso pedimos los datos y estos son introducidos por los setters
		
		System.out.print("Introduce nombre y apellidos del usuario: ");
		nombre = lector.nextLine();
		setNombre(nombre);
		
		System.out.print("Introduce la fecha de nacimiento del usuario (dd-MM-yyyy): ");
		String fecha_nacimiento = lector.nextLine();
		setFechaNacimiento(fecha_nacimiento);
		
		System.out.print("Introduce el email del usuario: ");
		email = lector.nextLine();
		setEmail(email);
		
		try	{
			// CREACION DEL FICHERO EN MODO ESCRITURA
			File fichero = new File("Usuarios.txt");
			FileWriter fw = new FileWriter(fichero, true);
			BufferedReader reader = new BufferedReader(new FileReader(fichero));
		
			String lineactual;
		
			// REALIZAMOS EL CONTROL PARA QUE NO SALGA DEL FICHERO
			while((lineactual = reader.readLine()) != null || controlador == false)	{
				String trimmedLine = lineactual.trim();
			
				// COMPROBAMOS QUE LA LINEA TENGA EL EMAIL INTRODUCIDO, EN CASO CONTRARIO NO SE INTRODUCE EL FICHERO DE ESCRITURA
				if(trimmedLine.contains(email))	{
					System.out.print("\nEl email que has introducido ya se encuentra registrado\n");
					controlador = false;
				}
			}
		
			if(controlador == true)	{
				// ESCRIBIMOS EN EL FICHERO LOS DATOS QUE INTRODUCIMOS ANTERIORMENTE
				fw.write("Email: " + getEmail() + ". Nombre: " + getNombre() + ". Fecha de nacimiento: " + getFechaNacimiento() + ". Fecha de inscripcion: " + getFechaInscripcion() + "\n");
			}
			
			// CERRAMOS EL FICHERO
			fw.close();
			reader.close();
		}
		
		catch(Exception e)	{
			System.out.print("\nError al escribir en el fichero" + e.getMessage() + "\n");
		}
	}
	
	public void darBajaUsuario(String email) throws IOException, FileNotFoundException
	{
		File f1 = new File("Usuarios.txt");
		File temp = new File("UsuariosAuxiliar.txt");
		
		// CREAMOS EL WRITER Y EL READER PARA LOS FICHEROS
		BufferedWriter writer = new BufferedWriter(new FileWriter(temp));
		BufferedReader reader = new BufferedReader(new FileReader(f1));
		
		String lineactual;
		
		// CONTROLAMOS QUE NO SE SALGA DEL FICHERO
		while((lineactual = reader.readLine()) != null)
		{
			// trim() -> Para eliminar los espacios al leer
			String trimmedLine = lineactual.trim();
			
			// COMPROBAMOS SI LA LINEA TIENE EL EMAIL INTRODUCIDO, SI NO LO TIENE SE INTRODUCE EN EL FICHERO DE ESCRITURA
			if(!trimmedLine.contains(email))	{
				writer.write(lineactual + "\n");
			}
			else {
				System.out.print("\n¡Usuario eliminado con exito!\n");
				
			}
		}
		
		// CIERRE DE LOS FICHEROS
		writer.close();
		reader.close();
		
		// BORRAMOS EL FICHERO ORIGINAL Y AL NUEVO LE CAMBIAMOS EL NOMBRE POR EL DEL ORIGINAL
		f1.delete();
		temp.renameTo(f1);
		
	}
	
	/**
	 * Esta funcion muestra todos los usuarios que se encuentran en el fichero
	 * 
	 * @return Usuarios
	 * @throws IOException, FileNotFoundException
	 */
	public ArrayList<String> listarUsuarios() throws IOException, FileNotFoundException
	{
		ArrayList<String> Usuarios = new ArrayList<String>();
		String linea;
		
		// ABRIMOS EL FICHERO DE LECTURA
		FileReader fichero = new FileReader("Usuarios.txt");
		BufferedReader breader = new BufferedReader(fichero);
		
		// MOSTRAMOS TODOS LOS USUARIOS POR PANTALLA
		while((linea = breader.readLine()) != null)
		{
			Usuarios.add(linea);
		}
		
		System.out.print("\n");
		breader.close();
		return Usuarios;
	}
	
	/**
	 * Esta funcion sirve para editar al usuario que quieras, mediante su email se podra identificar el usuario que quiere modificar
	 * 
	 * @param email
	 * @throws IOException, FileNotFoundException, ParseException
	 */
	public void actualizarUsuario(String email) throws IOException, FileNotFoundException, ParseException	{
		Scanner lector = new Scanner(System.in);
		Boolean controlador = true;
		
		// ABRIMOS LOS FICHEROS DE ESCRITURA Y LECTURA
		File f2 = new File("Usuarios.txt");
		File temp2 = new File("UsuariosAuxiliar.txt");
		BufferedReader reader = new BufferedReader(new FileReader(f2));

		BufferedReader reader2 = new BufferedReader(new FileReader(f2));
		BufferedWriter writer2 = new BufferedWriter(new FileWriter(temp2));
		
		String linea2;
		
		while((linea2 = reader2.readLine()) != null)
		{
			String trimmedLine = linea2.trim();
			
			// VEMOS SI LA LINEA CONTIENE EL EMAIL INTRODUCIDO, SI NO LO CONTIENE SE INTRODUCE EN EL FICHERO DE ESCRITURA
			if(!trimmedLine.contains(email))	{
				writer2.write(linea2 + "\n");
			}
			
			else {
				System.out.print("Introduce nombre y apellidos del usuario: ");
				String nombre = lector.nextLine();
				setNombre(nombre);
				
				System.out.print("Introduce la fecha de nacimiento del usuario (dd-MM-yyyy): ");
				String fecha_nacimiento = lector.nextLine();
				setFechaNacimiento(fecha_nacimiento);
				
				
				System.out.print("Introduce el email del usuario: ");
				email = lector.nextLine();
				setEmail(email); 
				
				String lineactual;
				
				// REALIZAMOS EL CONTROL PARA QUE NO SALGA DEL FICHERO
				while((lineactual = reader.readLine()) != null || controlador == false)	{
					String trimmedLine2 = lineactual.trim();
				
					// COMPROBAMOS QUE LA LINEA TENGA EL EMAIL INTRODUCIDO, EN CASO CONTRARIO NO SE INTRODUCE EL FICHERO DE ESCRITURA
					if(trimmedLine2.contains(email))	{
						System.out.print("\nEl email que has introducido ya se encuentra registrado\n");
						controlador = false;
					}
				}
			
				if(controlador == true)	{
					// ESCRIBIMOS EN EL FICHERO LOS DATOS QUE INTRODUCIMOS ANTERIORMENTE
					writer2.write("Email: " + getEmail() + ". Nombre: " + getNombre() + ". Fecha de nacimiento: " + getFechaNacimiento() + ". Fecha de inscripcion: " + getFechaInscripcion() + "\n");
				}
								
			}
		}
		
		
		reader.close();
		writer2.close();
		reader2.close();
		
		f2.delete();
		temp2.renameTo(f2);
	}
	
}
