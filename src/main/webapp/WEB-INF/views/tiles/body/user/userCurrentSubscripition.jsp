<%@taglib uri="/WEB-INF/customFunctions.tld" prefix="func" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<script type="text/javascript">

$(document).ready(function() {
    $('#example').dataTable( {
        stateSave: true
        
    } );
} );
</script>
<div>
	<div><p style="font-size:20px;">Your Selected Subscription Plan</p></div>
	<table id="example" class="display" cellspacing="0" width="100%">
         <thead style="background-color:#000000;color:grey;">
            <tr>
                <th>Name</th>
                <th>Details</th>
                <th>Maximum Book can Borrow</th>
                <th>Amount</th>
                <th>Period/Duration</th>
                <th>No of Books Requested</th>
                <th>Subscription Status</th>
                <th>Renew this Plan</th>
            </tr>
        </thead>
 
        <tbody>
        	<tr>
        		<td><c:out value="${user.getSubscription().subscriptionName}"/></td>
        		<td><c:out value="${user.getSubscription().subscriptionDetails}"/></td>
        		<td><c:out value="${user.getSubscription().maxBook}"/></td>
        		<td><c:out value="${user.getSubscription().subscriptionAmount}"/></td>
        		<td><c:out value="${user.getSubscription().periodOfSubscription}"/></td>
        		<td><c:out value="${user.requestBookCount}"/></td>
        		<td><c:out value="${user.getSubscription().subscriptionStatus}"/></td>
        		<td>
       			<c:choose>
        			<c:when test="${user.status}">
        				<button type="button" class="btn btn-danger" style="margin-top:3%;">Activated</button>
        			</c:when>
        			<c:otherwise>
        				<button type="button" class="btn btn-danger" style="margin-top:3%;" onclick="updateSubscription(${user.getSubscription().subscriptionId})">Renew this Subscription</button>
        			</c:otherwise>
        			</c:choose>
        			
        		</td>
        	</tr>
        </tbody>
	</table>
</div>

<div style="margin-top:55px;">
	<div><p style="font-size:20px;">Available Subscription Plan</p></div>
	<table id="example1" class="display" cellspacing="0" width="100%">
         <thead style="background-color:#000000;color:grey;">
            <tr>
                <th>Name</th>
                <th>Details</th>
                <th>Maximum Book can Borrow</th>
                <th>Amount</th>
                <th>Period/Duration</th>
                <th>Subscription Status</th>
                <th>Select Subscription</th>
            </tr>
        </thead>
 
        <tbody>
        	<c:forEach items="${subscriptionList}" var="subscription">
        		<tr>
        			<td><c:out value="${subscription.subscriptionName}"/></td>
        			<td><c:out value="${subscription.subscriptionDetails}"/></td>
        			<td><c:out value="${subscription.maxBook}"/></td>
        			<td><c:out value="${subscription.subscriptionAmount}"/></td>
        			<td><c:out value="${subscription.periodOfSubscription}"/></td>
        			<td><c:out value="${subscription.subscriptionStatus}"/></td>
        			<c:choose>
        				<c:when test="${user.status}">
        					<td><button type="button" class="btn btn-danger" style="margin-top:3%;">User already Activated</button></td>
        				</c:when>
        				<c:otherwise>
        					<td><button type="button" class="btn btn-danger" style="margin-top:3%;" onclick="updateSubscription(${subscription.subscriptionId})">Select me!</button></td>
        				</c:otherwise>
        			</c:choose>
        		</tr>
        	</c:forEach>
		</tbody>
	</table>
</div>

<div>
	<div><p>You Subscription History</p></div>
	<table id="example3" class="display" cellspacing="0" width="100%">
         <thead style="background-color:#000000;color:grey;">
            <tr>
                <th>Subscription Name</th>
                <th>Subscription Taken Date</th>
                <th>Subscription Expired Date</th>
                <th>No. of Book Requested</th>
            </tr>
        </thead>
 
        <tbody>
        	<c:if test="${(fn:length(subscriptionHistory) - 1)>=0}">
        		<c:forEach begin="0" end="${fn:length(subscriptionHistory) - 1}" varStatus="loop">
        			<tr>
        				<td>${userSubscriptionService[loop.index].subscriptionName}</td>
        				<td>${subscriptionHistory[loop.index].subscriptionStartDate}</td>
        				<td>${subscriptionHistory[loop.index].subscriptionEndDate}</td>
        				<td>${subscriptionHistory[loop.index].requestedBookCount}</td>
        			</tr>
        		</c:forEach>
        	</c:if>
        </tbody>
       </table>	
</div>

<script type="text/javascript">
	function updateSubscription(value)
	{
		window.location.href="/Booksmania/welcome/updateUserSubscription?id=".concat(value);
				
	}
</script>