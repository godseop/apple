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
            location.href = "${context}/login";
        }
    </script>
</head>
<body>
    <h1>WELCOME TO APPLE PROJECT</h1>

    <button type="button" id="btnLogin">로그인</button>
</body>
</html>