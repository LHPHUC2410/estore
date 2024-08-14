<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<div class="panel-body">
	${message}
	<form:form action="/admin/order/index" modelAttribute="model">
		<c:if test="${model.id > 0}">
			<div class="form-group">
				<label>Id</label>
				<form:input path="id" cssClass="form-control" readonly="true"/>
			</div>
		</c:if>
		<div class="form-group">
			<label>Customer</label>
			<form:input path="customer.id" cssClass="form-control"/>
		</div>
		<div class="form-group">
			<label>Order Date</label>
			<form:input path="orderDate" cssClass="form-control"/>
		</div>
		<div class="form-group">
			<label>Required Date</label>
			<form:input path="requireDate" cssClass="form-control"/>
		</div>
		<div class="form-group">
			<label>Receiver</label>
			<form:input path="receiver" cssClass="form-control"/>
		</div>
		<div class="form-group">
			<label>Address</label>
			<form:input path="address" cssClass="form-control"/>
		</div>
		<div class="form-group">
			<label>Amount</label>
			<form:input path="amount" cssClass="form-control"/>
		</div>
		<div class="form-group">
			<label>Notes</label>
			<form:textarea path="description" rows="5" cssClass="form-control"></form:textarea>
		</div>
		<div class="form-group">
			<button class="btn btn-default" formaction="/admin/order/insert">Insert</button>
			<button class="btn btn-default" formaction="/admin/order/update">Update</button>
			<button class="btn btn-default" formaction="/admin/order/delete">Delete</button>
			<button class="btn btn-default" formaction="/admin/order/index">Reset</button>
		</div>
	</form:form>
</div>
