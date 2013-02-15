<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Nachricht</title>
</head>
<body>
<h1>
	Hello ehotel!  
</h1>

<P>${meldung}</P>
<p>hier klicken für 	<a href="<c:url value="/j_spring_security_logout" />" > Logout</a> <p>
</body>
</html>
