<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.members.model.*"%>
<%@ page import="com.pet_activity.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
Integer petId = session.getAttribute("membersVO")==null ? -999:((MembersVO)session.getAttribute("membersVO")).getPetVO().getPetId();
%>
<!-- Recent Activity -->
<div class="card border-bottom-info">

	<div class="top_links filter">
        <a class="icon" href="javascript:void(0)"><i class="bi bi-three-dots"></i></a>
        <div class="dropdown_links filtermenu">
            <div class="dropdown_links_list">
                <ul>
                    <li><a href="front/pet/activity/add.jsp" class="dropdown-item">新增紀錄</a></li>
                    <li><a href="<%=request.getContextPath()%>/activity?action=all_Display&petId=<%=petId%>" class="dropdown-item">查看更多</a></li>
                </ul>
            </div>
        </div>
    </div>

	<div class="card-body">
		<h5 class="card-title">
			活動紀錄
		</h5>
		<div class="activity">
			<c:forEach var="paVO" items="${paList}">
			<!-- Activity Item-->
			<div class="activity-item d-flex">
				<div class="activite-label">${paVO.recordTime}</div>
				<i class='bi bi-circle-fill activity-badge text-muted align-self-start'></i>
				<div class="activity-content">
					<div id="${paVO.recordId}" class="allItem" > ${paVO.activity} </div>

				</div>
			</div>
			<!-- End Activity Item-->
			</c:forEach>
		</div>
	</div>
</div>
<!-- End Recent Activity -->