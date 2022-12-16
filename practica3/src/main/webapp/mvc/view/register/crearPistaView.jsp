<!--
      -Resumen: Vista del registro de la aplicación
      -Autor: David Cuevas Pérez
-->
     
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title> ${sessionScope.nombre} - Crear una pista</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/registro/pista.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
<script type="text/javascript" src="../../../js/validarFormatoPassword.js"></script>
</head>
<body>

	<div class="registrar">

		<h1>Registro de pista</h1>

		<!-- Mediante un formulario y el método post enviamos los datos al controlador que se encargará de registrar al usuario -->

		<form onsubmit="return validarFormatoPassword()" id="myForm" action="/practica3/crearPistaControllerServlet" method="post">
					
					<input type="text" name="nombre" placeholder="Introduzca el nombre de la pista" required="required" />
				   
				    <input type="number" name="num_max" placeholder="Introduce el numero maximo de karts:" required="required" />
				
					<br> <br>
					
					<h1>Seleccione el estado: </h1>
					
					<label class="container"><input type="checkbox" name="estado" value="disponible"><span class="checkmark"></span>Disponible</label>
					
					<label class="container"><input type="checkbox" name="estado" value="mantenimiento"><span class="checkmark"></span>En mantenimiento</label>
					
					<br> <br>
									
					<h1>Seleccione la dificultad: </h1>
					
					<label class="container"><input type="checkbox" name="dificultad" value="infantil"><span class="checkmark"></span>Infantil</label>
					
					<label class="container"><input type="checkbox" name="dificultad" value="familiar"><span class="checkmark"></span>Familiar</label>
					
					<label class="container"><input type="checkbox" name="dificultad" value="adultos"><span class="checkmark"></span>Adultos</label>
					
					<input type="submit" value="Enviar">
		</form>
</div>
</body>
</html>