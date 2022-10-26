package es.uco.pw.business;

import java.util.Date;

public class entidadesDTO
{
    /*
        ATRIBUTOS DE LA CLASE USUARIO
     */

    protected String correo;
    protected String nombre;
    protected Date fecha_nacimiento;
    protected Date fecha_inscripcion;

    /*
        ATRIBUTOS DE LA CLASE KART
     */

    protected int id_kart;
    protected String tipo;
    protected estado Estado;

    /*
        ATRIBUTOS DE LA CLASE PISTA
     */

    protected String nom_pista;
    protected String est_pista;
    protected dificultad Dificultad;
    protected int num_max;

    /*
        ATRIBUTOS DE LA CLASE RESERVA
     */

    protected int id_reserva;
    protected float precio;
    protected int duracion;
    protected float descuento;
    protected String hora;
    protected Date fecha;
    protected String usuario;
    protected String pista;

    /*
        ATRIBUTOS DE LA CLASE BONO
     */

    protected int id_bono;
    protected String correo_usuario;
    protected int num_sesiones;
    protected String tipo_bono;
    protected Date caducidad;
    protected double desc;

    // CONSTRUCTOR CLASE USUARIO

    public entidadesDTO(String correo, String nombre, Date fecha_nacimiento, Date fecha_inscripcion)
    {
        this.correo = correo;
        this.nombre = nombre;
        this.fecha_inscripcion = fecha_inscripcion;
        this.fecha_nacimiento = fecha_nacimiento;
    }

    // CONSTRUCTOR CLASE KART

    public entidadesDTO(int id_kart, String tipo, estado Estado)
    {
        this.id_kart = id_kart;
        this.tipo = tipo;
        this.Estado = Estado;
    }

    // CONSTRUCTOR CLASE PISTA

    public entidadesDTO(String nom_pista, String est_pista, dificultad Dificultad, int num_max)
    {
        this.nom_pista = nom_pista;
        this.est_pista = est_pista;
        this.Dificultad = Dificultad;
        this.num_max = num_max;
    }

    //CONSTRUCTOR CLASE BONO

    public entidadesDTO(int id_bono, String correo_usuario, int num_sesiones, String tipo_bono, Date caducidad, double desc)
    {
        this.id_bono = id_bono;
        this.correo_usuario = correo_usuario;
        this.num_sesiones = num_sesiones;
        this.tipo_bono = tipo_bono;
        this.caducidad = caducidad;
        this.desc = desc;
    }

    // CONSTRUCTOR CLASE RESERVA

    public entidadesDTO(int id_reserva, float precio, int duracion, float descuento, String hora, Date fecha, String usuario, String nom_pista)
    {
        this.id_reserva = id_reserva;
        this.precio = precio;
        this.duracion = duracion;
        this.descuento = descuento;
        this.hora = hora;
        this.fecha = fecha;
        this.usuario = usuario;
        this.nom_pista = pista;
    }

    // CONSTRUCTOR VACIO

    public entidadesDTO()
    {

    }

    // GET'S Y SET'S

    // CLASE USUARIO

    public String getNombre()
    {
        return this.nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getCorreo()
    {
        return this.correo;
    }

    public void setCorreo(String correo)
    {
        this.correo = correo;
    }

    public Date getFechaNacimiento()
    {
        return this.fecha_nacimiento;
    }

    public void setFechaNacimiento(Date fecha_nacimiento)
    {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public Date getFechaInscripcion()
    {
        return this.fecha_inscripcion;
    }

    public void setFechaInscripcion(Date fecha_inscripcioh)
    {
        this.fecha_inscripcion = fecha_inscripcioh;
    }

    // CLASE PISTA

    public String getNomPista()
    {
        return this.nom_pista;
    }

    public void setNomPista(String nom_pista)
    {
        this.nom_pista = nom_pista;
    }

    public String getEstPista()
    {
        return this.est_pista;
    }

    public void setEstPista(String est_pista)
    {
        this.est_pista = est_pista;
    }

    public dificultad getDificultad()
    {
        return this.Dificultad;
    }

    public void setDificultad(dificultad Dificultad)
    {
        this.Dificultad = Dificultad;
    }

    public int getNumMax()
    {
        return this.num_max;
    }

    public void setNumMax(int num_max)
    {
        this.num_max = num_max;
    }

    // CLASE KART

    public int getIdKart()
    {
        return this.id_kart;
    }

    public void setIdKart(int id_kart)
    {
        this.id_kart = id_kart;
    }

    public String getTipo()
    {
        return this.tipo;
    }

    public void setTipo(String tipo)
    {
        this.tipo = tipo;
    }

    public estado getEstado(estado Estado)
    {
        return this.Estado;
    }

    public void setEstado()
    {
        this.Estado = Estado;
    }

    // CLASE BONO

    public int getIdBono()
    {
        return this.id_bono;
    }

    public void setIdBono(int id_bono)
    {
        this.id_bono = id_bono;
    }

    public String getUsuario()
    {
        return this.usuario;
    }

    public void setUsuario(String usuario)
    {
        this.usuario = usuario;
    }

    public int getNumSesiones()
    {
        return this.num_sesiones;
    }

    public void setNumSesiones(int num_sesiones)
    {
        this.num_sesiones = num_sesiones;
    }

    public String getTipoBono()
    {
        return this.tipo_bono;
    }

    public void setTipoBono(String tipo_bono)
    {
        this.tipo_bono = tipo_bono;
    }

    public Date getCaducidad()
    {
        return this.caducidad;
    }

    public void setCaducidad(Date caducidad)
    {
        this.caducidad = caducidad;
    }

    public double getDesc()
    {
        return this.desc;
    }

    public void setDesc(double desc)
    {
        this.desc = desc;
    }

    // CLASE RESERVA

    public String getHora()
    {
        return this.hora;
    }

    public void setHora(String hora)
    {
        this.hora = hora;
    }

    public String getCorreoUsuario()
    {
        return this.correo_usuario;
    }

    public void setCorreoUsuario(String correo_usuario)
    {
        this.correo_usuario = correo_usuario;
    }

    public String getPista()
    {
        return this.pista;
    }

    public void setPista(String pista)
    {
        this.pista = pista;
    }

    public int getIdReserva()
    {
        return this.id_reserva;
    }

    public void setIdReserva(int id_reserva)
    {
        this.id_reserva = id_reserva;
    }

    public float getPrecio()
    {
        return this.precio;
    }

    public void setPrecio(float precio)
    {
        this.precio = precio;
    }

    public int getDuracion()
    {
        return this.duracion;
    }

    public void setDuracion(int duracion)
    {
        this.duracion = duracion;
    }

    public float getDescuento()
    {
        return this.descuento;
    }

    public void setDescuento(float descuento)
    {
        this.descuento = descuento;
    }

    public Date getFecha()
    {
        return this.fecha;
    }

    public void setFecha(Date fecha)
    {
        this.fecha = fecha;
    }

    // TO STRING DE LAS DIFERENTES CLASES

    public String toStringUsuario()
    {
        String info = "\nNombre: " + this.nombre + ". Correo: " + this.correo + ". Fecha Nacimiento: " + this.fecha_nacimiento + ". Fecha Inscripcion: " + this.fecha_inscripcion;
        return info;
    }

    public String toStringPista()
    {
        String info = "\nNombre: " + this.nom_pista + ". Estado: " + this.est_pista + ". Dificultad: " + this.Dificultad + ". Numero maximo: " + this.num_max;
        return info;
    }

    public String toStringKart()
    {
        String info = "\nID: " + this.id_kart + ". Tipo: " + this.tipo + ". Estado: " + this.Estado;
        return info;
    }

    public String toStringBono()
    {
        String info = "\nID: " + this.id_bono + ". Usuario: " + this.usuario + ". Numero de Sesiones: " + this.num_sesiones + ". Tipo de bono: " + this.tipo_bono + ". Caducidad: " + this.caducidad + ". Descuento:" + this.desc;
        return info;
    }

    public String toStringReserva()
    {
        String info = "\nID: " + this.id_reserva + ". Precio: " + this.precio + ". Duracion: " + this.duracion + ". Descuento: " + this.descuento + ". Hora de reserva: " + this.hora + ". Fecha de reserva: " + this.fecha + ". Usuario: " + this.correo_usuario + ". Pista: " + this.pista;
        return info;
    }
}
