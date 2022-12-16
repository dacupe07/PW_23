package es.uco.pw.business.reserva;

import java.time.LocalDate;

public class reservaDTO
{
     /*
        ATRIBUTOS DE LA CLASE RESERVA
     */

    protected int id_reserva;
    protected float precio;
    protected int duracion;
    protected float descuento;
    protected String hora;
    protected LocalDate fecha;
    protected String usuario;

    protected String pista;

    // CONSTRUCTOR PARAMETRIZADO

    public reservaDTO(int id_reserva, float precio, int duracion, float descuento, String hora, LocalDate fecha, String usuario, String pista)
    {
        this.id_reserva = id_reserva;
        this.precio = precio;
        this.duracion = duracion;
        this.descuento = descuento;
        this.hora = hora;
        this.fecha = fecha;
        this.usuario = usuario;
        this.pista = pista;
    }

    // CONSTRUCTOR VACIO

    public reservaDTO()
    {

    }

    // GETS & SETS

    public String getHora()
    {
        return this.hora;
    }

    public void setHora(String hora)
    {
        this.hora = hora;
    }

    public String getUsuario()
    {
        return this.usuario;
    }

    public void setUsuario(String usuario)
    {
        this.usuario = usuario;
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

    public LocalDate getFecha()
    {
        return this.fecha;
    }

    public void setFecha(LocalDate fecha)
    {
        this.fecha = fecha;
    }

    // TO STRING

    public String toStringReserva()
    {
        String info = "\nID: " + this.id_reserva + ". Precio: " + this.precio + ". Duracion: " + this.duracion + ". Descuento: " + this.descuento + ". Hora de reserva: " + this.hora + ". Fecha de reserva: " + this.fecha + ". Usuario: " + this.usuario + ". Pista: " + this.pista;
        return info;
    }
}
