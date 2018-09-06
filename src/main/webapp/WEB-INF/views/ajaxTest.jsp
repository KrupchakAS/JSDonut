<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>List</title>

    <link href="${contextPath}/resources/css/bootstrap.css/" rel="stylesheet"/>

</head>
<body>
<div class="generic-container">
    <div class="panel">
        <table id="table" class="table col-md-6">
            <thead>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Calories</th>
                <th>Prices</th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>

</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.js"></script>
<script>
    $.document.ready(function() {
        $.getJSON({
                url: ""
            },
            function (data) {
                var tablebody = $('#table tbody');
                tablebody.append(text.join(''));
                for (var i = 0; i < data.length; i++) {
                    var rowtext = ["<tr><td>" + data[i].id + "</td><td>" + data[i].name + "</td><td>"  + data[i].calories + "</td><td>" + data[i].price + "</td></tr>"];
                    tablebody.append(rowtext.join(''))
                }

            });
    })
</script>
</body>
</html>