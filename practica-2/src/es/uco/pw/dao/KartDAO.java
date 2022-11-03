package es.uco.pw.dao;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import es.uco.pw.business.KartDTO;
import es.uco.pw.business.PistaDTO;
import es.uco.pw.business.dificultad;
import es.uco.pw.business.estado;
import es.uco.pw.connection.DBConnection;


public class KartDAO {
	
	int status = 0;
	
	protected KartDTO kart;
	protected PistaDTO pista;
	
	public void crearKart(int id_kart, String tipo, estado Estado)
    {
        DBConnection cn = new DBConnection();
        try
        {
            String query = "INSERT INTO kart (id_kart, tipo, estado) VALUES (?, ?, ?)";
            PreparedStatement ps = cn.conex.prepareStatement(query);
            
            if(existeKart(kart.getIdKart())==false) {
            	ps.setInt(1, id_kart);
                ps.setString(2, tipo);
                ps.setString(3, String.valueOf(Estado));

                ps.executeUpdate();
                cn.conex.close();
            }
            else {
            	System.out.println("\nEl kart cuyo ID es " + id_kart + " ya existe en la BD.\n");
                cn.conex.close();
                ps.close();
            }

            
        }
        catch(Exception e)
        {
            System.out.print("ERROR: "+ e);
        }
    }

	public ArrayList<KartDTO> listarKartsDisponibles()
	{
        ArrayList<KartDTO> lista_karts = new ArrayList<KartDTO>();

        try
        {
            DBConnection cn = new DBConnection();

            String query = "SELECT * FROM kart WHERE estado = 'Disponible'";

	        Statement st = cn.conex.createStatement();
	        ResultSet rs = st.executeQuery(query);

	        while(rs.next())
	        {
	            int id_kart = rs.getInt("id_kart");
	            String tipo = rs.getString("tipo");
	            estado Estado = estado.valueOf(rs.getString("estado"));
	            lista_karts.add(new KartDTO(id_kart, tipo, Estado));
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

    
	public boolean existeKart(int id_kart)
    {
        boolean kartExist = false;
        try
        {
            DBConnection cn = new DBConnection();
            String query = "SELECT * FROM kart WHERE id_kart = '" + id_kart + "'";

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
	public void listaKartPista(int id_kart, String nombre_pista)
    {
        DBConnection cn = new DBConnection();

        try
        {
            String query_pista = "SELECT dificultad FROM pista WHERE nombre = '" + nombre_pista + "'";
            String query_kart = "SELECT tipo FROM kart WHERE id_kart = " + id_kart + "";
            String query_pista_kart = "INSERT INTO kart_pista (id_kart, nombre_pista, dificultad_pista, tipo_kart) VALUES (?, ?, ?, ?)";
            dificultad Dificultad;
            String tipo_kart;
            String dif;
            String tipo;

            Statement st = cn.conex.createStatement();
            ResultSet rs_pista = st.executeQuery(query_pista);

            kart = new KartDTO();
            pista = new PistaDTO();

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
}
