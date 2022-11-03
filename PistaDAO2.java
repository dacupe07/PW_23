package es.uco.pw.dao;

import java.util.ArrayList;
import java.sql.PreparedStatement;
import es.uco.pw.business.dificultad;
import es.uco.pw.business.PistaDTO;
import es.uco.pw.connection.DBConnection;

// FUNCIONES CLASE PISTA

public class PistaDAO2
{	
    public void crearPista(String nombre, String estado, dificultad Dificultad, int num_max)
    {
        DBConnection cn = new DBConnection();
        try
        {
            String query = "INSERT INTO pista (nombre, estado, dificultad, num_max) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = cn.conex.prepareStatement(query);

            if(existePista(nombre)==false)
            {
	            ps.setString(1, nombre);
	            ps.setString(2, estado);
	            ps.setString(3, String.valueOf(Dificultad));
	            ps.setInt(4, num_max);
	
	            ps.executeUpdate();
	            cn.conex.close();
            }
        }
        catch(Exception e)
        {
            System.out.print("ERROR: "+ e);
        }
    }


    public ArrayList<PistaDTO> listarPistasMantenimiento()
    {
        ArrayList<PistaDTO> lista_pistas = new ArrayList<PistaDTO>();

        try
        {
            DBConnection cn = new DBConnection();

            String query = "SELECT * FROM pista WHERE estado = 'Mantenimiento'";

            Statement st = cn.conex.createStatement();
            ResultSet rs = st.executeQuery(query);

            while(rs.next())
            {
                String nombre = rs.getString("nombre");
                String estado = rs.getString("estado");
                dificultad Dificultad = dificultad.valueOf(rs.getString("dificultad"));
                int num_max = rs.getInt("num_max");
                lista_pistas.add(new PistaDTO(nombre, estado, Dificultad, num_max));
            }

            cn.conex.close();
            st.close();
        }
        catch (Exception e)
        {
            System.err.println(e);

        }
        return lista_pistas;
    }


    public ArrayList<PistaDTO> listarPistasDisponibles()
    {
        ArrayList<PistaDTO> lista_pistas = new ArrayList<PistaDTO>();

        try
        {
            DBConnection cn = new DBConnection();

            String query = "SELECT * FROM pista WHERE estado = 'Disponible'";

            Statement st = cn.conex.createStatement();
            ResultSet rs = st.executeQuery(query);

            while(rs.next())
            {
                String nombre = rs.getString("nombre");
                String estado = rs.getString("estado");
                dificultad Dificultad = dificultad.valueOf(rs.getString("dificultad"));
                int num_max = rs.getInt("num_max");
                lista_pistas.add(new PistaDTO(nombre, estado, Dificultad, num_max));
            }

            cn.conex.close();
            st.close();
        }
        catch (Exception e)
        {
            System.err.println(e);

        }
        return lista_pistas;
    }


    public boolean existePista(String nombre)
    {
        boolean pistaExist = false;
        try
        {
            DBConnection cn = new DBConnection();
            String query = "SELECT * FROM pista WHERE nombre = '" + nombre + "'";

            Statement st = cn.conex.createStatement();
            ResultSet rs = st.executeQuery(query);


            if(rs.next())
            {
                pistaExist = true;
            }
            else
            {
                pistaExist = false;
            }
            cn.conex.close();
            st.close();
        }
        catch(Exception e)
        {
            System.err.println("Error: " + e);
        }
        return pistaExist;
    }
}
