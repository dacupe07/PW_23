package es.uco.pw.business;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;

import es.uco.pw.dao.UsuarioDAO;

public class GestorUsuario {
	
	public GestorUsuario()
    {

    }

    UsuarioDAO crearUsuario = new UsuarioDAO();
    UsuarioDAO listaUsuarios = new UsuarioDAO();
    UsuarioDAO eliminarUsuario = new UsuarioDAO();
    UsuarioDAO actualizarUsuario = new UsuarioDAO();
	
	public String listarUsuariosBBDD(Properties sql,Properties config)
    {
        String usuario = "";

        ArrayList<UsuarioDTO> usuarios = listaUsuarios.listarUsuariosRegistrados(config,sql);

        for(UsuarioDTO u: usuarios)
        {
            usuario = usuario + u.toString() + "\n";
        }

        return usuario;
    }

    public void crearUsuarioBBDD(Properties config,Properties sql) throws ParseException, SQLException {
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

        entrada.close();
        UsuarioDTO usuario=new UsuarioDTO(correo, nombre, fecha_nacimiento, fecha_inscripcion);
        crearUsuario.registrarUsuario(usuario,config,sql);
    }

    public void borrarUsuarioBBDD(Properties config,Properties sql) throws ParseException, SQLException {
        Scanner entrada = new Scanner(System.in);
        String correo;

        System.out.print("Introduce el correo del usuario: ");
        correo = entrada.nextLine();

        eliminarUsuario.eliminarUsuario(correo,config,sql);
        System.out.println("\n--- USUARIO ELIMINADO CON EXITO ---\n");
    }

    public void actualizarUsuarioBBDD(Properties config, Properties sql) throws ParseException, SQLException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Scanner entrada = new Scanner(System.in);
        String nombre, correo_aux, correo, fecha_inscripcion_aux, fecha_nacimiento_aux;
        Date fecha_inscripcion, fecha_nacimiento;

        System.out.print("Introduce el email del usuario que desea actualizar: ");
        correo_aux = entrada.nextLine();

        if(actualizarUsuario.existeUsuario(correo_aux,config,sql) == true)
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
            UsuarioDTO usuario=new UsuarioDTO(correo, nombre, fecha_nacimiento, fecha_inscripcion);
            actualizarUsuario.actualizarUsuario(usuario,correo_aux,config,sql);
        }
        else
        {
            String respuesta = "";
            System.out.print("El usuario introducido no existe en la BD, ¿Desea crearlo? (S -> Si / N -> No): ");
            respuesta = entrada.nextLine();

            if(respuesta == "S" || respuesta == "s")
            {
                crearUsuarioBBDD(config,sql);
            }
            else
            {
                System.out.print("Regresando al menu...");
            }
        }
    }
}
