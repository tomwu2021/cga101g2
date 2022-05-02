<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.pet_activity.service.*"%>
<%@ page import="com.pet_activity.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
PetActivityService paSvc = new PetActivityService();
List<PetActivityVO> list = paSvc.getFourByPetId(Integer.parseInt(request.getParameter("petId")));// TODO 確認petId存取scope
pageContext.setAttribute("paList", list);
%>
<!-- Recent Activity -->
<div class="card">
	<div class="top_links filter">
        <a class="icon" href="javascript:void(0)"><i class="bi bi-three-dots"></i></a>
        <div class="dropdown_links filtermenu">
            <div class="dropdown_links_list">
                <ul>
                    <li><a href="#" class="dropdown-item">新增紀錄</a></li>
                    <li><a href="activity/list.jsp?petId=${param.petId}" class="dropdown-item">查看更多</a></li>
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
					<a id="${paVO.recordId}" class="allItem" onclick="getDetail(this)"> ${paVO.activity} </a>
					<form id="form_${paVO.recordId}" method="post" action="activity/list.jsp?petId=2"><input type="hidden" name="recordId" value="${paVO.recordId}"></form>
				</div>
			</div>
			<!-- End Activity Item-->
			</c:forEach>
		</div>

	</div>
</div>
<!-- End Recent Activity -->
<script>
function getDetail(record){
	const formId="form_"+record.id;
	
	$('#'+formId).submit();

}
</script>	