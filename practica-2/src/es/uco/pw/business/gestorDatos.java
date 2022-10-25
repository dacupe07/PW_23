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

    public void actualizarUsuarioBBDD() throws ParseException {
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
    }



}
