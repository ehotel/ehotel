<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header_small.jsp"/>

<script>
function Event_Key(event)
{  
  if(event.keyCode == 13)
  {
	document.getElementById('login-form').submit()
  }  
}</script>

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
				<input type="text" name="vorname" value="${gast.vorname}" /><br/>
				<label>Nachname:</label>
				<input type="text" name="nachname" value="${gast.vorname}" /><br/>
				<font color="#FF0000">${emailError}</font>
				<label>E-Mail:</label><input type="text" name="email" size=40 maxlength=40 value="${gast.email}" /><br/>
				<font color="#FF0000">${benutzernameError}</font>
				<label>Benutzername:</label>
				<input type="text" name="benutzername" value="${gast.benutzername}" size=40 maxlength=40 /><br/>
				<font color="#FF0000">${passwordError}</font>
				<label>Passoword:</label>
				<input type="password" name="password" size=40 maxlength=40 /><br/>
				<label>Password wiederholen:</label>
				<input type="password" name="password2" size=40 maxlength=40 /><br/>
			</fieldset>
			<div style="float:left;">
				<div class="button"><span><span><a style="cursor:pointer;" onclick="document.getElementById('registrieren').submit()">Registrieren</a></span></span></div>
			</div>
		</form>
	</div>
</div>								      


			<div class="clear"></div>				
				</div>
			</div>
		</div>
	</div>	
	
<jsp:include page="footer.jsp"/>
