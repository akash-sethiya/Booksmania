<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<br><br><div><p>Books left to Request:${user.bookCount}</p></div><br><br><div style="position:relative;top:15px;">
	<table id="example" class="display" cellspacing="0" width="100%">
        <thead>
            <tr>
                <th>Request Id</th>
                <th>Image</th>
                <th>Requested Book</th>
                <th>Issued Date</th>
                <th>Returned Date</th>
                <th>Delivery Address</th>
                <th>Cancel</th>
                <th>Status</th>
            </tr>
        </thead>
 
        <tbody>
        	<c:forEach items="${requestList}" var="request">
        		<tr>
        			<td>
        				
        				<div id="bookImage">
        					<img src="<c:url value="/resources/images/${book.imageName} "/>" alt="${book.imageName}" style="width:90px;height:100px;"/>
        				</div>
        			</td>
        			<td><c:out value="${requestList.getBook().bookId}"/></td>
        			<td><c:out value="${requestList.status}"/></td>
        			<td><c:out value="${requestList.issuedDate}"/></td>
        			<td><c:out value="${requestList.returnedDate}"/></td>
        			<td><c:out value="${requestList.delieveryAddress}"/></td>
        			<td>
        				<input type="submit" name="cancelRequest" id="cancelRequest" value="Cancel This Book" onclick="#" />
        			</td>
        			<td><c:out value="${ requestList.status}"/></td>
        		</tr>	
        	</c:forEach>
        </tbody>
	</table>
</div>

