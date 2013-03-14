<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:include page="header_big.jsp"/>

<p><font color="#FF0000">${felderError}</font></p>
<fieldset>
<legend>Bewertung abgeben</legend>
<form id="bewerten" method="POST" action="../bewertung/erstellen">
	
	<input type="hidden" name="reservierung_id" value="${reservierung_id}"/>
<p>Bewertungspunkte: </p>
	<select name="punkte">
		<option value="5"> 5 Sterne - klasse</option>
		<option value="4"> 4 Sterne - gut</option>
		<option value="3"> 3 Sterne - ganz angenehm</option>
		<option value="2"> 2 Sterne - ausreichend</option>		
		<option value="1"> 1 Stern - schlecht</option>           		
	</select><br/><br/>
	
<!-- <input type="radio" name="group1" value="1">schlecht<br> -->
<!-- <input type="radio" name="group1" value="2">ausreichend<br> -->
<!-- <input type="radio" name="group1" value="3">ganz angenehm<br> -->
<!-- <input type="radio" name="group1" value="4">gut<br> -->
<!-- <input type="radio" name="group1" value="5">klasse<br> -->
	
<%-- <p>hier id: ${reservierung_id}</p> --%>
<p>Geben sie einen Bewertungstext ein: </p>
<p><textarea name="bw_text"  cols="50" rows="10"></textarea></p>
<div class="button" style="text-align:left"><span><span style="text-align:center"><a class="form-link" onclick="document.getElementById('bewerten').submit()">bewerten</a></span></span></div>  
</form>
</fieldset>

<jsp:include page="footer.jsp"/>

