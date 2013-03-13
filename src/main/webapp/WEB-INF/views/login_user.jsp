<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="header_small.jsp"/>

			<div class="content">
				<div class="indent">
					<h2>Welcome to E-HotelY</h2>
					<div class="h31"> Sign in with your E-Hotely userID und password</div>
					<div class="h31"> </div>
					<div class="line-hor-lang"></div>
					<c:if test="${not empty error}">
						Your login attempt was not successful, try again.<br /> Caused :
						${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
					</c:if>
						

					<form id="login-form" method="post" action="j_spring_security_check" >			
						<fieldset>
						<div class="field">UserID<br/><br/>
								<input type="text"  name="j_username" /></div>
								
							<div class="field"><br/>Password <br/><br/>
							<input type="password" name="j_password"  /></div>
						</fieldset>			
						Forgot your <a href="reset_pwd">password</a>?
				<div style="float:left;">
						<br><div class="button"><span><span><a onclick="document.getElementById('login-form').submit()">Sign in </a></span></span></div>
					</div><br><br><br>
					<div class="field">New to E-HotelY?</div><br>
					<div style="float:left;">
						<div class="button"><span><span><a href="registrieren" onclick="document.getElementById('registration-form').submit()">Register</a></span></span></div>
					</div>
				</div>
					</form>			      
					<div class="clear"></div>
				
				</div>
			</div>
		</div>
	</div>
<jsp:include page="footer.jsp"/>
