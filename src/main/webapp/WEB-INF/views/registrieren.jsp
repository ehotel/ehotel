<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Registrieren</title>
</head>
<body>
<h1>
	Hello ehotel!  
</h1>
<p><font color="#FF0000">${felderError}</font></p>
<form method="POST" action=
<c:choose>
      <c:when test="${modus=='create'}">"erzeugeGast"</c:when>
      <c:when test="${modus=='edit'}">"profilUpdate"</c:when>
</c:choose>
>
 <table>
  <tr>
   <td>Vorname:</td>
   <td><input type="text" name="vorname" value="${gast.vorname}"/></td>
  </tr>
  <tr>
   <td>Nachname:</td>
   <td><input type="text" name="nachname" value="${gast.nachname}"/></td>
  </tr>
  <tr>
  <td><font color="#FF0000">${emailError}</font></td>
  </tr>
  <tr>
   <td>E-Mail:</td>
   <td><input type="text" name="email" value="${gast.email}"/></td>
  </tr>
    <tr>
  <td><font color="#FF0000">${benutzernameError}</font></td>
  </tr>
  <tr>
   <td>Benutzername:</td>
   <td><input type="text" name="benutzername" value="${gast.benutzername}" <c:if test="${modus=='edit'}">readonly</c:if>/></td>
  </tr>
  <tr>
  <td><font color="#FF0000">${passwordError}</font></td>
  </tr>
  <tr>
   <td>Password:</td>
   <td><input type="password" name="password"/></td>
  </tr>
  <tr>
   <td>Password wiederholen:</td>
   <td><input type="password" name="password2"/></td>
  </tr>
 </table>
 <input type="submit"/>
</form>

</body>
</html>
