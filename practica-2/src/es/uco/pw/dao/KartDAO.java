package es.uco.pw.dao;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import es.uco.pw.business.KartDTO;
import es.uco.pw.business.PistaDTO;
import es.uco.pw.business.dificultad;
import es.uco.pw.business.estado;
import es.uco.pw.connection.DBConnection;

public class KartDAO {

    public int crearKart(KartDTO kartDTO,Properties config, Properties sql)
    {
        int status=0;
        try
        {
        	Connection cn = DBConnection.getConnection(config);
            PreparedStatement ps=cn.prepareStatement(sql.getProperty("crearKart"));
            if(existeKart(kartDTO.getIdKart(),config,sql)==false) {
            ps.setInt(1, kartDTO.getIdKart());
            ps.setString(2, kartDTO.getTipo());
            ps.setString(3, String.valueOf(kartDTO.getEstado()));

            status=ps.executeUpdate(); 
            cn.close();
            ps.close(); }
            
            else 
            {
            	System.out.println("\nLa pista con nombre " + kartDTO.getIdKart() + " ya existe en la BD.\n");
                cn.close();
                ps.close();
            }
        }
        catch(Exception e)
        {
            System.out.print("ERROR: "+ e);
        }
        return status;
    }

    public ArrayList<KartDTO> listarKartsDisponibles(Properties config,Properties sql)
    {
        ArrayList<KartDTO> lista_karts = new ArrayList<KartDTO>();

        try
        {
        	Connection cn = DBConnection.getConnection(config);
            PreparedStatement ps=cn.prepareStatement(sql.getProperty("listarDisponibles"));
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                int id_kart = rs.getInt("id_kart");
                String tipo = rs.getString("tipo");
                estado Estado = estado.valueOf(rs.getString("estado"));
                lista_karts.add(new KartDTO(id_kart, tipo, Estado));
            }

            cn.close();
            ps.close();
        }
        catch (Exception e)
        {
            System.err.println(e);

        }
        return lista_karts;
    }

    
    public boolean existeKart(int kart_id,Properties config,Properties sql)
    {
        boolean kartExist = false;
        try
        {
            Connection cn = DBConnection.getConnection(config);
            PreparedStatement ps=cn.prepareStatement(sql.getProperty("existeKart"));
            ps.setInt(1, kart_id);
            ResultSet rs = ps.executeQuery();


            if(rs.next())
            {
                kartExist = true;
            }
            else
            {
                kartExist = false;
            }
            cn.close();
            ps.close();
        }
        catch(Exception e)
        {
            System.err.println("Error: " + e);
        }
        return kartExist;
    }
}
