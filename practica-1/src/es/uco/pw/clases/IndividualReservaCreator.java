package es.uco.pw.clases;

import java.util.Scanner;

public class IndividualReservaCreator extends ReservaCreator {
	
	@Override
	public ReservaAdultos createReservaAdultos() {
		Scanner lector = new Scanner(System.in);
		int duracion;
		float precio;
		int antiguedad=3;
		
		ReservaAdultos reservaAdultos = new ReservaAdultos();
		
		
		System.out.println("Introduce la duracion de la reserva en minutos (60 // 90 // 120):");
		duracion = lector.nextInt();
		
		if(duracion == 60 || duracion== 90 || duracion== 120) {
			reservaAdultos.setDuracion(duracion);
		}
		else
		{
			System.out.println("La duracion introducida no es válida. \n");
			System.exit(1);
		}
		
		System.out.println("Calculando precio de la reserva... \n");
		precio = reservaAdultos.calcularPrecio(duracion);
		//Calcular antiguedad;
		if(antiguedad>2)
		{
			System.out.println("Usted ha recibido un descuento de 10% por tener una atigüedad superior a 2 años.\n");
			precio = (float) (precio*0.90);
		}
		reservaAdultos.setPrecio(precio);
		System.out.print("El precion de la reserva es: "+reservaAdultos.getPrecio());
		
		return reservaAdultos;
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
