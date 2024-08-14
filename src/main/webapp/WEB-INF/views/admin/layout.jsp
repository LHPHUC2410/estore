<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Shopee Admin</title>
  <link rel="icon" href="/images/logo-shopee.png" type="image/x-icon">
  <tiles:insertAttribute name="head"/>
</head>
<body>

<div class="container-fluid">
 	<header class="row" style="background-color: #EA501F; border-color: #EA501F; color: white; ">
			<div style="width: 100%">
<!-- 			/admin/home/login -->
				<a href="/account/login"
					style="display: flex; align-items: center; text-decoration: none; padding: 10px 0 10px 50px">
					<img width="60px" height="60px" src="/images/logo-shopee.png"
					alt="Shopee Logo" style="margin-right: 2px;">
					<h1 class="alert alert-success"
						style="background-color: #EA501F; border-color: #EA501F; color: white; margin: 0; padding-left: 0px; font-size: 33px;">Shopee
						Admin</h1>
				</a>
			</div>
		</header>
 	<nav class="row">
 		<tiles:insertAttribute name="menu"/>
 	</nav>
 	<div class="row">
 	<article class="col-sm-12" style="padding-left: 50px">
 		<tiles:insertAttribute name="body"/>
 	</article>
 	</div>
</div>

</body>
</html>