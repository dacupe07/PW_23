package es.uco.pw.dao;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import es.uco.pw.business.KartDTO;
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
    
    public int AsociarKartPista(int id_kart, String nombre_pista,Properties config,Properties sql)
    {
    	int status=0;
        try
        {	
        	Connection cn = DBConnection.getConnection(config);
        	PreparedStatement ps1=cn.prepareStatement(sql.getProperty("queryPista"));
            ps1.setString(1,nombre_pista);
            dificultad Dificultad;
            String tipo_kart;
            KartDTO kart=new KartDTO();
            PistaDTO pista=new PistaDTO();
            
            System.out.print("A");
            ResultSet rs_pista = ps1.executeQuery();

            while(rs_pista.next())
            {
                System.out.print("B");
                Dificultad = dificultad.valueOf(rs_pista.getString("dificultad"));
                pista.setDificultad(Dificultad);
            }

            ps1.close();
            
            PreparedStatement ps2=cn.prepareStatement(sql.getProperty("queryKart"));
            ps2.setInt(1,id_kart);
            ResultSet rs_kart = ps2.executeQuery();

            while(rs_kart.next())
            {
                System.out.print("C");
                tipo_kart = rs_kart.getString("tipo");
                kart.setTipo(tipo_kart);
            }

            ps2.close();

            PreparedStatement ps = cn.prepareStatement(sql.getProperty("query_pista_kart"));
            System.out.print("D");
            if(pista.getDificultad() == dificultad.valueOf("infantil") && kart.getTipo() == "ninos")
            {
                System.out.print("E");
                ps.setInt(1, id_kart);
                ps.setString(2, nombre_pista);
                ps.setString(3, String.valueOf(pista.getDificultad()));
                ps.setString(4, kart.getTipo());

                status = ps.executeUpdate();
            }

            if(pista.getDificultad() == dificultad.valueOf("adulto") && kart.getTipo() == "adultos")
            {
                System.out.print("F");
                ps.setInt(1, id_kart);
                ps.setString(2, nombre_pista);
                ps.setString(3, String.valueOf(pista.getDificultad()));
                ps.setString(4, kart.getTipo());

                status = ps.executeUpdate();
            }

            if(pista.getDificultad() == dificultad.valueOf("familiar") && (kart.getTipo() == "adultos" || kart.getTipo() == "ninos"))
            {
                System.out.print("g");
                ps.setInt(1, id_kart);
                ps.setString(2, nombre_pista);
                ps.setString(3, String.valueOf(pista.getDificultad()));
                ps.setString(4, kart.getTipo());

                status = ps.executeUpdate();
            }

            cn.close();
        }
        catch (Exception e)
        {
            System.err.println(e);

        }
        return status;
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
