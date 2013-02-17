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
<form method="POST" action="zimmerkategorieerstellen">
 <table>
  <tr>
   <td>Zimmertyp:</td>
   <td><input type="text" name="zimmertyp" value="${zimmertyp}"/></td>
  </tr>
  <tr>
   <td>Preis:</td>
   <td><input type="text" name="preis" value="${preis}"/></td>
  </tr>
  </table>
 <input type="submit"/>
</form>

</body>
</html>
