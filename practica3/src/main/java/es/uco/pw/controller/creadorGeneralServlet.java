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

import es.uco.pw.business.pista.dificultad;
import es.uco.pw.business.pista.pistaDTO;
import es.uco.pw.dao.pistaDAO;



/**
 * Servlet implementation class registerControllerServlet
 */
@WebServlet("/creadorGeneralServlet")
public class creadorGeneralServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public creadorGeneralServlet() 
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
		
		String opc = request.getParameter("opc");

		if(opc.equalsIgnoreCase("pista"))
		{
			RequestDispatcher rd;
			rd = request.getRequestDispatcher("/mvc/view/register/crearPistaView.jsp");
			rd.forward(request, response);
		}
		else if(opc.equalsIgnoreCase("kart"))
		{
			RequestDispatcher rd;
			rd = request.getRequestDispatcher("/mvc/view/register/crearKartView.jsp");
			rd.forward(request, response);
		}
		else if(opc.equalsIgnoreCase("modkart"))
		{
			RequestDispatcher rd;
			rd = request.getRequestDispatcher("/mvc/view/register/modificarKartView.jsp");
			rd.forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		doGet(request, response);		
		

	}
}
