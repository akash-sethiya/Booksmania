<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript">
	function redirectTile(bookId)
	{
		$.ajax({
			type : "Get",
			url : "showBook",
			data : "bookId=" + encodeURIComponent(bookId),
			success : function(response) {
				//alert("success="+response)
				$('#bookTileModal').html(response);	
			},
			error : function(xhr,status,error) {		
				alert("failure  " +xhr.responseText)
				//alert('Error: ' + e);
			}
		});
	}
</script>
<br><br><br><br><br><br>
<c:forEach items="${bookList}" var="book">
	<div style="" class="bookTile" data-toggle="modal" data-target="#bookTileModal" onclick="redirectTile(${book.bookId})">
		<img src="<c:url value="/resources/images/${book.imageName}"/>" alt="${book.title}" style="width:200px;height:300px;padding:10px;"/>
		<center><p>${book.title}</p></center>
	</div>
</c:forEach>

<div class="modal fade" id="bookTileModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >
	
</div>

<style>
.bookTile{
	float:left;
	box-shadow: 0 0 15px red;
	margin-left:70px;
	margin-top:20px;
	font-size:10px;
	margin-bottom:20px;
	width:200px;
}
.modal-backdrop.in {
	filter: alpha(opacity=0);
	opacity: 0;
}
.modal {
	background-color:rgba(0,0,0,0.5);
}
</style>