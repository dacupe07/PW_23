package es.uco.pw.dao;

import es.uco.pw.business.usuario.usuarioDTO;
import es.uco.pw.connection.DBConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Properties;

public class usuarioDAO
{
    private static usuarioDAO instance = null;
    private usuarioDTO usuario;
    int status = 0;

    protected Properties sql;

    public usuarioDAO(Properties sql)
    {
        this.sql = sql;
    }

    // FUNCIONES
    /**Funcion para comprobar la existencia de un usuario en la base datos
     * @param correo
     * @param sql Properties que contiene las sentencias sql utilizadas
     * @return userExist true si existe,false en caso contrario
     */
    public boolean existeUsuario(String correo, Properties sql)
    {
        boolean userExist = false;
        try
        {
            DBConnection cn = new DBConnection();
            String query = sql.getProperty("existeUsuario");

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


    /**Funcion para consultar todos usuario que hay en la base datos
     * @param sql Properties que contiene las sentencias sql utilizadas
     * @return lista_usuarios
     */
    public ArrayList<usuarioDTO> listarUsuariosRegistrados(Properties sql)
    {
        ArrayList<usuarioDTO> lista_usuarios = new ArrayList<usuarioDTO>();

        try
        {
            DBConnection cn = new DBConnection();

            String query = sql.getProperty("listarUsuario");


            Statement st = cn.conex.createStatement();
            ResultSet rs = st.executeQuery(query);

            while(rs.next())
            {
                String correo = rs.getString("correo");
                String nombre = rs.getString("nombre");
                LocalDate fecha_nacimiento = rs.getDate("fecha_nacimiento").toLocalDate();
                LocalDate fecha_inscripcion = rs.getDate("fecha_inscripcion").toLocalDate();
                lista_usuarios.add(new usuarioDTO(correo, nombre, fecha_nacimiento, fecha_inscripcion));
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


    /**Funcion para crear un usuario en la base datos 
     * @param correo
     * @param nombre 
     * @param fecha_nacimiento 
     * @param fecha_inscripcion
     * @param sql Properties que contiene las sentencias sql utilizadas
     * @throws SQLException
     */
    public void registrarUsuario(String correo, String nombre, LocalDate fecha_nacimiento, LocalDate fecha_inscripcion, Properties sql) throws SQLException
    {
        try
        {
            DBConnection cn = new DBConnection();
            String query = sql.getProperty("registrarUsuario");
            PreparedStatement ps = cn.conex.prepareStatement(query);
            java.sql.Date fecha_nacimiento_sql = java.sql.Date.valueOf(fecha_nacimiento);
            java.sql.Date fecha_inscripcion_sql = java.sql.Date.valueOf(fecha_inscripcion);

            if (existeUsuario(correo, sql) == false)
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


    /**Funcion para eliminar un usuario de la base de datos
     * @param correo
     * @param sql Properties que contiene las sentencias sql utilizadas
     * @throws SQLException
     */
    public void eliminarUsuario(String correo, Properties sql) throws SQLException
    {
        try
        {
            DBConnection cn = new DBConnection();
            String query = sql.getProperty("borrarUsuario");
            PreparedStatement ps = cn.conex.prepareStatement(query);

            status = ps.executeUpdate();
            cn.conex.close();
        }
        catch (Exception e)
        {
            System.err.println("ERROR: " + e);
        }
    }


     /**Funcion para actualizar un usuario en la base datos
     * @param correo
     * @param nombre 
     * @param fecha_nacimiento 
     * @param fecha_inscripcion
     * @param correo_aux
     * @param sql Properties que contiene las sentencias sql utilizadas
     */
    public void actualizarUsuario(String correo, String nombre, LocalDate fecha_nacimiento, LocalDate fecha_inscripcion, String correo_aux, Properties sql)
    {
        DBConnection cn = new DBConnection();

        try
        {
            //String query = "UPDATE usuario SET correo = '" + correo + "', nombre = '" + nombre + "', fecha_nacimiento = '" + fecha_nacimiento + "', fecha_inscripcion = '" + fecha_inscripcion + "'";
            String query = sql.getProperty("actualizarUsuario");
            PreparedStatement ps = cn.conex.prepareStatement(query);
            java.sql.Date fecha_nacimiento_sql = Date.valueOf(fecha_nacimiento);
            java.sql.Date fecha_inscripcion_sql = Date.valueOf(fecha_inscripcion);

            ps.setString(1,  correo);
            ps.setString(2, nombre);
            ps.setDate(3, fecha_nacimiento_sql);
            ps.setDate(4, fecha_inscripcion_sql);

            status = ps.executeUpdate();
            cn.conex.close();
            System.out.print("\n --- USUARIO ACTUALIZADO CON EXITO --- \n");
        }
        catch(Exception e)
        {
            System.out.println("ERROR: " + e);
        }
    }


    /**Funcion para obtener la fecha de inscripcion de un usuario
     * @param correo
     * @param sql Properties que contiene las sentencias sql utilizadas
     * @return fecha fecha de inscripcion de un usuario
     * @throws SQLException
     */
    public LocalDate obtenerFecha(String correo, Properties sql) throws SQLException
    {
        DBConnection cn = new DBConnection();

        String query = sql.getProperty("obtenerfecha");
        Statement st = cn.conex.createStatement();
        ResultSet rs = st.executeQuery(query);
        LocalDate fecha;

        usuario = new usuarioDTO();
        try
        {
            if (rs.next())
            {
                fecha = rs.getDate("fecha_inscripcion").toLocalDate();
                usuario.setFechaInscripcion(fecha);
            }
            st.close();
            cn.conex.close();
        }
        catch (Exception e)
        {
            System.err.print("ERROR: " + e);
        }

        fecha = usuario.getFechaInscripcion();

        return fecha;
    }

}
