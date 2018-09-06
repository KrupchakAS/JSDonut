function getSprinkleForm(sprinkleId, selector) {
    if (isNumber(sprinkleId)) {
        getSprinkle(sprinkleId, selector);
    } else {
        alert("Sprinkle ID is not number");
    }
}

function getSprinkle(id, selector) {
    var ajax = {};
    ajax.data = {id: id};
    ajax.type = "GET";
    ajax.url = "/jsDonut/admin/sprinkle/getSprinkle";
    ajax.dataType = 'JSON';
    ajax.selector = selector;
    ajax.successFunction = openSprinkleForm;
    sendAjax(ajax);
}

function openSprinkleForm(sprinkleObject) {
    $('.sprinkle-name').val(sprinkleObject.name);
    $('.sprinkle-calories').val(sprinkleObject.calories);
    $('.sprinkle-price').val(sprinkleObject.price);

    $('.container-head').text("Sprinkle: " + sprinkleObject.name);
    $('.sprinkle-list').addClass('block__display-none');
    $('.sprinkle-form').removeClass('block__display-none');
}

function closeSprinkle() {
    $('.container-head').text("prinkle list");
    $('.sprinkle-list').removeClass('block__display-none');
    $('.sprinkle-form').addClass('block__display-none');
}

function isNumber(value) {
    return (value !== undefined && value !== null && value > 0);
}

$(function() {
    $(document).on('click', '.sprinkle-edit', function() {
        // Почему не работает find()
        var id = $(this).parent().parent().data('id');
        getSprinkleForm(id, $(this));
    });
    $(document).on('click', '.sprinkle-close', function() {
        closeSprinkle();
    });
});