<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<div>
	<div><h1>Add Books</h1>
		<h2>${addBookMessage }</h2>
	</div>
	</div>
	
	<c:url value="addBook" var="add"></c:url>
	<form:form modelAttribute="Book" method="post" action="${add}" style="width:90%;" enctype="multipart/form-data">
	<div class="container-fluid well span6" style="margin-left:40%;margin-right:20%;">
		<div class="row-fluid" style="text-align:center;">
			
			
				<div class="form-group">
					<label for="usr">Title:</label>
					<input type="text" name="title" class="form-control" id="usr" required>
				</div>
				<div class="form-group">
					<label for="usr">Author:</label>
					<input type="text" name="author" class="form-control" id="usr" required>
				</div>
				<div class="form-group">
					<label for="usr">ISBN:</label>
					<input type="number" name="iSBN" class="form-control" id="usr" size="20" required>
				</div>
				<div class="form-group">
					<label for="usr">Description:</label>
					<input type="text" name="description" class="form-control" id="usr" required>
				</div>
				<div class="form-group">
					<label for="usr">Publisher:</label>
					<input type="text" name="publisher" class="form-control" id="usr" required>
				</div>
				
				<div class="form-group">
					<label for="usr">Language:</label>
					<select class="form-control" name="languageId">
						<c:forEach var="lang" items="${languageList}">
							<option value="${lang.languageId}">${lang.language}</option>
						</c:forEach>
					</select>
				</div>
		
				<div class="form-group">
					<label for="usr">Category:</label>
					<select class="form-control" name="categoryId">
						<c:forEach var="category" items="${categoryList}">
							<option value="${category.categoryId}">${category.category}</option>
						</c:forEach>
					</select>
				</div>
				
				<div class="form-group">
					<label for="usr">Add No. of copies:</label>
					<input type="number" name="quantity" class="form-control" id="usr" required>
				</div>
				
				<div class="form-group">
					<label for="image">Book Image:</label>
					<input type="file" name="image" class="form-control" id="image" required>
				</div>
				
				<button type="reset" name="addbook" class="btn btn-default" data-dismiss="modal">Reset</button>
				<button type="submit" name="reset" class="btn btn-primary">Add Book</button>
						
		</div>
	</div>
</form:form>