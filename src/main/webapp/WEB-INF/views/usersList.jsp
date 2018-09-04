<%@ page pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" >
    <meta name="author">

    <title>UsersList</title>
    <link href="${contextPath}/resources/css/bootstrap.css"  rel="stylesheet"/>
</head>

<body>
<div class="generic-container">
    <div class="well">

    </div>
    <div class="panel panel-default">
        <div class="panel-heading"><span class="lead">List of Users </span></div>
        <table class="table table-hover">
            <thead>
            <tr>
                <th>Id</th>
                <th>Login</th>
                <th>FirstName</th>
                <th>SurName</th>
                <th>PhoneNumber</th>
                <th>Email</th>
                <th width="100"></th>
                <th width="100"></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.login}</td>
                    <td>${user.firstName}</td>
                    <td>${user.surName}</td>
                    <td>${user.phoneNumber}</td>
                    <td>${user.email}</td>
                    <td><a href="<c:url value="/admin/edit-user-${user.id}" />" class="btn btn-success custom-width">edit</a></td>
                    <td><a href="<c:url value="/admin/delete-user-${user.id}" />" class="btn btn-danger custom-width">delete</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

</div>

<script  src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script  src="${contextPath}/resources/js/bootstrap.js"></script>
</body>
</html>
