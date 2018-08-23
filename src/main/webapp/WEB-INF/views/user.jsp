<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users List</title>
    <link href="<c:url value='https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css' />" rel="stylesheet"/>

</head>
<br>
    <input id="userName" onkeyup="doAjax()"/>

        </br>
        <strong>Ответ сервлета </strong>
        <span id="ajaxUserServletResponse"></span>

<script type="text/javascript">
    function doAjax(userName) {
            $.ajax( {
                url:"/user/findUser",
                data:{ uName : $('#userName').val()},
            success : function(data) {
                $("#ajaxUserServletResponse").text(data.id);
            }});
        }
</script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</body>
</html>