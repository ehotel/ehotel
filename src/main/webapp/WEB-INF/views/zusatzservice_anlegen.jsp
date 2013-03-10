<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="header_admin.jsp"/>

<p><font color="#FF0000">${felderError}</font></p>
<form method="POST" action=
<c:choose>
      <c:when test="${modus=='create'}">"../zusatzservice/erstellen"</c:when>
      <c:when test="${modus=='edit'}">"../../zusatzservice/aendern"</c:when>
</c:choose>
>
<input type="hidden" name="id" value="${zs.id}"/>
 <fieldset>
 	<legend>Zusatzservice anlegen</legend>
   	<label>Name:</label>
   	<input type="text" name="name" value="${zs.name}"/><br><br>
   	<label>Preis:</label>
   	<input type="text" name="preis" value="${zs.preis}"/><br><br>
   	<label>Anzahl:</label>
   	<input type="text" name="anzahl" value="${zs.anzahl}"/><br><br>   	
   <input type="submit" value="Service anlegen"/>
  </fieldset>

</form>

<jsp:include page="footer.jsp"/>
