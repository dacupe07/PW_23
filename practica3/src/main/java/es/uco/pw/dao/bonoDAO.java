package es.uco.pw.dao;

import es.uco.pw.business.bono.bonoDTO;
import es.uco.pw.connection.DBConnection;


import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Properties;
import java.sql.Connection;

public class bonoDAO
{
    private static bonoDAO instance = null;
    private static DBConnection con;
    private static Connection connection;
    private bonoDTO entidades;
    int status = 0;

    protected Properties sql;
    
    public bonoDAO(String BDdriver, String BDurl, String BDuser, String BDpass) throws SQLException 
    {

		con = new DBConnection(BDdriver, BDurl, BDuser, BDpass);
	}
    
    public bonoDAO(Properties sql)
    {
        this.sql = sql;
    }

    // FUNCIONES

    public boolean existeBono(int IDbono,Properties sql)
    {
        boolean bonoExist = false;
        try
        {
            con.DBConnection();
            connection = con.getJdbcConnection();
            PreparedStatement ps = connection.prepareStatement(sql.getProperty("existeBono"));
            ps.setInt(1, IDbono);
            ResultSet rs = ps.executeQuery();


            if(rs.next())
            {
                bonoExist = true;
            }
            else
            {
                bonoExist = false;
            }
            con.desconectar();
            ps.close();
        }
        catch(Exception e)
        {
            System.err.println("Error: " + e);
        }
        return bonoExist;
    }

    public int crearBono(bonoDTO bono,Properties sql)
    {

        int status=0;

        try 
        {
        	con.DBConnection();
        	connection = con.getJdbcConnection();
            PreparedStatement ps=connection.prepareStatement(sql.getProperty("crearBono"));

            if(existeBono(bono.getIdBono(),sql)==false)
            {
                ps.setInt(1, bono.getIdBono());
                ps.setString(2, bono.getUsuario());
                ps.setInt(3, bono.getNumSesiones());
                ps.setString(4, bono.getTipoBono());
                ps.setDate(5, Date.valueOf(bono.getCaducidad()));
                ps.setDouble(6, bono.getDesc());

                status = ps.executeUpdate();
                con.desconectar();
                ps.close();

            }
            else {
                System.out.println("\nEl bono con id " + bono.getIdBono() + " ya existe en la BD.\n");
                con.desconectar();
                ps.close();

            }

        }

        catch(Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    public ArrayList<bonoDTO> listarBonos(Properties sql)
    {
        ArrayList<bonoDTO> lista_bonos = new ArrayList<bonoDTO>();

        try
        {
            con.DBConnection();
            PreparedStatement ps=connection.prepareStatement(sql.getProperty("listarBono"));
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                int Id_bono=rs.getInt("id_bono");
                String correo=rs.getString("correo_usuario");
                int num_sesiones=rs.getInt("num_sesiones");
                String tipo=rs.getString("tipo_bono");
                LocalDate caducidad=rs.getDate("caducidad").toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                double descuento=rs.getDouble("desc");

                lista_bonos.add(new bonoDTO(Id_bono,correo,num_sesiones,tipo,caducidad,descuento));
            }

            con.desconectar();
            ps.close();
        }
        catch (Exception e)
        {
            System.err.println(e);

        }
        return lista_bonos;
    }

    public int eliminarUsuario(int id,Properties sql) throws SQLException
    {
        int status=0;
        try
        {
            con.DBConnection();
            PreparedStatement ps=connection.prepareStatement(sql.getProperty("borrarBono"));
            ps.setInt(1, id);
            status = ps.executeUpdate();
            con.desconectar();
            ps.close();
        }
        catch (Exception e)
        {
            System.err.println("ERROR: " + e);
        }
        return status;
    }

    public boolean tieneBono(String correo,Properties sql)
    {
        boolean bonoExist=false;
        try
        {
            con.DBConnection();
            PreparedStatement ps=connection.prepareStatement(sql.getProperty("tieneBono"));
            ps.setString(1, correo);
            ResultSet rs = ps.executeQuery();


            if(rs.next())
            {
                if(rs.getInt("num_sesiones")>0 && rs.getDate("caducidad").toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isAfter(LocalDate.now())) {
                    bonoExist=true; }
                else {
                    bonoExist=false;
                }
            }
            else
            {
                bonoExist=false;
            }
            con.desconectar();
            ps.close();
        }
        catch(Exception e)
        {
            System.err.println("Error: " + e);
        }
        return bonoExist;
    }
}
