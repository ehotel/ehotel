<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>Homepage</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta name="description" content="homepage"/>
<link href="/ehotel/resources/style.css" rel="stylesheet" type="text/css" />
<link href="/ehotel/resources/layout.css" rel="stylesheet" type="text/css" />
<script src="/ehotel/resources/maxheight.js" type="text/javascript"></script>
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
<!-- header -->
	<div id="header">
		<div class="row-1">
			<div class="wrapper">
				<div class="logo">
					<h1><a href="/ehotel">E-HOTEL<i>Y</i></a></h1>
					<em>Hotel</em>
					<strong>True Comfort</strong>
				</div>
				<div class="othermenu">
					<div class="fright"><a href="/ehotel/menu">My E-Hotel<i>Y</i></a> | <!-- <a href="/ehotel/arrival">Anfahrt</a> | --> <a href="/ehotel/kontakt">Kontakt</a> | <a href="/ehotel/impressum">Impressum</a></div><br/>
					<div class="fright">
						<sec:authorize ifAnyGranted="ROLE_USER">
					    	<a href="/ehotel/profil"><sec:authentication property="principal.username"/></a>
						    (<a href="<c:url value="/j_spring_security_logout"/>"> Logout</a>)
					    </sec:authorize>
						<sec:authorize ifAnyGranted="ROLE_ADMIN">
					    	<a href="/ehotel/admin"><sec:authentication property="principal.username"/></a>
						    (<a href="<c:url value="/j_spring_security_logout"/>"> Logout</a>)
					    </sec:authorize>
					</div>
				</div>
			</div>
		</div>
					
		<div class="row-2">
	 		<div class="indent">
<!-- header-box begin -->
				<div class="header-box">
					<div class="inner">
						<ul class="nav">
					 		<li><a id="home" href="/ehotel">Home</a></li>
							<li><a id="booking" href="/ehotel/online_booking">Online Booking</a></li>
							<li><a id="services" href="/ehotel/services">Services</a></li>
							<li><a id="restaurant" href="/ehotel/restaurant">Restaurant</a></li>
							<li><a id="testimonials" href="/ehotel/testimonials">Testimonials</a></li>
							<li><a id="gallery" href="/ehotel/gallery">Gallery</a></li>
						</ul>
					</div>
				</div>
<!-- header-box end -->
			</div>
		</div>
	</div>
	
	
	
	