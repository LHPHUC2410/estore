<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>

<h3>HÀNG TỒN THEO ${fn:toUpperCase(group)}</h3>
<table class="table">
	<thead>
		<tr>
			<th>${group}</th>
			<th>Số lượng</th>
			<th>Giá trị</th>
			<th>Tối thiểu</th>
			<th>Tối đa</th>
			<th>Trung bình</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="item" items="${list}">
			<tr>
				<td>${item[0]}</td>
				<td>${item[1]}</td>
				<td>${item[2]}</td>
				<td>${item[3]}</td>
				<td>${item[4]}</td>
				<td><f:formatNumber value="${item[5]}" maxFractionDigits="2"
						minFractionDigits="2" /></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
