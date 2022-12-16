<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import = "es.uco.pw.dao.usuarioDAO" %>
<%@page import = "es.uco.pw.business.usuario.usuarioDTO"%>
<%@page import = "es.uco.pw.controller.loginControllerServlet"%>
<%@page import = "es.uco.pw.controller.perfilControllerServlet"%>
<jsp:useBean id = "customerBean" scope = "session" class = "es.uco.pw.business.usuario.customerBean"></jsp:useBean>


<%
// Posibles flujos:
//	1) customerBean esta logado (!= null && != "") -> Se redirige al index.jsp
//	2) No hay parametros en el request -> procede de otra funcionalidad o index.jsp


// Caso 1; Por defecto vuelve al index.jsp
String nextPage = "../../index.jsp";
String mensajeNextPage = "";

// Caso 2
if(customerBean == null || customerBean.getEmailUser().equals(""))
{
	String correo = request.getParameter("correo");
	String password = request.getParameter("password");
	
	String BDdriver = getServletContext().getInitParameter("BDdriver");
	String BDurl = getServletContext().getInitParameter("BDurl");
	String BDuser = getServletContext().getInitParameter("BDuser");
	String BDpass = getServletContext().getInitParameter("BDpass");
	
	//Caso 2.a: Hay parametros -> procede de la VISTA
	if(correo != null)
	{
		//Se accede a la BD para obtener el usuario
		usuarioDAO userDAO = new usuarioDAO(BDdriver, BDurl, BDuser, BDpass);
		usuarioDTO usuario = userDAO.datosUsuarioPorEmail(correo);
				
		//Se realizan todas las comprobaciones necesarias del dominio
		//Aqui comprobamos que exista el usuario
		
		if(usuario != null && usuario.getCorreo().equalsIgnoreCase(correo))
		{
			//Usuario valido
%>
<jsp:setProperty property = "correo" value = "<%=correo%>" name="customerBean"/>	
<%
		}
		else
		{
			// Usuario no valido
			nextPage = "../view/loginView.jsp";
			mensajeNextPage = "El usuario que ha indicado no existe o no es valido";
		}
		//Caso 2.b - Se debe ir a la vista por primera vez
		

	}
	else
	{
		nextPage = "../view/loginView.jsp";
	}
}
%>

 <jsp:forward page="<%=nextPage%>">
 	<jsp:param value="<%=mensajeNextPage%>" name="message"/>
 </jsp:forward>