package es.uco.pw.clases;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.*;

public class pista 
{
	private String nombre;
	private boolean estado;
	private int num_max;
	private dificultad Dificultad;
	ArrayList<String> PistaKart = new ArrayList<String>();
	private static pista Pista = null;
	
	public pista()
	{
		
	}
	
	public static pista getPista(String nombre, boolean estado, dificultad Dificultad, int num_max)
	{
		if(Pista == null)
		{
			Pista = new pista(nombre, estado, Dificultad, num_max);
		}
		
		return Pista;
	}
	
	private pista(String nombre, boolean estado, dificultad Dificultad, int num_max)
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
	public boolean getEstado()
	{
		return estado;
	}
	
	/**
	 * Set de la variable estado
	 * 
	 * @param estado
	 */
	public void setEstado(boolean estado)
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
		String info = "Nombre: " + this.nombre + ". Estado: " + this.estado + ". Dificultad: " + this.Dificultad + ". Numero maximo: " + this.num_max;
		
		return info;
	}
	
	
}
