<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<c:set var="context" value="${pageContext.request.contextPath}"/>
<sec:csrfMetaTags />

<meta charset="UTF-8">
<title>Apple Project</title>

<!-- favicon -->
<link rel="icon" type="image/png" href="${context}/static/favicon.ico"/>
<!-- //favicon -->

<!-- css -->
<link rel="stylesheet" href="${context}/static/css/common.css">
<link rel="stylesheet" href="${context}/static/css/spinkit.css">
<!-- //css -->

<!-- javascript -->
<script src="${context}/static/js/jquery-3.4.1.min.js" type="text/javascript"></script>
<script src="${context}/static/js/jquery.serializeObject.min.js" type="text/javascript"></script>
<script src="${context}/static/js/common.js" type="text/javascript"></script>
<!-- //javascript -->

<script>
$.support.cors = true; // CORS 설정
var context = "${context}";
</script>

