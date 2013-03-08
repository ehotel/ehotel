<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page session="false" %>

<html>
<head>
	<title>Reservierung auflisten</title>
</head>
<body>
<h1>
	Hello ehotel!  
</h1>
<p><font color="#FF0000">${felderError}</font></p>
 <table border="1">
   <tr>
   <td>ReservierungsId</td>
   <td>Zimmertyp</td>
   <td>Startdatum</td>
   <td>Enddatum</td>
   <td>Status</td>
   <td>Stornieren</td>
   <td>Details</td>
   <td>Zusatzservice</td>   
  </tr>
	<c:forEach var="reservierung" items="${reservierungsliste}">
		<jsp:useBean id="start" class="java.util.Date" />
		<jsp:setProperty name="start" property="time" value="${reservierung.startdatum}" />
		<jsp:useBean id="ende" class="java.util.Date" />
		<jsp:setProperty name="ende" property="time" value="${reservierung.enddatum}" />
	  	<tr>
			<td>${reservierung.id}</td>
			<td>${reservierung.zimmer.zimmerkategorie.zimmertyp}</td>
			<td><fmt:formatDate value="${start}" pattern="dd.MM.yyyy" /></td>
			<td><fmt:formatDate value="${ende}" pattern="dd.MM.yyyy" /></td>
			<td>${reservierung.status}</td>
			<td><form action="../reservierung/stornieren/${reservierung.id}" method="POST">
			<input type="submit" value="stornieren"/></form>
			</td>
			<td><form action="../reservierung/details/${reservierung.id}" method="POST">
			<input type="submit" value="details"/></form>
			</td>
			<td><form action="../freie_services_suche_extra" method="POST">
			<input type="hidden" name="reservierung_id" value="${reservierung.id}" />
			<input type="submit" value="service buchen"/></form>
			</td>
		</tr>
	</c:forEach>  
  </table>

</body>
</html>