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
            <li class="active"><a href="home">Home</a></li>
            <li class="dropdown">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown">Operations <b class="caret"></b></a>
				<ul class="dropdown-menu">
					<li><a href="addBook">Add Book</a></li>
					<li><a href="viewBook">View Book</a></li>
					<li><a href="#" data-toggle="modal" data-target="#subscriptionModal">Add Subscription</a></li>
					<li><a href="viewSubscription">View Subscription</a></li>
					<li><a href="activeUsers">Active Users</a></li>
				</ul>
			</li>
			<li><a href="userRequests">User Requests</a></li>
            <li><a href="report">Reports</a></li>
            
		  </ul>
		   <c:url var="logoutUrl" value="/j_spring_security_logout"></c:url>
		  <p class="navbar-text navbar-right"><a href="${logoutUrl}" class="navbar-link">Sign Out</a></p>
		  <p class="navbar-text navbar-right"><a href="home" class="navbar-link">Admin</a></p>
		  
		  
	    </div>
	  </div>
    </div>


<!------------------------------------------------------ Add Subscription Form---------------------------------------------------->

<form:form modelAttribute="subscription" method="post" action="subscription" style="width:180%;" enctype="multipart/form-data">
	<div class="modal fade" id="subscriptionModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
					</button>
					<h4 class="modal-title" id="myModalLabel">
						Add Subscription Plans!
					</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label for="usr">Upload .xml file:</label>
						<input type="file" name="subscriptionFile" accept="*.xml" class="form-control" id="usr">
					</div>
					
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="Submit" class="btn btn-primary">Add Subscription</button>
					
				</div>
			</div>
		</div>
	</div>
</form:form>