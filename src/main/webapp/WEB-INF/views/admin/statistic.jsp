<%@ page pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="с" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<c:import url="/WEB-INF/views/admin/adminHeader.jsp"/>
<body>
<nav class="navbar navbar-custom navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#sidebar-collapse"><span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span></button>
            <a class="navbar-brand" href="${contextPath}/admin/adminPanel"><span></span>Admin Panel</a>
            <a class="navbar-brand" href="${contextPath}/welcome"><span></span>Home</a>
        </div>
    </div><!-- /.container-fluid -->
</nav>
<div id="sidebar-collapse" class="col-sm-3 col-lg-2 sidebar">
    <div class="profile-sidebar">
        <div class="profile-usertitle">
            <div class="profile-usertitle-name">ADMIN</div>
            <div class="profile-usertitle-status"><span class="indicator label-success"></span>Online</div>
        </div>
        <div class="clear"></div>
    </div>
    <div class="divider"></div>
    <ul class="nav menu">
        <li class="${statisticActive}"><a href="${contextPath}/admin/statistic"> Statistic</a></li>
        <li class="${orderActive}"><a href="${contextPath}/admin/order"> Orders</a></li>
        <li class="${categoryActive}"><a href="${contextPath}/admin/category"> Categories</a></li>
        <li class="${productActive}"><a href="${contextPath}/admin/product"> Products</a></li>
        <li class="${sprinkleActive}"><a href="${contextPath}/admin/sprinkle"> Sprinkles</a></li>
        <li class="${doughActive}"><a href="${contextPath}/admin/dough">Doughs</a></li>
        <li class="${fillingActive}"><a href="${contextPath}/admin/filling">Fillings</a></li>
    </ul>
</div>

<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading container-head">
                    Statistic
                </div>
                <div class="panel-body container-body">
                    <div class="row">
                        <div class="col-md-12">
                            <table id="category-table" class="table table-striped">
                                <thead>
                                <tr>
                                    <th>Revenue for the current Month</th>
                                    <th>Revenue for the current Week</th>
                                    <th>TOP 10 Customers</th>
                                    <th>TOP 10 Products</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>

                                        <c:choose>
                                            <c:when test="${ProceedsForLastMonth != null}">
                                                <fmt:formatNumber type="number" maxFractionDigits="2" var="proccedsMonth" value="${ProceedsForLastMonth}" />
                                                ${proccedsMonth}
                                            </c:when>
                                            <c:otherwise>
                                                No proceeds
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${ProceedsForLastWeek != null}">
                                                <fmt:formatNumber type="number" maxFractionDigits="2" var="proccedsWeek" value="${ProceedsForLastWeek}" />
                                                ${proccedsWeek}
                                            </c:when>
                                            <c:otherwise>
                                                No proceeds
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when  test="${top10users.size() > 0}">
                                                <c:set var="count" value="0" scope="page"/>
                                                <с:forEach var="user" items="${top10users}">
                                                    <c:set var="count" value="${count+1}" scope="page"/>
                                                    ${count})${user.firstName}-${user.surName}
                                                    <br>
                                                </с:forEach>
                                            </c:when>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when  test="${top10products.size() > 0}">
                                                <c:set var="count" value="0" scope="page"/>
                                                <с:forEach var="product" items="${top10products}">
                                                    <c:set var="count" value="${count+1}" scope="page"/>
                                                    ${count})${product.category.name}-${product.name}
                                                    <br>
                                                </с:forEach>
                                            </c:when>
                                        </c:choose>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<c:import url="/WEB-INF/views/admin/adminFooter.jsp"/>

</body>
</html>