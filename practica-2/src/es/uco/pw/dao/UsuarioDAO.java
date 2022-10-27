package es.uco.pw.dao;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import es.uco.pw.business.UsuarioDTO;
import es.uco.pw.connection.DBConnection;

public class UsuarioDAO {
	
	public boolean existeUsuario(String correo,Properties config,Properties sql)
    {
        boolean userExist = false;
        try
        {
            Connection cn = DBConnection.getConnection(config);
            PreparedStatement ps=cn.prepareStatement(sql.getProperty("InsertarUsuario"));
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
            cn.close();
            ps.close();
        }
        catch(Exception e)
        {
            System.err.println("Error: " + e);
        }
        return userExist;
    }


    public ArrayList<UsuarioDTO> listarUsuariosRegistrados(Properties config,Properties sql)
    {
        ArrayList<UsuarioDTO> lista_usuarios = new ArrayList<UsuarioDTO>();

        try
        {
        	Connection cn = DBConnection.getConnection(config);
            PreparedStatement ps=cn.prepareStatement(sql.getProperty("listarUsuario"));
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                String correo = rs.getString("correo");
                String nombre = rs.getString("nombre");
                Date fecha_nacimiento = rs.getDate("fecha_nacimiento");
                Date fecha_inscripcion = rs.getDate("fecha_inscripcion");
                lista_usuarios.add(new UsuarioDTO(correo, nombre, fecha_nacimiento, fecha_inscripcion));
            }

           cn.close();
           ps.close();
        }
        catch (Exception e)
        {
            System.err.println(e);

        }
        return lista_usuarios;
    }

    public int registrarUsuario(UsuarioDTO usuarioDTO, Properties config, Properties sql) throws SQLException
    {
    	int status=0;
        try
        {
        	Connection cn = DBConnection.getConnection(config);
            PreparedStatement ps=cn.prepareStatement(sql.getProperty("listarUsuario"));

            if (existeUsuario(usuarioDTO.getCorreo(),config,sql) == false)
            {
                ps.setString(1, usuarioDTO.getCorreo());
                ps.setString(2, usuarioDTO.getNombre());
                ps.setDate(3, (java.sql.Date) usuarioDTO.getFechaNacimiento());
                ps.setDate(4, (java.sql.Date) usuarioDTO.getFechaInscripcion());

                System.out.println("USUARIO REGISTRADO CON EXITO");
                status = ps.executeUpdate();
                cn.close();
                ps.close();
            }
            else
            {
                System.out.println("\nEl usuario con correo " + usuarioDTO.getCorreo() + " ya existe en la BD.\n");
                cn.close();
                ps.close();
            }
        }
        catch (Exception e)
        {
            System.err.println(e);
        }
		return status;
    }

    public int eliminarUsuario(UsuarioDTO usuarioDTO, Properties config, Properties sql) throws SQLException
    {
    	int status=0;
        try
        {
        	Connection cn = DBConnection.getConnection(config);
            PreparedStatement ps=cn.prepareStatement(sql.getProperty("borrarUsuario"));
            status = ps.executeUpdate();
            cn.close();
            ps.close();
        }
        catch (Exception e)
        {
            System.err.println("ERROR: " + e);
        }
        return status;
    }

    public int actualizarUsuario(UsuarioDTO usuarioDTO, String correo,Properties config, Properties sql)
    {
    	int status=0;
        try
        {
        	Connection cn = DBConnection.getConnection(config);
            PreparedStatement ps=cn.prepareStatement(sql.getProperty("actualizarUsuario"));

            ps.setString(1,  usuarioDTO.getCorreo());
            ps.setString(2, usuarioDTO.getNombre());
            ps.setDate(3, (java.sql.Date) usuarioDTO.getFechaNacimiento());
            ps.setDate(4, (java.sql.Date) usuarioDTO.getFechaInscripcion());
            ps.setString(5, correo);
            
            status = ps.executeUpdate();
            cn.close();
            ps.close();
            System.out.print("\n --- USUARIO ACTUALIZADO CON EXITO --- \n");
        }
        catch(Exception e)
        {
            System.out.println("ERROR: " + e);
        }
        return status;
    }

}
