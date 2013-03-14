<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header_admin.jsp"/>

<p><font color="#FF0000">${felderError}</font></p>
<form id="anlegen" method="POST" action=
<c:choose>
      <c:when test="${modus=='create'}">"../zimmer/erstellen"</c:when>
      <c:when test="${modus=='edit'}">"../../zimmer/aendern"</c:when>
</c:choose>
>
<input type="hidden" name="id" value="${zimmer.id}"/>
  <fieldset>
 	<legend>Zusatzservice anlegen</legend>
   	<label>ZimmerNr:</label>
   	<input type="text" name="zimmerNr" value="${zimmer.zimmerNr}"/><br><br>
   	<label>ZimmerTyp:</label>
   	<select name="zk_id">
			<c:forEach var="zk" items="${zimmerkategorien}">
				<option <c:if test="${zimmer.zimmerkategorie.id == zk.id}">selected</c:if> value="${zk.id}">${zk.zimmertyp}</option>        
      		</c:forEach>
  	</select><br><br>
	<div class="button" style="text-align:left"><span><span style="text-align:center"><a class="form-link" onclick="document.getElementById('anlegen').submit()">anlegen</a></span></span></div>  	
  </fieldset>
   
</form>

<jsp:include page="footer.jsp"/>
