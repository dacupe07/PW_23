package es.uco.pw.clases;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class main 
{
	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		kart k = kart.getKart(0, false, null);
		pista p = pista.getPista(null, false, null, 0);
		Scanner entrada = new Scanner(System.in);
		Scanner variables = new Scanner(System.in);
		ArrayList<String> PistaKart = new ArrayList<String>();
		
		int opcion = 1;
		
		while(opcion != 0)
		{
			System.out.println("0. Salir");
			
			
			System.out.println("SECCION USUARIO");
			System.out.println("-----------------------");
			// Dar de alta a un usuario comprobando que no está registrado previamente
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
			 * Dado un número de karts y tipo de pista, devolver el conjunto de pistas que estén
				libres (no reservadas ni en mantenimiento) y tengan al menos ese número de karts
				asociados.
			 */
			System.out.println("7. Listar pistas libres (NO IMPLEMENTADA)");
			
			
			System.out.println("\nSECCION KART");
			System.out.println("----------------------");
			// Crear kart
			System.out.println("8. Crear karts");
			
			/*Asociar karts a pistas disponibles, controlando que los karts no estén asignados a
				otras pistas o en mantenimiento. También deben cumplirse las restricciones entre
				tipos de pistas (es decir, su dificultad) y de karts, así como el número máximo de
				karts.
			 */
			System.out.println("9. Asociar karts (NO IMPLEMENTADA)");
			
			// Listar Karts disponibles (Que no esten en mantenimiento ni reservados)
			System.out.println("10. Listar karts disponibles");
			
			
			
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
				
				for(String A: PistaKart)
				{
					System.out.println(A + "\n");
					System.out.print("\n");
				}
				System.out.println("\n");
				break;
				
			case 7:
			
				break;
				
			case 8:
				k.crearKart();
				System.out.print(k.toString());
				break;
				
			case 9:
				break;
				
			case 10:
				PistaKart.clear();
				PistaKart = p.consultarKartsDisponibles();
				
				for(String A: PistaKart)
				{
					System.out.println(A + "\n");
					System.out.print("\n");
				}
				System.out.println("\n");
				break;
				
			}
		}
	}
}
