package es.uco.pw.business;

import es.uco.pw.dao.PistaDAO2;    
    
public class gestorPista2
{
	PistaDAO2 crearPista = new PistaDAO2();
    PistaDAO2 listaPistasMantenimiento = new PistaDAO2();
    PistaDAO2 listaPistasDisponibles = new PistaDAO2();
    PistaDAO2 asociarKartPista=new PistaDAO2();
	
    public gestorPista2()
    {
    	
    }
    
    public void crearPistaBBDD()
    {
        Scanner entrada = new Scanner(System.in);
        String nombre;
        String estado;
        dificultad Dificultad;
        int num_max;




        System.out.print("Introduce el nombre de la pista: ");
        nombre = entrada.nextLine();

        System.out.print("Introduce el estado de la pista (Disponible / Ocupada / En Mantenimiento): ");
        estado = entrada.nextLine();

        System.out.print("Introduce la dificultad de la pista (INFANTIL / FAMILIAR / ADULTOS): ");
        Dificultad = dificultad.valueOf(entrada.nextLine());

        System.out.print("Introduce el numero maximo de karts para la pista: ");
        num_max = entrada.nextInt();



        crearPista.crearPista(nombre, estado, Dificultad, num_max);
    }

    public String listarPistasMantenimientoBBDD()
    {
        String pista = "";

        ArrayList<PistaDTO> pistas = listaPistasMantenimiento.listarPistasMantenimiento();

        for(PistaDTO u: pistas)
        {
            pista = pista + u.toStringPista() + "\n";
        }

        return pista;
    }

    public String listarPistasDisponiblesBBDD()
    {
        String pista = "";

        ArrayList<PistaDTO> pistas = listaPistasDisponibles.listarPistasDisponibles();

        for(PistaDTO u: pistas)
        {
            pista = pista + u.toStringPista() + "\n";
        }

        return pista;
    }
}
