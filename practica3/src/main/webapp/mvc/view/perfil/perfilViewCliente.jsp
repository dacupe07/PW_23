<!--
      -Resumen: Vista que muestra el perfil de un usuario
      -Autor: Jorge Jesús Chaparro Ibarra y Elio Jesús Jiménez Luque
-->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>TU PERFIL</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/perfil/perfil.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
</head>
<body>
	
	<%@include file="../../../includes/header.jsp" %>

	<!-- Nos muestra la información del usuario y nos muestra enlaces a las distintas funcionalidades como crear anuncios o mostrarlos -->
	<div class="perfil">
	
		<div class="imagen">
			<img  alt="Foto de perfil" width= 150px src="${pageContext.request.contextPath}/img/perfilAvatar.svg" />      
		</div>      
        
 
        <h3 class="tituloPerfil"> Bienvenido, ${sessionScope.nombre}</h3>
        <h3 class="tituloPerfil"> Fecha Actual: ${sessionScope.fecha_actual}</h3>
        <h3 class="tituloPerfil">Fecha de inscripcion: ${sessionScope.fecha_inscripcion} </h3>

      <!--  <h5 class="tituloPerfil">Contraseña: ${sessionScope.password} </h5> -->
     
     <!--
   		<div class="container" >
        	<a href="${pageContext.request.contextPath}/listarAnunciosControllerServlet?tipo=crear_anuncio_general"> Crear Anuncio General</a>
		</div>
        
        <div class="container" >
        	<a href="${pageContext.request.contextPath}/listarAnunciosControllerServlet?tipo=crear_anuncio_tematico"> Crear Anuncio Tematico </a>
   		</div>
   		
   		<div class="container" >
       		<a href="${pageContext.request.contextPath}/listarAnunciosControllerServlet?tipo=crear_anuncio_individual"> Crear Anuncio Individual </a>
        </div>
       		
        <div class="container" >
       		<a href="${pageContext.request.contextPath}/listarAnunciosControllerServlet?tipo=crear_anuncio_flash"> Crear Anuncio Flash </a>
        </div>
        
        <div class="container" >
        	<a href="${pageContext.request.contextPath}/listarAnunciosControllerServlet?tipo=propietario"> Tus anuncios</a>
        </div>
        -->
        <div class="container" >
        	<a href="${pageContext.request.contextPath}/mvc/control/register/goModificarController.jsp"> Modifica tus datos</a>
        </div>
        <div class="container" >
        	<a href="/practica3/cerrarSesionControllerServlet">Cerrar Sesión</a>
        </div>       
    </div>
    <div class="pie">
        <footer class="footer">
		 	<p> &copy; 2022 - i72cuped, Realizado por estudiantes de la UCO  </p>
		</footer>
	</div>  
</body>

</html>