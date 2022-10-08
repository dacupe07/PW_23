package es.uco.pw.clases;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class BonoReservaCreator extends ReservaCreator{
	
	//DECLARACIÓN DE VARIABLES AUXILIARES Y COMUNES
		Scanner lector = new Scanner(System.in);
		String nombre;
		String fechaAux;
		int duracion;
		String pista;
		float precio;
		float precioFinal;
		float descuento;
		int antiguedad=3;
		Boolean disponible=false;
		Boolean tieneBono= false;
		dificultad especialidad;
		pista p = new pista();
		Bono b = new Bono();
		int sesiones;
	
	@Override
	public ReservaAdultos createReservaAdultos() {
		
		ReservaAdultos reservaAdultos = new ReservaAdultos();
		int nAdultos;
		
		
		//================COMENZAMOS PIDIENDO DATOS POR PANTALLA==================================
		
		//NOMBRE
		System.out.println("\nPara la realización de la reserva, introduzca su nombre: ");
		nombre= lector.nextLine();
		
		try {
			tieneBono = b.tieneBono(nombre, "ADULTOS");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		if(tieneBono == true) {
			System.out.println("\n El usuario introducido tiene bono de tipo Adulto.");
			reservaAdultos.setIdUsuario(nombre);
		}else {
			System.out.println("\n El usuario no dispone de bono o no es de la especialidad seleccionada. \n ");
			System.out.println("Saliendo del gestor...");
			System.exit(1);
		}
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
			disponible = p.pistaCumpleCondiciones(pista, "ADULTOS");
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		if(disponible == true) {
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
		
		//DESCUENTO DEL 5% POR BONO
		
		System.out.println("Usted ha recibido un descuento de 5% por realizar una reserva a través de bono.\n");
		precioFinal = (float) (precio*0.95);
		
		reservaAdultos.setDescuentoAntigüedad(0);
		
		reservaAdultos.setPrecio(precioFinal);
		System.out.print("El precio de la reserva es: "+reservaAdultos.getPrecio() + "\n\n");
		
		//RESTAMOS UNA SESION AL BONO
		
		
		//===================VOLCAMOS LOS DATOS DE LA RESERVA A UN FICHERO DE TEXTO========================
		try 
		{
			//Creamos el fichero en modo lecctura
			File fichero = new File("Reservas.txt");
			FileWriter fw = new FileWriter(fichero, true);
			
			//Establecemos los datos que queremos volcar al fichero y además, se escriben en este
			fw.write("Nombre: " + reservaAdultos.getIdUsuario() + ". Pista: "+reservaAdultos.getPista() +". Fecha: "+reservaAdultos.getFecha()+". Duracion: "+reservaAdultos.getDuracion()
					+". NºAdultos: "+reservaAdultos.getnAdultos()+". Especialidad: "+reservaAdultos.getEspecialidad()+"\n");	
			
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
		
		//NOMBRE
		System.out.println("\nPara la realización de la reserva, introduzca su nombre: ");
		nombre= lector.nextLine();
		
		try {
			tieneBono = b.tieneBono(nombre, "FAMILIAR");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		if(tieneBono == true) {
			System.out.println("\n El usuario introducido tiene bono de tipo Familiar.");
			reserva.setIdUsuario(nombre);
		}else {
			System.out.println("\n El usuario no dispone de bono o no es de la especialidad seleccionada. \n ");
			System.out.println("Saliendo del gestor...");
			System.exit(1);
		}
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
			disponible = p.pistaCumpleCondiciones(pista, "FAMILIAR");
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		if(disponible == true) {
			System.out.println("\n La pista cumple con las condiciones.");
			reserva.setPista(pista);
		}else {
			System.out.println("\n La pista no cumple con las condiciones. \n ");
			System.out.println("Saliendo del gestor...");
			System.exit(1);
		}
		
		
		//Nº DE ADULTOS PARTICIPANTES
		System.out.println("\nIntroduzca el numero de adultos, incluyendo el que realiza la reserva: ");
		nAdultos= lector.nextInt();
		reserva.setnAdultos(nAdultos);
		
		//Nº DE NIÑOS PARTICIPANTES
		System.out.println("\nIntroduzca el numero de niños, incluyendo el que realiza la reserva: ");
		nNiños= lector.nextInt();
		reserva.setnNiños(nNiños);
		
		//PRECIO DE LA RESERVA 
		System.out.println("Calculando precio de la reserva... \n");
		precio = reserva.calcularPrecio(duracion);
		
		//ESPECIALIDAD
		reserva.setEspecialidad(dificultad.FAMILIAR);
		
		//DESCUENTO DEL 5% POR BONO
		
		System.out.println("Usted ha recibido un descuento de 5% por realizar una reserva a través de bono.\n");
		precioFinal = (float) (precio*0.95);
		
		reserva.setDescuentoAntigüedad(0);
		
		reserva.setPrecio(precioFinal);
		System.out.print("El precio de la reserva es: "+reserva.getPrecio() + "\n\n");
		
		//RESTAMOS UNA SESION AL BONO
		
		
		//===================VOLCAMOS LOS DATOS DE LA RESERVA A UN FICHERO DE TEXTO========================
		try 
		{
			//Creamos el fichero en modo lecctura
			File fichero = new File("Reservas.txt");
			FileWriter fw = new FileWriter(fichero, true);
			
			//Establecemos los datos que queremos volcar al fichero y además, se escriben en este
			fw.write("Nombre: " + reserva.getIdUsuario() + ". Pista: "+reserva.getPista() +". Fecha: "+reserva.getFecha()+". Duracion: "+reserva.getDuracion()
					+". NºAdultos: "+reserva.getnAdultos()+". NºNiños: "+reserva.getnNiños()+". Especialidad: "+reserva.getEspecialidad()+"\n");	
			
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
		int nAdultos;
		int nNiños;
		
		
		//================COMENZAMOS PIDIENDO DATOS POR PANTALLA==================================
		
		//NOMBRE
		System.out.println("\nPara la realización de la reserva, introduzca su nombre: ");
		nombre= lector.nextLine();
		
		try {
			tieneBono = b.tieneBono(nombre, "INFANTIL");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		if(tieneBono == true) {
			System.out.println("\n El usuario introducido tiene bono de tipo Infantil.");
			reserva.setIdUsuario(nombre);
		}else {
			System.out.println("\n El usuario no dispone de bono o no es de la especialidad seleccionada. \n ");
			System.out.println("Saliendo del gestor...");
			System.exit(1);
		}
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
			disponible = p.pistaCumpleCondiciones(pista, "INFANTIL");
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		if(disponible == true) {
			System.out.println("\n La pista cumple con las condiciones.");
			reserva.setPista(pista);
		}else {
			System.out.println("\n La pista no cumple con las condiciones. \n ");
			System.out.println("Saliendo del gestor...");
			System.exit(1);
		}
		
		//Nº DE NIÑOS PARTICIPANTES
		System.out.println("\nIntroduzca el numero de niños, incluyendo el que realiza la reserva: ");
		nNiños= lector.nextInt();
		reserva.setnNiños(nNiños);
		
		//PRECIO DE LA RESERVA 
		System.out.println("Calculando precio de la reserva... \n");
		precio = reserva.calcularPrecio(duracion);
		
		//ESPECIALIDAD
		reserva.setEspecialidad(dificultad.INFANTIL);
		
		//DESCUENTO DEL 5% POR BONO
		
		System.out.println("Usted ha recibido un descuento de 5% por realizar una reserva a través de bono.\n");
		precioFinal = (float) (precio*0.95);
		
		reserva.setDescuentoAntigüedad(0);
		
		reserva.setPrecio(precioFinal);
		System.out.print("El precio de la reserva es: "+reserva.getPrecio() + "\n\n");
		
		//RESTAMOS UNA SESION AL BONO
		
		
		//===================VOLCAMOS LOS DATOS DE LA RESERVA A UN FICHERO DE TEXTO========================
		try 
		{
			//Creamos el fichero en modo lecctura
			File fichero = new File("Reservas.txt");
			FileWriter fw = new FileWriter(fichero, true);
			
			//Establecemos los datos que queremos volcar al fichero y además, se escriben en este
			fw.write("Nombre: " + reserva.getIdUsuario() + ". Pista: "+reserva.getPista() +". Fecha: "+reserva.getFecha()+". Duracion: "+reserva.getDuracion()
					+". NºNiños: "+reserva.getnNiños()+". Especialidad: "+reserva.getEspecialidad()+"\n");	
			
			//Se cierra el fichero
			fw.close();
		}
		catch(Exception e) {
			System.out.print("\nERROR AL ESCRIBIR EN EL FICHERO " + e.getMessage() + "\n");
		}
	
		return reserva;
	}
	
}

