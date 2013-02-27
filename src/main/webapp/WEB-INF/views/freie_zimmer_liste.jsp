<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Freie Zimmer Liste</title>
</head>
<body>
<h1>
	Hello ehotel!  
</h1>
<p><font color="#FF0000">${felderError}</font></p>
 <table border="1">
   <tr>
   <td>ZimmerTyp</td>
   <td>ZimmerPreis</td>
   <td>reservieren</td>
  </tr>
  <tr>
  <c:if test="${empty zimmer}">
    <td colspan="3">Keine freien Zimmer gefunden</td>
  </c:if>
  <c:if test="${not empty zimmer}">
  	<form action="reservieren" method="POST">
	  	<td>${zimmer.zimmerkategorie.zimmertyp}</td>
		<td>${zimmer.zimmerkategorie.preis}</td>
		<td><input type="submit" value="reservieren"/></td>
		<input type="hidden" name="anreise" value="${anreise}"/>
		<input type="hidden" name="abreise" value="${abreise}"/>
		<input type="hidden" name="zimmer_id" value="${zimmer.id}"/>
	</form>	    
  </c:if>  
  </tr>
  </table>
</body>
</html>
