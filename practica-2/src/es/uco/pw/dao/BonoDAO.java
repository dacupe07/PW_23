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

import es.uco.pw.business.BonoDTO;
import es.uco.pw.connection.DBConnection;

public class BonoDAO {
	
	public BonoDAO() {
		
	}
	
	public boolean existeBono(int IDbono,Properties sql)
    {
        boolean bonoExist = false;
        try
        {	
        	DBConnection cn = new DBConnection();
            PreparedStatement ps=cn.conex.prepareStatement(sql.getProperty("existeBono"));
            ps.setInt(1, IDbono);
            ResultSet rs = ps.executeQuery();


            if(rs.next())
            {
                bonoExist = true;
            }
            else
            {
                bonoExist = false;
            }
            cn.closeConnection();
            ps.close();
        }
        catch(Exception e)
        {
            System.err.println("Error: " + e);
        }
        return bonoExist;
    }
	
	public int crearBono(BonoDTO bono,Properties sql) {
		
		int status=0;
		
		try {
		DBConnection cn = new DBConnection();
        PreparedStatement ps=cn.conex.prepareStatement(sql.getProperty("crearBono"));
        
        if(existeBono(bono.getId_bono(),sql)==false) {
        ps.setInt(1, bono.getId_bono());
        ps.setString(2, bono.getCorreo_usuario());
		ps.setInt(3, bono.getNum_sesiones());
		ps.setString(4, bono.getTipo_bono());
		ps.setDate(5, Date.valueOf(bono.getCaducidad()));
		ps.setDouble(6, bono.getDesc());
		
		status = ps.executeUpdate();
        cn.closeConnection();
        ps.close();

        	}
        else {
        	 System.out.println("\nEl bono con id " + bono.getId_bono() + " ya existe en la BD.\n");
             cn.closeConnection();
             ps.close();
             
        }

		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return status;
	}
	
	public ArrayList<BonoDTO> listarBonos(Properties sql)
    {
        ArrayList<BonoDTO> lista_bonos = new ArrayList<BonoDTO>();

        try
        {
        	DBConnection cn = new DBConnection();
            PreparedStatement ps=cn.conex.prepareStatement(sql.getProperty("listarBono"));
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
            	int Id_bono=rs.getInt("id_bono");
                String correo=rs.getString("correo_usuario");
        		int num_sesiones=rs.getInt("num_sesiones");
        		String tipo=rs.getString("tipo_bono");
        		LocalDate caducidad=rs.getDate("caducidad").toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        		double descuento=rs.getDouble("desc");
        		
                lista_bonos.add(new BonoDTO(Id_bono,correo,num_sesiones,tipo,caducidad,descuento));
            }

           cn.closeConnection();
           ps.close();
        }
        catch (Exception e)
        {
            System.err.println(e);

        }
        return lista_bonos;
    }
	
    public int eliminarUsuario(int id,Properties sql) throws SQLException
    {
    	int status=0;
        try
        {
        	DBConnection cn = new DBConnection();
            PreparedStatement ps=cn.conex.prepareStatement(sql.getProperty("borrarBono"));
            ps.setInt(1, id);
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
	
    public boolean tieneBono(String correo,Properties sql)
    {
    	boolean bonoExist=false;
        try
        {	
        	DBConnection cn = new DBConnection();
            PreparedStatement ps=cn.conex.prepareStatement(sql.getProperty("tieneBono"));
            ps.setString(1, correo);
            ResultSet rs = ps.executeQuery();


            if(rs.next())
            {
            	if(rs.getInt("num_sesiones")>0 && rs.getDate("caducidad").toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isAfter(LocalDate.now())) {
                bonoExist=true; }
            	else { 
            		bonoExist=false;
            	}
            }
            else
            {
            	bonoExist=false;
            }
            cn.closeConnection();
            ps.close();
        }
        catch(Exception e)
        {
            System.err.println("Error: " + e);
        }
        return bonoExist;
    }
}
