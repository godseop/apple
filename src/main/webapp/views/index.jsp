<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <%@include file="include/header.jsp"%>

    <script>
        // JAVASCRIPT HERE
        $(function() {
            setEvent();
        });

        function setEvent() {

        }
    </script>
</head>
<body>
    <h1>WELCOME TO APPLE PROJECT</h1>

    <sec:authorize access="isAnonymous()">
        <a href="${context}/login">로그인하러가기</a>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <form action="${context}/logout" method="POST">
            <sec:csrfInput/>
            <button type="submit">로그아웃</button>
        </form>
    </sec:authorize>


</body>
</html>