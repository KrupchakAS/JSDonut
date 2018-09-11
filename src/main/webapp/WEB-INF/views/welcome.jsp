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
    <meta name="author" >
    <title>Welcome</title>
    <link href="${contextPath}/resources/css/bootstrap.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/app.css" rel="stylesheet">



</head>
<body>
<div>
    <sec:authorize access="!hasRole('ROLE_ADMIN') and !hasRole('ROLE_USER')">
        <p>
            <button id="log" class="btn btn-primary" data-toggle="collapse" data-target="#logpage"
                    aria-controls="logpage">Sign In
            </button>
            <button id="reg" class="btn btn-primary" data-toggle="collapse" data-target="#regpage"
                    aria-controls="regpage">Sign Up
            </button>
        </p>
        <div class="row">
            <div class="col">
                <jsp:include page="login.jsp"/>
            </div>
        </div>
        <div class="row">
            <div class="col">
                <jsp:include page="registration.jsp"/>
            </div>
        </div>

    </sec:authorize>
    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <a class="btn btn-primary" href="/jsDonut/admin/adminPanel" role="button">Admin Panel</a>
    </sec:authorize>
    <sec:authorize access="hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')">
        <a class="btn btn-secondary" href="/jsDonut/logout" role="button">Sign Out</a>
    </sec:authorize>
</div>
</br>
</br>


<!-- Initialize the plugin: -->

<p>Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon
    officia
    aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon
    tempor,
    sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh
    helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher
    vice lomo.
</p>

<script  src="${contextPath}/resources/assets/js/jquery-3.3.1.min.js"></script>
<script  src="${contextPath}/resources/js/bootstrap.js"></script>


</body>
</html>