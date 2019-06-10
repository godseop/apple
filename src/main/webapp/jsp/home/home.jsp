<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <%@include file="../include/header.jsp"%>

    <script>
        // JAVASCRIPT HERE
        $(function() {

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
    </sec:authorize>

    <sec:authorize access="hasAnyRole('AUTHOR', 'BASIC')">
        <p>준회원&정회원에게 보입니다</p>
    </sec:authorize>

    </a>

    <a href="${context}/logout">로그아웃</a>
</body>
</html>