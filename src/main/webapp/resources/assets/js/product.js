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
    $('.product-name').val(productObject.name);
    $('.product-calories').val(productObject.calories);
    $('.product-price').val(productObject.price);
    $('.product-workPrice').val(productObject.workPrice);
    $('.product-description').val(productObject.description);
    $('.product-weight').val(productObject.weight);
    $('.product-quantity').val(productObject.quantity);


    $('.container-head').text("Product: " + productObject.name);
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
});