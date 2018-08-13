<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<div class="container" id="loginpage">
    <form method="POST" action="/login" class="form-signin">
        <div class="form-group ${error != null ? 'has-error' : ''}">
            <span>${message}</span>
            <input name="login" type="text" class="form-control" placeholder="login">
            <input name="password" type="password" class="form-control" placeholder="Password">
            <span>${error}</span>
        </div>
        <button class="btn btn-xs" type="submit">Log In</button>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>

</div>
</head>