<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

	<div class="panel-body">
	<h4 class="errors">${message}</h4>
	<form:form action="/admin/supplier/insert"
		modelAttribute="model" enctype="multipart/form-data">
		<form:errors path = "*" element="ul" class="errors"/>
		<div class="form-group">
			<label>Id</label>
            <c:choose>
                <c:when test="${status == 2}">
                    <form:input path="id" cssClass="form-control" readonly="true" />
                </c:when>
                <c:otherwise>
                    <form:input path="id" cssClass="form-control" />
                </c:otherwise>
            </c:choose>
		</div>
		<div class="form-group">
			<label>Tên nhà cung cấp</label>
			<form:input path="name" cssClass="form-control"/>
		</div>
		<div class="form-group">
			<label>Số điện thoại</label>
			<form:input path="phone" cssClass="form-control"/>
		</div>
		<div class="form-group">
			<label>Email</label>
			<form:input path="email" cssClass="form-control"/>
		</div>
		<div class="form-group">
			<label>Logo</label>
			<input type="file" name="upLogo">
			<form:hidden path="logo"/>
		</div>
		<div class="form-group">
			<c:choose>
                <c:when test="${status != 2}">
                    <button type="submit" class="btn btn-default">Thêm</button>
                </c:when>
                <c:otherwise>
                    <button type="submit" formaction="/admin/supplier/update" class="btn btn-default">Sửa</button>
                </c:otherwise>
            </c:choose>
            <button type="submit" formaction="/admin/supplier/index" class="btn btn-default">Hoàn tác</button>
        </div>
	</form:form>
</div>