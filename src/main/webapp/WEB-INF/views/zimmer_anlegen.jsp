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
<form method="POST" action="zimmererstellen">
 <table>
  <tr>
   <td>ZimmerNr:</td>
   <td><input type="text" name="zimmerNr" value="${zimmernr}"/></td>
  </tr>
  <tr>
   <td>ZimmerTyp:</td>
   <td><select name="zk_id">
			<c:forEach var="zk" items="${zimmerkategorien}">
				<option value="${zk.id}">${zk.zimmertyp}</option>        
      		</c:forEach>
  		</select>
   </td>
  </tr>
  </table>
 <input type="submit"/>
</form>

</body>
</html>
