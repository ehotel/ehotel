<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Login</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<link href="style.css" rel="stylesheet" type="text/css" />
<link rel="top" title="Head" href="../../header.html">
<link href="layout.css" rel="stylesheet" type="text/css" />
<script src="maxheight.js" type="text/javascript"></script>
<!--[if lt IE 7]>
	<link href="ie_style.css" rel="stylesheet" type="text/css" />
   <script type="text/javascript" src="ie_png.js"></script>
   <script type="text/javascript">
	   ie_png.fix('.png, #header .row-2, #header .nav li a, #content, .gallery li');
   </script>
<![endif]-->
</head>

<body id="page1" onload="new ElementMaxHeight();">
<div id="main">
<!-- HEADER -->
	<div id="header">
		<div class="row-1">
			<div class="wrapper">
				<div class="logo">
					<h1><a href="index.html">E-Hotel<i>Y</i></a></h1>
					<em>Hotel</em>
					<strong>True Comfort</strong>
				</div>
			</div>
		</div>
					
		

	<div id="content">
		<div class="wrapper">
			<div class="aside maxheight">
 		
<!-- BODY -->
			</div>
			<div class="content">
				<div class="indent">
					<h2>Welcome to E-HotelY</h2>
					<div class="h31"> Sign in with your E-Hotely userID und password</div>
					<div class="h31"> </div>
					<div class="line-hor-lang"></div>
						
<!-- HIER KOMMEN DIE JSP VERKNÜPFUNGEN -->		
					<form action="beispielJSP.jsp" id="login-form" method="get">			
						<fieldset>
						<div class="field">UserID<br/><br/>
								<input type="text"  name="userid" /></div>
								
							<div class="field"><br/>Password <br/><br/>
							<input type="text" name="password"  /></div>
						</fieldset>			
						Forgot your <a href="passwordreset">password</a>?
				<div style="float:left;">
						<div class="button"><span><span><a href="index.html" onclick="document.getElementById('registration-form').submit()">Sign in</a></span></span></div>
					</div><br><br><br>
					<div class="field">New to E-HotelY?</div>><br>
					<div style="float:left;">
						<div class="button"><span><span><a href="registration" onclick="document.getElementById('registration-form').submit()">Register</a></span></span></div>
					</div>
				</div>
					</form>			      
					<div class="clear"></div>
								
				</div>
			</div>
		</div>
	</div>
<!-- footer -->
	<div id="footer">
  		<ul class="nav">
	 		<li><a href="index.html">Home</a>|</li>
			<li><a href="services.html">Services</a>|</li>
			<li><a href="gallery.html">Gallery</a>|</li>
			<li><a href="restaurant.html">Restaurant</a>|</li>
			<li><a href="testimonials.html">Testimonials</a>|</li>
			<li><a href="booking.html">Booking</a></li>
		</ul>
	
	</div>
</div>
</body>
</html>