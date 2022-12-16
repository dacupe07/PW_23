package es.uco.pw.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.uco.pw.dao.usuarioDAO;

@WebServlet("/LoginControllerServlet")

public class loginControllerServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	public loginControllerServlet()
	{
		super();
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.getWriter().append("Served at 1: ").append(request.getContextPath());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
		
				//Se comprueba si est� ya logueado mediante los valores del atributo asignado a session. En caso 
				//de ya haber iniciado sesi�n se asignar� a la varible de direccionamiento el valor de la ruta hacia el perfil personal
				//del usuario.
				//Si no est� logueado llamar� a la funci�n DAO que comprueba la validez de los datos introducidos en el form de la vista.
				//Es una funci�n booleana que pasar� al loginSuccess en caso de acierto o volver� al mismo login en caso de fallo.
				
		HttpSession session = request.getSession(true);
		
		if((session.getAttribute("correo") == null) || (session.getAttribute("correo") == ""))
		{
			String correo = request.getParameter("correo");
			String password = request.getParameter("password");
			
			String BDdriver = getServletContext().getInitParameter("BDdriver");
			String BDurl = getServletContext().getInitParameter("BDurl");
			String BDuser = getServletContext().getInitParameter("BDuser");
			String BDpass = getServletContext().getInitParameter("BDpass");
			
			usuarioDAO login;
			
			try
			{
				login = new usuarioDAO(BDdriver, BDurl, BDuser, BDpass);
				
				boolean userValidate = login.validarUsuario(correo, password);
				
				if(userValidate == true)
				{
					session.setAttribute("correo", correo);
					request.getRequestDispatcher("perfilControllerServlet").forward(request, response);
				}
				else
				{
					request.getRequestDispatcher("/mvc/view/login/loginViewFail.jsp").forward(request, response);
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			request.getRequestDispatcher("perfilControllerServlet").forward(request, response);
		}
	}
}
				


