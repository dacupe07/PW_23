package es.uco.pw.business;

import es.uco.pw.dao.entidadesDAO;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Scanner entrada = new Scanner(System.in);
        String nombre, correo, fecha_inscripcion_aux, fecha_nacimiento_aux;
        Date fecha_inscripcion, fecha_nacimiento;

        Calendar c1 = Calendar.getInstance();

        String dia = Integer.toString(c1.get(Calendar.DATE));
        String mes = Integer.toString(c1.get(Calendar.MONTH));
        String year = Integer.toString(c1.get(Calendar.YEAR));

        fecha_inscripcion_aux = year + "/" + mes + "/" + dia;
        fecha_inscripcion = sdf.parse(fecha_inscripcion_aux);



        System.out.print("Introduce el correo del usuario: ");
        correo = entrada.nextLine();

        System.out.print("Introduce el nombre del usuario: ");
        nombre = entrada.nextLine();

        System.out.print("Introduce la fecha de nacimiento (YYYY/MM/DD): ");
        fecha_nacimiento_aux = entrada.nextLine();
        fecha_nacimiento = sdf.parse(fecha_nacimiento_aux);


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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Scanner entrada = new Scanner(System.in);
        String nombre, correo_aux, correo, fecha_inscripcion_aux, fecha_nacimiento_aux;
        Date fecha_inscripcion, fecha_nacimiento;

        System.out.print("Introduce el email del usuario que desea actualizar: ");
        correo_aux = entrada.nextLine();

        if(actualizarUsuario.existeUsuario(correo_aux) == true)
        {
            Calendar c1 = Calendar.getInstance();

            String dia = Integer.toString(c1.get(Calendar.DATE));
            String mes = Integer.toString(c1.get(Calendar.MONTH));
            String year = Integer.toString(c1.get(Calendar.YEAR));

            fecha_inscripcion_aux = year + "/" + mes + "/" + dia;
            fecha_inscripcion = sdf.parse(fecha_inscripcion_aux);


            System.out.print("Introduce el correo del usuario: ");
            correo = entrada.nextLine();

            System.out.print("Introduce el nombre del usuario: ");
            nombre = entrada.nextLine();

            System.out.print("Introduce la fecha de nacimiento (YYYY/MM/DD): ");
            fecha_nacimiento_aux = entrada.nextLine();
            fecha_nacimiento = sdf.parse(fecha_nacimiento_aux);

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
}
