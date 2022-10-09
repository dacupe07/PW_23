package es.uco.pw.clases;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class IndividualReservaCreator extends ReservaCreator {
	
	//DECLARACIÓN DE VARIABLES AUXILIARES Y COMUNES
	Scanner lector = new Scanner(System.in);
	String email;
	String fecha;
	String hora;
	int duracion;
	String pista;
	float precio;
	float precioFinal;
	float descuento;
	int antiguedad=3;
	Boolean aux=false;
	dificultad especialidad;
	pista p = new pista();
	usuario u = new usuario();
	
	@Override
	public ReservaAdultos createReservaAdultos() {
		
		ReservaAdultos reservaAdultos = new ReservaAdultos();
		int nAdultos;
		
		
		//================COMENZAMOS PIDIENDO DATOS POR PANTALLA==================================
		
		//EMAIL
		System.out.println("\nPara la realización de la reserva, introduzca su email: ");
		email= lector.nextLine();
		
		try {
			aux = u.isUsuarioRegistrado(email);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		if(aux == true) {
			reservaAdultos.setIdUsuario(email);
		}else {
			System.out.println("\n El usuario no está registrado en el sistema. Registrese antes de realizar una reserva.\n ");
			System.out.println("Saliendo del gestor...");
			System.exit(1);
		}
		
		//FECHA
		System.out.println("\nIntroduzca la fecha con el siguiente formato: [dd-MM-yyyy]");
		fecha= lector.nextLine();
		reservaAdultos.setFecha(fecha);
		
		//HORA
		System.out.println("\nIntroduzca la hora con el siguiente formato: [hh:mm]24h");
		hora= lector.nextLine();
		reservaAdultos.setHora(hora);
		
		//DURACION DE LA RESERVA
		System.out.println("\nIntroduce la duracion de la reserva en minutos (60 // 90 // 120):");
		duracion = lector.nextInt();
		lector.nextLine();
		
		if(duracion == 60 || duracion== 90 || duracion== 120) {
			reservaAdultos.setDuracion(duracion);
		}
		else
		{
			System.out.println("La duracion introducida no es válida. \n");
			System.exit(1);
		}
		
		//PISTA QUE SE DESEA RESERVAR
		System.out.println("\nIntroduzca el nombre de la Pista que desea reservar: ");
		pista= lector.nextLine();
		
		
		try {
			aux = p.pistaCumpleCondiciones(pista, "ADULTOS", fecha, hora);
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		if(aux == true) {
			System.out.println("\n La pista cumple con las condiciones.");
			reservaAdultos.setPista(pista);
		}else {
			System.out.println("\n La pista no cumple con las condiciones. \n ");
			System.out.println("Saliendo del gestor...");
			System.exit(1);
		}
		
		
		//Nº DE ADULTOS PARTICIPANTES
		System.out.println("\nIntroduzca el numero de participantes, incluyendo el que realiza la reserva: ");
		nAdultos= lector.nextInt();
		reservaAdultos.setnAdultos(nAdultos);
		
		//PRECIO DE LA RESERVA 
		System.out.println("Calculando precio de la reserva... \n");
		precio = reservaAdultos.calcularPrecio(duracion);
		
		//ESPECIALIDAD
		reservaAdultos.setEspecialidad(dificultad.ADULTOS);
		
		//POSIBLE DESCUENTO EN FUNCIÓN DE LA ANTIGÜEDAD
		//Calcular antiguedad;
		if(antiguedad>2)
		{
			System.out.println("Usted ha recibido un descuento de 10% por tener una atigüedad superior a 2 años.\n");
			precioFinal = (float) (precio*0.90);
			descuento = precio - precioFinal;
			reservaAdultos.setDescuentoAntigüedad(descuento);
		}
		reservaAdultos.setPrecio(precioFinal);
		System.out.print("El precio de la reserva es: "+reservaAdultos.getPrecio() + "\n\n");
		
		//===================VOLCAMOS LOS DATOS DE LA RESERVA A UN FICHERO DE TEXTO========================
		try 
		{
			//Creamos el fichero en modo lecctura
			File fichero = new File("Reservas.txt");
			FileWriter fw = new FileWriter(fichero, true);
			
			//Establecemos los datos que queremos volvar al fichero y además, se escriben en este
			fw.write("Email: " + reservaAdultos.getIdUsuario() + ". Pista: "+reservaAdultos.getPista() +". Fecha: "+reservaAdultos.getFecha()+". Hora: "+reservaAdultos.getHora()+". Duracion: "+reservaAdultos.getDuracion()
					+". NºAdultos: "+reservaAdultos.getnAdultos()+". Especialidad: "+reservaAdultos.getEspecialidad()+". Descuento: "+reservaAdultos.getDescuentoAntigüedad()+"\n");	
			
			//Se cierra el fichero
			fw.close();
		}
		catch(Exception e) {
			System.out.print("\nERROR AL ESCRIBIR EN EL FICHERO " + e.getMessage() + "\n");
		}
	
		return reservaAdultos;
	}
	
	@Override
	public ReservaFamiliar createReservaFamiliar() {
		ReservaFamiliar reserva = new ReservaFamiliar();
		int nAdultos;
		int nNiños;
		
		//================COMENZAMOS PIDIENDO DATOS POR PANTALLA==================================
		
		//EMAIL
		System.out.println("\nPara la realización de la reserva, introduzca su email: ");
		email= lector.nextLine();
		
		try {
			aux = u.isUsuarioRegistrado(email);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		if(aux == true) {
			reserva.setIdUsuario(email);
		}else {
			System.out.println("\n El usuario no está registrado en el sistema. Registrese antes de realizar una reserva.\n ");
			System.out.println("Saliendo del gestor...");
			System.exit(1);
		}
		
		//FECHA
		System.out.println("\nIntroduzca la fecha con el siguiente formato: [dd-MM-yyyy]");
		fecha= lector.nextLine();
		reserva.setFecha(fecha);
		
		//HORA
		System.out.println("\nIntroduzca la hora con el siguiente formato: [hh:mm]24h");
		hora= lector.nextLine();
		reserva.setHora(hora);
				
		//DURACION DE LA RESERVA
		System.out.println("\nIntroduce la duracion de la reserva en minutos (60 // 90 // 120):");
		duracion = lector.nextInt();
		lector.nextLine();
		
		if(duracion == 60 || duracion== 90 || duracion== 120) {
			reserva.setDuracion(duracion);
		}
		else
		{
			System.out.println("La duracion introducida no es válida. \n");
			System.exit(1);
		}
		
		//PISTA QUE SE DESEA RESERVAR
		System.out.println("\nIntroduzca el nombre de la Pista que desea reservar: ");
		pista= lector.nextLine();
		
		try {
			aux = p.pistaCumpleCondiciones(pista, "FAMILIAR", fecha, hora);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		if(aux == true) {
			System.out.println("\n La pista cumple con las condiciones.");
			reserva.setPista(pista);
		}else {
			System.out.println("\n La pista no cumple con las condiciones. \n ");
			System.out.println("Saliendo del gestor...");
			System.exit(1);
		}
		
		
		//Nº DE ADULTOS PARTICIPANTES
		System.out.println("\nIntroduzca el numero de participantes adultos, incluyendo el que realiza la reserva: ");
		nAdultos= lector.nextInt();
		reserva.setnAdultos(nAdultos);
		
		//Nº DE NIÑOS PARTICIPANTES
		System.out.println("\nIntroduzca el numero de participantes niños: ");
		nNiños= lector.nextInt();
		reserva.setnNiños(nNiños);
		
		//PRECIO DE LA RESERVA 
		System.out.println("Calculando precio de la reserva... \n");
		precio = reserva.calcularPrecio(duracion);
		
		//ESPECIALIDAD
		reserva.setEspecialidad(dificultad.FAMILIAR);
		
		//POSIBLE DESCUENTO EN FUNCIÓN DE LA ANTIGÜEDAD
		
		//Calcular antiguedad;
		
		if(antiguedad>2)
		{
			System.out.println("Usted ha recibido un descuento de 10% por tener una atigüedad superior a 2 años.\n");
			precioFinal = (float) (precio*0.90);
			descuento = precio - precioFinal;
			reserva.setDescuentoAntigüedad(descuento);
		}
		reserva.setPrecio(precioFinal);
		System.out.print("El precio de la reserva es: "+reserva.getPrecio() + "\n\n");
		
		//===================VOLCAMOS LOS DATOS DE LA RESERVA A UN FICHERO DE TEXTO========================
		try 
		{
			//Creamos el fichero en modo lecctura
			File fichero = new File("Reservas.txt");
			FileWriter fw = new FileWriter(fichero, true);
			
			//Establecemos los datos que queremos volvar al fichero y además, se escriben en este
			fw.write("Email: " + reserva.getIdUsuario() + ". Pista: "+reserva.getPista() +". Fecha: "+reserva.getFecha()+". Duracion: "+reserva.getDuracion()
					+". NºAdultos: "+reserva.getnAdultos()+".NºNiños: "+reserva.getnNiños()+". Especialidad: "+reserva.getEspecialidad()+". Descuento: "+reserva.getDescuentoAntigüedad()+"\n");	
			
			//Se cierra el fichero
			fw.close();
		}
		catch(Exception e) {
			System.out.print("\nERROR AL ESCRIBIR EN EL FICHERO " + e.getMessage() + "\n");
		}
		
		return reserva;
	}
	
	@Override
	public ReservaInfantil createReservaInfantil() {
		ReservaInfantil reserva = new ReservaInfantil();
		
		int nNiños;
		
		//================COMENZAMOS PIDIENDO DATOS POR PANTALLA==================================
		
		//EMAIL
		System.out.println("\nPara la realización de la reserva, introduzca su email: ");
		email= lector.nextLine();
		
		try {
			aux = u.isUsuarioRegistrado(email);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		if(aux == true) {
			reserva.setIdUsuario(email);
		}else {
			System.out.println("\n El usuario no está registrado en el sistema. Registrese antes de realizar una reserva.\n ");
			System.out.println("Saliendo del gestor...");
			System.exit(1);
		}
		
		//FECHA
		System.out.println("\nIntroduzca la fecha con el siguiente formato: [dd-MM-yyyy]");
		fecha= lector.nextLine();
		reserva.setFecha(fecha);
		
		//HORA
		System.out.println("\nIntroduzca la hora con el siguiente formato: [hh:mm]24h");
		hora= lector.nextLine();
		reserva.setHora(hora);
		
		
		//DURACION DE LA RESERVA
		System.out.println("\nIntroduce la duracion de la reserva en minutos (60 // 90 // 120):");
		duracion = lector.nextInt();
		lector.nextLine();
		
		if(duracion == 60 || duracion== 90 || duracion== 120) {
			reserva.setDuracion(duracion);
		}
		else
		{
			System.out.println("La duracion introducida no es válida. \n");
			System.exit(1);
		}
		
		//PISTA QUE SE DESEA RESERVAR
		System.out.println("\nIntroduzca el nombre de la Pista que desea reservar: ");
		pista= lector.nextLine();
		
		try {
			aux = p.pistaCumpleCondiciones(pista, "INFANTIL", fecha, hora);
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		if(aux == true) {
			System.out.println("\n La pista cumple con las condiciones.");
			reserva.setPista(pista);
		}else {
			System.out.println("\n La pista no cumple con las condiciones. \n ");
			System.out.println("Saliendo del gestor...");
			System.exit(1);
		}
		
		
		//Nº DE NIÑOS PARTICIPANTES
		System.out.println("\nIntroduzca el numero de participantes niños: ");
		nNiños= lector.nextInt();
		reserva.setnNiños(nNiños);
		
		//PRECIO DE LA RESERVA 
		System.out.println("Calculando precio de la reserva... \n");
		precio = reserva.calcularPrecio(duracion);
		
		//ESPECIALIDAD
		reserva.setEspecialidad(dificultad.INFANTIL);
		
		//POSIBLE DESCUENTO EN FUNCIÓN DE LA ANTIGÜEDAD
		
		//Calcular antiguedad;
		
		if(antiguedad>2)
		{
			System.out.println("Usted ha recibido un descuento de 10% por tener una atigüedad superior a 2 años.\n");
			precioFinal = (float) (precio*0.90);
			descuento = precio - precioFinal;
			reserva.setDescuentoAntigüedad(descuento);
		}
		reserva.setPrecio(precioFinal);
		System.out.print("El precio de la reserva es: "+reserva.getPrecio() + "\n\n");
		
		//===================VOLCAMOS LOS DATOS DE LA RESERVA A UN FICHERO DE TEXTO========================
		try 
		{
			//Creamos el fichero en modo lecctura
			File fichero = new File("Reservas.txt");
			FileWriter fw = new FileWriter(fichero, true);
			
			//Establecemos los datos que queremos volvar al fichero y además, se escriben en este
			fw.write("Email: " + reserva.getIdUsuario() + ". Pista: "+reserva.getPista() +". Fecha: "+reserva.getFecha()+". Duracion: "+reserva.getDuracion()
					+". NºNiños: "+reserva.getnNiños()+". Especialidad: "+reserva.getEspecialidad()+". Descuento: "+reserva.getDescuentoAntigüedad());	
			
			//Se cierra el fichero
			fw.close();
		}
		catch(Exception e) {
			System.out.print("\nERROR AL ESCRIBIR EN EL FICHERO " + e.getMessage() + "\n");
		}
		
		return reserva;
	}
}



