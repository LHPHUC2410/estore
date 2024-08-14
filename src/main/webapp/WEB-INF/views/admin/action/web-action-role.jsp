<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Quản lý phân quyền chức năng</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
    <h3>Quản lý phân quyền chức năng</h3>

    <fieldset>
        <legend>Vai trò</legend>
        <select id="roleId" class="form-control">
            <c:forEach var="r" items="${roles}">
                <option value="${r.id}">${r.name}</option>
            </c:forEach>
        </select>
    </fieldset>

    <fieldset>
        <legend>Hành động</legend>
        <ul class="row list-unstyled">
            <c:forEach var="a" items="${actions}">
                <li class="col-sm-3">
                    <label class="checkbox-inline">
                        <input type="checkbox" value="${a.id}">
                        ${a.name}
                    </label>
                </li>
            </c:forEach>
        </ul>
    </fieldset>

    <script>
    $(function(){
        $("#roleId").change(function(){
            var roleId = $(this).val();
            $.ajax({
                url: `/admin/web-action-role/get-action-ids`, // Đảm bảo rằng đây là đường dẫn tuyệt đối từ gốc
                data: { roleId: roleId },
                dataType: "json",
                success: function(response){
                    $(":checkbox").prop("checked", false);
                    $(response).each(function(index, item){
                        $(":checkbox[value='" + item + "']").prop("checked", true);
                    });
                }
            });
        });
        
        // Gọi khi trang tải xong để load dữ liệu cho vai trò đầu tiên
        $("#roleId").change();

        $(":checkbox").click(function(){
            var roleId = $("#roleId").val();
            var actionId = $(this).val();
            var isChecked = $(this).prop("checked");

            $.ajax({
                url: `/admin/web-action-role/update`,
                method: "POST",
                data: {
                    roleId: roleId,
                    actionId: actionId,
                    isChecked: isChecked
                },
                success: function(response) {
                    // Xử lý thông báo thành công nếu cần
                },
                error: function(xhr, status, error) {
                    console.error("Error updating role action:", error);
                    // Xử lý lỗi nếu cần
                }
            });
        });
    });

    </script>
</body>
</html>
