function getFillingForm(fillingId, selector) {
    if (isNumber(fillingId)) {
        getFilling(fillingId, selector);
    } else {
        alert("Filling ID is not number");
    }
}

function getFilling(id, selector) {
    var ajax = {};
    ajax.data = {id: id};
    ajax.type = "GET";
    ajax.url = "/jsDonut/admin/filling/getFilling";
    ajax.dataType = 'JSON';
    ajax.selector = selector;
    ajax.successFunction = openFillingForm;
    sendAjax(ajax);
}

function openFillingForm(fillingObject) {

    $('.filling-id').val(fillingObject.id);
    $('.filling-name').val(fillingObject.name);
    $('.filling-calories').val(fillingObject.calories);
    $('.filling-price').val(fillingObject.price);

    $('.container-head').text("Filling: " + fillingObject.name);
    $('.filling-list').addClass('block__display-none');
    $('.filling-form').removeClass('block__display-none');
}

function closeFilling() {
    $('.container-head').text("Filling list");
    $('.filling-list').removeClass('block__display-none');
    $('.filling-form').addClass('block__display-none');
}

function isNumber(value) {
    return (value !== undefined && value !== null && value > 0);
}

$(function() {
    $(document).on('click', '.filling-edit', function() {
        // Почему не работает find()
        var id = $(this).parent().parent().data('id');
        getFillingForm(id, $(this));
    });
    $(document).on('click', '.filling-close', function() {
        closeFilling();
    });
    $(document).on('click', '.filling-update', function() {
        closeFilling();
    });
});

function saveItem(button) {

    var pst = {};
    pst.selector = button;
    pst.type = "POST";
    pst.url = '/jsDonut/admin/filling/updateFilling';
    pst.data = {};
    pst.data = getItemData();

    console.log(pst.data);

    sendAjax(pst);
}

function getItemData() {
    var filling = {};

    filling.id = parseInt($('.filling-id').val());
    filling.name = $('.filling-name').val();
    filling.price = parseFloat($('.filling-price').val());
    filling.calories = parseInt($('.filling-calories').val());

    return filling;
}


$(document).ready(function () {
    $(document).on('click', '.filling-update', function (e) {
        e.preventDefault();
        saveItem($(this) );
    });
});