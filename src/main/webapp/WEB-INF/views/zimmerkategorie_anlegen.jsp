<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
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
      <c:when test="${modus=='create'}">"zimmerkategorie_erstellen"</c:when>
      <c:when test="${modus=='edit'}">"../zimmerkategorie_aendern"</c:when>
</c:choose>
>

<input type="hidden" name="id" value="${zk.id}"/>
 <table>
  <tr>
   <td>Zimmertyp:</td>
   <td><input type="text" name="zimmertyp" value="${zk.zimmertyp}"/></td>
  </tr>
  <tr>
   <td>Preis:</td>
   <td><input type="text" name="preis" value="${zk.preis}"/></td>
  </tr>
  </table>
 <input type="submit"/>
</form>

</body>
</html>
