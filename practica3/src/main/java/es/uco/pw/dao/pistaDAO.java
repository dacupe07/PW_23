package es.uco.pw.dao;

import es.uco.pw.business.pista.dificultad;
import es.uco.pw.business.pista.pistaDTO;
import es.uco.pw.business.usuario.usuarioDTO;
import es.uco.pw.connection.DBConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

public class pistaDAO
{
    private static pistaDAO instance = null;
    private static DBConnection con;
    private static Connection connection;
    private pistaDTO pista;
    int status = 0;

    protected Properties sql;
    
    public pistaDAO(String BDdriver, String BDurl, String BDuser, String BDpass) throws SQLException 
    {

		con = new DBConnection(BDdriver, BDurl, BDuser, BDpass);
	}

    public pistaDAO(Properties sql)
    {
        this.sql = sql;
    }

    // FUNCIONES

    public void crearPista(String nombre, String estado, dificultad Dificultad, int num_max, Properties sql) throws SQLException
    {
        con.DBConnection();
        connection = con.getJdbcConnection();
        try
        {
            String query = sql.getProperty("crearPista");
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, nombre);
            ps.setString(2, estado);
            ps.setString(3, String.valueOf(Dificultad));
            ps.setInt(4, num_max);

            ps.executeUpdate();
            con.desconectar();;
        }
        catch(Exception e)
        {
            System.out.print("ERROR: "+ e);
        }
    }



    public ArrayList<pistaDTO> listarPistasMantenimiento(Properties sql)
    {
        ArrayList<pistaDTO> lista_pistas = new ArrayList<pistaDTO>();

        try
        {
            con.DBConnection();
            connection = con.getJdbcConnection();
            String query = sql.getProperty("listarPistasMantenimiento");

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);

            while(rs.next())
            {
                String nombre = rs.getString("nombre");
                String estado = rs.getString("estado");
                dificultad Dificultad = dificultad.valueOf(rs.getString("dificultad"));
                int num_max = rs.getInt("num_max");
                lista_pistas.add(new pistaDTO(nombre, estado, Dificultad, num_max));
            }

            con.desconectar();
            st.close();
        }
        catch (Exception e)
        {
            System.err.println(e);

        }
        return lista_pistas;
    }



    public ArrayList<pistaDTO> listarPistasDisponibles(Properties sql)
    {
        ArrayList<pistaDTO> lista_pistas = new ArrayList<pistaDTO>();

        try
        {
            con.DBConnection();
            connection = con.getJdbcConnection();
            String query = sql.getProperty("listarPistasDisponibles");

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);

            while(rs.next())
            {
                String nombre = rs.getString("nombre");
                String estado = rs.getString("estado");
                dificultad Dificultad = dificultad.valueOf(rs.getString("dificultad"));
                int num_max = rs.getInt("num_max");
                lista_pistas.add(new pistaDTO(nombre, estado, Dificultad, num_max));
            }

            con.desconectar();
            st.close();
        }
        catch (Exception e)
        {
            System.err.println(e);

        }
        return lista_pistas;
    }



    public boolean existePista(String nombre, Properties sql)
    {
        boolean pistaExist = false;
        try
        {
            con.DBConnection();
            connection = con.getJdbcConnection();
            String query = sql.getProperty("existePista");

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);


            if(rs.next())
            {
                pistaExist = true;
            }
            else
            {
                pistaExist = false;
            }
            con.desconectar();
            st.close();
        }
        catch(Exception e)
        {
            System.err.println("Error: " + e);
        }
        return pistaExist;
    }
    
    
	public int comprobarRegistro(String nombre) throws IOException 
	{
		int comprobacion = 5;

		try 
		{
			con.DBConnection();
			connection = con.getJdbcConnection();
			Statement statement2 = connection.createStatement();
			ResultSet resultset2 = statement2.executeQuery("select * from pista where nombre ='" + nombre + "'");

			while (resultset2.next()) 
			{

				comprobacion = 0;

			}
			
			con.desconectar();
		} catch (Exception e) {
			System.out.println(e);
		}

		return comprobacion;
	}
	
	public int save_info(pistaDTO usuario) throws IOException {
		int status = 0;

		try {
			con.DBConnection();
			connection = con.getJdbcConnection();
			PreparedStatement ps = connection.prepareStatement("INSERT INTO pista (nombre,estado,dificultad,num_max) VALUES(?,?,?,?)");

			ps.setString(1, usuario.getNomPista());
			ps.setString(2, usuario.getEstPista());
			ps.setString(3, String.valueOf(usuario.getDificultad()));
			ps.setInt(4, usuario.getNumMax());
			
			status = ps.executeUpdate();
			con.desconectar();
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}

}
