<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript">
	function redirectCancel(value)
	{
		window.location="/Booksmania/admin/closeRequest?id=".concat(value);
	}
	
	function redirectreturnCancel(value)
	{
		window.location="/Booksmania/admin/closeReturnRequest?id=".concat(value);
	}
</script>
<br><br><br><br><br>
<table style="width:100%;">
	<tr>
		<td>
			<div>
				<p class="lead" style="margin-left:30%;">Delievery Requests</p>
			</div>
		</td>
		<td>
			<div style="margin-left:55px;" >
				<p class="lead" style="margin-left:25%;">Return Requests</p>
			</div>
		</td>
	</tr>
	<tr>
		<td>
			<div class="CSSTableGenerator">
				<table>
					<tr>
						<th>Request Id</th>
						<th>Book Id</th>
						<th>User Id</th>
						<th>Title</th>
						<th>Image</th>
						<th>Close Request</th>
					</tr>
					<c:forEach items="${requestList}" var="request">
					<tr>
						<td>${request.requestId}</td>
						<td>${request.getBook().bookId}</td>
						<td>${request.getUser().userId }</td>
						<td>${request.getBook().title}</td>
						<td>
							<div id="bookImage">
       							<img src="<c:url value="/resources/images/${request.getBook().imageName} "/>" alt="${request.getBook().imageName}" style="width:90px;height:100px;"/>
       						</div>
						</td>
						<td><input type="submit" name="closeRequest" onclick="redirectCancel(${request.requestId})" value="Close Request"/></td>
					</tr>
					</c:forEach>
				</table>
			</div>
		</td>
		<td>
			<div class="CSSTableGenerator">
				<table>
					<tr>
						<th>Request Id</th>
						<th>Book Id</th>
						<th>User Id</th>
						<th>Title</th>
						<th>Image</th>
						<th>Close Request</th>
					</tr>
					<c:forEach items="${returnRequestList}" var="request">
						<tr>
							<td>${request.requestId}</td>
							<td>${request.getBook().bookId}</td>
							<td>${request.getUser().userId }</td>
							<td>${request.getBook().title}</td>
							<td>
								<div id="bookImage">
       								<img src="<c:url value="/resources/images/${request.getBook().imageName} "/>" alt="${request.getBook().imageName}" style="width:90px;height:100px;"/>
       							</div>
							</td>
							<td>
								<input type="submit" name="closeRequest" onclick="redirectreturnCancel(${request.requestId})" value="Close Request"/>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</td>
	</tr>
</table>

<style>
.CSSTableGenerator {
	/*position:fixed;*/
	/*float:left;*/
	margin:0px;
	padding:0px;
	width:80%;
	/*margin-top:0px;*/
	margin-left:2%;
	margin-right:2%;*/
	
	box-shadow: 10px 10px 5px #888888;
	border:1px solid #000000;
	
	-moz-border-radius-bottomleft:14px;
	-webkit-border-bottom-left-radius:14px;
	border-bottom-left-radius:14px;
	
	-moz-border-radius-bottomright:14px;
	-webkit-border-bottom-right-radius:14px;
	border-bottom-right-radius:14px;
	
	-moz-border-radius-topright:14px;
	-webkit-border-top-right-radius:14px;
	border-top-right-radius:14px;
	
	-moz-border-radius-topleft:14px;
	-webkit-border-top-left-radius:14px;
	border-top-left-radius:14px;
}.CSSTableGenerator table{
    border-collapse: collapse;
        border-spacing: 0;
	width:100%;
	height:100%;
	margin:0px;padding:0px;
}.CSSTableGenerator tr:last-child td:last-child {
	-moz-border-radius-bottomright:14px;
	-webkit-border-bottom-right-radius:14px;
	border-bottom-right-radius:14px;
}
.CSSTableGenerator table tr:first-child td:first-child {
	-moz-border-radius-topleft:14px;
	-webkit-border-top-left-radius:14px;
	border-top-left-radius:14px;
}
.CSSTableGenerator table tr:first-child td:last-child {
	-moz-border-radius-topright:14px;
	-webkit-border-top-right-radius:14px;
	border-top-right-radius:14px;
}.CSSTableGenerator tr:last-child td:first-child{
	-moz-border-radius-bottomleft:14px;
	-webkit-border-bottom-left-radius:14px;
	border-bottom-left-radius:14px;
}.CSSTableGenerator tr:hover td{
	background-color:#ffffff;
		

}
.CSSTableGenerator td{
	vertical-align:middle;
	
	background-color:#e5e5e5;

	border:1px solid #000000;
	border-width:0px 1px 1px 0px;
	text-align:left;
	padding:7px;
	font-size:10px;
	font-family:Arial;
	font-weight:normal;
	color:#000000;
}.CSSTableGenerator tr:last-child td{
	border-width:0px 1px 0px 0px;
}.CSSTableGenerator tr td:last-child{
	border-width:0px 0px 1px 0px;
}.CSSTableGenerator tr:last-child td:last-child{
	border-width:0px 0px 0px 0px;
}
.CSSTableGenerator tr:first-child td{
		background:-o-linear-gradient(bottom, #cccccc 5%, #b2b2b2 100%);	background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #cccccc), color-stop(1, #b2b2b2) );
	background:-moz-linear-gradient( center top, #cccccc 5%, #b2b2b2 100% );
	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr="#cccccc", endColorstr="#b2b2b2");	background: -o-linear-gradient(top,#cccccc,b2b2b2);

	background-color:#cccccc;
	border:0px solid #000000;
	text-align:center;
	border-width:0px 0px 1px 1px;
	font-size:14px;
	font-family:Arial;
	font-weight:bold;
	color:#000000;
}
.CSSTableGenerator tr:first-child:hover td{
	background:-o-linear-gradient(bottom, #cccccc 5%, #b2b2b2 100%);	background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #cccccc), color-stop(1, #b2b2b2) );
	background:-moz-linear-gradient( center top, #cccccc 5%, #b2b2b2 100% );
	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr="#cccccc", endColorstr="#b2b2b2");	background: -o-linear-gradient(top,#cccccc,b2b2b2);

	background-color:#cccccc;
}
.CSSTableGenerator tr:first-child td:first-child{
	border-width:0px 0px 1px 0px;
}
.CSSTableGenerator tr:first-child td:last-child{
	border-width:0px 0px 1px 1px;
}
</style>

