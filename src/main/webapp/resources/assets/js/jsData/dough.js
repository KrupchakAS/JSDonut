


// update --------------------

function getUpdateForm(doughId, selector) {
    if (isNumber(doughId)) {
        getDoughById(doughId, selector);
    } else {
        alert("Dough ID is not number");
    }
}
function isNumber(value) {
    return (value !== undefined && value !== null && value > 0);
}

function getDoughById(id, selector) {
    var ajax = {};
    ajax.data = {id: id};
    ajax.type = "GET";
    ajax.url = "/jsDonut/admin/dough/getDoughById";
    ajax.dataType = 'JSON';
    ajax.selector = selector;
    ajax.successFunction = openDoughFormUpdate;
    sendAjax(ajax);
}


function openDoughFormUpdate(doughObject) {

    $('.dough-id-up').val(doughObject.id);
    $('.dough-name-up').val(doughObject.name);
    $('.dough-calories-up').val(doughObject.calories);
    $('.dough-price-up').val(doughObject.price);

    $('.container-head').text("Dough: " + doughObject.name);
    $('.dough-list').addClass('block__display-none');
    $('.dough-add').addClass('block__display-none');
    $('.dough-form-create').addClass('block__display-none');
    $('.dough-form-update').removeClass('block__display-none');
}

function updateItem(button) {

    var pst = {};
    pst.selector = button;
    pst.type = "POST";
    pst.url = '/jsDonut/admin/dough/updateDough';
    pst.data = {};
    pst.data = getItemData();

    console.log(pst.data);

    sendAjax(pst);
}

function getItemData() {
    var dough = {};

    dough.id = parseInt($('.dough-id-up').val());
    dough.name = $('.dough-name-up').val();
    dough.price = parseFloat($('.dough-price-up').val());
    dough.calories = parseInt($('.dough-calories-up').val());

    return dough;
}


$(document).ready(function () {
    $(document).on('click', '.dough-update', function (e) {
        e.preventDefault();
        updateItem($(this));
    });
});

// save -------------------------------

function saveItem(button) {

    var pst = {};
    pst.selector = button;
    pst.type = "POST";
    pst.url = '/jsDonut/admin/dough/createDough';
    pst.data = {};
    pst.data = getFormCreate();

    console.log(pst.data);

    sendAjax(pst);
}

function getFormCreate() {
    var dough = {};

    dough.name = $('.dough-name-cr').val();
    dough.price = parseFloat($('.dough-price-cr').val());
    dough.calories = parseInt($('.dough-calories-cr').val());

    $('.dough-add').addClass('block__display-none');
    $('.dough-list').addClass('block__display-none');
    $('.dough-form-update').addClass('block__display-none');
    $('.dough-form-create').removeClass('block__display-none');

    return dough;
}


$(document).ready(function () {
    $(document).on('click', '.dough-save', function (e) {
        e.preventDefault();
        saveItem($(this));
    });
});

// delete -----------------------------

// Scripts

$(function() {
    $(document).on('click', '.dough-add', function () {
        getFormCreate();
    });
    $(document).on('click', '.dough-edit', function() {
        var id = $(this).closest('tr').data('id');
        getUpdateForm(id, $(this));
    });
    $(document).on('click', '.dough-close', function() {
        closeDough();
    });
    $(document).on('click', '.dough-update', function() {
        closeDough();
    });
    $(document).on('click', '.dough-save', function () {
        closeDough();
    });
});

function closeDough() {

    $('.container-head').text("Dough list");
    $('.dough-add').removeClass('block__display-none');
    $('.dough-list').removeClass('block__display-none');
    $('.dough-form-update').addClass('block__display-none');
    $('.dough-form-create').addClass('block__display-none');
}