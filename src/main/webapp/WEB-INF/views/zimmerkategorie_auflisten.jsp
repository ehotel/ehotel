<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Zimmerkategorien auflisten</title>
</head>
<body>
<h1>
	Hello ehotel!  
</h1>
<p><font color="#FF0000">${felderError}</font></p>
 <table border="1">
   <tr>
   <td>ZimmerTyp</td>
   <td>ZimmerPreis</td>
   <td>Edit</td>
   <td>Delete</td>
  </tr>
	<c:forEach var="zk" items="${zimmerkategorieliste}">
	  	<tr>
			<td>${zk.zimmertyp}</td>
			<td>${zk.preis}</td>
			<td><a href="./zimmerkategorie_editieren/${zk.id}">edit</a></td>
			<td><a href="./zimmerkategorie_loeschen/${zk.id}">delete</a></td>			
		</tr>        
	</c:forEach>  
  </table>

</body>
</html>
