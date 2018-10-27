// update -----------------------------------------------------------------

function updateItem(button) {

    var pst = {};
    pst.selector = button;
    pst.type = "POST";
    pst.url = '/jsDonut/admin/product/updateProduct';
    pst.data = {};
    pst.data = getItemData();
    pst.successFunction = function (product) {
        $('#ProdCategory-' + product.id).html(product.category.name);
        $('#ProdName-' + product.id).html(product.name);
        $('#ProdPrice-' + product.id).html(product.price);
        $('#ProdWeight-' + product.id).html(product.weight);
        $('#ProdQuantity-' + product.id).html(product.quantity);
        closeProduct();
        swal('Updated!');
    };

    console.log(pst.data);

    sendAjax(pst);
}

function getItemData() {
    var product = {};

    product.id = parseInt($('.product-id').val());
    product.name = $('.product-name').val();
    product.calories = parseInt($('.product-calories').val());
    product.price = Number(parseFloat($('.product-price').val())).toFixed(2);
    product.workPrice = parseFloat($('.product-workPrice').val());
    product.description = $('.product-description').val();
    product.weight = parseInt($('.product-weight').val());
    product.quantity = parseInt($('.product-quantity').val());
    product.category = {};
    product.category.id = parseInt($('.product__category-id').val());
    product.category.name = $('.product__category-id option:selected').text();
    product.dough = {};
    product.dough.id = parseInt($('.product__dough-id').val());
    product.filling = {};
    product.filling.id = parseInt($('.product__filling-id').val());
    product.List = [];
    product.sprinkleList = [];
    $('.product__sprinkle-id :selected').each(function () {
        product.List.push(parseInt($(this).val()));
    });
    for (var i = 0; i < product.List.length; i++) {
        product.sprinkle = {};
        product.sprinkle.id = product.List[i];
        product.sprinkleList.push(product.sprinkle);
    }


    return product;
}


$(document).ready(function () {
    $(document).on('click', '.product-update', function (e) {
        e.preventDefault();
        updateItem($(this));
    });
});

// get data to form for update product

function getUpdateForm(productId, selector) {
    if (isNumber(productId)) {
        getProductById(productId, selector);
    } else {
        alert("Product ID is not number");
    }
}

function isNumber(value) {
    return (value !== undefined && value !== null && value > 0);
}

function getProductById(id, selector) {
    var ajax = {};
    ajax.data = {id: id};
    ajax.type = "GET";
    ajax.url = "/jsDonut/admin/product/getProductById";
    ajax.dataType = 'JSON';
    ajax.selector = selector;
    ajax.successFunction = openProductFormUpdate;
    sendAjax(ajax);
}

// insert into form

function setSelect2Plugin() {
    $('.selec2-plugin:not(.setect2-plugin__setted)').each(function () {
        $(this).addClass('setect2-plugin__setted');
        $(this).select2();
    });
}

function openProductFormUpdate(productObject) {

    $('.product-id').val(productObject.id);
    $('.product-name').val(productObject.name);
    $('.product-calories').val(productObject.calories);
    $('.product-price').val(productObject.price);
    $('.product-workPrice').val(productObject.workPrice);
    $('.product-description').val(productObject.description);
    $('.product-weight').val(productObject.weight);
    $('.product-quantity').val(productObject.quantity);
    $('.product__category-id').val(productObject.category.id);
    $('.product__dough-id').val(productObject.dough.id);
    if (productObject.filling !== null ) {
        $('.product__filling-id').val(productObject.filling.id);
    }
    if (productObject.filling === null ) {
         $('.product__filling-id').val(0);
    }
    if (productObject.sprinkleList.length > 0) {
        var spIds = [];
        for (var a = 0; a < productObject.sprinkleList.length; a++) {
            spIds.push(productObject.sprinkleList[a].id);
            $('.product__sprinkle-id').val(spIds);
        }
    }else {
        $('.product__sprinkle-id').val('');
    }

    $('.container-head').text("Category: " + productObject.category.name + " Product: " + productObject.name);
    $('.product-list').addClass('block__display-none');
    $('.product-add').addClass('block__display-none');
    $('.product-save').addClass('block__display-none');
    $('.product-update').removeClass('block__display-none');
    $('.product-form').removeClass('block__display-none');

    setSelect2Plugin();
}

$(document).ready(function () {
    $('.product-workPrice').prop("disabled",true);
    $('.product-weight').keyup(function () {
        if($(this).val().length != 0){
            $('.product-workPrice').prop("disabled",false);
        }else {
            $('.product-workPrice').prop("disabled",true);
        }
    })
});

// save -----------------------------------------------------------------

function saveItem(button) {

    var pst = {};
    pst.selector = button;
    pst.type = "POST";
    pst.url = '/jsDonut/admin/product/createProduct';
    pst.data = {};
    pst.data = getDataFromForm();
    pst.successFunction = addNewProduct;

    console.log(pst.data);

    sendAjax(pst);
}

function recountPrice() {
    var fullPrice = parseFloat($('#workPrice').val());
    var fullWeight = parseFloat($('#weight').val());
    $('.component :selected').each(function () {
        fullPrice += getPrice(fullWeight, parseFloat($(this).data('price')));
    });
    $('#sprinkles :selected').each(function () {
        fullPrice += getPrice(fullWeight, parseFloat($(this).data('price'))/2);
    });
    $('#price').val(fullPrice);
}

function getPrice(fullWeight, price) {
    return fullWeight / 100 * price;
}

function getDataFromForm() {
    var product = {};

    product.name = $('.product-name').val();
    product.calories = parseInt($('.product-calories').val());
    product.price = Number(parseFloat($('.product-price').val())).toFixed(2);
    product.workPrice = parseFloat($('.product-workPrice').val());
    product.description = $('.product-description').val();
    product.weight = parseInt($('.product-weight').val());
    product.quantity = parseInt($('.product-quantity').val());
    product.category = {};
    product.category.id = parseInt($('.product__category-id').val());
    product.category.name = $('.product__category-id option:selected').text();
    product.dough = {};
    product.dough.id = parseInt($('.product__dough-id').val());
    if ($('.product__filling-id').val() != '0') {
        product.filling = {};
        product.filling.id = parseInt($('.product__filling-id').val());
    }
    product.sprinkleList = [];
    $.each($('.product__sprinkle-id').val(), function (index, value) {
        product.sprinkleList.push({id: parseInt(value)});
    });

    return product;
}

function getFormCreate() {

    $('.product-name').val('');
    $('.product-price').val('');
    $('.product-calories').val('');
    $('.product-workPrice').val('');
    $('.product-description').val('');
    $('.product-weight').val('');
    $('.product-quantity').val('');
    $('.product__category-id').val('Choose category');
    $('.product__filling-id').val('Choose filling');
    $('.product__dough-id').val('Choose dough');
    $('.product__sprinkle-id').val('Choose sprinkles');

    $('.product-add').addClass('block__display-none');
    $('.product-list').addClass('block__display-none');
    $('.product-update').addClass('block__display-none');
    $('.product-save').removeClass('block__display-none');
    $('.product-form').removeClass('block__display-none');

}

function addNewProduct(productObject) {
    swal('Saved!');
    console.log(productObject);

    $('#product-table').find('tbody').append(
        '<tr  class="product-table__row" data-id=' + productObject.id + '>' +
        '<td>' + productObject.id + '</td>' +
        '<td>' + productObject.category.name + '</td>' +
        '<td>' + productObject.name + '</td>' +
        '<td>' + productObject.price + '</td>' +
        '<td>' + productObject.weight + '</td>' +
        '<td>' + productObject.quantity + '</td>' +
        '<td>' + '<button type="button" class="btn btn-md btn-primary product-edit">' + 'Edit' + '</button>' + '</td>' +
        '<td>' + '<button type="button" class="btn btn-md btn-danger product-delete">' + 'Delete' + '</button>' + '</td>' +
        '</tr>');

    closeProduct();

}

$(document).ready(function () {
    $(document).on('click', '.product-save', function (e) {
        e.preventDefault();
        saveItem($(this));
    });
});

// delete --------------------------------------------

function deleteProduct(id, button) {

    if (intValueTest(id, 'Не удалось получить id')) return false;

    var pst = {};
    pst.data = id;
    pst.selector = button;
    pst.dataType = 'JSON';
    pst.type = "DELETE";
    pst.url = '/jsDonut/admin/product/deleteProduct';
    pst.successFunction = function (result) {
        pst.selector.closest('tr').remove();
        swal('Deleted!');
        closeProduct();
    };

    sendAjax(pst);
}

$(document).ready(function () {
    $(document).on('click', '.product-delete', function (e) {
        e.preventDefault();
        var id = $(this).closest('tr').data('id');
        deleteProduct(id, $(this));
    });
});


// GetProductsByParameters -------------------------------------------

function getProductByParameters(categoryId, fillingId, doughId, sprinkleIdList, productName, minPrice, maxPrice, selector) {
    var ajax = {};
    ajax.data = {};
    var filter = {};

    if (categoryId !== null && categoryId.length > 0) {
        filter.categoryId = parseInt(categoryId);
    }
    if (fillingId !== null && fillingId.length > 0) {
        filter.fillingId = parseInt(fillingId);
    }
    if (doughId !== null && doughId.length > 0) {
        filter.doughId = parseInt(doughId);
    }
    if (sprinkleIdList !== null && sprinkleIdList.length > 0) {
        filter.sprinkleIdList = sprinkleIdList;
    }
    if (productName.length > 0) {
        filter.productName = productName;
    }
    if (minPrice.length > 0) {
        filter.minPrice = parseInt(minPrice);
    }
    if (maxPrice.length > 0) {
        filter.maxPrice = parseInt(maxPrice);
    }
    ajax.data = filter;
    console.log(ajax.data);

    ajax.type = "POST";
    ajax.url = "/jsDonut/getProductsByParameters";
    ajax.dataType = 'JSON';
    ajax.selector = selector;
    ajax.successFunction = addProducts;

    sendAjax(ajax);
}

function addProducts(productList) {

    console.log(productList);

    if (productList === null || productList === undefined) {
        swal('Sorry, but we do not have any delicious by your criteria');
    } else {
        for (var i = 0; i < productList.length; i++) {
            var productObject = productList[i];
            var fil ='';
            if(productObject.filling != null){
                fil = 'Filling: ' + productObject.filling.name;
            }
            var sprList = '';
            if(productObject.sprinkleList.length >0){
                sprList = 'Sprinkles: ';
                for (var k = 0; k < productObject.sprinkleList.length; k++) {
                    sprList += '<span>'+(productObject.sprinkleList[k].name)+'</span>'+' ';
                }
            }
            $('.Product-item').append(
                '<div class="single-info"> <div class="single-top-left simpleCart_shelfItem wow fadeInRight animated" data-wow-delay=".5s">' +
                '<h3 style="float: right" class="item_price">' + productObject.price + '₽</h3>' +
                '(<span>' + productObject.category.name + '</span>)' +
                '<h6 >' + productObject.name + '</h6>' +
                '<p>' + productObject.description + '</p>' +
                '<span style="padding-right: 10px">Dough: ' + productObject.dough.name + '</span>'+' '+'<span>' + fil + '</span><p>' + sprList + '</p>' +
                '<span style="color: #c0a16b">Calories: ' + productObject.calories + '</span>' +
                '<div style="float: right" class="quantity"><p style="padding-right: 10px">Available quantity: ' + productObject.quantity + '</p><p style="color: red" class="qty">Select Quantity: </p><input min="1" type="number" value="1" name="item_quantity" class="item_quantity">' +
                '<div style="float: inherit; margin-left: 15px" data-id="' + productObject.id + '" class="btn_form" ><a href="#" style="color: green"  class="add-cart item_add">Add To Cart</a></div>' +
                '</div> </div> </div><hr>');
        }
    }
}

// AddProductToOrder ----------------------------------------------

function addToCart(id, selector) {
    if (isNumber(id)) {
        getProductByIdToCart(id, selector);
    } else {
        alert("Product ID is not number");
    }
}


function getProductByIdToCart(id, quantity, selector) {
    var ajax = {};
    ajax.data = {id: id, quantity: quantity};
    ajax.type = "GET";
    ajax.url = "/jsDonut/addProductToOrder";
    ajax.dataType = 'JSON';
    ajax.selector = selector;
    ajax.successFunction = AddedToOrder;

    sendAjax(ajax);
}

function AddedToOrder(order) {
    console.log(order);
    swal('Added');
    $('.CountProduct').text(order.productList.length.toString());
    $('.OrderTotalPrice').text(order.totalPrice.toString()+'₽');
}

//ClearCart

function emptyCart(selector) {
    var ajax = {};
    ajax.type = "GET";
    ajax.url = "/jsDonut/emptyCart";
    ajax.dataType = 'JSON';
    ajax.selector = selector;
    ajax.successFunction = setQtyCartZero;

    sendAjax(ajax);
}

function setQtyCartZero(productDTOList) {
    console.log(productDTOList);
    $('.CountProduct').text(productDTOList.length.toString());
    $('.OrderTotalPrice').text('0.0₽')
    swal('Cart is Empty');
}

// DeleteProductFromCartById

function deleteProductByIdFromOrder(id, selector) {

    if (intValueTest(id, 'Не удалось получить id')) return false;
    var ajax = {};
    ajax.data = {id: id};
    ajax.type = "GET";
    ajax.url = "/jsDonut/deleteProductByIdFromOrder";
    ajax.dataType = 'JSON';
    ajax.selector = selector;
    ajax.successFunction = function (order) {
        ajax.selector.closest('.divForRemove').remove();
        $('.Total-price').text('TotalPrice(minimum:600₽): ' + order.totalPrice.toString() + '₽');
    };

    sendAjax(ajax);
}

$(document).ready(function () {
    $(document).on('click', '.DeleteProductFromCart', function (e) {
        e.preventDefault();
        var id = parseInt($(this).closest('div').data('id'));
        deleteProductByIdFromOrder(id, $(this));
        swal('Removed!');
    });
});

// SaveOrder

function SaveOrder(button) {

    var pst = {};
    pst.selector = button;
    pst.type = "POST";
    pst.url = '/jsDonut/saveOrder';
    pst.data = {};
    pst.data = getDataFormForOrder();
    pst.successFunction = function () {
        $('.cart-info').addClass('block__display-none');
        $('.ChocDonut').removeClass('block__display-none');
        swal('Your order is accepted. We will contact you soon. You can watch details in your Account.');
    };

    console.log(pst.data);

    sendAjax(pst);
}

function getDataFormForOrder() {
    var order = {};

    order.deliveryOption = $('#DeliveryOption option:selected').text();
    order.paymentOption = $('#PaymentOption option:selected').text();

    if ($('#DeliveryOption option:selected').val() == 2) {
        order.address = {};
        if ($('#DeliveryAddresses option:selected').val() != 0) {
            order.address.id = parseInt($('#DeliveryAddresses option:selected').val());
        } else {
            order.address.city = $('#City').val();
            order.address.street = $('#Street').val();
            order.address.houseNumber = $('#HouseNumber').val();
            order.address.apartmentNumber = $('#ApartmentNumber').val();
            order.address.postCode = $('#PostCode').val();
        }
    }

    return order;
}


// ChangeUserPassword -------------------------------------------

function ChangeUserPassword(button) {

    var pst = {};
    pst.selector = button;
    pst.type = "POST";
    pst.url = '/jsDonut/changeUserPassword';
    pst.data = {};
    pst.data = getPasswords();
    pst.successFunction = function () {
        swal('Password Successfully Changed');
    };

    console.log(pst.data);

    sendAjax(pst);
}

function getPasswords() {

    var passwords = {};

    passwords.password = $('.Password').val();
    passwords.confirmPassword = $('.ConfirmPassword').val();

    return passwords;
}

$(document).ready(function () {
    $(document).on('click', '.ChangePass', function (e) {
        e.preventDefault();
        ChangeUserPassword($(this));
        $('.PasswordDiv').addClass('block__display-none');
    });
});

//ChangeUserInfo ---------------------------

function ChangeUserInfo(button) {

    var pst = {};
    pst.selector = button;
    pst.type = "POST";
    pst.url = '/jsDonut/changeUserInfo';
    pst.data = {};
    pst.data = getInfo();
    pst.successFunction = function () {
        $('.InfoDiv').addClass('block__display-none');
        swal('Info Successfully Changed');
        $('.firstName').val('');
        $('.surName').val('');
        $('.phoneNumber').val('');
        $('.birthDate').val('');
    };

    console.log(pst.data);

    sendAjax(pst);
}

function getInfo() {

    var userinfo = {};

    if ($('.firstName').val() != undefined && $('.firstName').val().length > 1) {
        userinfo.firstName = $('.firstName').val();
    }
    if ($('.surName').val() != undefined && $('.surName').val().length > 1) {
        userinfo.surName = $('.surName').val();
    }
    if ($('.phoneNumber').val() != undefined && $('.phoneNumber').val().length == 10) {
        userinfo.phoneNumber = $('.phoneNumber').val();
    }
    if ($('.birthDate').val() != undefined) {
        userinfo.birthDate = $('.birthDate').val();
    }

    return userinfo;
}

$(document).ready(function () {
    $(document).on('click', '.ChangeInfo', function (e) {
        e.preventDefault();
        ChangeUserInfo($(this));
    });
});

// Scripts

$(document).ready(function () {
    $('.DefaultProducts').removeClass('block__display-none');
    $('.cart-info').removeClass('block__display-none');
});

$(function () {
    $(document).on('click', '.product-add', function () {
        getFormCreate();
        setSelect2Plugin();
    });
    $(document).on('click', '.product-edit', function () {
        var id = $(this).closest('tr').data('id');
        getUpdateForm(id, $(this));
    });
    $(document).on('click', '.product-close', function () {
        closeProduct();
    });
    $(document).on('click', '.product-delete', function () {
        closeProduct();
    });
    $(document).on('change', '.component', function () {
        recountPrice();
    });
    $(document).on('change', '#sprinkles', function () {
        recountPrice();
    });
    $(document).on('keyup', '.priceChanger', function () {
        recountPrice();
    });
    $(document).on('click', '.products-search', function () {
        $('.Product-item').empty();
        var categoryId = $('.categoryId-Search').val();
        var fillingId = $('.fillingId-Search').val();
        var doughId = $('.doughId-Search').val();
        var productName = $('.productName-Search').val();
        var minPrice = $('.minPrice-Search').val();
        var maxPrice = $('.maxPrice-Search').val();
        var sprinkleIdList = [];
        $.each($('.sprinkleId-Search').val(), function (index, value) {
            sprinkleIdList.push(parseInt(value));
        });
        getProductByParameters(categoryId, fillingId, doughId, sprinkleIdList, productName, minPrice, maxPrice, $(this));
        $('.DefaultProducts').addClass('block__display-none');
    });

    $(document).on('click', '.add-cart', function () {
        var quantity = parseInt($(this).closest('div.quantity').find("input[name='item_quantity']").val());
        var id = $(this).closest('div').data('id');
        addToCart(id, quantity, $(this));
    });
    $(document).on('click', '.ClearButton', function () {
        emptyCart($(this));
    });
    $(document).on('click', '.OrderSave', function () {
        SaveOrder($(this));

    });
    $(document).on('click', '.ChangeYourPassword', function () {
        $('.PasswordDiv').removeClass('block__display-none');
        $('.Orders').addClass('block__display-none');
        $('.InfoDiv').addClass('block__display-none');
    });
    $(document).on('click', '.MyOrders', function () {
        $('.Orders').removeClass('block__display-none');
        $('.PasswordDiv').addClass('block__display-none');
        $('.InfoDiv').addClass('block__display-none');
    });
    $(document).on('click', '.ChangeUserInfo', function () {
        $('.Orders').addClass('block__display-none');
        $('.PasswordDiv').addClass('block__display-none');
        $('.InfoDiv').removeClass('block__display-none');
    });


});


// OptionsInCart
$(document).ready(function () {
    $('.PickUpDiv').removeClass('block__display-none');
    $('#DeliveryOption').on('change', function () {
        var a = $('#DeliveryOption').val();
        if (a == '2') {
            $('.delivery-option').removeClass('block__display-none');
            $('.AddressesDiv').removeClass('block__display-none');
            $('.PickUpDiv').addClass('block__display-none');

        } else {
            $('.PickUpDiv').removeClass('block__display-none');
            $('.delivery-option').addClass('block__display-none');
            $('.AddressesDiv').addClass('block__display-none');
        }
    });
    $('.addressForm').removeClass('block__display-none');
    $('#DeliveryAddresses').on('change', function () {
        var a = $('#DeliveryAddresses').val();
        if (a == '0') {
            $('.addressForm').removeClass('block__display-none');
        } else {
            $('.addressForm').addClass('block__display-none');
        }
    });
});

$(document).ready(function () {
    $('#PaymentOption').on('change', function () {
        var a = $('#PaymentOption').val();
        if (a == '2') {
            $('.payment').removeClass('block__display-none');
        } else {
            $('.payment').addClass('block__display-none');
        }
    });
});

//---------------------------------
function closeProduct() {

    $('.container-head').text("Product list");
    $('.product-list').removeClass('block__display-none');
    $('.product-add').removeClass('block__display-none');
    $('.product-form').addClass('block__display-none');

}

function intValueTest(value, text) {
    if (value === 0 || value === undefined) {
        swal('Ошибка', text, 'error');
        return true
    }
    return false;
}









