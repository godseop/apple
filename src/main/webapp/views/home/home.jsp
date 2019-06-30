<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <%@include file="../include/header.jsp"%>

    <script>
        // JAVASCRIPT HERE
        $(function() {
            $("#btnNow").on("click", function() {
                ajaxJson("/common/now", null, function(data) {
                    console.log(data.now * 1000);
                    alert("현재시간은 " + getDateTimeStampByMillis(data.now * 1000));
                });
            });

        });
    </script>
</head>
<body>
    <h1>HOME</h1>

    <sec:authorize access="hasRole('ADMIN')">
        <p>ADMIN일 경우 이게 보입니다^_^</p>
        <a href="${context}/member/list">회원목록</a>
    </sec:authorize>

    <sec:authorize access="isAuthenticated()">
        <p>인증된 사용자의 경우 이게 보입니다^_^</p>
    </sec:authorize>

    <sec:authorize access="permitAll()">
        <p>누구에게나 보입니다</p>
        <a href="${context}/dummy/">테스트페이지</a>
        <a href="${context}/dummy/chat">채팅페이지</a>
        <a href="${context}/dummy/video">비디오페이지</a>
    </sec:authorize>

    <sec:authorize access="hasAnyRole('AUTHOR', 'BASIC')">
        <p>준회원&정회원에게 보입니다</p>
    </sec:authorize>

    <form action="${context}/logout" method="POST">
        <sec:csrfInput/>
        <button type="submit">로그아웃</button>
    </form>

    <button id="btnNow">현재시간은?</button>
</body>
</html>