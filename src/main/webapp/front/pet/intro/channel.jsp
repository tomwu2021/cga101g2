<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.remind.model.*"%>
<!-- Pet Introduction -->
<div class="col-xxl-4 col-xl-12">
	<div class="card info-card customers-card border-bottom-warning">
		<div class="top_links filter">
			<a class="icon" href="javascript:void(0)"><i
				class="bi bi-three-dots"></i></a>
			<div class="dropdown_links filtermenu">
				<div class="dropdown_links_list">
					<ul>
						<li><a href="#" class="dropdown-item">編輯</a></li>
					</ul>
				</div>
			</div>
		</div>

		<div class="card-body">
			<h5 class="card-title">
				寵物基本資訊 <span>| 副標題</span>
			</h5>
			<c:forEach var="pVO" items="${pList}">			
			<div>${pVO.pictureId}</div>
			<div>${pVO.petName}</div>
			<div>${pVO.sort1Id}</div>
			<div>${pVO.gender}</div>
			<div>${pVO.introduction}</div>
			<div>${pVO.birthday}</div>
			</c:forEach>
			

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
