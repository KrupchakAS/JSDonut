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
    <link href="${contextPath}/resources/assetsMainPages/css/bootstrap.css" rel="stylesheet" type="text/css" media="all"/>
    <link href="${contextPath}/resources/assetsMainPages/css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <link rel="stylesheet" href="${contextPath}/resources/assetsMainPages/css/flexslider.css" type="text/css" media="screen"/>
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
                    <p>Welcome to Donut Shop<a href="${contextPath}/jsDonut/registration">Sign Up </a> Or <a href="${contextPath}/jsDonut/login">Sign In </a></p>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <p> Hi Admin, let's work<a href="/jsDonut/admin/adminPanel">Admin Panel</a> We will glad to see you again <a href="/jsDonut/logout">Sing Out </a></p>
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
                    <li><i class="glyphicon glyphicon-earphone" aria-hidden="true"></i>+7 965 002 43 21</li>
                    <li><i class="glyphicon glyphicon-envelope" aria-hidden="true"></i><a
                            href="mailto:info@example.com">KrupchakAS@yandex.ru</a></li>
                </ul>
            </div>
            <div class="nav navbar-nav logo wow zoomIn animated" data-wow-delay=".7s">
                <h1><a href="${contextPath}/jsDonut/welcome">Welcome to Donut Shop<b></b><span class="tag">Everything for your Sweet Dream </span> </a>
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
                        <li><a href="welcome.jsp" class="active">Home</a></li>
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
<!--banner-->
<div class="banner">
    <div class="container">
        <div class="banner-text">
            <div class="col-sm-5 banner-left wow fadeInLeft animated" data-wow-delay=".5s">
                <h2>On Entire Fashion range</h2>
                <h3>Coming Soon </h3>
                <h4>Our New Designs</h4>
                <div class="count main-row">
                    <ul id="example">
                        <li><span class="hours">00</span>
                            <p class="hours_text">Hours</p></li>
                        <li><span class="minutes">00</span>
                            <p class="minutes_text">Minutes</p></li>
                        <li><span class="seconds">00</span>
                            <p class="seconds_text">Seconds</p></li>
                    </ul>
                    <div class="clearfix"></div>
                    <script type="text/javascript" src="${contextPath}/resources/assetsMainPages/js/jquery.countdown.min.js"></script>
                    <script type="text/javascript">
                        $('#example').countdown({
                            date: '12/24/2020 15:59:59',
                            offset: -8,
                            day: 'Day',
                            days: 'Days'
                        }, function () {
                            alert('Done!');
                        });
                    </script>
                </div>

            </div>
            <div class="col-sm-7 banner-right wow fadeInRight animated" data-wow-delay=".5s">
                <section class="slider grid">
                    <div class="flexslider">
                        <ul class="slides">
                            <li>
                                <h4>-30%</h4>
                                <img src="${contextPath}/resources/assetsMainPages/images/b1.png" alt="">
                            </li>
                            <li>
                                <h4>-25%</h4>
                                <img src="${contextPath}/resources/assetsMainPages/images/b2.png" alt="">
                            </li>
                            <li>
                                <h4>-32%</h4>
                                <img src="${contextPath}/resources/assetsMainPages/images/b3.png" alt="">
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
<!--//banner-->
<!--new-->
<div class="new">
    <div class="container">
        <div class="title-info wow fadeInUp animated" data-wow-delay=".5s">
            <h3 class="title">New <span>Arrivals</span></h3>
            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit curabitur </p>
        </div>
        <div class="new-info">
            <div class="col-md-3 new-grid simpleCart_shelfItem wow flipInY animated" data-wow-delay=".5s">
                <div class="new-top">
                    <a href="single.jsp"><img src="${contextPath}/resources/assetsMainPages/images/g9.jpg" class="img-responsive" alt=""/></a>
                    <div class="new-text">
                        <ul>
                            <li><a class="item_add" href=""> Add to cart</a></li>
                            <li><a href="single.jsp">Quick View </a></li>
                            <li><a href="products.jsp">Show Details </a></li>
                        </ul>
                    </div>
                </div>
                <div class="new-bottom">
                    <h5><a class="name" href="single.jsp">Baby Red Dress </a></h5>
                    <div class="rating">
                        <span class="on">☆</span>
                        <span class="on">☆</span>
                        <span class="on">☆</span>
                        <span class="on">☆</span>
                        <span>☆</span>
                    </div>
                    <div class="ofr">
                        <p class="pric1">
                            <del>$2000.00</del>
                        </p>
                        <p><span class="item_price">$500.00</span></p>
                    </div>
                </div>
            </div>
            <div class="col-md-3 new-grid new-mdl simpleCart_shelfItem wow flipInY animated" data-wow-delay=".7s">
                <div class="new-top">
                    <a href="single.jsp"><img src="${contextPath}/resources/assetsMainPages/images/g10.jpg" class="img-responsive" alt=""/></a>
                    <div class="new-text">
                        <ul>
                            <li><a class="item_add" href=""> Add to cart</a></li>
                            <li><a href="single.jsp">Quick View </a></li>
                            <li><a href="products.jsp">Show Details </a></li>
                        </ul>
                    </div>
                </div>
                <div class="new-bottom">
                    <h5><a class="name" href="single.jsp">Crocs Sandals </a></h5>
                    <div class="rating">
                        <span class="on">☆</span>
                        <span class="on">☆</span>
                        <span class="on">☆</span>
                        <span class="on">☆</span>
                        <span>☆</span>
                    </div>
                    <div class="ofr">
                        <p><span class="item_price">$50.00</span></p>
                    </div>
                </div>
            </div>
            <div class="col-md-3 new-grid new-mdl1 simpleCart_shelfItem wow flipInY animated" data-wow-delay=".9s">
                <div class="new-top">
                    <a href="single.jsp"><img src="${contextPath}/resources/assetsMainPages/images/g11.jpg" class="img-responsive" alt=""/></a>
                    <div class="new-text">
                        <ul>
                            <li><a class="item_add" href=""> Add to cart</a></li>
                            <li><a href="single.jsp">Quick View </a></li>
                            <li><a href="products.jsp">Show Details </a></li>
                        </ul>
                    </div>
                </div>
                <div class="new-bottom">
                    <h5><a class="name" href="single.jsp">Pink Sip Cup </a></h5>
                    <div class="rating">
                        <span class="on">☆</span>
                        <span class="on">☆</span>
                        <span class="on">☆</span>
                        <span class="on">☆</span>
                        <span>☆</span>
                    </div>
                    <div class="ofr">
                        <p><span class="item_price">$150.00</span></p>
                    </div>
                </div>
            </div>
            <div class="col-md-3 new-grid simpleCart_shelfItem wow flipInY animated" data-wow-delay="1.1s">
                <div class="new-top">
                    <a href="single.jsp"><img src="${contextPath}/resources/assetsMainPages/images/g12.jpg" class="img-responsive" alt=""/></a>
                    <div class="new-text">
                        <ul>
                            <li><a class="item_add" href=""> Add to cart</a></li>
                            <li><a href="single.jsp">Quick View </a></li>
                            <li><a href="products.jsp">Show Details </a></li>
                        </ul>
                    </div>
                </div>
                <div class="new-bottom">
                    <h5><a class="name" href="single.jsp">Child Print Bike </a></h5>
                    <div class="rating">
                        <span class="on">☆</span>
                        <span class="on">☆</span>
                        <span class="on">☆</span>
                        <span class="on">☆</span>
                        <span>☆</span>
                    </div>
                    <div class="ofr">
                        <p class="pric1">
                            <del>$5050.00</del>
                        </p>
                        <p><span class="item_price">$3020.00</span></p>
                    </div>
                </div>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
</div>
<!--//new-->
<!--gallery-->
<div class="gallery">
    <div class="container">
        <div class="title-info wow fadeInUp animated" data-wow-delay=".5s">
            <h3 class="title">Popular<span> Products</span></h3>
            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit curabitur </p>
        </div>
        <div class="gallery-info">
            <div class="col-md-3 gallery-grid wow flipInY animated" data-wow-delay=".5s">
                <a href="products.jsp"><img src="${contextPath}/resources/assetsMainPages/images/g1.jpg" class="img-responsive" alt=""/></a>
                <div class="gallery-text simpleCart_shelfItem">
                    <h5><a class="name" href="single.jsp">Baby Girls' Dress </a></h5>
                    <p><span class="item_price">100$</span></p>
                    <h4 class="sizes">Sizes: <a href="#"> s</a> - <a href="#">m</a> - <a href="#">l</a> - <a
                            href="#">xl</a></h4>
                    <ul>
                        <li><a href="#"><span class="glyphicon glyphicon-globe" aria-hidden="true"></span></a></li>
                        <li><a class="item_add" href="#"><span class="glyphicon glyphicon glyphicon-shopping-cart"
                                                               aria-hidden="true"></span></a></li>
                        <li><a href="#"><span class="glyphicon glyphicon glyphicon-heart-empty"
                                              aria-hidden="true"></span></a></li>
                    </ul>
                </div>
            </div>
            <div class="col-md-3 gallery-grid gallery-grid1 wow flipInY animated" data-wow-delay=".7s">
                <a href="products.jsp"><img src="${contextPath}/resources/assetsMainPages/images/g2.jpg" class="img-responsive" alt=""/></a>
                <div class="gallery-text simpleCart_shelfItem">
                    <h5><a class="name" href="single.jsp">Pokemon Onesies</a></h5>
                    <p><span class="item_price">79$</span></p>
                    <h4 class="sizes">Sizes: <a href="#"> s</a> - <a href="#">m</a> - <a href="#">l</a> - <a
                            href="#">xl</a></h4>
                    <ul>
                        <li><a href="#"><span class="glyphicon glyphicon-globe" aria-hidden="true"></span></a></li>
                        <li><a class="item_add" href="#"><span class="glyphicon glyphicon glyphicon-shopping-cart"
                                                               aria-hidden="true"></span></a></li>
                        <li><a href="#"><span class="glyphicon glyphicon glyphicon-heart-empty"
                                              aria-hidden="true"></span></a></li>
                    </ul>
                </div>
            </div>
            <div class="col-md-3 gallery-grid gallery-grid2 wow flipInY animated" data-wow-delay=".9s">
                <a href="products.jsp"><img src="${contextPath}/resources/assetsMainPages/images/g3.jpg" class="img-responsive" alt=""/></a>
                <div class="gallery-text simpleCart_shelfItem">
                    <h5><a class="name" href="single.jsp">Doctor Play Set</a></h5>
                    <p><span class="item_price">30$</span></p>
                    <h4 class="sizes">Sizes: <a href="#"> s</a> - <a href="#">m</a> - <a href="#">l</a> - <a
                            href="#">xl</a></h4>
                    <ul>
                        <li><a href="#"><span class="glyphicon glyphicon-globe" aria-hidden="true"></span></a></li>
                        <li><a class="item_add" href="#"><span class="glyphicon glyphicon glyphicon-shopping-cart"
                                                               aria-hidden="true"></span></a></li>
                        <li><a href="#"><span class="glyphicon glyphicon glyphicon-heart-empty"
                                              aria-hidden="true"></span></a></li>
                    </ul>
                </div>
            </div>
            <div class="col-md-3 gallery-grid wow flipInY animated" data-wow-delay="1.1s">
                <a href="products.jsp"><img src="${contextPath}/resources/assetsMainPages/images/g4.jpg" class="img-responsive" alt=""/></a>
                <div class="gallery-text simpleCart_shelfItem">
                    <h5><a class="name" href="single.jsp">Cap & Gloves Set</a></h5>
                    <p><span class="item_price">15$</span></p>
                    <h4 class="sizes">Sizes: <a href="#"> s</a> - <a href="#">m</a> - <a href="#">l</a> - <a
                            href="#">xl</a></h4>
                    <ul>
                        <li><a href="#"><span class="glyphicon glyphicon-globe" aria-hidden="true"></span></a></li>
                        <li><a class="item_add" href="#"><span class="glyphicon glyphicon glyphicon-shopping-cart"
                                                               aria-hidden="true"></span></a></li>
                        <li><a href="#"><span class="glyphicon glyphicon glyphicon-heart-empty"
                                              aria-hidden="true"></span></a></li>
                    </ul>
                </div>
            </div>
            <div class="col-md-3 gallery-grid wow flipInY animated" data-wow-delay=".5s">
                <a href="products.jsp"><img src="${contextPath}/resources/assetsMainPages/images/g5.jpg" class="img-responsive" alt=""/></a>
                <div class="gallery-text simpleCart_shelfItem">
                    <h5><a class="name" href="single.jsp">Full Sleeves Romper</a></h5>
                    <p><span class="item_price">60$</span></p>
                    <h4 class="sizes">Sizes: <a href="#"> s</a> - <a href="#">m</a> - <a href="#">l</a> - <a
                            href="#">xl</a></h4>
                    <ul>
                        <li><a href="#"><span class="glyphicon glyphicon-globe" aria-hidden="true"></span></a></li>
                        <li><a class="item_add" href="#"><span class="glyphicon glyphicon glyphicon-shopping-cart"
                                                               aria-hidden="true"></span></a></li>
                        <li><a href="#"><span class="glyphicon glyphicon glyphicon-heart-empty"
                                              aria-hidden="true"></span></a></li>
                    </ul>
                </div>
            </div>
            <div class="col-md-3 gallery-grid gallery-grid1 wow flipInY animated" data-wow-delay=".7s">
                <a href="products.jsp"><img src="${contextPath}/resources/assetsMainPages/images/g6.jpg" class="img-responsive" alt=""/></a>
                <div class="gallery-text simpleCart_shelfItem">
                    <h5><a class="name" href="single.jsp">Party Wear Frock</a></h5>
                    <p><span class="item_price">80$</span></p>
                    <h4 class="sizes">Sizes: <a href="#"> s</a> - <a href="#">m</a> - <a href="#">l</a> - <a
                            href="#">xl</a></h4>
                    <ul>
                        <li><a href="#"><span class="glyphicon glyphicon-globe" aria-hidden="true"></span></a></li>
                        <li><a class="item_add" href="#"><span class="glyphicon glyphicon glyphicon-shopping-cart"
                                                               aria-hidden="true"></span></a></li>
                        <li><a href="#"><span class="glyphicon glyphicon glyphicon-heart-empty"
                                              aria-hidden="true"></span></a></li>
                    </ul>
                </div>
            </div>
            <div class="col-md-3 gallery-grid gallery-grid2 wow flipInY animated" data-wow-delay=".9s">
                <a href="products.jsp"><img src="${contextPath}/resources/assetsMainPages/images/g7.jpg" class="img-responsive" alt=""/></a>
                <div class="gallery-text simpleCart_shelfItem">
                    <h5><a class="name" href="single.jsp">Bear Diaper Bag</a></h5>
                    <p><span class="item_price">110$</span></p>
                    <h4 class="sizes">Sizes: <a href="#"> s</a> - <a href="#">m</a> - <a href="#">l</a> - <a
                            href="#">xl</a></h4>
                    <ul>
                        <li><a href="#"><span class="glyphicon glyphicon-globe" aria-hidden="true"></span></a></li>
                        <li><a class="item_add" href="#"><span class="glyphicon glyphicon glyphicon-shopping-cart"
                                                               aria-hidden="true"></span></a></li>
                        <li><a href="#"><span class="glyphicon glyphicon glyphicon-heart-empty"
                                              aria-hidden="true"></span></a></li>
                    </ul>
                </div>
            </div>
            <div class="col-md-3 gallery-grid wow flipInY animated" data-wow-delay="1.1s">
                <a href="products.jsp"><img src="${contextPath}/resources/assetsMainPages/images/g8.jpg" class="img-responsive" alt=""/></a>
                <div class="gallery-text simpleCart_shelfItem">
                    <h5><a class="name" href="single.jsp">Battery Police Bike</a></h5>
                    <p><span class="item_price">300$</span></p>
                    <h4 class="sizes">Sizes: <a href="#"> s</a> - <a href="#">m</a> - <a href="#">l</a> - <a
                            href="#">xl</a></h4>
                    <ul>
                        <li><a href="#"><span class="glyphicon glyphicon-globe" aria-hidden="true"></span></a></li>
                        <li><a class="item_add" href="#"><span class="glyphicon glyphicon glyphicon-shopping-cart"
                                                               aria-hidden="true"></span></a></li>
                        <li><a href="#"><span class="glyphicon glyphicon glyphicon-heart-empty"
                                              aria-hidden="true"></span></a></li>
                    </ul>
                </div>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
</div>
<!--//gallery-->
<!--trend-->
<div class="trend wow zoomIn animated" data-wow-delay=".5s">
    <div class="container">
        <div class="trend-info">
            <section class="slider grid">
                <div class="flexslider trend-slider">
                    <ul class="slides">
                        <li>
                            <div class="col-md-5 trend-left">
                                <img src="${contextPath}/resources/assetsMainPages/images/t1.png" alt=""/>
                            </div>
                            <div class="col-md-7 trend-right">
                                <h4>TOP 10 TRENDS <span>FOR YOU</span></h4>
                                <h5>Flat 20% OFF</h5>
                                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce tempus, justo ac
                                    volutpat vestibulum, dolor massa pharetra nunc, nec facilisis lectus nulla a tortor.
                                    Duis ultrices nunc a nisi malesuada suscipit. Class aptent taciti sociosqu ad litora
                                    torquent per conubia nostra, per inceptos himenaeos. Aliquam eu bibendum felis. Sed
                                    viverra dapibus tincidunt.</p>
                            </div>
                            <div class="clearfix"></div>
                        </li>
                        <li>
                            <div class="col-md-5 trend-left">
                                <img src="${contextPath}/resources/assetsMainPages/images/t2.png" alt=""/>
                            </div>
                            <div class="col-md-7 trend-right">
                                <h4>TOP 10 TRENDS <span>FOR YOU</span></h4>
                                <h5>Flat 20% OFF</h5>
                                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce tempus, justo ac
                                    volutpat vestibulum, dolor massa pharetra nunc, nec facilisis lectus nulla a tortor.
                                    Duis ultrices nunc a nisi malesuada suscipit. Class aptent taciti sociosqu ad litora
                                    torquent per conubia nostra, per inceptos himenaeos. Aliquam eu bibendum felis. Sed
                                    viverra dapibus tincidunt.</p>
                            </div>
                            <div class="clearfix"></div>
                        </li>
                        <li>
                            <div class="col-md-5 trend-left">
                                <img src="${contextPath}/resources/assetsMainPages/images/t3.png" alt=""/>
                            </div>
                            <div class="col-md-7 trend-right">
                                <h4>TOP 10 TRENDS <span>FOR YOU</span></h4>
                                <h5>Flat 20% OFF</h5>
                                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce tempus, justo ac
                                    volutpat vestibulum, dolor massa pharetra nunc, nec facilisis lectus nulla a tortor.
                                    Duis ultrices nunc a nisi malesuada suscipit. Class aptent taciti sociosqu ad litora
                                    torquent per conubia nostra, per inceptos himenaeos. Aliquam eu bibendum felis. Sed
                                    viverra dapibus tincidunt.</p>
                            </div>
                            <div class="clearfix"></div>
                        </li>
                        <li>
                            <div class="col-md-5 trend-left">
                                <img src="${contextPath}/resources/assetsMainPages/images/t4.png" alt=""/>
                            </div>
                            <div class="col-md-7 trend-right">
                                <h4>TOP 10 TRENDS <span>FOR YOU</span></h4>
                                <h5>Flat 20% OFF</h5>
                                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce tempus, justo ac
                                    volutpat vestibulum, dolor massa pharetra nunc, nec facilisis lectus nulla a tortor.
                                    Duis ultrices nunc a nisi malesuada suscipit. Class aptent taciti sociosqu ad litora
                                    torquent per conubia nostra, per inceptos himenaeos. Aliquam eu bibendum felis. Sed
                                    viverra dapibus tincidunt.</p>
                            </div>
                            <div class="clearfix"></div>
                        </li>
                    </ul>
                </div>
            </section>
        </div>
    </div>
</div>
<!--//trend-->
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