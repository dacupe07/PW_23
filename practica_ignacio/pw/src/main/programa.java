package main;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;


import Pistas.*;
import Reservas.*;
import Usuarios.*;


public class programa {
	public static void main(String [] args) {
		
		Properties prop = new Properties();
		Scanner entrada = new Scanner (System.in);
		String rutaAbsoluta = "./ficheros";
		try{
			String rutaFicheroPropiedades = rutaAbsoluta + "/propiedades.properties";
			InputStream is = new FileInputStream(rutaFicheroPropiedades);		
			prop.load(is);
			
			int opcion=50;
			while(opcion!=0){
				System.out.print("Introduce una opcion: 0.Salir 1.Gestionar usuarios 2.Gestionar pistas 3.Gestionar karts 4.Gestionar reservas ");
				try{ 
					opcion = entrada.nextInt();
					if(opcion == 1) {
						
						entrada = new Scanner(System.in); // Limpiamos el buffer de entrada
						GestorUsuario gestor=new GestorUsuario();
						gestor.cargarUsuarios(prop);
						if(gestor.crearUsuarios(entrada) == 1) {
							
							System.out.println("Se ha registro con exito el usuario"); }
							
							gestor.listarUsuarios();
							gestor.RegistroUsuario(prop); // Actualizamos el fichero de datos
						
							entrada = new Scanner(System.in); // Limpiamos el buffer de entrada
						
					}
					
					if(opcion == 2) {
						
						entrada = new Scanner(System.in); // Limpiamos el buffer de entrada
						GestorPistas gestor=new GestorPistas();
						gestor.cargarPistas(prop);
						if(gestor.crearPistas(entrada) == 1) {
							
							System.out.println("Se ha registro con exito la pista"); }
							
							gestor.ListarPistasMantenimiento();
							gestor.RegistroPistas(prop); // Actualizamos el fichero de datos
						
							entrada = new Scanner(System.in); // Limpiamos el buffer de entrada
						
					}
					
					if(opcion == 3) {
						
						entrada = new Scanner(System.in); // Limpiamos el buffer de entrada
						GestorPistas gestor=new GestorPistas();
						gestor.cargarPistas(prop);
						if(gestor.crearKarts(entrada) == 1) {
							
							System.out.println("Se ha registro con exito el kart"); }
							
							gestor.RegistroKart(prop); // Actualizamos el fichero de datos
						
							entrada = new Scanner(System.in); // Limpiamos el buffer de entrada
					}
					
					if(opcion == 4) {
						
						entrada = new Scanner(System.in); // Limpiamos el buffer de entrada
						GestorReservas gestor=new GestorReservas();
						gestor.cargarUsuarios(prop);
						gestor.cargarPistas(prop);
						gestor.cargarUsuarios(prop);
						if(gestor.hacerReservaIndividual(entrada)) {
							
							System.out.println("Se ha registro con exito la reserva"); }
							
							gestor.RegistroReservas(prop); // Actualizamos el fichero de datos
						
							entrada = new Scanner(System.in); // Limpiamos el buffer de entrada
					}
					
					}catch(Exception ex1) { 
						System.out.println("¡Cuidado! Solo puedes insertar números. ");
						entrada.next();}
				}
		
		}catch(Exception ex) {
			System.out.println("Error al obtener el fichero de propiedades");
			return;
		}
		
	}
}
