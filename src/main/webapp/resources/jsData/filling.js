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

    $('.filling-id').val(fillingObject.id);
    $('.filling-name').val(fillingObject.name);
    $('.filling-calories').val(fillingObject.calories);
    $('.filling-price').val(fillingObject.price);

    $('.container-head').text("Filling: " + fillingObject.name);
    $('.filling-add').addClass('block__display-none');
    $('.filling-list').addClass('block__display-none');
    $('.filling-save').addClass('block__display-none');
    $('.filling-form').removeClass('block__display-none');
    $('.filling-update').removeClass('block__display-none');


}

function updateItem(button) {

    var pst = {};
    pst.selector = button;
    pst.type = "POST";
    pst.url = '/jsDonut/admin/filling/updateFilling';
    pst.data = {};
    pst.data = getItemData();
    pst.successFunction = function (filling) {
        $('#FilName-' + filling.id).html(filling.name);
        $('#FilCal-' + filling.id).html(filling.calories);
        $('#FilPr-' + filling.id).html(filling.price);
    };

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

    filling.name = $('.filling-name').val();
    filling.price = parseFloat($('.filling-price').val());
    filling.calories = parseInt($('.filling-calories').val());
    return filling;
}

function getFormCreate() {

    $('.filling-name-cr').val('');
    $('.filling-price-cr').val('');
    $('.filling-calories-cr').val('');

    $('.filling-add').addClass('block__display-none');
    $('.filling-list').addClass('block__display-none');
    $('.filling-form').removeClass('block__display-none');
    $('.filling-update').addClass('block__display-none');

}

function addNewFilling(fillingObject) {
    swal('SAVED!');
    console.log(fillingObject);

    $('#filling-table').find('tbody').append(
        '<tr  class="filling-table__row" data-id=' + fillingObject.id + '>' +
        '<td>' + fillingObject.id + '</td>' +
        '<td>' + fillingObject.name + '</td>' +
        '<td>' + fillingObject.calories + '</td>' +
        '<td>' + fillingObject.price + '</td>' +
        '<td>' + '<button type="button" class="btn btn-md btn-primary filling-edit">' + 'Edit' + '</button>' + '</td>' +
        '<td>' + '<button type="button" class="btn btn-md btn-danger filling-delete">' + 'Delete' + '</button>' + '</td>' +
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
    $('.filling-form').addClass('block__display-none');



}

function intValueTest(value, text) {
    if (value === 0 || value === undefined) {
        swal('Ошибка', text, 'error');
        return true
    }
    return false;
}