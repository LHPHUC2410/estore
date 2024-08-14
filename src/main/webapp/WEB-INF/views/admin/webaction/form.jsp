<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<div class="panel-body">
    <h4 class="errors">${message}</h4>
    <form:form action="/admin/webaction/index" modelAttribute="model">
        <%-- <form:errors path="*" element="ul" cssClass="errors" /> --%>
        <div class="form-group">
            <label>Mã chức năng</label>
            <form:input path="id" cssClass="form-control"/>
            <form:errors path="id" cssClass="text-danger" />
        </div>
        <div class="form-group">
            <label>Tên chức năng</label>
            <form:input path="name" cssClass="form-control"/>
            <form:errors path="name" cssClass="text-danger" />
        </div>
        <div class="form-group">
            <label>Mô tả chức năng</label>
            <form:input path="description" cssClass="form-control"/>
            <form:errors path="description" cssClass="text-danger" />
        </div>
        <div class="form-group">
            <c:choose>
                <c:when test="${empty model.id}">
                    <button type="submit" class="btn btn-success" formaction="/admin/webaction/insert">Thêm</button>
                </c:when>
                <c:otherwise>
                    <button type="submit" class="btn btn-success" formaction="/admin/webaction/update">Sửa</button>
                </c:otherwise>
            </c:choose>
            <button type="submit" class="btn btn-danger">Hoàn tác</button>
        </div>
<!--         <div class="form-group">
            <button class="btn btn-default" formaction="/admin/webaction/insert">Thêm</button>
            <button class="btn btn-default" formaction="/admin/webaction/update">Cập nhật</button>
            <button class="btn btn-default" formaction="/admin/webaction/delete">Xóa</button>
            <button class="btn btn-default" formaction="/admin/webaction/index">Hoàn tác</button>
        </div> -->
    </form:form>
</div>
