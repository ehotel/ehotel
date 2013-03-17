<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header_big.jsp"/>

<script type="text/javascript">
	document.getElementById("testimonials").setAttribute("class", "current");
</script>

<div id="content">
<div class="indent">
			<h2>Kundenbewertungen</h2>
			<ul class="list4">
			
				<c:forEach var="bewertung" items="${bewertungsliste}">
				<li>
					<p>
						<b>${bewertung.gast.vorname} ${bewertung.gast.nachname}</b><br/>
						<b>${bewertung.reservierung.zimmer.zimmerkategorie.zimmertyp}</b><br/>
						<c:forEach var="i" begin="1" end="${bewertung.bewertungspunkte}" >
							<img src="/ehotel/resources/images/stern_rot.png" height="20px" />		
						</c:forEach>
						<c:forEach var="i" begin="${bewertung.bewertungspunkte}" end="4" >
							<img src="/ehotel/resources/images/stern_schwarz.png" height="20px" />		
						</c:forEach><br/>
					</p>						
					${bewertung.text} <br/><br/>
				</li>
				</c:forEach>			
			
				
			</ul><br/>
		</div>
</div>
		
<jsp:include page="footer.jsp"/>