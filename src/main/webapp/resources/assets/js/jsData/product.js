

// update -----------------------------------------------------------------

function updateItem(button) {

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

    product.id = parseInt($('.product-id-up').val());
    product.name = $('.product-name-up').val();
    product.calories = parseInt($('.product-calories-up').val());
    product.price = parseFloat($('.product-price-up').val());
    product.workPrice = parseFloat($('.product-workPrice-up').val());
    product.description = $('.product-description-up').val();
    product.weight = parseInt($('.product-weight-up').val());
    product.quantity = parseInt($('.product-quantity-up').val());
    product.category = {};
    product.category.id = parseInt($('.product__category-id-up').val());
    product.filling = {};
    product.filling.id = parseInt($('.product__filling-id-up').val());
    product.dough = {};
    product.dough.id = parseInt($('.product__dough-id-up').val());

    return product;
}


$(document).ready(function () {
    $(document).on('click', '.product-update', function (e) {
        e.preventDefault();
        updateItem($(this));
        swal('Updated!');
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

function openProductFormUpdate(productObject) {

    $('.product-id-up').val(productObject.id);
    $('.product-name-up').val(productObject.name);
    $('.product-calories-up').val(productObject.calories);
    $('.product-price-up').val(productObject.price);
    $('.product-workPrice-up').val(productObject.workPrice);
    $('.product-description-up').val(productObject.description);
    $('.product-weight-up').val(productObject.weight);
    $('.product-quantity-up').val(productObject.quantity);
    $('.product__category-id-up').val(productObject.category.id);
    $('.product__filling-id-up').val(productObject.filling.id);
    $('.product__dough-id-up').val(productObject.dough.id);

    $('.container-head').text("Category: " + productObject.category.name + " Product: " + productObject.name);
    $('.product-list').addClass('block__display-none');
    $('.product-add').addClass('block__display-none');
    $('.product-form-create').addClass('block__display-none');
    $('.product-form-update').removeClass('block__display-none');

}


// save -----------------------------------------------------------------

function saveItem(button) {

    var pst = {};
    pst.selector = button;
    pst.type = "POST";
    pst.url = '/jsDonut/admin/product/createProduct';
    pst.data = {};
    pst.data = getFormCreate();

    console.log(pst.data);

    sendAjax(pst);
}

function getFormCreate() {
    var product = {};

    product.name = $('.product-name-cr').val();
    product.calories = parseInt($('.product-calories-cr').val());
    product.price = parseFloat($('.product-price-cr').val());
    product.workPrice = parseFloat($('.product-workPrice-cr').val());
    product.description = $('.product-description-cr').val();
    product.weight = parseInt($('.product-weight-cr').val());
    product.quantity = parseInt($('.product-quantity-cr').val());
    product.category = {};
    product.category.id = parseInt($('.product__category-id-cr').val());
    product.filling = {};
    product.filling.id = parseInt($('.product__filling-id-cr').val());
    product.dough = {};
    product.dough.id = parseInt($('.product__dough-id-cr').val());

    $('.product-add').addClass('block__display-none');
    $('.product-list').addClass('block__display-none');
    $('.product-form-update').addClass('block__display-none');
    $('.product-form-create').removeClass('block__display-none');

    return product;
}


$(document).ready(function () {
    $(document).on('click', '.product-save', function (e) {
        e.preventDefault();
        saveItem($(this));
        swal('SAVED!');
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
    };

    sendAjax(pst);
}

$(document).ready(function () {
    $(document).on('click', '.product-delete', function (e) {
        e.preventDefault();
        var id = $(this).closest('tr').data('id');
                deleteProduct(id, $(this));
        swal('Deleted!');
    });
});


// Scripts

$(function() {
    $(document).on('click', '.product-add', function () {
        getFormCreate();
    });
    $(document).on('click', '.product-edit', function () {
        var id = $(this).closest('tr').data('id');
        getUpdateForm(id, $(this));
    });
    $(document).on('click', '.product-close', function () {
        closeProduct();
    });
    $(document).on('click', '.product-update', function () {
        closeProduct();
    });
    $(document).on('click', '.product-save', function () {
        closeProduct();
    });
    $(document).on('click', '.product-delete', function () {
        closeDough();
    });
});


function closeProduct() {

    $('.container-head').text("Product list");
    $('.product-list').removeClass('block__display-none');
    $('.product-add').removeClass('block__display-none');
    $('.product-form-update').addClass('block__display-none');
    $('.product-form-create').addClass('block__display-none');
}

function intValueTest(value, text) {
    if (value === 0 || value === undefined) {
        swal('Ошибка', text, 'error');
        return true
    }
    return false;
}