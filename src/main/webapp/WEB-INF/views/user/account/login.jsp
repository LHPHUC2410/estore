<%@ page pageEncoding="UTF-8"%>
<h2>ĐĂNG NHẬP</h2>
<h4>${message}</h4>
<form action="/account/login" method="post">
<div class="form-group">
	<label for="id">Tên đăng nhập</label>
	<input id="id" name="id" class="form-control" style="width: 50%" value="${uid}">
</div>
<div class="form-group">
	<label>Mật khẩu</label>
	<input type="password" name="pw" class="form-control" style="width: 50%" value="${pwd}" >
</div>
<div class="form-group">
	<label>Lưu mật khẩu</label>
	<input name="rm" type="checkbox" style="margin-top: 5px">
</div>

<div class="form-group">
	<button class="btn btn-default" style="background-color: #EA501F; color: white">Login</button>
</div>
</form>