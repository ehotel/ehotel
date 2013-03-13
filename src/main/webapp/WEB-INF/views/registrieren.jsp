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
				<input type="text" name="vorname" value="${gast.vorname}" /><br/>
				<label>Nachname:</label>
				<input type="text" name="nachname" value="${gast.vorname}" /><br/>
				<font color="#FF0000">${emailError}</font>
				<label>E-Mail:</label><input type="text" name="email" value="${gast.email}" /><br/>
				<font color="#FF0000">${benutzernameError}</font>
				<label>Benutzername:</label>
				<input <c:if test="${modus=='edit'}">readonly</c:if> type="text" name="benutzername" value="${gast.benutzername}" /><br/>
				<font color="#FF0000">${passwordError}</font>
				<label>Passoword:</label>
				<input type="password" name="password" /><br/>
				<label>Password best.:</label>
				<input type="password" name="password2" /><br/>
			</fieldset>
			<div style="float:left;">
				<div class="button"><span><span><a style="cursor:pointer;" onclick="document.getElementById('registrieren').submit()"><c:if test="${modus=='edit'}">Speichern</c:if><c:if test="${modus=='create'}">Registrieren</c:if></a></span></span></div>
			</div>
			<div class="content">
				<div class="indent">
					<h2>Get Started with E-Hotely</h2>
					<div class="h31"> Please fill the cases below to help us serve you better</div>
					<div class="line-hor-lang"></div>
					
					
					
		<form method="POST" action= <c:choose>
      <c:when test="${modus=='create'}">"erzeugeGast"</c:when>
      <c:when test="${modus=='edit'}">"profilUpdate"</c:when>
</c:choose>
>
						<fieldset>
							<div class="field">First Name <br/><br/>
								<input type="text"  name="vorname" value="${gast.vorname}"/></div>
								
							<div class="field"><br/>Last Name  <br/><br/>
								<input type="text" name="nachname" value="${gast.vorname}" /></div>
						<font color="#FF0000">${emailError}</font>
							<div class="field"><br/>Email address <br/><br/>
								<input type="text" name="email" size=40 maxlength=40 value="${gast.email}"/></div>
					<font color="#FF0000">${benutzernameError}</font>
							<div class="field"><br/>E-HotelY user ID<br/><br/>
								<input type="text" name="benutzername" value="${gast.benutzername}" size=40 maxlength=40/></div>
						<font color="#FF0000">${passwordError}</font>
							<div class="field"><br/>Password<br/><br/>
								<input type="text" name="password1" size=40 maxlength=40/></div>
							<div class="field"><br/>Confirm Password<br/><br/>
								<input type="text" name="password2" size=40 maxlength=40/></div>
						</fieldset>			
						
				<div style="float:right;">
						<div class="button"><span><span><a onclick="document.getElementById('registration-form').submit()">Submit</a></span></span></div>
					</div>
				</div>
					</form>			      
					<div class="clear"></div>
								
				</div>
			</div>
		</div>
	</div>
				
<jsp:include page="footer.jsp"/>
