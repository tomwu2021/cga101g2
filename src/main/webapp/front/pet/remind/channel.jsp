<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.remind.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
Integer memberId = (Integer)(session.getAttribute("memberId"));
Integer petId = (Integer)(session.getAttribute("petId"));
%>
<!-- Recent Remind -->
<div class="card">
	<div class="top_links filter">
	    <a class="icon" href="javascript:void(0)"><i class="bi bi-three-dots"></i></a>
	    <div class="dropdown_links filtermenu">
	        <div class="dropdown_links_list">
	            <ul>
	                <li><a href="front/pet/remind/add.jsp" class="dropdown-item">新增紀錄</a></li>
	                <li><a href="<%=request.getContextPath()%>/remind?action=all_Display&memberId=<%=memberId%>" class="dropdown-item">查看更多</a></li>
                </ul>
	        </div>
	    </div>
	</div>

	<div class="card-body">
		<h5 class="card-title">
			提醒事項
		</h5>

		<div class="activity">
			<c:forEach var="rVO" items="${rList}">
			<!-- Remind Item-->
			<div class="activity-item d-flex">
				<div class="remind-label text-danger">
					<fmt:formatDate value="${rVO.time}" pattern="yyyy-MM-dd" /></div>
				<div class="activity-content">
					<div id="${rVO.remindId}" class="allItem" onclick="getDetail(this)"> ${rVO.content} </div>
					<div class="text-muted small"><fmt:formatDate value="${rVO.time}" pattern="HH:mm" /></div>
				</div>
			</div>
			<!-- End Remind Item-->
			</c:forEach>
		</div>

	</div>
</div>
<!-- End Recent Remind -->