<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- <div>
	<table>
		<form:form modelAttribute="request" action="userRequests?bookId=${book.bookId }" method="post">
			<tr>
				<td>Book Name:</td>
				<td><input type="text" name="title" id="title" value="${book.title}" readonly="readonly"/></td>
			</tr>
		
			<tr>
				<td>Book ISBN:</td>
				<td><input type="text" name="ISBN" id="ISBN" value="${book.ISBN}" readonly="readonly"/></td>
			</tr>
		
			<tr>
				<td>Author:</td>
				<td><input type="text" name="author" id="author" value="${book.author}" readonly="readonly"/></td>
			</tr>
		
			<tr>
				<td>Delievery Address</td>
				<td><input type="text" name="delieveryAddress" id="delieveryAddress" /></td>
			</tr>
			<tr>
				<td><input type="submit" name="postRequest" id="postRequest" value="Request Book" /></td>
				<td><input type="reset" name="Reset" id="" value="Reset" /></td>
			</tr>
			
		</form:form>
	</table>
</div>
 --%>
 
 <form:form modelAttribute="request" action="userRequests?bookId=${book.bookId}" method="post">
 <div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
					</button>
					<h4 class="modal-title" id="myModalLabel">
						Request Book!
					</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label for="usr">Title:</label>
<%-- 						<input type="hidden" name="bookId" id="bookId" value="${book.bookId}"/> --%>
						<input type="text" class="form-control" id="usr" name="title" value="${book.title}">
					</div>
					
					<div class="form-group">
						<label for="usr">Author:</label>
						<input type="text" class="form-control" id="usr" name="author" value="${book.author}">
					</div>
					
					<div class="form-group">
						<label for="usr">Description:</label>
						<input type="text" class="form-control" id="usr" name="description" value="${book.description}">
					</div>
					
					<div class="form-group">
						<label for="usr">Publisher:</label>
						<input type="text" class="form-control" id="usr" name="publisher" value="${book.publisher}">
					</div>
					
					<div class="form-group">
						<label for="usr">Delievery Address</label>
						<input type="text" class="form-control" id="usr" name="delieveryAddress" required />
					</div>
					<button type="Submit" class="btn btn-primary">Request</button>
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
</form:form>

		