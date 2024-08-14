<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<table class="table">
	<thead>
		<tr>
			<th>Id</th>
			<th>Tên</th>
			<th>Đơn giá</th>
			<th>Mô tả</th>
			<th>Loại</th>
			<th>Nhà cung cấp</th>
			<th>Thao tác</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="item" items="${list}">
		<tr>
			<td>${item.id}</td>
			<td>${item.name}</td>
			<td>${item.unitPrice}</td>
			<td>${item.unitBrief}</td>
			<td>${item.category.nameVN}</td>
			<td>${item.supplier.name}</td>
 			<td><a class="btn btn-default btn-warning" href="/admin/product/edit/${item.id}">Sửa</a>
 			<a class="btn btn-default btn-danger" href="/admin/product/delete/${item.id}">Xóa</a></td> 
 			 
<%-- 			<td><a href="/admin/product/edit/${item.id}">Edit</a></td> --%>
		</tr>
		</c:forEach>
	</tbody>
	</table>