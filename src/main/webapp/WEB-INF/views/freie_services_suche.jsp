<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Freie Service Suche</title>
	
    <link href="resources/jquery-ui-1.10.1.custom.min.css" rel="stylesheet" type="text/css" />
    <script src="resources/jquery-1.9.1.js"></script>
    <script src="resources/jquery-ui-1.10.1.custom.js"></script>
<!--     <script src="js/jquery-ui-1.10.1.custom.min.js"></script>
 -->    <script> 
      $(document).ready(function(){
    	     	        	  
    	$('#anreise').datepicker({ dateFormat: 'dd.mm.yy', minDate:  new Date("${min}"), maxDate: new Date("${max}")});
        $('#abreise').datepicker({ dateFormat: 'dd.mm.yy', minDate:  new Date("${min}"), maxDate: new Date("${max}")});
        
        
        });      
    </script> 
</head><body>
<h1>
	Hello ehotel!  
</h1>
<p><font color="#FF0000">${felderError}</font></p>
   <c:if test="${empty zusatzservices}">
    <p>Keine freien ZusatzServices gefunden</p>
  </c:if>
  <c:if test="${not empty zusatzservices}">
	<form method="POST" action="service_reservieren">
	<table>
	<tr>
	   <td>Service:</td>
	   <td><select name="service">
				<c:forEach var="z" items="${zusatzservices}">
					<option value="${z.id}">${z.name}</option>        
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
  </c:if>
</body>
</html>
