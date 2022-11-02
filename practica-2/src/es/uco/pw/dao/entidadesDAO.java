package es.uco.pw.dao;

import es.uco.pw.business.dificultad;
import es.uco.pw.business.entidadesDTO;
import es.uco.pw.business.estado;
import es.uco.pw.connection.DBConnection;

import java.sql.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class entidadesDAO
{
    private static entidadesDAO instance = null;
    private entidadesDTO entidades;
    int status = 0;


    // FUNCIONES CLASE USUARIO

    public boolean existeUsuario(String correo)
    {
        boolean userExist = false;
        try
        {
            DBConnection cn = new DBConnection();
            String query = "SELECT nombre FROM usuario WHERE correo = '" + correo + "'";

            Statement st = cn.conex.createStatement();
            ResultSet rs = st.executeQuery(query);


            if(rs.next())
            {
                userExist = true;
            }
            else
            {
                userExist = false;
            }
            cn.conex.close();
            st.close();
        }
        catch(Exception e)
        {
            System.err.println("Error: " + e);
        }
        return userExist;
    }


    public ArrayList<entidadesDTO> listarUsuariosRegistrados()
    {
        ArrayList<entidadesDTO> lista_usuarios = new ArrayList<entidadesDTO>();

        try
        {
            DBConnection cn = new DBConnection();

            String query = "SELECT * FROM usuario";

            Statement st = cn.conex.createStatement();
            ResultSet rs = st.executeQuery(query);

            while(rs.next())
            {
                String correo = rs.getString("correo");
                String nombre = rs.getString("nombre");
                LocalDate fecha_nacimiento = rs.getDate("fecha_nacimiento").toLocalDate();
                LocalDate fecha_inscripcion = rs.getDate("fecha_inscripcion").toLocalDate();
                lista_usuarios.add(new entidadesDTO(correo, nombre, fecha_nacimiento, fecha_inscripcion));
            }

           cn.conex.close();
            st.close();
        }
        catch (Exception e)
        {
            System.err.println(e);

        }
        return lista_usuarios;
    }

    public void registrarUsuario(String correo, String nombre, LocalDate fecha_nacimiento, LocalDate fecha_inscripcion) throws SQLException
    {
        try
        {
            DBConnection cn = new DBConnection();
            String query = "INSERT INTO usuario (correo, nombre, fecha_nacimiento, fecha_inscripcion) VALUES(?,?,?,?)";
            PreparedStatement ps = cn.conex.prepareStatement(query);
            java.sql.Date fecha_nacimiento_sql = java.sql.Date.valueOf(fecha_nacimiento);
            java.sql.Date fecha_inscripcion_sql = java.sql.Date.valueOf(fecha_inscripcion);

            if (existeUsuario(correo) == false)
            {
                ps.setString(1, correo);
                ps.setString(2, nombre);
                ps.setDate(3, fecha_nacimiento_sql);
                ps.setDate(4, fecha_inscripcion_sql);

                System.out.println("USUARIO REGISTRADO CON EXITO");
                status = ps.executeUpdate();
                cn.conex.close();
            }
            else
            {
                System.out.println("\nEl usuario con correo " + correo + " ya existe en la BD.\n");
                cn.conex.close();
            }
        }
        catch (Exception e)
        {
            System.err.println(e);
        }
    }

    public void eliminarUsuario(String correo) throws SQLException
    {
        try
        {
            DBConnection cn = new DBConnection();
            String query = "DELETE FROM usuario WHERE correo = '" + correo + "'";
            PreparedStatement ps = cn.conex.prepareStatement(query);

            status = ps.executeUpdate();
            cn.conex.close();
        }
        catch (Exception e)
        {
            System.err.println("ERROR: " + e);
        }
    }

    public void actualizarUsuario(String correo, String nombre, LocalDate fecha_nacimiento, LocalDate fecha_inscripcion, String correo_aux)
    {
        DBConnection cn = new DBConnection();

        try
        {
            //String query = "UPDATE usuario SET correo = '" + correo + "', nombre = '" + nombre + "', fecha_nacimiento = '" + fecha_nacimiento + "', fecha_inscripcion = '" + fecha_inscripcion + "'";
            String query = "UPDATE usuario SET correo = ?, nombre = ?, fecha_nacimiento = ?, fecha_inscripcion = ? WHERE correo = '" + correo_aux + "'";
            PreparedStatement ps = cn.conex.prepareStatement(query);
            java.sql.Date fecha_nacimiento_sql = Date.valueOf(fecha_nacimiento);
            java.sql.Date fecha_inscripcion_sql = Date.valueOf(fecha_inscripcion);

            ps.setString(1,  correo);
            ps.setString(2, nombre);
            ps.setDate(3, fecha_nacimiento_sql);
            ps.setDate(4, fecha_inscripcion_sql);

            status = ps.executeUpdate();
            cn.conex.close();
            System.out.print("\n --- USUARIO ACTUALIZADO CON EXITO --- \n");
        }
        catch(Exception e)
        {
            System.out.println("ERROR: " + e);
        }
    }

    public LocalDate obtenerFecha(String correo) throws SQLException
    {
        DBConnection cn = new DBConnection();

        String query = "SELECT fecha_inscripcion FROM usuario WHERE correo = '" + correo + "'";
        Statement st = cn.conex.createStatement();
        ResultSet rs = st.executeQuery(query);
        LocalDate fecha;

        entidades = new entidadesDTO();
        try
        {
            if (rs.next())
            {
                fecha = rs.getDate("fecha_inscripcion").toLocalDate();
                entidades.setFechaInscripcion(fecha);
            }
            st.close();
            cn.conex.close();
        }
        catch (Exception e)
        {
            System.err.print("ERROR: " + e);
        }

        fecha = entidades.getFechaInscripcion();

        return fecha;
    }


    // FUNCIONES CLASE PISTA

    public void crearPista(String nombre, String estado, dificultad Dificultad, int num_max)
    {
        DBConnection cn = new DBConnection();
        try
        {
            String query = "INSERT INTO pista (nombre, estado, dificultad, num_max) VALUES (?, ?, ?, ?)";
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


    public ArrayList<entidadesDTO> listarPistasMantenimiento()
    {
        ArrayList<entidadesDTO> lista_pistas = new ArrayList<entidadesDTO>();

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
                lista_pistas.add(new entidadesDTO(nombre, estado, Dificultad, num_max));
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


    public ArrayList<entidadesDTO> listarPistasDisponibles()
    {
        ArrayList<entidadesDTO> lista_pistas = new ArrayList<entidadesDTO>();

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
                lista_pistas.add(new entidadesDTO(nombre, estado, Dificultad, num_max));
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

    // FUNCIONES CLASE KART

    public void crearKart(int id_kart, String tipo, estado Estado)
    {
        DBConnection cn = new DBConnection();
        try
        {
            String query = "INSERT INTO kart (id_kart, tipo, estado) VALUES (?, ?, ?)";
            PreparedStatement ps = cn.conex.prepareStatement(query);

            ps.setInt(1, id_kart);
            ps.setString(2, tipo);
            ps.setString(3, String.valueOf(Estado));

            ps.executeUpdate();
            cn.conex.close();
        }
        catch(Exception e)
        {
            System.out.print("ERROR: "+ e);
        }
    }

    public ArrayList<entidadesDTO> listarKartsDisponibles()
    {
        ArrayList<entidadesDTO> lista_karts = new ArrayList<entidadesDTO>();

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
                lista_karts.add(new entidadesDTO(id_kart, tipo, Estado));
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

            entidades = new entidadesDTO();

            while(rs_pista.next())
            {
                Dificultad = dificultad.valueOf(rs_pista.getString("dificultad"));
                entidades.setDificultad(Dificultad);
            }

            st.close();

            Statement st1 = cn.conex.createStatement();
            ResultSet rs_kart = st1.executeQuery(query_kart);

            while(rs_kart.next())
            {
                System.out.print("C");
                tipo_kart = rs_kart.getString("tipo");
                entidades.setTipo(tipo_kart);
            }

            st1.close();

            PreparedStatement ps = cn.conex.prepareStatement(query_pista_kart);
            dif = String.valueOf(entidades.getDificultad());
            tipo = entidades.getTipo();

            if(dif.equals("infantil") && tipo.equals("Niño"))
            {
                System.out.print("E");
                ps.setInt(1, id_kart);
                ps.setString(2, nombre_pista);
                ps.setString(3, String.valueOf(entidades.getDificultad()));
                ps.setString(4, entidades.getTipo());

                status = ps.executeUpdate();
            }

            if(dif.equals("adultos") && tipo.equals("Adulto"))
            {
                System.out.print("F");
                ps.setInt(1, id_kart);
                ps.setString(2, nombre_pista);
                ps.setString(3, String.valueOf(entidades.getDificultad()));
                ps.setString(4, entidades.getTipo());

                status = ps.executeUpdate();
            }

            if(dif.equals("familiar") && tipo.equals("Niño"))
            {
                System.out.print("g");
                ps.setInt(1, id_kart);
                ps.setString(2, nombre_pista);
                ps.setString(3, String.valueOf(entidades.getDificultad()));
                ps.setString(4, entidades.getTipo());

                status = ps.executeUpdate();
            }

            if(dif.equals("familiar") && tipo.equals("Adulto"))
            {
                System.out.print("g");
                ps.setInt(1, id_kart);
                ps.setString(2, nombre_pista);
                ps.setString(3, String.valueOf(entidades.getDificultad()));
                ps.setString(4, entidades.getTipo());

                status = ps.executeUpdate();
            }

            cn.conex.close();
        }
        catch (Exception e)
        {
            System.err.println(e);

        }
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

    // FUNCIONES CLASE RESERVA

    public boolean existeReserva(int idReserva)
    {
        boolean resertExist = false;
        try
        {
            DBConnection cn = new DBConnection();
            String query = "SELECT * FROM reserva WHERE idReserva = " + idReserva + "";

            Statement st = cn.conex.createStatement();
            ResultSet rs = st.executeQuery(query);


            if(rs.next())
            {
                resertExist = true;
            }
            else
            {
                resertExist = false;
            }
            cn.conex.close();
            st.close();
        }
        catch(Exception e)
        {
            System.err.println("Error: " + e);
        }
        return resertExist;
    }


    public boolean comprobarAntiguedad(String correo) throws SQLException
    {
        DBConnection cn = new DBConnection();

        boolean control = false;

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String query = "SELECT * FROM usuario WHERE correo = '" + correo + "'";

        Statement st = cn.conex.createStatement();
        ResultSet rs = st.executeQuery(query);
        String fecha_aux;
        LocalDate fechaInscripcion;


        entidades = new entidadesDTO();
        try
        {
            while (rs.next())
            {
                fecha_aux = rs.getString("fecha_inscripcion");
                fechaInscripcion = LocalDate.parse(fecha_aux);
                entidades.setFechaInscripcion(fechaInscripcion);
            }

            LocalDate ahora = LocalDate.now();

            fechaInscripcion = entidades.getFechaInscripcion();

            Period periodo = Period.between(fechaInscripcion, ahora);
            int años = periodo.getYears();

            if (años >= 2)
            {
                control = true;
            }
            else
            {
                control = false;
            }

            st.close();
            cn.conex.close();
        }
        catch(Exception e)
        {
            System.err.print("ERROR: " + e);
        }

        return control;
    }

    public void reservaIndividual(String correo, String nom_pista, int idRes, LocalDate fecha, int duracion, String hora, float precio, float descuento) throws SQLException
    {
        DBConnection cn = new DBConnection();

        String query = "INSERT INTO reserva (idReserva, precio, duracion, descuento, hora, fecha, usuario, pista) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement ps = cn.conex.prepareStatement(query);

        try
        {
            ps.setInt(1, idRes);
            ps.setFloat(2, precio);
            ps.setInt(3, duracion);
            ps.setFloat(4, descuento);
            ps.setString(5, hora);
            ps.setDate(6, java.sql.Date.valueOf(fecha));
            ps.setString(7, correo);
            ps.setString(8, nom_pista);

            ps.executeUpdate();

            cn.conex.close();
        }
        catch(Exception e)
        {
            System.err.print("ERROR: " + e);
        }
    }

    public ArrayList<entidadesDTO> listarReservas(LocalDate fecha, String pista)
    {
        ArrayList<entidadesDTO> lista_reservas = new ArrayList<entidadesDTO>();

        try
        {
            DBConnection cn = new DBConnection();

            String query = "SELECT * FROM reserva WHERE fecha = '" + fecha + "' AND pista = '" + pista + "'";

            Statement st = cn.conex.createStatement();
            ResultSet rs = st.executeQuery(query);

            while(rs.next())
            {
                int idReserva = rs.getInt("idReserva");
                float precio = rs.getFloat("precio");
                int duracion = rs.getInt("duracion");
                float descuento = rs.getFloat("descuento");
                String hora = rs.getString("hora");
                String usuario = rs.getString("usuario");
                lista_reservas.add(new entidadesDTO(idReserva, precio, duracion, descuento, hora, fecha, usuario, pista));
            }

            cn.conex.close();
            st.close();
        }
        catch (Exception e)
        {
            System.err.println(e);

        }
        return lista_reservas;
    }


    public ArrayList<entidadesDTO> listarReservasFuturas(LocalDate fecha, String pista)
    {
        ArrayList<entidadesDTO> lista_reservas = new ArrayList<entidadesDTO>();

        try
        {
            DBConnection cn = new DBConnection();

            String query = "SELECT * FROM reserva WHERE fecha >= '" + fecha + "' AND pista = '" + pista + "'";

            Statement st = cn.conex.createStatement();
            ResultSet rs = st.executeQuery(query);

            while(rs.next())
            {
                int idReserva = rs.getInt("idReserva");
                float precio = rs.getFloat("precio");
                int duracion = rs.getInt("duracion");
                float descuento = rs.getFloat("descuento");
                String hora = rs.getString("hora");
                String usuario = rs.getString("usuario");
                lista_reservas.add(new entidadesDTO(idReserva, precio, duracion, descuento, hora, fecha, usuario, pista));
            }

            cn.conex.close();
            st.close();
        }
        catch (Exception e)
        {
            System.err.println(e);

        }
        return lista_reservas;
    }

    public void cancelarReserva(int idRes) throws SQLException
    {
        try
        {
            DBConnection cn = new DBConnection();
            String query = "DELETE FROM reserva WHERE idReserva = " + idRes + "";
            PreparedStatement ps = cn.conex.prepareStatement(query);

            status = ps.executeUpdate();
            cn.conex.close();
        }
        catch (Exception e)
        {
            System.err.println("ERROR: " + e);
        }
    }

    public void actualizarReserva(String correo, String nom_pista, int idRes, LocalDate fecha, int duracion, String hora, float precio, float descuento)
    {
        DBConnection cn = new DBConnection();

        try
        {
            String query = "UPDATE reserva SET idReserva = ?, precio = ?, duracion = ?, descuento = ?, hora = ?, fecha = ?, usuario = ?, pista = ? WHERE idReserva = " + idRes + "";
            PreparedStatement ps = cn.conex.prepareStatement(query);

            ps.setInt(1, idRes);
            ps.setFloat(2, precio);
            ps.setInt(3, duracion);
            ps.setFloat(4, descuento);
            ps.setString(5, hora);
            ps.setString(6, String.valueOf(fecha));
            ps.setString(7, correo);
            ps.setString(8, nom_pista);

            status = ps.executeUpdate();
            cn.conex.close();
            System.out.print("\n --- RESERVA ACTUALIZADA CON EXITO --- \n");
        }
        catch(Exception e)
        {
            System.out.println("ERROR: " + e);
        }
    }

}
