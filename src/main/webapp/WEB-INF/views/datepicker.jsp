<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Datepicker</title>
	
    <link href="resources/jquery-ui-1.10.1.custom.min.css" rel="stylesheet" type="text/css" />
    <script src="resources/jquery-1.9.1.js"></script>
    <script src="resources/jquery-ui-1.10.1.custom.js"></script>
<!--     <script src="js/jquery-ui-1.10.1.custom.min.js"></script>
 -->    <script> 
      $(document).ready(function(){ 
        $('#vonDate').datepicker(); 
      }); 
    </script> 
</head>
<body>
<h1>
	Hello ehotel!  
</h1>

<P>Hier wird der datepicker getestet</P>

<form method="POST" action="erzeugeGast">
<input type="text" name="datum" id="vonDate"/></form>
</body>
</html>
