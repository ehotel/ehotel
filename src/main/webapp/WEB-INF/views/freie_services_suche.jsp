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
    				minDate:  new Date("${min}"),
    				maxDate: new Date("${max}"),
			   	 	onClose: function( selectedDate ) {
			   		$( "#abreise" ).datepicker( "option", "minDate", selectedDate );
   					}    	
    		});
    	
        $('#abreise').datepicker({ dateFormat: 'dd.mm.yy',
        			minDate:  new Date("${min}"),
        			maxDate: new Date("${max}"),
   	 				onClose: function( selectedDate ) {
	   				$( "#anreise" ).datepicker( "option", "maxDate", selectedDate );
					}
        	});
        });
      
  	document.getElementById("services").setAttribute("class", "current");
      
    </script> 

<div id="content">
<p><font color="#FF0000">${felderError}</font></p>
   <c:if test="${empty zusatzservices}">
    <p>Keine freien ZusatzServices gefunden</p>
  </c:if>
  <c:if test="${not empty zusatzservices}">
	<form method="POST" action="service_reservieren">
	 	<fieldset>
			<legend>Service Suche</legend>
			<label>Service:</label>
		   	<select name="service">
				<c:forEach var="z" items="${zusatzservices}">
					<option value="${z.id}">${z.name}</option>        
		    	</c:forEach>
		  	</select><br><br>
			<label>Anreise:</label>
			<input type="text" name="anreise" id="anreise"/><br><br>
			<label>Abreise:</label>
			<input type="text" name="abreise" id="abreise"/><br><br>
			<input type="submit"/>
		</fieldset>
	</form>	
  </c:if>
</div>  
<div style="height:100px"></div>

<jsp:include page="footer.jsp"/>

