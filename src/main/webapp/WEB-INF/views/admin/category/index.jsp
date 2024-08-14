<%@ page pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<h3>Quản lý loại mặt hàng</h3>
	
	<ul class="nav nav-tabs">
		<li class="active"><a data-toggle="tab" href="#edit">Thêm/Thay đổi</a></li>
		<li><a data-toggle="tab" href="#list">Danh sách loại hàng</a></li>
	</ul>

	<div class="tab-content">
		<div id="edit" class="tab-pane fade in active">
			<!-- FORM -->
			<jsp:include page="form.jsp" />
		</div>
		<div id="list" class="tab-pane fade">
			<!-- BANG -->
			<jsp:include page="table.jsp" />
		</div>
	</div>
</body>
</html>