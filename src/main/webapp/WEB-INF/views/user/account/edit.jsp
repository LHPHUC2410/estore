<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<h2>CHỈNH SỬA THÔNG TIN</h2>
	${message}
	<form:form action="/account/edit" modelAttribute="form"
		enctype="multipart/form-data">
		<div class="form-group">
			<label>Tên đăng nhập</label>
			<form:input path="id" style="width: 50%" cssClass="form-control" readonly="true"/>
		</div>
		<form:hidden path="password" cssClass="form-control"/>
		<div class="form-group">
			<label>Họ và tên</label>
			<form:input path="fullname" style="width: 50%" cssClass="form-control"/>
		</div>
		<div class="form-group">
			<label>Email</label>
			<form:input path="email" style="width: 50%" cssClass="form-control"/>
		</div>
		<div class="form-group">
			<label>Ảnh đại diện</label>
			<input type="file" name="photo_file">
			<form:hidden path="photo"/>
			<img src="/static/images/customers/${form.photo}" width="100">
		</div>
		<form:hidden path="activated"/>
		<div class="form-group">
			<button class="btn btn-primary">Cập nhật</button>
		</div>
	</form:form>