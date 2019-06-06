// input name=a[0].b[0].c 패턴 매칭을 위한 확장
$.extend(FormSerializer.patterns, {
    validate: /^[a-z][a-z0-9_]*((?:[\d+])*(?:.[^0-9][\w]*)?)*$/i,
});

function ajaxJson(url, object, callback, isLoadingBar=true) {
    $.post({
        url:         url,
        contentType: "application/json; charset=UTF-8",
        data:        JSON.stringify(object),
        beforeSend:  isLoadingBar && showLoadingBar,
        success:     callback,
        error: function() {
            alert("서버로 요청중 에러가 발생했습니다.");
        },
        complete:    isLoadingBar && hideLoadingBar,
    });
}

function ajaxEncoded(url, object, callback, isLoadingBar=true) {
    $.post({
        url:         url,
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        data:        object,
        beforeSend:  isLoadingBar && showLoadingBar,
        success:     callback,
        error: function() {
            alert("서버로 요청중 에러가 발생했습니다.");
        },
        complete:    isLoadingBar && hideLoadingBar,
    });
}

// 로딩바 보임
async function showLoadingBar() {
    await $(".sk-wave").show();
}

// 로딩바 숨김
async function hideLoadingBar() {
    await $(".sk-wave").hide();
}