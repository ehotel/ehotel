<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
 <form action="reservieren" method="POST">
 <table border="1">
   <tr>
   <td>ZimmerTyp</td>
   <td>ZimmerPreis</td>
   <sec:authorize ifAnyGranted="ROLE_ADMIN">
    	<td>Username</td>
    </sec:authorize>
   <td>reservieren</td>
  </tr>
  <tr>
  <c:if test="${empty zimmer}">
    <td colspan="3">Keine freien Zimmer gefunden</td>
  </c:if>
  <c:if test="${not empty zimmer}"> 	
	  	<td>${zimmer.zimmerkategorie.zimmertyp}</td>
		<td>${zimmer.zimmerkategorie.preis}</td>
		<sec:authorize ifAnyGranted="ROLE_ADMIN">
    		<td><select name="user">
					<c:forEach var="gast" items="${gaesteliste}">
						<option value="${gast.benutzername}">${gast.benutzername}</option>        
		      		</c:forEach>
		  		</select>
	  		</td>
    	</sec:authorize>
		<td>
		<input type="submit" value="reservieren"/>
		<input type="hidden" name="anreise" value="${anreise}"/>
		<input type="hidden" name="abreise" value="${abreise}"/>
		<input type="hidden" name="zimmer_id" value="${zimmer.id}"/>
		</td>
  </c:if>  
  </tr>
  </table>
  </form>
</body>
</html>
