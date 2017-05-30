<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container">
		<div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
		  <a class="navbar-brand" href="home">BooksMania</a>
        </div>
        <div class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li><a href="home">Home</a></li>
            <li><a href="#">About</a></li>
			<li><a href="#">Subscription Plans</a></li>
            <li class="active"><a href="contact">Contact</a></li>
           </ul>
		<c:url var="loginUrl" value="/j_spring_security_check"></c:url>
		<form class="navbar-form navbar-left" method="post" action="${loginUrl }" role="search" style="margin-left:5%;">
			<div class="form-group">
				<input type="email" class="form-control" placeholder="Username" name="j_username" required>
			</div>
			<div class="form-group">
				<input type="password" class="form-control" placeholder="Password" name="j_password" required>
			</div>
			<button type="submit" class="btn btn-success">Login</button>
		</form>
			
		<div class="navbar-form navbar-left"><a href="register" role="button" class="btn btn-primary">SignUp</a></div>
		</div>
	  </div>
</div>
	
	<!---------------------------------------------------Search Box--------------------------------------->
<form:form methodAttribute="book" action="search" method="post" cssClass="form-horizontal" cssStyle="margin-left:25%;margin-right:25%;margin-top:5%;">
	<div class="input-group">
		<input type="text" class="form-control input-lg" name="search" placeholder="Search by Title , Author , Category" required/>
		<span class="input-group-btn">
			<input class="btn btn-success btn-lg btn-block" type="submit" value="Go!"/>
		</span>
	</div>
</form:form>