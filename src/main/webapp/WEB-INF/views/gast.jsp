<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello ehotel!  
</h1>

<form method="POST" action="erzeugeGast">
 <table>
  <tr>
   <td>Vorname:</td>
   <td><input type="text" name="vorname"/></td>
  </tr>
  <tr>
   <td>Nachname:</td>
   <td><input type="text" name="nachname"/></td>
  </tr>
  <tr>
   <td>Benutzername:</td>
   <td><input type="text" name="benutzername"/></td>
  </tr>
 </table>
 <input type="submit"/>
</form>

</body>
</html>
