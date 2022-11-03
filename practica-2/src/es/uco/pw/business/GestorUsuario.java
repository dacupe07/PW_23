package es.uco.pw.business;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import es.uco.pw.dao.UsuarioDAO;

public class GestorUsuario {
	
	public GestorUsuario()
    {

    }
	
    UsuarioDAO crearUsuario = new UsuarioDAO();
    UsuarioDAO listaUsuarios = new UsuarioDAO();
    UsuarioDAO eliminarUsuario = new UsuarioDAO();
    UsuarioDAO actualizarUsuario = new UsuarioDAO();
	
	public String listarUsuariosBBDD(Properties sql)
    {
        String usuario = "";

        ArrayList<UsuarioDTO> usuarios = listaUsuarios.listarUsuariosRegistrados(sql);

        for(UsuarioDTO u: usuarios)
        {
            usuario = usuario + u.toString() + "\n";
        }

        return usuario;
    }

    public void crearUsuarioBBDD(Properties sql) throws ParseException, SQLException {
    	DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        Scanner entrada = new Scanner(System.in);
        String nombre, correo, fecha_nacimiento_aux;
        LocalDate fecha_inscripcion, fecha_nacimiento;

        fecha_inscripcion = LocalDate.now();

        System.out.print("Introduce el correo del usuario: ");
        correo = entrada.nextLine();

        System.out.print("Introduce el nombre del usuario: ");
        nombre = entrada.nextLine();

        System.out.print("Introduce la fecha de nacimiento (YYYY/MM/DD): ");
        fecha_nacimiento_aux = entrada.nextLine();
        fecha_nacimiento = LocalDate.parse(fecha_nacimiento_aux, fmt);

        entrada.close();
        UsuarioDTO usuario=new UsuarioDTO(correo, nombre, fecha_nacimiento, fecha_inscripcion);
        crearUsuario.registrarUsuario(usuario,sql);
    }

    public void borrarUsuarioBBDD(Properties sql) throws ParseException, SQLException {
        Scanner entrada = new Scanner(System.in);
        String correo;

        System.out.print("Introduce el correo del usuario: ");
        correo = entrada.nextLine();
        
        entrada.close();
        eliminarUsuario.eliminarUsuario(correo,sql);
        System.out.println("\n--- USUARIO ELIMINADO CON EXITO ---\n");
    }

    public void actualizarUsuarioBBDD(Properties sql) throws ParseException, SQLException {
    	DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        Scanner entrada = new Scanner(System.in);
        String nombre, correo_aux, correo, fecha_inscripcion_aux, fecha_nacimiento_aux, control;
        LocalDate fecha_inscripcion, fecha_nacimiento;

        System.out.print("Introduce el email del usuario que desea actualizar: ");
        correo_aux = entrada.nextLine();

        if(actualizarUsuario.existeUsuario(correo_aux,sql) == true)
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
                fecha_inscripcion = actualizarUsuario.obtenerFecha(correo,sql);

            }
            UsuarioDTO usuario= new UsuarioDTO(correo, nombre, fecha_nacimiento, fecha_inscripcion);
            actualizarUsuario.actualizarUsuario(usuario,correo_aux,sql);
            entrada.close();
        }
        else
        {
            String respuesta = "";
            System.out.print("El usuario introducido no existe en la BD, ¿Desea crearlo? (S -> Si / N -> No): ");
            respuesta = entrada.nextLine();
            entrada.close();

            if(respuesta == "S" || respuesta == "s")
            {
                crearUsuarioBBDD(sql);
            }
            else
            {
                System.out.print("Regresando al menu...");
            }
        }
    }
}
