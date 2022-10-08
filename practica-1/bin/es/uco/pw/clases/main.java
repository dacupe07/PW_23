package es.uco.pw.clases;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class main 
{
	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		kart k = kart.getKart(0, null, null);
		pista p = pista.getPista(null, null, null, 0);
		Reserva r = new Reserva();
		Scanner entrada = new Scanner(System.in);
		Scanner variables = new Scanner(System.in);
		ArrayList<String> PistaKart = new ArrayList<String>();
		ArrayList<String> kartAsociados = new ArrayList<String>();
		
		int opcion = 1;
		
		while(opcion != 0)
		{
			System.out.println("0. Salir");
			
			
			System.out.println("SECCION USUARIO");
			System.out.println("-----------------------");
			// Dar de alta a un usuario comprobando que no est� registrado previamente
			System.out.println("1. Dar de alta a un usuario (NO IMPLEMENTADA)");
			
			// Dar de baja a un usuario
			System.out.println("2. Dar de baja a un usuario (NO IMPLEMENTADA)");
			
			// Consultar los datos de un usuario
			System.out.println("3. Consultar datos del usuario (NO IMPLEMENTADA)");
			
			// Modificar la informacion de un usuario
			System.out.println("4. Actualizar datos de un usuario (NO IMPLEMENTADA)");
			
			System.out.println("\nSECCION PISTA");
			System.out.println("------------------------");
			// Crear pista
			System.out.println("5. Crear pista");	
			
			// Mostrar las pistas en mantenimiento
			System.out.println("6. Listar pistas en mantenimiento");
			
			/*
			 * Dado un n�mero de karts y tipo de pista, devolver el conjunto de pistas que est�n
				libres (no reservadas ni en mantenimiento) y tengan al menos ese n�mero de karts
				asociados.
			 */
			System.out.println("7. Listar pistas libres");
			
			
			System.out.println("\nSECCION KART");
			System.out.println("----------------------");
			// Crear kart
			System.out.println("8. Crear karts");
			
			/*Asociar karts a pistas disponibles, controlando que los karts no est�n asignados a
				otras pistas o en mantenimiento. Tambi�n deben cumplirse las restricciones entre
				tipos de pistas (es decir, su dificultad) y de karts, as� como el n�mero m�ximo de
				karts.
			 */
			System.out.println("9. Asociar karts (NO IMPLEMENTADA)");
			
			// Listar Karts disponibles (Que no esten en mantenimiento ni reservados)
			System.out.println("10. Listar karts disponibles");
			
			System.out.println("\nSECCION RESERVAS");
			System.out.println("----------------------");
			
			/*Realizar una reserva por parte de un usuario ya registrado para una pista que cumpla las condiciones establecidas.
			  Si el usuario tiene una antigëdad mayor a dos años, se le aplica un descuento de 10%. La reserva se debe realizar con 24h de antelación.
			 */
			System.out.println("11. Realizar Reserva Individual (NO IMPLEMENTADA)");
			
			/*Realizar reserva para un usuario con un bono activo. El precio de la reserva
			  dentro de este bono tiene un descuento del 5% con respecto al precio original 
			  según la duración de la reserva. No existe rebaja por antigëdad.
			 */
			System.out.println("12. Realizar Reserva con bono. (NO IMPLEMENTADA");
			
			//Sacar un bono de reservas de un determinado tipo y con posibilidad a 5 reservas.
			System.out.println("13. Sacar Bono de reservas. (NO IMPLEMENTADA)");
			
			//Se permite modificar la reserva con 24h de antelación.
			System.out.println("14. Modificar Reserva. (NO IMPLEMENTADA)");
			
			//Se permite cancelar la reserva con 24h de antelación.
			System.out.println("15. Cancelar Reserva. (NO IMPLEMENTADA)");
			
			//Se permite consultar el número de reservas existentes a partir de la fecha de consulta. 
			System.out.println("16. Consultar reservas a partir de hoy. (NO IMPLEMENTADA)");
			
			//Se permite consultar las reservas existentes para un día y una fecha determinada.
			System.out.println("17. Consultar reservas de una pista y día concreto. (NO IMPLEMENTADA)");
			
			
			System.out.println("\n----------------------");
			System.out.print("Introduzca la opcion deseada: ");
			opcion = entrada.nextInt();
			entrada.nextLine();
			
			switch(opcion)
			{
			case 0:
				System.out.println("Saliendo del gestor...");
				System.exit(1);
				break;
				
			case 1:
				break;
				
			case 2:
				break;
				
			case 3:
				break;
				
			case 4:
				break;
				
			case 5:
				p.crearPista();
				System.out.print(p.toString());
				break;
				
			case 6:
				PistaKart.clear();
				PistaKart = p.pistasMantenimiento();
				break;
				
			case 7:
				PistaKart.clear();
				PistaKart = p.pistasDisponibles();
				break;
				
			case 8:
				k.crearKart();
				System.out.print(k.toString());
				break;
				
			case 9:
				//p.asociarKartAPista();
				break;
				
			case 10:
				PistaKart.clear();
				PistaKart = p.consultarKartsDisponibles();
				break;
				
			case 11:
				r.crearReservaIndividual();
				
				break;
				
			case 12:
				r.crearReservaBono();
				break;
			
			case 13:
				
				break;
			
			case 14:
				
				break;
				
			case 15:
				
				break;
				
			case 16:
				
				break;
				
			case 17:
				
				break;
			}
		}
	}
}