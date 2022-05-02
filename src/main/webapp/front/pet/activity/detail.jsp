<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.pet_activity.model.*"%>
<%@ page import="com.pet_activity.service.*"%>

<form method="post" action="list.jsp?petId=2">
	<div class="row">
		<div class="mb-3 col-lg-9" style="font-size: 2em;">
			<input name="recordTime" id="r_date1" type="date" placeholder="請輸入日期${errorMsgs.recordTime}"
					value="${paVO.recordTime}"  style="border:none;max-width:200px;background:none;"/>
			<input name="recordId" type="hidden" value="${paVO.recordId}">
		</div>
		<div class="mb-3 col-lg-3">
			<button class="btn btn-primary form-btn-circle btn-sm" title="新增" id="insert" onclick="goToInsert(this)">
				<i class="fas fa-plus"></i>
			</button>
			<button class="btn btn-warning form-btn-circle btn-sm" title="修改" id="update" onclick="goToUpdate(this)">
				<i class="fas fa-edit"></i>
			</button>
			<button class="btn btn-danger form-btn-circle btn-sm" title="刪除" id="delete" onclick="goToDelete(this)">
				<i class="fas fa-trash"></i>
			</button>
		</div>
	</div>
	<textarea id="activity" name="activity" placeholder="請輸入內容${errorMsgs.activity}"  style="border:none;height:40vh;width:96%;background:none;resize:none;">${paVO.activity}</textarea>
</form>
<!-- Activity Detail -->
