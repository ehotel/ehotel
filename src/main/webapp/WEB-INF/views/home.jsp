<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header_big.jsp"/>

    <link href="resources/jquery-ui-1.10.1.custom.css" rel="stylesheet" type="text/css" />
    <script src="resources/jquery-1.9.1.js"></script>
    <script src="resources/jquery-ui-1.10.1.custom.js"></script>
<!--     <script src="js/jquery-ui-1.10.1.custom.min.js"></script>
 -->    <script> 
		 $(document).ready(function(){ 
			   	$('#anreise').datepicker({
			   		dateFormat: 'dd.mm.yy',
			   		minDate: 0,
			   	 	onClose: function( selectedDate ) {
				   		$( "#abreise" ).datepicker( "option", "minDate", selectedDate );
			   		}
			   	});
			   	
			       $('#abreise').datepicker({
			       	dateFormat: 'dd.mm.yy',
			        minDate: 1,
			   	 	onClose: function( selectedDate ) {
				   		$( "#anreise" ).datepicker( "option", "maxDate", selectedDate );
			   		}
			    });
		 	});
		 
		 document.getElementById("home").setAttribute("class", "current");
		 
    </script>

	<div id="content">
		<div class="wrapper">
			<div class="aside maxheight">
<!-- box begin -->
<br>
				<div class="box maxheight">
					<div class="inner">
						<h3>Reservation:</h3>
						<form method="POST" id="suche-form" action="zimmer_suche">
								<div class="field"><label>Check In:</label><input type="text" name="anreise" id="anreise"/></div>
								<div class="field"><label>Check Out:</label><input type="text" name="abreise" id="abreise"/></div>
								<div class="field"> Room type:&nbsp; <select name="zk_typ">
															<c:forEach var="zk" items="${zimmerkategorien}">
															<option value="${zk.zimmertyp}">${zk.zimmertyp}</option>        
      														</c:forEach>
  																	</select>
  								</div><br/>
								<div class="button"><span><span><a style="cursor:pointer;" onclick="document.getElementById('suche-form').submit()">Suche Zimmer</a></span></span></div>
						</form>
					</div>
				</div>
<!-- box end -->
			</div>
			<div class="content">
				<div class="indent">
					<h2>FiveStar is happy to welcome you!</h2>
					<img class="img-indent png" alt="" src="resources/images/1page-img1.png" />
					<p class="alt-top">Come alone or bring your family with you, stay here for a night or for weeks, stay here while on business trip or at some kind of conference - either way our hotel is the best possible variant.</p>
				 	Feel free to contact us anytime in case you have any questions or concerns.<br><br>
					<div style="float:right;">
						<div class="button" ><span><span><a href="login_user">Hier einloggen!</a></span></span></div> 
						<div class="aligncenter">neu? <a href="registrieren">Jetzt registrieren!</a></div> 
					</div>
				</div>
						      
					<div class="clear"></div>
					<div class="line-hor"></div>
					<div class="wrapper line-ver">
						<div class="col-1">
				 			<h3>Special Offers</h3>
							<ul>
								<li>FREE wide-screen TV</li>
								<li>50% Discount for Restaraunt service</li>
								<li>30% Discount for 3 days+ orders</li>
								<li>FREE drinks and beverages in rooms</li>
								<li>Exclusive souvenirs</li>
							</ul>
							<div class="button"><span><span><a href="#">Order Now!</a></span></span></div>
						</div>
						<div class="col-2">
				 			<h3>News</h3>
							<p>New Luxus shops have opened closed to E-Hotely. (MORE)</p>
							
						</div>
					</div>
				</div>
			</div>
		
<jsp:include page="footer.jsp"/>


