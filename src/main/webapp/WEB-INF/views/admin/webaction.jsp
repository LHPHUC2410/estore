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
	<h3>Quản lý hành động website</h3>

	<ul class="nav nav-tabs">
	  <li class="active"><a data-toggle="tab" href="#edit">Edition</a></li>
	  <li><a data-toggle="tab" href="#list">List</a></li>
	</ul>
	
	<div class="tab-content">
	  <div id="edit" class="tab-pane fade in active">
	    <!-- FORM -->
	    <jsp:include page="webaction/form.jsp"/>
	  </div>
	  <div id="list" class="tab-pane fade">
	    <!-- BANG -->
	    <jsp:include page="webaction/table.jsp"/>
	  </div>
	</div>
</body>
</html>