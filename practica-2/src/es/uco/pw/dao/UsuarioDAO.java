package es.uco.pw.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.Properties;

import es.uco.pw.business.UsuarioDTO;
import es.uco.pw.connection.DBConnection;

public class UsuarioDAO {
	
	protected Properties sql;
    
	public UsuarioDAO() {
		
	}
	
	public boolean existeUsuario(String correo,Properties sql)
    {
        boolean userExist = false;
        try
        {	
        	DBConnection cn = new DBConnection();
            PreparedStatement ps=cn.conex.prepareStatement(sql.getProperty("InsertarUsuario"));
            ps.setString(1, correo);
            ResultSet rs = ps.executeQuery();


            if(rs.next())
            {
                userExist = true;
            }
            else
            {
                userExist = false;
            }
            cn.closeConnection();
            ps.close();
        }
        catch(Exception e)
        {
            System.err.println("Error: " + e);
        }
        return userExist;
    }


    public ArrayList<UsuarioDTO> listarUsuariosRegistrados(Properties sql)
    {
        ArrayList<UsuarioDTO> lista_usuarios = new ArrayList<UsuarioDTO>();

        try
        {
        	DBConnection cn = new DBConnection();
            PreparedStatement ps=cn.conex.prepareStatement(sql.getProperty("listarUsuario"));
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                String correo = rs.getString("correo");
                String nombre = rs.getString("nombre");
                LocalDate fecha_nacimiento = rs.getDate("fecha_nacimiento").toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate fecha_inscripcion = rs.getDate("fecha_inscripcion").toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                lista_usuarios.add(new UsuarioDTO(correo, nombre, fecha_nacimiento, fecha_inscripcion));
            }

           cn.closeConnection();
           ps.close();
        }
        catch (Exception e)
        {
            System.err.println(e);

        }
        return lista_usuarios;
    }

    public int registrarUsuario(UsuarioDTO usuarioDTO, Properties sql) throws SQLException
    {
    	int status=0;
        try
        {
        	DBConnection cn = new DBConnection();
            PreparedStatement ps=cn.conex.prepareStatement(sql.getProperty("listarUsuario"));

            if (existeUsuario(usuarioDTO.getCorreo(),sql) == false)
            {
                ps.setString(1, usuarioDTO.getCorreo());
                ps.setString(2, usuarioDTO.getNombre());
                ps.setDate(3, Date.valueOf(usuarioDTO.getFechaNacimiento()));
                ps.setDate(4, Date.valueOf(usuarioDTO.getFechaInscripcion()));

                status = ps.executeUpdate();
                cn.closeConnection();
                ps.close();
            }
            else
            {
                System.out.println("\nEl usuario con correo " + usuarioDTO.getCorreo() + " ya existe en la BD.\n");
                cn.closeConnection();
                ps.close();
            }
        }
        catch (Exception e)
        {
            System.err.println(e);
        }
		return status;
    }

    public int eliminarUsuario(String correo,Properties sql) throws SQLException
    {
    	int status=0;
        try
        {
        	DBConnection cn = new DBConnection();
            PreparedStatement ps=cn.conex.prepareStatement(sql.getProperty("borrarUsuario"));
            ps.setString(1, correo);
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

    public int actualizarUsuario(UsuarioDTO usuarioDTO, String correo, Properties sql)
    {
    	int status=0;
        try
        {
        	DBConnection cn = new DBConnection();
            PreparedStatement ps=cn.conex.prepareStatement(sql.getProperty("actualizarUsuario"));
            
            ps.setString(1,  usuarioDTO.getCorreo());
            ps.setString(2, usuarioDTO.getNombre());
            ps.setDate(3, Date.valueOf(usuarioDTO.getFechaNacimiento()));
            ps.setDate(4, Date.valueOf(usuarioDTO.getFechaInscripcion()));
            ps.setString(5, correo);
            
            status = ps.executeUpdate();
            cn.closeConnection();
            ps.close();
            System.out.print("\n --- USUARIO ACTUALIZADO CON EXITO --- \n");
        }
        catch(Exception e)
        {
            System.out.println("ERROR: " + e);
        }
        return status;
    }
    
    public LocalDate obtenerFecha(String correo,Properties sql) throws SQLException
	{
    	LocalDate fecha=null;
	    DBConnection cn = new DBConnection();
	    PreparedStatement ps=cn.conex.prepareStatement(sql.getProperty("obtenerfecha"));
	    ps.setString(1, correo);
	    
	    ResultSet rs=ps.executeQuery();
	    
	    try
	    {
	        if (rs.next())
	        {
	            fecha = rs.getDate("fecha_inscripcion").toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	        }
	        ps.close();
	        cn.conex.close();
	    }
	    catch (Exception e)
	    {
	        System.err.print("ERROR: " + e);
	    }
	
	    return fecha;
	}
}
