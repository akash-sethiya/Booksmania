<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:forEach begin="0" end="${fn:length(historyList) - 1}" varStatus="loop">
 <div style="" class="bookTile">
		<img src="<c:url value="/resources/images/${bookList[loop.index].imageName} "/>" alt="${bookList[loop.index].imageName}" style="width:200px;height:300px;padding:10px;"/>
		<center>
			<p>Title:${bookList[loop.index].title}</p>
        	<p>Author:${bookList[loop.index].author}</p>
        	<p>Publisher:${bookList[loop.index].publisher}</p>
        	<c:choose>
        	<c:when test="${empty historyList[loop.index].issuedDate or empty historyList[loop.index].returnDate}">
        		<c:out value="Book Requested then Cancel the request" />
        	</c:when>
        	<c:otherwise>
        		<p>Issued Date:${historyList[loop.index].issuedDate}</p>
        		<p>Return Date:${historyList[loop.index].returnDate}</p>
        	</c:otherwise>
        	</c:choose>
		</center>
</div>
</c:forEach>	
	<style>
.bookTile{
	float:left;
	box-shadow: 0 0 15px #5B5B5B;
	margin-left:70px;
	font-size:10px;
	width:200px;
	margin-top:15px;
	margin-bottom:50px;
}
.modal-backdrop.in {
	filter: alpha(opacity=0);
	opacity: 0;
}
.modal {
	background-color:rgba(0,0,0,0.5);
}
</style>