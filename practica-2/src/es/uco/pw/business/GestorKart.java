package es.uco.pw.business;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Properties;

import es.uco.pw.dao.KartDAO;

public class GestorKart {
	
	KartDAO crearKart = new KartDAO();
    KartDAO listarKartsDisponibles = new KartDAO();
	
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


        KartDTO kart=new KartDTO(id_kart, tipo, Estado);
        crearKart.crearKart(kart,config,sql);
    }

    public String listarKartsDisponiblesBBDD(Properties config, Properties sql)
    {
        String kart = "";

        ArrayList<KartDTO> karts = listarKartsDisponibles.listarKartsDisponibles(config,sql);

        for(KartDTO u: karts)
        {
            kart = kart + u.toStringKart() + "\n";
        }

        return kart;
    }
}
