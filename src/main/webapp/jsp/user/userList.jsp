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
            ajaxJson("getUserListAll", null, showUserList);
        });

        $("#btnJsonTest").on("click", function() {
            let _user = $("#formUser").serializeObject();
            ajaxJson("json", _user, testSuccess);
        });

        $("#btnEncodedTest").on("click", function() {
            let _user = $("#formUser").serialize();
            ajaxEncoded("encoded", _user, testSuccess, false);
        });
    }

    function showUserList(data) {
        let li = "";
        for (let element of data) {
            li += "<li data-id='" + element.id + "'>" + element.name + "</li>";
        }
        $("#ulUserList").empty().html(li);
    }

    function testSuccess(data) {
        console.log("return data : " + JSON.stringify(data));
    }
    </script>

</head>
<body>
    <h1>dfdfdf</h1>
    <p>사용자 리스트</p>
    <button id="btnSearch">조회</button>
    <ul id="ulUserList">
        <!-- 사용자 리스트 출력 -->
    </ul>

    <button type="button" id="btnJsonTest">ajaxJSON 테스트</button>
    <button type="button" id="btnEncodedTest">ajaxEncoded 테스트</button>

    <form id="formUser">
        <input type="hidden" name="seq" value="3"/>
        <input type="hidden" name="id" value="hahaha"/>
        <input type="hidden" name="name" value="하하하"/>

        <input type="hidden" name="comments[0].name" value="이름a"/>
        <input type="hidden" name="comments[0].email" value="이메일1"/>
        <input type="hidden" name="comments[0].content" value="내용1"/>
        <input type="hidden" name="comments[0].demos[0].a" value="데모문자열1"/>
        <input type="hidden" name="comments[0].demos[0].b" value="1583"/>
        <input type="hidden" name="comments[0].demos[1].a" value="데모문자열2"/>
        <input type="hidden" name="comments[0].demos[1].b" value="7676"/>


        <input type="hidden" name="comments[1].name" value="이름2"/>
        <input type="hidden" name="comments[1].email" value="이메일2"/>
        <input type="hidden" name="comments[1].content" value="내용2"/>

        <input type="hidden" name="comments[2].name" value="이름2"/>
        <input type="hidden" name="comments[2].email" value="이메일2"/>
        <input type="hidden" name="comments[2].content" value="내용2"/>

        <input type="hidden" name="comments[3].name" value="이름2"/>
        <input type="hidden" name="comments[3].email" value="이메일2"/>
        <input type="hidden" name="comments[3].content" value="내용2"/>

        <input type="hidden" name="comments[4].name" value="이름2"/>
        <input type="hidden" name="comments[4].email" value="이메일2"/>
        <input type="hidden" name="comments[4].content" value="내용2"/>

        <input type="hidden" name="comments[5].name" value="이름2"/>
        <input type="hidden" name="comments[5].email" value="이메일2"/>
        <input type="hidden" name="comments[5].content" value="내용2"/>

        <input type="hidden" name="comments[6].name" value="이름2"/>
        <input type="hidden" name="comments[6].email" value="이메일2"/>
        <input type="hidden" name="comments[6].content" value="내용2"/>

        <input type="hidden" name="comments[7].name" value="이름2"/>
        <input type="hidden" name="comments[7].email" value="이메일2"/>
        <input type="hidden" name="comments[7].content" value="내용2"/>

        <input type="hidden" name="comments[8].name" value="이름2"/>
        <input type="hidden" name="comments[8].email" value="이메일2"/>
        <input type="hidden" name="comments[8].content" value="내용2"/>

        <input type="hidden" name="comments[9].name" value="이름2"/>
        <input type="hidden" name="comments[9].email" value="이메일2"/>
        <input type="hidden" name="comments[9].content" value="내용2"/>

        <input type="hidden" name="comments[10].name" value="이름2"/>
        <input type="hidden" name="comments[10].email" value="이메일2"/>
        <input type="hidden" name="comments[10].content" value="내용2"/>

        <input type="hidden" name="comments[11].name" value="이름2"/>
        <input type="hidden" name="comments[11].email" value="이메일2"/>
        <input type="hidden" name="comments[11].content" value="내용2"/>



    </form>
</body>

</html>