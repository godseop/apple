// input name= _a[0].b[0].c 패턴 매칭을 위한 확장
$.extend(FormSerializer.patterns, {
    validate: /^[_]*[a-z][a-z0-9_]*((?:[\d+])*(?:.[^0-9][\w]*)?)*$/i,
});

function ajaxJson(url, object, callback, isLoadingBar=true) {
    console.log(context);
    $.post({
        url:         context + url,
        contentType: "application/json; charset=UTF-8",
        headers:     buildHeaders(),
        data:        JSON.stringify(object),
        beforeSend:  isLoadingBar && showLoadingBar,
        success: function(data) {
            if (data.result.code !== "0000") {
                alert("[" + data.result.code + "] " + data.result.message);
            } else {
                callback.call(this, data.response);
            }
        },
        error: function(data) {
            alert("[" + data.result.code + "] " + data.result.message);
        },
        complete:    isLoadingBar && hideLoadingBar,
    });
}

function ajaxEncoded(url, object, callback, isLoadingBar=true) {
    $.post({
        url:         context + url,
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        headers:     buildHeaders(),
        data:        object,
        beforeSend:  isLoadingBar && showLoadingBar,
        success: function(data) {
            if (data.result.code !== "0000") {
                alert("[" + data.result.code + "] " + data.result.message);
            } else {
                callback.call(this, data.response);
            }
        },
        error: function(data) {
            alert("[" + data.result.code + "] " + data.result.message);
        },
        complete:    isLoadingBar && hideLoadingBar,
    });
}

function ajaxMultipart(url, formData, callback, isLoadingBar=true) {
    $.post({
        url:         context + url,
        contentType: false,
        headers:     buildHeaders(),
        cache:       false,
        processData: false,
        timeout:     600000,
        enctype:     "multipart/form-data",
        data:        formData,
        beforeSend:  isLoadingBar && showLoadingBar,
        success: function(data) {
            if (data.result.code !== "0000") {
                alert("[" + data.result.code + "] " + data.result.message);
            } else {
                callback.call(this, data.response);
            }
        },
        error: function(data) {
            alert("[" + data.result.code + "] " + data.result.message);
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

// csrf 헤더 만들기
function buildHeaders() {
    //var csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
    var csrfToken = $("meta[name='_csrf']").attr("content");
    var csrfHeader = $("meta[name='_csrf_header']").attr("content");
    var headers = {};
    headers[csrfHeader] = csrfToken;

    return headers;
}

// jascript object ==> urlencoded string (e.g a=1&b=2&c[0].a=3&c[0].b=4&c[1].a=5 ...)
function serializeUrlEncoded(obj, prefix) {
    var str = [], p;

    for (p in obj) {
        if (obj.hasOwnProperty(p)) {
            var k = isNaN(p) ? (prefix ? prefix + "." + p : p) : (prefix ? prefix + "[" + p + "]" : p);
            var v = obj[p];

            str.push((v !== null && typeof v === "object") ?
                serializeUrlEncoded(v, k) :
                encodeURIComponent(k) + "=" + encodeURIComponent(v));
        }
    }
    return str.join("&");
}
