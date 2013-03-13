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
      
  	document.getElementById("booking").setAttribute("class", "current");
      
    </script> 

<div id="content">
<p><font color="#FF0000">${felderError}</font></p>
<form method="POST" action="zimmer_suche">
 <fieldset>
 <legend>Zimmer suchen</legend>
   <label>Zimmertyp:</label>
   <select name="zk_typ">
	<option value="egal">-- egal --</option>
		<c:forEach var="zk" items="${zimmerkategorien}">
			<option value="${zk.zimmertyp}">${zk.zimmertyp}</option>        
   		</c:forEach>
 	</select><br><br>
 <label>Anreise:</label>
 <input type="text" name="anreise" id="anreise"/><br><br>
 <label>Abreise:</label>
 <input type="text" name="abreise" id="abreise"/><br><br>
 <div class="button" style="text-align:left"><span><span style="text-align:center"><a class="form-link" onclick="document.getElementById('service_suche').submit()">Suche Zimmer</a></span></span></div> 
 </fieldset>
</form>
</div>
<div style="height:100px"></div>

<jsp:include page="footer.jsp"/>

