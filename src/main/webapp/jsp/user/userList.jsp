<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <%@include file="../include/header.jsp"%>

    <script>
    const user = {
        seq: 1,
        id: "godseop",
        name: "임행섭",
        comments: [
            {name:"이름1", email:"이메일1", content:"내용1"},
            {name:"이름2", email:"이메일2", content:"내용2"},
            {name:"이름2", email:"이메일3", content:"내용3"},
        ],
    };

    $(function() {
        setEvent();
    });

    function setEvent() {
        $("#btnSearch").on("click", function() {
            ajaxJson("getUserListAll", {}, showUserList);
        });

        $("#btnJsonTest").on("click", function() {
            console.log($("#userForm").serializeObject());
            ajaxJson("json", $("#userForm").serializeObject(), testSuccess, false);
        });

        $("#btnEncodedTest").on("click", function() {
            ajaxEncoded("encoded", $("#userForm").serialize(), testSuccess, false);
        });
    }

    async function showUserList(data) {
        await sleep(5000);
        console.log(data);
        let li = "";
        for (let data of data) {
            li += "<li data-id='" + data[idx].id + "'>" + data[idx].name + "</li>";
        }
        $("#ulUserList").empty().html(li);
    }

    async function testSuccess(data) {
        console.log(data);
    }

    $.fn.serializeObject = function() {
        "use strict"
        let result = {}
        let extend = function(i, element) {
            let node = result[element.name]
            if ("undefined" !== typeof node && node !== null) {
                if ($.isArray(node)) {
                    node.push(element.value)
                } else {
                    result[element.name] = [node, element.value]
                }
            } else {
                result[element.name] = element.value
            }
        }

        $.each(this.serializeArray(), extend)
        return result
    }

    </script>

</head>
<body>
    <p>사용자 리스트</p>
    <button id="btnSearch">조회</button>
    <ul id="ulUserList">
        <!-- 사용자 리스트 출력 -->
    </ul>

    <button type="button" id="btnJsonTest">ajaxJSON 테스트</button>
    <button type="button" id="btnEncodedTest">ajaxEncoded 테스트</button>

    <form id="userForm">
        <input type="hidden" name="seq" value="3"/>
        <input type="hidden" name="id" value="hahaha"/>
        <input type="hidden" name="name" value="하하하"/>

        <input type="hidden" name="seq" value="2"/>
        <input type="hidden" name="id" value="jghj"/>
        <input type="hidden" name="name" value="잘되라"/>

        <input type="hidden" name="seq" value="1"/>
        <input type="hidden" name="id" value="fgfg"/>
        <input type="hidden" name="name" value="테스트"/>

        <%--<input type="hidden" name="comments.name" value="이름1"/>--%>
        <%--<input type="hidden" name="comments.email" value="이메일1"/>--%>
        <%--<input type="hidden" name="comments.content" value="내용1"/>--%>

        <%--<input type="hidden" name="comments.name" value="이름2"/>--%>
        <%--<input type="hidden" name="comments.email" value="이메일2"/>--%>
        <%--<input type="hidden" name="comments.content" value="내용2"/>--%>
    </form>
</body>

</html>