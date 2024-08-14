<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<h2>Thông tin đơn đặt hàng</h2>
	<form:form action="/order/checkout" modelAttribute="order">
		<form:hidden path="id"/>
		<div class="row">
			<div class="form-group col-sm-4">
				<label>Tài khoản</label>
				<form:input path="customer.id" readonly="true" cssClass="form-control"/>
			</div>
			<div class="form-group col-sm-4">
				<label>Ngày đặt</label>
				<form:input path="orderDate" readonly="true" cssClass="form-control"/>
			</div>
			<div class="form-group col-sm-4">
				<label>Ngày giao dự kiến</label>
				<form:input path="requireDate" readonly="true" cssClass="form-control"/>
			</div>
		</div>
		<div class="row">
			<div class="form-group col-sm-4">
				<label>Người nhận</label>
				<form:input path="receiver" readonly="true" cssClass="form-control"/>
			</div>
			<div class="form-group col-sm-4">
				<label>Địa chỉ</label>
				<form:input path="address" readonly="true" cssClass="form-control"/>
			</div>
			<div class="form-group col-sm-4">
				<label>Tổng đơn hàng $</label>
				<form:input path="amount" readonly="true" cssClass="form-control"/>
			</div>
		</div>
		<div class="row">
			<div class="form-group col-sm-12">
				<label>Ghi chú</label>
				<form:textarea path="description" readonly="true" cssClass="form-control" rows="5"></form:textarea>
			</div>
		</div>
	</form:form>	
	<h2>Chi tiết đơn hàng</h2>
	<table class="table">
	<thead>
		<tr>
<!-- 			<th>Id</th> -->
			<th>Tên sản phẩm</th>
			<th>Đơn giá</th>
			<th>Số lượng</th>
			<th>Giảm giá</th>
			<th>Thành tiền</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="d" items="${details}">
			<tr>
<%-- 				<td>${d.product.id}</td> --%>
				<td>${d.product.name}</td>
				<td>$${d.unitPrice}</td>
				<td>${d.quantity}</td>
				<td>${d.discount}</td>
				<td>$${d.unitPrice*d.quantity*(1-d.discount)}</td>
			</tr>
		</c:forEach>
	</tbody>
	</table>
