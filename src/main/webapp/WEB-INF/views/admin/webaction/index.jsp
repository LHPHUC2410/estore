<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Insert title here</title>
</head>
<body>
	<h3>Quản lý chức năng website</h3>

	<ul class="nav nav-tabs">
	  <li class="active"><a data-toggle="tab" href="#edit">Chỉnh sửa</a></li>
	  <li><a data-toggle="tab" href="#list">Danh sách</a></li>
	</ul>
	
	<div class="tab-content">
	  <div id="edit" class="tab-pane fade in active">
	    <!-- FORM -->
	    <jsp:include page="form.jsp"/>
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
		        url: "/admin/webaction/load",
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
		        url: "/admin/webaction/count",
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