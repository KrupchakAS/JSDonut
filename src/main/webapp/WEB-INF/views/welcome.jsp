<%@ page pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>


<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Welcome</title>
    <link href="${contextPath}/resources/css/bootstrap.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/app.css" rel="stylesheet">
    <%@include file="login.jsp" %>
    <%@include file="registration.jsp" %>
</head>
<body>
<sec:authorize access="!hasRole('ROLE_ADMIN') and !hasRole('ROLE_USER')">
    <p>
        <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#logpage"
                aria-expanded="false" aria-controls="logpage">Sign In
        </button>
        <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#regpage"
                aria-expanded="false" aria-controls="regpage">Sign Up
        </button>
    </p>
</sec:authorize>
<sec:authorize access="hasRole('ROLE_ADMIN')">
    <a class="btn btn-primary" href="/admin" role="button">Admin Panel</a>
</sec:authorize>
<sec:authorize access="hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')">
    <a class="btn btn-secondary" href="/logout" role="button">Sign Out</a>
</sec:authorize>
</br>
</br>
</br>
<p>Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia
    aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor,
    sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh
    helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo.
    Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them
    accusamus labore sustainable VHS.</p>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>