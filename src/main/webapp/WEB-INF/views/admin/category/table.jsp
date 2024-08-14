<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<table class="table">
    <thead>
        <tr>
            <th>Id</th>
            <th>Tên Loại Hàng</th>
            <th></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="e" items="${list}">
            <tr>
                <td>${e.id}</td>
                <td>${e.nameVN}</td>
                <td>
                    <a class="btn btn-sm btn-warning" href="/admin/category/edit/${e.id}">Edit</a>
                    <a class="btn btn-sm btn-danger" href="/admin/category/delete/${e.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

