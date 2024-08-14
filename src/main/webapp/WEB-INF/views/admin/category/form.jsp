<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<div class="panel-body">
	<h4 class="errors">${message}</h4>
	<form:form action="/admin/category/index" modelAttribute="model">
		<form:errors path="*" element="ul" cssClass="errors" />
		<c:choose>
			<c:when test="${model.id > 0}">
				<div class="form-group">
					<label>Id</label>
					<form:input path="id" cssClass="form-control" readonly="true" />
				</div>
				<div class="form-group">
					<label>Tên loại hàng</label>
					<form:input path="nameVN" cssClass="form-control" />
				</div>
				<div class="form-group">
					<button class="btn btn-default" formaction="/admin/category/update">Thay
						đổi</button>
					<button class="btn btn-default" formaction="/admin/category/index">Hoàn tác</button>
				</div>
			</c:when>
			<c:otherwise>
				<div class="form-group">
					<label>Tên loại hàng</label>
					<form:input path="nameVN" cssClass="form-control" />
				</div>
				<div class="form-group">
					<button class="btn btn-default" formaction="/admin/category/insert">Thêm</button>
					<button class="btn btn-default">Hoàn tác</button>
				</div>
			</c:otherwise>
		</c:choose>
	</form:form>

</div>