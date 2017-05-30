<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="updateProfile">
<div id="updateProfileMenu">Update your Profile</div>
${updateMessage}
	<table>
		<c:url var="updateURL" value="update" />
		<form:form modelAttribute="subscription" action="${updateURL}" method="post">
			
			<input type="hidden" name="userId" id="userId" value="${user.userId}" readonly="true" />
			<input type="hidden" name="userDetailsId" id="userId" value="${user.getUserDetails().userDetailsId}" readonly="true" />
			<input type="hidden" name="addresId" id="userId" value="${user.getUseraddress().addresId}" readonly="true" />
			<tr>
				<td>Name:</td>
				<td><input type="text" name="name" id="1username" value="${user.getUserDetails().name}" /></td>
			</tr>
			
			<tr>
				<td>Gender</td>
				<td>
					<input type="radio" name="gender" id="1gender" value="M" ${user.getUserDetails().gender=='M'?'checked':''}/>Male
					<input type="radio" name="gender" id="1gender" value="F" ${user.getUserDetails().gender=='F'?'checked':''}/>Female
				</td>
			</tr>
			
			<tr>
				<td>Date of Birth</td>
				<td><input type="date" name="dob" id="1dob" value="${user.getUserDetails().dob}" readonly="readonly"/></td>	
			</tr>
			
			<tr>
				<td>Primary Address:</td>
				<td><input type="textarea" name="primaryAddress" id="1address" value="${user.getUseraddress().primaryAddress}"/></td>
			</tr>
			
			<tr>
				<td>Secondary Address:</td>
				<td><input type="textarea" name="secondaryAddress" id="1address" value="${user.getUseraddress().secondaryAddress}"/></td>
			</tr>
			
			<tr>
				<td>ZIP:</td>
				<td><input type="number" name="zIP" id="1ZIP" value="${user.getUseraddress().zIP }"/></td>
			</tr>
			
			<tr>
				<td>City: </td>
				<td><input type="text" name="city" id="1city" value="${user.getUseraddress().city}"/></td>
			</tr>
			
			<tr>
				<td>State: </td>
				<td><input type="text" name="state" id="1state" value="${user.getUseraddress().state}"/></td>
			</tr>
			
			<tr>
				<td>Contact No: </td>
				<td><input type="number" name="userContactInfo" id="1contact" value="${user.getUserDetails().userContactInfo}"/></td>
			</tr>
			
			<tr>
				<td>Email:</td>
				<td><input type="email" name="email" id="1email" value="${user.email}" readonly="true"/></td>
			</tr>
			
			<tr>
				<td>Active Subscription Plan</td>
				<td><input type="text" name="subscriptionName" value="${user.getSubscription().subscriptionName}" readonly="true"/></td>
				<td><input type="hidden" name="subscriptionId" value="${user.getSubscription().subscriptionId}" /></td>
			</tr>
			
			<tr>
				<td><input type="submit" name="submit" value="Update" id="1submit" /></td>
				<td><input type="reset" name="reset" value="Reset" id="1reset" /></td>
			</tr>
			
		</form:form>
	</table>
</div>