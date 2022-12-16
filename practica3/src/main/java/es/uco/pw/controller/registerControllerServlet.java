package es.uco.pw.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.print.attribute.standard.DateTimeAtCompleted;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.uco.pw.business.usuario.rol;
import es.uco.pw.business.usuario.usuarioDTO;
import es.uco.pw.dao.usuarioDAO;



/**
 * Servlet implementation class registerControllerServlet
 */
@WebServlet("/registerControllerServlet")
public class registerControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registerControllerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		response.setContentType("text/html");
		
		String BDdriver = getServletContext().getInitParameter("BDdriver");
		String BDurl = getServletContext().getInitParameter("BDurl");
		String BDuser = getServletContext().getInitParameter("BDuser");
		String BDpass = getServletContext().getInitParameter("BDpass");
		
		usuarioDTO u = new usuarioDTO();
		
		String nombre = request.getParameter("nombre");
		String correo = request.getParameter("correo");
		String password = request.getParameter("password");
		
		String fecha_nacimiento = request.getParameter("fecha_nacimiento");
		
		fecha_nacimiento = String.format(fecha_nacimiento, "yyyy/mm/dd");
		LocalDate fecha_nac = LocalDate.parse(fecha_nacimiento);

		
		LocalDate fecha_inscripcion = LocalDate.now();
		
		rol Rol = rol.valueOf(request.getParameter("rol"));

		
		try 
		{
			usuarioDAO DatosDao = new usuarioDAO(BDdriver, BDurl, BDuser, BDpass);
			
			int registro = DatosDao.comprobarRegistro(correo);
			
			if(registro != 0) 
			{
				u.setNombre(nombre);
				u.setCorreo(correo);
				u.setPassowrd(password);
				u.setFechaNacimiento(fecha_nac);
				u.setRol(Rol);
				u.setFechaInscripcion(fecha_inscripcion);
				
				
				DatosDao.save(u);
				DatosDao.save_info(u);
				
				RequestDispatcher rd;
				rd = request.getRequestDispatcher("/mvc/control/login/goLoginController.jsp");
				rd.forward(request, response);
			}else {
				RequestDispatcher rd;
				rd = request.getRequestDispatcher("/mvc/view/registro/registerViewFail.jsp");
				rd.forward(request, response);
			}
			
			
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
