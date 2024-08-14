<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<nav class="navbar navbar-inverse" style = "margin: 0px 10px 0px 10px; border-color: #EA501F; background-color: #EA501F;">
    <div class="container-fluid">
        <ul class="nav navbar-nav">
            <li><a href="/home/about" style="color: white;">About Us</a></li>
            <li><a href="/home/contact" style="color: white;">Contact Us</a></li>
            <li><a href="/home/feedback" style="color: white;">Feedback</a></li>
          </ul>
          <ul class="nav navbar-nav navbar-right"> 
            <li class="dropdown">
            <a class="dropdown-toggle"
				data-toggle="dropdown" href="#" style="color: white;"> 
					<c:choose>
						<c:when test="${sessionScope.user!=null}">
						${ sessionScope.user.fullname}
						</c:when>
						<c:otherwise>
							Tài khoản
						</c:otherwise>
					</c:choose> <span class="caret"></span>
			</a> <c:choose>
                    <c:when test="${empty sessionScope.user}">
                        <ul class="dropdown-menu">
                            <li><a href="/account/login">Đăng nhập</a></li>
                            <li><a href="/account/register">Đăng ký</a></li>
                            <li><a href="/account/forgot">Quên mật khẩu</a></li>
                        </ul>
                    </c:when>
                    <c:otherwise>
                        <ul class="dropdown-menu">
                            <li><a href="/account/logoff">Đăng xuất</a></li>
                            <li><a href="/account/change">Đổi mật khẩu</a></li>
                            <li><a href="/account/edit">Cập nhật tài khoản</a></li>
                            <li><a href="/order/list">Đơn hàng</a></li>
                            <li><a href="/order/items">Sản phẩm đã mua</a></li>
                        </ul>
                    </c:otherwise>
                </c:choose>
            </li>
        </ul>
<!--         <ul class="nav navbar-nav navbar-right"> -->
<!--             <li><a href="#">VietNam</a></li> -->
<!--             <li><a href="#">English</a></li> -->
<!--         </ul> -->
    </div>
</nav>