<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="header_admin.jsp"/>

<p><font color="#FF0000">${felderError}</font></p>
 <table border="1">
   <tr>
   <td>Service-Name</td>
   <td>Preis</td>
   <td>Anzahl</td>
   <td>Edit</td>
   <td>Delete</td>
  </tr>
	<c:forEach var="zs" items="${zsliste}">
	  	<tr>
			<td>${zs.name}</td>
			<td>${zs.preis}</td>
			<td>${zs.anzahl}</td>
			<td><a href="./../zusatzservice/editieren/${zs.id}">edit</a></td>
			<td><a href="./../zusatzservice/loeschen/${zs.id}">delete</a></td>			
		</tr>        
	</c:forEach>  
  </table>

<jsp:include page="footer.jsp"/>

