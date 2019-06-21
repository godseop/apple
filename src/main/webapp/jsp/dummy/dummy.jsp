<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <%@include file="../include/header.jsp"%>

    <script>
        var dummy = {
            id    : 1,
            bool  : 0,
            count : 10,
            name  : "더미",
            time  : "20190622045013",
            yn    : "Y",
        };

        $(function() {
            setEvent();
        });

        function setEvent() {
            $("#btnJsonTest").on("click", function() {
                // you can pass data of js-object
                ajaxJson("/dummy/json", dummy, testSuccess, false);

                // also you can pass data of serializeObject form data
                //let _data = $("#formDummy").serializeObject();
                //ajaxJson("/dummy/json", _data, testSuccess);
            });

            $("#btnEncodedTest").on("click", function() {
                // you can pass data of serialize form data
                //let _data = $("#formDummy").serialize();
                //ajaxEncoded("/dummy/encoded", _data, testSuccess, false);

                // also you can pass data of url encoded js-object
                let _data = serializeUrlEncoded(dummy);
                ajaxEncoded("/dummy/encoded", _data, testSuccess);
            });

            $("#btnMultipartTest").on("click", function() {
                let _formData = new FormData($("#formMultipart")[0]);
                ajaxMultipart("/dummy/multipart", _formData, multipartSuccess);
            });

            $("#btnBucket").on("click", function() {
                ajaxJson("/dummy/s3list", null, s3listSuccess);
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

    </script>

</head>
<body>

    <form id="formDummy">
        <input type="hidden" name="id" value="1"/>
        <input type="hidden" name="bool" value="0"/>
        <input type="hidden" name="count" value="10"/>
        <input type="hidden" name="name" value="더미"/>
        <input type="hidden" name="time" value="20190622045013"/>
        <input type="hidden" name="yn" value="Y"/>
    </form>


    <h1>테스트를 해볼까?</h1>
        <button type="button" id="btnJsonTest">ajaxJSON 테스트</button>
        <button type="button" id="btnEncodedTest">ajaxEncoded 테스트</button>
        <button type="button" id="btnMultipartTest">ajaxMultipart 테스트</button>
        <button type="button" id="btnBucket">S3 버킷목록조회 테스트</button>

        <ul id="ulDummy"></ul>
    <hr/>

    <h1>멀티파트 AJAX 업로드</h1>
    <form id="formMultipart">
        <input type="text" name="name" placeholder="name 입력"/>
        <input type="text" name="yn" placeholder="yn 입력"/>
        멀티파일 선택 <input type="file" name="fileMultiple" multiple="multiple"/><br/>
        단일파일 선택 <input type="file" name="fileOne"/>
    </form>

</body>

</html>