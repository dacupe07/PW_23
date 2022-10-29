package es.uco.pw.business;


public class PistaDTO {
	
	protected String nom_pista;
    protected String est_pista;
    protected dificultad Dificultad;
    protected int num_max;
	
    public PistaDTO() {
    	
    }
	public PistaDTO(String nom_pista, String est_pista, dificultad Dificultad, int num_max)
    {
        this.nom_pista = nom_pista;
        this.est_pista = est_pista;
        this.Dificultad = Dificultad;
        this.num_max = num_max;
    }
	
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
    
    public String toStringPista()
    {
        String info = "\nNombre: " + this.nom_pista + ". Estado: " + this.est_pista + ". Dificultad: " + this.Dificultad + ". Numero maximo: " + this.num_max;
        return info;
    }
}
