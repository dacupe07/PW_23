package es.uco.pw.dao;

import es.uco.pw.business.reserva.reservaDTO;
import es.uco.pw.business.usuario.usuarioDTO;
import es.uco.pw.connection.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Properties;

public class reservaDAO
{
    private static reservaDAO instance = null;
    private static DBConnection con;
    private static Connection connection;
    private reservaDTO reserva;

    private usuarioDTO usuario;

    int status = 0;

    protected Properties sql;

    
    public reservaDAO(String BDdriver, String BDurl, String BDuser, String BDpass) throws SQLException 
    {

		con = new DBConnection(BDdriver, BDurl, BDuser, BDpass);
	}
    
    public reservaDAO(Properties sql)
    {
        this.sql = sql;
    }

    // FUNCIONES


    public boolean existeReserva(int idReserva, Properties sql)
    {
        boolean resertExist = false;
        try
        {
            con.DBConnection();
            connection = con.getJdbcConnection();
            String query = sql.getProperty("existeReserva");

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);


            if(rs.next())
            {
                resertExist = true;
            }
            else
            {
                resertExist = false;
            }
            con.desconectar();
            st.close();
        }
        catch(Exception e)
        {
            System.err.println("Error: " + e);
        }
        return resertExist;
    }



    public boolean comprobarAntiguedad(String correo, Properties sql) throws SQLException
    {
        con.DBConnection();
        connection = con.getJdbcConnection();

        boolean control = false;

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String query = sql.getProperty("comprobarAntiguedad");

        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);
        String fecha_aux;
        LocalDate fechaInscripcion;


        reserva = new reservaDTO();
        usuario = new usuarioDTO();
        try
        {
            while (rs.next())
            {
                fecha_aux = rs.getString("fecha_inscripcion");
                fechaInscripcion = LocalDate.parse(fecha_aux);
                usuario.setFechaInscripcion(fechaInscripcion);
            }

            LocalDate ahora = LocalDate.now();

            fechaInscripcion = usuario.getFechaInscripcion();

            Period periodo = Period.between(fechaInscripcion, ahora);
            int años = periodo.getYears();

            if (años >= 2)
            {
                control = true;
            }
            else
            {
                control = false;
            }

            st.close();
            con.desconectar();
        }
        catch(Exception e)
        {
            System.err.print("ERROR: " + e);
        }

        return control;
    }


    public void reservaIndividual(String correo, String nom_pista, int idRes, LocalDate fecha, int duracion, String hora, float precio, float descuento, Properties sql) throws SQLException
    {
        con.DBConnection();
        connection = con.getJdbcConnection();

        String query = sql.getProperty("reservaIndividual");

        PreparedStatement ps = connection.prepareStatement(query);

        try
        {
            ps.setInt(1, idRes);
            ps.setFloat(2, precio);
            ps.setInt(3, duracion);
            ps.setFloat(4, descuento);
            ps.setString(5, hora);
            ps.setDate(6, java.sql.Date.valueOf(fecha));
            ps.setString(7, correo);
            ps.setString(8, nom_pista);

            ps.executeUpdate();

            con.desconectar();
        }
        catch(Exception e)
        {
            System.err.print("ERROR: " + e);
        }
    }



    public ArrayList<reservaDTO> listarReservas(LocalDate fecha, String pista, Properties sql)
    {
        ArrayList<reservaDTO> lista_reservas = new ArrayList<reservaDTO>();

        try
        {
            con.DBConnection();
            connection = con.getJdbcConnection();

            String query = sql.getProperty("listarReservas");

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);

            while(rs.next())
            {
                int idReserva = rs.getInt("idReserva");
                float precio = rs.getFloat("precio");
                int duracion = rs.getInt("duracion");
                float descuento = rs.getFloat("descuento");
                String hora = rs.getString("hora");
                String usuario = rs.getString("usuario");
                lista_reservas.add(new reservaDTO(idReserva, precio, duracion, descuento, hora, fecha, usuario, pista));
            }

            con.desconectar();
            st.close();
        }
        catch (Exception e)
        {
            System.err.println(e);

        }
        return lista_reservas;
    }


    public ArrayList<reservaDTO> listarReservasFuturas(LocalDate fecha, String pista, Properties sql)
    {
        ArrayList<reservaDTO> lista_reservas = new ArrayList<reservaDTO>();

        try
        {
            con.DBConnection();
            connection = con.getJdbcConnection();

            String query = sql.getProperty("listarReservasFuturas");

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);

            while(rs.next())
            {
                int idReserva = rs.getInt("idReserva");
                float precio = rs.getFloat("precio");
                int duracion = rs.getInt("duracion");
                float descuento = rs.getFloat("descuento");
                String hora = rs.getString("hora");
                String usuario = rs.getString("usuario");
                lista_reservas.add(new reservaDTO(idReserva, precio, duracion, descuento, hora, fecha, usuario, pista));
            }

            con.desconectar();
            st.close();
        }
        catch (Exception e)
        {
            System.err.println(e);

        }
        return lista_reservas;
    }



    public void cancelarReserva(int idRes, Properties sql) throws SQLException
    {
        try
        {
            con.DBConnection();
            connection = con.getJdbcConnection();
            String query = sql.getProperty("cancelarReserva");
            PreparedStatement ps = connection.prepareStatement(query);

            status = ps.executeUpdate();
            con.desconectar();
        }
        catch (Exception e)
        {
            System.err.println("ERROR: " + e);
        }
    }


    public void actualizarReserva(String correo, String nom_pista, int idRes, LocalDate fecha, int duracion, String hora, float precio, float descuento, Properties sql) throws SQLException
    {
        con.DBConnection();
        connection = con.getJdbcConnection();

        try
        {
            String query = sql.getProperty("actualizarReserva");
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setInt(1, idRes);
            ps.setFloat(2, precio);
            ps.setInt(3, duracion);
            ps.setFloat(4, descuento);
            ps.setString(5, hora);
            ps.setString(6, String.valueOf(fecha));
            ps.setString(7, correo);
            ps.setString(8, nom_pista);

            status = ps.executeUpdate();
            con.desconectar();
            System.out.print("\n --- RESERVA ACTUALIZADA CON EXITO --- \n");
        }
        catch(Exception e)
        {
            System.out.println("ERROR: " + e);
        }
    }

}
