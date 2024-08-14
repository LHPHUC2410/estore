<%@ page pageEncoding="UTF-8"%>
<h2>ĐỔI MẬT KHẨU</h2>
<h4>${message}</h4>
<form action="/account/change" method="post">
<div class="form-group">
	<label>Tên đăng nhập</label>
	<input name="id" style="width: 50%" class="form-control">
</div>
<div class="form-group">
	<label>Mật khẩu hiện tại</label>
	<input name="pw" style="width: 50%" class="form-control" >
</div>
<div class="form-group">
	<label>Mật khẩu mới</label>
	<input name="pw1" style="width: 50%" class="form-control" >
</div>
<div class="form-group">
	<label>Nhập lại mật khẩu mới</label>
	<input name="pw2" style="width: 50%" class="form-control" >
</div>

<div class="form-group">
	<button class="btn btn-default" style="background-color: #EA501F; color: white">Đổi mật khẩu</button>
</div>
</form>