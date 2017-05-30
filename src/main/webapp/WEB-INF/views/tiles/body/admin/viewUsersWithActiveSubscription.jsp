<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<style>
	#book-image
	{
		width:150px; 
		height:150px; 
		float:top;
	}
	#activeUser-details
	{
		position:relative;
		margin-left:200px;
		margin-top:-150px;	
		width:450px; 
		height:auto; 
		float:top;
	}
	#activeUser
	{
		margin-left:200px;
		margin-top:5%; 
		text-align:center; 
		padding:30px; 
		width:30%; 
		height:auto; 
		float:left;
	}
</style>
<br><br>
<c:forEach var="user" items="${userList}">
<c:if test="${user.getRole().roleId ==2}">
	<div id="activeUser">
		<div id="book-image">
			<c:if test="${user.getUserDetails().gender=='M' }">
				<img src="<c:url value="/resources/images/boy1.jpg"/>" alt="boy" class="img-thumbnail"/>
			</c:if>
			<c:if test="${user.getUserDetails().gender=='F' }">
				<img src="<c:url value="/resources/images/girl.png"/>" alt="boy" class="img-thumbnail"/>
			</c:if>
		</div>
		<div style="" id="activeUser-details">
			<table id="activeUserDescription" class="table table-hover">
				<tr><td>User Id:</td><td>${user.userId}</td></tr>
		       	<tr><td>Name</td><td>${user.getUserDetails().name}</td></tr>
	            <tr><td>Address:</td><td>${userRequest.getAddress().primaryAddress}&nbsp;</td></tr>
	       	    <tr><td>Active Subscription Plan</td><td>${user.getSubscription().subscriptionName}</td></tr>
	       	    <tr><td>Subscription Start Date</td><td>${user.getUserDetails().subscriptionStartDate}</td></tr>
	       	    <tr><td>Subscription End Date</td><td>${user.getUserDetails().subscriptionEndDate}</td></tr>
	       	    <tr><td>No of book user Requested</td><td>${user.requestBookCount}</td></tr>
	        </table>
	    </div>
	 </div>
</c:if>
</c:forEach>