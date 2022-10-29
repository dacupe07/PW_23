package es.uco.pw.business;

import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

import es.uco.pw.dao.PistaDAO;

public class GestorPista {
	
	PistaDAO crearPista = new PistaDAO();
    PistaDAO listaPistasMantenimiento = new PistaDAO();
    PistaDAO listaPistasDisponibles = new PistaDAO();
    PistaDAO asociarKartPista=new PistaDAO();
	
    public GestorPista() {
    	
    }
    
	public void crearPistaBBDD(Properties config,Properties sql)
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
        

        PistaDTO pista=new PistaDTO(nombre, estado, Dificultad, num_max);
        crearPista.crearPista(pista,config,sql);
    }

    public String listarPistasMantenimientoBBDD(Properties config,Properties sql)
    {
        String pista = "";

        ArrayList<PistaDTO> pistas = listaPistasMantenimiento.listarPistasMantenimiento(config,sql);

        for(PistaDTO u: pistas)
        {
            pista = pista + u.toStringPista() + "\n";
        }

        return pista;
    }

    public String listarPistasDisponiblesBBDD(Properties config,Properties sql)
    {
        String pista = "";

        ArrayList<PistaDTO> pistas = listaPistasDisponibles.listarPistasDisponibles(config,sql);

        for(PistaDTO u: pistas)
        {
            pista = pista + u.toStringPista() + "\n";
        }

        return pista;
    }
    
    public void asociarKartPistaBBDD(Properties config,Properties sql)
    {
        Scanner sc = new Scanner(System.in);
        int id_kart;
        String nombre_pista;

        System.out.print("Introduce el ID del Kart que desea seleccionar: ");
        id_kart = sc.nextInt();
        sc.nextLine();
        
        if(asociarKartPista.existeKart(id_kart,config,sql) == true)
        {
            System.out.print("Introduce el nombre de la pista a la que se le desea asociar: ");
            nombre_pista = sc.nextLine();

            if(asociarKartPista.existePista(nombre_pista,config,sql) == true)
            {
                asociarKartPista.asociarKartPista(id_kart, nombre_pista,config,sql);
            }
            else
            {
                System.out.print("La pista introducida en la BBDD no existe");
            }
        }
        else
        {
            System.out.print("El kart introducido no existe en la BBDD");
        }

    }
}
