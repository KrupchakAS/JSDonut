<!DOCTYPE html>
<%@ page  pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>


<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Welcome</title>
    <link href="${contextPath}/resources/css/bootstrap.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/app.css" rel="stylesheet">
    </head>
<body>
<div class="container">
    <nav class="navbar navbar-fixed-top navbar-default container__navbar" role="navigation">
        <div class="container-fluid">
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav navbar-right">
                        <sec:authorize access="!hasRole('ROLE_ADMIN') and !hasRole('ROLE_USER')">
                            <li><a data-toggle="modal" data-target="#loginpage" href="#">Вход</a></li>
                            <li><a data-toggle="modal" data-target="#regpage" href="#">Регистрация</a></li>
                        </sec:authorize>
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <li><a href="/admin">Админка</a></li>
                        </sec:authorize>
                        <sec:authorize access="hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')">
                            <li><a href="/logout">Выход</a></li>
                        </sec:authorize>
                    </ul>
                </div>
        </div>
    </nav>
</div>
</br>
</br>
</br>
<sec:authorize access="!hasRole('ADMIN') and !hasRole('MANAGER') and !hasRole('USER')">
    <c:import url="/WEB-INF/views/registration.jsp"  />
    <%--<c:import url="/WEB-INF/views/login.jsp" />--%>
</sec:authorize>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>