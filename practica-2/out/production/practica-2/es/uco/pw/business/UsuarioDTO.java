package es.uco.pw.business;

import java.util.Date;

public class UsuarioDTO {
	
	 /*
    ATRIBUTOS DE LA CLASE USUARIO
 */

		protected String correo;
		protected String nombre;
		protected Date fecha_nacimiento;
		protected Date fecha_inscripcion;
		
		
		public UsuarioDTO(String correo, String nombre, Date fecha_nacimiento, Date fecha_inscripcion)
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

		    public Date getFechaNacimiento()
		    {
		        return this.fecha_nacimiento;
		    }

		    public void setFechaNacimiento(Date fecha_nacimiento)
		    {
		        this.fecha_nacimiento = fecha_nacimiento;
		    }

		    public Date getFechaInscripcion()
		    {
		        return this.fecha_inscripcion;
		    }

		    public void setFechaInscripcion(Date fecha_inscripcioh)
		    {
		        this.fecha_inscripcion = fecha_inscripcioh;
		    }
}
