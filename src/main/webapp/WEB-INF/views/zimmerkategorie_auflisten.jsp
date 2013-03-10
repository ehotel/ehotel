<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="header_admin.jsp"/>

<p><font color="#FF0000">${felderError}</font></p>
 <table>
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
			<td><a href="./../zimmerkategorie/editieren/${zk.id}">edit</a></td>
			<td><a href="./../zimmerkategorie/loeschen/${zk.id}">delete</a></td>			
		</tr>        
	</c:forEach>  
  </table>
  
<jsp:include page="footer.jsp"/>

