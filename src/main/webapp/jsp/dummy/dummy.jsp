<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <%@include file="../include/header.jsp"%>

    <script>
        var dummy = {
            id    : 1,
            bool  : false,
            count : 10,
            name  : "더미",
            time  : "2019-06-22 11:21",
            yn    : "Y",
        };

        $(function() {
            setEvent();
        });

        function setEvent() {
            $("#btnJsonTest").on("click", function() {
                // you can pass data of js-object
                //ajaxJson("/dummy/json", dummy, testSuccess, false);

                // also you can pass data of serializeObject form data
                let _data = $("#formDummy").serializeObject();
                ajaxJson("/dummy/json", _data, testSuccess);
            });

            $("#btnEncodedTest").on("click", function() {
                // you can pass data of serialize form data
                //let _data = $("#formDummy").serialize();
                //ajaxEncoded("/dummy/encoded", _data, testSuccess, false);

                // also you can pass data of url encoded js-object
                let _data = $(dummy).serializeUrlEncoded();
                ajaxEncoded("/dummy/encoded", _data, testSuccess);
            });

            $("#btnMultipartTest").on("click", function() {
                let _formData = new FormData($("#formMultipart")[0]);
                ajaxMultipart("/dummy/multipart", _formData, multipartSuccess);
            });

            $("#btnBucket").on("click", function() {
                ajaxJson("/dummy/s3list", null, s3listSuccess);
            });

            $("#btnPaging").on("click", function() {
                searchDummyList();
            });

            $(".date").flatpickr();

            $(".datetime").flatpickr({
                enableTime: true,
            });

        }

        function searchDummyList(pageNumber=1) {
            let _data = {pageNumber: pageNumber};
            ajaxJson("/dummy/paging", _data, function(data) {
                let html = $("#dummy-template").render(data.list);
                $("#ulDummy").empty().append(html);

                $("#divPage").paginate(data.page, searchDummyList);
            });
        }

        function testSuccess(data) {
            console.log(data);
        }

        function multipartSuccess(data) {
            console.log(data);
        }

        function s3listSuccess(data) {
            console.log(data);
        }

        Handlebars.registerHelper({
            helpEmpty : function() {
                return "";
            },
            helpTime : function(time) {
                return formatLocalDateTime(time);
            },
            helpUse : function(yn) {
                return yn === "Y" ? "사용" : "사용안함";
            }
        });

    </script>

</head>
<body>

    <form id="formDummy">
        <input type="hidden" name="id" value="1"/>
        <input type="hidden" name="bool" value="false"/>
        <input type="hidden" name="count" value="10"/>
        <input type="hidden" name="name" value="더미"/>
        <input type="hidden" name="time" value="2019-06-22 11:21"/>
        <input type="hidden" name="yn" value="Y"/>
    </form>


    <h1>테스트를 해볼까?</h1>
    <button type="button" id="btnJsonTest">ajaxJSON 테스트</button>
    <button type="button" id="btnEncodedTest">ajaxEncoded 테스트</button>
    <button type="button" id="btnMultipartTest">ajaxMultipart 테스트</button>
    <button type="button" id="btnBucket">S3 버킷목록조회 테스트</button>
    <button type="button" id="btnPaging">페이징 테스트</button>


    <ul id="ulDummy"></ul>
    <script id="dummy-template" type="text/x-handlebars-template">
        {{#helpEmpty}}{{/helpEmpty}}

        {{#each list}}
        <li>
            {{@key}} : {{id}} - {{bool}} - {{count}} - {{name}} - {{helpTime time}} - {{helpUse yn}}
        </li>
        {{/each}}
    </script>
    <div id="divPage"></div>

    <hr/>

    <h1>멀티파트 AJAX 업로드</h1>
    <form id="formMultipart">
        <input type="text" name="id" placeholder="수정하려면 id입력"/><br/>
        <label for="chkBool">bool입력</label>
            <input type="hidden" name="_bool" value="false"/>
            <input type="checkbox" id="chkBool" name="bool"/><br/>
        <input type="number" name="count" placeholder="count 입력"/><br/>
        <input type="text" name="name" placeholder="name 입력"/><br/>
        <input type="text" class="datetime" name="time" placeholder="time 입력"/><br/>
        <span>yn입력</span>
        <label for="rdoY">Y</label><input type="radio" id="rdoY" name="yn" value="Y"/>
        <label for="rdoN">N</label><input type="radio" id="rdoN" name="yn" value="N"/><br/>
        S3 업로드(단일) <input type="file" name="fileOne"/><br/>
        로컬 업로드(멀티) <input type="file" name="fileMultiple" multiple="multiple"/><br/>
    </form>


    <hr/>

    <h1>이것은 무엇인고?</h1>
    <form id="formNothing">
        <input type="date" class="date" placeholder="nothing..."/><br/>
        <input type="email" placeholder="email 입력"/><br/>
    </form>

</body>

</html>