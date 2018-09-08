function getDoughForm(doughId, selector) {
    if (isNumber(doughId)) {
        getDough(doughId, selector);
    } else {
        alert("Dough ID is not number");
    }
}

function getDough(id, selector) {
    var ajax = {};
    ajax.data = {id: id};
    ajax.type = "GET";
    ajax.url = "/jsDonut/admin/dough/getDough";
    ajax.dataType = 'JSON';
    ajax.selector = selector;
    ajax.successFunction = openDoughForm;
    sendAjax(ajax);
}

function openDoughForm(doughObject) {

    $('.dough-id').val(doughObject.id);
    $('.dough-name').val(doughObject.name);
    $('.dough-calories').val(doughObject.calories);
    $('.dough-price').val(doughObject.price);

    $('.container-head').text("Dough: " + doughObject.name);
    $('.dough-list').addClass('block__display-none');
    $('.dough-form').removeClass('block__display-none');
}

function closeDough() {
    $('.container-head').text("Dough list");
    $('.dough-list').removeClass('block__display-none');
    $('.dough-form').addClass('block__display-none');
}

function isNumber(value) {
    return (value !== undefined && value !== null && value > 0);
}

$(function() {
    $(document).on('click', '.dough-edit', function() {
        // Почему не работает find()
        var id = $(this).parent().parent().data('id');
        getDoughForm(id, $(this));
    });
    $(document).on('click', '.dough-close', function() {
        closeDough();
    });
    $(document).on('click', '.dough-save', function() {
        closeDough();
    });
});