<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <title>FAKE Shopee</title>
  <link rel="icon" href="/images/logo-shopee.png" type="image/x-icon">
  <tiles:insertAttribute name="head"/>
  <style id="cart-effect"></style>
</head>
<body>

<div class="container-fluid">
 	<header class="row" style="position: fixed; z-index: 1000; width: 100%; background-color: #EA501F">
 		<tiles:insertAttribute name="menu"/>	
 		
 		<div style="height: 70px; display: flex; align-items: center; padding: 0 20px">
    		<div class="col-sm-2" style="display: flex; align-items: center;">
			    <a href="/home/index" style="display: flex; align-items: center; text-decoration: none;">
			        <img width="60px" height="60px" src="/images/logo-shopee.png" alt="Shopee Logo" style="margin-right: 2px;">
			        <h1 class="alert alert-success" style="background-color: #EA501F; border-color: #EA501F; color: white; margin: 0; padding-left: 0px; font-size: 33px;">Shopee</h1>
			    </a>
			</div>
			<div class="col-sm-9 search-wrapper">
			        <form action="/product/list-by-keywords/" method="post" class="search-container">
			        	<input type="text" name="keywords" value="${param.keywords }" class="search-input" placeholder="Tìm sản phẩm">
			        	<img width="30px" height="30px" src="/images/icon-search.png" class="search-icon" onclick="this.closest('form').submit();">
			    	</form>
			    </div>
			    
	   		<div class="col-sm-1">
	   			<c:set var="cart" value="${sessionScope['scopedTarget.cartService']}"/>
				<p>
					<a href="/cart/view" class="cart-link">
				    <img id="cart-img" alt="Icon giỏ hàng" src="/static/images/ico-cart.png" style="width: 40px; height: 40px;">
				</a>
				<b id="cart-cnt" style = "color: white;">${cart.count}</b>
				</p>
	   		</div>
 		</div>
		
		<div>
			<div class="col-sm-2" style="display: flex; align-items: center; height: 50px; padding-left: 35px">
				<a class="dropdown-toggle" data-toggle="dropdown" href="#" style="color: white;">Danh mục <span class="caret"></span></a>
				<ul class="dropdown-menu ">
		      		<c:forEach var="c" items="${cates}">
		          		<li><a href="/product/list-by-category/${c.id}" class="dropdown-item">${c.nameVN}</a></li>
		      		</c:forEach>
		 		</ul>
			</div>
			<div class="col-sm-10">
				<ul class="nav navbar-nav">
		           <li><a href="/product/list-by-special/0" style="color: white;">Sản phẩm mới</a> </li>
		           <li><a href="/product/list-by-special/1" style="color: white;">Bán chạy</a> </li>
		           <li><a href="/product/list-by-special/2" style="color: white;">Phổ biến</a></li>
		           <li><a href="/product/list-by-special/3" style="color: white;">Đang giảm giá</a></li>
		        </ul>
			</div>
		</div>	
	</header>
	
 	<div class="row" style="margin-top: 180px" >
	 	<article class="col-sm-12">
	 		<tiles:insertAttribute name="body"/>
	 	</article>
 	</div>
</div>
</body>
</html>





