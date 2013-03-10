<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Reservierung aendern</title>
	
  <link href="../../resources/jquery-ui-1.10.1.custom.min.css" rel="stylesheet" type="text/css" />
    <script src="../../resources/jquery-1.9.1.js"></script>
    <script src="../../resources/jquery-ui-1.10.1.custom.js"></script>
<!--     <script src="js/jquery-ui-1.10.1.custom.min.js"></script>
 -->    <script> 
      $(document).ready(function(){ 
    	$('#anreise').datepicker({ dateFormat: 'dd.mm.yy', minDate: 0});
        $('#abreise').datepicker({ dateFormat: 'dd.mm.yy', minDate: 1});
        }); 
    </script> 
</head>

<body>
<h1>
	Hello ehotel!  
</h1>

<p><font color="#FF0000">${felderError}</font></p>
<form method="POST" action="../../reservierung/update">
 <table>
 <tr>
   <td>ZimmerTyp:</td>
   <td><select name="zk_typ">
			<c:forEach var="zk" items="${zimmerkategorieliste}">				
				<option <c:if test="${zk.zimmertyp == zimmerkategorie}">selected</c:if> value="${zk.zimmertyp}">${zk.zimmertyp}</option>        
      		</c:forEach>
  		</select>
   </td>
 </tr>
 <tr>
 <td>Anreise:</td>
 <td><input type="text" name="startdatum" id="anreise" value="${min}"/></td>
 </tr>
 <tr>
 <td>Abreise:</td>
 <td><input type="text" name="enddatum" id="abreise" value="${max}"/></td>
 </tr>
 </table>
 <input type="hidden" name="id" value="${reservierung.id}" />
 <input type="submit" value="Reservierung aendern"/>
</form>

<br>
  <p>ZusatzServices</p>
  <form action="../../freie_services_suche_extra" method="POST">
				<c:if test="${reservierung.status=='Aktiv'}">
					<input type="hidden" name="reservierung_id" value="${reservierung.id}" />
					<input type="submit" value="service buchen"/>
				</c:if>
  </form>
  
  <c:if test="${not empty reservierungserviceliste}">  
  <table border="1">
   <tr>
   <td>ZusatzServiceId</td>
   <td>Name</td>
   <td>Preis</td>
   <td>Startdatum</td>
   <td>Enddatum</td>
   <td>Löschen</td>
   <td>Ändern</td>
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
			<input type="submit" value="löschen"/></form>
			</td>
			<td><form action="../reservierungservice/aendern" method="POST">
			<input type="hidden" name="service_id" value="${service.id}" />
			<input type="submit" value="ändern"/></form>
			</td>
		</tr>
	</c:forEach>  
  </table>
  </c:if>
  <c:if test="${empty reservierungserviceliste}">
  <p> Keine ZusatzServices gebucht </p>
  </c:if>

</body>
</html>