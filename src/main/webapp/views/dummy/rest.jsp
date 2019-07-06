<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <%@include file="../include/header.jsp"%>

    <script type="text/javascript">
        $(function() {
            setEvent();
        });

        function setEvent() {
            $("#btnBook").on("click", function() {
                ajaxEncoded("/external/google/book", {isbn: $("input[name=isbn]").val()}, successFunction);
            });

            $("#btnUser").on("click", function() {
                ajaxEncoded("/external/github/user", {githubId: $("input[name=githubId]").val()}, successFunction);
            });
        }

        function successFunction(data) {
            $("#txtResult").val(JSON.stringify(data));
        }

    </script>
</head>
<body>
    <h1>Google API 테스트</h1>

    ISBN : <input type="text" name="isbn" value="0747532699"/>
    <button type="button" id="btnBook">google Book Search</button>

    <h1>GitHub API 테스트</h1>

    ID : <input type="text" name="githubId" value="godseop"/>
    <button type="button" id="btnUser">github user Search</button>



    <h1>결과</h1>
    <textarea id="txtResult" maxlength="300" rows="50"></textarea>
</body>
</html>