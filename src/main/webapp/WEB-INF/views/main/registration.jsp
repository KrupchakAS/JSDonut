<%@ page pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
                    <p>Welcome to Donut<a href="${contextPath}/jsDonut/registration">Sign Up </a> Or <a
                            href="${contextPath}/jsDonut/login">Sign In </a></p>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <p> Hi Admin, let's work<a href="/jsDonut/admin/adminPanel">Admin Panel</a> We will glad to see you
                        again <a href="/jsDonut/logout">Sing Out </a></p>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_USER')">
                    <p>We will glad to see you again ${pageContext.request.userPrincipal.name} <a
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
</div>
<!--//breadcrumbs-->
<!--login-->
<div class="login-page">
    <div class="widget-shadow">
        <div class="login-top wow fadeInUp animated" data-wow-delay=".7s">
            <h4>Already have an Account ?<a href="${contextPath}/jsDonut/login"> Sign In »</a></h4>
        </div>

        <div class="login-body">
            <form:form method="POST" modelAttribute="userForm">
                <div>
                    <span>Enter your login:</span>
                    <form:input type="text" minlength="4" maxlength="16" path="login" class=" form-control"
                                placeholder="Login"></form:input>
                    <div class="has-error">
                        <form:errors path="login"></form:errors></div>
                </div>
                <div>
                    <span>Enter your firstName:</span>
                    <form:input type="text" minlength="4" maxlength="16" path="firstName" class="form-control"
                                placeholder="FirstName"></form:input>
                    <div class="has-error">
                        <form:errors path="firstName"></form:errors></div>
                </div>
                <div>
                    <span>Enter your surName:</span>
                    <form:input type="text" minlength="4" maxlength="16" path="surName" class="form-control"
                                placeholder="SurName"></form:input>
                    <div class="has-error">
                        <form:errors path="surName"></form:errors></div>
                </div>
                <div>
                    <span>Enter your phoneNumber:</span>
                    <form:input type="text" minlength="4" maxlength="16" path="phoneNumber" class="form-control"
                                placeholder="PhoneNumber"></form:input>
                    <div class="has-error">
                        <form:errors path="phoneNumber"></form:errors></div>
                </div>
                <div>
                    <span>Enter your birthday:</span>
                    <form:input type="date" value="2000-01-01" min="1900-01-01" max="2004-01-01" path="birthDate"
                                class="form-control"
                                placeholder="BirthDate"></form:input>
                    <div class="has-error">
                        <form:errors path="birthDate"></form:errors></div>
                </div>
                <div>
                    <span>Enter your email:</span>
                    <form:input type="text" minlength="4" maxlength="50" path="email" class="form-control"
                                placeholder="Email"></form:input>
                    <div class="has-error">
                        <form:errors path="email"></form:errors></div>
                </div>
                <div>
                    <span>Enter your birthday:</span>
                    <form:input type="password" minlength="4" maxlength="16" path="password" class="form-control"
                                placeholder="Password"></form:input>
                    <div class="has-error">
                        <form:errors path="password"></form:errors></div>
                </div>
                <div>
                    <span>Enter your confirmPassword:</span>
                    <form:input type="password" minlength="4" maxlength="16" path="confirmPassword" class="form-control"
                                placeholder="Confirm your password"></form:input>
                    <div class="has-error">
                        <form:errors path="confirmPassword"></form:errors>
                    </div>
                </div>
                <input type="submit" name="Sign up" value="Sign up">
            </form:form>
        </div>
    </div>
</div>
<!--//login-->
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