package es.uco.pw.business;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Properties;

import es.uco.pw.dao.KartDAO;
import es.uco.pw.dao.entidadesDAO;

public class GestorKart {
	
	KartDAO crearKart = new KartDAO();
    KartDAO listarKartsDisponibles = new KartDAO();
    entidadesDAO asociarKartPista = new entidadesDAO();
	
	public void crearKartBBDD(Properties config, Properties sql)
    {
		Scanner entrada = new Scanner(System.in);
        int id_kart;
        String tipo;
        estado Estado;


        System.out.print("Introduce el ID del Kart: ");
        id_kart = entrada.nextInt();
        entrada.nextLine();

        System.out.print("Introduce el tipo de Kart (Niño / Adulto): ");
        tipo = entrada.nextLine();

        System.out.print("Introduce el estado del Kart (Disponible / Reservado / Mantenimiento): ");
        Estado = estado.valueOf(entrada.nextLine());



        crearKart.crearKart(id_kart, tipo, Estado);
    }

    public String listarKartsDisponiblesBBDD(Properties config, Properties sql)
    {
        String kart = "";

        ArrayList<KartDTO> karts = listarKartsDisponibles.listarKartsDisponibles();

        for(KartDTO u: karts)
        {
            kart = kart + u.toStringKart() + "\n";
        }

        return kart;
    }
    
    public void asociarKartPistaBBDD()
    {
        Scanner sc = new Scanner(System.in);
        int id_kart;
        String nombre_pista;

        System.out.print("Introduce el ID del Kart que desea seleccionar: ");
        id_kart = sc.nextInt();
        sc.nextLine();

        if(asociarKartPista.existeKart(id_kart) == true)
        {
            System.out.print("Introduce el nombre de la pista a la que se le desea asociar: ");
            nombre_pista = sc.nextLine();

            if(asociarKartPista.existePista(nombre_pista) == true)
            {
                asociarKartPista.listaKartPista(id_kart, nombre_pista);
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
