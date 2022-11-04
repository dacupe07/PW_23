package es.uco.pw.business.kart;

import es.uco.pw.dao.kartDAO;
import es.uco.pw.dao.pistaDAO;

import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

public class gestorKart
{
    Properties sql;

    kartDAO kart = new kartDAO(sql);

    pistaDAO pista = new pistaDAO(sql);

    public gestorKart(Properties sql)
    {
        this.sql = sql;
        kartDAO  kart = new kartDAO(this.sql);
    }

    // FUNCIONES

    /**Funcion gestora para crear un kart
     * @param sql Properties con todas las sentencias sql
     */
    public void crearKartBBDD(Properties sql)
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



        kart.crearKart(id_kart, tipo, Estado, sql);
    }

    /**Funcion gestora para listar los karts
     * @param sql Properties con todas las sentencias sql
     * @return kart_list String con toda la informacion de los karts disponibles
     */
    public String listarKartsDisponiblesBBDD(Properties sql)
    {
        String kart_list = "";

        ArrayList<kartDTO> karts = kart.listarKartsDisponibles(sql);

        for(kartDTO u: karts)
        {
            kart_list = kart_list + u.toStringKart() + "\n";
        }

        return kart_list;
    }

    /**Funcion gestora para asociar un kart a una pista
     * @param sql Properties con todas las sentencias sql
     */
    public void asociarKartPistaBBDD(Properties sql)
    {
        Scanner sc = new Scanner(System.in);
        int id_kart;
        String nombre_pista;

        System.out.print("Introduce el ID del Kart que desea seleccionar: ");
        id_kart = sc.nextInt();
        sc.nextLine();

        if(kart.existeKart(id_kart, sql) == true)
        {
            System.out.print("Introduce el nombre de la pista a la que se le desea asociar: ");
            nombre_pista = sc.nextLine();

            if(pista.existePista(nombre_pista, sql) == true)
            {
                kart.listaKartPista(id_kart, nombre_pista, sql);
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
