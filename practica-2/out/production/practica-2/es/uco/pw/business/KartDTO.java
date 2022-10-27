package es.uco.pw.business;

public class KartDTO {
	
	protected int id_kart;
    protected String tipo;
    protected estado Estado;
    
    public KartDTO() {
    	
    }
    
    public KartDTO(int id_kart, String tipo, estado Estado)
    {
        this.id_kart = id_kart;
        this.tipo = tipo;
        this.Estado = Estado;
    }
    
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

    public estado getEstado()
    {
        return this.Estado;
    }

    public void setEstado(estado Estado)
    {
        this.Estado = Estado;
    }
}
