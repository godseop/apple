<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>

    <meta charset="UTF-8">
    <sec:csrfMetaTags />
    <title>Apple Project</title>

    <!-- favicon -->
    <link rel="icon" type="image/png" href="${context}/static/favicon.ico"/>
    <!-- //favicon -->

    <!-- css -->
    <link rel="stylesheet" href="${context}/static/css/common.css">
    <link rel="stylesheet" href="${context}/static/css/spinkit.css">
    <link rel="stylesheet" href="${context}/static/css/flatpickr-4.5.7.min.css">
    <!-- //css -->

    <!-- javascript -->
    <script src="${context}/static/js/jquery-3.4.1.min.js" type="text/javascript"></script>
    <script src="${context}/static/js/jquery.serializeObject.min.js" type="text/javascript"></script>
    <script src="${context}/static/js/handlebars-4.1.2.min.js" type="text/javascript"></script>
    <script src="${context}/static/js/moment-2.24.0.min.js" type="text/javascript"></script>
    <script src="${context}/static/js/moment-locales.min.js" type="text/javascript"></script>
    <script src="${context}/static/js/flatpickr-4.5.7.min.js" type="text/javascript"></script>
    <script src="${context}/static/js/flatpickr-locale.ko.js" type="text/javascript"></script>
    <script src="${context}/static/js/common.js" type="text/javascript"></script>
    <!-- //javascript -->

    <style>
        /** for active page **/
        button.paging.active {
            background-color: darkred;
        }
    </style>

    <script>
        var context = "${context}";

        flatpickr.localize(flatpickr.l10ns.ko);  // flatpickr localization
        flatpickr.l10ns.default.firstDayOfWeek = 0; // sunday
        flatpickr.setDefaults({
            time_24hr: true,
            defaultHour: 0,
            defaultMinute: 0,
            dateFormat: "Y-m-d",
            //defaultDate: getDateTimeStamp(),
        });

        moment.locale('ko');    // moment localization
        $.support.cors = true;  // permit cors

        Handlebars.registerHelper({     // paging helper
            helpActivePage : function(current, active) {
                if (current === active)
                    return "active";
            },
        });
    </script>

    <script id="page-template" type="text/x-handlebars-template">
        <button type="button" class="paging" data-page-number="1">처음으로</button>
        <button type="button" class="paging" data-page-number="{{prevPageNumber}}">이전페이지</button>
        {{#each pageList}}
        <button type="button" class="paging {{#helpActivePage this ../pageNumber}}{{/helpActivePage}}" data-page-number="{{this}}">{{this}}페이지</button>
        {{/each}}
        <button type="button" class="paging" data-page-number="{{nextPageNumber}}">다음페이지</button>
        <button type="button" class="paging" data-page-number="{{totalPage}}">끝으로</button>
    </script>




