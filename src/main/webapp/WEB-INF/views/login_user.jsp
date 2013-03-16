<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header_small.jsp"/>

<script>
function Event_Key(event)
{  
  if(event.keyCode == 13)
  {
	document.getElementById('loginform').submit();
  }  
}</script>

			<div class="content">
				<div class="indent">
 					<h2>Willkommen to E-HotelY</h2>
					<div class="h31"> Loggen Sie sich ein mit Benutzernamen und Password</div>
					<div class="line-hor-lang" style="margin:10px 0px"></div>
					<c:if test="${not empty error}">
						Your login attempt was not successful, try again.<br /> Caused :
						${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
					</c:if>
					<form id="loginform" method="post" action="j_spring_security_check" onkeydown="Event_Key(event);">			
						<fieldset style="padding:10px">
						<div class="field">
							Benutzername<br/><br/><input type="text" id="j_username"  name="j_username" />
						</div>
						<div class="field">
							<br/>Password <br/><br/><input type="password" name="j_password"  /><br/>
							<p style="margin:10px 0px">Forgot your <a href="reset_pwd">password?</a></p>
						</div>
						<div style="float:left;">
							<div class="button">
								<span><span><a style="cursor:pointer;" onclick="document.getElementById('loginform').submit()">Einloggen</a></span></span>
							</div>
						</div><br/><br/><br/>
						<div class="field">New to E-HotelY?</div>
						<div style="float:left;">
							<div class="button">
								<span><span><a href="registrieren">Registrieren</a></span></span>
							</div>
						</div>
						</fieldset>
					</form>
				</div>
				</div>								      
				<div class="clear"></div>				

			</div>
		</div>
	</div>
	
<script>

document.body.onload = function(){
	new ElementMaxHeight();
	document.getElementById('j_username').focus();
    };
</script>

<jsp:include page="footer.jsp"/>

