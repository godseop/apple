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
    <h1>로그인</h1>

    <form id="formLogin" method="post">
        <sec:csrfInput />
        <div class="row">
            <div>
                <label for="uid">ID</label>
                <input type="text" id="uid" name="uid"/>
            </div>
        </div>
        <div class="row">
            <div>
                <label for="password">Password</label>
                <input type="password" id="password" name="password"/>
            </div>
        </div>
        <input type="submit" value="로그인" />
    </form>

    <form id="formRegister" action="${context}/join">
        <input type="submit" value="가입하기" />
    </form>

</body>
</html>