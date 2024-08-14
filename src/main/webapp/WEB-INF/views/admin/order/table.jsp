<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<table class="table" border="1">
	<thead>
		<tr>
			<th>ID</th>
			<th>Customer ID</th>
			<th>Order Date</th>
			<th>Amount</th>
			<th>Receiver</th>
<!-- 			<th>Action</th> -->
		</tr>
	</thead>
	<tbody>
		<c:forEach var="item" items="${list}">
			<tr>
				<td>${item.id}</td>
				<td>${item.customer.id}</td>
				<td>${item.orderDate}</td>
				<td>${item.amount}</td>
				<td>${item.receiver}</td>
<%-- 				<td><a href="/admin/order/detail/${item.id}" --%>
<!-- 					class="detail-link">Detail</a></td> -->
			</tr>
		</c:forEach>
	</tbody>
</table>
<a class="btn btn-success" href="#">Quay lại</a>
