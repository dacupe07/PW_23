package es.uco.pw.dao;

import es.uco.pw.business.pista.dificultad;
import es.uco.pw.business.pista.pistaDTO;
import es.uco.pw.connection.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

public class pistaDAO
{
    private static pistaDAO instance = null;
    private pistaDTO pista;
    int status = 0;

    protected Properties sql;

    public pistaDAO(Properties sql)
    {
        this.sql = sql;
    }

    // FUNCIONES

    /**Funcion para crear pistas asociadas a la pista que hay en la base de datos
     * @param nombre
     * @param estado
     * @param dificultad
     * @param num_max
     * @param sql Properties que contiene las sentencias sql utilizadas
     */
    public void crearPista(String nombre, String estado, dificultad Dificultad, int num_max, Properties sql)
    {
        DBConnection cn = new DBConnection();
        try
        {
            String query = sql.getProperty("crearPista");
            PreparedStatement ps = cn.conex.prepareStatement(query);

            ps.setString(1, nombre);
            ps.setString(2, estado);
            ps.setString(3, String.valueOf(Dificultad));
            ps.setInt(4, num_max);

            ps.executeUpdate();
            cn.conex.close();
        }
        catch(Exception e)
        {
            System.out.print("ERROR: "+ e);
        }
    }


    /**Funcion para consultar las pistas que estan en mantenimiento
     * @param sql Properties que contiene las sentencias sql utilizadas
     * @return lista_pistas
     */
    public ArrayList<pistaDTO> listarPistasMantenimiento(Properties sql)
    {
        ArrayList<pistaDTO> lista_pistas = new ArrayList<pistaDTO>();

        try
        {
            DBConnection cn = new DBConnection();

            String query = sql.getProperty("listarPistasMantenimiento");

            Statement st = cn.conex.createStatement();
            ResultSet rs = st.executeQuery(query);

            while(rs.next())
            {
                String nombre = rs.getString("nombre");
                String estado = rs.getString("estado");
                dificultad Dificultad = dificultad.valueOf(rs.getString("dificultad"));
                int num_max = rs.getInt("num_max");
                lista_pistas.add(new pistaDTO(nombre, estado, Dificultad, num_max));
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


    /**Funcion para consultar las pistas que estan disponibles
     * @param sql Properties que contiene las sentencias sql utilizadas
     * @return lista_pistas
     */
    public ArrayList<pistaDTO> listarPistasDisponibles(Properties sql)
    {
        ArrayList<pistaDTO> lista_pistas = new ArrayList<pistaDTO>();

        try
        {
            DBConnection cn = new DBConnection();

            String query = sql.getProperty("listarPistasDisponibles");

            Statement st = cn.conex.createStatement();
            ResultSet rs = st.executeQuery(query);

            while(rs.next())
            {
                String nombre = rs.getString("nombre");
                String estado = rs.getString("estado");
                dificultad Dificultad = dificultad.valueOf(rs.getString("dificultad"));
                int num_max = rs.getInt("num_max");
                lista_pistas.add(new pistaDTO(nombre, estado, Dificultad, num_max));
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


    /**Funcion para consultar si existe una pista
     * @param nombre
     * @param sql Properties que contiene las sentencias sql utilizadas
     * @return pistaExist true si existe, false en caso contrario
     */
    public boolean existePista(String nombre, Properties sql)
    {
        boolean pistaExist = false;
        try
        {
            DBConnection cn = new DBConnection();
            String query = sql.getProperty("existePista");

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
