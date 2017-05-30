<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script type="text/javascript">
function redirectCancelRequest(value)
{
	window.location="/Booksmania/welcome/cancelRequest?requestId=".concat(value);
}
</script>

<c:forEach var="userRequest" items="${requestList}">
	<div id="request">
		<div id="book-image"><img src="<c:url value="/resources/images/${userRequest.getBook().imageName} "/>" alt="${userRequest.getBook().imageName}" class="img-thumbnail"/></div>
			<div style="" id="request-details">
				<table id="requestDescription" class="table table-hover">
		        	<tr><td>Title</td><td>${userRequest.getBook().title}</td></tr>
	    	        <tr><td>Author</td><td>${userRequest.getBook().author}</td></tr>
	        	    <tr><td>Publisher</td><td>${userRequest.getBook().publisher}</td></tr>
	        	    <tr><td>Issued Date</td><td>${userRequest.issuedDate}</td></tr>
	        	    <tr><td>Return Date</td><td>${userRequest.expectedReturnedDate}</td></tr>
	        	    <tr><td>Delievery Address</td><td>${userRequest.delieveryAddress}</td></tr>
	            	<tr><td><button type="button" class="btn btn-default" data-dismiss="modal" onclick="redirectCancelRequest(${userRequest.requestId})">I don't want to Read</button></td></tr> 
	              </table>
	        </div>
	 </div>
</c:forEach>

<style>
	#book-image
	{
		width:150px; 
		height:150px; 
		float:top;
	}
	#request-details
	{
		position:relative;
		margin-left:200px;
		margin-top:-150px;	
		width:450px; 
		height:auto; 
		float:top;
	}
	#request
	{
		margin-left:200px; 
		text-align:center; 
		padding:30px; 
		width:700px; 
		height:auto; 
		float:top;
	}
</style>
