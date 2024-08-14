<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<table class="table">
	<thead>
		<tr>
<!-- 			<th>Id</th> -->
			<th>Tên sản phẩm</th>
			<th>Đơn giá</th>
			<th>Giảm giá</th>
			<th>Số lượng</th>
			<th>Tổng</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="p"
			items="${sessionScope['scopedTarget.cartService'].items}">
			<tr>
<%-- 				<td>${p.id}</td> --%>
				<td>${p.name}</td>
				<td>$${p.unitPrice}</td>
				<td>${p.discount}</td>
				<td>${p.quantity}</td>
				<td>$${p.quantity * p.unitPrice * (1 - p.discount)}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<h2>CHECKOUT</h2>
<h4>${message}</h4>
<form:form action="/order/checkout" modelAttribute="order">
	<form:errors path="*" element="ul" cssClass="errors"/>
	<form:hidden path="id" />
	<div class="row">
		<div class="form-group col-sm-4">
			<label>Tài khoản</label>
			<form:input path="customer.id" readonly="true"
				cssClass="form-control" />
		</div>
		<div class="form-group col-sm-4">
			<label>Ngày đặt</label>
			<form:input path="orderDate" readonly="true" cssClass="form-control" />
		</div>
		<div class="form-group col-sm-4">
			<label>Ngày giao dự kiến</label>
			<form:input path="requireDate" readonly="true" cssClass="form-control" />
		</div>
	</div>
	<div class="row">
		<div class="form-group col-sm-4">
			<label>Người nhận hàng</label>
			<form:input path="receiver" cssClass="form-control" />
		</div>
		<div class="form-group col-sm-4">
			<label>Địa chỉ</label>
			<form:input path="address" cssClass="form-control" />
		</div>
		<div class="form-group col-sm-4">
			<label>Tổng</label>
			<form:input path="amount" readonly="true" cssClass="form-control" />
		</div>
	</div>
	<div class="row">
		<div class="form-group col-sm-12">
			<label>Ghi chú</label>
			<form:textarea path="description" cssClass="form-control" rows="5"></form:textarea>
		</div>
		<div class="form-group col-sm-12">
			<button class="btn btn-default">Purchase</button>
		</div>
	</div>
</form:form>
