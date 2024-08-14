<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Trang quản lý hàng hóa</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

</head>
<body>
	<h3>Trang quản lý hàng hóa</h3>

	<ul class="nav nav-tabs">
		<li class="active"><a data-toggle="tab" href="#edit">Thông
				tin</a></li>
		<li><a data-toggle="tab" href="#list">Danh sách</a></li>
	</ul>

	<div class="tab-content">
		<div id="edit" class="tab-pane fade in active">
			<!-- FORM -->
			<jsp:include page="_form.jsp" />
		</div>
		<div id="list" class="tab-pane fade">
			<!-- BANG -->
			<ul class="pager">
				<li><a href="#" class="first-page"><span
						class="glyphicon glyphicon-fast-backward"></span></a></li>
				<li><a href="#" class="prev-page"><span
						class="glyphicon glyphicon-backward"></span></a></li>
				<li><a href="#" class="next-page"><span
						class="glyphicon glyphicon-forward"></span></a></li>
				<li><a href="#" class="last-page"><span
						class="glyphicon glyphicon-fast-forward"></span></a></li>
			</ul>
			<div id="table">
				<jsp:include page="table.jsp" />
			</div>
		</div>
	</div>
<script>
	$(function() {
		var pageNo = 0;
		var pageSize = 10;
		var pageCount;

		function loadPage() {
		    $.ajax({
		        url: "/admin/product/load",
		        data: {
		            pageNo: pageNo,
		            pageSize: pageSize
		        },
		        success: function(response) {
		            $("#table").html(response);
		        }
		    });
		}

		function getPageCount() {
		    $.ajax({
		        url: "/admin/product/count",
		        data: {
		            pageSize: pageSize
		        },
		        success: function(response) {
		            pageCount = parseInt(response);
		        }
		    });
		}


		$(".first-page").click(function() {
			pageNo = 0;
			loadPage();
			return false;
		});

		$(".prev-page").click(function() {
			if (pageNo > 0) {
				pageNo -= 1;
				loadPage();
			}
			return false;
		});

		$(".next-page").click(function() {
			if (pageNo < pageCount - 1) {
				pageNo += 1;
				loadPage();
			}
			return false;
		});

		$(".last-page").click(function() {
			pageNo = pageCount - 1;
			loadPage();
			return false;
		});

		getPageCount();
		loadPage();
	});
</script>
</body>
</html>
