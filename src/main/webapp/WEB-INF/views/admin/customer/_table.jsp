<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>


<table class="table">
	<thead>
		<tr>
			<th>Mã khách hàng</th>
			<th>Họ và tên</th>
			<th>Email</th>
			<th>Mật khẩu</th>
			<th>Ảnh</th>
			<th>Đã kích hoạt?</th>
			<th>Thao tác</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="item" items="${list}">
			<tr>
				<td>${item.id}</td>
				<td>${item.fullname}</td>
				<td>${item.email}</td>
				<td>${item.password}</td>
				<td>${item.photo}</td>
				<td>${item.activated?'Yes':'No'}</td>
				<td><a class="btn btn-sm btn-warning"
					href="/admin/customer/edit/${item.id}">Sửa</a> <a
					class="btn btn-sm btn-danger"
					href="/admin/customer/delete/${item.id}">Xóa</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>