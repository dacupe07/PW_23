package es.uco.pw.clases;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.*;


public class kart
{
	
	private int idKart;
	private boolean tipoKart;
	private disponibilidad Disponibilidad;
	
	
	private static kart Kart = null;
	
	public kart()
	{
		
	}
	
	public static kart getKart(int idKart, boolean tipoKart, disponibilidad Disponibilidad)
	{
		if(Kart == null)
		{
			Kart = new kart(idKart, tipoKart, Disponibilidad);
		}
		return Kart;
	}
	
	private kart(int idKart, boolean tipoKart, disponibilidad Disponibilidad)
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
	public boolean getKartTipo()
	{
		return tipoKart;
	}

	/**
	 * Set de la variable tipoKart
	 * 
	 * @param tipoKart
	 */
	public void setKartTipo(boolean tipoKart)
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
		String info = "ID: " + this.idKart + ". Tipo: " + this.tipoKart + ". Disponibilidad: " + this.Disponibilidad;
		
		return info;
	}
}
