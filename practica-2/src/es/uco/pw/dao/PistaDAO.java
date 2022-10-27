package es.uco.pw.dao;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import es.uco.pw.business.PistaDTO;
import es.uco.pw.business.dificultad;
import es.uco.pw.connection.DBConnection;

public class PistaDAO {
	
	public boolean existePista(String nombre,Properties config,Properties sql)
    {
        boolean userExist = false;
        try
        {
            Connection cn = DBConnection.getConnection(config);
            PreparedStatement ps=cn.prepareStatement(sql.getProperty("existePista"));
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();


            if(rs.next())
            {
                userExist = true;
            }
            else
            {
                userExist = false;
            }
            cn.close();
            ps.close();
        }
        catch(Exception e)
        {
            System.err.println("Error: " + e);
        }
        return userExist;
    }
	
	
	public int crearPista(PistaDTO pistaDTO, Properties config, Properties sql)
    {
        int status=0;
        try
        {
        	Connection cn = DBConnection.getConnection(config);
            PreparedStatement ps=cn.prepareStatement(sql.getProperty("crearPista"));
            
            if(existePista(pistaDTO.getNomPista(),config,sql) == false)
            {
            ps.setString(1, pistaDTO.getNomPista());
            ps.setString(2, pistaDTO.getEstPista());
            ps.setString(3, String.valueOf(pistaDTO.getDificultad()));
            ps.setInt(4, pistaDTO.getNumMax());
            
            System.out.println("PISTA REGISTRADA CON EXITO");
            status=ps.executeUpdate();
            cn.close();
            ps.close();
            }
            
            else 
            {
            	System.out.println("\nLa pista con nombre " + pistaDTO.getNomPista() + " ya existe en la BD.\n");
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


    public ArrayList<PistaDTO> listarPistasMantenimiento(Properties config,Properties sql)
    {
        ArrayList<PistaDTO> lista_pistas = new ArrayList<PistaDTO>();

        try
        {
        	Connection cn = DBConnection.getConnection(config);
            PreparedStatement ps=cn.prepareStatement(sql.getProperty("listarPistasMantenimiento"));
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                String nombre = rs.getString("nombre");
                String estado = rs.getString("estado");
                dificultad Dificultad = dificultad.valueOf(rs.getString("dificultad"));
                int num_max = rs.getInt("num_max");
                lista_pistas.add(new PistaDTO(nombre, estado, Dificultad, num_max));
            }

            cn.close();
            ps.close();
        }
        catch (Exception e)
        {
            System.err.println(e);

        }
        return lista_pistas;
    }


    public ArrayList<PistaDTO> listarPistasDisponibles(Properties config,Properties sql)
    {
    	 ArrayList<PistaDTO> lista_pistas = new ArrayList<PistaDTO>();

         try
         {
         	Connection cn = DBConnection.getConnection(config);
             PreparedStatement ps=cn.prepareStatement(sql.getProperty("listarPistasDisponibles"));
             ResultSet rs = ps.executeQuery();

             while(rs.next())
             {
                 String nombre = rs.getString("nombre");
                 String estado = rs.getString("estado");
                 dificultad Dificultad = dificultad.valueOf(rs.getString("dificultad"));
                 int num_max = rs.getInt("num_max");
                 lista_pistas.add(new PistaDTO(nombre, estado, Dificultad, num_max));
             }

             cn.close();
             ps.close();
         }
         catch (Exception e)
         {
             System.err.println(e);

         }
         return lista_pistas;
    }

}
