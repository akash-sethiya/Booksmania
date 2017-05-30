<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>



<br><br>
<div class="container-fluid well span6" style="margin-left:30%;margin-right:30%;">
	<div class="row-fluid" style="text-align:center;">
        <div class="span2" >
        	<c:if test="${user.getUserDetails().gender=='F'}">
		    <img src="<c:url value="/resources/images/girl.png"/>" class="img-circle" style="width:10%;height:5%;">
		    </c:if>
		    
		    <c:if test="${user.getUserDetails().gender=='M'}">
		    <img src="<c:url value="/resources/images/boy1.jpg"/>" class="img-circle" style="width:10%;height:5%;">
		    </c:if>
        </div>
        
        <div class="span8">
            <h1><p>${user.getUserDetails().name}</p></h1>
			<hr>
			<table style="margin-left:15%;">
			<tr><td><h4><p>Email:&nbsp;</td><td>${user.email}</p></h4></td></tr>
			
			<c:if test="${user.getUserDetails().gender=='M'}"><tr><td><h4><p>Gender: </td><td>Male</p></h4></td></tr></c:if>
			<c:if test="${user.getUserDetails().gender=='F'}"><tr><td><h4><p>Gender: </td><td>Female</p></h4></td></tr></c:if>
			
            <tr><td><h4><p>Date Of Birth:&nbsp;</td><td>${user.getUserDetails().dob}</p></h4></td></tr>
            <tr><td><h4><p>Address:&nbsp;</td><td>${user.getUseraddress().primaryAddress}&nbsp;${user.getUseraddress().secondaryAddress}</p></h4></td></tr>
            <tr><td><h4><p>City:&nbsp;</td><td>${user.getUseraddress().city}</p></h4></td></tr>
            <tr><td><h4><p>State:&nbsp;</td><td>${user.getUseraddress().state}</p></h4></td></tr>
            <tr><td><h4><p>Contact:&nbsp;</td><td>${user.getUserDetails().userContactInfo}</p></h4></td></tr>
            <tr><td><h4><p>Active Subscription Plan:&nbsp;</td><td>${user.getSubscription().subscriptionName}</p></h4></td></tr>
            <tr><td><h4><p>Subscription Plan Begins From:&nbsp;</td><td>${user.getUserDetails().subscriptionStartDate}</p></h4></td></tr>
            <tr><td><h4><p>Subscription Plan Ends on:&nbsp;</td><td>${user.getUserDetails().subscriptionEndDate}</p></h4></td></tr>
			</table>
        </div>
        
        <div class="span2" style="margin-top:5%;">
            <button type="submit" class="btn btn-success" data-toggle="modal" data-target="#myModal">Update my Profile</button>
        </div>
	</div>
</div>

<!--------------------------------------------------- User update Profile------------------------------------->
<c:url var="updateURL" value="update" />
<form:form modelAttribute="subscription" action="${updateURL}" method="post" style="width:180%;">
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
					</button>
					<h4 class="modal-title" id="myModalLabel">
						Update Your Profile!
					</h4>
				</div>
				<div class="modal-body">
					<p>Personal Information</p>
					<hr>
						<input type="hidden" name="userId" id="userId" value="${user.userId}" readonly="true" />
			<input type="hidden" name="userDetailsId" id="userId" value="${user.getUserDetails().userDetailsId}" readonly="true" />
			<input type="hidden" name="addresId" id="userId" value="${user.getUseraddress().addresId}" readonly="true" />
					<div class="form-group">
						<label for="usr">Name:</label>
						<input type="text"  name="name" class="form-control" id="usr" value="${user.getUserDetails().name}">
					</div>
					<div class="form-group">
						<label for="usr">Email:</label>
						<input type="text" name="email" class="form-control" id="usr" readOnly="readonly" value="${user.email}">
					</div>
					<div class="form-group">
						<label for="options">Gender:</label><br>
						<div class="btn-group" data-toggle="buttons">
							<label class="btn btn-primary">
								<input type="radio" name="gender" value="M" id="option1" ${user.getUserDetails().gender=='M'?'checked':''}> Male
							</label>
							<label class="btn btn-primary">
								<input type="radio" name="gender" value="F" id="option2" ${user.getUserDetails().gender=='F'?'checked':''}> Female
							</label>
						</div>
					</div>
					
					<div class="form-group">
						<label for="usr">Date of Birth:</label>
						<input type="date" name="dob" class="form-control" id="usr" readonly="readonly" value="${user.getUserDetails().dob}">
					</div>
					<hr><p>Contact Information</p><hr>
           
					<div class="form-group">
						<label for="usr">Primary Address:</label>
						<input type="textarea" name="primaryAddress" class="form-control input-xlarge" id="usr" value="${user.getUseraddress().primaryAddress}">
					</div>
					<div class="form-group">
						<label for="usr">Secondary Address:</label>
						<input type="textarea" name="secondaryAddress" class="form-control input-xlarge" id="usr" value="${user.getUseraddress().secondaryAddress}">
					</div>
					<div class="form-group">
						<label for="usr">Zip Code:</label>
						<input type="number" name="zIP" class="form-control" id="usr" value="${user.getUseraddress().ZIP }">
					</div>                         
					<div class="form-group">
						<label for="usr">State:</label>
						<select class="form-control" name="state">
							<option value="MP">Madhya Pradesh</option>
						</select>
					</div>
                    <div class="form-group">
						<label for="usr">City:</label>
						<select class="form-control" name="city">
							<option value="Indore">Indore</option>
						</select>
					</div>
                    <div class="form-group">
						<label for="usr">Country:</label>
							<select class="form-control" name="country">
								<option value="India">India</option>
							</select>
					</div>
					<div class="form-group">
						<label for="usr">Contact No.:</label>
						<input type="number" name="userContactInfo" class="form-control" id="usr" value="${user.getUserDetails().userContactInfo}"/>
					</div>                         
					<div class="form-group">
						<label for="usr">Your Subscription Plan:</label>
						<input type="text"  name="subscriptionName" class="form-control" id="usr" readOnly="readOnly" value="${user.getSubscription().subscriptionName}"/>
						<input type="hidden" name="subscriptionId" class="form-control" value="${user.getSubscription().subscriptionId}"/>
					</div>
					
					<!--<div class="form-group">
						<table class="table table-bordered" data-sort-name="name" data-sort-order="desc">
							<tr><th>Subscription Name</th><th>Subscription Details</th><th>Max Book Can Borrow</th><th>Amount</th><th>Subscribe Please</th></tr>
							<tr><td>Subscription Name</td><td>Subscription Details</td><td>Max Book Can Borrow</td><td>Amount</td><td></td></tr>
							<tr><td>Subscription Name</td><td>Subscription Details</td><td>Max Book Can Borrow</td><td>Amount</td><td></td></tr>
							
						</table>
					</div>-->
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="submit" class="btn btn-primary">Update</button>
				</div>
			</div>
		</div>
	</div>
</form:form>







<style>
h4{margin-top:5%;}
</style>
