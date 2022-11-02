package es.uco.pw.business;

import es.uco.pw.dao.entidadesDAO;

import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class gestorDatos
{
    public gestorDatos()
    {

    }

    entidadesDAO crearUsuario = new entidadesDAO();
    entidadesDAO listaUsuarios = new entidadesDAO();
    entidadesDAO eliminarUsuario = new entidadesDAO();
    entidadesDAO actualizarUsuario = new entidadesDAO();



    entidadesDAO crearPista = new entidadesDAO();
    entidadesDAO listaPistasMantenimiento = new entidadesDAO();
    entidadesDAO listaPistasDisponibles = new entidadesDAO();


    entidadesDAO crearKart = new entidadesDAO();
    entidadesDAO listarKartsDisponibles = new entidadesDAO();
    entidadesDAO asociarKartPista = new entidadesDAO();


    entidadesDAO reservarIndividual = new entidadesDAO();
    entidadesDAO establecePrecio = new entidadesDAO();
    entidadesDAO cancelaReserva = new entidadesDAO();
    entidadesDAO actualizaReserva = new entidadesDAO();

    public String listarUsuariosBBDD()
    {
        String usuario = "";

        ArrayList<entidadesDTO> usuarios = listaUsuarios.listarUsuariosRegistrados();

        for(entidadesDTO u: usuarios)
        {
            usuario = usuario + u.toStringUsuario() + "\n";
        }

        return usuario;
    }

    public void crearUsuarioBBDD() throws ParseException, SQLException {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        Scanner entrada = new Scanner(System.in);
        String nombre, correo, fecha_inscripcion_aux, fecha_nacimiento_aux;
        LocalDate fecha_inscripcion, fecha_nacimiento;

        fecha_inscripcion = LocalDate.now();

        System.out.print("Introduce el correo del usuario: ");
        correo = entrada.nextLine();

        System.out.print("Introduce el nombre del usuario: ");
        nombre = entrada.nextLine();

        System.out.print("Introduce la fecha de nacimiento (YYYY/MM/DD): ");
        fecha_nacimiento_aux = entrada.nextLine();
        fecha_nacimiento = LocalDate.parse(fecha_nacimiento_aux, fmt);


        crearUsuario.registrarUsuario(correo, nombre, fecha_nacimiento, fecha_inscripcion);
    }

    public void borrarUsuarioBBDD() throws ParseException, SQLException {
        Scanner entrada = new Scanner(System.in);
        String correo;

        System.out.print("Introduce el correo del usuario: ");
        correo = entrada.nextLine();

        eliminarUsuario.eliminarUsuario(correo);
        System.out.println("\n--- USUARIO ELIMINADO CON EXITO ---\n");
    }

    public void actualizarUsuarioBBDD() throws ParseException, SQLException {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        Scanner entrada = new Scanner(System.in);
        String nombre, correo_aux, correo, fecha_inscripcion_aux, fecha_nacimiento_aux, control;
        LocalDate fecha_inscripcion, fecha_nacimiento;

        System.out.print("Introduce el email del usuario que desea actualizar: ");
        correo_aux = entrada.nextLine();

        if(actualizarUsuario.existeUsuario(correo_aux) == true)
        {

            System.out.print("Introduce el correo del usuario: ");
            correo = entrada.nextLine();

            System.out.print("Introduce el nombre del usuario: ");
            nombre = entrada.nextLine();

            System.out.print("Introduce la fecha de nacimiento (YYYY/MM/DD): ");
            fecha_nacimiento_aux = entrada.nextLine();
            fecha_nacimiento = LocalDate.parse(fecha_nacimiento_aux, fmt);

            System.out.print("¿Desea modificar la fecha de inscripcion? (S -> Si / N -> No:" );
            control = entrada.nextLine();

            if(control.equals("S"))
            {
                System.out.print("¿Desea aplicar la fecha actual (S / N)?: ");
                control = entrada.nextLine();

                if(control.equals("S"))
                {
                    fecha_inscripcion = LocalDate.now();
                }
                else
                {
                    System.out.print("Introduce la fecha de inscripcion (YYYY/MM/DD): ");
                    fecha_inscripcion_aux = entrada.nextLine();
                    fecha_inscripcion = LocalDate.parse(fecha_inscripcion_aux, fmt);
                }
            }
            else
            {
                fecha_inscripcion = actualizarUsuario.obtenerFecha(correo);

            }

            actualizarUsuario.actualizarUsuario(correo, nombre, fecha_nacimiento, fecha_inscripcion, correo_aux);
        }
        else
        {
            String respuesta = "";
            System.out.print("El usuario introducido no existe en la BD, ¿Desea crearlo? (S -> Si / N -> No): ");
            respuesta = entrada.nextLine();

            if(respuesta == "S" || respuesta == "s")
            {
                crearUsuarioBBDD();
            }
            else
            {
                System.out.print("Regresando al menu...");
            }
        }
    }



    public void crearPistaBBDD()
    {
        Scanner entrada = new Scanner(System.in);
        String nombre;
        String estado;
        dificultad Dificultad;
        int num_max;




        System.out.print("Introduce el nombre de la pista: ");
        nombre = entrada.nextLine();

        System.out.print("Introduce el estado de la pista (Disponible / Ocupada / En Mantenimiento): ");
        estado = entrada.nextLine();

        System.out.print("Introduce la dificultad de la pista (INFANTIL / FAMILIAR / ADULTOS): ");
        Dificultad = dificultad.valueOf(entrada.nextLine());

        System.out.print("Introduce el numero maximo de karts para la pista: ");
        num_max = entrada.nextInt();



        crearPista.crearPista(nombre, estado, Dificultad, num_max);
    }

    public String listarPistasMantenimientoBBDD()
    {
        String pista = "";

        ArrayList<entidadesDTO> pistas = listaPistasMantenimiento.listarPistasMantenimiento();

        for(entidadesDTO u: pistas)
        {
            pista = pista + u.toStringPista() + "\n";
        }

        return pista;
    }

    public String listarPistasDisponiblesBBDD()
    {
        String pista = "";

        ArrayList<entidadesDTO> pistas = listaPistasDisponibles.listarPistasDisponibles();

        for(entidadesDTO u: pistas)
        {
            pista = pista + u.toStringPista() + "\n";
        }

        return pista;
    }

    public void crearKartBBDD()
    {
        Scanner entrada = new Scanner(System.in);
        int id_kart;
        String tipo;
        estado Estado;


        System.out.print("Introduce el ID del Kart: ");
        id_kart = entrada.nextInt();
        entrada.nextLine();

        System.out.print("Introduce el tipo de Kart (Niño / Adulto): ");
        tipo = entrada.nextLine();

        System.out.print("Introduce el estado del Kart (Disponible / Reservado / Mantenimiento): ");
        Estado = estado.valueOf(entrada.nextLine());



        crearKart.crearKart(id_kart, tipo, Estado);
    }

    public String listarKartsDisponiblesBBDD()
    {
        String kart = "";

        ArrayList<entidadesDTO> karts = listarKartsDisponibles.listarKartsDisponibles();

        for(entidadesDTO u: karts)
        {
            kart = kart + u.toStringKart() + "\n";
        }

        return kart;
    }

    public void asociarKartPistaBBDD()
    {
        Scanner sc = new Scanner(System.in);
        int id_kart;
        String nombre_pista;

        System.out.print("Introduce el ID del Kart que desea seleccionar: ");
        id_kart = sc.nextInt();
        sc.nextLine();

        if(asociarKartPista.existeKart(id_kart) == true)
        {
            System.out.print("Introduce el nombre de la pista a la que se le desea asociar: ");
            nombre_pista = sc.nextLine();

            if(asociarKartPista.existePista(nombre_pista) == true)
            {
                asociarKartPista.listaKartPista(id_kart, nombre_pista);
            }
            else
            {
                System.out.print("La pista introducida en la BBDD no existe");
            }
        }
        else
        {
            System.out.print("El kart introducido no existe en la BBDD");
        }

    }

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
