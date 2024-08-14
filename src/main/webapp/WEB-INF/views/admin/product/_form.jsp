<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<div class="panel-body">
	<h4 class="errors">${message}</h4>
	<form:form action="/admin/product/index"
		modelAttribute="model" enctype="multipart/form-data">
		<form:errors path = "*" element="ul" class="errors"/>
		<div class="row">
			<div class="form-group col-sm-4">
				<label>Id</label>
				<form:input path="id" cssClass="form-control" readonly ="true" placeholder="Auto Number"/>
			</div>
			<div class="form-group col-sm-4">
				<label>Tên sản phẩm</label>
				<form:input path="name" cssClass="form-control"/>
			</div>
			<div class="form-group col-sm-4">
				<label>Đơn giá</label>
				<form:input path="unitPrice" cssClass="form-control"/>
			</div>
		</div>
		
		<div class="row">
			<div class="form-group col-sm-4">
				<label>Tóm tắt sản phẩm</label>
				<form:input path="unitBrief" cssClass="form-control"/>
			</div>
			<div class="form-group col-sm-4">
				<label>Số lượng</label>
				<form:input path="quantity" cssClass="form-control"/>
			</div>
			<div class="form-group col-sm-4">
				<label>Giảm giá</label>
				<form:input path="discount" cssClass="form-control" />
			</div>
		</div>
		
		<div class="row">
			 <div class="form-group col-sm-4">
				<label>Ngày sản xuất</label>
				<form:input path="productDate" cssClass="form-control" placeholder="dd/mm/yyyy"/>
			</div> 
			
			<div class="form-group col-sm-4">
			    <label>Số lần xem</label>
			    <form:input path="views" cssClass="form-control" disabled="true"/>
			</div>
			<div class="form-group col-sm-4">
				<label>Features</label>
				<div class="form-control">
					<form:checkbox path="available" label="Available?"/>
					<form:checkbox path="special" label="Special?"/>
					<form:checkbox path="latest" label="Latest?"/>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="form-group col-sm-4">
				<label>Hình ảnh</label>
				<input type="file" name="photo_file">
				<form:hidden path="image"/>
			</div>
			<div class="form-group col-sm-4">
				<label>Loại sản phẩm</label>
				<form:select path="category.id" cssClass="form-control"
					items="${categories}" itemValue="id" itemLabel="nameVN"/>
			</div>
			<div class="form-group col-sm-4">
				<label>Nhà cung cấp</label>
				<form:select path="supplier.id" cssClass="form-control"
					items="${suppliers}" itemValue="id" itemLabel="name"/>
			</div>
		</div>
		
		<div class="row">
			<div class="form-group col-sm-12">
				<label>Mô tả</label>
				<form:textarea path="description" rows="3" cssClass="form-control"></form:textarea>
			</div>
			<div class="form-group col-sm-12">
				<c:choose>
				    <c:when test="${empty model.id}">
				    	<button class="btn btn-default" formaction="/admin/product/insert">Thêm mới</button>
				    </c:when>
				    <c:otherwise><button class="btn btn-default" formaction="/admin/product/update">Hoàn tất</button></c:otherwise>
				</c:choose>

<!-- 				<button class="btn btn-default" formaction="/admin/product/insert">Thêm mới</button> -->
				<%-- <button ${empty model.id?'disabled':''} class="btn btn-default" formaction="/admin/product/update">Sửa</button>
				<button ${empty model.id?'disabled':''} class="btn btn-default" formaction="/admin/product/delete">Xóa</button> --%>
				<button class="btn btn-default" formaction="/admin/product/index">Tải lại</button>
			</div>
		</div>
	</form:form>
</div>

<script type="text/javascript">
 
        bkLib.onDomLoaded(function() { nicEditors.allTextAreas() });
</script>