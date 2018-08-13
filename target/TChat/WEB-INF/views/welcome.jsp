<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="text/html; content=" IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Welcome</title>
    <link href="${contextPath}/resources/css/bootstrap.css" rel="stylesheet">
    </head>
<body>

<%--<div class="container">--%>

<%--<c:if test="${pageContext.request.userPrincipal.name != null}">--%>
<%--<form id="logoutForm" method="POST" action="${contextPath}/logout">--%>
<%--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>
<%--</form>--%>

<%--<h4>Welcome ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a>--%>
<%--</h4>--%>

<%--</c:if>--%>
<%--</div>--%>
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

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>