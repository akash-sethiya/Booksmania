<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<center><div style="box-shadow: 0 0 15px red;margin-top:5%;font-size:30px;">${loginMessage}</div></center>
	<div id="login-form" style="margin-left:35%;margin-top:5%;width:35%;">
		<c:url var="loginUrl" value='/j_spring_security_check'></c:url>
		<form class="form-horizontal" action="${loginUrl}" method="post"
			role="form" style="box-shadow: 0 0 15px black;height:270px;">

			<div class="form-group">
				<div class="input-group" style="margin-left:15%;margin-top:7px;">
					<label for="username" class="control-label">Enter Username</label>
					<input name="j_username" type="email"
						class="form-control"/>
				</div>
			</div>

			<div class="form-group">
				<div class="input-group" style="margin-left:15%;margin-top:4px;">
					<label for="password" class="control-label">Enter Password</label>
					<input name="j_password" type="password"
						class="form-control" />
				</div>
			</div>

			<button class="btn btn-primary" type="submit" id="login-button" style="margin-left:17%;width:20%;">Login</button>
			<button type="reset" class="btn btn-danger" id="reset-button" style="width:25%;">Reset</button>
		</form>
		<div id="login-extraneous-element">
				<p><a href="" data-toggle="modal" data-target="#one">Forget Password!</a></p>
				<p><a href="register">New User! SignUp from here</a></p>
		</div>
		

	</div>
	
	<div class="modal fade" id="one" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
					</button>
					<h4 class="modal-title" id="myModalLabel">
						Forget Password
					</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label for="usr">Enter your Registered Email-id:</label>
						<input type="email" class="form-control" id="email" name="email" required/>
					</div>
					<button type="button" id="forgetPass" class="btn btn-primary" data-dismiss="modal">Give my Password</button>			
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	
	<style>
		#login-extraneous-element p{
			float:left;
			margin-left:50px;
			position:relative;
			bottom:30px;
		}
	</style>
	
	<script type="text/javascript">
	$(document).ready(function(){
		  $("#forgetPass").click(function(){
		    var email=document.getElementById("email").value;
		    
		    $.ajax({
				type : "Get",
				url : "forgetPassword",
				data : "email=" + encodeURIComponent(email),
				success : function(response) {
					alert(response);
				},
				error : function(xhr,status,error) {		
					alert("failure  " +xhr.responseText);
					//alert('Error: ' + e);
				}
			});
			});})
	</script>
