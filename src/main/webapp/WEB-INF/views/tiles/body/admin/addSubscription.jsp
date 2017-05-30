<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<br><br><br><br><br>
<div>
	<table>
		<tr></tr>
		<form:form modelAttribute="subscription" action="" method="post" enctype="multipart/form-data">
			<tr>
				<td>Upload You Subscription File:</td>
				<td><input type="file" name="subscriptionFile" accept="*.xml" /></td>
			</tr>
			<tr>
				<td><input type="submit" name="submit" value="Upload" /></td>
			</tr>
		</form:form>
	</table>
</div>