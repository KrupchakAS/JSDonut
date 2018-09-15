<%@ page pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="с" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<c:import url="/WEB-INF/views/adminPages/adminHeader.jsp"/>
<body>
<nav class="navbar navbar-custom navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#sidebar-collapse"><span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span></button>
            <a class="navbar-brand" href="/jsDonut/admin/adminPanel"><span></span>Admin Panel</a>

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
        <li class="${categoryActive}"><a href="${contextPath}/jsDonut/admin/category"> Categories</a></li>
        <li class="${productActive}"><a href="${contextPath}/jsDonut/admin/product">Product</a></li>
        <li class="${sprinkleActive}"><a href="${contextPath}/jsDonut/admin/sprinkle">Sprinkle</a></li>
        <li class="${doughActive}"><a href="${contextPath}/jsDonut/admin/dough">Dough</a></li>
        <li class="${fillingActive}"><a href="${contextPath}/jsDonut/admin/filling">Filling</a></li>
    </ul>
</div><!--/.sidebar-->

<div id="main"  class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading container-head">
                    Filling list
                </div>
                <div class="panel-body container-body">
                    <button type="button" class="btn btn-md btn-success filling-add">
                        Add Filling
                    </button>
                    <div class="row">
                        <div class="col-md-12 filling-list">
                            <table id="filling-table" class="table table-striped filling-table">
                                <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Name</th>
                                    <th>Calories</th>
                                    <th>Price</th>
                                    <th></th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:choose>
                                    <c:when test="${fillingList.size() > 0}">
                                        <с:forEach var="filling" items="${fillingList}">
                                            <tr class="filling-table__row"  data-id="${filling.id}">
                                                <th id="fil-id">${filling.id}</th>
                                                <th id="fil-name">${filling.name}</th>
                                                <th id="fil-calories">${filling.calories}</th>
                                                <th id="fil-price">${filling.price}</th>
                                                <th>
                                                    <button type="button" class="btn btn-md btn-primary filling-edit">
                                                        Edit
                                                    </button>
                                                </th>
                                                <th>
                                                    <button type="button" class="btn btn-md btn-danger filling-delete">
                                                        Delete
                                                    </button>
                                                </th>
                                            </tr>
                                        </с:forEach>
                                    </c:when>
                                    <c:otherwise>
                                        <tr>
                                            <th colspan="4">Not filling</th>
                                        </tr>
                                    </c:otherwise>
                                </c:choose>
                                </tbody>
                            </table>
                        </div>

                        <div class="col-md-12 filling-form block__display-none">
                            <form  method="post" role="form">
                                <div class="form-group">
                                    <input required type="hidden" disabled class="form-control filling-id" placeholder="Id">
                                </div>
                                <div class="form-group">
                                    <label>Name</label>
                                    <input minlength="1" maxlength="16" class="form-control filling-name" placeholder="Name">
                                </div>
                                <div class="form-group">
                                    <label>Calories</label>
                                    <input minlength="1" maxlength="5" type="number" class="form-control filling-calories" placeholder="Calories">
                                </div>
                                <div class="form-group">
                                    <label>Price</label>
                                    <input minlength="1" maxlength="5" type="number" class="form-control filling-price" placeholder="Price">
                                </div>
                            </form>
                            <div class="row">
                                <div class="col-md-6">
                                    <button type="button" class="btn btn-lg btn-success btn-block filling-save">Save
                                    </button>
                                    <button type="button" class="btn btn-lg btn-success btn-block filling-update">Update
                                    </button>
                                </div>
                                <div class="col-md-6">
                                    <button type="button" class="btn btn-lg btn-danger btn-block filling-close">Cancel
                                    </button>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<c:import url="/WEB-INF/views/adminPages/adminFooter.jsp/"/>
<script type="text/javascript" src="${contextPath}/resources/assets/js/jsData/filling.js"></script>
</body>
</html>