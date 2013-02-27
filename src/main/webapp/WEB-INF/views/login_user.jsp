<jsp:include page="header.jsp"/>

<!-- BODY -->			
			<div class="content">
				<div class="indent">
					<h2>Welcome to E-HotelY</h2>
					<div class="h31"> Sign in with your E-Hotely userID und password</div>
					<div class="h31"> </div>
					<div class="line-hor-lang"></div>
						
<!-- HIER KOMMEN DIE JSP VERKNUEPFUNGEN -->		
					<form action="beispielJSP.jsp" id="login-form" method="get">			
						<fieldset>
							<div class="field">UserID<br/><br/>
								<input type="text"  name="userid" /></div>
								
							<div class="field"><br/>Password <br/><br/>
							<input type="text" name="password"  /></div>
						</fieldset>			
						Forgot your <a href="passwordreset">password</a>?
				<div style="float:left;">
						<div class="button"><span><span><a href="index.html" onclick="document.getElementById('registration-form').submit()">Sign in</a></span></span></div>
					</div><br><br><br>
					<div class="field">New to E-HotelY?</div>><br>
					<div style="float:left;">
						<div class="button"><span><span><a href="registration" onclick="document.getElementById('registration-form').submit()">Register</a></span></span></div>
					</div>
				</div>
					</form>			      
					<div class="clear"></div>
								
				</div>
				
<jsp:include page="footer.jsp"/>