<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<div class="container" id="regpage">
    <form:form method="POST" modelAttribute="userForm" action="" class="form-signin">
        <bind path="username">
            <form:input type="text" path="username" class="form-control" placeholder="Username"
                        autofocus="true"></form:input>
            <div class="has-error">
                <form:errors path="username"></form:errors></div>
        </bind>

        <bind path="password">
            <form:input type="password" path="password" class="form-control" placeholder="Password"></form:input>
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
