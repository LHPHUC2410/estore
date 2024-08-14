<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<div class="panel-body">
    <h4 class="errors">${message}</h4>
    <form:form action="/admin/customer/insert" modelAttribute="model" enctype="multipart/form-data" method="post">
        <form:errors path="*" element="ul" cssClass="errors" />
        <div class="form-group">
            <label>Mã khách hàng</label>
            <c:choose>
                <c:when test="${status == 2}">
                    <form:input path="id" cssClass="form-control" readonly="true" />
                </c:when>
                <c:otherwise>
                    <form:input path="id" cssClass="form-control" />
                </c:otherwise>
            </c:choose>
        </div>
        <div class="form-group">
            <label>Mật khẩu</label>
            <form:input path="password" cssClass="form-control" />
        </div>
        <div class="form-group">
            <label>Họ và tên</label>
            <form:input path="fullname" cssClass="form-control" />
        </div>
        <div class="form-group">
            <label>Email</label>
            <form:input path="email" cssClass="form-control" />
        </div>
        <div class="form-group">
            <label>Đã kích hoạt?</label>
            <div class="form-control">
                <form:radiobutton path="activated" value="true" label="Yes" />
                <form:radiobutton path="activated" value="false" label="No" />
            </div>
        </div>
        <div class="form-group">
            <label>Ảnh</label>
            <input type="file" name="photo_file">
            <form:hidden path="photo" />
        </div>
        <div class="form-group">
            <c:choose>
                <c:when test="${status != 2}">
                    <button type="submit" class="btn btn-default">Thêm</button>
                </c:when>
                <c:otherwise>
                    <button type="submit" formaction="/admin/customer/update" class="btn btn-default">Sửa</button>
                </c:otherwise>
            </c:choose>
            <button type="submit" formaction="/admin/customer/index" class="btn btn-default">Hoàn tác</button>
        </div>
    </form:form>
</div>
