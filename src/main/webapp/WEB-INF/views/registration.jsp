<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en" encoding="UTF-8">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Create an account</title>

    <link href="<c:url value="/resources/css/bootstrap.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/common.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/app.css"/>" rel="stylesheet">

    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>

</head>

<body>

<div class="container">

    <form:form method="POST" modelAttribute="userForm" class="form-signin">
        <h2 class="form-signin">Sign up</h2>
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

        <button class="btn btn-md " type="submit">Sign up</button>
        <a href="/login">Back and Log in</a>
    </form:form>

</div>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>