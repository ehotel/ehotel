<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="header_admin.jsp"/>

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
			<td><a href="../zimmer/editieren/${zimmer.id}">edit</a></td>
			<td><a href="../zimmer/loeschen/${zimmer.id}">delete</a></td>
		</tr>
	</c:forEach>  
  </table>

<jsp:include page="footer.jsp"/>

