<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.pet.model.*"%>
<%
Integer loginId = session.getAttribute("membersVO")==null ? -999:((com.members.model.MembersVO)session.getAttribute("membersVO")).getMemberId();
%>
<% 
	java.util.Calendar cal = Calendar.getInstance();
	int currentYear = cal.get(Calendar.YEAR);
	java.sql.Date birthday = ((PetVO)request.getAttribute("pVO")).getBirthday();
	String _age = "";
	if(birthday != null){
	cal.setTimeInMillis(birthday.getTime());
	int birthdayYear = cal.get(Calendar.YEAR);
	int age = currentYear - birthdayYear;
	_age ="年齡： "+ age + "歲";
	}
%>
<!-- Pet Introduction -->
<div class="col-xxl-4 col-xl-12">
	<div class="card info-card customers-card border-bottom-warning">
		<div class="top_links filter">
			<a class="icon" href="javascript:void(0)"><i
				class="bi bi-three-dots"></i></a>
			<div class="dropdown_links filtermenu">
				<div class="dropdown_links_list">
					<ul>
						<li><a href="<%=request.getContextPath()%>/pet?memberId=<%=loginId%>&action=goToUpdate" class="dropdown-item">編輯</a></li>
					</ul>
				</div>
			</div>
		</div>

		<div class="card-body">
			<h5 class="card-title">
				寵物基本資訊 <span>${(pVO.sort1Id==1)?"| 喵主子":"| 汪星人"} </span>
			</h5>
			<div class='row'>
				<div class='col-lg-4'>
					<img src='${pVO.picVO.url}' onerror='this.src="${pVO.picVO.url}"' style='height:180px;min-width:180px;max-width:180px;object-fit: cover;border-radius: 50%;box-shadow: 0px 0px 5px #ccc, 0 0 10px #eee;'>
				</div>
				<div class='col-lg-8'>
					<div class='text-secondary font-weight-bold' style='font-size:2em;'>${pVO.petName}</div>
					<hr/>
					<div>${((pVO.gender==2)?"性別： 女孩":((pVO.gender==1)?"性別： 男孩":""))}</div>
					<div>${(pVO.birthday=="" || pVO.birthday==null)?"":"生日： "}<fmt:formatDate value="${pVO.birthday}" pattern="MM-dd" /></div>
					<div><%=_age%></div>
					<div>${(pVO.introduction=="" || pVO.introduction==null)?"簡介： 無":"簡介： "}<br/>${pVO.introduction}</div>
				</div>
			</div>		
			

		</div>
	</div>
</div>
<!-- End Pet Introduction -->
<script>
	function getDetail(record) {
		const formId = "form_" + record.id;

		$('#' + formId).submit();

	}
</script>
