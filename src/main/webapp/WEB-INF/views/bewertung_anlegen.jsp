<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:include page="header_big.jsp"/>

<p><font color="#FF0000">${felderError}</font></p>
<form method="POST" action="../bewertung/erstellen">
	
	<input type="hidden" name="reservierung_id" value="${reservierung.id}"/>
<p>Bewertungspunkte: </p>
	<select name="punkte">		
		<option> 1 </option>  
		<option> 2 </option> 
		<option> 3 </option> 
		<option> 4 </option> 
		<option> 5 </option>            		
	</select><br></br>
<p>Geben sie einen Bewertungstext ein: </p>
<p><textarea name="bw_text"  cols="50" rows="10"></textarea></p>
<input type="submit" value="Service anlegen"/>  
</form>

<jsp:include page="footer.jsp"/>

