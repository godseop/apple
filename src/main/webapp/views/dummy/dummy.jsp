<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <%@include file="../include/header.jsp"%>

    <style type="text/css">
         input:read-only {
            border: 0;
            background-color: transparent;
            outline: none;
        }
    </style>

    <script type="text/javascript">
        var dummy = {
            id    : 1,
            bool  : false,
            count : 10,
            name  : "더미",
            time  : "2019-06-22 11:21",
            yn    : "Y",
        };

        $(function() {
            setCountdown();
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

            $("#btnBigFileTest").on("click", function() {
                let _formData = new FormData($("#formBigFile")[0]);
                ajaxMultipart("/dummy/bigfile", _formData, multipartSuccess);
            });


            $("#btnBucket").on("click", function() {
                ajaxJson("/dummy/s3list", null, s3listSuccess);
            });

            $("#btnLocal").on("click", function() {
                ajaxJson("/dummy/locallist", null, locallistSuccess);
            });

            $("#btnPaging").on("click", function() {
                searchDummyList();
            });

            $("#btnSerial").on("click", function() {
                let _data = $("#tbodyDummy").serializeTable();
                ajaxJson("/dummy/dummylist", _data, dummylistSuccess);
            });

            $(".date").flatpickr();

            $(".datetime").flatpickr({
                enableTime: true,
            });

            $("#chkAll").on("click", function() {
                $("input[name=chk]").prop("checked", this.checked)
                    .closest("tr").toggleClass("selected", this.checked);
            });
        }

        function setRefreshEvent() {
            $("input:checkbox[name=chk]").on("click", function () {
                $(this).closest("tr").toggleClass("selected");
            });

            $(".datetime").flatpickr({
                enableTime: true,
                //allowInput: true,
            });
        }

        function searchDummyList(pageNumber=1) {
            let _data = {pageNumber: pageNumber};
            ajaxJson("/dummy/paging", _data, function(data) {
                let html = $("#dummy-template").render(data.list);
                $("#tbodyDummy").empty().append(html)
                    .promise().then(setRefreshEvent);

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

        function locallistSuccess(data) {
            console.log(data);
        }

        function dummylistSuccess(data) {
            console.log(data);
        }

        function setCountdown() {
            let startTime = moment("20190626230000", "YYYYMMDDHHmmss");
            let endTime = moment("20190626235500", "YYYYMMDDHHmmss");
            let serverTime = moment("20190626233027", "YYYYMMDDHHmmss");

            let diffTime = endTime.valueOf() - serverTime.valueOf();
            let duration = moment.duration(diffTime, 'milliseconds');

            var tiktok = setInterval(function() {
                duration = moment.duration(duration.asMilliseconds() - 1000, 'milliseconds');

                $("#countdown").text(
                    "COUNTDOWN : " + moment.utc(moment(duration.asMilliseconds())).format("HH:mm:ss")
                );
            }, 1000);

        }

        Handlebars.registerHelper({
            helpEmpty: function(data) {    // listing helper
                if (data.list.length === 0) {
                    return "<tr><td colspan='8'>OOPS! NOTHING HERE...</td></tr>";
                } else {
                    return "";
                }
            },
            helpTime: function(time) {
                return formatLocalDateTime(time);
            },
            helpUse: function(yn) {
                return yn === "Y" ? "selected" : "";
            },
            helpChecked: function(checked) {
                return Boolean(checked) ? "checked" : "";
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

    <h1 id="countdown"></h1>


    <h1>테스트를 해볼까?</h1>
    <button type="button" id="btnJsonTest">ajaxJSON 테스트</button>
    <button type="button" id="btnEncodedTest">ajaxEncoded 테스트</button>
    <button type="button" id="btnMultipartTest">ajaxMultipart 테스트</button>
    <button type="button" id="btnBigFileTest">대용량업로드 테스트</button>
    <button type="button" id="btnBucket">S3 버킷목록조회 테스트</button>
    <button type="button" id="btnLocal">서버 로컬목록조회 테스트</button>
    <button type="button" id="btnPaging">페이징 테스트</button>
    <button type="button" id="btnSerial">직렬화 테스트</button>

    <table>
        <colgroup>
            <col style="width: 50px; text-align: center;"/>
            <col style="width: auto; text-align: center;"/>
            <col style="width: auto; text-align: center;"/>
            <col style="width: auto; text-align: center;"/>
            <col style="width: auto; text-align: center;"/>
            <col style="width: auto; text-align: center;"/>
            <col style="width: auto; text-align: center;"/>
            <col style="width: auto; text-align: center;"/>
        </colgroup>
        <thead>
            <tr>
                <td><input type="checkbox" id="chkAll"/></td>
                <td>순번</td>
                <td>아이디</td>
                <td>참거짓</td>
                <td>카운트</td>
                <td>이름</td>
                <td>시간</td>
                <td>사용여부</td>
            </tr>
        </thead>

        <tbody id="tbodyDummy">
            <tr>
                <td colspan="8">OOPS! NOTHING HERE...</td>
            </tr>
        </tbody>

        <script id="dummy-template" type="text/x-handlebars-template">
            {{#helpEmpty this}}{{/helpEmpty}}

            {{#each list}}
            <tr>
                <td><input type="checkbox" id="chk{{@key}}" name="chk"/></td>
                <td>{{@key}}</td>
                <td><input type="text" name="id" value="{{id}}" readonly/></td>
                <td>
                    <input type="checkbox" name="bool" value="{{bool}}" {{#helpChecked bool}}{{/helpChecked}} />
                </td>
                <td><input type="number" name="count" value="{{count}}"/></td>
                <td><input type="text" name="name" value="{{name}}" maxlength="30"/></td>
                <td>
                    <input type="text" class="datetime" name="time" value="{{helpTime time}}" readonly/>
                </td>
                <td>
                    <select name="yn">
                        <option value="Y" {{#helpUse yn}}{{/helpUse}}>사용</option>
                        <option value="N" {{#helpUse yn}}{{/helpUse}}>사용안함</option>
                    </select>
                </td>
            </tr>
            {{/each}}
        </script>
    </table>

    <div id="divPage"></div>



    <hr/>

    <h1>멀티파트 AJAX 업로드</h1>
    <form id="formMultipart">
        <input type="text" name="id" placeholder="수정하려면 id입력"/><br/>
        <label for="chkBool">bool입력</label>
            <input type="hidden" name="_bool" value="false"/> <!-- for unchecked checkbox value false -->
            <input type="checkbox" id="chkBool" name="bool"/><br/>
        <input type="number" name="count" placeholder="count 입력"/><br/>
        <input type="text" name="name" placeholder="name 입력"/><br/>
        <input type="text" class="datetime" name="time" placeholder="time 입력"/><br/>
        <span>yn입력</span>
        <label for="rdoY">Y</label><input type="radio" id="rdoY" name="yn" value="Y"/>
        <label for="rdoN">N</label><input type="radio" id="rdoN" name="yn" value="N"/><br/>
        S3 업로드(단일) <input type="file" name="fileOne"/><br/>
        로컬 업로드(멀티) <input type="file" name="fileMultiple" multiple/><br/>
    </form>


    <hr/>

    <h1>이것은 무엇인고?</h1>
    <form id="formNothing">
        <input type="date" class="date" placeholder="nothing..."/><br/>
        <input type="email" placeholder="email 입력"/><br/>
    </form>


    <hr/>
    <form id="formBigFile">
        S3 업로드(대용량멀티) <input type="file" name="fileList" multiple/><br/>
    </form>

</body>

</html>