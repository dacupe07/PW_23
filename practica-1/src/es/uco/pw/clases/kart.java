package es.uco.pw.clases;

import java.util.Scanner;
import java.io.*;


public class kart
{
	
	private int idKart;
	private String tipoKart;
	private disponibilidad Disponibilidad;
	
	
	private static kart Kart = null;
	
	public kart()
	{
		
	}
	
	public static kart getKart(int idKart, String tipoKart, disponibilidad Disponibilidad)
	{
		if(Kart == null)
		{
			Kart = new kart(idKart, tipoKart, Disponibilidad);
		}
		return Kart;
	}
	
	private kart(int idKart, String tipoKart, disponibilidad Disponibilidad)
	{
		this.idKart = idKart;
		this.tipoKart = tipoKart;
		this.Disponibilidad = Disponibilidad;
	}
	
	// DECLARACION DE GET Y SET

	/**
	 * Get de la variable ID
	 * 
	 * @return id
	 */	
	public int getKartID()
	{
		return idKart;
	}

	/**
	 * Set de la variable idKart
	 * 
	 * @param idKart
	 */
	public void setKartID(int idKart)
	{
		this.idKart = idKart;
	}
	
	/**
	 * Get de la variable Tipo
	 * 
	 * @return tipo
	 */	
	public String getKartTipo()
	{
		return tipoKart;
	}

	/**
	 * Set de la variable tipoKart
	 * 
	 * @param tipoKart
	 */
	public void setKartTipo(String tipoKart)
	{
		this.tipoKart = tipoKart;
	}
	
	/**
	 * Get de la variable Disponibilidad
	 * 
	 * @return Disponibilidad
	 */	
	public disponibilidad getDisponibilidad()
	{
		return this.Disponibilidad;
	}

	/**
	 * Set de la variable Disponibilidad
	 * 
	 * @param Disponibilidad
	 */
	public void setDisponibilidad(disponibilidad Disponibilidad)
	{
		this.Disponibilidad = Disponibilidad;
	}
	
	public String toString()
	{
		String info = "\n--- INFORMACION INTRODUCIDA --- \nID: " + this.idKart + ". Tipo: " + this.tipoKart + ". Disponibilidad: " + this.Disponibilidad + "\n\n";
		
		return info;
	}
	
	public void crearKart() throws IOException, FileNotFoundException
	{
		Scanner lector = new Scanner(System.in);
		Boolean controlador = true;
		int aux_disp;
		
		System.out.print("Introduce el identificar del kart: ");
		idKart = lector.nextInt();
		setKartID(idKart);
		
		System.out.print("Introduce el tipo de kart (Niños / Adultos): ");
		tipoKart = lector.nextLine();
		setKartTipo(tipoKart);
		
		System.out.print("Estado del Kart (1. Disponible / 2. Reservado / 3. Mantenimiento): ");
		aux_disp = lector.nextInt();
		
		if(aux_disp >= 1 && aux_disp <=3)
		{
			if(aux_disp == 1)
			{
				setDisponibilidad(Disponibilidad.DISPONIBLE);
			}
			else if(aux_disp == 2)
			{
				setDisponibilidad(Disponibilidad.RESERVADO);
			}
			else if(aux_disp == 3)
			{
				setDisponibilidad(Disponibilidad.MANTENIMIENTO);
			}
		}
		else
		{
			System.out.print("\n\nLa disponibilidad insertada no es valida.\n\n");
		}
		
		try
		{
			// CREAMOS EL FICHERO EN MODO ESCRITURA
			File fichero = new File("Karts.txt");
			FileWriter fw = new FileWriter(fichero, true);
			BufferedReader reader = new BufferedReader(new FileReader(fichero));

			String lineactual; // Variable con la que controlamos la linea que se esta leyendo
			
			// REALIZAMOS EL CONTROL PARA QUE NO SALGA DEL FICHERO
			while((lineactual = reader.readLine()) != null || controlador == false)
			{
				String trimmedLine = lineactual.trim();
				
				String id_aux = String.valueOf(idKart);
				
				// COMPROBAMOS QUE LA LINEA TENGA EL ID DEL KART INTRODUCIDO, PARA EVITAR REPETICIONES
				if(trimmedLine.contains(id_aux))
				{
					System.out.print("\nEl Kart introducido ya esta registrado. \n");
					controlador = false;
				}
			}
			
			if(controlador == true)
			{
				// ESCRIBIMOS EN EL FICHERO LOS DATOS INTRODUCIDOS
				fw.write("ID: " + getKartID() + ". Tipo: " + getKartTipo() + ". Disponibilidad: " + getDisponibilidad() + "\n");
			}
			
			// CERRAMOS EL FICHERO
			fw.close();
			reader.close();
		} 
		catch(Exception e)
		{
			System.out.print("\nERROR AL ESCRIBIR EN EL FICHERO" + e.getMessage() + "\n");
		}
	}
	
	
}

