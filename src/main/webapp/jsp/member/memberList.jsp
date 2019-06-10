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

    <form id="formUser">
        <sec:csrfInput/>
        <input type="hidden" name="id" value="3"/>
        <input type="hidden" name="uid" value="hahaha"/>
        <input type="hidden" name="nickname" value="하하하"/>
    </form>
</body>

</html>