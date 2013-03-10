<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>ehotel Adminbereich</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Style-Type" content="text/css" />
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
<!-- HEADER -->
	<div id="">
		<div class="row-1">
			<div class="wrapper">
				<div class="logo">
					<h1><a href="index.html">E-Hotel<i>Y</i></a></h1>
					<br />
					<div>
						Sie sind als Admin eingeloggt. <a href="<c:url value="/j_spring_security_logout" />">Logout</a><br/><br/>
					</div>					
					<div class="nav">
							<div style="float:left;">
								<a href="/ehotel/zimmerkategorie/anlegen">Zimmerkategorie anlegen </a><br />
								<a href="/ehotel/zimmerkategorie/liste">Zimmerkategorien auflisten </a>
							</div>
							<div style="float:left;margin-left:20px">
								<a href="/ehotel/zimmer/anlegen">Zimmer anlegen </a><br />
								<a href="/ehotel/zimmer/liste">Zimmer auflisten </a>
							</div>
							<div style="float:left;margin-left:20px">
								<a href="/ehotel/zusatzservice/anlegen">ZusatzServices anlegen </a><br />
								<a href="/ehotel/zusatzservice/liste">ZusatzServices auflisten </a>
							</div>
							<div style="float:left;margin-left:20px">
								<a href="/ehotel/freie_zimmer_suche">Reservierungen anlegen</a><br />
								<a href="/ehotel/admin/reservierung/liste">Reservierungen auflisten</a><br />
							</div>
					</div>
				</div>
			</div>
			<div style="margin-bottom:20px"></div>
		</div>
		
	<div id="content">
		<div class="wrapper" style="font-size:10pt"> 