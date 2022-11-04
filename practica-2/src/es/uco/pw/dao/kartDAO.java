package es.uco.pw.dao;

import es.uco.pw.business.kart.estado;
import es.uco.pw.business.kart.kartDTO;
import es.uco.pw.business.pista.dificultad;
import es.uco.pw.business.pista.pistaDTO;
import es.uco.pw.connection.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

public class kartDAO
{
    private static kartDAO instance = null;
    private kartDTO kart;

    private pistaDTO pista;

    int status = 0;

    protected Properties sql;

    public kartDAO(Properties sql)
    {
        this.sql = sql;
    }



    // FUNCIONES

    public void crearKart(int id_kart, String tipo, estado Estado, Properties sql)
    {
        DBConnection cn = new DBConnection();
        try
        {
            String query = sql.getProperty("crearKart");
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



    public ArrayList<kartDTO> listarKartsDisponibles(Properties sql)
    {
        ArrayList<kartDTO> lista_karts = new ArrayList<kartDTO>();

        try
        {
            DBConnection cn = new DBConnection();

            String query = sql.getProperty("listarKartsDisponibles");

            Statement st = cn.conex.createStatement();
            ResultSet rs = st.executeQuery(query);

            while(rs.next())
            {
                int id_kart = rs.getInt("id_kart");
                String tipo = rs.getString("tipo");
                estado Estado = estado.valueOf(rs.getString("estado"));
                lista_karts.add(new kartDTO(id_kart, tipo, Estado));
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



    public void listaKartPista(int id_kart, String nombre_pista, Properties sql)
    {
        DBConnection cn = new DBConnection();

        try
        {
            String query_pista = sql.getProperty("queryPista");
            String query_kart = sql.getProperty("query_kart");
            String query_pista_kart = sql.getProperty("query_pista_kart");
            dificultad Dificultad;
            String tipo_kart;
            String dif;
            String tipo;

            Statement st = cn.conex.createStatement();
            ResultSet rs_pista = st.executeQuery(query_pista);

            kart = new kartDTO();
            pista = new pistaDTO();

            while(rs_pista.next())
            {
                Dificultad = dificultad.valueOf(rs_pista.getString("dificultad"));
                pista.setDificultad(Dificultad);
            }

            st.close();

            Statement st1 = cn.conex.createStatement();
            ResultSet rs_kart = st1.executeQuery(query_kart);

            while(rs_kart.next())
            {
                System.out.print("C");
                tipo_kart = rs_kart.getString("tipo");
                kart.setTipo(tipo_kart);
            }

            st1.close();

            PreparedStatement ps = cn.conex.prepareStatement(query_pista_kart);
            dif = String.valueOf(pista.getDificultad());
            tipo = kart.getTipo();

            if(dif.equals("infantil") && tipo.equals("Niño"))
            {
                System.out.print("E");
                ps.setInt(1, id_kart);
                ps.setString(2, nombre_pista);
                ps.setString(3, String.valueOf(pista.getDificultad()));
                ps.setString(4, kart.getTipo());

                status = ps.executeUpdate();
            }

            if(dif.equals("adultos") && tipo.equals("Adulto"))
            {
                System.out.print("F");
                ps.setInt(1, id_kart);
                ps.setString(2, nombre_pista);
                ps.setString(3, String.valueOf(pista.getDificultad()));
                ps.setString(4, kart.getTipo());

                status = ps.executeUpdate();
            }

            if(dif.equals("familiar") && tipo.equals("Niño"))
            {
                System.out.print("g");
                ps.setInt(1, id_kart);
                ps.setString(2, nombre_pista);
                ps.setString(3, String.valueOf(pista.getDificultad()));
                ps.setString(4, kart.getTipo());

                status = ps.executeUpdate();
            }

            if(dif.equals("familiar") && tipo.equals("Adulto"))
            {
                System.out.print("g");
                ps.setInt(1, id_kart);
                ps.setString(2, nombre_pista);
                ps.setString(3, String.valueOf(pista.getDificultad()));
                ps.setString(4, kart.getTipo());

                status = ps.executeUpdate();
            }

            cn.conex.close();
        }
        catch (Exception e)
        {
            System.err.println(e);

        }
    }



    public boolean existeKart(int id_kart, Properties sql)
    {
        boolean kartExist = false;
        try
        {
            DBConnection cn = new DBConnection();
            String query = sql.getProperty("existeKart");

            Statement st = cn.conex.createStatement();
            ResultSet rs = st.executeQuery(query);


            if(rs.next())
            {
                kartExist = true;
            }
            else
            {
                kartExist = false;
            }
            cn.conex.close();
            st.close();
        }
        catch(Exception e)
        {
            System.err.println("Error: " + e);
        }
        return kartExist;
    }

}
