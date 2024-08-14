<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<table class="table">
<thead>
	<tr>
		<th>Id</th>
		<th>Name</th>
		<th></th>
	</tr>
</thead>
<tbody>
	<c:forEach var="item" items="${list}">
	<tr>
		<td>${item.id}</td>
		<td>${item.name}</td>
		<td><a class="btn btn-sm btn-warning" href="/admin/role/edit/${item.id}">Edit</a>
		<a class="btn btn-sm btn-danger" href="/admin/role/delete/${item.id}">Delete</a>
		</td>
	</tr>
	</c:forEach>
</tbody>
</table>