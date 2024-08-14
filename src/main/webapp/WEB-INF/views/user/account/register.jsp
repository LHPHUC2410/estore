<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<h2>ĐĂNG KÝ TÀI KHOẢN</h2>
<h4>${message}</h4>
<form:form action="/account/register" 
modelAttribute="form" enctype="multipart/form-data">
<div class="form-group">
	<label>Tên đăng nhập</label>
	<form:input path="id" style="width: 50%" class="form-control"/>
</div>
<div class="form-group">
	<label>Mật khẩu</label>
	<form:input path="password" style="width: 50%" class="form-control"/>
</div>
<div class="form-group">
	<label>Họ và tên</label>
	<form:input path="fullname" style="width: 50%" class="form-control"/>
</div>
<div class="form-group">
	<label>Email</label>
	<form:input path="email" style="width: 50%" class="form-control"/>
</div>
<div class="form-group">
	<label>Ảnh đại diện</label>
	<input type="file" name="photo_file">
	<form:hidden path="photo" class="form-control"/>
</div>

<div class="form-group">
	<button class="btn btn-default" style="background-color: #EA501F; color: white">Đăng ký</button>
</div>
</form:form>