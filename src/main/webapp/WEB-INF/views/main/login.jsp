<%@ page pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>


<!DOCTYPE html>
<html>
<head>
    <title>Modern Shoppe a Ecommerce Online Shopping Category Flat Bootstrap Responsive Website Template | Home ::
        w3layouts</title>
    <!-- for-mobile-apps -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content="Modern Shoppe Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template,
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design"/>
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
<!--header-->
<div class="header">
    <div class="top-header navbar navbar-default"><!--header-one-->
        <div class="container">
            <div class="nav navbar-nav wow fadeInLeft animated" data-wow-delay=".5s">
                <sec:authorize access="!hasRole('ROLE_ADMIN') and !hasRole('ROLE_USER')">
                    <p>Welcome to Donut<a href="${contextPath}/jsDonut/registration">Sign Up </a> Or <a href="${contextPath}/jsDonut/login">Sign In </a></p>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <p><a href="/jsDonut/admin/adminPanel">Admin Panel</a> We will glad to see you again <a href="/jsDonut/logout">Sing Out </a></p>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_USER')">
                    <p>We will glad to see you again <a href="/jsDonut/logout">Sing Out </a></p>
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
                    <li><i class="glyphicon glyphicon-earphone" aria-hidden="true"></i>+1234 567 892</li>
                    <li><i class="glyphicon glyphicon-envelope" aria-hidden="true"></i><a
                            href="mailto:info@example.com">mail@example.com</a></li>
                </ul>
            </div>
            <div class="nav navbar-nav logo wow zoomIn animated" data-wow-delay=".7s">
                <h1><a href="welcome.jsp">Modern <b>Shoppe</b><span class="tag">Everything for Kids world </span> </a>
                </h1>
            </div>
            <div class="nav navbar-nav navbar-right header-two-right">
                <div class="header-right my-account">
                    <a href="contact.jsp"><span class="glyphicon glyphicon-map-marker" aria-hidden="true"></span>
                        CONTACT US</a>
                </div>
                <div class="header-right cart">
                    <a href="#"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span></a>
                    <h4><a href="checkout.jsp">
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
                    <ul class="nav navbar-nav top-nav-info">
                        <li><a href="welcome.jsp">Home</a></li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Baby<b class="caret"></b></a>
                            <ul class="dropdown-menu multi-column multi-column1">
                                <div class="row">
                                    <div class="col-sm-4 menu-grids menulist1">
                                        <h4>Bath & Care</h4>
                                        <ul class="multi-column-dropdown ">
                                            <li><a class="list" href="products.jsp">Diapering</a></li>
                                            <li><a class="list" href="products.jsp">Baby Wipes</a></li>
                                            <li><a class="list" href="products.jsp">Baby Soaps</a></li>
                                            <li><a class="list" href="products.jsp">Lotions & Oils </a></li>
                                            <li><a class="list" href="products.jsp">Powders</a></li>
                                            <li><a class="list" href="products.jsp">Shampoos</a></li>
                                        </ul>
                                        <ul class="multi-column-dropdown">
                                            <li><a class="list" href="products.jsp">Body Wash</a></li>
                                            <li><a class="list" href="products.jsp">Cloth Diapers</a></li>
                                            <li><a class="list" href="products.jsp">Baby Nappies</a></li>
                                            <li><a class="list" href="products.jsp">Medical Care</a></li>
                                            <li><a class="list" href="products.jsp">Grooming</a></li>
                                            <li><h6><a class="list" href="products.jsp">Combo Packs</a></h6></li>
                                        </ul>
                                    </div>
                                    <div class="col-sm-2 menu-grids">
                                        <h4>Baby Clothes</h4>
                                        <ul class="multi-column-dropdown">
                                            <li><a class="list" href="products.jsp">Onesies & Rompers</a></li>
                                            <li><a class="list" href="products.jsp">Frocks</a></li>
                                            <li><a class="list" href="products.jsp">Socks & Tights</a></li>
                                            <li><a class="list" href="products.jsp">Sweaters & Caps</a></li>
                                            <li><a class="list" href="products.jsp">Night Wear</a></li>
                                            <li><a class="list" href="products.jsp">Quilts & Wraps</a></li>
                                        </ul>
                                    </div>
                                    <div class="col-sm-3 menu-grids">
                                        <ul class="multi-column-dropdown">
                                            <li><a class="list" href="products.jsp">Blankets</a></li>
                                            <li><a class="list" href="products.jsp">Gloves & Mittens</a></li>
                                            <h4>Shop by Age</h4>
                                            <li><a class="list" href="products.jsp">New Born (0 - 5 M)</a></li>
                                            <li><a class="list" href="products.jsp">5 - 10 Months</a></li>
                                            <li><a class="list" href="products.jsp">10 - 15 Months</a></li>
                                            <li><a class="list" href="products.jsp">15 Months Above</a></li>
                                        </ul>
                                    </div>
                                    <div class="col-sm-3 menu-grids">
                                        <ul class="multi-column-dropdown">
                                            <li><h6><a class="list" href="products.jsp">Feeding & Nursing</a></h6></li>
                                            <h4>Baby Gear</h4>
                                            <li><a class="list" href="products.jsp">Baby Walkers</a></li>
                                            <li><a class="list" href="products.jsp">Strollers</a></li>
                                            <li><a class="list" href="products.jsp">Prams & Toys</a></li>
                                            <li><a class="list" href="products.jsp">Cribs & Cradles</a></li>
                                            <li><a class="list" href="products.jsp">Booster Seats</a></li>
                                        </ul>
                                    </div>
                                    <div class="clearfix"></div>
                                </div>
                            </ul>
                        </li>
                        <li class="dropdown grid">
                            <a href="#" class="dropdown-toggle list1" data-toggle="dropdown">Kids<b
                                    class="caret"></b></a>
                            <ul class="dropdown-menu multi-column multi-column2">
                                <div class="row">
                                    <div class="col-sm-3 menu-grids">
                                        <h4>New Arrivals</h4>
                                        <ul class="multi-column-dropdown">
                                            <li><a class="list" href="products.jsp">Topwear</a></li>
                                            <li><a class="list" href="products.jsp">Bottomwear</a></li>
                                            <li><a class="list" href="products.jsp">Innerwear</a></li>
                                            <li><a class="list" href="products.jsp">Nightwear</a></li>
                                            <li><a class="list" href="products.jsp">Swimwear</a></li>
                                            <li><a class="list" href="products.jsp">Jumpers</a></li>
                                        </ul>
                                    </div>
                                    <div class="col-sm-3 menu-grids">
                                        <h4>Boys</h4>
                                        <ul class="multi-column-dropdown">
                                            <li><a class="list" href="products.jsp">Jeans</a></li>
                                            <li><a class="list" href="products.jsp">Shirts</a></li>
                                            <li><a class="list" href="products.jsp">T-shirts</a></li>
                                            <li><a class="list" href="products.jsp">Dhoti Kurta Sets</a></li>
                                            <li><a class="list" href="products.jsp">Winter wear</a></li>
                                            <li><a class="list" href="products.jsp">Party Wear</a></li>
                                        </ul>
                                    </div>
                                    <div class="col-sm-3 menu-grids">
                                        <h4>Girls</h4>
                                        <ul class="multi-column-dropdown">
                                            <li><a class="list" href="products.jsp">Tops</a></li>
                                            <li><a class="list" href="products.jsp">Leggings</a></li>
                                            <li><a class="list" href="products.jsp">Dresses </a></li>
                                            <li><a class="list" href="products.jsp">Skirts</a></li>
                                            <li><a class="list" href="products.jsp">Casual Dresses</a></li>
                                            <li><a class="list" href="products.jsp">Capris & 3/4ths</a></li>
                                        </ul>
                                    </div>
                                    <div class="col-sm-3 menu-grids new-add2">
                                        <a href="products.jsp">
                                            <h6>Kids Special</h6>
                                            <img src="${contextPath}/resources/assetsMainPages/images/img1.jpg" alt="">
                                        </a>
                                    </div>
                                    <div class="clearfix"></div>
                                </div>
                            </ul>
                        </li>
                        <li class="dropdown grid">
                            <a href="#" class="dropdown-toggle list1" data-toggle="dropdown">Accessories<b
                                    class="caret"></b></a>
                            <ul class="dropdown-menu multi-column menu-two multi-column3">
                                <div class="row">
                                    <div class="col-sm-4 menu-grids">
                                        <ul class="multi-column-dropdown">
                                            <li><a class="list" href="products.jsp">Jewellery</a></li>
                                            <li><a class="list" href="products.jsp">Hair bands & Clips</a></li>
                                            <li><a class="list" href="products.jsp">Bangles </a></li>
                                            <li><a class="list" href="products.jsp">Caps & Belts</a></li>
                                            <li><a class="list" href="products.jsp">Rain wear</a></li>
                                            <li><a class="list" href="products.jsp">Bags</a></li>
                                        </ul>
                                    </div>
                                    <div class="col-sm-8 menu-grids">
                                        <a href="products.jsp">
                                            <div class="new-add">
                                                <h5>30% OFF <br> Today Only</h5>
                                            </div>
                                        </a>
                                    </div>
                                    <div class="clearfix"></div>
                                </div>
                            </ul>
                        </li>
                        <li class="dropdown grid">
                            <a href="#" class="dropdown-toggle list1" data-toggle="dropdown">Toys <b class="caret"></b></a>
                            <ul class="dropdown-menu multi-column multi-column4">
                                <div class="row">
                                    <div class="col-sm-4 menu-grids menulist1">
                                        <h4>BABY</h4>
                                        <ul class="multi-column-dropdown ">
                                            <li><a class="list" href="products.jsp">Rockers</a></li>
                                            <li><a class="list" href="products.jsp">Rattles</a></li>
                                            <li><a class="list" href="products.jsp">Stroller Toys</a></li>
                                            <li><a class="list" href="products.jsp">Musical Toys </a></li>
                                            <li><a class="list" href="products.jsp">Doll Houses</a></li>
                                            <li><a class="list" href="products.jsp">Play Sets</a></li>
                                        </ul>
                                        <ul class="multi-column-dropdown">
                                            <li><a class="list" href="products.jsp">Toys Dolls</a></li>
                                            <li><a class="list" href="products.jsp">Pacifiers</a></li>
                                            <li><a class="list" href="products.jsp">Building Sets</a></li>
                                            <li><a class="list" href="products.jsp">Bath Toys</a></li>
                                            <li><a class="list" href="products.jsp">Soft Toys</a></li>
                                            <li><h6><a class="list" href="products.jsp">Special Off</a></h6></li>
                                        </ul>
                                    </div>
                                    <div class="col-sm-2 menu-grids">
                                        <h4>Pretend Play</h4>
                                        <ul class="multi-column-dropdown">
                                            <li><h6><a class="list" href="products.jsp">Video Games</a></h6></li>
                                            <li><a class="list" href="products.jsp">Kitchen Sets</a></li>
                                            <li><a class="list" href="products.jsp">Sand Toys</a></li>
                                            <li><a class="list" href="products.jsp">Tool Sets</a></li>
                                            <li><a class="list" href="products.jsp">Bath Toys</a></li>
                                            <li><a class="list" href="products.jsp">Medical Set</a></li>
                                        </ul>
                                    </div>
                                    <div class="col-sm-2 menu-grids">
                                        <h4>Outdoor</h4>
                                        <ul class="multi-column-dropdown">
                                            <li><a class="list" href="products.jsp">Swimming</a></li>
                                            <li><a class="list" href="products.jsp">Rideons </a></li>
                                            <li><a class="list" href="products.jsp">Scooters</a></li>
                                            <li><a class="list" href="products.jsp">Remote Control</a></li>
                                            <li><a class="list" href="products.jsp">Animals</a></li>
                                            <li><a class="list" href="products.jsp">Make up</a></li>
                                        </ul>
                                    </div>
                                    <div class="col-sm-4 menu-grids">
                                        <a href="products.jsp">
                                            <div class="new-add">
                                                <h5>30% OFF <br> Today Only</h5>
                                            </div>
                                        </a>
                                    </div>
                                    <div class="clearfix"></div>
                                </div>
                            </ul>
                        </li>
                        <li><a href="codes.jsp">Special Offers</a></li>
                    </ul>
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
                    <input type="search" placeholder="Search...">
                </form>
            </div>
        </div>
    </div>
</div>
<!--//header-->
<!--breadcrumbs-->
<div class="breadcrumbs">
    <div class="container">
        <ol class="breadcrumb breadcrumb1 animated wow fadeInUp" data-wow-delay=".5s">
            <li><a href="welcome.jsp"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>Home</a></li>
            <li class="active">Sign In</li>
        </ol>
    </div>
</div>
<!--//breadcrumbs-->
<!--login-->
<div class="login-page">
    <div class="title-info wow fadeInUp animated" data-wow-delay=".5s">
        <h3 class="title">SignIn<span> Form</span></h3>
        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit curabitur </p>
    </div>
    <div class="widget-shadow">
        <div class="login-top wow fadeInUp animated" data-wow-delay=".7s">
            <h4>Welcome back to Modern Shoppe ! <br> Not a Member? <a href="registration.jsp"> Register Now »</a></h4>
        </div>
        <div class="login-body wow fadeInUp animated" data-wow-delay=".7s">
            <form method="post" action="/login">
                <div class="${error != null ? 'has-error' : ''}">
                    <span>${message}</span>
                    <input type="text" class="user" name="username" placeholder="Login" required="">
                    <input type="password" name="password" class="lock" placeholder="Password">
                    <span>${error}</span>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input type="submit" name="Sign In" value="Sign In">
                    <div class="forgot-grid">
                        <label class="checkbox"><input type="checkbox" name="checkbox"><i></i>Remember me</label>
                        <div class="forgot">
                            <a href="#">Forgot Password?</a>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="login-page-bottom">
        <h5> - OR -</h5>
        <div class="social-btn"><a href="#"><i>Sign In with Facebook</i></a></div>
        <div class="social-btn sb-two"><a href="#"><i>Sign In with Twitter</i></a></div>
    </div>
</div>
<!--//login-->
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
                    <li><a href="checkout.jsp">Wishlist</a></li>
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