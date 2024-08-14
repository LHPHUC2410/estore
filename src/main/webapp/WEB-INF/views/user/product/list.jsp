<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<c:forEach var="p" items="${list}">
	<div class="col-sm-3">
		<div class="thumbnail">
			<a href="/product/detail/${p.id}"> <img class="estore-prod"
				src="/static/images/products/${p.image}">
			</a>
			<div class="caption">
				<p>
					${p.name} 
					${p.quantity} 
					<c:if test="${p.quantity < 1 or not p.available}">
						<span style="color: red;">(Hết hàng)</span>
					</c:if>
				</p>
				<div data-id="${p.id}" class="pull-right">
					<c:if test="${p.quantity >= 1 and p.available}">
						<button class="btn btn-sm btn-danger">
							<i class="glyphicon glyphicon-shopping-cart btn-add-to-cart"></i>
						</button>
					</c:if>
					<button class="btn btn-sm btn-warning btn-star">
						<i class="glyphicon glyphicon-star"> </i>
					</button>
					<button class="btn btn-sm btn-success btn-open-dialog"
						data-toggle="modal" data-target="#myModal">
						<i class="glyphicon glyphicon-envelope"> </i>
					</button>
				</div>
				<p>$${p.unitPrice}</p>
			</div>


		</div>
		<%-- <p>${p.name} - ${p.unitPrice} </p> --%>
	</div>

</c:forEach>

<jsp:include page="dialog.jsp" />