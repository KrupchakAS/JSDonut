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
        <li class="${fillingActive}"><a href="${contextPath}/admin/filling"> Fillings</a></li>

    </ul>
</div>

<div id="main" class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading container-head">
                    Product list
                </div>
                <div class="panel-body container-body">
                    <button type="button" class="btn btn-md btn-success product-add">
                        Add Product
                    </button>
                    <div class="row">
                        <div class="col-md-12 product-list">
                            <table id="product-table" class="table table-striped product-table">
                                <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Category</th>
                                    <th>Name</th>
                                    <th>Price</th>
                                    <th>Weight</th>
                                    <th>Quantity</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:choose>
                                    <c:when test="${productList.size() > 0}">
                                        <с:forEach var="product" items="${productList}">
                                            <tr class="product-table__row" data-id="${product.id}">
                                                <td>${product.id}</td>
                                                <td id="ProdCategory-${product.id}">${product.category.name}</td>
                                                <td id="ProdName-${product.id}">${product.name}</td>
                                                <td id="ProdPrice-${product.id}">${product.price}</td>
                                                <td id="ProdWeight-${product.id}">${product.weight}</td>
                                                <td id="ProdQuantity-${product.id}">${product.quantity}</td>
                                                <td>
                                                    <button type="button" class="btn btn-md btn-primary product-edit">
                                                        Edit
                                                    </button>
                                                </td>
                                                <td>
                                                    <button type="button" class="btn btn-md btn-danger product-delete">
                                                        Delete
                                                    </button>
                                                </td>
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
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-group block__display-none">
                                            <input id="id" type="hidden" disabled class="form-control  product-id" placeholder="Id">
                                        </div>
                                        <div class="form-group">
                                            <label for="name">Name</label>
                                            <input id="name" minlength="3" maxlength="16" class="form-control product-name" placeholder="Name">
                                        </div>
                                        <div class="form-group">
                                            <label for="description" >Description</label>
                                            <textarea id="description" minlength="1" maxlength="255" required class="form-control product-description"
                                                      placeholder="Description"></textarea>
                                        </div>

                                        <div class="form-group">
                                            <label for="weight">Weight</label>
                                            <input id="weight" minlength="1" maxlength="5" type="number" class="form-control product-weight priceChanger"
                                                   placeholder="Weight">
                                        </div>
                                        <div class="form-group">
                                            <label for="workPrice">Price of the work</label>
                                            <input id="workPrice" minlength="1" maxlength="5" type="number" class="form-control product-workPrice priceChanger"
                                                   placeholder="WorkPrice">
                                        </div>

                                        <div class="form-group">
                                            <label for="price">Full price</label>
                                            <input id="price" disabled minlength="1" maxlength="5" type="number" class="form-control product-price"
                                                   placeholder="Price">
                                        </div>
                                    </div>

                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label for="category">Category</label>
                                            <select id="category" required class="selec2-plugin form-control product__category-id">
                                                <option disabled value="0" selected>Choose category</option>
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
                                        </div>
                                        <div class="form-group">
                                            <label for="dough">Dough</label>
                                            <select id="dough" required class="selec2-plugin form-control product__dough-id component">
                                                <option  value="0" selected data-price="0" data-calories="0">Choose dough</option>
                                                <c:choose>
                                                    <c:when test="${doughList.size() > 0}">
                                                        <с:forEach var="dough" items="${doughList}">
                                                            <option data-id="${dough.id}" value="${dough.id}" data-price="${dough.price}" data-calories="${dough.calories}">${dough.name}</option>
                                                        </с:forEach>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option disabled selected>Dough not found</option>
                                                    </c:otherwise>
                                                </c:choose>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label for="filling">Filling</label>
                                            <select id="filling" required class="form-control selec2-plugin product__filling-id component">
                                                <option value="0" selected data-price="0" data-calories="0">Choose filling</option>
                                                <c:choose>
                                                    <c:when test="${fillingList.size() > 0}">
                                                        <с:forEach var="filling" items="${fillingList}">
                                                            <option data-id="${filling.id}" value="${filling.id}" data-price="${filling.price}" data-calories="${filling.calories}">${filling.name}</option>
                                                        </с:forEach>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option disabled selected>Filling not found</option>
                                                    </c:otherwise>
                                                </c:choose>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label for="sprinkles">Sprinkle</label>
                                            <select id="sprinkles" class="form-control selec2-plugin product__sprinkle-id" name="sprinkles[]" multiple="multiple">
                                                <option disabled value="0" data-price="0" data-calories="0">Choose sprinkle</option>
                                                <c:choose>
                                                    <c:when test="${sprinkleList.size() > 0}">
                                                        <с:forEach var="sprinkle" items="${sprinkleList}">
                                                            <option data-id="${sprinkle.id}" value="${sprinkle.id}" data-price="${sprinkle.price}" data-calories="${sprinkle.calories}">${sprinkle.name}</option>
                                                        </с:forEach>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option disabled selected>Sprinkles not found</option>
                                                    </c:otherwise>
                                                </c:choose>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label for="quantity">Quantity</label>
                                            <input id="quantity" minlength="1" maxlength="5" type="number" class="form-control product-quantity"
                                                   placeholder="Quantity">
                                        </div>
                                        <div class="form-group">
                                            <label for="calories">Calories</label>
                                            <input id="calories" minlength="1" maxlength="5" type="number" class="form-control product-calories"
                                                   placeholder="Calories">
                                        </div>
                                    </div>
                                </div>
                            </form>
                            <div class="row">
                                <div class="col-md-6">
                                    <button type="button" class="btn btn-lg btn-success btn-block product-save">Save
                                    </button>
                                    <button type="button" class="btn btn-lg btn-success btn-block product-update">Update
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
    </div>
</div>


<c:import url="/WEB-INF/views/admin/adminFooter.jsp"/>

<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js"></script>
<script type="text/javascript" src="${contextPath}/resources/jsData/product.js"></script>
</body>
</html>