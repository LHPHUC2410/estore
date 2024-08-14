<%@ page pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Insert title here</title>
</head>
<body>
	
	<div>
		<div class="text-center col-sm-5">
			<img src="/static/images/Registration.png" class="img-responsve">
		</div>
		<form action="/admin/home/login" method="post" class="col-sm-7">
			<h2>LOGIN</h2>
			<div class="form-group">
				<label>Username</label>
				<input name="id" class="form-control">
			</div>
			<div class="form-group">
				<label>Password</label>
				<input name="password" class="form-control">
			</div>
			<div class="form-group">
				<button class="btn btn-primary">Login</button>
			</div>
			<div>${message}</div>
		</form>
	</div>
</body>
</html>