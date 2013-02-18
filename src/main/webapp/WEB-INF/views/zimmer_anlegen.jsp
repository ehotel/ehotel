<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Zimmerkategorie Anlegen</title>
</head>
<body>
<h1>
	Hello ehotel!  
</h1>
<p><font color="#FF0000">${felderError}</font></p>
<form method="POST" action=
<c:choose>
      <c:when test="${modus=='create'}">"zimmererstellen"</c:when>
      <c:when test="${modus=='edit'}">"../zimmer_aendern"</c:when>
</c:choose>
>
<input type="hidden" name="id" value="${zimmer.id}"/>
 <table>
  <tr>
   <td>ZimmerNr:</td>
   <td><input type="text" name="zimmerNr" value="${zimmer.zimmerNr}"/></td>
  </tr>
  <tr>
   <td>ZimmerTyp:</td>
   <td><select name="zk_id">
			<c:forEach var="zk" items="${zimmerkategorien}">
				<option <c:if test="${zimmer.zimmerkategorie.id == zk.id}">selected</c:if> value="${zk.id}">${zk.zimmertyp}</option>        
      		</c:forEach>
  		</select>
   </td>
  </tr>
  </table>
 <input type="submit"/>
</form>

</body>
</html>
