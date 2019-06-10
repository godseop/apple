<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <%@include file="../include/header.jsp"%>
</head>
<body>
    <h1>DATABASE ERROR</h1>
    <p>${pageContext.request.getAttribute("exception")}</p>
</body>
</html>