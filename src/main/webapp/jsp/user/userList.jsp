<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <%@include file="../include/header.jsp"%>

    <script>
    $(function() {
        setEvent();
    });

    function setEvent() {
        $("#btnSearch").on("click", function() {
            ajaxJson("getUserListAll", null, showUserList);
        });
    }

    function showUserList(data) {
        delay(3000);
        console.log(data);
        let li = "";
        for (let idx in data) {
            li += "<li data-id='" + data[idx].id + "'>" + data[idx].name + "</li>";
        }
        $("#ulUserList").empty().html(li);
    }

    </script>

</head>
<body>
    <p>사용자 리스트</p>
    <button id="btnSearch">조회</button>
    <ul id="ulUserList">
        <!-- 사용자 리스트 출력 -->
    </ul>
</body>

</html>