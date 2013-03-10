<jsp:include page="header_small.jsp"/>

			<div class="content">
				<div class="indent">
					<h2>Password reset</h2>
					<div class="h31"> Please enter your Userid</div>
					<div class="h31"> </div>
					<div class="line-hor-lang"></div>
					<p><font color="#FF0000">${felderError}</font></p>						
					<form id="reset-form" method="post" action="reset" >			
						<fieldset>
						<div class="field">UserID<br/><br/>
								<input type="text"  name="j_username" /></div>
						</fieldset>			
						
				<div style="float:left;">
						<br><div class="button"><span><span><a href="resetbestätigung" onclick="document.getElementById('reset-form').submit()">Submit request </a></span></span></div>
					</div><br><br><br>
					
					
				</div>
					</form>			      
					<div class="clear"></div>
								
				</div>
			</div>
		</div>
	</div>
<jsp:include page="footer.jsp"/>
</div>
</body>
</html>


