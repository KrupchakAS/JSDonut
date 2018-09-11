<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<div class="collapse" id="logpage">
    <div class="card card-body">
        <div class="container">
            <form:form method="POST" action="/login" class="form-signin">
                <div class="form-group ${error != null ? 'has-error' : ''}">
                    <span>${message}</span>
                    <input required min="4" max="16" name="username" type="text" class="form-control" placeholder="Login">
                    <input required min="4" max="16" name="password" type="password" class="form-control" placeholder="Password">
                    <span>${error}</span>
                </div>
                <button class="btn btn-md" type="submit">Sign In</button>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form:form>
        </div>
    </div>
</div>