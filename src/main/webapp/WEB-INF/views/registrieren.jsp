
<jsp:include page="header_small.jsp"/>
<div id="content">
		<div class="wrapper">
			<div class="aside maxheight">
			
<!-- box end -->
			</div>
			<div class="content">
				<div class="indent">
					<h2>Get Started with E-Hotely</h2>
					<div class="h31"> Please fill the cases below to help us serve you better</div>
					<div class="line-hor-lang"></div>
					
					
					
		<form method="POST" action=
<c:choose>
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
	</div><br/><br/><br/><br/><br/><br/><br/>
	

<jsp:include page="footer.jsp"/>




