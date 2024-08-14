<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<table class="table">
	<thead>
		<tr>
			<th>Mã nhà cung cấp</th>
			<th>Tên</th>
			<th>Email</th>
			<th>Số điện thoại</th>
			<th>Logo</th>
			<th>Thao tác</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="item" items="${list}">
			<tr>
				<td>${item.id}</td>
				<td>${item.name}</td>
				<td>${item.email}</td>
				<td>${item.phone}</td>
				<td>${item.logo}</td>
				<td><a class="btn btn-warning"
					href="/admin/supplier/edit/${item.id}">Sửa </a> <a
					class="btn btn-danger" href="/admin/supplier/delete/${item.id}">Xóa</a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>