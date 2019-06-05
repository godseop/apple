<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- global jstl variable -->
<c:set var="context" value="${pageContext.request.contextPath}"/>

<meta charset="UTF-8">
<title>Apple Project</title>

<!-- favicon -->
<link rel="icon" type="image/png" href="${context}/static/favicon.ico"/>

<!-- css -->
<link rel="stylesheet" href="${context}/static/css/common.css">
<link rel="stylesheet" href="${context}/static/css/spinkit.css">

<!-- javascript -->
<script src="${context}/static/js/jquery-3.4.1.min.js" type="text/javascript"></script>
<script src="${context}/static/js/common.js" type="text/javascript"></script>

<!-- spinner -->
<div class="sk-wave" style="display: none;">
    <div class="sk-rect sk-rect1"></div>
    <div class="sk-rect sk-rect2"></div>
    <div class="sk-rect sk-rect3"></div>
    <div class="sk-rect sk-rect4"></div>
    <div class="sk-rect sk-rect5"></div>
</div>
<!-- //spinner -->