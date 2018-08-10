<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Users List</title>

    <link href="<c:url value='https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css' />"
          rel="stylesheet"/>

</head>
<body>
<div class="generic-container">
    <div class="panel">
        <span class="lead">List of Users </span>
        <input id="userName" type="text" class="form-control col-md-2" placeholder="Username" onkeyup="doAjax()"/>
        </br>
        <table id="tableOfUsers" class="table col-md-6">
            <thead>
            <tr>
                <th>Id</th>
                <th>Name</th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>

</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
<script type="text/javascript">
    function doAjax() {
        $.getJSON({
                url: "/userList/getUserList",
                data: {uName: $('#userName').val()},
            },
            function (data) {
                var tablebody = $('#tableOfUsers tbody');
                tablebody.empty();
                if ($('#userName').val() === '') {
                    tablebody.empty();
                    var text = ["<tr><td>" + "Response is empty" + "</td></tr>"];
                    tablebody.append(text.join(''));
                } else {
                    for (var i = 0; i < data.length; i++) {
                        var rowtext = ["<tr><td>" + data[i].id + "</td><td>" + data[i].username + "</td></tr>"];
                        tablebody.append(rowtext.join(''));
                    }
                }
            });
    }
</script>
</body>
</html>
