<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <%@include file="../include/header.jsp"%>

    <script>

    </script>

</head>
<body>
<h1>사용자 상세</h1>

<form id="formUser">
    번호 : <input type="text" name="seq" value="${member.seq}" disabled/><br/>
    아이디 : <input type="text" name="id" value="${member.id}"/><br/>
    이름 : <input type="text" name="name" value="${member.name}"/>
</form>

<button type="button" id="btnSave">저장</button>

</body>

</html>