function ajaxJson(url, obj, callback, async) {
    async = (typeof async !== 'undefined' ? async : true);

    $.ajax({
        url: url,
        method: "post",
        async: async,
        contentType: "application/json; charset=UTF-8",
        data: JSON.stringify(obj),
        beforeSend: showLoadingBar,
        complete: hideLoadingBar,
        success: callback,
        error: function(jqXHR, textStatus, errorThrown) {
            alert("[AJAX FAIL]jqXHR: " + JSON.stringify(jqXHR)
                + "\ntextStatus: " + textStatus
                + "\nerrorThrown: " + errorThrown);
        }
    });
}

function ajaxEncoded(url, $form, callback, async) {
    async = (typeof async !== 'undefined' ? async : true);

    $.ajax({
        url: url,
        method: "post",
        async: async,
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        data: $form.serialize(),
        beforeSend: showLoadingBar,
        complete: hideLoadingBar,
        success: callback,
        error: function(jqXHR, textStatus, errorThrown) {
            alert("[AJAX FAIL]jqXHR: " + JSON.stringify(jqXHR)
                + "\ntextStatus: " + textStatus
                + "\nerrorThrown: " + errorThrown);
        }
    });
}

// 로딩바 보임
function showLoadingBar(jqXHR, settings) {
    $(".sk-wave").show();
    console.log("jqXHR: " + JSON.stringify(jqXHR)
        + "\nsettings: " + JSON.stringify(settings));
}

// 로딩바 숨김
function hideLoadingBar(jqXHR, textStatus) {
    $(".sk-wave").hide();
    console.log("jqXHR: " + JSON.stringify(jqXHR)
        + "\ntextStatus: " + textStatus);
}


// millisecond 동안 딜레이 함수
function delay(millisecond) {
    let then, now;
    then = new Date().getTime();
    now = then;
    while ((now - then) < millisecond) {
        now = new Date().getTime();
    }
}
