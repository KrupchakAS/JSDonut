<%@ page pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<c:import url="/WEB-INF/views/header.jsp"/>
<body>
<div>
    <sec:authorize access="!hasRole('ROLE_ADMIN') and !hasRole('ROLE_USER')">
        <p>
            <button id="log" class="btn btn-primary" data-toggle="collapse" data-target="#logpage"
                    aria-controls="logpage">Sign In
            </button>
            <button id="reg" class="btn btn-primary" data-toggle="collapse" data-target="#regpage"
                    aria-controls="regpage">Sign Up
            </button>
        </p>
        <div class="row">
            <div class="col-md-6">
                <jsp:include page="login.jsp"/>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6">
                <jsp:include page="registration.jsp"/>
            </div>
        </div>

    </sec:authorize>
    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <a class="btn btn-primary" href="/jsDonut/admin/adminPanel" role="button">Admin Panel</a>
    </sec:authorize>
    <sec:authorize access="hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')">
        <a class="btn btn-primary" href="/jsDonut/logout" role="button">Sign Out</a>
    </sec:authorize>
</div>


<c:import url="/WEB-INF/views/footer.jsp"/>
</body>
</html>