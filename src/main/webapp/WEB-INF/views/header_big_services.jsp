<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>Homepage</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta name="description" content="homepage"/>
<link href="resources/style.css" rel="stylesheet" type="text/css" />
<link href="resources/layout.css" rel="stylesheet" type="text/css" />
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
<!-- header -->
	<div id="header">
		<div class="row-1">
			<div class="wrapper">
				<div class="logo">
					<h1><a href="index.html">Five Star</a></h1>
					<em>Hotel</em>
					<strong>True Luxury</strong>
				</div>
				<div class="othermenu">
					<div class="fright">My E-Hotel<i>Y</i> | Anfahrt | Kontakt | Impressum</div><br/>
					<div class="fright"><sec:authorize ifAnyGranted="ROLE_USER">
    	 			<sec:authentication property="principal.username"/>(<a href="<c:url value="/j_spring_security_logout"/>" >Logout</a>)
    </sec:authorize></div>
				</div>
			</div>
		</div>
					
		<div class="row-2">
	 		<div class="indent">
<!-- header-box begin -->
				<div class="header-box">
					<div class="inner">
						<ul class="nav">
					 		<li><a href="home">Home page</a></li>
							<li><a href="online_booking">Online Booking</a></li>
							<li><a href="services" class="current">Services</a></li>
							<li><a href="restaurant">Restaurant</a></li>
							<li><a href="testimonials">Testimonials</a></li>
							<li><a href="gallery">Gallery</a></li>
						</ul>
					</div>
				</div>
<!-- header-box end -->
			</div>
		</div>
	</div>
	
	
	
	