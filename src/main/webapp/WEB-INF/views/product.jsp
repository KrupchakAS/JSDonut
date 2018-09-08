<%@ page pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="с" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<c:import url="/WEB-INF/views/header.jsp"/>

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
        <div class="profile-userpic">
            <img src="http://placehold.it/50/30a5ff/fff" class="img-responsive" alt="">
        </div>
        <div class="profile-usertitle">
            <div class="profile-usertitle-name">ADMIN</div>
            <div class="profile-usertitle-status"><span class="indicator label-success"></span>Online</div>
        </div>
        <div class="clear"></div>
    </div>
    <div class="divider"></div>
    <form role="search">
        <div class="form-group">
            <input type="text" class="form-control" placeholder="Search">
        </div>
    </form>
    <ul class="nav menu">
        <li class="${productActive}"><a href="${contextPath}/jsDonut/admin/product"><em
                class="fa fa-dashboard">&nbsp;</em> Products</a></li>
        <li class="${sprinkleActive}"><a href="${contextPath}/jsDonut/admin/sprinkle"><em
                class="fa fa-dashboard">&nbsp;</em> Sprinkles</a></li>
        <li class="${doughActive}"><a href="${contextPath}/jsDonut/admin/dough"><em class="fa fa-dashboard">&nbsp;</em>
            Doughs</a></li>
        <li class="${fillingActive}"><a href="${contextPath}/jsDonut/admin/filling"><em
                class="fa fa-dashboard">&nbsp;</em> Fillings</a></li>

    </ul>
</div>

<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
    <div class="row">
        <ol class="breadcrumb">
            <li><a href="#">
                <em class="fa fa-home"></em>
            </a></li>
            <li class="active">Products</li>
        </ol>
    </div><!--/.row-->

    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">Products</h1>
        </div>
    </div><!--/.row-->

    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading container-head">
                    Product list
                </div>
                <div class="panel-body container-body">
                    <div class="row">
                        <div class="col-md-12 product-list">
                            <table class="table table-striped product-table">
                                <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Name</th>
                                    <th>Description</th>
                                    <th>Price</th>
                                    <th>WorkPrice</th>
                                    <th>Weight</th>
                                    <th>Quantity</th>
                                    <th>Calories</th>
                                    <th>Category</th>
                                    <th>User</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:choose>
                                    <c:when test="${productList.size() > 0}">
                                        <с:forEach var="product" items="${productList}">
                                            <tr class="product-table__row" data-id="${product.id}">
                                                <th>${product.id}</th>
                                                <th>${product.name}</th>
                                                <th>${product.description}</th>
                                                <th>${product.price}</th>
                                                <th>${product.workPrice}</th>
                                                <th>${product.weight}</th>
                                                <th>${product.quantity}</th>
                                                <th>${product.calories}</th>
                                                <th>${product.category.name}</th>
                                                <th>${product.user.name}</th>
                                                <th>
                                                    <button type="button" class="btn btn-md btn-primary product-edit">
                                                        Edit
                                                    </button>
                                                </th>
                                                <th>
                                                    <button type="button" class="btn btn-md btn-danger product-delete">
                                                        Delete
                                                    </button>
                                                </th>
                                            </tr>
                                        </с:forEach>
                                    </c:when>
                                    <c:otherwise>
                                        <tr>
                                            <th colspan="4">Not product</th>
                                        </tr>
                                    </c:otherwise>
                                </c:choose>
                                </tbody>
                            </table>
                        </div>
                        <div class="col-md-12 product-form block__display-none">
                            <form role="form">
                                <div class="form-group">
                                    <label>Name</label>
                                    <input class="form-control product-name" placeholder="Name">
                                </div>
                                <div class="form-group">
                                    <label>Description</label>
                                    <input class="form-control product-description" placeholder="Description">
                                </div>
                                <div class="form-group">
                                    <label>Price</label>
                                    <input class="form-control product-price" placeholder="Price">
                                </div>
                                <div class="form-group">
                                    <label>WorkPrice</label>
                                    <input class="form-control product-workPrice" placeholder="WorkPrice">
                                </div>
                                <div class="form-group">
                                    <label>Weight</label>
                                    <input class="form-control product-weight" placeholder="Weight">
                                </div>
                                <div class="form-group">
                                    <label>Quantity</label>
                                    <input class="form-control product-quantity" placeholder="Quantity">
                                </div>
                                <div class="form-group">
                                    <label>Calories</label>
                                    <input class="form-control product-calories" placeholder="Calories">
                                </div>
                            </form>
                            <div class="row">
                                <div class="col-md-6">
                                    <button type="button" class="btn btn-lg btn-success btn-block product-save">Save
                                    </button>
                                </div>
                                <div class="col-md-6">
                                    <button type="button" class="btn btn-lg btn-danger btn-block product-close">Cancel
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div><!--/.row-->
</div>    <!--/.main-->

<c:import url="/WEB-INF/views/footer.jsp"/>
<script type="text/javascript" src="${contextPath}/resources/assets/js/jsData/product.js"></script>
</body>
</html>