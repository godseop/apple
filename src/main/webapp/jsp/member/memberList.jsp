<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <%@include file="../include/header.jsp"%>

    <script>
    let member = {
        id: 1,
        uid: "godseop",
        nickname: "임행섭",
    };

    $(function() {
        console.log(member);
        setEvent();
    });

    function setEvent() {
        $("#btnSearch").on("click", function() {
            ajaxJson("all", null, showUserList);
        });

        $("#btnJsonTest").on("click", function() {
            let _user = $("#formUser").serializeObject();
            ajaxJson("${context}/dummy/json", _user, testSuccess);
            // also you can pass data of javacript object
            // ajaxJson("json, member, testSuccess);
        });

        $("#btnEncodedTest").on("click", function() {
            let _user = $("#formUser").serialize();
            ajaxEncoded("${context}/dummy/encoded", _user, testSuccess, false);

            // TODO:then how to pass data of javascript object by url-encoded type?
            // ajaxEncoded("encoded", member, testSuccess, false);
        });

        $("#btnMultipartTest").on("click", function() {
            let _formData = new FormData($("#formMultipart")[0]);
            ajaxMultipart("${context}/dummy/multipart", _formData, uploadSuccess, false);
        });

        $("#btnS3Test").on("click", function() {
            ajaxJson("${context}/dummy/s3list", null, listSuccess, true);
        });
    }

    function showUserList(data) {
        let li = "";
        for (let member of data.memberList) {
            li += "<li data-id='" + member.uid + "'>" + member.nickname + "</li>";
        }
        $("#ulUserList").empty().html(li);
    }


    function testSuccess(data) {
        let li = "<li data-id='" + data.member.uid + "'>" + data.member.nickname + "</li>";
        $("#ulUserList").empty().html(li);
    }

    function uploadSuccess(data) {
        console.log(data);
    }

    function listSuccess(data) {
        console.log(data);
    }

    </script>

</head>
<body>
    <h1>유저 리스트(MODEL)</h1>
    <ul>
    <c:forEach var="member" items="${memberList}" varStatus="status">
        <li>
            ${status.count} : <a href="${context}/member/detail/${member.uid}">${member.nickname}</a>
        </li>
    </c:forEach>
    </ul>

    <p>사용자 리스트(AJAX)</p>
    <button id="btnSearch">조회</button>
    <ul id="ulUserList">
        <!-- 사용자 리스트 출력 -->
    </ul>

    <button type="button" id="btnJsonTest">ajaxJSON 테스트</button>
    <button type="button" id="btnEncodedTest">ajaxEncoded 테스트</button>
    <button type="button" id="btnMultipartTest">ajaxMultipart 테스트</button>
    <button type="button" id="btnS3Test">S3 목록조회 테스트</button>

    <form id="formUser">
        <sec:csrfInput/>
        <input type="hidden" name="id" value="3"/>
        <input type="hidden" name="uid" value="hahaha"/>
        <input type="hidden" name="nickname" value="하하하"/>
    </form>

    <hr/>
    <p>멀티파트 AJAX 업로드</p>
    <form id="formMultipart">
        <sec:csrfInput/>
        <input type="text" name="uid" placeholder="아이디입력해봐"/>
        <input type="text" name="nickname" placeholder="이름이뭐니?"/><br>
        멀티파일 선택 <input type="file" name="fileMultiple" multiple="multiple"/><br/>
        단일파일 선택 <input type="file" name="fileOne"/>
    </form>
</body>

</html>