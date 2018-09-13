// update --------------------------------------

function getUpdateForm(sprinkleId, selector) {
    if (isNumber(sprinkleId)) {
        getSprinkleById(sprinkleId, selector);
    } else {
        alert("Sprinkle ID is not number");
    }
}

function isNumber(value) {
    return (value !== undefined && value !== null && value > 0);
}

function getSprinkleById(id, selector) {
    var ajax = {};
    ajax.data = {id: id};
    ajax.type = "GET";
    ajax.url = "/jsDonut/admin/sprinkle/getSprinkleById";
    ajax.dataType = 'JSON';
    ajax.selector = selector;
    ajax.successFunction = openSprinkleFormUpdate;
    sendAjax(ajax);
}

function openSprinkleFormUpdate(sprinkleObject) {

    $('.sprinkle-id-up').val(sprinkleObject.id);
    $('.sprinkle-name-up').val(sprinkleObject.name);
    $('.sprinkle-calories-up').val(sprinkleObject.calories);
    $('.sprinkle-price-up').val(sprinkleObject.price);

    $('.container-head').text("Sprinkle: " + sprinkleObject.name);
    $('.sprinkle-add').addClass('block__display-none');
    $('.sprinkle-list').addClass('block__display-none');
    $('.sprinkle-form-create').addClass('block__display-none');
    $('.sprinkle-form-update').removeClass('block__display-none');
}

function updateItem(button) {

    var pst = {};
    pst.selector = button;
    pst.type = "POST";
    pst.url = '/jsDonut/admin/sprinkle/updateSprinkle';
    pst.data = {};
    pst.data = getItemData();

    console.log(pst.data);

    sendAjax(pst);
}

function getItemData() {

    var sprinkle = {};

    sprinkle.id = parseInt($('.sprinkle-id-up').val());
    sprinkle.name = $('.sprinkle-name-up').val();
    sprinkle.price = parseFloat($('.sprinkle-price-up').val());
    sprinkle.calories = parseInt($('.sprinkle-calories-up').val());

    return sprinkle;
}


$(document).ready(function () {
    $(document).on('click', '.sprinkle-update', function (e) {
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
    pst.url = '/jsDonut/admin/sprinkle/createSprinkle';
    pst.data = {};
    pst.data = getDataFromForm();
    pst.successFunction = addNewSprinkle;

    console.log(pst.data);

    sendAjax(pst);
}

function getDataFromForm() {
    var sprinkle = {};

    sprinkle.name = $('.sprinkle-name-cr').val();
    sprinkle.price = parseFloat($('.sprinkle-price-cr').val());
    sprinkle.calories = parseInt($('.sprinkle-calories-cr').val());
    return sprinkle;
}

function getFormCreate() {

    $('.sprinkle-name-cr').val('');
    $('.sprinkle-price-cr').val('');
    $('.sprinkle-calories-cr').val('');

    $('.sprinkle-add').addClass('block__display-none');
    $('.sprinkle-list').addClass('block__display-none');
    $('.sprinkle-form-update').addClass('block__display-none');
    $('.sprinkle-form-create').removeClass('block__display-none');
}

function addNewSprinkle(sprinkleObject) {
    swal('SAVED!');
    console.log(sprinkleObject);

    $('#sprinkle-table').find('tbody').append(
        '<tr  class="sprinkle-table__row" data-id=' + sprinkleObject.id + '>' +
        '<th>' + sprinkleObject.id + '</th>' +
        '<th>' + sprinkleObject.name + '</th>' +
        '<th>' + sprinkleObject.calories + '</th>' +
        '<th>' + sprinkleObject.price + '</th>' +
        '<th>' + '<button type="button" class="btn btn-md btn-primary sprinkle-update">' + 'Edit' + '</button>' + '</th>' +
        '<th>' + '<button type="button" class="btn btn-md btn-danger sprinkle-delete">' + 'Delete' + '</button>' + '</th>' +
        '</tr>');

    closeSprinkle();
}


$(document).ready(function () {
    $(document).on('click', '.sprinkle-save', function (e) {
        e.preventDefault();
        saveItem($(this));
    });
});

// delete -------------------------------


function deleteSprinkle(id, button) {

    if (intValueTest(id, 'Не удалось получить id')) return false;

    var pst = {};
    pst.data = id;

    pst.selector = button;
    pst.dataType = 'JSON';
    pst.type = "DELETE";
    pst.url = '/jsDonut/admin/sprinkle/deleteSprinkle';
    pst.successFunction = function (result) {
        pst.selector.closest('tr').remove();
    };

    sendAjax(pst);
}

$(document).ready(function () {
    $(document).on('click', '.sprinkle-delete', function (e) {
        e.preventDefault();
        var id = $(this).closest('tr').data('id');
        deleteSprinkle(id, $(this));
        swal('Deleted!');
    });
});


// Scripts

$(function () {
    $(document).on('click', '.sprinkle-add', function () {
        getFormCreate();
    });
    $(document).on('click', '.sprinkle-edit', function () {
        var id = $(this).closest('tr').data('id');
        getUpdateForm(id, $(this));
    });
    $(document).on('click', '.sprinkle-close', function () {
        closeSprinkle();
    });
    $(document).on('click', '.sprinkle-update', function () {
        closeSprinkle();
    });

    $(document).on('click', '.sprinkle-delete', function () {
        closeSprinkle();
    });
});

function closeSprinkle() {

    $('.container-head').text("Sprinkle list");
    $('.sprinkle-list').removeClass('block__display-none');
    $('.sprinkle-add').removeClass('block__display-none');
    $('.sprinkle-form-update').addClass('block__display-none');
    $('.sprinkle-form-create').addClass('block__display-none');
}

function intValueTest(value, text) {
    if (value === 0 || value === undefined) {
        swal('Ошибка', text, 'error');
        return true
    }
    return false;
}