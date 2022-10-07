package es.uco.pw.clases;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class IndividualReservaCreator extends ReservaCreator {
	
	@Override
	public ReservaAdultos createReservaAdultos() {
		
		//DECLARACIÓN DE VARIABLES AUXILIARES
		Scanner lector = new Scanner(System.in);
		Boolean controlador = true;
		String nombre;
		String fechaAux;
		int duracion;
		String pista;
		int nAdultos;
		float precio;
		int antiguedad=3;
		
		
		ReservaAdultos reservaAdultos = new ReservaAdultos();
		
		
		//COMENZAMOS PIDIENDO DATOS POR PANTALLA
		
		//NOMBRE
		System.out.println("\nPara la realización de la reserva, introduzca su nombre: ");
		nombre= lector.nextLine();
		reservaAdultos.setIdUsuario(nombre);
		
		
		//FECHA
		/*
		
		System.out.println("\nIntroduzca la fecha y hora de la reserva en el siguiente formato: dd/mm/yyyy HH:mm:ss");
		fechaAux= lector.nextLine();
		
		try {
			SimpleDateFormat formato = new SimpleDateFormat("dd//MM//yyyy HH:mm:ss");
			Date fecha = formato.parse("12/07/2023 12:00:00");
			reservaAdultos.setFecha(fecha);
			System.out.println("Fecha: "+fecha);
			
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		
		*/
		System.out.println("\nIntroduce la duracion de la reserva en minutos (60 // 90 // 120):");
		duracion = lector.nextInt();
		
		if(duracion == 60 || duracion== 90 || duracion== 120) {
			reservaAdultos.setDuracion(duracion);
		}
		else
		{
			System.out.println("La duracion introducida no es válida. \n");
			System.exit(1);
		}
		
		System.out.println("\nIntroduzca el nombre de la Pista que desea reservar: ");
		pista= lector.nextLine();
		reservaAdultos.setPista(pista);
		
		System.out.println("\nIntroduzca el numero de participantes, incluyendo el que realiza la reserva: ");
		nAdultos= lector.nextInt();
		reservaAdultos.setnAdultos(nAdultos);
		
		System.out.println("Calculando precio de la reserva... \n");
		precio = reservaAdultos.calcularPrecio(duracion);
		//Calcular antiguedad;
		if(antiguedad>2)
		{
			System.out.println("Usted ha recibido un descuento de 10% por tener una atigüedad superior a 2 años.\n");
			precio = (float) (precio*0.90);
		}
		reservaAdultos.setPrecio(precio);
		System.out.print("El precion de la reserva es: "+reservaAdultos.getPrecio() + "\n\n");
		
		return reservaAdultos;
		
		try 
		{
			//CREAMOS EL FICHERO EN MODO ESCRITURA
			File fichero = new File(pathname:"Reservas.txt");
			FileWriter fw = new FileWriter(fichero, true);
			
			fw.write("Nombre: " + reservaAdultos.getIdUsuario() + ". Pista: "+reservaAdultos.getPista() +". Fecha: "+reservaAdultos.getFecha()+". Duracion: "+reservaAdultos.getDuracion()
					+". NºAdultos: "+reservaAdultos.getnAdultos()+". Especialidad: "+reservaAdultos.getEspecialidad()+"Descuento: "+reservaAdultos.getDescuentoAntigüedad());
			
			fw.close();
			
		}
		catch(Exception e) {
			System.out.print("\nERROR AL ESCRIBIR EN EL FICHERO " + e.getMessage() + "\n");
		}
		
	}
	
	@Override
	public ReservaFamiliar createReservaFamiliar() {
		ReservaFamiliar reserva = new ReservaFamiliar();
		
		return reserva;
	}
	
	@Override
	public ReservaInfantil createReservaInfantil() {
		ReservaInfantil reserva = new ReservaInfantil();
		
		return reserva;
	}
}



