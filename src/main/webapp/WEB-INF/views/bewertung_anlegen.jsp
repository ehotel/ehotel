<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header_admin.jsp"/>

<p><font color="#FF0000">${felderError}</font></p>
<form method="POST" action=bewertung/anlegen>
<input type="hidden" name="id" value="${reservierung.id}"/>

  <fieldset>
 	<legend>Reservierung anlegen</legend>
   	<label>Bewertungspunkte:</label>
   	   	<select name="bw_id">			
		<option> value=1 </option>  
		<option> value=2 </option> 
		<option> value=3 </option> 
		<option> value=4 </option> 
		<option> value=5 </option>            		
  	</select><br><br>
   <input type="submit" value="Service anlegen"/>
   <label>Bewertungstext:</label>
   <input type="text" name="bw_text" /><br><br>

  </fieldset>

   
</form>

<jsp:include page="footer.jsp"/>
