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

    product.id = parseInt($('.product-id').val());
    product.name = $('.product-name').val();
    product.calories = parseInt($('.product-calories').val());
    product.price = parseFloat($('.product-price').val());
    product.workPrice = parseFloat($('.product-workPrice').val());
    product.description = $('.product-description').val();
    product.weight = parseInt($('.product-weight').val());
    product.quantity = parseInt($('.product-quantity').val());
    product.category = {};
    product.category.id = parseInt($('.product__category-id').val());
    product.filling = {};
    product.filling.id = parseInt($('.product__filling-id').val());
    product.dough = {};
    product.dough.id = parseInt($('.product__dough-id').val());
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
    $('.product__filling-id').val(productObject.filling.id);
    $('.product__dough-id').val(productObject.dough.id);



    $('.container-head').text("Category: " + productObject.category.name + " Product: " + productObject.name);
    $('.product-list').addClass('block__display-none');
    $('.product-add').addClass('block__display-none');
    $('.product-save').addClass('block__display-none');
    $('.product-update').removeClass('block__display-none');
    $('.product-form').removeClass('block__display-none');

    setSelect2Plugin();
}



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
        fullPrice += getPrice(fullWeight, parseFloat($(this).data('price')));
    });
    $('#price').val(fullPrice);
}

function getPrice(fullWeight, price) {
    return fullWeight/100 * price;
}

function getDataFromForm() {
    var product = {};

    product.name = $('.product-name').val();
    product.calories = parseInt($('.product-calories').val());
    product.price = parseFloat($('.product-price').val());
    product.workPrice = parseFloat($('.product-workPrice').val());
    product.description = $('.product-description').val();
    product.weight = parseInt($('.product-weight').val());
    product.quantity = parseInt($('.product-quantity').val());
    product.category = {};
    product.category.id = parseInt($('.product__category-id').val());
    product.category.name = $('.product__category-id option:selected').text();
    product.filling = {};
    product.filling.id = parseInt($('.product__filling-id').val());
    product.dough = {};
    product.dough.id = parseInt($('.product__dough-id').val());
    product.List = [];
    product.sprinkleList = [];
    $.each($('.selectpicker.form-control.product__sprinkle-id').val(), function (index, value) {
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
    $('.product__sprinkle-id').val('Choose sprinkle');

    $('.product-add').addClass('block__display-none');
    $('.product-list').addClass('block__display-none');
    $('.product-update').addClass('block__display-none');
    $('.product-save').removeClass('block__display-none');
    $('.product-form').removeClass('block__display-none');

}

function addNewProduct(productObject) {
    swal('SAVED!');
    console.log(productObject);

    $('#product-table').find('tbody').append(
        '<tr  class="product-table__row" data-id=' + productObject.id + '>' +
        '<th>' + productObject.id + '</th>' +
        '<th>' + productObject.category.name + '</th>' +
        '<th>' + productObject.name + '</th>' +
        '<th>' + productObject.price + '</th>' +
        '<th>' + productObject.weight + '</th>' +
        '<th>' + productObject.quantity + '</th>' +
        '<th>' + '<button type="button" class="btn btn-md btn-primary product-update">' + 'Edit' + '</button>' + '</th>' +
        '<th>' + '<button type="button" class="btn btn-md btn-danger product-delete">' + 'Delete' + '</button>' + '</th>' +
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

// GetProductsByParameters

function getProducts(categoryName,productName,minPrice,maxPrice, selector) {
    if (isNumber(minPrice) && isNumber(maxPrice)) {
        getProductByParameters(categoryName,productName,minPrice,maxPrice, selector);
    } else {
        swal("Product price is not number");
    }
}


function getProductByParameters(categoryName, productName, minPrice, maxPrice, selector) {
    var ajax = {};
    ajax.data = {categoryName: categoryName};
    ajax.data = {productName: productName};
    ajax.data = {minPrice: minPrice};
    ajax.data = {maxPrice: maxPrice};
    ajax.type = "GET";
    ajax.url = "/jsDonut/admin/product/getProductsByParameters";
    ajax.dataType = 'JSON';
    ajax.selector = selector;
    ajax.successFunction = openProductFormUpdate;

    sendAjax(ajax);
}


// Scripts

$(function () {
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
    $(document).on('click', '.product-delete', function () {
        closeProduct();
    });
    $(document).on('change', '.component', function() {
        recountPrice();
    });
    $(document).on('change', '#sprinkles', function() {
        recountPrice();
    });
    $(document).on('keyup', '.priceChanger', function() {
        recountPrice();
    });
    $(document).on('click', '.products-search', function () {
        var categoryName = $('.categoryName-Search').val();
        var productName = $('.productName-Search').val();
        var minPrice = $('.minPrice-Search').val();
        var maxPrice = $('.maxPrice-Search').val();
        getProducts(categoryName, productName, minPrice, maxPrice, $(this))
    });
});


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