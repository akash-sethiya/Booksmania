<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
			&times;
		</button>
		<h4 class="modal-title" id="myModalLabel">
			${book.title}
		</h4>
		</div>
		<div class="modal-body">
			<div id="report">
				<c:url value="/resources/images/${book.imageName}" var="image"/>
				<div id="book-image"><img src="${image}" alt="${book.title}" class="img-thumbnail" style="margin-left:60px;width:inherit"/></div>
				<div style="" id="report-details">
					<table id="reportDescription" class="table table-hover">
		        		<tr><td>Author</td><td>${book.author}</td></tr>
	        	    	<tr><td>Publisher</td><td>${book.publisher}</td></tr>
	        	    	<tr><td>Description</td><td>${book.description}</td></tr>
	        	  	</table>
	        	</div>
	 		</div>
		</div>
		<div class="modal-footer">
			<p>You need to <a href="home">Login</a> to borrow this book.Thank You</p>
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		</div>
	</div>
</div>

