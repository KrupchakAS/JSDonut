<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<div class="row">
    <div class="col">
        <div class="collapse multi-collapse" id="logpage">
            <div class="card card-body">
                <div class="container">
                    <form method="POST" action="${contextPath}/login" class="form-signin">
                        <div class="form-group ${error != null ? 'has-error' : ''}">
                            <span>${message}</span>
                            <input name="username" type="text" class="form-control" placeholder="Login">
                            <input name="password" type="password" class="form-control" placeholder="Password">
                            <span>${error}</span>
                        </div>
                        <button class="btn btn-xs" type="submit">Sign In</button>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>