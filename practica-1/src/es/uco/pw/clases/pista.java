package es.uco.pw.clases;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.*;
import java.util.Properties;


public class pista 
{
	private String nombre;
	private String estado;
	private int num_max;
	private dificultad Dificultad;
	ArrayList<String> PistaKart = new ArrayList<String>();
	private static pista Pista = null;
	
	public pista()
	{
		
	}
	
	public static pista getPista(String nombre, String estado, dificultad Dificultad, int num_max)
	{
		if(Pista == null)
		{
			Pista = new pista(nombre, estado, Dificultad, num_max);
		}
		
		return Pista;
	}
	
	private pista(String nombre, String estado, dificultad Dificultad, int num_max)
	{
		this.nombre = nombre;
		this.estado = estado;
		this.Dificultad = Dificultad;
		this.num_max = num_max;
	}
	
	/**
	 * Get de la variable nombre
	 * 
	 * @return nombre
	 */	
	public String getNombre()
	{
		return nombre;
	}
	
	/**
	 * Set de la variable nombre
	 * 
	 * @param nombre
	 */
	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}
	
	/**
	 * Get de la variable estado
	 * 
	 * @return estado
	 */	
	public String getEstado()
	{
		return estado;
	}
	
	/**
	 * Set de la variable estado
	 * 
	 * @param estado
	 */
	public void setEstado(String estado)
	{
		this.estado = estado;
	}
	
	/**
	 * Get de la variable Dificultad
	 * 
	 * @return Dificultad
	 */	
	public dificultad getDificultad()
	{
		return Dificultad;
	}
	
	/**
	 * Set de la variable Dificultad
	 * 
	 * @param Dificultad
	 */
	public void setDificultad(dificultad Dificultad)
	{
		this.Dificultad = Dificultad;
	}
	
	/**
	 * Get de la variable num_max
	 * 
	 * @return num_max
	 */	
	public int getNumMax()
	{
		return num_max;
	}
	
	/**
	 * Set de la variable num_max
	 * 
	 * @param num_max
	 */
	public void setNumMax(int num_max)
	{
		this.num_max = num_max;
	}
	
	public String toString()
	{
		String info = "\n\n--- DATOS INTRODUCIDOS --- \nNombre: " + this.nombre + ". Estado: " + this.estado + ". Dificultad: " + this.Dificultad + ". Numero maximo: " + this.num_max + "\n\n";
		
		return info;
	}
	
	public ArrayList<String> consultarKartsDisponibles() throws IOException, FileNotFoundException
	{
		String linea;
		
		// ABRIMOS EL FICHERO DE LECTURA
		FileReader fichero = new FileReader("Karts.txt");
		BufferedReader breader = new BufferedReader(fichero);
		
		while((linea = breader.readLine()) != null)
		{
			String trimmedLine = linea.trim();
			
			// COMPROBAMOS SI EL KART ESTA DISPONIBLE
			if(trimmedLine.contains("DISPONIBLE"))
			{
				PistaKart.add(linea);
			}
		}
		System.out.print("\n");
		breader.close();

		for(String A: PistaKart)
		{
			System.out.println(A + "\n");
			System.out.print("\n");
		}
		System.out.println("\n");
		return PistaKart;
	}
	
	public void crearPista() throws IOException, FileNotFoundException
	{
		Scanner lector = new Scanner(System.in);
		Boolean controlador = true;
		int aux_disp;

		System.out.print("Introduce el nombre de la pista: ");
		nombre = lector.nextLine();
		setNombre(nombre);

		System.out.print("Introduce el estado de la pista (Disponible / False -> Ocupada): ");
		estado = lector.nextLine();

		if((estado == "Disponible") || (estado == "DISPONIBLE") || (estado == "disponible"))
		{
			setEstado("Disponible");
		}
		else if((estado == "Ocupada") || (estado == "OCUPADA") || (estado == "ocupada"))
		{
			setEstado("Ocupada");
		}

		System.out.print("Dificultad de la pista (1. Infantil / 2. Familiar / 3. Adultos): ");
		aux_disp = lector.nextInt();

		if(aux_disp >= 1 && aux_disp <=3)
		{
			if(aux_disp == 1)
			{
				setDificultad(Dificultad.INFANTIL);
			}
			else if(aux_disp == 2)
			{
				setDificultad(Dificultad.FAMILIAR);
			}
			else if(aux_disp == 3)
			{
				setDificultad(Dificultad.ADULTOS);
			}
		}
		else
		{
			System.out.print("\n\nLa dificultad insertada no es valida.\n\n");
		}

		System.out.print("Numero maximo de karts: ");
		num_max = lector.nextInt();
		setNumMax(num_max);

		try
		{
			// CREAMOS EL FICHERO EN MODO ESCRITURA
			File fichero = new File("Pistas.txt");
			FileWriter fw = new FileWriter(fichero, true);
			BufferedReader reader = new BufferedReader(new FileReader(fichero));

			String lineactual; // Variable con la que controlamos la linea que se esta leyendo

			// REALIZAMOS EL CONTROL PARA QUE NO SALGA DEL FICHERO
			while((lineactual = reader.readLine()) != null || controlador == false)
			{
				String trimmedLine = lineactual.trim();

				String id_aux = nombre;

				// COMPROBAMOS QUE LA LINEA TENGA EL NOMBRE DE LA PISTA, PARA EVITAR REPETICIONES
				if(trimmedLine.contains(id_aux))
				{
					System.out.print("\nLa pista introducida ya esta registrada. \n");
					controlador = false;
				}
			}

			if(controlador == true)
			{
				// ESCRIBIMOS EN EL FICHERO LOS DATOS INTRODUCIDOS
				fw.write("Nombre: " + getNombre() + ". Estado: " + getEstado() + ". Dificultad: " + getDificultad() + ". Num_Max: " + getNumMax() + "\n");
			}

			// CERRAMOS EL FICHERO
			fw.close();
			reader.close();
		}
		catch(Exception e)
		{
			System.out.print("\nERROR AL ESCRIBIR EN EL FICHERO " + e.getMessage() + "\n");
		}
	}
	
	public ArrayList<String> pistasMantenimiento() throws IOException, FileNotFoundException
	{
		String linea;
		
		// ABRIMOS EL FICHERO DE LECTURA
		FileReader fichero = new FileReader("Pistas.txt");
		BufferedReader breader = new BufferedReader(fichero);
		
		while((linea = breader.readLine()) != null)
		{
			String trimmedLine = linea.trim();
			
			// COMPROBAMOS SI LA PISTA ESTA EN MANTENIMIENTO
			if(trimmedLine.contains("Ocupada"))
			{
				PistaKart.add(linea);
			}
		}
		System.out.print("\n");
		breader.close();

		for(String A: PistaKart)
		{
			System.out.println(A + "\n");
			System.out.print("\n");
		}
		System.out.println("\n");
		return PistaKart;
	}

	public ArrayList<String> pistasDisponibles() throws IOException, FileNotFoundException
	{
		String linea;

		// ABRIMOS EL FICHERO DE LECTURA
		FileReader fichero = new FileReader("Pistas.txt");
		BufferedReader breader = new BufferedReader(fichero);

		while((linea = breader.readLine()) != null)
		{
			String trimmedLine = linea.trim();

			// COMPROBAMOS SI LA PISTA ESTA LIBRE
			if(!trimmedLine.contains("Ocupada"))
			{
				PistaKart.add(linea);
			}
		}
		System.out.print("\n");
		breader.close();

		for(String A: PistaKart)
		{
			System.out.println(A + "\n");
			System.out.print("\n");
		}
		System.out.println("\n");

		return PistaKart;
	}
	
	/*
	public ArrayList<String> pistasDisponibles() throws IOException, FileNotFoundException
	{
		Scanner lector = new Scanner(System.in);
		String linea;
		int num_aux;
		String tipo;
		Properties prop = new Properties();
		
		
		System.out.println("Introduce el numero de karts deseados: ");
		num_aux = lector.nextInt();
		
		System.out.println("Introduce el tipo de pista (INFANTIL / FAMILIAR / ADULTOS): ");
		tipo = lector.nextLine();
		
		// ABRIMOS EL FICHERO DE LECTURA
		FileReader fichero = new FileReader("Pistas.txt");
		BufferedReader breader = new BufferedReader(fichero);
		
		prop.load(breader);
		int num = prop.getProperty("Num_Max");
		
		
		while((linea = breader.readLine()) != null)
		{
			String trimmedLine = linea.trim();
			
			// COMPROBAMOS SI LA PISTA TIENE KARTS SUFICIENTES Y EL TIPO DE PISTA
			if(trimmedLine.contains(tipo) && (num_aux))
			{
				PistaKart.add(linea);
			}
		}
		System.out.print("\n");
		breader.close();
		return PistaKart;
	}
	*/

	
	public void asociarKartAPista() throws IOException
	{
		Scanner lector = new Scanner(System.in);
		Boolean controlador = true;
		String tipo;
		String linea;

		System.out.println("Introduce el tipo de kart que desea introducir (Niños / Adultos): ");
		tipo = lector.nextLine();

		FileReader fichero = new FileReader("Pistas.txt");
		BufferedReader breader = new BufferedReader(fichero);

		if(tipo.contains("Niños")) {
			while ((linea = breader.readLine()) != null) {
				String trimmedLine = linea.trim();
				// COMPROBAMOS SI LA PISTA ESTA EN MANTENIMIENTO
				if ((trimmedLine.contains("INF")) || (trimmedLine.contains("FAM"))) {
					PistaKart.add(linea);
				}

				System.out.println("\n\nLas pistas aptas para este tipo de Kart son: \n\n");

				for(String A: PistaKart)
				{
					System.out.println(A + "\n");
					System.out.print("\n");
				}

				System.out.println("\n\nIntroduce el nombre de la pista en la cual quieras introducir el Kart: ");
				nombre = lector.nextLine();

				while((linea = breader.readLine()) != null)
				{
					trimmedLine = linea.trim();

				}

			}
		} else if (tipo == "Adultos") {
			while ((linea = breader.readLine()) != null) {
				String trimmedLine = linea.trim();

				// COMPROBAMOS SI LA PISTA ESTA EN MANTENIMIENTO
				if (trimmedLine.contains("ADULTOS") || trimmedLine.contains("FAMILIAR")) {
					PistaKart.add(linea);
				}
			}
		}
		System.out.print("\n");
		breader.close();


	}

	public Boolean pistaCumpleCondiciones(String pista, String especialidad, String fecha, String hora) throws IOException, FileNotFoundException
	{
		ArrayList<String> pistasDisponibles = Pista.pistasDisponibles();
		
		for(String aux: pistasDisponibles ) {
			if(aux.contains(pista) && aux.contains(especialidad)) {
				System.out.println();
				if(aux.contains(fecha) && aux.contains(hora)) {
					System.out.println("La pista no esta disponible a la hora y fecha requerida.");
					return false;
				}
				else {
					System.out.println("La pista introducida está disponible para el dia y la fecha seleccionada y, además, cumple las condiciones de especialidad de la pista.");
					return true;
				}
				
			}
			
		}
		
		return false;
	}
}
