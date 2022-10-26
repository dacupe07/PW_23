package es.uco.pw.dao;

import es.uco.pw.business.dificultad;
import es.uco.pw.business.entidadesDTO;
import es.uco.pw.business.estado;
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

    public void actualizarUsuario(String correo, String nombre, Date fecha_nacimiento, Date fecha_inscripcion, String correo_aux)
    {
        DBConnection cn = new DBConnection();

        try
        {
            //String query = "UPDATE usuario SET correo = '" + correo + "', nombre = '" + nombre + "', fecha_nacimiento = '" + fecha_nacimiento + "', fecha_inscripcion = '" + fecha_inscripcion + "'";
            String query = "UPDATE usuario SET correo = ?, nombre = ?, fecha_nacimiento = ?, fecha_inscripcion = ? WHERE correo = '" + correo_aux + "'";
            PreparedStatement ps = cn.conex.prepareStatement(query);
            java.sql.Date fecha_nacimiento_sql = new java.sql.Date(fecha_nacimiento.getYear(), fecha_nacimiento.getMonth(), fecha_nacimiento.getDay());
            java.sql.Date fecha_inscripcion_sql = new java.sql.Date(fecha_inscripcion.getYear(), fecha_inscripcion.getMonth(), fecha_inscripcion.getDay());

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


    // FUNCIONES CLASE PISTA

    public void crearPista(String nombre, String estado, dificultad Dificultad, int num_max)
    {
        DBConnection cn = new DBConnection();
        try
        {
            String query = "INSERT INTO pista (nombre, estado, dificultad, num_max) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = cn.conex.prepareStatement(query);

            ps.setString(1, nombre);
            ps.setString(2, estado);
            ps.setString(3, String.valueOf(Dificultad));
            ps.setInt(4, num_max);

            ps.executeUpdate();
            cn.conex.close();
        }
        catch(Exception e)
        {
            System.out.print("ERROR: "+ e);
        }
    }


    public ArrayList<entidadesDTO> listarPistasMantenimiento()
    {
        ArrayList<entidadesDTO> lista_pistas = new ArrayList<entidadesDTO>();

        try
        {
            DBConnection cn = new DBConnection();

            String query = "SELECT * FROM pista WHERE estado = 'Mantenimiento'";

            Statement st = cn.conex.createStatement();
            ResultSet rs = st.executeQuery(query);

            while(rs.next())
            {
                String nombre = rs.getString("nombre");
                String estado = rs.getString("estado");
                dificultad Dificultad = dificultad.valueOf(rs.getString("dificultad"));
                int num_max = rs.getInt("num_max");
                lista_pistas.add(new entidadesDTO(nombre, estado, Dificultad, num_max));
            }

            cn.conex.close();
            st.close();
        }
        catch (Exception e)
        {
            System.err.println(e);

        }
        return lista_pistas;
    }


    public ArrayList<entidadesDTO> listarPistasDisponibles()
    {
        ArrayList<entidadesDTO> lista_pistas = new ArrayList<entidadesDTO>();

        try
        {
            DBConnection cn = new DBConnection();

            String query = "SELECT * FROM pista WHERE estado = 'Disponible'";

            Statement st = cn.conex.createStatement();
            ResultSet rs = st.executeQuery(query);

            while(rs.next())
            {
                String nombre = rs.getString("nombre");
                String estado = rs.getString("estado");
                dificultad Dificultad = dificultad.valueOf(rs.getString("dificultad"));
                int num_max = rs.getInt("num_max");
                lista_pistas.add(new entidadesDTO(nombre, estado, Dificultad, num_max));
            }

            cn.conex.close();
            st.close();
        }
        catch (Exception e)
        {
            System.err.println(e);

        }
        return lista_pistas;
    }


    // FUNCIONES CLASE KART

    public void crearKart(int id_kart, String tipo, estado Estado)
    {
        DBConnection cn = new DBConnection();
        try
        {
            String query = "INSERT INTO kart (id_kart, tipo, estado) VALUES (?, ?, ?)";
            PreparedStatement ps = cn.conex.prepareStatement(query);

            ps.setInt(1, id_kart);
            ps.setString(2, tipo);
            ps.setString(3, String.valueOf(Estado));

            ps.executeUpdate();
            cn.conex.close();
        }
        catch(Exception e)
        {
            System.out.print("ERROR: "+ e);
        }
    }

    public ArrayList<entidadesDTO> listarKartsDisponibles()
    {
        ArrayList<entidadesDTO> lista_karts = new ArrayList<entidadesDTO>();

        try
        {
            DBConnection cn = new DBConnection();

            String query = "SELECT * FROM kart WHERE estado = 'Disponible'";

            Statement st = cn.conex.createStatement();
            ResultSet rs = st.executeQuery(query);

            while(rs.next())
            {
                int id_kart = rs.getInt("id_kart");
                String tipo = rs.getString("tipo");
                estado Estado = estado.valueOf(rs.getString("estado"));
                lista_karts.add(new entidadesDTO(id_kart, tipo, Estado));
            }

            cn.conex.close();
            st.close();
        }
        catch (Exception e)
        {
            System.err.println(e);

        }
        return lista_karts;
    }
}
