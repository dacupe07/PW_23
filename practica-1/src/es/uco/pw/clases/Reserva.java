package es.uco.pw.clases;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.text.*;
import java.util.*;
import java.io.*;

public class Reserva {
	private String idUsuario;
	private LocalDate fecha;
	private String hora;
	private int duracion;
	private String pista;
	private float precio;
	private float descuentoAntigüedad;
	private dificultad especialidad;
	ArrayList<String> reservas = new ArrayList<String>();
	
	public Reserva() {
		
	}
	
	
	/**
	 * @return the idUsuario
	 */
	public String getIdUsuario() {
		return idUsuario;
	}
	/**
	 * @param idUsuario the idUsuario to set
	 */
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	/**
	 * @return the fecha
	 */
	public LocalDate getFecha() {
		return fecha;
	}
	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate ld = LocalDate.parse(fecha, formatter);
		this.fecha = ld;
		
		
	}
	/**
	 * @return the hora
	 */
	public String getHora() {
		return hora;
	}
	/**
	 * @param hora the hora to set
	 */
	public void setHora(String hora) {
		this.hora = hora;
	}


	/**
	 * @return the duracion
	 */
	public int getDuracion() {
		return duracion;
	}
	/**
	 * @param duracion the duracion to set
	 */
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	/**
	 * @return the precio
	 */
	public float getPrecio() {
		return precio;
	}
	/**
	 * @param precio the precio to set
	 */
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	/**
	 * @return the descuentoAntigüedad
	 */
	public float getDescuentoAntigüedad() {
		return descuentoAntigüedad;
	}
	/**
	 * @param descuentoAntigüedad the descuentoAntigüedad to set
	 */
	public void setDescuentoAntigüedad(float descuentoAntigüedad) {
		this.descuentoAntigüedad = descuentoAntigüedad;
	}
	/**
	 * @return the pista
	 */
	public String getPista() {
		return pista;
	}


	/**
	 * @param pista the pista to set
	 */
	public void setPista(String pista) {
		this.pista = pista;
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


	public String toString() {
		String info = "\n--- INFORMACION INTRODUCIDA --- \n ID Usuario=" + idUsuario + ", Fecha=" + fecha +", Hora=" + hora + ", Duracion=" + duracion
				+ ", Pista=" + pista + ", Especialidad=" + especialidad +", Precio=" + precio + ", Descuento por Antigüedad=" + descuentoAntigüedad + "\n\n";
		return info;
	}
	
	public  void crearReservaBono() {
		int tipo;
		BonoReservaCreator reservaBono = new BonoReservaCreator();
		Scanner lector = new Scanner(System.in);
		
		
		System.out.println("¿Qué tipo de reserva desea realizar? 0.ADULTOS//1.FAMILIAR//2.INFANTIL \n");
		System.out.println(" Introduce el numero perteneciente a la opción deseada: ");
		tipo = lector.nextInt();
		
		switch(tipo) {
		
		case 0:
			reservaBono.createReservaAdultos();
			break;
		case 1:
			reservaBono.createReservaFamiliar();
			break;
		case 2:
			reservaBono.createReservaInfantil();
			break;
		}
		
		
	}
	
	public  void crearReservaIndividual() {
		int tipo;
		IndividualReservaCreator reservaIndividual = new IndividualReservaCreator();
		Scanner lector = new Scanner(System.in);
		
		
		System.out.println("¿Qué tipo de reserva desea realizar? 0.ADULTOS//1.FAMILIAR//2.INFANTIL \n");
		System.out.println(" Introduce el numero perteneciente a la opción deseada: ");
		tipo = lector.nextInt();
		
		switch(tipo) {
		
		case 0:
			reservaIndividual.createReservaAdultos();
			
			break;
		case 1:
			reservaIndividual.createReservaFamiliar();
			break;
		case 2:
			reservaIndividual.createReservaInfantil();
			break;
		}
		
		
	}
	
	public float calcularPrecio(int duracion) {
		float precio;
		
		if(duracion == 60 ) {
			precio = 20;
		}
		else if(duracion == 90) {
			precio = 30;
		}
		else
		{
			precio = 40;
		}
		
		return precio;
	}
	
	//ELIMINAR RESERVA
	public void eliminarReserva(String nombre, String fecha, String hora, String pista) {
		try
		{
			File f1 = new File("Reservas.txt");
			File temp = new File("ReservasAuxiliar.txt");
			
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
				if(!trimmedLine.contains(nombre) || !trimmedLine.contains(fecha) || !trimmedLine.contains(hora) || !trimmedLine.contains(pista))
				{
					writer.write(lineactual + "\n");
				}
				else if(trimmedLine.contains(nombre) || trimmedLine.contains(fecha) || trimmedLine.contains(hora) || trimmedLine.contains(pista) ) {
					System.out.println("Reserva Eliminada Correctamente.\n");
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
	
	public void listarReservas() throws IOException {
		String linea;

		// ABRIMOS EL FICHERO DE LECTURA
		FileReader fichero = new FileReader("Reservas.txt");
		BufferedReader breader = new BufferedReader(fichero);

		while((linea = breader.readLine()) != null)
		{
			String trimmedLine = linea.trim();
			reservas.add(linea);
		}
		
		System.out.print("\n");
		breader.close();

		for(String aux: reservas)
		{
			System.out.println(aux + "\n");
		}
		System.out.println("\n");
	}
	
	public void listarReservasPistaDia(String pista, String fecha) throws IOException {
		String linea;

		// ABRIMOS EL FICHERO DE LECTURA
		FileReader fichero = new FileReader("Reservas.txt");
		BufferedReader breader = new BufferedReader(fichero);

		while((linea = breader.readLine()) != null)
		{
			String trimmedLine = linea.trim();
			reservas.add(linea);
		}
		
		System.out.print("\n");
		breader.close();

		for(String aux: reservas)
		{
			if(aux.contains(pista) && aux.contains(fecha)) {
				System.out.println(aux + "\n");
			}
		}
		System.out.println("\n");
	}
	
	
}

