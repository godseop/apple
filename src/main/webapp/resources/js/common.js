// jquert.serializeObject > input name= _a[0].b[0].c 패턴 매칭을 위한 확장
$.extend(FormSerializer.patterns, {
    validate: /^[_]*[a-z][a-z0-9_]*((?:[\d+])*(?:.[^0-9][\w]*)?)*$/i,
});

function ajaxJson(url, object, callback, isLoadingBar=true) {
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
        error: function(request, status, error) {
            alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
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
        error: function(request, status, error) {
            alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
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
        error: function(request, status, error) {
            alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
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
    //let csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
    let csrfToken = $("meta[name='_csrf']").attr("content");
    let csrfHeader = $("meta[name='_csrf_header']").attr("content");
    let headers = {};
    headers[csrfHeader] = csrfToken;

    return headers;
}

function formatLocalDateTime(localDateTime) {
    // localDateTime likely '2019-06-22T06:35:59'
    return moment(localDateTime).format("YYYY-MM-DD HH:mm");
}

function getDateStamp(offset) {
    offset = (Number.isInteger(offset) ? offset : 0);
    return moment().add(offset, 'days').format("YYYY-MM-DD");
}

function getDateTimeStamp(offset) {
    offset = (Number.isInteger(offset) ? offset : 0);
    return moment().add(offset, 'days').format("YYYY-MM-DD HH:mm");
}


function getDateTimeStampByMillis(milliseconds) {
    return moment(milliseconds).format("YYYY-MM-DD HH:mm");
}

$.fn.extend({
    render: function(list) {
        let source = this.html();
        return Handlebars.compile(source)({list : list});
    },

    paginate: function(page, callback) {
        let source = $("#page-template").html();
        let html = Handlebars.compile(source)(page);
        this.empty().append(html);

        // register click event
        this.off("click").on("click", ".paging", function() {
            let pageNumber = $(this).data("pageNumber");
            callback.call(this, pageNumber);
        });
    },
    // jascript object ==> urlencoded string (e.g a=1&b=2&c[0].a=3&c[0].b=4&c[1].a=5 ...)
    serializeUrlEncoded: function(prefix) {
        let obj = this[0];
        if (obj == null || typeof obj !== "object")
            return;

        let str = [], p;
        for (p in obj) {
            if (obj.hasOwnProperty(p)) {
                let k = isNaN(p) ? (prefix ? prefix + "." + p : p) : (prefix ? prefix + "[" + p + "]" : p);
                let v = obj[p];

                str.push((v !== null && typeof v === "object") ?
                    v.serializeUrlEncoded(k) :
                    encodeURIComponent(k) + "=" + encodeURIComponent(v));
            }
        }
        return str.join("&");
    },

    // table tbody element serialize... ==> {list: [{tr1...}, {tr2...}]}
    serializeTable: function() {
        $(this).find("input select").each(function(index) {
            $(tr).serializeObject();
            // TODO 내일은 여기부터
        });



        return {list: }
    },
});
