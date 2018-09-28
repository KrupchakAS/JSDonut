<%@ page pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="с" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <title>Donut Shop</title>
    <!-- for-mobile-apps -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content="Donut Shop"/>
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <script type="application/x-javascript"> addEventListener("load", function () {
        setTimeout(hideURLbar, 0);
    }, false);

    function hideURLbar() {
        window.scrollTo(0, 1);
    } </script>
    <!--//for-mobile-apps -->
    <!--Custom Theme files -->
    <link href="${contextPath}/resources/assetsMainPages/css/bootstrap.css" rel="stylesheet" type="text/css"
          media="all"/>
    <link href="${contextPath}/resources/assetsMainPages/css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <link rel="stylesheet" href="${contextPath}/resources/assetsMainPages/css/flexslider.css" type="text/css"
          media="screen"/>
    <link href="${contextPath}/resources/assetsAdminPanel/css/sweetalert.css" rel="stylesheet">
    <link href="${contextPath}/resources/assetsAdminPanel/css/project.css" rel="stylesheet">
    <!--//Custom Theme files -->
    <!--js-->
    <script type="text/javascript" src="${contextPath}/resources/assetsMainPages/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="${contextPath}/resources/assetsMainPages/js/modernizr.custom.js"></script>
    <script type="text/javascript" src="${contextPath}/resources/jsData/sendAjax.js"></script>
    <script type="text/javascript" src="${contextPath}/resources/assetsMainPages/js/sweetalert.min.js"></script>
    <script type="text/javascript" src="${contextPath}/resources/jsData/product.js"></script>
    <script type="text/javascript" src="${contextPath}/resources/assetsMainPages/js/bootstrap.js"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/css/select2.min.css" rel="stylesheet"/>
    <!--//js-->
    <!--cart-->
    <script src="${contextPath}/resources/assetsMainPages/js/simpleCart.js"></script>
    <!--cart-->
    <!--web-fonts-->
    <link href='//fonts.googleapis.com/css?family=Raleway:400,100,100italic,200,200italic,300,400italic,500,500italic,600,600italic,700,700italic,800,800italic,900,900italic'
          rel='stylesheet' type='text/css'>
    <link href='//fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic'
          rel='stylesheet' type='text/css'>
    <link href='//fonts.googleapis.com/css?family=Pompiere' rel='stylesheet' type='text/css'>
    <link href='//fonts.googleapis.com/css?family=Fascinate' rel='stylesheet' type='text/css'>
    <!--web-fonts-->
    <!--animation-effect-->
    <link href="${contextPath}/resources/assetsMainPages/css/animate.min.css" rel="stylesheet">
    <script src="${contextPath}/resources/assetsMainPages/js/wow.min.js"></script>
    <script>
        new WOW().init();
    </script>
    <!--//animation-effect-->
    <!--close-button-->
    <script>$(document).ready(function (c) {
        $('.alert-close').on('click', function (c) {
            $('.cart-header').fadeOut('slow', function (c) {
                $('.cart-header').remove();
            });
        });
    });
    </script>
    <script>$(document).ready(function (c) {
        $('.alert-close1').on('click', function (c) {
            $('.cart-header1').fadeOut('slow', function (c) {
                $('.cart-header1').remove();
            });
        });
    });
    </script>
    <script>$(document).ready(function (c) {
        $('.alert-close2').on('click', function (c) {
            $('.cart-header2').fadeOut('slow', function (c) {
                $('.cart-header2').remove();
            });
        });
    });
    </script>
    <!--//close-button-->
    <!--start-smooth-scrolling-->
    <script type="text/javascript" src="${contextPath}/resources/assetsMainPages/js/move-top.js"></script>
    <script type="text/javascript" src="${contextPath}/resources/assetsMainPages/js/easing.js"></script>
    <script type="text/javascript">
        jQuery(document).ready(function ($) {
            $(".scroll").click(function (event) {
                event.preventDefault();
                $('html,body').animate({scrollTop: $(this.hash).offset().top}, 1000);
            });
        });
    </script>
    <!--//end-smooth-scrolling-->
</head>
<body>
<!--breadcrumbs-->
<div class="breadcrumbs">
    <div class="container">
        <ol class="breadcrumb breadcrumb1 animated wow slideInLeft" data-wow-delay=".5s">
            <li><a href="/jsDonut/welcome"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>Home</a>
            </li>
            <li><a href="/jsDonut/filter"><span class="glyphicon glyphicon-search" aria-hidden="true"></span>Filter</a>
            </li>
        </ol>
    </div>
</div>
<!--//breadcrumbs-->
<!--header-->
<div class="header">
    <div class="top-header navbar navbar-default"><!--header-one-->
        <div class="container">
            <div class="nav navbar-nav wow fadeInLeft animated" data-wow-delay=".5s">
                <sec:authorize access="!hasRole('ROLE_ADMIN') and !hasRole('ROLE_USER')">
                    <p><a href="${contextPath}/jsDonut/registration">Sign Up </a> Or <a
                            href="${contextPath}/jsDonut/login">Sign In </a></p>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <p> Hi Admin, let's work<a href="/jsDonut/admin/adminPanel">Admin Panel</a> <a
                            href="/jsDonut/account">My Account</a> <a href="/jsDonut/logout">Sing Out </a></p>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_USER')">
                    <p>Hi, ${pageContext.request.userPrincipal.name} <a href="/jsDonut/account">My Account</a> <a
                            href="/jsDonut/logout">Sing Out </a></p>
                </sec:authorize>
            </div>

        </div>
    </div>
    <div class="header-two navbar navbar-default"><!--header-two-->
        <div class="container">
            <div class="nav navbar-nav header-two-left">
                <ul>
                    <li><i class="glyphicon glyphicon-earphone" aria-hidden="true"></i>+7 965 002 43 21</li>
                    <li><i class="glyphicon glyphicon-envelope" aria-hidden="true"></i><a
                            href="#">KrupchakAS@yandex.ru</a></li>
                </ul>
            </div>
            <div class="nav navbar-nav logo wow zoomIn animated" data-wow-delay=".7s">
                <h1><a href="${contextPath}/jsDonut/welcome">Welcome to Donut Shop<b></b><span class="tag">Everything for your Sweet Dream </span>
                </a>
                </h1>
            </div>
            <div class="nav navbar-nav navbar-right header-two-right">
                <div class="header-right cart">
                    <a href="${contextPath}/jsDonut/cart"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span></a><h4>
                        (<span class="CountProduct">${sessionScope.countProductInOrder.toString()}</span>)</h4>
                    <div class="cart-box">
                        <p><a href="#" class="ClearButton simpleCart_empty">Empty cart</a></p>
                        <div class="clearfix"></div>
                    </div>
                </div>
                <div class="clearfix"></div>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
</div>
<div class="row">
    <div style="border-right: 1px solid #ccc; padding-bottom : 5px; padding-top: 5px; margin-right: 30px;"
         class="col-sm-3 top-nav navbar navbar-default ">

        <div>
            <div class="row">
                <h3 style="color: #00a6d6;  margin-left: 50px">Filter Panel</h3>
                <form style="padding: 5px; margin: 30px" class=" form-group-sm">
                    <select style="margin-bottom: 5px" id="category" class="form-control categoryId-Search">
                        <option disabled value="0" selected>Category</option>
                        <c:choose>
                            <c:when test="${categoryList.size() > 0}">
                                <с:forEach var="category" items="${categoryList}">
                                    <option data-id="${category.id}" value="${category.id}">${category.name}</option>
                                </с:forEach>
                            </c:when>
                            <c:otherwise>
                                <option disabled selected>Category not found</option>
                            </c:otherwise>
                        </c:choose>
                    </select>

                    <select style="margin-bottom: 5px" id="filling" class="form-control fillingId-Search">
                        <option disabled value="0" selected>Filling</option>
                        <c:choose>
                            <c:when test="${fillingList.size() > 0}">
                                <с:forEach var="filling" items="${fillingList}">
                                    <option data-id="${filling.id}" value="${filling.id}">${filling.name}</option>
                                </с:forEach>
                            </c:when>
                            <c:otherwise>
                                <option disabled selected>Filling not found</option>
                            </c:otherwise>
                        </c:choose>
                    </select>

                    <select style="margin-bottom: 5px" id="dough" class="form-control doughId-Search">
                        <option disabled value="0" selected>Dough</option>
                        <c:choose>
                            <c:when test="${doughList.size() > 0}">
                                <с:forEach var="dough" items="${doughList}">
                                    <option data-id="${dough.id}" value="${dough.id}">${dough.name}</option>
                                </с:forEach>
                            </c:when>
                            <c:otherwise>
                                <option disabled selected>Dough not found</option>
                            </c:otherwise>
                        </c:choose>
                    </select>

                    <select  id="sprinkles" name="sprinkles[]" class="form-control sprinkleId-Search" multiple="multiple">
                        <option disabled value="0" >Sprinkle</option>
                        <c:choose>
                            <c:when test="${sprinkleList.size() > 0}">
                                <с:forEach var="sprinkle" items="${sprinkleList}">
                                    <option data-id="${sprinkle.id}" value="${sprinkle.id}">${sprinkle.name}</option>
                                </с:forEach>
                            </c:when>
                            <c:otherwise>
                                <option disabled selected>Sprinkle not found</option>
                            </c:otherwise>
                        </c:choose>
                    </select>

                    <input style="margin-bottom: 5px;margin-top: 5px" class="form-control productName-Search" type="text"
                           placeholder="Product name">

                    <input style="margin-bottom: 5px" class=" form-control minPrice-Search" type="number"
                           placeholder="min Price">

                    <input style="margin-bottom: 5px" class=" form-control maxPrice-Search" type="number"
                           placeholder="max Price">

                    <h2 ><a href="#"><span
                            class="products-search label label-info">Search</span></a></h2>

                </form>
            </div>
        </div>
    </div>

    <div class="DefaultProducts col-sm-8 cart-items block__display-none">
        <div>
            <c:choose>
                <c:when test="${allProducts.size() > 0}">
                    <с:forEach var="product" items="${allProducts}">
                        <div class="single-info">
                            <div class="single-top-left simpleCart_shelfItem wow fadeInRight animated"
                                 data-wow-delay=".5s">
                                <h3 style="float: right" class="item_price">${product.price}₽</h3>
                                <span>(${product.category.name})</span>
                                <h6>${product.name}</h6>
                                <p>${product.description}</p>
                                <span>Filling: ${product.filling.name}</span><span>Dough: ${product.dough.name}</span>
                                <span style="color: #c0a16b">Calories: ${product.calories}</span>
                                <div style="float: right" class="quantity"><p style="color: red" class="qty">Select
                                    Quantity: </p><input min="1" type="number" value="1" name="item_quantity"
                                                         class="item_quantity">
                                    <div style="float: inherit; margin-left: 15px" data-id="${product.id}"
                                         class="btn_form"><a href="#" style="color: green" class="add-cart item_add">Add
                                        To Cart</a></div>
                                </div>
                            </div>
                        </div>
                        <hr>
                    </с:forEach>
                </c:when>
                <c:otherwise>
                    <span>not found</span>
                </c:otherwise>
            </c:choose>
        </div>
    </div>

    <!--cart-items-->
    <div class="col-sm-8 cart-items">
        <div class="Product-item">

        </div>
    </div>
</div>
<!--//cart-items-->
<!--footer-->
<div class="footer">
    <div class="container">
        <div class="footer-info">
            <div class="col-md-4 footer-grids wow fadeInUp animated" data-wow-delay=".5s">
                <h4 class="footer-logo"><a href="/jsDonut/welcome">Donut <b>Shop</b> <span class="tag">Everything for you Sweet Dream </span>
                </a></h4>
                <p>© 2018 Donut Shop. All rights reserved</p>
            </div>
            <div class="col-md-4 footer-grids wow fadeInUp animated" data-wow-delay=".7s">
                <h3>Popular</h3>
                <ul>
                    <li><a href="#">About Us</a></li>
                    <li><a href="#">new</a></li>
                    <li><a href="#">Contact Us</a></li>
                    <li><a href="#">FAQ</a></li>
                    <li><a href="#">Wishlist</a></li>
                </ul>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
</div>
<!--//footer-->
<!--search jQuery-->
<script src="${contextPath}/resources/assetsMainPages/js/main.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js"></script>
<!--//search jQuery-->
<!--smooth-scrolling-of-move-up-->
<script type="text/javascript">
    $(document).ready(function () {

        var defaults = {
            containerID: 'toTop', // fading element id
            containerHoverID: 'toTopHover', // fading element hover id
            scrollSpeed: 1200,
            easingType: 'linear'
        };

        $().UItoTop({easingType: 'easeOutQuart'});

    });
</script>
<script>$(document).ready(function() {
    $('.sprinkleId-Search').select2();
});</script>
</body>
</html>