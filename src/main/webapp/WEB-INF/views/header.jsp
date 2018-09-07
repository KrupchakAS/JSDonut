<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="description" content="">
    <meta name="author">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <title>Admin Panel DONUT</title>

    <link href="${contextPath}/resources/assets/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/assets/css/font-awesome.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/assets/css/datepicker3.css" rel="stylesheet">
    <link href="${contextPath}/resources/assets/css/styles.css" rel="stylesheet">
    <link href="${contextPath}/resources/assets/css/project.css" rel="stylesheet">
    <!--Custom Font-->
    <link href="https://fonts.googleapis.com/css?family=Montserrat:300,300i,400,400i,500,500i,600,600i,700,700i"
          rel="stylesheet">

</head>