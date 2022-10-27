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
import es.uco.pw.business.entidadesDTO;
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

            ps.setInt(1, kartDTO.getIdKart());
            ps.setString(2, kartDTO.getTipo());
            ps.setString(3, String.valueOf(kartDTO.getEstado()));

            status=ps.executeUpdate();
            cn.close();
            ps.close();
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

    public int listaKartPista(int id_kart, String nombre_pista,Properties config,Properties sql)
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
