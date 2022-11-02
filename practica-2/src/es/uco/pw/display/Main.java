package es.uco.pw.display;

import es.uco.pw.business.gestorDatos;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) throws SQLException, ParseException {
        gestorDatos Gestor = new gestorDatos();
        Scanner entrada = new Scanner(System.in);
        int opcion;


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
            System.out.println("15. Modificar Reserva.");
            System.out.println("16. Consultar reservas a partir de hoy.");
            System.out.println("17. Consultar reservas de una pista y día concreto.");
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
                    Gestor.crearUsuarioBBDD();
                    break;

                case 2:
                    Gestor.borrarUsuarioBBDD();
                    break;

                case 3:
                    String lista_usuarios = Gestor.listarUsuariosBBDD();
                    System.out.println("\n--- USUARIOS REGISTRADOS ---");
                    System.out.println(lista_usuarios);
                    break;

                case 4:
                    Gestor.actualizarUsuarioBBDD();
                    break;

                case 5:
                    Gestor.crearPistaBBDD();
                    break;

                case 6:
                    String lista_pistas_mant = Gestor.listarPistasMantenimientoBBDD();
                    System.out.println("\n --- PISTAS EN MANTENIMIENTO ---");
                    System.out.println(lista_pistas_mant);
                    break;

                case 7:
                    String lista_pistas_disp = Gestor.listarPistasDisponiblesBBDD();
                    System.out.println("\n --- PISTAS DISPONIBLES ---");
                    System.out.println(lista_pistas_disp);
                    break;

                case 8:
                    Gestor.crearKartBBDD();
                    break;

                case 9:
                    Gestor.asociarKartPistaBBDD();
                    break;

                case 10:
                    String lista_karts_disp = Gestor.listarKartsDisponiblesBBDD();
                    System.out.println("\n --- KARTS DISPONIBLES ---");
                    System.out.println(lista_karts_disp);
                    break;

                case 11:
                    Gestor.reservaIndividualBBDD();
                    break;

                case 12:
                    break;

                case 13:
                    break;

                case 14:
                    Gestor.cancelarReservaBBDD();
                    break;

                case 15:
                    Gestor.actualizarReservaBBDD();
                    break;

                case 16:
                    String lista_reservas_futu = Gestor.listarReservasFuturasBBDD();
                    System.out.println("\n --- RESERVAS REALIZADAS ---");
                    System.out.println(lista_reservas_futu);
                    break;

                case 17:
                    String lista_reservas_act = Gestor.listarReservasBBDD();
                    System.out.println("\n --- RESERVAS REALIZADAS ---");
                    System.out.println(lista_reservas_act);
                    break;

            }
        }



    }
}