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
						<b>${bewertung.reservierung.zimmer.zimmerkategorie.zimmertyp} ${bewertung.gast.vorname} ${bewertung.gast.nachname}</b>
						<c:forEach var="i" begin="1" end="${bewertung.bewertungspunkte}" >
							<img src="/ehotel/resources/images/stern_rot.png" height="20px" />		
						</c:forEach>
						<c:forEach var="i" begin="${bewertung.bewertungspunkte}" end="4" >
							<img src="/ehotel/resources/images/stern_schwarz.png" height="20px" />		
						</c:forEach>						
					</p>						
					${bewertung.text} <br/><br/>
				</li>
				</c:forEach>			
			
<!-- 				<li><p><b>Matthew, London</b>
				<img src="/ehotel/resources/images/stern_rot.png" height="20px" />
				<img src="/ehotel/resources/images/stern_rot.png" height="20px" />
				<img src="/ehotel/resources/images/stern_rot.png" height="20px" />
				<img src="/ehotel/resources/images/stern_schwarz.png" height="20px" />
				<img src="/ehotel/resources/images/stern_schwarz.png" height="20px" />
				</p>																
				Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquast, qui dolorem ipsum.
				</li>
				<li><p><b>Matthew, London</b>
				<img src="/ehotel/resources/images/stern_rot.png" height="20px" />
				<img src="/ehotel/resources/images/stern_rot.png" height="20px" />
				<img src="/ehotel/resources/images/stern_rot.png" height="20px" />
				<img src="/ehotel/resources/images/stern_rot.png" height="20px" />
				<img src="/ehotel/resources/images/stern_rot.png" height="20px" />
				</p>At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore.</li>
				<li><p><b>Matthew, London</b>
				<img src="/ehotel/resources/images/stern_rot.png" height="20px" />
				<img src="/ehotel/resources/images/stern_rot.png" height="20px" />
				<img src="/ehotel/resources/images/stern_schwarz.png" height="20px" />
				<img src="/ehotel/resources/images/stern_schwarz.png" height="20px" />
				<img src="/ehotel/resources/images/stern_schwarz.png" height="20px" />
				</p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</li>
				<li><p><b>Matthew, London</b>
				<img src="/ehotel/resources/images/stern_rot.png" height="20px" />
				<img src="/ehotel/resources/images/stern_rot.png" height="20px" />
				<img src="/ehotel/resources/images/stern_rot.png" height="20px" />
				<img src="/ehotel/resources/images/stern_rot.png" height="20px" />
				<img src="/ehotel/resources/images/stern_schwarz.png" height="20px" />
				</p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquast, qui dolorem ipsum.</li>
				 -->
				
			</ul><br/>
<!-- 			<div class="button1"><span><span><a href="#">Geben Sie ihre eigene Bewertung ab</a></span></span></div> -->
		</div>
</div>
		
<jsp:include page="footer.jsp"/>