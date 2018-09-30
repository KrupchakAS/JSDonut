<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="ru">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>404</title>
    <link href="${contextPath}/resources/assetsMainPages/css/bootstrap.css" rel="stylesheet"/>
<body>

<section id="container" >
    <div class="row margin-top-10">
        <div class="col-lg-6 col-lg-offset-3">
            <div class="lock-screen">
                <h1><span class="color">Error 405</span><br><span class="color">405 Method Not Allowed</span><br/></h1>
                <h1><a class="color" href="/jsDonut/welcome">Home Page</a></h1>
            </div>
        </div>
    </div>
</section>

<script  src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script  src="${contextPath}/resources/assetsMainPages/js/bootstrap.js"></script>
</body>
</html>