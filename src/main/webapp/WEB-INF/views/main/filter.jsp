<%@ page pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <title>Donut Shop</title>
    <!-- for-mobile-apps -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content="Donut Shop"/>
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
    <!--//Custom Theme files -->
    <!--js-->
    <script src="${contextPath}/resources/assetsMainPages/js/jquery-1.11.1.min.js"></script>
    <script src="${contextPath}/resources/assetsMainPages/js/modernizr.custom.js"></script>
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
<!--breadcrumbs-->
<div class="breadcrumbs">
    <div class="container">
        <ol class="breadcrumb breadcrumb1 animated wow slideInLeft" data-wow-delay=".5s">
            <li><a href="/jsDonut/welcome"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>Home</a></li>

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
                    <p>Welcome to Donut Shop<a href="${contextPath}/jsDonut/registration">Sign Up </a> Or <a
                            href="${contextPath}/jsDonut/login">Sign In </a></p>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <p> Hi Admin, let's work<a href="/jsDonut/admin/adminPanel">Admin Panel</a> We will glad to see you
                        again <a href="/jsDonut/logout">Sing Out </a></p>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_USER')">
                    <p>We will glad to see you again, ${pageContext.request.userPrincipal.name} <a
                            href="/jsDonut/logout">Sing Out </a></p>
                </sec:authorize>
            </div>
            <div class="nav navbar-nav navbar-right social-icons wow fadeInRight animated" data-wow-delay=".5s">
                <ul>
                    <li><a href="#"> </a></li>
                    <li><a href="#" class="pin"> </a></li>
                    <li><a href="#" class="in"> </a></li>
                    <li><a href="#" class="be"> </a></li>
                    <li><a href="#" class="you"> </a></li>
                    <li><a href="#" class="vimeo"> </a></li>
                </ul>
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
                            href="mailto:info@example.com">KrupchakAS@yandex.ru</a></li>
                </ul>
            </div>
            <div class="nav navbar-nav logo wow zoomIn animated" data-wow-delay=".7s">
                <h1><a href="${contextPath}/jsDonut/welcome">Welcome to Donut Shop<b></b><span class="tag">Everything for your Sweet Dream </span>
                </a>
                </h1>
            </div>
            <div class="nav navbar-nav navbar-right header-two-right">
                <div class="header-right my-account">
                    <a href="contact.jsp"><span class="glyphicon glyphicon-map-marker" aria-hidden="true"></span>
                        CONTACT US</a>
                </div>
                <div class="header-right cart">
                    <a href="#"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span></a>
                    <h4><a href="${contextPath}/jsDonut/filter">
                        <span class="simpleCart_total"> $0.00 </span> (<span id="simpleCart_quantity"
                                                                             class="simpleCart_quantity"> 0 </span>)
                    </a></h4>
                    <div class="cart-box">
                        <p><a href="javascript:;" class="simpleCart_empty">Empty cart</a></p>
                        <div class="clearfix"></div>
                    </div>
                </div>
                <div class="clearfix"></div>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
</div>
<div class="top-nav navbar navbar-default"><!--header-three-->
    <div class="container">
        <nav class="navbar" role="navigation">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse"
                        data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div>
            <!--navbar-header-->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <div class="clearfix"></div>
                <!--//navbar-collapse-->
                <header class="cd-main-header">
                    <ul class="cd-header-buttons">
                        <li><a class="cd-search-trigger" href="#cd-search"> <span></span></a></li>
                    </ul> <!-- cd-header-buttons -->
                </header>
            </div>
            <!--//navbar-header-->
        </nav>
        <div id="cd-search" class="cd-search">
            <form>
                <input class="categoryName-Search" type="search" placeholder="Enter Category">
                <input class="productName-Search" type="search" placeholder="Enter Product name">
                <input class="minPrice-Search" type="search" placeholder="Enter min Price">
                <input class="maxPrice-Search" type="search" placeholder="Enter max Price">
            </form>
        </div>
    </div>
</div>
</div>
<!--//header-->
<!--cart-items-->
<div class="cart-items">
    <div class="container">
        <h3 class="wow fadeInUp animated" data-wow-delay=".5s">My Shopping Cart(3)</h3>
        <div class="cart-header wow fadeInUp animated" data-wow-delay=".5s">
            <div class="alert-close"></div>
            <div class="cart-sec simpleCart_shelfItem">
                <div class="cart-item cyc">
                    <a href="single.jsp"><img src="${contextPath}/resources/assetsMainPages/images/g1.jpg"
                                              class="img-responsive" alt=""></a>
                </div>
                <div class="cart-item-info">
                    <h4><a href="single.jsp"> Lorem Ipsum is not simply </a><span>Pickup time :</span></h4>
                    <ul class="qty">
                        <li><p>Min. order value :</p></li>
                        <li><p>FREE delivery</p></li>
                    </ul>
                    <div class="delivery">
                        <p>Service Charges : $10.00</p>
                        <span>Delivered in 1-1:30 hours</span>
                        <div class="clearfix"></div>
                    </div>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>
        <div class="cart-header1 wow fadeInUp animated" data-wow-delay=".7s">
            <div class="alert-close1"></div>
            <div class="cart-sec simpleCart_shelfItem">
                <div class="cart-item cyc">
                    <a href="single.jsp"><img src="${contextPath}/resources/assetsMainPages/images/g5.jpg"
                                              class="img-responsive" alt=""></a>
                </div>
                <div class="cart-item-info">
                    <h4><a href="single.jsp"> Lorem Ipsum is not simply </a><span>Pickup time :</span></h4>
                    <ul class="qty">
                        <li><p>Min. order value :</p></li>
                        <li><p>FREE delivery</p></li>
                    </ul>
                    <div class="delivery">
                        <p>Service Charges : $10.00</p>
                        <span>Delivered in 1-1:30 hours</span>
                        <div class="clearfix"></div>
                    </div>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>
        <div class="cart-header2 wow fadeInUp animated" data-wow-delay=".9s">
            <div class="alert-close2"></div>
            <div class="cart-sec simpleCart_shelfItem">
                <div class="cart-item cyc">
                    <a href="single.jsp"><img src="${contextPath}/resources/assetsMainPages/images/g9.jpg"
                                              class="img-responsive" alt=""></a>
                </div>
                <div class="cart-item-info">
                    <h4><a href="single.jsp"> Lorem Ipsum is not simply </a><span>Pickup time :</span></h4>
                    <ul class="qty">
                        <li><p>Min. order value :</p></li>
                        <li><p>FREE delivery</p></li>
                    </ul>
                    <div class="delivery">
                        <p>Service Charges : 200.00</p>
                        <span>Delivered in 1-1:30 hours</span>
                        <div class="clearfix"></div>
                    </div>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>
    </div>
</div>
<!--//cart-items-->
<!--footer-->
<div class="footer">
    <div class="container">
        <div class="footer-info">
            <div class="col-md-4 footer-grids wow fadeInUp animated" data-wow-delay=".5s">
                <h4 class="footer-logo"><a href="welcome.jsp">Modern <b>Shoppe</b> <span class="tag">Everything for Kids world </span>
                </a></h4>
                <p>© 2016 Modern Shoppe . All rights reserved | Design by <a href="http://w3layouts.com"
                                                                             target="_blank">W3layouts</a></p>
            </div>
            <div class="col-md-4 footer-grids wow fadeInUp animated" data-wow-delay=".7s">
                <h3>Popular</h3>
                <ul>
                    <li><a href="about.jsp">About Us</a></li>
                    <li><a href="products.jsp">new</a></li>
                    <li><a href="contact.jsp">Contact Us</a></li>
                    <li><a href="faq.jsp">FAQ</a></li>
                    <li><a href="filter.jsp">Wishlist</a></li>
                </ul>
            </div>
            <div class="col-md-4 footer-grids wow fadeInUp animated" data-wow-delay=".9s">
                <h3>Subscribe</h3>
                <p>Sign Up Now For More Information <br> About Our Company </p>
                <form>
                    <input type="text" placeholder="Enter Your Email" required="">
                    <input type="submit" value="Go">
                </form>
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