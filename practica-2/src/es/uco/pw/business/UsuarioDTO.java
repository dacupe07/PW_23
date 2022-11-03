package es.uco.pw.business;

import java.time.LocalDate;

public class UsuarioDTO {
	
	 /*
    ATRIBUTOS DE LA CLASE USUARIO
 */

		protected String correo;
		protected String nombre;
		protected LocalDate fecha_nacimiento;
		protected LocalDate fecha_inscripcion;
		
		public UsuarioDTO() {}
		
		public UsuarioDTO(String correo, String nombre, LocalDate fecha_nacimiento, LocalDate fecha_inscripcion)
	    {
	        this.correo = correo;
	        this.nombre = nombre;
	        this.fecha_inscripcion = fecha_inscripcion;
	        this.fecha_nacimiento = fecha_nacimiento;
	    }
		
		 public String getNombre()
		    {
		        return this.nombre;
		    }

		    public void setNombre(String nombre)
		    {
		        this.nombre = nombre;
		    }

		    public String getCorreo()
		    {
		        return this.correo;
		    }

		    public void setCorreo(String correo)
		    {
		        this.correo = correo;
		    }

		    public LocalDate getFechaNacimiento()
		    {
		        return this.fecha_nacimiento;
		    }

		    public void setFechaNacimiento(LocalDate fecha_nacimiento)
		    {
		        this.fecha_nacimiento = fecha_nacimiento;
		    }

		    public LocalDate getFechaInscripcion()
		    {
		        return this.fecha_inscripcion;
		    }

		    public void setFechaInscripcion(LocalDate fecha_inscripcioh)
		    {
		        this.fecha_inscripcion = fecha_inscripcioh;
		    }
		    
		    public String toString() {
				String info = "\nNombre y apellidos: " + this.nombre + "\nFecha de nacimiento: " + this.fecha_nacimiento + "\nFecha de inscripcion: " + this.fecha_inscripcion + "\nEmail: " + this.correo + "\n\n";
				
				return info;
			}
}
