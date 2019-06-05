function ajaxJson(url, object, callback, isLoadingBar=true) {
    $.post({
        url: url,
        contentType: "application/json; charset=UTF-8",
        data: JSON.stringify(object),
        beforeSend: isLoadingBar && showLoadingBar,
        success: callback,
        error: function() {
            alert("서버로 요청중 에러가 발생했습니다.");
        },
        complete: isLoadingBar && hideLoadingBar,
    });
}

function ajaxEncoded(url, object, callback, isLoadingBar=true) {
    $.post({
        url: url,
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        data: object,
        beforeSend: isLoadingBar && showLoadingBar,
        success: callback,
        error: function() {
            alert("서버로 요청중 에러가 발생했습니다.");
        },
        complete: isLoadingBar && hideLoadingBar,
    });
}

// 로딩바 보임
function showLoadingBar() {
    $(".sk-wave").show();
}

// 로딩바 숨김
function hideLoadingBar() {
    $(".sk-wave").hide();
}


function sleep(millisecond) {
    return new Promise(resolve => setTimeout(resolve, millisecond));
}