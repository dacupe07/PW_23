package es.uco.pw.business;

import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import es.uco.pw.dao.ReservaDAO;

public class GestorReserva {

		ReservaDAO reservarIndividual = new ReservaDAO();
	    ReservaDAO establecePrecio = new ReservaDAO();
	    ReservaDAO cancelaReserva = new ReservaDAO();
	    ReservaDAO actualizaReserva = new ReservaDAO();
	    
	    
	    public float establecerPrecio(int duracion, String email) throws SQLException
	    {
	        float precio = 0;

	        if(duracion == 60)
	        {
	            precio = 20;
	        }
	        else if(duracion == 90)
	        {
	            precio = 30;
	        }
	        else if(duracion == 120)
	        {
	            precio = 40;
	        }

	        if(establecePrecio.comprobarAntiguedad(email) && duracion == 60)
	        {
	            precio = precio - 2;
	        }

	        if(establecePrecio.comprobarAntiguedad(email) && duracion == 90)
	        {
	            precio = precio - 3;
	        }

	        if(establecePrecio.comprobarAntiguedad(email) && duracion == 120)
	        {
	            precio = precio - 4;
	        }

	        return precio;
	    }

	    public void reservaIndividualBBDD() throws SQLException
	    {
	        Scanner sc = new Scanner(System.in);
	        String correo;
	        String nom_pista;
	        int idRes, duracion;
	        float precio, descuento;
	        String hora;
	        LocalDate fecha;
	        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy/MM/dd");



	        System.out.print("Introduce el correo del usuario: ");
	        correo = sc.nextLine();

	        if(reservarIndividual.existeUsuario(correo))
	        {
	            System.out.print("Introduce el nombre de la pista: ");
	            nom_pista = sc.nextLine();

	            if(reservarIndividual.existePista(nom_pista))
	            {
	                System.out.print("Introduce el ID de la reserva: ");
	                idRes = sc.nextInt();
	                sc.nextLine();

	                System.out.print("Introduce la fecha de la reserva: ");
	                fecha = LocalDate.parse(sc.nextLine(), fmt);

	                System.out.print("Introduce la duracion de la reserva (60 min / 90 min / 120 min): ");
	                duracion = sc.nextInt();
	                sc.nextLine();

	                System.out.print("Introduce la hora de la reserva: ");
	                hora = sc.nextLine();

	                if(reservarIndividual.comprobarAntiguedad(correo) == true)
	                {
	                    descuento = 10;
	                }
	                else
	                {
	                    descuento = 0;
	                }

	                precio = establecerPrecio(duracion, correo);

	                reservarIndividual.reservaIndividual(correo, nom_pista, idRes, fecha, duracion, hora, precio, descuento);
	            }
	        }
	    }

	    public String listarReservasBBDD()
	    {
	        Scanner sc = new Scanner(System.in);
	        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy/MM/dd");

	        System.out.print("Introduce la fecha de la reserva: ");
	        LocalDate fecha = LocalDate.parse(sc.nextLine(), fmt);

	        System.out.print("Introduce la pista: ");
	        String pista = sc.nextLine();

	        String reserva = "";

	        ArrayList<entidadesDTO> reservas = listaUsuarios.listarReservas(fecha, pista);

	        for(entidadesDTO u: reservas)
	        {
	            reserva = reserva + u.toStringReserva() + "\n";
	        }

	        return reserva;
	    }

	    public String listarReservasFuturasBBDD()
	    {
	        Scanner sc = new Scanner(System.in);
	        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy/MM/dd");

	        LocalDate fecha = LocalDate.now();

	        System.out.print("Introduce la pista: ");
	        String pista = sc.nextLine();

	        String reserva = "";

	        ArrayList<entidadesDTO> reservas = listaUsuarios.listarReservasFuturas(fecha, pista);

	        for(entidadesDTO u: reservas)
	        {
	            reserva = reserva + u.toStringReserva() + "\n";
	        }

	        return reserva;
	    }

	    public void cancelarReservaBBDD() throws ParseException, SQLException {
	        Scanner entrada = new Scanner(System.in);
	        int idRes;

	        System.out.print("Introduce la ID de la reserva: ");
	        idRes = entrada.nextInt();
	        entrada.nextLine();

	        cancelaReserva.cancelarReserva(idRes);
	        System.out.println("\n--- USUARIO ELIMINADO CON EXITO ---\n");
	    }

	    public void actualizarReservaBBDD() throws ParseException, SQLException {
	        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	        Scanner entrada = new Scanner(System.in);
	        int idRes, duracion;
	        String correo, nombre, hora;
	        LocalDate fecha;
	        float descuento;

	        System.out.print("Introduce el ID de la reserva: ");
	        idRes = entrada.nextInt();
	        entrada.nextLine();

	        if(actualizaReserva.existeReserva(idRes) == true)
	        {

	            System.out.print("Introduce el correo del usuario: ");
	            correo = entrada.nextLine();

	            if(actualizaReserva.existeUsuario(correo) == true)
	            {
	                System.out.print("Introduce el nombre de la pista: ");
	                nombre = entrada.nextLine();

	                if(actualizaReserva.existePista(nombre) == true)
	                {
	                    System.out.print("Introduce la fecha de reserva (YYYY/MM/DD): ");
	                    fecha = LocalDate.parse(entrada.nextLine(), fmt);

	                    System.out.print("Introduce la duracion de la reserva: ");
	                    duracion = entrada.nextInt();
	                    entrada.nextLine();

	                    System.out.print("Introduce la hora de la reserva: ");
	                    hora = entrada.nextLine();

	                    if (actualizaReserva.comprobarAntiguedad(correo) == true)
	                    {
	                        descuento = 10;
	                    }
	                    else
	                    {
	                        descuento = 0;
	                    }


	                    float precio = establecerPrecio(duracion, correo);
	                    actualizaReserva.actualizarReserva(correo, nombre, idRes, fecha, duracion, hora, precio, descuento);
	                }
	            }
	        }
	        else
	        {
	            String respuesta = "";
	            System.out.print("La reserva introducida no existe, ¿Desea crearla? (S -> Si / N -> No): ");
	            respuesta = entrada.nextLine();

	            if(respuesta == "S" || respuesta == "s")
	            {
	                reservaIndividualBBDD();
	            }
	            else
	            {
	                System.out.print("Regresando al menu...");
	            }
	        }
	    }
}
