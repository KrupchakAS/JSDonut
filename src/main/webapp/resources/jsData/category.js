// update --------------------

function getUpdateForm(categoryId, selector) {
    if (isNumber(categoryId)) {
        getDoughById(categoryId, selector);
    } else {
        swal("Category ID is not number");
    }
}
function isNumber(value) {
    return (value !== undefined && value !== null && value > 0);
}

function getDoughById(id, selector) {
    var ajax = {};
    ajax.data = {id: id};
    ajax.type = "GET";
    ajax.url = "/jsDonut/admin/category/getCategoryById";
    ajax.dataType = 'JSON';
    ajax.selector = selector;
    ajax.successFunction = openCategoryFormUpdate;
    sendAjax(ajax);
}


function openCategoryFormUpdate(categoryObject) {

    $('.category-id').val(categoryObject.id);
    $('.category-name').val(categoryObject.name);

    $('.container-head').text("Category: " + categoryObject.name);
    $('.category-list').addClass('block__display-none');
    $('.category-add').addClass('block__display-none');
    $('.category-save').addClass('block__display-none');
    $('.category-form').removeClass('block__display-none');
    $('.category-update').removeClass('block__display-none');

}

function updateItem(button) {

    var pst = {};
    pst.selector = button;
    pst.type = "POST";
    pst.url = '/jsDonut/admin/category/updateCategory';
    pst.data = {};
    pst.data = getItemData();
    pst.successFunction = function (category) {
        $('#CatName').html(category.name)
        closeCategory();
        swal('Updated!');
    };

    console.log(pst.data);

    sendAjax(pst);
}

function getItemData() {
    var category = {};

    category.id = parseInt($('.category-id').val());
    category.name = $('.category-name').val();

    return category;
}


$(document).ready(function () {
    $(document).on('click', '.category-update', function (e) {
        e.preventDefault();
        updateItem($(this));

    });
});

// save -------------------------------

function saveItem(button) {

    var pst = {};
    pst.selector = button;
    pst.type = "POST";
    pst.url = '/jsDonut/admin/category/createCategory';
    pst.data = {};
    pst.data = getDataFromForm();
    pst.successFunction = addNewCategory;

    console.log(pst.data);

    sendAjax(pst);
}

function getFormCreate() {

    $('.category-name').val('');

    $('.category-add').addClass('block__display-none');
    $('.category-list').addClass('block__display-none');
    $('.category-update').addClass('block__display-none');
    $('.category-form').removeClass('block__display-none');
    $('.category-save').removeClass('block__display-none');

}

function getDataFromForm() {
    var category = {};

    category.name = $('.category-name').val();

    return category;
}

function addNewCategory(categoryObject) {
    swal('Saved!');

    console.log(categoryObject);

    $('#category-table').find('tbody').append(
        '<tr  class="category-table__row" data-id='+categoryObject.id+'>' +
        '<td>' + categoryObject.id + '</td>' +
        '<td>' + categoryObject.name + '</td>' +
        '<td>' + '<button type="button" class="btn btn-md btn-primary category-update">' + 'Edit' + '</button>' + '</td>' +
        '<td>' + '<button type="button" class="btn btn-md btn-danger category-delete">' + 'Delete' + '</button>' + '</td>' +
        '</tr>');

    closeCategory();
}

$(document).ready(function () {
    $(document).on('click', '.category-save', function (e) {
        e.preventDefault();
        saveItem($(this));
    });
});

// delete -----------------------------

function deleteCategory(id, button) {

    if (intValueTest(id, 'Не удалось получить id')) return false;

    var pst = {};
    pst.data = id;
    pst.selector = button;
    pst.dataType = 'JSON';
    pst.type = "DELETE";
    pst.url = '/jsDonut/admin/category/deleteCategory';
    pst.successFunction = function (result) {
        pst.selector.closest('tr').remove();
        swal('Deleted!');
        closeCategory();
    };

    sendAjax(pst);
}

$(document).ready(function () {
    $(document).on('click', '.category-delete', function (e) {
        e.preventDefault();
        var id = $(this).closest('tr').data('id');
        deleteCategory(id, $(this));
    });
});

// Scripts

$(function() {
    $(document).on('click', '.category-add', function () {
        getFormCreate();
    });
    $(document).on('click', '.category-edit', function() {
        var id = $(this).closest('tr').data('id');
        getUpdateForm(id, $(this));
    });
    $(document).on('click', '.category-close', function() {
        closeCategory();
    });
});

function closeCategory() {

    $('.container-head').text("Category list");
    $('.category-add').removeClass('block__display-none');
    $('.category-list').removeClass('block__display-none');
    $('.category-form').addClass('block__display-none');

}
function intValueTest(value, text) {
    if (value === 0 || value === undefined) {
        swal('Ошибка', text, 'error');
        return true
    }
    return false;
}