<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<div class="panel-body">
	<h4 class="errors">${message}</h4>
	<form:form action="/admin/master/index"
		modelAttribute="model" enctype="multipart/form-data">
		<form:errors path = "*" element="ul" class="errors"/>
		<div class="form-group">
			<label>Tên đăng nhập</label>
			<c:choose>
				<c:when test="${status==2}"> 
					<form:input path="id" cssClass="form-control" readonly="true"/>
				</c:when>
				<c:otherwise>
					<form:input path="id" cssClass="form-control"/>			
				</c:otherwise>
			</c:choose>
		</div>
		<div class="form-group">
			<label>Mật khẩu</label>
			<form:input path="password" cssClass="form-control"/>
		</div>
		<div class="form-group">
			<label>Họ tên</label>
			<form:input path="fullName" cssClass="form-control"/>
		</div>
		<div class="form-group">
			<label>Số điện thoại</label>
			<form:input path="mobile" cssClass="form-control"/>
		</div>
		<div class="form-group">
			<label>Email</label>
			<form:input path="email" cssClass="form-control"/>
		</div>
		<div class="form-group">
			<c:choose>
				<c:when test="${status!=2}"> 
					<button type="submit" class="btn btn-default" formaction="/admin/master/insert">Thêm</button>
				</c:when>
				<c:otherwise>
					<button type="submit" class="btn btn-default" formaction="/admin/master/update">Sửa</button>				
				</c:otherwise>
			</c:choose>
<!-- 			<button class="btn btn-default" formaction="/admin/master/insert">Thêm</button> -->
<!-- 			<button class="btn btn-default" formaction="/admin/master/update">Update</button> -->
<!-- 			<button class="btn btn-default" formaction="/admin/master/delete">Delete</button> -->
			<button class="btn btn-default" formaction="/admin/master/index">Hoàn tác</button> 
		</div>
	</form:form>
</div>