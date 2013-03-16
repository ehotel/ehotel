<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page="header_big.jsp"/>

<script type="text/javascript">
	document.getElementById("booking").setAttribute("class", "current");
</script>
<div id="content">
<p><font color="#FF0000">${felderError}</font></p>
 <table border="1">
   <tr>
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
			<td>${reservierung.zimmer.zimmerkategorie.zimmertyp}</td>
			<td><fmt:formatDate value="${start}" pattern="dd.MM.yyyy" /></td>
			<td><fmt:formatDate value="${ende}" pattern="dd.MM.yyyy" /></td>
			<td>${reservierung.status}</td>
			<td><form id="stornieren${reservierung.id}" action="../reservierung/stornieren/${reservierung.id}" method="POST">
				<c:if test="${reservierung.status=='Aktiv'}">
					<a class="form-link" onclick="document.getElementById('stornieren${reservierung.id}').submit()">stornieren</a>
				</c:if></form>
			</td>
			<td><form id="details${reservierung.id}" action="../reservierung/details/${reservierung.id}" method="POST">
			<a class="form-link" onclick="document.getElementById('details${reservierung.id}').submit()">Details</a>
			</form></td>
			<td><form id="extra_service${reservierung.id}" action="../freie_services_suche_extra" method="POST">
				<c:if test="${reservierung.status=='Aktiv'}">
					<input type="hidden" name="reservierung_id" value="${reservierung.id}" />
					<a class="form-link" onclick="document.getElementById('extra_service${reservierung.id}').submit()">Service buchen</a>
				</c:if>
				</form>
			</td>
		</tr>
	</c:forEach>  
  </table>
</div>
<jsp:include page="footer.jsp"/>

