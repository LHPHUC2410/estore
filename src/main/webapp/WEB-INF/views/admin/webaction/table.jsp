<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<table class="table">
<thead>
	<tr>
		<th>Mã chức năng</th>
		<th>Tên chức năng</th>
		<th>Mô tả chức năng</th>
		<th></th>
	</tr>
</thead>
<tbody>
	<c:forEach var="item" items="${list}">
	<tr>
		<td>${item.id}</td>
		<td>${item.name}</td>
		<td>${item.description}</td>
		<td><a class="btn btn-default btn-warning" href="/admin/webaction/edit/${item.id}">Sửa</a>
		<a class="btn btn-default btn-danger" href="/admin/webaction/delete/${item.id}">Xóa</a></td>
	</tr>
	</c:forEach>
</tbody>
</table>