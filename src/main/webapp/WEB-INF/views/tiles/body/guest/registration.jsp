<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<br>
<br>
<div class="stepwizard">
	<div class="stepwizard-row setup-panel">
		<div class="stepwizard-step">
			<a href="#step-1" type="button" class="btn btn-primary btn-circle">1</a>
			<p>Personal Information</p>
		</div>
		<div class="stepwizard-step">
			<a href="#step-2" type="button" class="btn btn-default btn-circle"
				disabled="disabled">2</a>
			<p>Contact Information</p>
		</div>
		<div class="stepwizard-step">
			<a href="#step-3" type="button" class="btn btn-default btn-circle"
				disabled="disabled">3</a>
			<p>Choose your Subscription</p>
		</div>
	</div>
</div>
<div style="color: green; margin-left: 50%;">${successMSG }</div>
<form:form modelAttribute="subscription" role="form" method="get"
	action="registration">
	<div class="row setup-content" id="step-1">
		<div class="col-xs-12">
			<div class="col-md-12">
				<h3 style="margin-left:30%;">Personal Information</h3>
				<div class="form-group">
					<label class="control-label" style="margin-left: 30%;">Name</label>
					<input maxlength="100" type="text" name="name" required="required"
						class="form-control" placeholder="Required"
						style="width: 35%; margin-left: 30%;" />
				</div>
				<div class="form-group">
					<label class="control-label" style="margin-left: 30%;">Email</label>
					<input maxlength="100" type="email" name="email"
						required="required" class="form-control" placeholder="Required"
						style="width: 35%; margin-left: 30%;" />
				</div>

				<div class="form-group">
					<label class="control-label" style="margin-left: 30%;">Password</label>
					<input maxlength="100" type="password" name="password"
						required="required" class="form-control" placeholder="Requried"
						style="width: 35%; margin-left: 30%;" />
				</div>

				<div class="form-group">
					<label class="control-label" style="margin-left: 30%;">Confirm
						Password</label> <input maxlength="100" type="password"
						required="required" class="form-control" placeholder=""
						style="width: 35%; margin-left: 30%;" />
				</div>

				<div class="form-group">
					<label class="control-label" style="margin-left: 30%;">Gender</label>
					<div class="btn-group" data-toggle="buttons">
						<label class="btn btn-primary active"> <input type="radio"
							name="gender" id="option1" value="M" checked> Male
						</label> <label class="btn btn-primary"> <input type="radio"
							name="gender" id="option2" value="F"> Female
						</label>
					</div>
				</div>

				<div class="form-group">
					<label class="control-label" style="margin-left: 30%;">Date
						of Birth</label> <input maxlength="100" type="date" name="dob"
						required="required" class="form-control" placeholder="Required"
						style="width: 35%; margin-left: 30%;" />
				</div>

				<div class="form-group">
					<label class="control-label" style="margin-left: 30%;">Language</label>
					<select class="form-control" name="languageId"
						style="width: 35%; margin-left: 30%;">
						<c:forEach items="${languageList}" var="lang">
							<option value="${lang.languageId}">${lang.language}</option>
						</c:forEach>
					</select>
				</div>

				<button class="btn btn-primary nextBtn btn-lg pull-right"
					type="button">Next</button>
			</div>
		</div>
	</div>
	<div class="row setup-content" id="step-2">
		<div class="col-xs-12">
			<div class="col-md-12">
				<h3 style="margin-left:30%;">Contact Information</h3>
				<div class="form-group">
					<label class="control-label" style="margin-left: 30%;">Primary
						Address</label> <input maxlength="200" type="text" name="primaryAddress"
						required="required" class="form-control" placeholder="Required"
						style="width: 35%; margin-left: 30%;" />
				</div>
				<div class="form-group">
					<label class="control-label" style="margin-left: 30%;">Secondary
						Address</label> <input maxlength="200" type="text" name="secondaryAddress"
						class="form-control" placeholder="Required"
						style="width: 35%; margin-left: 30%;" />
				</div>
				<div class="form-group">
					<label class="control-label" style="margin-left: 30%;">ZIP</label>
					<input maxlength="6" type="number" name="zIP" required="required"
						class="form-control" placeholder="Required"
						style="width: 35%; margin-left: 30%;" />
				</div>
				<div class="form-group">
					<label class="control-label" style="margin-left: 30%;">City</label>
					<select class="form-control" name="city"
						style="width: 35%; margin-left: 30%;">
						<option value="Indore">Indore</option>
					</select>
				</div>
				<div class="form-group">
					<label class="control-label" style="margin-left: 30%;">State</label>
					<select class="form-control" name="state"
						style="width: 35%; margin-left: 30%;">
						<option value="MP">Madhya Pradesh</option>
					</select>
				</div>
				<div class="form-group">
					<label class="control-label" style="margin-left: 30%;">Country</label>
					<select class="form-control" name="country"
						style="width: 35%; margin-left: 30%;">
						<option value="India">India</option>
					</select>
				</div>
				<div class="form-group">
					<label class="control-label" style="margin-left: 30%;">Contact</label>
					<input maxlength="10" type="number" name="userContactInfo"
						required="required" class="form-control" placeholder="Required"
						style="width: 35%; margin-left: 30%;" />
				</div>
				<button class="btn btn-primary nextBtn btn-lg pull-right"
					type="button">Next</button>
			</div>
		</div>
	</div>
	<div class="row setup-content" id="step-3">
		<div class="col-xs-12">
			<div class="col-md-12">
				<h3 style="margin-left:30%;">Choose Subscription Plan</h3>
				<div class="form-group">
					<label class="control-label" style="margin-left: 30%;">Subscription
						Plan</label> <input maxlength="10" type="hidden" name="subscriptionId"
						id="subscriptionId" class="form-control"
						style="width: 35%; margin-left: 30%;" readOnly="readOnly" /> <input
						maxlength="10" type="text" name="subscriptionName"
						id="subscriptionName" class="form-control"
						style="width: 35%; margin-left: 30%;" readOnly="readOnly" /> <a
						href="#" data-toggle="modal" data-target="#subscriptionModal"
						style="margin-left: 30%;">Click here to see Subscriptions List</a>
				</div>


				<button class="btn btn-success btn-lg pull-right" type="submit">Register!</button>
			</div>
		</div>
	</div>
</form:form>

<div class="modal fade" id="subscriptionModal" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog" style="width: 100%;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">Available
					Subscription Plan!</h4>
			</div>
			<div class="modal-body">
				<table class="table table-striped" id="subscriptionTableData"
					style="width: 100;">
					<tr id="tableRow">
						<th id="tableHeading"></th>
						<c:forEach items="${subscriptions }" var="subscription">
							<th id="tableHeading">${subscription.subscriptionName}</th>
						</c:forEach>
					</tr>

					<tr id="tableRow">
						<td id="tableHeading">Subscription Id</td>
						<c:forEach items="${subscriptions}" var="subscription">
							<td id="tableColumn">${subscription.subscriptionId}</td>
						</c:forEach>

					</tr>
					<tr id="tableRow">
						<td id="tableHeading">Details</td>
						<c:forEach items="${subscriptions }" var="subscription">
							<td id="tableColumn">${subscription.subscriptionDetails}</td>
						</c:forEach>
					</tr>
					<tr id="tableRow">
						<td id="tableHeading">Maximum Books Borrow</td>
						<c:forEach items="${subscriptions }" var="subscription">
							<td id="tableColumn">${subscription.maxBook}</td>
						</c:forEach>
					</tr>

					<tr id="tableRow">
						<td id="tableHeading">Period</td>
						<c:forEach items="${subscriptions }" var="subscription">
							<td id="tableColumn">${subscription.periodOfSubscription}</td>
						</c:forEach>
					</tr>
					<tr id="tableRow">
						<td id="tableHeading">Amount</td>
						<c:forEach items="${subscriptions }" var="subscription">
							<td id="tableColumn">${subscription.subscriptionAmount}</td>
						</c:forEach>
					</tr>
					<tr id="tableRow">
						<td id="tableHeading"></td>
						<c:forEach items="${subscriptions }" var="subscription">
							<td><input type="button" data-dismiss="modal"
								value="SUBSCRIBE" class="btn btn-primary"
								name="${subscription.subscriptionName}"
								id="${subscription.subscriptionId}"
								onclick="myfun(this.id,this.name)" /></td>
						</c:forEach>
					</tr>
				</table>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>



<style>
#tableHeading {
	word-wrap: break-word;
}

body {
	margin-top: 40px;
}

.stepwizard-step p {
	margin-top: 10px;
}

.stepwizard-row {
	display: table-row;
}

.stepwizard {
	display: table;
	width: 100%;
	position: relative;
}

.stepwizard-step button[disabled] {
	opacity: 1 !important;
	filter: alpha(opacity = 100) !important;
}

.stepwizard-row:before {
	top: 14px;
	bottom: 0;
	position: absolute;
	content: " ";
	width: 100%;
	height: 1px;
	background-color: #ccc;
	z-order: 0;
}

.stepwizard-step {
	display: table-cell;
	text-align: center;
	position: relative;
}

.btn-circle {
	width: 30px;
	height: 30px;
	text-align: center;
	padding: 6px 0;
	font-size: 12px;
	line-height: 1.428571429;
	border-radius: 15px;
}
</style>

<script type="text/javascript">
$(document).ready(function () {

    var navListItems = $('div.setup-panel div a'),
            allWells = $('.setup-content'),
            allNextBtn = $('.nextBtn');

    allWells.hide();

    navListItems.click(function (e) {
        e.preventDefault();
        var $target = $($(this).attr('href')),
                $item = $(this);

        if (!$item.hasClass('disabled')) {
            navListItems.removeClass('btn-primary').addClass('btn-default');
            $item.addClass('btn-primary');
            allWells.hide();
            $target.show();
            $target.find('input:eq(0)').focus();
        }
    });

    allNextBtn.click(function(){
        var curStep = $(this).closest(".setup-content"),
            curStepBtn = curStep.attr("id"),
            nextStepWizard = $('div.setup-panel div a[href="#' + curStepBtn + '"]').parent().next().children("a"),
            curInputs = curStep.find("input[type='text'],input[type='url'],input[type='email'],input[type='password'],input[type='date']"),
            isValid = true;

        $(".form-group").removeClass("has-error");
        for(var i=0; i<curInputs.length; i++){
            if (!curInputs[i].validity.valid){
                isValid = false;
                $(curInputs[i]).closest(".form-group").addClass("has-error");
            }
        }

        if (isValid)
            nextStepWizard.removeAttr('disabled').trigger('click');
    });

    $('div.setup-panel div a.btn-primary').trigger('click');
});
</script>

<script type="text/javascript">
function myfun(id,name)
{
	$('#subscriptionId').val(id);
	$('#subscriptionName').val(name);
}


</script>