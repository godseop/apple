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
    <p>HOME</p>

    <form id="formLogout" action="${context}/logout" method="get">
        <input type="submit" value="로그아웃"/>
    </form>
</body>
</html>