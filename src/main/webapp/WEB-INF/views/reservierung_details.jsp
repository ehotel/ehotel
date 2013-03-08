<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page session="false" %>

<html>
<head>
	<title>Reservierung Details</title>
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
  </tr>
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
			<td><form action="../../reservierung/stornieren/${reservierung.id}" method="POST">
			<input type="submit" value="stornieren"/></form>
			</td>
		</tr>
  </table>
  
  <br>
  <p>ZusatzServices</p>
  
  <c:if test="${not empty reservierungserviceliste}">  
  <table border="1">
   <tr>
   <td>ZusatzServiceId</td>
   <td>Name</td>
   <td>Preis</td>
   <td>Startdatum</td>
   <td>Enddatum</td>
   <td>L�schen</td>
  </tr>
	<c:forEach var="service" items="${reservierungserviceliste}">
		<jsp:useBean id="start_service" class="java.util.Date" />
		<jsp:setProperty name="start_service" property="time" value="${service.startdatum}" />
		<jsp:useBean id="ende_service" class="java.util.Date" />
		<jsp:setProperty name="ende_service" property="time" value="${service.enddatum}" />
	  	<tr>
			<td>${service.id}</td>
			<td>${service.zusatzService.name}</td>
			<td>${service.zusatzService.preis}</td>
			<td><fmt:formatDate value="${start_service}" pattern="dd.MM.yyyy" /></td>
			<td><fmt:formatDate value="${ende_service}" pattern="dd.MM.yyyy" /></td>
			<td><form action="../reservierungservice/loeschen" method="POST">
			<input type="hidden" name="service_id" value="${service.id}" />
			<input type="submit" value="l�schen"/></form>
			</td>
		</tr>
	</c:forEach>  
  </table>
  </c:if>
  <c:if test="${empty reservierungserviceliste}">
  <p> Keine ZusatzServices gefunden </p>
  </c:if>

</body>
</html>