<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f"%>

<h2>Giỏ hàng</h2>
<h4>${message}</h4>
<table class="table">
	<thead>
		<tr>
<!-- 			<th>Mã sản phẩm</th> -->
			<th>Tên sản phẩm</th>
			<th>Đơn giá</th>
			<th>Giảm giá</th>
			<th>Số lượng</th>
			<th>Tổng tiền</th>
			<th>Thao tác</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="p"
			items="${sessionScope['scopedTarget.cartService'].items}">
			<c:set var="maxQuantity"
				value="${sessionScope['scopedTarget.cartService'].getMaxQuantity(p.id)}" />
			<tr data-id="${p.id}" data-quantity="${p.quantity}"
				data-price="${p.unitPrice}" data-discount="${p.discount}">
<%-- 				<td>${p.id}</td> --%>
				<td>${p.name}</td>
				<td>$<f:formatNumber value="${p.unitPrice}" pattern="#,###.00"/></td>
				<td>${p.discount * 100}%</td>
				<td>
					<input id="quantity-${p.id}"
				       value="${p.quantity}" type="number" min="1" max="${maxQuantity}" 
				       oninput="validateInput(this, ${maxQuantity}, 'error-message-${p.id}')"
				       style="width: 70px">
					<span id="error-message-${p.id}" style="color: red; display: none;">Số lượng tối đa: ${maxQuantity}</span>

					<script>
					function validateInput(input, maxQuantity, errorMessageId) {
					    // Remove non-digit characters
					    input.value = input.value.replace(/[^0-9]/g, '');
					
					    // Remove leading zeros
					    input.value = input.value.replace(/^0+/, '');
					
					    // If the input is empty after removing invalid characters, set it to 1
					    if (input.value === '') {
					        input.value = 1;
					    }
					
					    // Convert input value to number
					    let value = parseInt(input.value, 10);
					
					    // Get the error message element
					    let errorMessageElement = document.getElementById(errorMessageId);
					
					    // Ensure the value is not greater than maxQuantity
					    if (value > maxQuantity) {
					        input.value = maxQuantity;
					        errorMessageElement.style.display = 'block';
					    } else {
					        errorMessageElement.style.display = 'none';
					    }
					}
					</script>
				</td>
				<td class="amt">$<f:formatNumber value="${p.quantity*p.unitPrice*(1-p.discount)}" pattern="#,###.00"/></td>
				<td>
					<button class="btn btn-sm btn-warning btn-cart-remove">
						<i class="glyphicon glyphicon-trash"></i>
					</button>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<button class="btn btn-sm btn-danger  btn-cart-clear">Clear</button>
<a href="/home/index" class="btn btn-sm btn-info">Continue</a>
<a href="/order/checkout" class="btn btn-sm btn-success">Checkout</a>