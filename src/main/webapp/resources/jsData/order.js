// update --------------------

function getUpdateForm(orderId, selector) {
    if (isNumber(orderId)) {
        getOrderById(orderId, selector);
    } else {
        swal("Order ID is not number");
    }
}
function isNumber(value) {
    return (value !== undefined && value !== null && value > 0);
}

function getOrderById(id, selector) {
    var ajax = {};
    ajax.data = {id: id};
    ajax.type = "GET";
    ajax.url = "/jsDonut/admin/order/getOrderById";
    ajax.dataType = 'JSON';
    ajax.selector = selector;
    ajax.successFunction = openOrderFormUpdate;
    sendAjax(ajax);
}


function openOrderFormUpdate(orderObject) {

    $('.order-id').val(orderObject.id);


    $('.container-head').text("Category: " + orderObject.id);
    $('.order-list').addClass('block__display-none');
    $('.order-add').addClass('block__display-none');
    $('.order-save').addClass('block__display-none');
    $('.order-form').removeClass('block__display-none');
    $('.order-update').removeClass('block__display-none');

}

function updateItem(button) {

    var pst = {};
    pst.selector = button;
    pst.type = "POST";
    pst.url = '/jsDonut/admin/order/updateOrder';
    pst.data = {};
    pst.data = getItemData();

    console.log(pst.data);

    sendAjax(pst);
}

function getItemData() {
    var order = {};

    order.id = parseInt($('.category-id').val());

    return order;
}


$(document).ready(function () {
    $(document).on('click', '.order-update', function (e) {
        e.preventDefault();
        updateItem($(this));
        swal('Updated!');
    });
});

// delete -----------------------------

// Scripts

$(function() {
    $(document).on('click', '.order-edit', function() {
        var id = $(this).closest('tr').data('id');
        getUpdateForm(id, $(this));
    });
    $(document).on('click', '.order-close', function() {
        closeOrder();
    });
    $(document).on('click', '.order-update', function() {
        closeOrder();
    });

});

function closeOrder() {

    $('.container-head').text("Order list");
    $('.order-list').removeClass('block__display-none');
    $('.order-form').addClass('block__display-none');

}
