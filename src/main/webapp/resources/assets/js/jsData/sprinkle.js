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
    $('.sprinkle-id').val(sprinkleObject.id);
    $('.sprinkle-name').val(sprinkleObject.name);
    $('.sprinkle-calories').val(sprinkleObject.calories);
    $('.sprinkle-price').val(sprinkleObject.price);

    $('.container-head').text("Sprinkle: " + sprinkleObject.name);
    $('.sprinkle-list').addClass('block__display-none');
    $('.sprinkle-form').removeClass('block__display-none');
}

function closeSprinkle() {
    $('.container-head').text("Sprinkle list");
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
    $(document).on('click', '.sprinkle-update', function() {
        closeSprinkle();
    });
    $(document).on('click', '.sprinkle-delete', function() {
        closeSprinkle();
    });
});

function saveItem(button) {

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

    sprinkle.id = parseInt($('.sprinkle-id').val());
    sprinkle.name = $('.sprinkle-name').val();
    sprinkle.price = parseFloat($('.sprinkle-price').val());
    sprinkle.calories = parseInt($('.sprinkle-calories').val());

    return sprinkle;
}


$(document).ready(function () {
    $(document).on('click', '.sprinkle-update', function (e) {
        e.preventDefault();
        saveItem($(this));
    });
});




//
// function deleteItem(button) {
//     var sprinkle = {};
//     sprinkle.id = parseInt({id:id});
//
//     var pst = {};
//     pst.selector = button;
//     pst.type = "POST";
//     pst.url = '/jsDonut/admin/sprinkle/deleteSprinkle';
//     pst.data = sprinkle;
//
//     console.log(pst.data);
//
//     sendAjax(pst);
// }
// $(document).ready(function () {
//     $(document).on('click', '.sprinkle-delete', function (e) {
//         e.preventDefault();
//         deleteItem($(this));
//     });
// });