<%@ page pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="с" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <link href="${contextPath}/resources/assetsMainPages/css/bootstrap.css" rel="stylesheet" type="text/css" media="all"/>
    <link href="${contextPath}/resources/assetsMainPages/css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <link rel="stylesheet" href="${contextPath}/resources/assetsMainPages/css/flexslider.css" type="text/css" media="screen"/>
    <link href="${contextPath}/resources/assetsAdminPanel/css/sweetalert.css" rel="stylesheet">
    <!--//Custom Theme files -->
    <!--js-->

    <script type="text/javascript" src="${contextPath}/resources/assetsMainPages/js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="${contextPath}/resources/assetsMainPages/js/modernizr.custom.js"></script>
    <script type="text/javascript" src="${contextPath}/resources/jsData/sendAjax.js"></script>
    <script type="text/javascript" src="${contextPath}/resources/assetsMainPages/js/sweetalert.min.js"></script>
    <script type="text/javascript" src="${contextPath}/resources/jsData/product.js"></script>
    <script type="text/javascript" src="${contextPath}/resources/assetsMainPages/js/bootstrap.js"></script>
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

<div class="breadcrumbs">
    <div class="container">
        <ol class="breadcrumb breadcrumb1 animated wow slideInLeft" data-wow-delay=".5s">
            <li><a href="${contextPath}/welcome"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>Home</a></li>
            <li><a href="${contextPath}/filter"><span class="glyphicon glyphicon-search" aria-hidden="true"></span>Filter</a></li>
        </ol>
    </div>
</div>
<body>
<!--header-->
<div class="header">
    <div class="top-header navbar navbar-default"><!--header-one-->
        <div class="container">
            <div class="nav navbar-nav wow fadeInLeft animated" data-wow-delay=".5s">
                <sec:authorize access="!hasRole('ROLE_ADMIN') and !hasRole('ROLE_USER')">
                    <p><a href="${contextPath}/registration">Sign Up </a> Or <a href="${contextPath}/login">Sign In </a></p>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <p> Hi Admin, let's work<a href="${contextPath}/admin/adminPanel">Admin Panel</a>   <a href="${contextPath}/account">My Account</a>     <a href="${contextPath}/jsDonut/logout">Sing Out </a></p>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_USER')">
                    <p>Hi, ${pageContext.request.userPrincipal.name}    <a href="${contextPath}/account">My Account</a>     <a href="${contextPath}/logout">Sing Out </a></p>
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
                <h1><a href="${contextPath}/welcome">Welcome to Donut Shop<b></b><span class="tag">Everything for your Sweet Dream </span> </a>
                </h1>
            </div>
            <div class="nav navbar-nav navbar-right header-two-right">

                <div class="header-right cart">
                    <a href="${contextPath}/cart"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span></a>
                    <h4>
                        <fmt:formatNumber type="number" maxFractionDigits="2" var="price" value="${orderTotalPrice.totalPrice}" />
                        <span class="OrderTotalPrice">${price}₽</span>(<span class="CountProduct">${sessionScope.countProductInOrder.toString()}</span>)
                    </h4>
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
<!--//header-->
<!--banner-->
<a style="display:block" href="${contextPath}/filter">
<div   class="banner">
    <div class="container">
        <div class="banner-text">
            <div class="col-sm-5 banner-left wow fadeInLeft animated" data-wow-delay=".5s">
                <%--<h2>Our Special Delicious</h2>--%>
                <%--<div class="count main-row">--%>
                    <%--<ul id="example">--%>
                        <%--<li><span class="hours">00</span>--%>
                            <%--<p class="hours_text">Hours</p></li>--%>
                        <%--<li><span class="minutes">00</span>--%>
                            <%--<p class="minutes_text">Minutes</p></li>--%>
                        <%--<li><span class="seconds">00</span>--%>
                            <%--<p class="seconds_text">Seconds</p></li>--%>
                    <%--</ul>--%>
                    <%--<div class="clearfix"></div>--%>
                    <%--<script type="text/javascript" src="${contextPath}/resources/assetsMainPages/js/jquery.countdown.min.js"></script>--%>
                    <%--<script type="text/javascript">--%>
                        <%--$('#example').countdown({--%>
                            <%--date: '12/24/2020 15:59:59',--%>
                            <%--offset: -8,--%>
                            <%--day: 'Day',--%>
                            <%--days: 'Days'--%>
                        <%--}, function () {--%>
                            <%--alert('Done!');--%>
                        <%--});--%>
                    <%--</script>--%>
                <%--</div>--%>

            </div>
            <div class="col-sm-7 banner-right wow fadeInRight animated" data-wow-delay=".5s">
                <section class="slider grid">
                    <div class="flexslider">
                        <ul class="slides">
                            <li>
                                <h4></h4>
                                <img src="${contextPath}/resources/assetsMainPages/images/b1.jpg" alt="">
                            </li>
                            <li>
                                <h4></h4>
                                <img src="${contextPath}/resources/assetsMainPages/images/b2.jpg" alt="">
                            </li>
                            <li>
                                <h4></h4>
                                <img src="${contextPath}/resources/assetsMainPages/images/b3.jpg" alt="">
                            </li>
                        </ul>
                    </div>
                </section>
                <!--FlexSlider-->
                <script defer src="${contextPath}/resources/assetsMainPages/js/jquery.flexslider.js"></script>
                <script type="text/javascript">
                    $(window).load(function () {
                        $('.flexslider').flexslider({
                            animation: "pagination",
                            start: function (slider) {
                                $('body').removeClass('loading');
                            }
                        });
                    });
                </script>
                <!--End-slider-script-->
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
</div>
</a>
<!--//banner-->
<!--new-->

<!--//new-->
<!--gallery-->

<!--//trend-->
<!--footer-->
<div class="footer">
    <div class="container">
        <div class="footer-info">
            <div class="col-md-4 footer-grids wow fadeInUp animated" data-wow-delay=".5s">
                <h4 class="footer-logo"><a href="${contextPath}/welcome">Donut <b>Shop</b> <span class="tag">Everything for you Sweet Dream </span>
                </a></h4>
                <p>© 2018 Donut Shop. All rights reserved</p>
            </div>
        </div>
    </div>
</div>
<!--//footer-->
<!--search jQuery-->
<script src="${contextPath}/resources/assetsMainPages/js/main.js"></script>
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
<!--//smooth-scrolling-of-move-up-->
<!--Bootstrap core JavaScript
================================================== -->
<!--Placed at the end of the document so the pages load faster -->
<script src="${contextPath}/resources/assetsMainPages/js/bootstrap.js"></script>
</body>
</html>