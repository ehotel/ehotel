<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page="header_admin.jsp"/>

<p><font color="#FF0000">${felderError}</font></p>
 <table border="1">
   <tr>
   <td>Id</td>
   <td>Gast</td>
   <td>Zimmertyp</td>
   <td>Startdatum</td>
   <td>Enddatum</td>
   <td>Status</td>
   <td>Stornieren</td>
   <td>Details</td>
   <td>Zusatzservice</td>
   <td>Loeschen</td>
  </tr>
	<c:forEach var="reservierung" items="${reservierungsliste}">
		<jsp:useBean id="start" class="java.util.Date" />
		<jsp:setProperty name="start" property="time" value="${reservierung.startdatum}" />
		<jsp:useBean id="ende" class="java.util.Date" />
		<jsp:setProperty name="ende" property="time" value="${reservierung.enddatum}" />
	  	<tr>
			<td>${reservierung.id}</td>
			<td>${reservierung.gast.benutzername}</td>
			<td>${reservierung.zimmer.zimmerkategorie.zimmertyp}</td>
			<td><fmt:formatDate value="${start}" pattern="dd.MM.yyyy" /></td>
			<td><fmt:formatDate value="${ende}" pattern="dd.MM.yyyy" /></td>
			<td>${reservierung.status}</td>
			<td><form action="../../admin/reservierung/stornieren/${reservierung.id}" method="POST">
			<input type="submit" value="Stornieren"/></form>
			</td>
			<td><form action="../../reservierung/details/${reservierung.id}" method="POST">
			<input type="submit" value="Details"/></form>
			</td>
			<td><form action="../../freie_services_suche_extra" method="POST">
				<c:if test="${reservierung.status=='Aktiv'}">
					<input type="hidden" name="reservierung_id" value="${reservierung.id}" />
					<input type="submit" value="Service buchen"/>
				</c:if>
				</form>
			</td>
			<td><form action="../../reservierung/loeschen/${reservierung.id}" method="POST">
			<input type="submit" value="Loeschen"/></form>
			</td>
		</tr>
	</c:forEach>  
  </table>

<jsp:include page="footer.jsp"/>

