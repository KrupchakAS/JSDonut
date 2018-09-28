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
    <!--//js-->
    <!--cart-->
    <script src="${contextPath}/resources/assetsMainPages/js/simpleCart.min.js"></script>
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
<!--header-->
<div class="header">
    <div class="top-header navbar navbar-default"><!--header-one-->
        <div class="container">
            <div class="nav navbar-nav wow fadeInLeft animated" data-wow-delay=".5s">
                <sec:authorize access="!hasRole('ROLE_ADMIN') and !hasRole('ROLE_USER')">
                    <p>Welcome to Donut Shop<a href="${contextPath}/jsDonut/registration">Sign Up </a> Or <a
                            href="${contextPath}/jsDonut/login">Sign In </a></p>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <p> Hi Admin, let's work<a href="/jsDonut/admin/adminPanel">Admin Panel</a> We will glad to see you
                        again <a href="/jsDonut/logout">Sing Out </a></p>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_USER')">
                    <p>Hi, ${pageContext.request.userPrincipal.name} <a href="/jsDonut/account">My Account</a> <a
                            href="/jsDonut/logout">Sing Out </a></p>
                </sec:authorize>
            </div>
            <div class="clearfix"></div>
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

                <div class="clearfix"></div>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
</div>
<!--//header-->

<div class="row">
    <div style="border-right: 1px solid #ccc; padding-bottom : 5px; padding-top: 5px; margin-right: 30px;"
         class="col-sm-3 top-nav navbar navbar-default ">

        <div>
            <div class="row">
                <h3 style="color: tomato;  margin-left: 50px">Account Panel</h3>
                <br>

                <h5 style="color: #23527c;  margin-left: 50px">${user.firstName} ${user.surName}</h5>
                <br>
                <h5 style="color: #23527c;  margin-left: 50px">Дата рождения: ${user.birthDate}</h5>

                <form style="padding: 10px; margin: 30px" class=" form-group-sm">
                    <h4 style="margin: 10px;"><a href="#"><span
                            class="ChangeUserInfo label label-default">Change my Info</span></a></h4>
                    <h4 style="margin: 10px;"><a href="#"><span class="ChangeYourPassword label label-default">Change Password</span></a>
                    </h4>
                    <h4 style="margin: 10px;"><a href="#"><span
                            class="MyOrders label label-default">My Orders</span></a></h4>
                </form>
            </div>
        </div>
    </div>
    <div class="InfoDiv block__display-none login-page">
        <div class="widget-shadow">
            <div class="login-body wow fadeInUp animated" data-wow-delay=".7s">
                <form method="post" action="/jsDonut/changeUserInfo">
                    <div>
                        <input type="text" class="firstName" placeholder="firstName" minlength="2" maxlength="16">
                        <input type="text" class="surName" placeholder="surName" minlength="2" maxlength="16">
                        <input type="text" class="phoneNumber" placeholder="phoneNumber" minlength="10" maxlength="10">
                        <input type="date" class="birthDate" placeholder="birthDate">
                        <input type="submit" class="ChangeInfo" value="Change">
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="PasswordDiv block__display-none login-page">
        <div class="widget-shadow">
            <div class="login-body wow fadeInUp animated" data-wow-delay=".7s">
                <form method="post" action="/jsDonut/changeUserPassword">
                    <div>
                        <input type="password" class="Password" placeholder="Password" minlength="4" maxlength="16"
                               required>
                        <input type="password" class="ConfirmPassword" placeholder="ConfirmPassword" minlength="4"
                               maxlength="16" required>
                        <input type="submit" class="ChangePass" value="Change">
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="Orders col-sm-6 cart-items block__display-none">
        <div class="container">
            <c:choose>
                <c:when test="${orderList.size() > 0}">
                    <с:forEach var="order" items="${orderList}">
                        Order №${order.id} <h4 style="color: #ce8483">Order Status: (${order.getOrderStatus()})</h4><h4 style="color: #ce8483">Payment
                        Status:(${order.getPaymentStatus()})</h4>
                        <c:choose>
                            <c:when test="${order.getAddress() != null}">
                                <h4 style="color: #ce8483">Address Delivery:
                                    (${order.getAddress().city},${order.getAddress().street},${order.getAddress().houseNumber},${order.getAddress().apartmentNumber})</h4>
                            </c:when>
                            <c:otherwise>
                                <h4 style="color: #ce8483">PICKUP: -> Saint-Petersburg str.Buharestskaya 100.</h4>
                            </c:otherwise>
                        </c:choose>
                        <br>
                        <с:forEach var="product" items="${order.productList}">
                            <div>
                                <h6 style=" display: inline-block">${product.category.name}</h6>|<h5
                                    style="color: #c0a16b; display: inline-block"> ${product.name} </h5>|<h6
                                    style="display: inline-block"> Quantity: ${product.quantity}</h6>
                            </div>
                        </с:forEach>
                        <h3 style="padding-left: 700px;color: #c0a16b">Total Price: ${order.totalPrice}</h3>
                        <hr>
                    </с:forEach>
                </c:when>
                <c:otherwise>
                    <span>Orders not found</span>
                </c:otherwise>
            </c:choose>
            <hr>
        </div>
    </div>
</div>


<!--footer-->
<div class="footer">
    <div class="container">
        <div class="footer-info">
            <div class="col-md-4 footer-grids wow fadeInUp animated" data-wow-delay=".5s">
                <h4 class="footer-logo"><a href="${contextPath}/jsDonut/welcome">Donut <b>Shop</b> <span class="tag">Everything for you Sweet Dream  </span>
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