<jsp:include page="header_small.jsp"/>

			<div class="content">
				<div class="indent">
					<h2>Password reset</h2>
					<div class="h31"> Bitte geben Sie ihren Benutzernamen ein</div>
					<div class="h31"> </div>
					<div class="line-hor-lang"></div>
					<p><font color="#FF0000">${felderError}</font></p>						
					<form id="reset-form" method="post" action="reset" >			
						<div class="field">UserID:
								<input type="text"  name="j_username" />
						</div>
						<div style="float:left;">
							<br><div class="button"><span><span><a style="cursor:pointer;" onclick="document.getElementById('reset-form').submit()">Reset password </a></span></span></div>
						</div><br><br><br>
					</form>
				</div>							      
				<div class="clear"></div>								
				</div>
			</div>
		</div>
	</div>
<jsp:include page="footer.jsp"/>
</div>
</body>
</html>


