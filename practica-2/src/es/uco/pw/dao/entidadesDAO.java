package es.uco.pw.dao;

import es.uco.pw.business.entidadesDTO;
import es.uco.pw.connection.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class entidadesDAO
{
    private static entidadesDAO instance = null;
    private entidadesDTO entidades;
    int status = 0;


    // FUNCIONES CLASE USUARIO

    public boolean existeUsuario(String correo)
    {
        boolean userExist = false;
        try
        {
            DBConnection cn = new DBConnection();
            String query = "SELECT * FROM usuario WHERE correo = '" + correo + "'";

            Statement st = cn.conex.createStatement();
            ResultSet rs = st.executeQuery(query);


            if(rs.next())
            {
                userExist = true;
            }
            else
            {
                userExist = false;
            }
            cn.conex.close();
            st.close();
        }
        catch(Exception e)
        {
            System.err.println("Error: " + e);
        }
        return userExist;
    }


    public ArrayList<entidadesDTO> listarUsuariosRegistrados()
    {
        ArrayList<entidadesDTO> lista_usuarios = new ArrayList<entidadesDTO>();

        try
        {
            DBConnection cn = new DBConnection();

            String query = "SELECT * FROM usuario";

            Statement st = cn.conex.createStatement();
            ResultSet rs = st.executeQuery(query);

            while(rs.next())
            {
                String correo = rs.getString("correo");
                String nombre = rs.getString("nombre");
                Date fecha_nacimiento = rs.getDate("fecha_nacimiento");
                Date fecha_inscripcion = rs.getDate("fecha_inscripcion");
                lista_usuarios.add(new entidadesDTO(correo, nombre, fecha_nacimiento, fecha_inscripcion));
            }

           cn.conex.close();
            st.close();
        }
        catch (Exception e)
        {
            System.err.println(e);

        }
        return lista_usuarios;
    }

    public void registrarUsuario(String correo, String nombre, Date fecha_nacimiento, Date fecha_inscripcion) throws SQLException
    {
        try
        {
            DBConnection cn = new DBConnection();
            String query = "INSERT INTO usuario (correo, nombre, fecha_nacimiento, fecha_inscripcion) VALUES(?,?,?,?)";
            PreparedStatement ps = cn.conex.prepareStatement(query);
            java.sql.Date fecha_nacimiento_sql = new java.sql.Date(fecha_nacimiento.getYear(), fecha_nacimiento.getMonth(), fecha_nacimiento.getDay());
            java.sql.Date fecha_inscripcion_sql = new java.sql.Date(fecha_inscripcion.getYear(), fecha_inscripcion.getMonth(), fecha_inscripcion.getDay());

            if (existeUsuario(correo) == false)
            {
                ps.setString(1, correo);
                ps.setString(2, nombre);
                ps.setDate(3, fecha_nacimiento_sql);
                ps.setDate(4, fecha_inscripcion_sql);

                System.out.println("USUARIO REGISTRADO CON EXITO");
                status = ps.executeUpdate();
                cn.conex.close();
            }
            else
            {
                System.out.println("\nEl usuario con correo " + correo + " ya existe en la BD.\n");
                cn.conex.close();
            }
        }
        catch (Exception e)
        {
            System.err.println(e);
        }
    }

    public void eliminarUsuario(String correo) throws SQLException
    {
        try
        {
            DBConnection cn = new DBConnection();
            String query = "DELETE FROM usuario WHERE correo = '" + correo + "'";
            PreparedStatement ps = cn.conex.prepareStatement(query);

            status = ps.executeUpdate();
            cn.conex.close();
        }
        catch (Exception e)
        {
            System.err.println("ERROR: " + e);
        }
    }

    public void actualizarUsuario(String correo)
    {
        DBConnection cn = new DBConnection();

        try
        {
            //String query = "UPDATE usuario SET correo = '" + correo + "', nombre = '" + nombre + "'"
        }
        catch(Exception e)
        {
            System.out.println("ERROR: " + e);
        }
    }
}
