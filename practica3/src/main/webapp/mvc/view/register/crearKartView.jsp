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
<title> ${sessionScope.nombre} - Crear un kart</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/registro/pista.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
<script type="text/javascript" src="../../../js/validarFormatoPassword.js"></script>
</head>
<body>

	<div class="registrar">

		<h1>Registro de kart</h1>

		<!-- Mediante un formulario y el método post enviamos los datos al controlador que se encargará de registrar al usuario -->

		<form onsubmit="return validarFormatoPassword()" id="myForm" action="/practica3/crearKartControllerServlet" method="post">
				   
				    <input type="number" name="id_kart" placeholder="Introduce el ID: " required="required" />
				
					<br> <br>
					
					<h1>Seleccione el estado: </h1>
					
					<label class="container"><input type="checkbox" name="estado" value="disponible"><span class="checkmark"></span>Disponible</label>
					
					<label class="container"><input type="checkbox" name="estado" value="mantenimiento"><span class="checkmark"></span>En mantenimiento</label>

					<label class="container"><input type="checkbox" name="estado" value="reservado"><span class="checkmark"></span>Reservado</label>
					
										
					<br> <br>
									
					<h1>Seleccione el tipo: </h1>
					
					<label class="container"><input type="checkbox" name="tipo" value="niño"><span class="checkmark"></span>Niño</label>
					
					<label class="container"><input type="checkbox" name="tipo" value="adulto"><span class="checkmark"></span>Adulto</label>
										
					<input type="submit" value="Enviar">
		</form>
</div>
</body>
</html>