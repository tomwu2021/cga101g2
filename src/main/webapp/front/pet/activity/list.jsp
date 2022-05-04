<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.pet_activity.model.*"%>
<%@ page import="com.pet_activity.service.*"%>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
%>
<!-- Activity List -->
<div class="activity" style="height:45vh;overflow-y:auto;">
			<%int count = 0; %>
	<c:forEach var="paVO" items="${paAll}">
	<!-- Activity Item-->
	<div class="activity-item d-flex">
		<div class="activite-label">${paVO.recordTime}</div>
		<% count+=10;%>
		<i class='bi bi-circle-fill activity-badge text-info align-self-start' style="filter:hue-rotate(<%=count%>deg)"></i>
		<div class="activity-content">
			<a id="${paVO.recordId}" class="allItem" onclick="getDetail(this)"> ${paVO.activity} </a>
		</div>
	</div>
	<!-- End Activity Item-->
	</c:forEach>
</div>
<!-- End Activity List -->