package es.uco.pw.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import es.uco.pw.business.ReservaDTO;
import es.uco.pw.business.UsuarioDTO;
import es.uco.pw.connection.DBConnection;

public class ReservaDAO {
	
	private ReservaDTO reserva;
	private UsuarioDTO usuario;
	int status = 0;
	
	public boolean existeReserva(int idReserva)
    {
        boolean resertExist = false;
        try
        {
            DBConnection cn = new DBConnection();
            String query = "SELECT * FROM reserva WHERE idReserva = " + idReserva + "";

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


    public boolean comprobarAntiguedad(String correo) throws SQLException
    {
        DBConnection cn = new DBConnection();

        boolean control = false;

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String query = "SELECT * FROM usuario WHERE correo = '" + correo + "'";

        Statement st = cn.conex.createStatement();
        ResultSet rs = st.executeQuery(query);
        String fecha_aux;
        LocalDate fechaInscripcion;


        usuario = new UsuarioDTO();
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

    public void reservaIndividual(String correo, String pista, int idRes, LocalDate fecha, int duracion, String hora, float precio, float descuento) throws SQLException
    {
        DBConnection cn = new DBConnection();

        String query = "INSERT INTO reserva (idReserva, precio, duracion, descuento, hora, fecha, usuario, pista) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

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
            ps.setString(8, pista);

            ps.executeUpdate();

            cn.conex.close();
        }
        catch(Exception e)
        {
            System.err.print("ERROR: " + e);
        }
    }

    public ArrayList<ReservaDTO> listarReservas(LocalDate fecha, String pista)
    {
        ArrayList<ReservaDTO> lista_reservas = new ArrayList<ReservaDTO>();

        try
        {
            DBConnection cn = new DBConnection();

            String query = "SELECT * FROM reserva WHERE fecha = '" + fecha + "' AND pista = '" + pista + "'";

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
                lista_reservas.add(new ReservaDTO(idReserva, precio, duracion, descuento, hora, fecha, usuario, pista));
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


    public ArrayList<ReservaDTO> listarReservasFuturas(LocalDate fecha, String pista)
    {
        ArrayList<ReservaDTO> lista_reservas = new ArrayList<ReservaDTO>();

        try
        {
            DBConnection cn = new DBConnection();

            String query = "SELECT * FROM reserva WHERE fecha >= '" + fecha + "' AND pista = '" + pista + "'";

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
                lista_reservas.add(new ReservaDTO(idReserva, precio, duracion, descuento, hora, fecha, usuario, pista));
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

    public void cancelarReserva(int idRes) throws SQLException
    {
        try
        {
            DBConnection cn = new DBConnection();
            String query = "DELETE FROM reserva WHERE idReserva = " + idRes + "";
            PreparedStatement ps = cn.conex.prepareStatement(query);

            status = ps.executeUpdate();
            cn.conex.close();
        }
        catch (Exception e)
        {
            System.err.println("ERROR: " + e);
        }
    }

    public void actualizarReserva(String correo, String nom_pista, int idRes, LocalDate fecha, int duracion, String hora, float precio, float descuento)
    {
        DBConnection cn = new DBConnection();

        try
        {
            String query = "UPDATE reserva SET idReserva = ?, precio = ?, duracion = ?, descuento = ?, hora = ?, fecha = ?, usuario = ?, pista = ? WHERE idReserva = " + idRes + "";
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
