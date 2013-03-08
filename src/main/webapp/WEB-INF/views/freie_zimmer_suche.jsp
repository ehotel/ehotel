<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Freie Zimmer Suche</title>
	
    <link href="resources/jquery-ui-1.10.1.custom.css" rel="stylesheet" type="text/css" />
    <script src="resources/jquery-1.9.1.js"></script>
    <script src="resources/jquery-ui-1.10.1.custom.js"></script>
<!--     <script src="js/jquery-ui-1.10.1.custom.min.js"></script>
 -->    <script> 
      $(document).ready(function(){ 
		   	$('#anreise').datepicker({
		   		dateFormat: 'dd.mm.yy',
		   		minDate: 0,
		   	 	onClose: function( selectedDate ) {
			   		$( "#abreise" ).datepicker( "option", "minDate", selectedDate );
		   		}
		   	});
		   	
		       $('#abreise').datepicker({
		       	dateFormat: 'dd.mm.yy',
		        minDate: 1,
		   	 	onClose: function( selectedDate ) {
			   		$( "#anreise" ).datepicker( "option", "maxDate", selectedDate );
		   		}
		    });
        }); 
    </script> 
</head><body>
<h1>
	Hello ehotel!  
</h1>
<p><font color="#FF0000">${felderError}</font></p>
<form method="POST" action="zimmer_suche">
 <table>
 <tr>
   <td>ZimmerTyp:</td>
   <td><select name="zk_typ">
   			<option value="egal">-- egal --</option>
			<c:forEach var="zk" items="${zimmerkategorien}">
				<option value="${zk.zimmertyp}">${zk.zimmertyp}</option>        
      		</c:forEach>
  		</select>
   </td>
 </tr>
 <tr>
 <td>Anreise:</td>
 <td><input type="text" name="anreise" id="anreise"/></td>
 </tr>
 <tr>
 <td>Abreise:</td>
 <td><input type="text" name="abreise" id="abreise"/></td>
 </tr>
 </table>
 <input type="submit"/>
</form>

</body>
</html>
