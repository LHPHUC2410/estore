<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<body>

	<div class="navbar navbar-inverse row">
		<div class="navbar-header" style="background-color: #EA501F; border-color: #EA501F;">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-collapse">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
		</div>
		<div class="navbar-collapse collapse" style="padding-left: 50px; padding-right: 20px;background-color: #EA501F; border-color: #EA501F; color: white;">
			<ul class="nav navbar-nav">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" style="color: white;">Quản lý<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="/admin/product/index">Hàng hóa</a></li>
						<li><a href="/admin/supplier/index">Nhà cung cấp</a></li>
						<li><a href="/admin/category/index">Loại mặt hàng</a></li>
						<li><a href="/admin/customer/index">Khách hàng</a></li>
						<li><a href="/admin/order/index">Đơn hàng</a></li>
					</ul></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" style="color: white;">Thống kê<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="/admin/inventory/bycategory">Theo loại</a></li>
						<li><a href="/admin/inventory/bysupplier">Theo hãng</a></li>
						<li class="divider"></li>
						<li><a href="/admin/revenue/byproduct">Theo từng mặt hàng</a></li>
						<li><a href="/admin/revenue/bycategory">Theo từng loại
								hàng</a></li>
						<li><a href="/admin/revenue/bysupplier">Theo từng hãng</a></li>
						<li><a href="/admin/revenue/bycustomer">Theo từng khách
								hàng</a></li>
						<li class="divider"></li>
						<li><a href="/admin/revenue/byyear">Theo từng năm</a></li>
						<li><a href="/admin/revenue/byquarter">Theo từng quí</a></li>
						<li><a href="/admin/revenue/bymonth">Theo từng tháng</a></li>
					</ul></li>
			</ul>			
			<ul class="nav navbar-nav navbar-right">
                    <li><a href="#" class="dropdown-toggle" data-toggle="dropdown" style="padding-right: 40px; color: white;">      
                    <c:choose>
						<c:when test="${sessionScope.master!=null}">
						${ sessionScope.master.fullName}
						</c:when>
						<c:otherwise>
							Tài khoản
						</c:otherwise>
					</c:choose>
                    <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <c:choose>
                            	<c:when test="${empty master}">
                            		<li><a href="/account/login">Đăng nhập</a></li></c:when>
                            	<c:otherwise>
                            		<li><a href="/account/logoff">Đăng xuất</a></li></c:otherwise>
                            </c:choose>
                            <li class="divider"></li>
                            <li><a href="/admin/master/index">Quản lý tài khoản</a></li>
                            <li><a href="/admin/role/index">Quản lý vai trò</a></li>
                            <li><a href="/admin/webaction/index">Quản lý chức năng</a></li>
                            <li class="divider"></li>
                            <li><a href="/admin/master-role/index">Phân vai trò</a></li>
                            <li><a href="/admin/web-action-role/index">Phân quyền sử dụng</a></li>
                        </ul>
                    </li>
                </ul>
			
			
			
			
		</div>
	</div>



	</div>
</body>