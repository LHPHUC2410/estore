<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<table class="table">
<thead>
	<tr>
		<th>Tên đăng nhập</th>
		<th>Họ tên</th>
		<th>Email</th>
		<th>Số điện thoại</th>
		<th>Thao tác</th>
	</tr>
</thead>
<tbody>
	<c:forEach var="item" items="${list}">
	<tr>
		<td>${item.id}</td>
		<td>${item.fullName}</td>
		<td>${item.email}</td>
		<td>${item.mobile}</td>
		<td>
			<a class="btn btn-warning" href="/admin/master/edit/${item.id}">Sửa</a>
			<a class="btn btn-danger" href="/admin/master/delete/${item.id}">Xóa</a>
		</td>
	</tr>
	</c:forEach>
</tbody>
</table>