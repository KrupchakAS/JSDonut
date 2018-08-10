<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Welcome</title>
</head>
<body>
<h1>HI! We are glad to see you! PLEASE <a href="<c:url value="/login"/>">Log in</a> or <a href="<c:url value="/registration"/>">Sign up</a></h1>
</body>
</html>