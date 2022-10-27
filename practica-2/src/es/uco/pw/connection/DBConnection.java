package es.uco.pw.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;




public class DBConnection
{
		
	protected Connection connection = null;
		/**
		 * Funcion que obtiene la conexion con la base de datos
		 * @param prop Fichero de propiedades
		 * @return Conexion con la base de datos
		 */

		public static Connection getConnection(Properties prop) {
			Connection con=null;
			try 
			{
				// Obtenemos el driver de mysql
				
				Class.forName(prop.getProperty("driver"));
			  
				// Obtenemos los datos de conexion con la base de datos
			  
				con= DriverManager.getConnection(prop.getProperty("url"),prop.getProperty("usuario"),prop.getProperty("contrasena"));
			  
			} catch(Exception e) {
				System.out.println("Los datos de conexion con la base de datos no son correctos o no se esta utilizando una direccion IP de la UCO");
			}
			
			return con;
		}

    public void closeConnection(Connection con) throws SQLException {
        if(con != null)
        {
            if(!con.isClosed())
            {
                con.close();
            }
        }
    }
}



