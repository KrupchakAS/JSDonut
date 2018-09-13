<%@ page pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="с" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<c:import url="/WEB-INF/views/header.jsp"/>
<link href="${contextPath}/resources/assets/css/multiselect/bootstrap-select.css" rel="stylesheet">

<body>
<nav class="navbar navbar-custom navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#sidebar-collapse"><span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span></button>
            <a class="navbar-brand" href="/jsDonut/admin/adminPanel"><span></span>Admin Panel</a>
        </div>
    </div><!-- /.container-fluid -->
</nav>
<div id="sidebar-collapse" class="col-sm-3 col-lg-2 sidebar">
    <div class="profile-sidebar">
        <div class="profile-userpic">
            <img src="http://placehold.it/50/30a5ff/fff" class="img-responsive" alt="">
        </div>
        <div class="profile-usertitle">
            <div class="profile-usertitle-name">ADMIN</div>
            <div class="profile-usertitle-status"><span class="indicator label-success"></span>Online</div>
        </div>
        <div class="clear"></div>
    </div>
    <div class="divider"></div>
    <form role="search">
        <div class="form-group">
            <input type="text" class="form-control" placeholder="Search">
        </div>
    </form>
    <ul class="nav menu">
        <li class="${productActive}"><a href="${contextPath}/jsDonut/admin/product"> Products</a></li>
        <li class="${sprinkleActive}"><a href="${contextPath}/jsDonut/admin/sprinkle"> Sprinkles</a></li>
        <li class="${doughActive}"><a href="${contextPath}/jsDonut/admin/dough">Doughs</a></li>
        <li class="${fillingActive}"><a href="${contextPath}/jsDonut/admin/filling"> Fillings</a></li>

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
                                                <th>${product.id}</th>
                                                <th>${product.category.name}</th>
                                                <th>${product.name}</th>
                                                <th>${product.price}</th>
                                                <th>${product.weight}</th>
                                                <th>${product.quantity}</th>
                                                <th>
                                                    <button type="button" class="btn btn-md btn-primary product-edit">
                                                        Edit
                                                    </button>
                                                </th>
                                                <th>
                                                    <button type="button" class="btn btn-md btn-danger product-delete">
                                                        Delete
                                                    </button>
                                                </th>
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

                        <div class="col-md-12 product-form-create block__display-none">
                            <form role="form">
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label>Name</label>
                                            <input required class="form-control product-name-cr" placeholder="Name">
                                        </div>
                                        <div class="form-group">
                                            <label>Price</label>
                                            <input required type="number" class="form-control product-price-cr" placeholder="Price">
                                        </div>
                                        <div class="form-group">
                                            <label>WorkPrice</label>
                                            <input required type="number" class="form-control product-workPrice-cr" placeholder="WorkPrice">
                                        </div>
                                        <div class="form-group">
                                            <label>Weight</label>
                                            <input required type="number" class="form-control product-weight-cr" placeholder="Weight">
                                        </div>
                                        <div class="form-group">
                                            <label>Quantity</label>
                                            <input required type="number" class="form-control product-quantity-cr" placeholder="Quantity">
                                        </div>
                                        <div class="form-group">
                                            <label>Calories</label>
                                            <input required type="number" class="form-control product-calories-cr" placeholder="Calories">
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label>Description</label>
                                            <textarea required class="form-control product-description-cr"
                                                      placeholder="Description"></textarea>
                                        </div>
                                        <div class="form-group">
                                            <label>Category</label>
                                            <div class="input-group">
                                                <select required class=" form-control product__category-id-cr">
                                                    <option disabled selected>Choose category</option>
                                                    <option value="1">Донатсы</option>
                                                    <option value="2">Макарони</option>
                                                    <option value="3">Пироженные</option>
                                                    <option value="4">Торты</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label>Dough</label>
                                            <div class="input-group">
                                                <select required class=" form-control product__dough-id-cr">
                                                    <option disabled selected>Choose dough</option>
                                                    <option value="1">Бисквитное</option>
                                                    <option value="2">Песочное</option>
                                                    <option value="3">Слоеное</option>
                                                    <option value="4">Заварное</option>
                                                    <option value="5">Миндальное</option>
                                                    <option value="6">Классическое(дрожжевое)</option>
                                                    <option value="7">Классическое(бездрожжевое)</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label>Filling</label>
                                            <div class="input-group">
                                                <select required class="form-control product__filling-id-cr">
                                                    <option disabled selected>Choose filling</option>
                                                    <option value="1">Шоколад(темный)</option>
                                                    <option value="2">Шоколад(молочный)</option>
                                                    <option value="3">Банан</option>
                                                    <option value="4">Клубника</option>
                                                    <option value="5">Вишня</option>
                                                    <option value="6">Киви</option>
                                                    <option value="7">Смородина</option>
                                                    <option value="8">Заварной крем</option>
                                                    <option value="9">Творог</option>
                                                    <option value="10">Ваниль</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                            </form>
                            <div class="row">
                                <div class="col-md-6">
                                    <button type="button" class="btn btn-lg btn-success btn-block product-save">Save
                                    </button>
                                </div>
                                <div class="col-md-6">
                                    <button type="button" class="btn btn-lg btn-danger btn-block product-close">Cancel
                                    </button>
                                </div>
                            </div>
                        </div>


                        <div class="col-md-12 product-form-update block__display-none">
                            <form role="form">
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label>Id</label>
                                            <input disabled class="form-control  product-id-up" placeholder="Id">
                                        </div>
                                        <div class="form-group">
                                            <label>Name</label>
                                            <input required class="form-control product-name-up" placeholder="Name">
                                        </div>
                                        <div class="form-group">
                                            <label>Price</label>
                                            <input required type="number" class="form-control product-price-up" placeholder="Price">
                                        </div>
                                        <div class="form-group">
                                            <label>WorkPrice</label>
                                            <input required type="number" class="form-control product-workPrice-up" placeholder="WorkPrice">
                                        </div>
                                        <div class="form-group">
                                            <label>Weight</label>
                                            <input required type="number" class="form-control product-weight-up" placeholder="Weight">
                                        </div>
                                        <div class="form-group">
                                            <label>Quantity</label>
                                            <input required type="number" class="form-control product-quantity-up" placeholder="Quantity">
                                        </div>
                                        <div class="form-group">
                                            <label>Calories</label>
                                            <input required type="number" class="form-control product-calories-up" placeholder="Calories">
                                        </div>
                                    </div>

                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label>Description</label>
                                            <textarea required class="form-control product-description-up"
                                                      placeholder="Description"></textarea>
                                        </div>
                                        <div class="form-group">
                                            <label>Category</label>
                                            <div class="input-group">
                                                <select required class="form-control product__category-id-up">
                                                    <option disabled selected>Choose category</option>
                                                    <option value="1">Донатсы</option>
                                                    <option value="2">Макарони</option>
                                                    <option value="3">Пироженные</option>
                                                    <option value="4">Торты</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label>Dough</label>
                                            <div class="input-group">
                                                <select required class=" form-control product__dough-id-up">
                                                    <option disabled selected>Choose dough</option>
                                                    <option value="1">Бисквитное</option>
                                                    <option value="2">Песочное</option>
                                                    <option value="3">Слоеное</option>
                                                    <option value="4">Заварное</option>
                                                    <option value="5">Миндальное</option>
                                                    <option value="6">Классическое(дрожжевое)</option>
                                                    <option value="7">Классическое(бездрожжевое)</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label>Filling</label>
                                            <div class="input-group">
                                                <select required class="form-control product__filling-id-up">
                                                    <option disabled selected>Choose filling</option>
                                                    <option value="1">Шоколад(темный)</option>
                                                    <option value="2">Шоколад(молочный)</option>
                                                    <option value="3">Банан</option>
                                                    <option value="4">Клубника</option>
                                                    <option value="5">Вишня</option>
                                                    <option value="6">Киви</option>
                                                    <option value="7">Смородина</option>
                                                    <option value="8">Заварной крем</option>
                                                    <option value="9">Творог</option>
                                                    <option value="10">Ваниль</option>
                                                </select>
                                            </div>
                                        </div>

                                        <select class="selectpicker" multiple>
                                            <option value="1">Mustard</option>
                                            <option value="2">Ketchup</option>
                                            <option value="3">Relish</option>
                                        </select>

                                    </div>
                                </div>
                            </form>
                            <div class="row">
                                <div class="col-md-6">
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


<c:import url="/WEB-INF/views/footer.jsp"/>
<script type="text/javascript" src="${contextPath}/resources/assets/js/jsData/product.js"></script>
<script type="text/javascript" src="${contextPath}/resources/assets/js/multiselect/bootstrap-select.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $('.selectpicker').selectpicker();
    });
</script>
</body>
</html>