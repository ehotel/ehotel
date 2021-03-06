<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
<title>Login Page</title>
<style>
.errorblock {
	color: #ff0000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>
</head>
<body onload='document.f.j_username.focus();'>
	<h3>Login with Username and Password (Authentication with Database)</h3>

	<c:if test="${not empty error}">
		<div class="errorblock">
			Your login attempt was not successful, try again.<br /> Caused :
			${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
		</div>
	</c:if>

	<form name='f' action="<c:url value='j_spring_security_check' />"
		method='POST'>

		<table>
			<tr>
				<td>User:</td>
				<td><input type='text' name='j_username' value=''>
				</td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type='password' name='j_password' /></td>
			</tr>
			<tr>
				<td colspan='2'> Sie haben ihr Password vergessen? <a href="reset_pwd">hier zuruecksetzen</a></td>
			</tr>			
			<tr>
				<td colspan='2'><input name="submit" type="submit"
					value="submit" />
				</td>
			</tr>
			<tr>
				<td colspan='2'><input name="reset" type="reset" />
				</td>
			</tr>
		</table>

	</form>
    <sec:authorize ifAnyGranted="ROLE_USER">
    	Sie sind eingeloggt mit benutzernamen: <sec:authentication property="principal.username"/>
    </sec:authorize>
    <sec:authorize ifNotGranted="ROLE_USER">
        Sie sind ausgeloggt
    </sec:authorize>
    <sec:authorize ifAnyGranted="ROLE_ADMIN">
    	<br />Sie sind Admin
    </sec:authorize>
    
    <p>hier klicken f�r 	<a href="<c:url value="/j_spring_security_logout" />" > Logout</a> <p>
	
</body>
</html>