<%@ page contentType="text/html; charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<head>
<meta charset="utf-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>${title}</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Favicon -->
<link rel="shortcut icon" type="image/x-icon"
	href="<%=request.getContextPath()%>/assets/img/favicon.ico">

<%@ include file="/front/layout/commonCSS.jsp"%>

<!-- include  common JS-->
<%@ include file="/front/layout/commonJS.jsp"%>

<script>
    function getContextPath(){
     return "<%=request.getContextPath()%>
	";
	}
</script>
</head>
