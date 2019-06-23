<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <%@include file="../include/header.jsp"%>

    <style type="text/css">
        body {
            margin: 0;
            background-color: #333333;
        }

        .parent {
            width: 100%;
            height: 100%;
            margin: 0;
            padding: 0;
        }

        .center {
            width: 800px;
            height: 600px;

            position: fixed;
            left: 0; right: 0;
            top: 0; bottom: 0;
            margin:auto;

            overflow:hidden;
        }

        .error.code {
            display: block;
            margin: 0 auto;
            font-size: 200px;
            color: #959ea9;
            text-align: center;
        }

        .error.text {
            display: block;
            margin: 0 auto;
            font-size: 100px;
            color: white;
            text-align: center;
        }
    </style>

</head>
<body>
    <div class="parent">
        <div class="center">
            <span class="error code">404</span>
            <span class="error text">PAGE NOT FOUND</span>
        </div>
    </div>
</body>
</html>