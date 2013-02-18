<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Zimmer auflisten</title>
</head>
<body>
<h1>
	Hello ehotel!  
</h1>
<p><font color="#FF0000">${felderError}</font></p>
 <table border="1">
   <tr>
   <td>ZimmerNr</td>
   <td>ZimmerTyp</td>
   <td>Edit</td>
   <td>Delete</td>
  </tr>
	<c:forEach var="zimmer" items="${zimmerliste}">
	  	<tr>
			<td>${zimmer.zimmerNr}</td>
			<td>${zimmer.zimmerkategorie.zimmertyp}</td>
			<td><a href="./zimmer_editieren/${zimmer.id}">edit</a></td>
			<td><a href="./zimmer_loeschen/${zimmer.id}">delete</a></td>
		</tr>
	</c:forEach>  
  </table>

</body>
</html>