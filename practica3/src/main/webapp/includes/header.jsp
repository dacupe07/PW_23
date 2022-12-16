<!--
      -Resumen: Header de nuestra p�gina
      -Autor: Jorge Jes�s Chaparro Ibarra y Elio Jes�s Jim�nez Luque
-->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    

<header>
		<ul>
		  <li><a>Iniciado como: ${sessionScope.rol}</a></li>
		  <li><a class="active" href="${pageContext.request.contextPath}/listarAnunciosControllerServlet?tipo=todos">Inicio</a></li>
  		  <li><a class="active" href="/practica3/creadorGeneralServlet?opc=pista">Crear pista</a></li>
  		  <li><a class="active" href="/practica3/creadorGeneralServlet?opc=kart">Crear kart</a></li>
  		  <li><a class="active" href="${pageContext.request.contextPath}/listarAnunciosControllerServlet?tipo=tematico">Asociar Kart y pistas</a></li>
  		  <li><a class="active" href="${pageContext.request.contextPath}/listarAnunciosControllerServlet?tipo=individual">Modificar Kart</a></li>
  		  <li><a class="active" href="${pageContext.request.contextPath}/listarAnunciosControllerServlet?tipo=flash">Modificar Pista</a></li>
  	  	  <li><a class="active" href="${pageContext.request.contextPath}/listarAnunciosControllerServlet?tipo=propietario">Eliminar Reservas</a></li>
  	  	  <li><a class="active" href="/practica3/perfilControllerServlet">Perfil</a></li>
  	  	 <!-- <li><a class="active" href="/practica3/cerrarSesionControllerServlet">Cerrar Sesi�n</a></li>--> 
		</ul>
</header>