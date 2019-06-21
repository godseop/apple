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
        nickname: "노량",
    };

    $(function() {
        setEvent();
    });

    function setEvent() {
        $("#btnSearch").on("click", function() {
            let _data = $("#formCondition").serializeObject();
            ajaxJson("/member/all", _data, showUserList);
        });
    }

    function showUserList(data) {
        let li = "";
        for (let member of data.memberList) {
            li += "<li data-id='" + member.uid + "'>" + member.nickname + "</li>";
        }
        $("#ulUserList").empty().html(li);
    }
    </script>

</head>
<body>

    <form id="formCondition">
        <input type="hidden" name="regStDate" value="2019-01-01"/>
        <input type="hidden" name="regEdDate" value="2019-12-31"/>
    </form>

    <h1>멤버 리스트(MODEL)</h1>
    <ul>
    <c:forEach var="member" items="${memberList}" varStatus="status">
        <li>
            ${status.count} : <a href="${context}/member/detail/${member.uid}">${member.nickname}</a>
        </li>
    </c:forEach>
    </ul>

    <hr/>

    <h1>멤버 리스트(AJAX)</h1>
    <button id="btnSearch">조회</button>
    <ul id="ulUserList">
        <!-- 사용자 리스트 출력 -->
    </ul>

</body>

</html>