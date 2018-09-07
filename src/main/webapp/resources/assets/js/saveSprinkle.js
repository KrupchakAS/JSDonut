
function saveItem(button) {

    var pst = {};
    pst.button = button;
    pst.url = '/jsDonut/admin/sprinkle/saveSprinkle';
    pst.data = {};
    pst.data = getItemData();

    console.log(pst.data);

    sendAjax(pst);
}

function getItemData() {
    var sprinkle = {};

    sprinkle.name = $('.sprinkle-name').val();
    sprinkle.price = $('.sprinkle-price').val();
    sprinkle.calories = $('.sprinkle-calories').val();

    return sprinkle;
}


$(document).ready(function () {
    $(document).on('click', '.sprinkle-save', function (e) {
        e.preventDefault();
        saveItem($(this));
    });
});