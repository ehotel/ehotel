<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Login</title>
</head>
<body>
<h1>
	Hello ehotel!  
</h1>

<form method="POST" action="erzeugeGast">
 <table>
  <tr>
   <td>Benutzername:</td>
   <td><input type="text" name="vorname"/></td>
  </tr>
  <tr>
   <td>Password:</td>
   <td><input type="text" name="nachname"/></td>
  </tr>
 </table>
 <input type="submit"/>
</form>

</body>
</html>
