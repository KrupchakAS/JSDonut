<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<div class="collapse" id="logpage">
    <div class="card card-body">
        <div class="container">
            <form:form method="POST" action="/login" class="form-signin">
                <div class="form-group">
                    <div>
                        <label>Enter your login:</label>
                        <input required minlength="4" maxlength="16" name="username" type="text" class="form-control"
                               placeholder="Login">
                    </div>
                    <div>
                        <label>Enter your password:</label>
                        <input required minlength="4" maxlength="16" name="password" type="password"
                               class="form-control" placeholder="Password">
                    </div>
                </div>
                <button class="btn btn-md" type="submit">Sign In</button>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form:form>
        </div>
    </div>
</div>