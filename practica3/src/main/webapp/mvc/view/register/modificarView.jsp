<!--
      -Resumen: Vista para modificar los datos de un usuario
      -Autor: Jorge Jesús Chaparro Ibarra y Elio Jesús Jiménez Luque
-->
     
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title> ${sessionScope.nombre} - Modifica tus datos</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/registro/registro.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
<script type="text/javascript" src="../../../js/validarFormatoPassword.js"></script>
</head>
<body>
<%@include file="../../../includes/header.jsp" %>
	<div class="login">
		
		<!--  Modificaremos los datos que nos interese mediante un nuevo formulario y se actualizarán los datos del perfil del usuario -->
		<form onsubmit="return validarFormatoPassword()" id="myForm" action="../../../modificarControllerServlet" method="post">
			<br><br>
			<h1>Modifique sus datos</h1>
			<h4>Nombre y apellidos: </h4><input type="text" name="nombre">
			<br><br>
			<h4>Contraseña: </h4> <input type="text" name="password">
			<br><br>
			<h4>Correo: ${sessionScope.correo}</h4>
			<br>
			<h4>Rol: ${sessionScope.rol}</h4>
			<br>
			<h4>Fecha de nacimiento:</h4> <input type="date" name="fecha_nacimiento">
			<br><br>
			<input type="submit" value="Enviar">
		</form>
	</div>
</body>
</html>