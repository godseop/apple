<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <%@include file="../include/header.jsp"%>

    <script>
    $(function() {
        $("#btnReg").on("click", function() {
            let _user = $("#formUser").serializeObject();
            ajaxJson("reg", _user, testSuccess);
        });
    });

    function testSuccess() {

    }

    </script>

</head>
<body>
<h1>사용자 등록</h1>

<form id="formUser">
    아이디 : <input type="text" name="id"/><br/>
    이름 : <input type="text" name="name"/>
</form>

<button type="button" id="btnReg">등록</button>

</body>

</html>