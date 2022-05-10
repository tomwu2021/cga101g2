<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.remind.model.*"%>
<%@ page import="com.remind.service.*"%>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
%>
<!-- Activity List -->
<div class="activity" style="height:45vh;overflow-y:auto;">
			<%int count = 60; %>
	<c:forEach var="rVO" items="${rAll}">
	<!-- Activity Item-->
	<%count -= 60; %>
	<div class="activity-item d-flex">
		<div class="remind-label">
			<div class=" text-danger" style="filter:hue-rotate(<%=count%>deg)"><fmt:formatDate value="${rVO.time}" pattern="yyyy-MM-dd " /></div>
					
		<div class="text-muted small"><i class="far fa-clock"></i>&nbsp;&nbsp;<fmt:formatDate value="${rVO.time}" pattern="HH:mm" /></div></div>
		
		<div class="activity-content">
			<a id="${rVO.remindId}" class="allItem" onclick="getDetail(this)"> ${rVO.content} </a>
		</div>
	</div>
	<!-- End Activity Item-->
	</c:forEach>
</div>
<!-- End Activity List -->