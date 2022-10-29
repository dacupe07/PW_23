package es.uco.pw.display;

import es.uco.pw.business.GestorUsuario;
import es.uco.pw.business.GestorPista;
import es.uco.pw.business.GestorKart;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;
import java.io.*;
import java.util.Properties;

public class Main
{
    public static void main(String[] args) throws SQLException, ParseException {
        GestorUsuario Gestorusuario = new GestorUsuario();
        GestorKart Gestorkart = new GestorKart();
        GestorPista Gestorpista = new GestorPista();
        Scanner entrada = new Scanner(System.in);
        int opcion;
        
        	Properties config = new Properties();
			InputStream is;
			try {
				
				is = new FileInputStream("config.properties");
				try {
					config.load(is);
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			Properties sql = new Properties();
			InputStream is1;
			try {
				
				is1 = new FileInputStream("sql.properties");
				try {
					sql.load(is1);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
    	
        boolean controlador = true;

        while(controlador == true)
        {
            System.out.println("\n0. Salir\n");
            System.out.println("SECCION USUARIO");
            System.out.println("-----------------------");
            System.out.println("1. Dar de alta a un usuario");
            System.out.println("2. Dar de baja a un usuario");
            System.out.println("3. Listar usuarios actualmente registrados");
            System.out.println("4. Actualizar datos de un usuario");
            System.out.println("\nSECCION PISTA");
            System.out.println("------------------------");
            System.out.println("5. Crear pista");
            System.out.println("6. Listar pistas en mantenimiento");
            System.out.println("7. Listar pistas libres");
            System.out.println("\nSECCION KART");
            System.out.println("----------------------");
            System.out.println("8. Crear karts");
            System.out.println("9. Asociar karts");
            System.out.println("10. Listar karts disponibles");
            System.out.println("\nSECCION RESERVAS");
            System.out.println("----------------------");
            System.out.println("11. Realizar Reserva Individual");
            System.out.println("12. Realizar Reserva con bono");
            System.out.println("13. Sacar Bono de reservas");
            System.out.println("14. Cancelar Reserva.");
            System.out.println("15. Consultar reservas a partir de hoy.");
            System.out.println("16. Consultar reservas de una pista y día concreto.");
            System.out.println("\n----------------------");
            System.out.print("\nIntroduzca la opcion deseada: ");
            opcion = entrada.nextInt();
            entrada.nextLine();


            switch(opcion)
            {
                case 0:
                    System.out.println("Saliendo del gestor...");
                    controlador = false;
                    break;

                case 1:
                    Gestorusuario.crearUsuarioBBDD(config,sql);
                    break;

                case 2:
                    Gestorusuario.borrarUsuarioBBDD(config,sql);
                    break;

                case 3:
                    String lista_usuarios = Gestorusuario.listarUsuariosBBDD(config,sql);
                    System.out.println("\n--- USUARIOS REGISTRADOS ---");
                    System.out.println(lista_usuarios);
                    break;

                case 4:
                    Gestorusuario.actualizarUsuarioBBDD(config,sql);
                    break;

                case 5:
                    Gestorpista.crearPistaBBDD(config,sql);
                    break;

                case 6:
                    String lista_pistas_mant = Gestorpista.listarPistasMantenimientoBBDD(config,sql);
                    System.out.println("\n --- PISTAS EN MANTENIMIENTO ---");
                    System.out.println(lista_pistas_mant);
                    break;

                case 7:
                    String lista_pistas_disp = Gestorpista.listarPistasDisponiblesBBDD(config,sql);
                    System.out.println("\n --- PISTAS DISPONIBLES ---");
                    System.out.println(lista_pistas_disp);
                    break;

                case 8:
                    Gestorkart.crearKartBBDD(config,sql);
                    break;

                case 9:
                    Gestorpista.asociarKartPistaBBDD(config,sql);
                    break;

                case 10:
                    String lista_karts_disp = Gestorkart.listarKartsDisponiblesBBDD(config,sql);
                    System.out.println("\n --- KARTS DISPONIBLES ---");
                    System.out.println(lista_karts_disp);
                    break;
            }
        }

    }
}