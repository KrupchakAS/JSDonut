function saveItem(button) {

    var pst = {};
    pst.selector = button;
    pst.type = "POST";
    pst.url = '/jsDonut/admin/dough/saveDough';
    pst.data = {};
    pst.data = getItemData();

    console.log(pst.data);

    sendAjax(pst);
}

function getItemData() {
    var dough = {};

    dough.id = parseInt($('.dough-id').val());
    dough.name = $('.dough-name').val();
    dough.price = parseFloat($('.dough-price').val());
    dough.calories = parseInt($('.dough-calories').val());

    return dough;
}


$(document).ready(function () {
    $(document).on('click', '.dough-save', function (e) {
        e.preventDefault();
        saveItem($(this));
    });
});