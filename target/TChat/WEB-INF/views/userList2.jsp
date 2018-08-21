<!doctype html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Users List</title>

    <link href="${contextPath}/resources/css/bootstrap.css" rel="stylesheet">

    <link  href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" rel="stylesheet">
</head>
<body>

<input id="userName" class="col-md-3">
</br>
</br>
</br>
<div>--------------------------</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-1.12.1.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
</script>
<script>
    $(document).ready(function () {
        $('#userName').autocomplete({
            source: function (req, resp) {
                $.getJSON({
                    url: "/userList2/getUserList",
                    data: { uName: $('#userName').val()},
                    success: function (data) {
                        resp($.map(data, function(v,i){
                            return v.login;
                        }));
                    }
                });
            }
        })
    })
</script>
</body>
</html>
