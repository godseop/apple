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
        success: function(response) {
            if (response.code !== "0000") {
                alert("[" + response.code + "] " + response.message);
            } else {
                callback.call(this, response.data);
            }
        },
        error: function(request, status, error) {
            console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
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
        success: function(response) {
            if (response.code !== "0000") {
                alert("[" + response.code + "] " + response.message);
            } else {
                callback.call(this, response.data);
            }
        },
        error: function(request, status, error) {
            console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
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
        success: function(response) {
            if (response.code !== "0000") {
                alert("[" + response.code + "] " + response.message);
            } else {
                callback.call(this, response.data);
            }
        },
        error: function(request, status, error) {
            console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
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
    if (localDateTime == null) return null;
    return moment(localDateTime).format("YYYY-MM-DD HH:mm");
}

function getDateStamp(offset) {
    offset = Number.isInteger(offset) ? offset : 0;
    return moment().add(offset, 'days').format("YYYY-MM-DD");
}

function getDateTimeStamp(offset) {
    offset = Number.isInteger(offset) ? offset : 0;
    return moment().add(offset, 'days').format("YYYY-MM-DD HH:mm");
}


function getDateTimeStampByMillis(milliseconds) {
    if (!Number.isInteger(milliseconds)) return null;
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
        let object = this[0];
        if (object == null || typeof object !== "object")
            return;

        let result = [], property;
        for (property in object) {
            if (object.hasOwnProperty(property)) {
                let key = isNaN(property) ?
                    (prefix ? prefix + "." + property : property)
                  : (prefix ? prefix + "[" + property + "]" : property);
                let value = object[property];

                result.push((value !== null && typeof value === "object") ?
                                value.serializeUrlEncoded(key)
                              : encodeURIComponent(key) + "=" + encodeURIComponent(value));
            }
        }
        return result.join("&");
    },

    // table tbody element serialize... ==> [{tr1...}, {tr2...}]
    serializeTable: function() {
        let result = [];
        let tr = {};
        this.find("tr").each(function(trIndex, trElement) {
            tr = {};
            $(trElement).find("input, select").each(function(tdIndex, tdElement) {
                tr[$(tdElement).attr("name")] = $(tdElement).val();
            });
            result.push(tr);
        });
        return result;
    },
});
