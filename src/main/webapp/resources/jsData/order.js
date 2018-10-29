// UpdateStatusesInOrder --------------------------

function updateOrder(button) {

    var pst = {};
    pst.selector = button;
    pst.type = "POST";
    pst.url = '/jsDonut/admin/order/updateOrder';
    pst.data = {};
    pst.data = getItemOrder();
    pst.successFunction = function (order) {
        $('#PayOpt-'+order.id).text(order.paymentOption);
        $('#DelOpt-'+order.id).text(order.deliveryOption);
        $('#PayStat-'+order.id).text(order.paymentStatus);
        $('#OrdStat-'+order.id).text(order.orderStatus);
        swal('Updated!');
    };
    console.log(pst.data);

    sendAjax(pst);
}

function getItemOrder() {
    var order = {};

    order.id = parseInt($('.order-id').val());
    order.paymentOption = $('#PaymentOption-update option:selected').text();
    order.deliveryOption = $('#DeliveryOption-update option:selected').text();
    order.paymentStatus = $('#PaymentStatus-update option:selected').text();
    order.orderStatus = $('#OrderStatus-update option:selected').text();

    return order;
}


$(document).ready(function () {
    $(document).on('click', '.order-update', function (e) {
        e.preventDefault();
        updateOrder($(this));

    });
});

// get data to form for update product

function getUpdateForm(orderId, selector) {
    if (isNumber(orderId)) {
        getOrderById(orderId, selector);
    } else {
        swal("Order ID is not number");
    }
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

// insert into form

function openOrderFormUpdate(orderObject) {

    $('.container-head').text("Order: " + orderObject.id);
    $('.order-list').addClass('block__display-none');
    $('.order-form').removeClass('block__display-none');
    $('.order-update').removeClass('block__display-none');

    $('.order-id').val(orderObject.id);

    $('#PaymentOption-update option').filter(function () {
        return $(this).text() == orderObject.paymentOption.toString();
    }).prop('selected',true);
     $('#DeliveryOption-update option').filter(function () {
         return $(this).text() == orderObject.deliveryOption.toString();
     }).prop('selected',true);
     $('#PaymentStatus-update option').filter(function () {
         return $(this).text() == orderObject.paymentStatus.toString();
     }).prop('selected',true);
     $('#OrderStatus-update option').filter(function () {
         return $(this).text() == orderObject.orderStatus.toString();
     }).prop('selected',true);

}


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

function isNumber(value) {
    return (value !== undefined && value !== null && value > 0);
}
