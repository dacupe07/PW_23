package es.uco.pw.dao;

import es.uco.pw.business.pista.dificultad;
import es.uco.pw.business.usuario.*;
import es.uco.pw.business.usuario.usuarioDTO;
import es.uco.pw.connection.DBConnection;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Properties;

public class usuarioDAO
{
    private static usuarioDAO instance = null;
    private static DBConnection con;
    private static Connection connection;
    private usuarioDTO usuario;
    int status = 0;

    protected Properties sql;
    
    public usuarioDAO(String BDdriver, String BDurl, String BDuser, String BDpass) throws SQLException 
    {

		con = new DBConnection(BDdriver, BDurl, BDuser, BDpass);
	}

    public usuarioDAO(Properties sql)
    {
        this.sql = sql;
    }

    // FUNCIONES

    
    public boolean validarUsuario(String correo, String password)
    {
    	boolean validar = false;
    	
    	try
    	{
    		con.DBConnection();
    		connection = con.getJdbcConnection();
    		Statement st = connection.createStatement();
    		ResultSet rs;
    		
    		rs = st.executeQuery("SELECT * FROM sesion_usuario WHERE correo ='" + correo + "' AND password ='" + password + "'");
    		
    		if(rs.next())
    		{
    			return validar = true;
    		}
    		con.desconectar();
    		
    	}
    	catch(Exception e)
    	{
    		System.err.print(e);
    	}
    	
    	return validar;
    }
    
    
    public boolean existeUsuario(String correo, Properties sql)
    {
        boolean userExist = false;
        try
        {
            con.DBConnection();
            connection = con.getJdbcConnection();
            String query = sql.getProperty("existeUsuario");

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, correo);

            if(rs.next())
            {
                userExist = true;
            }
            else
            {
                userExist = false;
            }
            con.desconectar();
            st.close();
        }
        catch(Exception e)
        {
            System.err.println("Error: " + e);
        }
        return userExist;
    }



    public ArrayList<usuarioDTO> listarUsuariosRegistrados(Properties sql)
    {
        ArrayList<usuarioDTO> lista_usuarios = new ArrayList<usuarioDTO>();

        try
        {
            con.DBConnection();
            connection = con.getJdbcConnection();

            String query = sql.getProperty("listarUsuario");


            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);

            while(rs.next())
            {
                String correo = rs.getString("correo");
                String nombre = rs.getString("nombre");
                String password = rs.getString("password");
                LocalDate fecha_nacimiento = rs.getDate("fecha_nacimiento").toLocalDate();
                LocalDate fecha_inscripcion = rs.getDate("fecha_inscripcion").toLocalDate();
                rol Rol = rol.valueOf(rs.getString("rol"));
                lista_usuarios.add(new usuarioDTO(correo, nombre, fecha_nacimiento, fecha_inscripcion, password, Rol));
            }

            con.desconectar();
            st.close();
        }
        catch (Exception e)
        {
            System.err.println(e);

        }
        return lista_usuarios;
    }



    public void registrarUsuario(String correo, String nombre, LocalDate fecha_nacimiento, LocalDate fecha_inscripcion, Properties sql) throws SQLException
    {
        try
        {
            con.DBConnection();
            connection = con.getJdbcConnection();
            String query = sql.getProperty("registrarUsuario");
            PreparedStatement ps = connection.prepareStatement(query);
            java.sql.Date fecha_nacimiento_sql = java.sql.Date.valueOf(fecha_nacimiento);
            java.sql.Date fecha_inscripcion_sql = java.sql.Date.valueOf(fecha_inscripcion);

            if (existeUsuario(correo, sql) == false)
            {
                ps.setString(1, correo);
                ps.setString(2, nombre);
                ps.setDate(3, fecha_nacimiento_sql);
                ps.setDate(4, fecha_inscripcion_sql);

                System.out.println("USUARIO REGISTRADO CON EXITO");
                status = ps.executeUpdate();
                con.desconectar();
            }
            else
            {
                System.out.println("\nEl usuario con correo " + correo + " ya existe en la BD.\n");
                con.desconectar();
            }
        }
        catch (Exception e)
        {
            System.err.println(e);
        }
    }



    public void eliminarUsuario(String correo, Properties sql) throws SQLException
    {
        try
        {
            con.DBConnection();
            connection = con.getJdbcConnection();
            String query = sql.getProperty("borrarUsuario");
            PreparedStatement ps = connection.prepareStatement(query);

            status = ps.executeUpdate();
            con.desconectar();
        }
        catch (Exception e)
        {
            System.err.println("ERROR: " + e);
        }
    }



    public void actualizarUsuario(String correo, String nombre, LocalDate fecha_nacimiento, LocalDate fecha_inscripcion, String correo_aux, Properties sql) throws SQLException
    {
       con.DBConnection();
       connection = con.getJdbcConnection();

        try
        {
            //String query = "UPDATE usuario SET correo = '" + correo + "', nombre = '" + nombre + "', fecha_nacimiento = '" + fecha_nacimiento + "', fecha_inscripcion = '" + fecha_inscripcion + "'";
            String query = sql.getProperty("actualizarUsuario");
            PreparedStatement ps = connection.prepareStatement(query);
            java.sql.Date fecha_nacimiento_sql = Date.valueOf(fecha_nacimiento);
            java.sql.Date fecha_inscripcion_sql = Date.valueOf(fecha_inscripcion);

            ps.setString(1,  correo);
            ps.setString(2, nombre);
            ps.setDate(3, fecha_nacimiento_sql);
            ps.setDate(4, fecha_inscripcion_sql);

            status = ps.executeUpdate();
            con.desconectar();
            System.out.print("\n --- USUARIO ACTUALIZADO CON EXITO --- \n");
        }
        catch(Exception e)
        {
            System.out.println("ERROR: " + e);
        }
    }



    // FUNCIONALIDADES ENTORNO WEB
    
    public LocalDate obtenerFecha(String correo, Properties sql) throws SQLException
    {
        con.DBConnection();
        connection = con.getJdbcConnection();

        String query = sql.getProperty("obtenerfecha");
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);
        LocalDate fecha;

        usuario = new usuarioDTO();
        try
        {
            if (rs.next())
            {
                fecha = rs.getDate("fecha_inscripcion").toLocalDate();
                usuario.setFechaInscripcion(fecha);
            }
            st.close();
            con.desconectar();
        }
        catch (Exception e)
        {
            System.err.print("ERROR: " + e);
        }

        fecha = usuario.getFechaInscripcion();

        return fecha;
    }
    
    public usuarioDTO datosUsuarioPorEmail(String correo)
    {
    	usuarioDTO usuario = new usuarioDTO();
    	
    	try
    	{
    		con.DBConnection();
    		connection = con.getJdbcConnection();
    		PreparedStatement ps = connection.prepareStatement("SELECT * FROM usuario WHERE correo = ?");
    		
    		ps.setString(1, correo);
    		
    		ResultSet rs = ps.executeQuery();
    		
    		while(rs.next())
    		{
    			usuario.setNombre(rs.getString("nombre"));
    			usuario.setCorreo(rs.getString("correo"));
    			
    			LocalDate fecha_nacimiento_sql = (rs.getDate("fecha_nacimiento").toLocalDate());
    			usuario.setFechaNacimiento(fecha_nacimiento_sql);
    			rol Rol = rol.valueOf(rs.getString("rol"));
    			usuario.setRol(Rol);
    			LocalDate fecha_inscripcion_sql = (rs.getDate("fecha_inscripcion").toLocalDate());
    			usuario.setFechaInscripcion(fecha_inscripcion_sql);
    		}
    		con.desconectar();
    	}
    	catch(Exception e)
    	{
    		System.err.print(e);
    	}
    	
    	return usuario;
    }
    
    
    public int updatePassword(usuarioDTO usuario, String correo) throws IOException
    {
    	int status = 0;
    	
    	try
    	{
    		con.DBConnection();
    		connection = con.getJdbcConnection();
    		
    		PreparedStatement ps = connection.prepareStatement("UPDATE sesion_usuario SET password = ? WHERE correo = ?");
    		
    		ps.setString(1, usuario.getPassword());
    		ps.setString(2, correo);
    		
    		status = ps.executeUpdate();
    		System.out.print("CONTRASEÑA ACTUALIZADA");
    		con.desconectar();
    	}
    	catch(Exception e)
    	{
    		System.err.print(e);
    	}
    	return status;
    }
    
    
	public int updateDatos(usuarioDTO usuario, String correo_propietario) throws IOException {
		int status = 0;

		try 
		{
			con.DBConnection();
			connection = con.getJdbcConnection();
			
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("SELECT fecha_inscripcion FROM usuario WHERE correo = '" + correo_propietario + "'");
			PreparedStatement ps = connection.prepareStatement("UPDATE usuario SET nombre = ?, fecha_nacimiento = ?, fecha_inscripcion = ? WHERE correo = ?");
			
	
			while(rs.next())
			{
				usuario.setFechaInscripcion(rs.getDate("fecha_inscripcion").toLocalDate());
			}
			
			st.close();
			//java.sql.Date fecha_aux = Date.valueOf(fecha_nacimiento);

			ps.setString(1, usuario.getNombre());
			ps.setDate(2, Date.valueOf(usuario.getFechaNacimiento()));
			ps.setDate(3, Date.valueOf(usuario.getFechaInscripcion()));
			
			//ps.setDate(3, fecha_aux);
			ps.setString(4, correo_propietario);


			status = ps.executeUpdate();
			
			System.out.println("DATOS ACTUALIZADOS");
			con.desconectar();
			
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}
		return status;
	}

	
	public int comprobarRegistro(String correo) throws IOException 
	{
		int comprobacion = 5;

		try 
		{
			con.DBConnection();
			connection = con.getJdbcConnection();
			Statement statement2 = connection.createStatement();
			ResultSet resultset2 = statement2.executeQuery("select * from usuario where correo='" + correo + "'");

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
	
	public int save(usuarioDTO usuario) throws IOException {
		int status = 0;

		try {
			con.DBConnection();
			connection = con.getJdbcConnection();

			PreparedStatement ps = connection.prepareStatement("INSERT INTO sesion_usuario (correo,password) VALUES(?,?)");

			ps.setString(1, usuario.getCorreo());
			ps.setString(2, usuario.getPassword());

			status = ps.executeUpdate();
			con.desconectar();
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}

	/** Funci�n para guardar la informaci�n de un usuario*/

	public int save_info(usuarioDTO usuario) throws IOException {
		int status = 0;

		try {
			con.DBConnection();
			connection = con.getJdbcConnection();
			PreparedStatement ps = connection.prepareStatement("INSERT INTO usuario (correo,nombre,fecha_nacimiento, fecha_inscripcion,rol) VALUES(?,?,?,?,?)");

			ps.setString(1, usuario.getCorreo());
			ps.setString(2, usuario.getNombre());
			ps.setDate(3, Date.valueOf(usuario.getFechaNacimiento()));
			ps.setDate(4, Date.valueOf(usuario.getFechaInscripcion()));
			ps.setString(5, String.valueOf(usuario.getRol()));
			
			status = ps.executeUpdate();
			con.desconectar();
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}
}
