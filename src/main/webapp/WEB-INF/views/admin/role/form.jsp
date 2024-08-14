<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<div class="panel-body">
	<h4 class="errors">${message}</h4>
	<form:form action="/admin/role/insert" modelAttribute="model">
		<form:errors path="*" element="ul" cssClass="errors" />
		<div class="form-group">
			<label>Mã vai trò</label>
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
			<label>Tên vài trò</label>
			<form:input path="name" cssClass="form-control" />
		</div>
		<div class="form-group">
			<c:choose>
				<c:when test="${status != 2}">
					<button type="submit" class="btn btn-default">Thêm</button>
				</c:when>
				<c:otherwise>
					<button type="submit" formaction="/admin/role/update"
						class="btn btn-default">Sửa</button>
				</c:otherwise>
			</c:choose>
			<button type="submit" formaction="/admin/role/index"
				class="btn btn-default">Hoàn tác</button>
		</div>
	</form:form>
</div>