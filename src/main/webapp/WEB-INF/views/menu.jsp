<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<jsp:include page="header_big.jsp"/>

<sec:authorize ifAnyGranted="ROLE_ADMIN">
<c:redirect url="/admin" />
</sec:authorize>

<div style="font-size:12pt" id="content">
<br/>
	<ul>
		<li><a href="/ehotel/reservierung/liste">Reservierung auflisten</a><br/><br/></li>
		<li><a href="/ehotel/freie_zimmer_suche">Zimmer suchen</a><br/><br/></li>
		<li><a href="/ehotel/profil">Profil pflegen</a><br/><br/></li>
	</ul>

</div>

<jsp:include page="footer.jsp"/>

