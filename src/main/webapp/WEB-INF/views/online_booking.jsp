<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header_big.jsp"/>

    <link href="resources/jquery-ui-1.10.1.custom.css" rel="stylesheet" type="text/css" />
    <script src="resources/jquery-1.9.1.js"></script>
    <script src="resources/jquery-ui-1.10.1.custom.js"></script>
 	<script> 
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
		 
			document.getElementById("booking").setAttribute("class", "current");
    </script>
    					
<div id="content">
		<div class="wrapper">
			<div class="aside maxheight">
<!-- box begin -->
				<div class="box maxheight">
					<div class="inner">
						<h3>Reservation:</h3>
						<form method="POST" id="suche-form" action="zimmer_suche">
								<div class="field"><label>Check In:</label><input type="text" name="anreise" id="anreise"/></div>
								<div class="field"><label>Check Out:</label><input type="text" name="abreise" id="abreise"/></div>
								<div class="field"> Room type: 
									<select name="zk_typ">
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
					<h2>Our location</h2>
					<img class="img-indent png" alt="" src="resources/images/5page-img1.png" />
					<div class="extra-wrap">
						<p class="alt-top">Our hotel is located in the most spectacular part of Prague - surrounded by boutiques, restaurants and luxurious shops.</p>
						<p>Please feel free to come visit us at the following adress:</p>
						<dl class="contacts-list">
							<dt>Gazek st., 210</dt>
							<dd>1-800-412-4556</dd>
							<dd>1-800-542-6448</dd>
						</dl>
					</div>
					<div class="clear"></div>
				</div>
			</div>
		</div>
	</div>
	
<jsp:include page="footer.jsp"/>

