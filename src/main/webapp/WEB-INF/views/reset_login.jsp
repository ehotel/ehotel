<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Password zurücksetzen</title>
</head>
<body>
<h1>
	Hello ehotel!
</h1>
<p><font color="#FF0000">${felderError}</font></p>
<p>Sie haben ihr Password vergessen? Einfach Username eintragen und abschicken!</p>
<form method="POST" action="reset">
 <table>
  <tr>
   <td>Username:</td>
   <td><input type="text" name="username"/></td>
  </tr>
  </table>
 <input type="submit" value="Password zuruecksetzen"/>
</form>
</body>
</html>
