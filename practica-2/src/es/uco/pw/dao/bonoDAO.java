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

public class bonoDAO
{
    private static bonoDAO instance = null;
    private bonoDTO entidades;
    int status = 0;

    protected Properties sql;

    public bonoDAO(Properties sql)
    {
        this.sql = sql;
    }

    // FUNCIONES

    /**Funcion para existe un bono
     * @param IDbono
     * @param sql Archivo properties con todas las consutas sql
     */
    public boolean existeBono(int IDbono,Properties sql)
    {
        boolean bonoExist = false;
        try
        {
            DBConnection cn = new DBConnection();
            PreparedStatement ps=cn.conex.prepareStatement(sql.getProperty("existeBono"));
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
            cn.closeConnection();
            ps.close();
        }
        catch(Exception e)
        {
            System.err.println("Error: " + e);
        }
        return bonoExist;
    }

    /**Funcion para crear bono
     * @param bono
     * @param sql Archivo properties con todas las consutas sql
     */
    public int crearBono(bonoDTO bono,Properties sql)
    {

        int status=0;

        try {
            DBConnection cn = new DBConnection();
            PreparedStatement ps=cn.conex.prepareStatement(sql.getProperty("crearBono"));

            if(existeBono(bono.getIdBono(),sql)==false)
            {
                ps.setInt(1, bono.getIdBono());
                ps.setString(2, bono.getUsuario());
                ps.setInt(3, bono.getNumSesiones());
                ps.setString(4, bono.getTipoBono());
                ps.setDate(5, Date.valueOf(bono.getCaducidad()));
                ps.setDouble(6, bono.getDesc());

                status = ps.executeUpdate();
                cn.closeConnection();
                ps.close();

            }
            else {
                System.out.println("\nEl bono con id " + bono.getIdBono() + " ya existe en la BD.\n");
                cn.closeConnection();
                ps.close();

            }

        }

        catch(Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    /**Funcion para listar los bonos
     * @param sql Archivo properties con todas las consutas sql
     * @return lista_bonos Lista todos los bonos
     */
    public ArrayList<bonoDTO> listarBonos(Properties sql)
    {
        ArrayList<bonoDTO> lista_bonos = new ArrayList<bonoDTO>();

        try
        {
            DBConnection cn = new DBConnection();
            PreparedStatement ps=cn.conex.prepareStatement(sql.getProperty("listarBono"));
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

            cn.closeConnection();
            ps.close();
        }
        catch (Exception e)
        {
            System.err.println(e);

        }
        return lista_bonos;
    }

    /**Funcion para eliminar un bono
     * @param correo
     * @param sql Archivo properties con todas las consultas sql
     * @throws SQLException
     */
    public int eliminarUsuario(int id,Properties sql) throws SQLException
    {
        int status=0;
        try
        {
            DBConnection cn = new DBConnection();
            PreparedStatement ps=cn.conex.prepareStatement(sql.getProperty("borrarBono"));
            ps.setInt(1, id);
            status = ps.executeUpdate();
            cn.conex.close();
            ps.close();
        }
        catch (Exception e)
        {
            System.err.println("ERROR: " + e);
        }
        return status;
    }

    /**Funcion para saber si un usuario tiene un bono valido
     * @param correo
     * @param sql Archivo properties con todas las consultas sql
     * @throws SQLException
     */
    public boolean tieneBono(String correo,Properties sql)
    {
        boolean bonoExist=false;
        try
        {
            DBConnection cn = new DBConnection();
            PreparedStatement ps=cn.conex.prepareStatement(sql.getProperty("tieneBono"));
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
            cn.closeConnection();
            ps.close();
        }
        catch(Exception e)
        {
            System.err.println("Error: " + e);
        }
        return bonoExist;
    }
}
