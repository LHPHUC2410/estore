<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Quản lý phân quyền vai trò</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
    <h3>Quản lý phân quyền vai trò</h3>

    <fieldset>
        <legend>
            Master 
            <select id="masterId">
                <c:forEach var="m" items="${masters}">
                    <option value="${m.id}">${m.fullName}</option>
                </c:forEach>
            </select>
        </legend>
    </fieldset>

    <fieldset>
        <legend>Roles</legend>
        <ul>
            <c:forEach var="r" items="${roles}">
                <li>
                    <label class="checkbox-inline">
                        <input type="checkbox" value="${r.id}"> ${r.name}
                    </label>
                </li>
            </c:forEach>
        </ul>
    </fieldset>

    <script type="text/javascript">
        $(document).ready(function() {
            function loadRoles(masterId) {
                $.ajax({
                    url: "/admin/master-role/get-role-ids",
                    data: { masterId: masterId },
                    dataType: "json",
                    success: function(response) {
                        $(":checkbox").prop("checked", false);
                        $(response).each(function(index, item) {
                            $(":checkbox[value=" + item + "]").prop("checked", true);
                        });
                    }
                });
            }

            $("#masterId").change(function() {
                var masterId = $(this).val();
                loadRoles(masterId);
            });

            $("#masterId").change(); // Load roles for the initial master

            $(":checkbox").click(function() {
                var roleId = $(this).val();
                var masterId = $("#masterId").val();
                var isChecked = $(this).is(":checked");
                $.ajax({
                    url: "/admin/master-role/update",
                    data: {
                        masterId: masterId,
                        roleId: roleId,
                        isChecked: isChecked
                    }
                });
            });
        });
    </script>
</body>
</html>
