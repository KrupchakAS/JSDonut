<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<div class="row">
    <div class="col">
        <div class="collapse multi-collapse" id="regpage">
            <div class="card card-body">
                <div class="container">
                    <form:form method="POST" modelAttribute="userForm" class="form-signin">
                        <bind path="login">
                            <form:input type="text" path="login" class="form-control" placeholder="Login"></form:input>
                            <div class="has-error">
                                <form:errors path="login"></form:errors></div>
                        </bind>
                        <bind path="firstName">
                            <form:input type="text" path="firstName" class="form-control"
                                        placeholder="FirstName"></form:input>
                            <div class="has-error">
                                <form:errors path="firstName"></form:errors></div>
                        </bind>
                        <bind path="surName">
                            <form:input type="text" path="surName" class="form-control"
                                        placeholder="SurName"></form:input>
                            <div class="has-error">
                                <form:errors path="surName"></form:errors></div>
                        </bind>
                        <bind path="phoneNumber">
                            <form:input type="text" path="phoneNumber" class="form-control"
                                        placeholder="PhoneNumber"></form:input>
                            <div class="has-error">
                                <form:errors path="phoneNumber"></form:errors></div>
                        </bind>
                        <bind path="birthDate">
                            <form:input type="date" path="birthDate" class="form-control"
                                        placeholder="BirthDate"></form:input>
                            <div class="has-error">
                                <form:errors path="birthDate"></form:errors></div>
                        </bind>
                        <bind path="email">
                            <form:input type="text" path="email" class="form-control" placeholder="Email"></form:input>
                            <div class="has-error">
                                <form:errors path="email"></form:errors></div>
                        </bind>
                        <bind path="password">
                            <form:input type="password" path="password" class="form-control"
                                        placeholder="Password"></form:input>
                            <div class="has-error">
                                <form:errors path="password"></form:errors></div>
                        </bind>
                        <bind path="confirmPassword">
                            <form:input type="password" path="confirmPassword" class="form-control"
                                        placeholder="Confirm your password"></form:input>
                            <div class="has-error">
                                <form:errors path="confirmPassword"></form:errors>
                            </div>
                        </bind>
                        <button class="btn btn-xs" type="submit">Sign up</button>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>