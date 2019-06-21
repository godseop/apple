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
        $("#btnSave").on("click", function() {
            let _data = $("#formMember").serializeObject();
            ajaxJson("/member/modify", _data, function(data) {
                alert("저장되었습니다.");
            });
        });
    }
    </script>

</head>
<body>

    <h1>사용자 상세</h1>
    <button type="button" id="btnSave">저장</button>

    <form id="formMember">
        <label for="id">번호</label><input type="text" id="id" name="id" value="${member.id}" readonly/><br/>
        <label for="uid">아이디</label><input type="text" id="uid" name="uid" value="${member.uid}"/><br/>
        <label for="nickname">별명</label><input type="text" id="nickname" name="nickname" value="${member.nickname}"/>
    </form>

</body>

</html>