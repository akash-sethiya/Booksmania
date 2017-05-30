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
            <li><a href="profile">Profile</a></li>
			<li><a href="myShelf">My Shelf</a></li>
            <li class="dropdown">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown">Request</a>
				<ul class="dropdown-menu">
					<li><a href="delieveryRequests">Books Pending Requests</a></li>
					<li class="dropdown"><a href="returnRequests">Return Requests</a></li>
					<li><a href="returnPendingRequests">Return Pending Requests</a></li>
				</ul>
			</li>
            <li class="dropdown">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown">Others <b class="caret"></b></a>
				<ul class="dropdown-menu">
					<li><a href="recommend">Recommended Books</a></li>
					<li><a href="mySubscription">Subscription Plans</a></li>
					<li><a href="history">History</a></li>
				</ul>
			</li>
		  </ul>
		  <c:url var="logoutUrl" value="/j_spring_security_logout"></c:url>
		  <p class="navbar-text navbar-right"><a href="${logoutUrl}" class="navbar-link">Sign Out</a></p>
		  <p class="navbar-text navbar-right"><a href="profile" class="navbar-link">${user.getUserDetails().name}</a></p>
		  
		  
	    </div>
	  </div>
    </div>
	
		<!--Search Box-->
		<form:form methodAttribute="book" action="home" method="post" cssClass="form-horizontal" cssStyle="margin-left:25%;margin-right:25%;margin-top:5%;">
		<div class="input-group">
			<input type="text" class="form-control input-lg" name="search" placeholder="Search by Title , Author , Category"/>
			<span class="input-group-btn">
				<input class="btn btn-success btn-lg btn-block" type="submit" value="Go!"/>
			</span>
		</div>
	</form:form>

