<%@taglib uri="/WEB-INF/customFunctions.tld" prefix="func" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript">
function redirectAddToShelf(value)
{
	window.location="/Booksmania/welcome/add?bookId=".concat(value);
}
function bookCountExtended()
{
	alert("You have reached Maximum book requests.");
}
</script>
<script type="text/javascript">

$(document).ready(function() {
    $('#example').dataTable( {
        stateSave: true
        
    } );
} );
</script>
<script type="text/javascript">
function redirectRequest(bookId) {

	//alert("You have requested book...id= "+ bookId);
	$.ajax({
		type : "Get",
		url : "request",
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
</script>

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
                <th>Copies Left</th>
                <th>Availability</th>
                <th>Add to Shelf</th>
                <th>Request Book</th>
            </tr>
        </thead>
        	<tbody>
        	<c:forEach items="${bookList}" var="book">
        		<tr>
        			<td>
        				
        				<div id="bookImage">
        					<img src="<c:url value="/resources/images/${book.imageName} "/>" alt="${book.imageName}" style="width:90px;height:100px;"/>
        				</div>
        			</td>
        			<td><c:out value="${book.title}"/></td>
        			<td><c:out value="${book.author}"/></td>
        			<td><c:out value="${book.publisher}"/></td>
        			<td><c:out value="${book.description}"/></td>
        			<td><c:out value="${book.getBookLanguage().language}"/></td>
        			<td><c:out value="${book.getCategory().category}"/></td>
        			<td><c:out value="${book.availability}"/></td>
        			<td><c:if test="${book.availability>0}"><c:out value="Available"/></c:if><c:if test="${book.availability==0}"><c:out value="Unavailable"/></c:if> </td>
        			<td>
        				<c:choose>
        					<c:when test="${func:contains(shelfItemList,book.bookId)}">
        						<button type="button" class="btn btn-danger" style="margin-top:3%;">Already In Shelf</button>
        					</c:when>
        					<c:otherwise>
        						<button type="submit" class="btn btn-primary" style="margin-top:3%;" onclick="redirectAddToShelf(${book.bookId})">Add To Shelf</button>
        					</c:otherwise>
        				</c:choose>
        				
        			</td>
        			<td>
        				<c:choose>
        					<c:when test="${user.status}">
        						<c:if test="${book.availability<=0}"><button type="button" class="btn btn-danger" style="margin-top:3%;">Book is unavailable</button></c:if>
        						<c:if test="${book.availability>0 }">
        						<c:choose>
	        						<c:when test="${func:contains(requestList,book.bookId)}">
    	    							<button type="button" class="btn btn-danger" style="margin-top:3%;">Already Requested</button>
        							</c:when>
        							<c:otherwise>
        								<c:if test="${user.requestBookCount>=user.getSubscription().maxBook}"><button type="button" class="btn btn-default" style="margin-top:3%;">Book Request Limit Exceeded</button></c:if>
        								<c:if test="${user.requestBookCount<user.getSubscription().maxBook}"><button type="button" data-toggle="modal" data-target="#one" class="btn btn-primary" style="margin-top:3%;" onclick="redirectRequest(${book.bookId})">Request Book</button></c:if>	
        							</c:otherwise>
        						</c:choose>
        					</c:if>
        					</c:when>
        					<c:otherwise>
	        					<button type="button" class="btn btn-danger" style="margin-top:3%;" disabled>Subscription Plan Ended</button>
    	    				</c:otherwise>
        				</c:choose>
        			</td>
        		</tr>	
        	</c:forEach>
        </tbody>
        <tbody>
        	
        </tbody>
	</table>

</div>
<div class="modal fade" id="one" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" ></div>