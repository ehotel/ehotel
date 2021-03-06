<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<jsp:include page="header_big.jsp"/>

<script type="text/javascript">
	document.getElementById("booking").setAttribute("class", "current");
</script>
<div id="content">
<p><font color="#FF0000">${felderError}</font></p>
 <table border="1">
 	
 	<jsp:useBean id="start" class="java.util.Date" />
	<jsp:setProperty name="start" property="time" value="${reservierung.startdatum}" />
	<jsp:useBean id="ende" class="java.util.Date" />
	<jsp:setProperty name="ende" property="time" value="${reservierung.enddatum}" />
	
	<jsp:useBean id="heute" class="java.util.Date" />
	<jsp:setProperty name="heute" property="time" />
 
   <tr>
   <sec:authorize ifAnyGranted="ROLE_ADMIN">
   		<td>Username</td>
   </sec:authorize>
   <td>Zimmertyp</td>
   <td>Startdatum</td>
   <td>Enddatum</td>
   <td>Status</td>
   <td>Stornieren</td>
   <td>Aendern</td>
   <c:if test="${ende < heute}">
   		<td>Bewertung</td>
   </c:if>
  </tr>				
	  	<tr>
			<sec:authorize ifAnyGranted="ROLE_ADMIN">
   				<td>${reservierung.gast.benutzername}</td>
   			</sec:authorize>
			<td>${reservierung.zimmer.zimmerkategorie.zimmertyp}</td>
			<td><fmt:formatDate value="${start}" pattern="dd.MM.yyyy" /></td>
			<td><fmt:formatDate value="${ende}" pattern="dd.MM.yyyy" /></td>
			<td>${reservierung.status}</td>
			<td><form id="stornieren" action="../../reservierung/stornieren/${reservierung.id}" method="POST">
			<a class="form-link" onclick="document.getElementById('stornieren').submit()">stornieren</a>				
			</form>
			</td>
			<td>
			<c:if test="${reservierung.status=='Aktiv'}">
				<form id="aendern" action="../../reservierung/aendern/${reservierung.id}" method="POST">
					<a class="form-link" onclick="document.getElementById('aendern').submit()">aendern</a>
				</form>
			</c:if>
			</td>
			<c:if test="${ende < heute}">
			<td><form id="bewerten" action="../../bewertung/anlegen" method="POST">
				<input type="hidden" name="reservierung_id" value="${reservierung.id}" />
				
					<a class="form-link" onclick="document.getElementById('bewerten').submit()">Zimmer bewerten</a>						
				</form>
			</td>
			</c:if>
		</tr>
  </table>
  
  <br>
  <p>ZusatzServices</p>
  
  <c:if test="${not empty reservierungserviceliste}">  
  <table border="1">
   <tr>
   <td>Name</td>
   <td>Preis</td>
   <td>Startdatum</td>
   <td>Enddatum</td>
  </tr>
	<c:forEach var="service" items="${reservierungserviceliste}">
		<jsp:useBean id="start_service" class="java.util.Date" />
		<jsp:setProperty name="start_service" property="time" value="${service.startdatum}" />
		<jsp:useBean id="ende_service" class="java.util.Date" />
		<jsp:setProperty name="ende_service" property="time" value="${service.enddatum}" />
	  	<tr>
			<td>${service.zusatzService.name}</td>
			<td>${service.zusatzService.preis}</td>
			<td><fmt:formatDate value="${start_service}" pattern="dd.MM.yyyy" /></td>
			<td><fmt:formatDate value="${ende_service}" pattern="dd.MM.yyyy" /></td>
		</tr>
	</c:forEach>  
  </table>
  </c:if>
  <c:if test="${empty reservierungserviceliste}">
  <p> Keine ZusatzServices gefunden </p>
  </c:if>
</div>
<jsp:include page="footer.jsp"/>