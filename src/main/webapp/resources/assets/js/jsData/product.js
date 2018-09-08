function getProductForm(productId, selector) {
    if (isNumber(productId)) {
        getProduct(productId, selector);
    } else {
        alert("Product ID is not number");
    }
}

function getProduct(id, selector) {
    var ajax = {};
    ajax.data = {id: id};
    ajax.type = "GET";
    ajax.url = "/jsDonut/admin/product/getProduct";
    ajax.dataType = 'JSON';
    ajax.selector = selector;
    ajax.successFunction = openProductForm;
    sendAjax(ajax);
}

function openProductForm(productObject) {

    $('.product-id').val(productObject.id);
    $('.product-name').val(productObject.name);
    $('.product-calories').val(productObject.calories);
    $('.product-price').val(productObject.price);
    $('.product-workPrice').val(productObject.workPrice);
    $('.product-description').val(productObject.description);
    $('.product-weight').val(productObject.weight);
    $('.product-quantity').val(productObject.quantity);
    $('.product__category-id').val(productObject.category.id);


    $('.container-head').text("Category: " + productObject.category.name + " Product: " + productObject.name);
    $('.product-list').addClass('block__display-none');
    $('.product-form').removeClass('block__display-none');
}

function closeProduct() {
    $('.container-head').text("Product list");
    $('.product-list').removeClass('block__display-none');
    $('.product-form').addClass('block__display-none');
}

function isNumber(value) {
    return (value !== undefined && value !== null && value > 0);
}

$(function() {
    $(document).on('click', '.product-edit', function() {
        // Почему не работает find()
        var id = $(this).parent().parent().data('id');
        getProductForm(id, $(this));
    });
    $(document).on('click', '.product-close', function() {
        closeProduct();
    });
    $(document).on('click', '.product-update', function() {
        closeProduct();
    });
});


function saveItem(button) {

    var pst = {};
    pst.selector = button;
    pst.type = "POST";
    pst.url = '/jsDonut/admin/product/updateProduct';
    pst.data = {};
    pst.data = getItemData();

    console.log(pst.data);

    sendAjax(pst);
}

function getItemData() {
    var product = {};

    product.id = parseInt($('.product-id').val());
    product.name = $('.product-name').val();
    product.calories = parseInt($('.product-calories').val());
    product.price = parseFloat($('.product-price').val());
    product.workPrice = parseFloat($('.product-workPrice').val());
    product.description = $('.product-description').val();
    product.weight = parseInt($('.product-weight').val());
    product.quantity = parseInt($('.product-quantity').val());
    product.category= {};
    product.category.id = parseInt($('.product__category-id').val());

    return product;
}


$(document).ready(function () {
    $(document).on('click', '.product-update', function (e) {
        e.preventDefault();
        saveItem($(this) );
    });
});