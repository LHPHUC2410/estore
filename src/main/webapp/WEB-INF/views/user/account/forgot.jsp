<%@ page pageEncoding="UTF-8"%>
<h2>QUÊN MẬT KHẨU</h2>
<h4>${message}</h4>
<form action="/account/forgot" method="post">
<div class="form-group" style="margin:top 10px">
	<label>Tên đăng nhập</label>
	<input name="id" style="width: 50%" class="form-control">
</div>
<div class="form-group">
	<label>Email</label>
	<input name="email" style="width: 50%" class="form-control" >
</div>

<div class="form-group">
	<button class="btn btn-default" style="background-color: #EA501F; color: white">Lấy lại mật khẩu</button>
</div>
</form>