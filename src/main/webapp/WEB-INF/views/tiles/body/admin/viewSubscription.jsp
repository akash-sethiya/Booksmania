<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="/WEB-INF/customFunctions.tld" prefix="func" %>
<script type="text/javascript">

$(document).ready(function() {
    $('#example').dataTable( {
        stateSave: true
        
    } );
} );
</script>
<script type="text/javascript">
	function deleteSubscription(value)
	{
		window.location.href="/Booksmania/admin/deleteSubscription?id=".concat(value);
	}
</script>
<br><br><br><br>
<div style="position:relative;top:15px;">
	<table id="example" class="display" cellspacing="0" width="100%">
        <thead style="background-color:#000000;color:grey;">
            <tr>
                <th>Subscription Id</th>
                <th>Name</th>
                <th>Details</th>
                <th>Maximum Book can Borrow</th>
                <th>Amount</th>
                <th>Period/Duration</th>
                <th>Status</th>
                <th>Delete Subscription</th>
            </tr>
        </thead>
 
        <tbody>
        	<c:forEach items="${subscriptionList}" var="subscription">
        		<tr>
        			<td><c:out value="${subscription.subscriptionId}"/></td>
        			<td><c:out value="${subscription.subscriptionName}"/></td>
        			<td><c:out value="${subscription.subscriptionDetails}"/></td>
        			<td><c:out value="${subscription.maxBook}"/></td>
        			<td><c:out value="${subscription.subscriptionAmount}"/></td>
        			<td><c:out value="${subscription.periodOfSubscription}"/></td>
        			<td><c:out value="${subscription.subscriptionStatus}"/></td>
        			<c:choose>
        				<c:when test="${func:contains(userSuscribedIdList,subscription.subscriptionId)}">
        					<td><button type="button" class="btn btn-danger" style="margin-top:3%;">Can Not Delete</button></td>
<!--         					<td><input type="submit" name="adminSubscriptionDelete" id="sub_update" value="Delete Subscription" disabled="disabled"/></td> -->
        				</c:when>
        				<c:otherwise>
        					<td><button type="button" class="btn btn-primary" style="margin-top:3%;" onclick="deleteSubscription(${subscription.subscriptionId})">Delete Subscription</button></td>
<%--         					<td><input type="submit" name="adminSubscriptionDelete" id="sub_update" value="Delete Subscription" onclick="deleteSubscription(${subscription.subscriptionId})"/></td> --%>
        				</c:otherwise>
        			</c:choose>
        			
        		</tr>	
        	</c:forEach>
        </tbody>
	</table>
</div>

<div><button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal" style="margin-top:3%;margin-left:10%;width:70%;height:35%;">Update your Subscription Plan</button></div>

<form:form modelAttribute="subscription" method="post" action="" style="width:180%;" enctype="multipart/form-data">
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
					</button>
					<h4 class="modal-title" id="myModalLabel">
						Update Subscription Plans!
					</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label for="usr">Upload .xml file:</label>
						<input type="file" name="subscriptionFile" accept="*.xml" class="form-control" id="usr">
					</div>
					
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="Submit" class="btn btn-primary">Update</button>
					
				</div>
			</div>
		</div>
	</div>
</form:form>










<!-- 


<p style="font-size:50px;">Update your Subscription Plan:</p>
<div>
	<table>
		<tr>
		<td>${updateSubscriptionStatus}</td>
		</tr>
		<form:form modelAttribute="subscription" action="" method="post" enctype="multipart/form-data">
			<tr>
				<td>Upload You Subscription File:</td>
				<td><input type="file" name="subscriptionFile" accept="*.xml" /></td>
			</tr>
			<tr>
				<td><input type="submit" name="submit" value="Update Subscription" /></td>
			</tr>
		</form:form>
	</table>
</div>
-->