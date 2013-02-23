<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<html>
<head>
	<title>Zusatzservice Anlegen</title>
</head>
<body>
<h1>
	Hello ehotel!  
</h1>
<p><font color="#FF0000">${felderError}</font></p>
<form method="POST" action=
<c:choose>
      <c:when test="${modus=='create'}">"zusatzservice_erstellen"</c:when>
      <c:when test="${modus=='edit'}">"../zusatzservice_aendern"</c:when>
</c:choose>
>

<input type="hidden" name="id" value="${zs.id}"/>
 <table>
  <tr>
   <td>Name:</td>
   <td><input type="text" name="name" value="${zs.name}"/></td>
  </tr>
  <tr>
   <td>Preis:</td>
   <td><input type="text" name="preis" value="${zs.preis}"/></td>
  </tr>
  <tr>
   <td>Anzahl:</td>
   <td><input type="text" name="anzahl" value="${zs.anzahl}"/></td>
  </tr>  
  </table>
 <input type="submit"/>
</form>

</body>
</html>
