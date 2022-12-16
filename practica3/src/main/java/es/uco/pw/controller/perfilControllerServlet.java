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
import java.util.Date;

import es.uco.pw.business.usuario.usuarioDTO;
import es.uco.pw.dao.usuarioDAO;



/**
 * Servlet implementation class perfilControllerServlet
 */
@WebServlet("/perfilControllerServlet")
public class perfilControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public perfilControllerServlet() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String BDdriver = getServletContext().getInitParameter("BDdriver");
		String BDurl = getServletContext().getInitParameter("BDurl");
		String BDuser = getServletContext().getInitParameter("BDuser");
		String BDpass = getServletContext().getInitParameter("BDpass");
	
		
		HttpSession session = request.getSession(true);
		
		session.setAttribute("controlador", 0);
		
		
		if (((String)session.getAttribute("correo") == null) || ((String)session.getAttribute("correo") == ""))
		{
			request.getRequestDispatcher("/mvc/view/login/loginViewFail.jsp").forward(request, response);	
		}
		else
		{
			
			String correo = (String)session.getAttribute("correo");
			
			try 
			{
				usuarioDAO DatosDao = new usuarioDAO(BDdriver, BDurl, BDuser, BDpass);
				usuarioDTO usuario = new usuarioDTO();
				usuario = DatosDao.datosUsuarioPorEmail(correo);
				Date date = new Date();
				 
				String Rol = String.valueOf(usuario.getRol());
				
				session.setAttribute("nombre", usuario.getNombre());
				session.setAttribute("correo", usuario.getCorreo());
				session.setAttribute("fecha_nacimiento", usuario.getFechaNacimiento());
				session.setAttribute("fecha_inscripcion", usuario.getFechaInscripcion());
				session.setAttribute("rol", Rol);
				session.setAttribute("fecha_actual", date);
				
				if(session.getAttribute("rol").equals("cliente"))
				{
					request.getRequestDispatcher("/mvc/view/perfil/perfilViewCliente.jsp").forward(request, response);	
				}
				else
				{
					request.getRequestDispatcher("/mvc/view/perfil/perfilViewAdmin.jsp").forward(request, response);	
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
	doGet(request, response);
	}

}






















/*
@WebServlet("/perfilControllerServlet")
public class perfilControllerServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	public perfilControllerServlet()
	{
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.getWriter().append("Served at 2: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
		
		Date date = new Date();
		String BDdriver = getServletContext().getInitParameter("BDdriver");
		String BDurl = getServletContext().getInitParameter("BDurl");
		String BDuser = getServletContext().getInitParameter("BDuser");
		String BDpass = getServletContext().getInitParameter("BDpass");
		
		HttpSession session = request.getSession(true);
		
		
		if ((session.getAttribute("correo") == null) || (session.getAttribute("correo") == ""))
		{
			request.getRequestDispatcher("/mvc/view/login/loginViewFail.jsp").forward(request, response);
		}
		else
		{
			
			String correo = (String)session.getAttribute("correo");
			
			
			
				try 
				{
					usuarioDAO DatosDao = new usuarioDAO(BDdriver, BDurl, BDuser, BDpass);
					usuarioDTO usuario = new usuarioDTO();
					usuario = DatosDao.datosUsuarioPorEmail(correo);
				 
					session.setAttribute("nombre", usuario.getNombre());
					session.setAttribute("correo", usuario.getCorreo());
					session.setAttribute("fecha_nacimiento", usuario.getFechaNacimiento());
					session.setAttribute("fecha_inscripcion", usuario.getFechaInscripcion());
					
					session.setAttribute("rol", usuario.getRol());
					session.setAttribute("fecha_actual", date);
				
					if(session.getAttribute("rol").equals("cliente"))
					{
						request.getRequestDispatcher("/mvc/view/perfil/perfilViewCliente.jsp").forward(request, response);
					}
					else
					{
						request.getRequestDispatcher("/mvc/view/perfil/perfilViewAdmin.jsp").forward(request, response);	
					}
				
				} 
				catch (SQLException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				
				
			
		}
		
	}
}
*/