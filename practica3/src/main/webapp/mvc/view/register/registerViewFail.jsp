<!--
      -Resumen: Vista de fallo al registrarse
      -Autor: Jorge Jesús Chaparro Ibarra y Elio Jesús Jiménez Luque
-->
     
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script src="js/password.js"></script>
<script type="text/javascript" src="../../../js/validarFormatoPassword.js"></script>
<title>Nunow - Registro</title>
<link rel="stylesheet" href="../../../css/registro/registro.css">
</head>
<body>

	<div class="login">

		<h1>Registro de usuario</h1>
		
		<h3>Ese correo ya está registrado!!!</h3>

		<!-- Al comprobar que el correo ya estaba registrado en la base de datos, vuelve a mostrar el formulario para enviar los datos mediante el método post -->

		<form onsubmit="return validarFormatoPassword()" id="myForm" action="../../../registerControllerServlet" method="post">
					
					<input type="text" name="nombre" placeholder="Introduzca el nombre" required="required" />
				    
				    <input type="text" name="apellidos" placeholder="Introduzca sus apellidos" required="required" />
				   
				    <input type="text" name="correo" placeholder="Introduzca su correo" required="required" />
				   
				    <input type="password" name="password" placeholder="Introduzca su contraseña" required="required" />
				    
				    <input type="date" name="fecha_nacimiento" required="required" />
				
					<br> <br>
					<label class="container"><input type="checkbox" name="coches" value="coches"><span class="checkmark"></span>Coches</label>
					
					<label class="container"><input type="checkbox" name="motos" value="motos"><span class="checkmark"></span>Motos</label>
					
					<label class="container"><input type="checkbox" name="animales" value="animales"><span class="checkmark"></span>Animales</label>
				
					<label class="container"><input type="checkbox" name="plantas" value="plantas"><span class="checkmark"></span>Plantas</label>
				
					<label class="container"><input type="checkbox" name="tecnologia" value="tecnologia"><span class="checkmark"></span>Tecnología</label>
				
					<label class="container"><input type="checkbox" name="videojuegos" value="videojuegos"><span class="checkmark"></span>Videojuegos</label>
					
					<label class="container"><input type="checkbox" name="musica" value="musica"><span class="checkmark"></span>Música</label>
				
					<label class="container"><input type="checkbox" name="artes" value="artes"><span class="checkmark"></span>Artes</label>
					
					<input type="submit" value="Enviar">
		</form>
</div>
</body>
</html>