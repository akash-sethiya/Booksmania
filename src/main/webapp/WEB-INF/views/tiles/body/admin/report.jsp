<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<br><br><br><br><br><br><br><br>

<div style="position:relative;left:25%;">
	<c:url var="generate" value="report"/>
	<form:form modelAttribute="history" action="${generate}" method="post">
		<label for="author">Author:</label>		
		<select name="author" id="author">
			<option value="all">All Author</option>
			<c:forEach items="${bookAuthorList}" var="book">
				<option value="${book}">${book}</option>	
			</c:forEach>
		</select>
		
		<label for="category">Category: </label>
		<select name="category" id="category">
			<option value="all">All Categories</option>
				<c:forEach items="${categoryList}" var="category">
					<option value="${category.categoryId}">${category.category}</option>	
				</c:forEach>
		</select>
		<br>
		<label for="from">From</label>
		<input type="text" id="from" name="from" />
		<label for="to">to</label>
		<input type="text" id="to" name="to" />
		<input type="submit" class="btn btn-primary" name="filter" value="Filter" />
		<input type="submit" class="btn btn-classic" name="generate" value="Generate"/>

	</form:form>
</div>

<c:if test="${(fn:length(historyList) - 1)>0}">
<c:forEach begin="0" end="${fn:length(historyList) - 1}" varStatus="loop">
	<div id="report">
		<div id="book-image"><img src="<c:url value="/resources/images/${bookList[loop.index].imageName}  "/>" alt="${bookList[loop.index].title} " class="img-thumbnail"/></div>
			<div style="" id="report-details">
				<table id="reportDescription" class="table table-hover">
		        	<tr><td>Title</td><td>${bookList[loop.index].title}</td></tr>
	    	        <tr><td>Issued Date</td><td>${historyList[loop.index].issuedDate}</td></tr>
	        	    <tr><td>Return Date</td><td>${historyList[loop.index].returnDate}</td></tr>
	        	    <tr><td>User Name</td><td>${userList[loop.index].getUserDetails().name}</td></tr>
	        	  </table>
	        </div>
	 </div>
</c:forEach>
</c:if>


<style>
	#book-image
	{
		width:150px; 
		height:150px; 
		float:top;
	}
	#report-details
	{
		position:relative;
		margin-left:200px;
		margin-top:-150px;	
		width:450px; 
		height:auto; 
		float:top;
	}
	#report
	{
		margin-left:200px;
		margin-top:5%; 
		text-align:center; 
		padding:30px; 
		width:30%; 
		height:auto; 
		float:left;
	}
</style>





	<script type="text/javascript">
	$(function() {
		
		$( "#from" ).datepicker({
			dateFormat:"yy-mm-dd",
			defaultDate: "+1w",
			changeMonth: false,
			numberOfMonths: 1,
			onClose: function( selectedDate ) {
				$( "#to" ).datepicker( "option", "minDate", selectedDate );
			}
		});
		$( "#to" ).datepicker({
			dateFormat:"yy-mm-dd",
			defaultDate: "+1w",
			changeMonth: false,
			numberOfMonths: 1,
			onClose: function( selectedDate ) {
				$( "#from" ).datepicker( "option", "maxDate", selectedDate );
			}
		});
	});
	</script>
	
	