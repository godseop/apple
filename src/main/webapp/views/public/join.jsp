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
        $("#btnJoin").on("click", () => {
            let data = $("#formJoin").serializeObject();
            ajaxJson("/join", data, successJoin);
        });
    }

    function successJoin(data) {
        console.log(data);
        alert("등록되었습니다. 로그인하러갑시다^^");
        location.href = "${context}/";
    }
    </script>

</head>
<body>
<h1>사용자 등록</h1>

<form id="formJoin">
    아이디 : <input type="text" name="uid"/><br/>
    별명 : <input type="text" name="nickname"/><br/>
    비밀번호 : <input type="password" name="password"/><br/>

    이메일 : <input type="text" name="email"/><br/>
    지역 : <input type="text" name="location"/><br/>
</form>

<button type="button" id="btnJoin">등록</button>

</body>

</html>