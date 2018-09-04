<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>UsersList</title>

    <link rel="stylesheet" href="${contextPath}/resources/css/bootstrap.min.css" />
</head>

<body>
<div class="generic-container">
    <div class="well">
        <button class="btn"><a href="<c:url value="/admin/addProduct"/>">Add New Car</a></button>
    </div>
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">List of Cars </span></div>
        <table class="table table-hover">
            <thead>
            <tr>
                <th>Id</th>
                <th>Brand</th>
                <th>Model</th>
                <th>Color</th>
                <th>Price</th>
                <th>Year</th>
                <th width="100"></th>
                <th width="100"></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${cars}" var="car">
                <tr>
                    <td>${car.id}</td>
                    <td>${car.brand}</td>
                    <td>${car.model}</td>
                    <td>${car.color}</td>
                    <td>${car.price}</td>
                    <td>${car.year}</td>
                    <td><a href="<c:url value="/adminCar/edit-car-${car.id}" />" class="btn btn-success custom-width">edit</a></td>
                    <td><a href="<c:url value="/adminCar/delete-car-${car.id}" />" class="btn btn-danger custom-width">delete</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

</div>
</body>
</html>
