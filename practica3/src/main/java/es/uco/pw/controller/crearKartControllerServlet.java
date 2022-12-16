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
import javax.servlet.http.HttpSession;

import es.uco.pw.business.kart.estado;
import es.uco.pw.business.kart.kartDTO;
import es.uco.pw.dao.kartDAO;



/**
 * Servlet implementation class registerControllerServlet
 */
@WebServlet("/crearKartControllerServlet")
public class crearKartControllerServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public crearKartControllerServlet() 
    {
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
		
		HttpSession session = request.getSession(true);
		
		kartDTO k = new kartDTO();
		
		String id = request.getParameter("id_kart");		
		String tipo = request.getParameter("tipo");
		
		estado Estado = estado.valueOf(request.getParameter("estado"));
		
		
		
		String BDdriver = getServletContext().getInitParameter("BDdriver");
		String BDurl = getServletContext().getInitParameter("BDurl");
		String BDuser = getServletContext().getInitParameter("BDuser");
		String BDpass = getServletContext().getInitParameter("BDpass");
		


		
		try 
		{
			kartDAO DatosDao = new kartDAO(BDdriver, BDurl, BDuser, BDpass);
				
				int registro = DatosDao.comprobarRegistro(Integer.parseInt(id));
			
				if(registro != 0) 
				{

					k.setIdKart(Integer.parseInt(id));
					k.setTipo(tipo);
					k.setEstado(Estado);
	
					DatosDao.save_info(k);
					
					RequestDispatcher rd;
					rd = request.getRequestDispatcher("perfilControllerServlet");
					rd.forward(request, response);
				}
				else 
				{
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
