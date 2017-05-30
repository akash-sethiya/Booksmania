<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<br><br><br>
<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
					</button>
					<h4 class="modal-title" id="myModalLabel">
						Update Book Info!
					</h4>
				</div>
				<div class="modal-body">
					<hr>
					<div class="form-group">
						<label for="usr">Title:</label>
						<input type="hidden" name="bookId" id="bookId" value="${book.bookId}"/>
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
						<label for="usr">Add No of Copies:</label>
						<input type="text" class="form-control" id="usr" name="quantity" value="${book.quantity}">
					</div>
					
					<div class="form-group">
						<label for="usr">Language:</label>
						<select class="form-control" name="languageId" id="usr">
							<c:forEach var="lang" items="${languageList}">
								<option value="${lang.languageId}" ${lang.languageId==book.getBookLanguage().languageId? 'selected' : ''}>${lang.language}</option>
							</c:forEach>
						</select>
					</div>
					
					<div class="form-group">
						<label for="usr">Category:</label>
						<select class="form-control" name="categoryId">
							<c:forEach var="category" items="${categoryList}">
								<option value="${category.categoryId}" ${category.categoryId==book.getCategory().categoryId? 'selected' : ''}>${category.category}</option>
							</c:forEach>
						</select>
					</div>
					
					<div class="form-group">
						<label for="usr">Book Image:</label>
						<input type="file" accept="*.xml" class="form-control" id="usr" name="imageFile" value="${book.imageName}">
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="Submit" class="btn btn-primary">Update Book</button>
				</div>
			</div>
		</div>