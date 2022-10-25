package es.uco.pw.connection;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;




public class DBConnection
{
    public Connection conex;
    Properties config_propiedades = new Properties();
    InputStream config = null;


   public DBConnection()
    {
        try
        {
            config = new FileInputStream("config.properties");
            config_propiedades.load(config);

            Class.forName(config_propiedades.getProperty("driver"));
            conex = DriverManager.getConnection("jdbc:mysql://" + config_propiedades.getProperty("nombre_servidor") + ":" + config_propiedades.getProperty("puerto") + "/i72cuped", config_propiedades.getProperty("usuario_bd"), config_propiedades.getProperty("password_bd"));
            System.out.println("La conexion a la base de datos se ha establecido con exito!");
        }
        catch(Exception e)
        {
            System.err.println("ERROR: " + e);
        }
    }

    public void closeConnection() throws SQLException {
        if(conex != null)
        {
            if(!conex.isClosed())
            {
                conex.close();
            }
        }
    }
}


