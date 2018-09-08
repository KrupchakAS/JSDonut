function sendAjax(ajax) {

    var request = $.ajax({

        type: ajax.type,

        contentType: "application/json;charset=UTF-8",

        url: ajax.url,

        data: (ajax.type === "POST") ? JSON.stringify(ajax.data) : ajax.data,

        dataType: ajax.dataType,

        beforeSend: function (xhr) {

            if (ajax.selector !== undefined) {

                disabledController(ajax.selector, true);
                var token = $("meta[name='_csrf']").attr("content");
                var header = $("meta[name='_csrf_header']").attr("content");
                xhr.setRequestHeader(header, token);
            }

        }

    });

    request.done(function (result) {

        if (result.code === 200 || result.code === undefined) {
            if(ajax.successFunction !== undefined) {
                ajax.successFunction(result.data);
            }
        } else {

            if (ajax.errorFunction !== undefined) {

                ajax.errorFunction(result);

            } else {

                console.log(result.error);

            }

        }

    });

    request.fail(function (jqXHR, textStatus) {

        alert(jqXHR.statusText);

    });

    request.always(function () {

        if (ajax.selector !== undefined) {

            disabledController(ajax.selector, false);

        }

    });

}

function disabledController(selector, flag) {

    if (Array.isArray(selector)) {

        $.each(selector, function () {

            setSelectorDisabled($(this), flag);

        });

    } else setSelectorDisabled(selector, flag);

}

function setSelectorDisabled(selector, flag) {

    if (flag) {

        selector.attr('disabled', 'disabled');

        selector.addClass('disabled');

    } else {

        selector.removeAttr('disabled');

        selector.removeClass('disabled');

    }

}