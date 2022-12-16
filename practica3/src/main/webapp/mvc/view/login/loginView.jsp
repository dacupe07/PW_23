<!--
      -Resumen: Vista de inicio de sesi�n
      -Autor: Jorge Jes�s Chaparro Ibarra y Elio Jes�s Jim�nez Luque
-->
     
<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="UTF-8">
    <title>Inicio de Sesi�n</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login/login.css">
    <link rel="shortcut icon" href="../../../img/favicon.ico">


  </head>
  <body>
	
    <div class="login">
      <h1 class=sombra>Inicio de Sesi�n</h1>
	  
	  <!-- Se introducen los datos en el formulario para enviarlos mediante el m�todo post -->
	  <form method="post" action="/practica3/LoginControllerServlet"> <!--FORMULARIO-->   
        
        <div class="campos">
        	<!-- Correo INPUT -->
        	<input type="text" placeholder="Introduce el correo" name="correo" required>
        	<br>
   
        	<!-- CONTRASE�A INPUT -->
        	<input type="password" placeholder="Introduce la contrase�a" name="password" required>
	
        	<br>
        	<br>
        	<input type="submit" name="inicio" id="a" class="boton-personalizado" value="Iniciar Sesi�n"><br>        
		</div>  
      </form>
    </div>
    
    	<footer class="footer">
		 	<p> &copy; 2022 - i72cuped, Realizado por estudiantes de la UCO  </p>
		 	<a href="../../../index.jsp">Volver a inicio</a><i class="fas fa-id-card"></i>
		</footer>  
    
  </body>
</html>