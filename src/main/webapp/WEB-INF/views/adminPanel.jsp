<%@ page pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>AdminPanel</title>

    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/bootstrap.css" />
</head>

<body>
<H2>Welcome Admin</H2>

<div><a href="<c:url value="/admin/usersList"/>">Go to Users list</a></div>
<div><a href="<c:url value="/admin/productsList"/>">Go to Products list</a></div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.js"></script>

</body>
</html>