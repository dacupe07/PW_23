package es.uco.pw.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import es.uco.pw.dao.usuarioDAO;
import es.uco.pw.business.usuario.usuarioDTO;

/**
 * Servlet implementation class modificarControllerServlet
 */
@WebServlet("/modificarControllerServlet")
public class modificarControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public modificarControllerServlet() {
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
		
		String BDdriver = getServletContext().getInitParameter("BDdriver");
		String BDurl = getServletContext().getInitParameter("BDurl");
		String BDuser = getServletContext().getInitParameter("BDuser");
		String BDpass = getServletContext().getInitParameter("BDpass");
		
		usuarioDTO usuario = new usuarioDTO();
		
		String nombre = request.getParameter("nombre");
		String password = request.getParameter("password");
	
		String fecha_nacimiento = request.getParameter("fecha_nacimiento");
		
		fecha_nacimiento = String.format(fecha_nacimiento, "yyyy/mm/dd");
		LocalDate fecha_nac = LocalDate.parse(fecha_nacimiento);

		
		
		
		try 
		{
			//Salta el warning porque no se usa el objeto pero es necesario crearlo para pasarle las variables de la base de datos.
			usuarioDAO DatosDao = new usuarioDAO(BDdriver, BDurl, BDuser, BDpass);
			
			usuario.setNombre(nombre);
			usuario.setPassowrd(password);
			usuario.setFechaNacimiento(fecha_nac);
			
			
			HttpSession session = request.getSession(true);
			
			String correo_propietario = (String) session.getAttribute("correo");
			
			DatosDao.updatePassword(usuario, correo_propietario);
			DatosDao.updateDatos(usuario, correo_propietario);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher rd;
		rd = request.getRequestDispatcher("/mvc/control/login/goLoginController.jsp");
		rd.forward(request, response);
	}

}
