<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="header_admin.jsp"/>

<p><font color="#FF0000">${felderError}</font></p>
<form id="anlegen" method="POST" action=
<c:choose>
      <c:when test="${modus=='create'}">"../zimmerkategorie/erstellen"</c:when>
      <c:when test="${modus=='edit'}">"../../zimmerkategorie/aendern"</c:when>
</c:choose>
>

<input type="hidden" name="id" value="${zk.id}"/>
 <fieldset>
 	<legend>Zimmerkategorie anlegen</legend>
   	<label>Zimmertyp:</label>
   	<input type="text" name="zimmertyp" value="${zk.zimmertyp}"/><br><br>
   	<label>Preis:</label>
   	<input type="text" name="preis" value="${zk.preis}"/><br><br>
	<div class="button" style="text-align:left"><span><span style="text-align:center"><a class="form-link" onclick="document.getElementById('anlegen').submit()">anlegen</a></span></span></div>
  </fieldset>

</form>

<jsp:include page="footer.jsp"/>

