package es.uco.pw.clases;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Bono {
	
	private int IDBono;
	private String usuario;
	private dificultad especialidad;
	private int sesiones=5;
	private String caducidad;
	private ArrayList<String> bonos = new ArrayList<String>();
	
	
	public Bono() {
		
	}

	/**
	 * @return the iDBono
	 */
	public int getIDBono() {
		return IDBono;
	}

	/**
	 * @param iDBono the iDBono to set
	 */
	public void setIDBono(int iDBono) {
		IDBono = iDBono;
	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the especialidad
	 */
	public dificultad getEspecialidad() {
		return especialidad;
	}

	/**
	 * @param especialidad the especialidad to set
	 */
	public void setEspecialidad(dificultad especialidad) {
		this.especialidad = especialidad;
	}

	/**
	 * @return the sesiones
	 */
	public int getSesiones() {
		return sesiones;
	}

	/**
	 * @param sesiones the sesiones to set
	 */
	public void setSesiones(int sesiones) {
		this.sesiones = sesiones;
	}

	/**
	 * @return the caducidad
	 */
	public String getCaducidad() {
		return caducidad;
	}

	/**
	 * @param caducidad the caducidad to set
	 */
	public void setCaducidad(String caducidad) {
		this.caducidad = caducidad;
	}

	@Override
	public String toString() {
		return "\n--- INFORMACION INTRODUCIDA ---\n IDBono=" + IDBono + ", Usuario=" + usuario + ", Especialidad=" + especialidad + ", Sesiones="
				+ sesiones + ", Caducidad=" + caducidad + "\n\n";
	}
	
	
	public void sacarBono() throws IOException, FileNotFoundException {
		Bono b = new Bono();
		String nombre;
		String caducidad;
		int especialidad;
		int IDBono;

		Scanner lector = new Scanner(System.in);
		Boolean controlador = true;
		int aux_disp;
		
		//Establecemos el identificador del bono
		IDBono= (int)(Math.random()*100+1);
		b.setIDBono(IDBono);
		
		//Introducimos el nombre de la persona que saca el bono
		System.out.println("Introduce el nombre de usuario: ");
		nombre = lector.nextLine();
		b.setUsuario(nombre);
		
		//Establecemos la especialidad del bono:
		System.out.println("Introduce la especialidad del bono que desea sacar: (0.Infantil// 1.Familiar// 2. Adultos) \n");
		System.out.println(" Introduce el numero perteneciente a la opción deseada: ");
		especialidad = lector.nextInt();
		lector.nextLine();
		
		switch(especialidad) {
			case 0:
				b.setEspecialidad(dificultad.INFANTIL);
				break;
			case 1:
				b.setEspecialidad(dificultad.FAMILIAR);
				break;
			case 2:
				b.setEspecialidad(dificultad.ADULTOS);
				break;
			default:
				System.out.println("La opción introducida no es válida.\n");
				break;
		}
		
		//Establecer la Fecha de caducidad
		System.out.println("\n Introduce la fecha de caducidad, debe ser 1 año posterior a la fecha actual: (dd/mm/yyyy) ");
		caducidad= lector.nextLine();
		b.setCaducidad(caducidad);
		
		// CREAMOS EL FICHERO EN MODO ESCRITURA
		
		try {
			File fichero = new File("Bonos.txt");
			FileWriter fw = new FileWriter(fichero, true);
			BufferedReader reader = new BufferedReader(new FileReader(fichero));
			
			String lineactual; // Variable con la que controlamos la linea que se esta leyendo
			
			// REALIZAMOS EL CONTROL PARA QUE NO SALGA DEL FICHERO
			while((lineactual = reader.readLine()) != null || controlador == false)
			{
				String trimmedLine = lineactual.trim();
				
				String id_aux = String.valueOf(b.getIDBono());
				
				// COMPROBAMOS QUE LA LINEA TENGA EL ID DEL KART INTRODUCIDO, PARA EVITAR REPETICIONES
				if(trimmedLine.contains(id_aux))
				{
					System.out.print("\n El Bono con dicho identificador ya está registrado.\n");
					controlador = false;
				}
			}
			
			if(controlador == true)
			{
				// ESCRIBIMOS EN EL FICHERO LOS DATOS INTRODUCIDOS
				fw.write("ID: " + b.getIDBono() + ". Usuario: " + b.getUsuario() + ". Especialidad: " + b.getEspecialidad() 
						+ ". Sesiones: " + b.getSesiones() + ". FechaCaducidad: "+b.getCaducidad()+"\n");
			}

			// CERRAMOS EL FICHERO
			fw.close();
			reader.close();
			
			
		} catch (IOException e) {
			System.out.print("\nERROR AL ESCRIBIR EN EL FICHERO " + e.getMessage() + "\n");
		}
		
		b.toString();
	}
	
	public ArrayList<String> listarBonos() throws IOException, FileNotFoundException {
		String linea;

		// ABRIMOS EL FICHERO DE LECTURA
		FileReader fichero = new FileReader("Bonos.txt");
		BufferedReader breader = new BufferedReader(fichero);

		while((linea = breader.readLine()) != null)
		{
			bonos.add(linea);
		}
		System.out.print("\n");
		breader.close();

		for(String bono: bonos)
		{
			System.out.println(bono + "\n");
			System.out.print("\n");
		}
		System.out.println("\n");

		return bonos;
	}
	
	public Boolean tieneBono(String nombre, String especialidad) throws IOException, FileNotFoundException {
		Bono bono= new Bono();
		ArrayList<String> bonos = bono.listarBonos();
		
		for(String aux: bonos ) {
			if(aux.contains(nombre) && aux.contains(especialidad)) {
				if(aux.contains("Sesiones: 0")) {
					System.out.println("SESIONES AGOTADAS.");
					return false;
				}
				System.out.println("Usuario Correcto. Dispone de bono en vigor de la especialidad seleccionada.");
				return true;
			}
		}
		
		return false;
	}
	
	public void eliminarBono(String nombre) {
		try
		{
			File f1 = new File("Bonos.txt");
			File temp = new File("BonosAuxiliar.txt");
			
			// CREAMOS EL WRITER Y EL READER PARA LOS FICHEROS
			FileWriter writer = new FileWriter(temp, true);
			BufferedReader reader = new BufferedReader(new FileReader(f1));
			
			String lineactual;
			
			// CONTROLAMOS QUE NO SE SALGA DEL FICHERO
			while((lineactual = reader.readLine()) != null)
			{
				// trim() -> Para eliminar los espacios al leer
				String trimmedLine = lineactual.trim();
				
				// COMPROBAMOS SI LA LINEA TIENE EL NOMBRE INTRODUCIDO, SI NO LO TIENE SE INTRODUCE EN EL FICHERO DE ESCRITURA
				if(!trimmedLine.contains(nombre))
				{
					writer.write(lineactual + "\n");
				}
			}
			
			// CIERRE DE LOS FICHEROS
			writer.close();
			reader.close();
			
			// BORRAMOS EL FICHERO ORIGINAL Y AL NUEVO LE CAMBIAMOS EL NOMBRE POR EL DEL ORIGINAL
			f1.delete();
			temp.renameTo(f1);
			
			
		}catch (IOException e) {
			System.out.print("\nERROR AL ESCRIBIR EN EL FICHERO " + e.getMessage() + "\n");
		}
		
	}
}
