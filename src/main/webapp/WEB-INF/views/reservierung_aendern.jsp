<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page="header_big.jsp"/>
	
  	<link href="../../resources/jquery-ui-1.10.1.custom.css" rel="stylesheet" type="text/css" />
    <script src="../../resources/jquery-1.9.1.js"></script>
    <script src="../../resources/jquery-ui-1.10.1.custom.js"></script>
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
<p><font color="#FF0000">${felderError}</font></p>
<form id="aendern" method="POST" action="../../reservierung/update">
 <fieldset>
 <legend>Zimmer suchen</legend>
   <label>Zimmertyp:</label>
   <select name="zk_typ">
			<c:forEach var="zk" items="${zimmerkategorieliste}">				
				<option <c:if test="${zk.zimmertyp == zimmerkategorie}">selected</c:if> value="${zk.zimmertyp}">${zk.zimmertyp}</option>        
      		</c:forEach>
  		</select><br/><br/>
 <label>Anreise:</label>
 <input type="text" name="startdatum" id="anreise" value="${min}"/><br/><br/>
 <label>Abreise:</label>
 <input type="text" name="enddatum" id="abreise" value="${max}"/><br/><br/>
 <input type="hidden" name="id" value="${reservierung.id}" />
 <!-- <input type="submit" value="Reservierung aendern"/> -->
 <div class="button" style="text-align:left"><span><span style="text-align:center"><a style="cursor:pointer;" onclick="document.getElementById('aendern').submit()">aendern</a></span></span></div>
 </fieldset> 
</form>

<br/>
<fieldset>
 <legend>ZusatzServices</legend>
  <form id="buchen" action="../../freie_services_suche_extra" method="POST">
				<c:if test="${reservierung.status=='Aktiv'}">
					<input type="hidden" name="reservierung_id" value="${reservierung.id}" />
					<!-- <input type="submit" value="service buchen"/><br/> -->
				 	<div class="button" style="text-align:left"><span><span style="text-align:center"><a style="cursor:pointer;" onclick="document.getElementById('buchen').submit()">buche Service</a></span></span></div><br/>
				</c:if>
  </form>  
  <c:if test="${not empty reservierungserviceliste}">  
  <table border="1">
   <tr>
   <td>Name</td>
   <td>Preis</td>
   <td>Startdatum</td>
   <td>Enddatum</td>
   <td>Löschen</td>
<!--    <td>Ändern</td> -->
  </tr>
	<c:forEach var="service" items="${reservierungserviceliste}">
		<jsp:useBean id="start_service" class="java.util.Date" />
		<jsp:setProperty name="start_service" property="time" value="${service.startdatum}" />
		<jsp:useBean id="ende_service" class="java.util.Date" />
		<jsp:setProperty name="ende_service" property="time" value="${service.enddatum}" />
	  	<tr>
			<td>${service.zusatzService.name}</td>
			<td>${service.zusatzService.preis}</td>
			<td><fmt:formatDate value="${start_service}" pattern="dd.MM.yyyy" /></td>
			<td><fmt:formatDate value="${ende_service}" pattern="dd.MM.yyyy" /></td>
			<td><form id="loeschen" action="../reservierungservice/loeschen" method="POST">
			<input type="hidden" name="service_id" value="${service.id}" />
			<a class="form-link" onclick="document.getElementById('loeschen').submit()">loeschen</a>
			</form>
			</td>
		</tr>
	</c:forEach>  
  </table>
  </c:if>
  <c:if test="${empty reservierungserviceliste}">
  <p> Keine ZusatzServices gebucht </p>
  </c:if>
</fieldset>  
</div>  
  
<jsp:include page="footer.jsp"/>
