<%@ page pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="с" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<c:import url="/WEB-INF/views/admin/adminHeader.jsp"/>
<link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/css/select2.min.css" rel="stylesheet" />
<body>
<nav class="navbar navbar-custom navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#sidebar-collapse"><span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span></button>
            <a class="navbar-brand" href="${contextPath}/admin/adminPanel"><span></span>Admin Panel</a>
            <a class="navbar-brand" href="${contextPath}/welcome"><span></span>Home</a>
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
        <li class="${statisticActive}"><a href="${contextPath}/admin/statistic"> Statistic</a></li>
        <li class="${orderActive}"><a href="${contextPath}/admin/order"> Orders</a></li>
        <li class="${categoryActive}"><a href="${contextPath}/admin/category"> Categories</a></li>
        <li class="${productActive}"><a href="${contextPath}/admin/product"> Products</a></li>
        <li class="${sprinkleActive}"><a href="${contextPath}/admin/sprinkle"> Sprinkles</a></li>
        <li class="${doughActive}"><a href="${contextPath}/admin/dough">Doughs</a></li>
        <li class="${fillingActive}"><a href="${contextPath}/admin/filling">Fillings</a></li>
    </ul>
</div>

<div id="main" class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading container-head">
                    Order list
                </div>
                <div class="panel-body container-body">
                    <div class="row">
                        <div class="col-md-12 order-list">
                            <table id="category-table" class="table table-striped order-table">
                                <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Product List</th>
                                    <th>User Name</th>
                                    <th>User Phone</th>
                                    <th>Payment Option</th>
                                    <th>Delivery Option</th>
                                    <th>Payment Status</th>
                                    <th>Order Status</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:choose>
                                    <c:when test="${orderList.size() > 0}">
                                        <с:forEach var="order" items="${orderList}">
                                            <tr class="order-table__row" data-id="${order.id}">
                                                <td>${order.id}</td>
                                                <td>
                                                    <с:forEach var="product" items="${order.productList}">
                                                        ${product.category.name}
                                                        ${product.name}
                                                        (Qty)${product.quantity}
                                                        <br>
                                                    </с:forEach>
                                                </td>
                                                <td>${order.getUserDTO().firstName}</td>
                                                <td>${order.getUserDTO().phoneNumber}</td>
                                                <td id="PayOpt-${order.id}">${order.getPaymentOption()}</td>
                                                <td id="DelOpt-${order.id}">${order.getDeliveryOption()}</td>
                                                <td id="PayStat-${order.id}">${order.getPaymentStatus()}</td>
                                                <td id="OrdStat-${order.id}">${order.getOrderStatus()}</td>
                                                <td>
                                                    <button type="button" class="btn btn-md btn-primary order-edit">
                                                        Edit
                                                    </button>
                                                </td>
                                            </tr>
                                        </с:forEach>
                                    </c:when>
                                    <c:otherwise>
                                        <tr>
                                            <th colspan="4">Not orders</th>
                                        </tr>
                                    </c:otherwise>
                                </c:choose>
                                </tbody>
                            </table>
                        </div>

                        <div class="col-md-12 order-form block__display-none">
                            <form method="post" role="form">
                                <div class="form-group">
                                    <input required type="hidden" disabled class="form-control order-id"
                                           placeholder="Id">
                                </div>
                                <div class="row">
                                    <div class="col-sm-3">
                                        <label for="PaymentOption-update">Payment Option</label>
                                        <select id="PaymentOption-update"
                                                class="form-control ">
                                            <option value="0">Choose Payment Option</option>
                                            <option value="1">CASH</option>
                                            <option value="2">CARD</option>
                                        </select>
                                        <br>
                                    </div>

                                    <div class="col-sm-3">
                                        <label for="DeliveryOption-update">Delivery Option</label>
                                        <select id="DeliveryOption-update"
                                                class="form-control ">
                                            <option value="0">Choose Delivery Option</option>
                                            <option value="1">PICKUP</option>
                                            <option value="2">DELIVERY</option>
                                        </select>
                                        <br>
                                    </div>

                                    <div class="col-sm-3">
                                        <label for="PaymentStatus-update">Payment Status</label>
                                        <select id="PaymentStatus-update"
                                                class="form-control ">
                                            <option value="0">Choose Payment Status</option>
                                            <option value="1">NOT_PAID</option>
                                            <option value="2">PAID</option>
                                        </select>
                                        <br>
                                    </div>

                                    <div class="col-sm-3">
                                        <label for="OrderStatus-update">Order Status</label>
                                        <select id="OrderStatus-update"
                                                class="form-control ">
                                            <option value="0">Choose Order Status</option>
                                            <option value="1">AWAITING_PAYMENT</option>
                                            <option value="2">AWAITING_SHIPMENT</option>
                                            <option value="3">SHIPPED</option>
                                            <option value="4">DELIVERED</option>
                                        </select>
                                        <br>
                                    </div>
                                </div>
                            </form>

                            <div class="row">
                                <div class="col-md-6">
                                    <button type="button" class="btn btn-lg btn-success btn-block order-update">Update
                                    </button>
                                </div>
                                <div class="col-md-6">
                                    <button type="button" class="btn btn-lg btn-danger btn-block order-close">Cancel
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

<c:import url="/WEB-INF/views/admin/adminFooter.jsp"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js"></script>
<script type="text/javascript" src="${contextPath}/resources/jsData/order.js"></script>


</body>
</html>