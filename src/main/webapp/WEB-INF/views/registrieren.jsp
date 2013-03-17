<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header_small.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<script>
function Event_Key(event)
{  
  if(event.keyCode == 13)
  {
		document.getElementById('login-form').submit();
  }  
}
</script> 
<div class="content">
	<div class="indent">
		<h2>Get Started with E-Hotely</h2>
		<div class="h31">Bitte füllen Sie alle Felder aus</div>
		<form method="POST" id="registrieren" action=
			<c:choose>
		    	<c:when test="${modus=='create'}">"erzeugeGast"</c:when>
	    	  	<c:when test="${modus=='edit'}">"profilUpdate"</c:when>
			</c:choose> >
			<fieldset>
				<font color="#FF0000">${felderError}</font>			
				<label>Vorname:</label>
				<input type="text" name="vorname" value="${gast.vorname}" /><br/><br/>
				<label>Nachname:</label>
				<input type="text" name="nachname" value="${gast.nachname}" /><br/><br/>
				<font color="#FF0000">${emailError}</font>
				<label>E-Mail:</label><input type="text" name="email" value="${gast.email}" /><br/><br/>
				<font color="#FF0000">${benutzernameError}</font>
				<label>Benutzername:</label>
				<input <c:if test="${modus=='edit'}">readonly</c:if> type="text" name="benutzername" value="${gast.benutzername}" /><br/><br/>
				<font color="#FF0000">${passwordError}</font>
				<label>Password:</label>
				<input type="password" name="password" /><br/><br/>
				<label>Password best.:</label>
				<input type="password" name="password2" /><br/>
			</fieldset>
		</form>
			<div style="float:left;">
				<div class="button"><span><span><a style="cursor:pointer;" onclick="document.getElementById('registrieren').submit()"><c:if test="${modus=='edit'}">Speichern</c:if><c:if test="${modus=='create'}">Registrieren</c:if></a></span></span></div>
			</div>
	      
	      	<div class="clear"></div>								
				</div>
			</div>
			</div>			
		</div>
	</div>
				
<jsp:include page="footer.jsp"/>
