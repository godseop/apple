<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<c:set var="context" scope="page" value="${pageContext.request.contextPath}"/>

    <meta charset="UTF-8">
    <sec:csrfMetaTags />
    <title>Apple Project</title>

    <!-- favicon -->
    <link rel="icon" type="image/png" href="${context}/resources/favicon.ico"/>
    <!-- //favicon -->

    <!-- css -->
    <link rel="stylesheet" href="${context}/resources/css/common.css">
    <link rel="stylesheet" href="${context}/resources/css/spinkit.css">
    <link rel="stylesheet" href="${context}/resources/css/flatpickr-4.5.7.min.css">
    <!-- //css -->

    <!-- javascript -->
    <script src="${context}/resources/js/jquery-3.4.1.min.js" type="text/javascript"></script>
    <script src="${context}/resources/js/jquery.serializeObject.min.js" type="text/javascript"></script>
    <script src="${context}/resources/js/handlebars-4.1.2.min.js" type="text/javascript"></script>
    <script src="${context}/resources/js/moment-2.24.0.min.js" type="text/javascript"></script>
    <script src="${context}/resources/js/moment-locales.min.js" type="text/javascript"></script>
    <script src="${context}/resources/js/flatpickr-4.5.7.min.js" type="text/javascript"></script>
    <script src="${context}/resources/js/flatpickr-locale.ko.js" type="text/javascript"></script>
    <script src="${context}/resources/js/sockjs-client-1.3.0.min.js" type="text/javascript"></script>
    <script src="${context}/resources/js/stomp.umd.min.js" type="text/javascript"></script>
    <script src="${context}/resources/js/common.js" type="text/javascript"></script>
    <!-- //javascript -->

    <script type="text/javascript">
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

        Handlebars.registerHelper({
            helpActivePage : function(current, active) {    // paging helper
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




