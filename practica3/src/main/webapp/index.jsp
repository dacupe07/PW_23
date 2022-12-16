<!--
      -Resumen: Vista del index de la aplicación
      -Autor: David Cuevas Pérez
-->

<!DOCTYPE html>
<html>
<head>
	
	<title>Login y registro para pistas</title>
    <link rel="stylesheet" href="css/index/index.css">
    <style type="text/css"></style>
    <link rel="shortcut icon" href="img/favicon.ico">
</head>
<body>

	<div class="contenedor">
<header class="header">
	  <div class="logeo">
				<a href="mvc/control/login/goLoginController.jsp">Iniciar Sesion</a> <em class="fas fa-sign-in-alt"></em>	
				<div class="logo">
					<img alt="logo" width="70px" src="img/logocarreras.png">
				</div>
			</div>
			
			<div class="titulo">
				<h1>Karting "El Nano" Alonso</h1>
			</div>  
			     
		</header>
		<div class="titleMid">
			<h1> Con nuestra aplicación web podras administrar y realizar reserva de kart y pistas</h1>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			
			<div class="registro">
				<h3> Registrate pulsando el siguiente enlace</h3>
				<a href="mvc/control/register/goRegisterController.jsp">Registrarse</a><i class="fas fa-id-card"></i>	
			</div>	
		</div>
		<div class="imagen"> </div>
		         
		<div class = "wrapper">
<div class="slider" id="slider">
      <ul class="slider-controler">
		      <li><a href="#slide1">&bullet;</a></li>
		      <li><a href="#slide2">&bullet;</a></li>
		      <li><a href="#slide3">&bullet;</a></li>
		      <li><a href="#slide4">&bullet;</a></li>
      </ul>
			<ul class="slides">
					<li class="slide" id="slide1">
						<a href="#">
							<p class="caption">Texto</p>
							<img src="img1.jpg" alt="photo 1">
						</a>
					</li>
					<li class="slide" id="slide2">
						<a href="#">
							<p class="caption">Texto</p>
							<img src="img2.jpg" alt="photo 2">
						</a>
					</li>
					<li class="slide" id="slide3">
						<a href="#">
							<p class="caption">Texto</p>
							<img src="img3.jpg" alt="photo 3">
						</a>
					</li>
					<li class="slide" id="slide4">
						<a href="#">
							<p class="caption">Texto</p>
							<img src="img4.jpg" alt="photo 4">
						</a>
					</li>
			  </ul>
</div>

		</div>
			         
		<footer class="footer">
		 	<p> &copy; 2022 - i72cuped, Realizado por estudiantes de la UCO  </p>
		</footer>    
	</div>
</body>
</html>