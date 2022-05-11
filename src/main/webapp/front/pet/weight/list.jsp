<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.pet_weight.model.*"%>
<%@ page import="com.pet_weight.service.*"%>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
%>
<!-- Activity List -->
<div class="activity" style="height:20vh;overflow-y:auto;">
			<%int count = 0; %>
	<c:forEach var="pwVO" items="${pwAll}">
	<!-- Activity Item-->
	<div class="activity-item d-flex">
		<div class="activite-label">${pwVO.recordTime}</div>
		<% count+=10;%>
		<i class='bi bi-circle-fill activity-badge text-primary align-self-start' style="filter:hue-rotate(<%=count%>deg)"></i>
		<div class="activity-content">
			<a id="${pwVO.recordId}" class="allItem" onclick="getDetail(this)"> ${pwVO.weightRecord} kg</a>
		</div>
	</div>
	<!-- End Activity Item-->
	</c:forEach>
</div>
<!-- End Activity List -->