package es.uco.pw.dao;

import es.uco.pw.business.reserva.reservaDTO;
import es.uco.pw.business.usuario.usuarioDTO;
import es.uco.pw.connection.DBConnection;

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
    private reservaDTO reserva;

    private usuarioDTO usuario;

    int status = 0;

    protected Properties sql;

    public reservaDAO(Properties sql)
    {
        this.sql = sql;
    }

    // FUNCIONES

    /**Funcion para consultar la existencia de una reserva
     * @param idReserva
     * @param sql Properties que contiene las sentencias sql utilizadas
     * @return resertExist true si existe, false en caso contrario
     */
    public boolean existeReserva(int idReserva, Properties sql)
    {
        boolean resertExist = false;
        try
        {
            DBConnection cn = new DBConnection();
            String query = sql.getProperty("existeReserva");

            Statement st = cn.conex.createStatement();
            ResultSet rs = st.executeQuery(query);


            if(rs.next())
            {
                resertExist = true;
            }
            else
            {
                resertExist = false;
            }
            cn.conex.close();
            st.close();
        }
        catch(Exception e)
        {
            System.err.println("Error: " + e);
        }
        return resertExist;
    }


    /**Funcion que comprueba la antiguedad de un usuario
     * @param correo
     * @param sql Properties que contiene las sentencias sql utilizadas
     * @return control true si la antiguedad es mayor de 2 años
     * @throws SQLException
     */
    public boolean comprobarAntiguedad(String correo, Properties sql) throws SQLException
    {
        DBConnection cn = new DBConnection();

        boolean control = false;

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String query = sql.getProperty("comprobarAntiguedad");

        Statement st = cn.conex.createStatement();
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
            cn.conex.close();
        }
        catch(Exception e)
        {
            System.err.print("ERROR: " + e);
        }

        return control;
    }

    /**Funcion para crear una reserva individual en la base de datos
     * @param correo
     * @param nom_pista
     * @param idRes
     * @param fecha
     * @param duracion
     * @param hora
     * @param precio
     * @param descuento
     * @param sql Properties que contiene las sentencias sql utilizadas
     * @throws SQLException
     */
    public void reservaIndividual(String correo, String nom_pista, int idRes, LocalDate fecha, int duracion, String hora, float precio, float descuento, Properties sql) throws SQLException
    {
        DBConnection cn = new DBConnection();

        String query = sql.getProperty("reservaIndividual");

        PreparedStatement ps = cn.conex.prepareStatement(query);

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

            cn.conex.close();
        }
        catch(Exception e)
        {
            System.err.print("ERROR: " + e);
        }
    }


    /**Funcion para listar las reservas que hay en una fecha y para una pista
     * @param fecha
     * @param pista
     * @param sql Properties que contiene las sentencias sql utilizadas
     * @return lista_reservas
     */
    public ArrayList<reservaDTO> listarReservas(LocalDate fecha, String pista, Properties sql)
    {
        ArrayList<reservaDTO> lista_reservas = new ArrayList<reservaDTO>();

        try
        {
            DBConnection cn = new DBConnection();

            String query = sql.getProperty("listarReservas");

            Statement st = cn.conex.createStatement();
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

            cn.conex.close();
            st.close();
        }
        catch (Exception e)
        {
            System.err.println(e);

        }
        return lista_reservas;
    }

    /**Funcion que lista las reservas que hay despues de una fecha
     * @param fecha
     * @param pista
     * @param sql Properties que contiene las sentencias sql utilizadas
     * @return lista_reservas
     */
    public ArrayList<reservaDTO> listarReservasFuturas(LocalDate fecha, String pista, Properties sql)
    {
        ArrayList<reservaDTO> lista_reservas = new ArrayList<reservaDTO>();

        try
        {
            DBConnection cn = new DBConnection();

            String query = sql.getProperty("listarReservasFuturas");

            Statement st = cn.conex.createStatement();
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

            cn.conex.close();
            st.close();
        }
        catch (Exception e)
        {
            System.err.println(e);

        }
        return lista_reservas;
    }


    /**Funcion para eliminar una reserva de la base de datos
     * @param idRes
     * @param sql Properties que contiene las sentencias sql utilizadas
     * @throws SQLException
     */
    public void cancelarReserva(int idRes, Properties sql) throws SQLException
    {
        try
        {
            DBConnection cn = new DBConnection();
            String query = sql.getProperty("cancelarReserva");
            PreparedStatement ps = cn.conex.prepareStatement(query);

            status = ps.executeUpdate();
            cn.conex.close();
        }
        catch (Exception e)
        {
            System.err.println("ERROR: " + e);
        }
    }

    /**Funcion para actualizar una reserva en la base de datos
     * @param correo
     * @param nom_pista
     * @param idRes
     * @param fecha
     * @param duracion
     * @param hora
     * @param precio
     * @param descuento
     * @param sql Properties que contiene las sentencias sql utilizadas
     */
    public void actualizarReserva(String correo, String nom_pista, int idRes, LocalDate fecha, int duracion, String hora, float precio, float descuento, Properties sql)
    {
        DBConnection cn = new DBConnection();

        try
        {
            String query = sql.getProperty("actualizarReserva");
            PreparedStatement ps = cn.conex.prepareStatement(query);

            ps.setInt(1, idRes);
            ps.setFloat(2, precio);
            ps.setInt(3, duracion);
            ps.setFloat(4, descuento);
            ps.setString(5, hora);
            ps.setString(6, String.valueOf(fecha));
            ps.setString(7, correo);
            ps.setString(8, nom_pista);

            status = ps.executeUpdate();
            cn.conex.close();
            System.out.print("\n --- RESERVA ACTUALIZADA CON EXITO --- \n");
        }
        catch(Exception e)
        {
            System.out.println("ERROR: " + e);
        }
    }

}
