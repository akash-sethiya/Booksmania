<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="/WEB-INF/customFunctions.tld" prefix="func" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<script type="text/javascript">

$(document).ready(function() {
    $('#example').dataTable( {
        stateSave: true
        
    } );
} );
</script>

<script type="text/javascript">

function redirectUpdate(bookId) {

	//alert("You have requested book...id= "+ bookId)
	$.ajax({
		type : "Get",
		url : "update",
		data : "bookId=" + encodeURIComponent(bookId),
		success : function(response) {
			//alert("success="+response)
			$('#one').html(response);	
		},
		error : function(xhr,status,error) {		
			alert("failure  " +xhr.responseText)
			//alert('Error: ' + e);
		}
	});
	}



/*function redirectUpdate(value)
{
	window.location="/Booksmania/admin/update?bookId=".concat(value);
}*/
function redirectDelete(value)
{
	window.location="/Booksmania/admin/delete?bookId=".concat(value);
}
</script>
<br><br><br><br>
<div style="position:relative;top:15px;">
	<table id="example" class="display" cellspacing="0" width="100%">
        <thead style="background-color:#000000;color:grey;">
            <tr>
                <th>Image</th>
                <th>Title</th>
                <th>Author</th>
                <th>Publisher</th>
                <th>Description</th>
                <th>Language</th>
                <th>Category</th>
                <th>Total Copies</th>
                <th>Availability</th>
                <th>Update Book</th>
                <th>Delete Book</th>
            </tr>
        </thead>
        <tbody>
        	<c:forEach items="${bookList}" var="book">
        		<tr>
        			<td>
        				
        				<div id="bookImage">
<%--         					<c:url value="${imagePath}${book.imageName}" var="imageServerPath"></c:url> --%>
        					<img src="<c:url value="/resources/images/${book.imageName} "/>" alt="${book.imageName}" style="width:90px;height:100px;"/>
        				</div>
        			</td>
        			<td><c:out value="${book.title}"/></td>
        			<td><c:out value="${book.author}"/></td>
        			<td><c:out value="${book.publisher}"/></td>
        			<td><c:out value="${book.description}"/></td>
        			<td><c:out value="${book.getBookLanguage().language}"/></td>
        			<td><c:out value="${book.getCategory().category}"/></td>
        			<td><c:out value="${book.quantity}"/></td>
        			<td><c:if test="${book.availability>0}"><c:out value="Available"/></c:if><c:if test="${book.availability==0}"><c:out value="Unavailable"/></c:if> </td>
        			<td><button type="button" class="btn btn-success" style="margin-top:3%;" data-toggle="modal" data-target="#one" onclick="redirectUpdate(${book.bookId})">Update Book</button></td>
        			<td>
        				<c:choose>
        					<c:when test="${book.availability!=book.quantity}">
        						<button type="button" class="btn btn-danger" style="margin-top:3%;">Book Taken! Can't Delete</button>
        					</c:when>
        					<c:otherwise>
        						<c:choose>
        							<c:when test="${func:contains(requestedBookIdList,book.bookId) or func:contains(shelfItemList,book.bookId)}">
        								<button type="button" class="btn btn-danger" style="margin-top:3%;">Book Requested</button>
        							</c:when>
        							<c:otherwise>
        								<button type="button" class="btn btn-success" style="margin-top:3%;" onclick="redirectDelete(${book.bookId})">Delete Book</button>
        							</c:otherwise>
        						</c:choose>
        						
        					</c:otherwise>
        				</c:choose>
         				
        			</td>
        		</tr>	
        	</c:forEach>
        </tbody>
	</table>
</div>
<!-- Update Book Form -->
<c:url value="update" var="updateBook"/>
<form:form modelAttribute="book" action="${updateBook}" method="post" enctype="multipart/form-data" style="width:180%;">
<div class="modal fade" id="one" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" ></div>
</form:form>



