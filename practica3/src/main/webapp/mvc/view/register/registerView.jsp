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
<script src="js/password.js"></script>
<script type="text/javascript" src="../../../js/validarFormatoPassword.js"></script>
<title>Registro en el sitio Web</title>
<link rel="stylesheet" href="../../../css/registro/registro.css">
</head>
<body>

	<div class="login">

		<h1>Registro de usuario</h1>

		<!-- Mediante un formulario y el método post enviamos los datos al controlador que se encargará de registrar al usuario -->

		<form onsubmit="return validarFormatoPassword()" id="myForm" action="../../../registerControllerServlet" method="post">
					
					<input type="text" name="nombre" placeholder="Introduzca el nombre completo" required="required" />
				   
				    <input type="text" name="correo" placeholder="Introduzca su correo" required="required" />
				   
				    <input type="password" name="password" placeholder="Introduzca su contraseña" required="required" />
				    
				    <input type="date" name="fecha_nacimiento" required="required" />
				
					<br> <br>
					
					<h1>Seleccione su rol: </h1>
					
					<label class="container"><input type="checkbox" name="rol" value="administrador"><span class="checkmark"></span>Administrador</label>
					
					<label class="container"><input type="checkbox" name="rol" value="cliente"><span class="checkmark"></span>Cliente</label>
					
					<input type="submit" value="Enviar">
		</form>
</div>
</body>
</html>