<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<h2>ORDER LIST</h2>
<table class="table table-hover">
<thead>
	<tr>
<!-- 	<th>Id</th> -->
	<th>Ngày đặt</th>
	<th>Người nhận</th>
	<th>Địa chỉ</th>
	<th>Tổng</th>
	<th>Thao tác</th>
	</tr>
</thead>
<tbody>
<c:forEach var="o" items="${orders}">
	<tr>
<%-- 				<td>${o.id}</td> --%>
				<td>${o.orderDate}</td>
				<td>${o.receiver}</td>
				<td>${o.address}</td>
				<td>$${o.amount}</td>
	<td>
		<a href ="/order/detail/${o.id}" class="btn btn-sm btn-warning">
			Detail
		</a>
	</td>
	</tr>
</c:forEach>
</tbody>
</table>