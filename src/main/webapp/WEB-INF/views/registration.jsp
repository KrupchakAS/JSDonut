<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<div class="collapse " id="regpage">
    <div class="card card-body">
        <div class="container ">
            <form:form method="POST" modelAttribute="userForm" class="form-signin">
                <div>
                    <label>Enter your login:</label>
                    <form:input type="text"  minlength="4" maxlength="16" path="login" class="form-control"
                                placeholder="Login"></form:input>
                    <div class="has-error">
                        <form:errors path="login"></form:errors></div>
                </div>
                <div>
                    <label>Enter your firstName:</label>
                    <form:input type="text"  minlength="4" maxlength="16" path="firstName" class="form-control"
                                placeholder="FirstName"></form:input>
                    <div class="has-error">
                        <form:errors path="firstName"></form:errors></div>
                </div>
                <div>
                    <label>Enter your surName:</label>
                    <form:input type="text" minlength="4" maxlength="16" path="surName" class="form-control"
                                placeholder="SurName"></form:input>
                    <div class="has-error">
                        <form:errors path="surName"></form:errors></div>
                </div>
                <div>
                    <label>Enter your phoneNumber:</label>
                    <form:input type="text" minlength="4" maxlength="16" path="phoneNumber" class="form-control"
                                placeholder="PhoneNumber"></form:input>
                    <div class="has-error">
                        <form:errors path="phoneNumber"></form:errors></div>
                </div>
                <div>
                    <label>Enter your birthday:</label>
                    <form:input type="date" value="2000-01-01" min="1900-01-01" max="2014-01-01" path="birthDate"
                                class="form-control"
                                placeholder="BirthDate"></form:input>
                    <div class="has-error">
                        <form:errors path="birthDate"></form:errors></div>
                </div>
                <div>
                    <label>Enter your email:</label>
                    <form:input type="text" minlength="4" maxlength="50" path="email" class="form-control" placeholder="Email"></form:input>
                    <div class="has-error">
                        <form:errors path="email"></form:errors></div>
                </div>
                <div>
                    <label>Enter your birthday:</label>
                    <form:input type="password" minlength="4" maxlength="16" path="password" class="form-control"
                                placeholder="Password"></form:input>
                    <div class="has-error">
                        <form:errors path="password"></form:errors></div>
                </div>
                <div>
                    <label>Enter your confirmPassword:</label>
                    <form:input type="password" minlength="4" maxlength="16" path="confirmPassword" class="form-control"
                                placeholder="Confirm your password"></form:input>
                    <div class="has-error">
                        <form:errors path="confirmPassword"></form:errors>
                    </div>
                </div>
                <%--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>
                <button class="btn btn-md">Sign up</button>
            </form:form>
        </div>
    </div>
</div>
