<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f"%>
<div class="row">
	<div>
		<div class="col-sm-5 text-center">
			<img class="detail-img" src="/static/images/products/${prod.image}">
		</div>
		<div class="col-sm-7" style="font: Time new roman">
			<ul class="detail-info">
				<li>Tên: ${prod.name}</li>
				<li>Đơn giá: $<f:formatNumber value="${prod.unitPrice}"
						pattern="#,###.00" />
				</li>
				<li>UnitBrief: ${prod.unitBrief}</li>
				<li>Ngày sản xuất:<f:formatDate value="${prod.productDate}"
						pattern="dd-MM-yyyy" /></li>
				<li>Danh mục: ${prod.category.nameVN}</li>
				<li>Số lượng: ${prod.quantity}</li>
				<li>Giảm giá: <f:formatNumber value="${prod.discount}"
						type="percent" /></li>

				<li>Sẵn hàng: ${prod.available?'Yes':'No'}</li>
				<li>Đặc biệt: ${prod.special?'Yes':'No'}</li>
				<li>Mới nhất: ${prod.latest?'Yes':'No'}</li>
				<li>Lần xem: ${prod.views}</li>
			</ul>
		</div>
	</div>
</div>



<%-- <div class="text-justify">${prod.description}</div> --%>
<ul class="nav nav-tabs">
	<li class="active"><a data-toggle="tab" href="#tab1">Cùng loại</a></li>
	<li><a data-toggle="tab" href="#tab2">Yêu thích</a></li>
	<li><a data-toggle="tab" href="#tab3">Đã xem</a></li>
</ul>

<div class="tab-content">
	<div id="tab1" class="tab-pane fade in active">
		<div class="flex">
			<c:forEach var="p" items="${list}">
				<a href="/product/detail/${p.id}"> <img class="thumb-img"
					src="/static/images/products/${p.image}">
				</a>
			</c:forEach>
		</div>
	</div>
	<div id="tab2" class="tab-pane fade">
		<div class="flex">
			<c:forEach var="p" items="${favo}">
				<a href="/product/detail/${p.id}"> <img class="thumb-img"
					src="/static/images/products/${p.image}">
				</a>
			</c:forEach>
		</div>
	</div>
	<div id="tab3" class="tab-pane fade">
		<div class="flex">
			<c:forEach var="p" items="${viewed}">
				<a href="/product/detail/${p.id}"> <img class="thumb-img"
					src="/static/images/products/${p.image}">
				</a>
			</c:forEach>
		</div>
	</div>
</div>

