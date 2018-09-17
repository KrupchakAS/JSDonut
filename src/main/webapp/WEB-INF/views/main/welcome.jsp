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
                    <p>We will glad to see you again, ${pageContext.request.userPrincipal.name} <a href="/jsDonut/logout">Sing Out </a></p>
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
                    <h4><a href="${contextPath}/jsDonut/checkout">
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
                        <li><a href="${contextPath}/jsDonut/welcome" class="active">Home</a></li>
                        <li class="dropdown grid">
                            <a href="#" class="dropdown-toggle list1" data-toggle="dropdown">Categories<b
                                    class="caret"></b></a>
                            <ul class="dropdown-menu multi-column multi-column2">
                                <div class="row">
                                    <div class="col-sm-3 menu-grids">
                                        <h4>New Arrivals</h4>
                                        <ul class="multi-column-dropdown">
                                            <%--<li><a class="list" href="${contextPath}/jsDonut/products">Diapering</a></li>--%>
                                            <c:choose>
                                                <c:when test="${categoryList.size() > 0}">
                                                    <с:forEach var="category" items="${categoryList}">
                                                        <li data-id="${category.id}" value="${category.id}">${category.name}</li>
                                                    </с:forEach>
                                                </c:when>
                                                <c:otherwise>
                                                    <option disabled selected>Category not found</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </ul>
                                    </div>
                                    <div class="col-sm-3 menu-grids new-add2 ">
                                        <a href="products.jsp">
                                            <h6>Yuuumi (:</h6>
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
                <h2>Our Special Delicious</h2>
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
                                <h4>-15%</h4>
                                <img src="${contextPath}/resources/assetsMainPages/images/b1.jpg" alt="">
                            </li>
                            <li>
                                <h4>-20%</h4>
                                <img src="${contextPath}/resources/assetsMainPages/images/b2.jpg" alt="">
                            </li>
                            <li>
                                <h4>-20%</h4>
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
<!--//trend-->
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
                    <li><a href="about.jsp">About Us</a></li>
                    <li><a href="products.jsp">new</a></li>
                    <li><a href="contact.jsp">Contact Us</a></li>
                    <li><a href="faq.jsp">FAQ</a></li>
                    <li><a href="checkout.jsp">Wishlist</a></li>
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