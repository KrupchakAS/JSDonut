// update --------------------------------------

function getUpdateForm(fillingId, selector) {
    if (isNumber(fillingId)) {
        getFillingById(fillingId, selector);
    } else {
        alert("Filling ID is not number");
    }
}

function isNumber(value) {
    return (value !== undefined && value !== null && value > 0);
}

function getFillingById(id, selector) {
    var ajax = {};
    ajax.data = {id: id};
    ajax.type = "GET";
    ajax.url = "/jsDonut/admin/filling/getFillingById";
    ajax.dataType = 'JSON';
    ajax.selector = selector;
    ajax.successFunction = openFillingFormUpdate;
    sendAjax(ajax);
}

function openFillingFormUpdate(fillingObject) {

    $('.filling-id-up').val(fillingObject.id);
    $('.filling-name-up').val(fillingObject.name);
    $('.filling-calories-up').val(fillingObject.calories);
    $('.filling-price-up').val(fillingObject.price);


    // $("#fil-id").text($('.filling-id-up').val());
    // $("#fil-name").text($('.filling-name-up').val());
    // $("#fil-price").text(parseFloat($('.filling-price-up').val()));
    // $("#fil-calories").text($('.filling-calories-up').val());


    $('.container-head').text("Filling: " + fillingObject.name);
    $('.filling-add').addClass('block__display-none');
    $('.filling-list').addClass('block__display-none');
    $('.filling-form-create').addClass('block__display-none');
    $('.filling-form-update').removeClass('block__display-none');
}

function updateItem(button) {

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

    filling.id = parseInt($('.filling-id-up').val());
    filling.name = $('.filling-name-up').val();
    filling.price = parseFloat($('.filling-price-up').val());
    filling.calories = parseInt($('.filling-calories-up').val());


    return filling;
}


$(document).ready(function () {
    $(document).on('click', '.filling-update', function (e) {
        e.preventDefault();
        updateItem($(this));
        swal('Updated!');
    });
});

// save -----------------------------

function saveItem(button) {

    var pst = {};
    pst.selector = button;
    pst.type = "POST";
    pst.url = '/jsDonut/admin/filling/createFilling';
    pst.data = {};
    pst.data = getDataFromForm();
    pst.successFunction = addNewFilling;

    console.log(pst.data);

    sendAjax(pst);
}

function getDataFromForm() {
    var filling = {};

    filling.name = $('.filling-name-cr').val();
    filling.price = parseFloat($('.filling-price-cr').val());
    filling.calories = parseInt($('.filling-calories-cr').val());
    return filling;
}

function getFormCreate() {

    $('.filling-name-cr').val('');
    $('.filling-price-cr').val('');
    $('.filling-calories-cr').val('');

    $('.filling-add').addClass('block__display-none');
    $('.filling-list').addClass('block__display-none');
    $('.filling-form-update').addClass('block__display-none');
    $('.filling-form-create').removeClass('block__display-none');

}

function addNewFilling(fillingObject) {
    swal('SAVED!');
    console.log(fillingObject);

    $('#filling-table').find('tbody').append(
        '<tr  class="filling-table__row" data-id='+fillingObject.id+'>' +
        '<th>' + fillingObject.id + '</th>' +
        '<th>' + fillingObject.name + '</th>' +
        '<th>' + fillingObject.calories + '</th>' +
        '<th>' + fillingObject.price + '</th>' +
        '<th>' + '<button type="button" class="btn btn-md btn-primary filling-update">' + 'Edit' + '</button>' + '</th>' +
        '<th>' + '<button type="button" class="btn btn-md btn-danger filling-delete">' + 'Delete' + '</button>' + '</th>' +
        '</tr>');
    closeFilling();
}

$(document).ready(function () {
    $(document).on('click', '.filling-save', function (e) {
        e.preventDefault();
        saveItem($(this));
        swal('SAVED!');

    });
});

// delete ---------------------------


function deleteFilling(id, button) {

    if (intValueTest(id, 'Не удалось получить id')) return false;

    var pst = {};
    pst.data = id;

    pst.selector = button;
    pst.dataType = 'JSON';
    pst.type = "DELETE";
    pst.url = '/jsDonut/admin/filling/deleteFilling';
    pst.successFunction = function (result) {
        pst.selector.closest('tr').remove();
    };

    sendAjax(pst);
}

$(document).ready(function () {
    $(document).on('click', '.filling-delete', function (e) {
        e.preventDefault();
        var id = $(this).closest('tr').data('id');
        deleteFilling(id, $(this));
        swal('Deleted!');
    });
});

// Scripts

$(function () {
    $(document).on('click', '.filling-add', function () {
        getFormCreate();
    });
    $(document).on('click', '.filling-edit', function () {
        var id = $(this).closest('tr').data('id');
        getUpdateForm(id, $(this));
    });
    $(document).on('click', '.filling-close', function () {
        closeFilling();
    });
    $(document).on('click', '.filling-update', function () {
        closeFilling();
    });
    $(document).on('click', '.filling-delete', function () {
        closeFilling();
    });
});

function closeFilling() {

    $('.container-head').text("Filling list");
    $('.filling-list').removeClass('block__display-none');
    $('.filling-add').removeClass('block__display-none');
    $('.filling-form-update').addClass('block__display-none');
    $('.filling-form-create').addClass('block__display-none');
}

function intValueTest(value, text) {
    if (value === 0 || value === undefined) {
        swal('Ошибка', text, 'error');
        return true
    }
    return false;
}