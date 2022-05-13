<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.members.model.*"%>
<%@ page import="com.pet_activity.model.*"%>
<%@ page import="com.pet_activity.service.*"%>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
%>
<%
Integer petId = session.getAttribute("membersVO")==null ? -999:((MembersVO)session.getAttribute("membersVO")).getPetVO().getPetId();
%>
<% 
	java.util.Calendar cal = Calendar.getInstance();
	cal.set(Calendar.MINUTE, 0);
	cal.set(Calendar.SECOND, 0);
	cal.set(Calendar.MILLISECOND,0);
	cal.set(Calendar.HOUR, 0);
	java.sql.Date defaultDate = new java.sql.Date(cal.getTime().getTime());

	java.sql.Date tempTime = null;
	try{
		tempTime = java.sql.Date.valueOf(request.getParameter("recordTime").trim());
	} catch(Exception e){
		tempTime = new java.sql.Date(System.currentTimeMillis());
	}
%>
<!-- Activity Edit -->
<form method="post" action="<%=request.getContextPath()%>/activity" id="editForm">
	<div class="row">
		<div class="mb-3 col-lg-9" style="font-size: 2em;">
			<input name="recordTime" id="r_date1" type="text" value="${paVO.recordTime}"  style="border:none;max-width:200px;background:none;" disabled/>
			<input name="recordId" type="hidden" value="${paVO.recordId}">
		</div>

	</div>
	<textarea id="activity" name="activity" class="form-control" style="border:none;height:40vh;width:96%;background:none;resize:none;" disabled>${paVO.activity}</textarea>
	<input name="petId" type="hidden" value="<%=petId%>">
</form>
<script>
//進入編輯模式
function updateBtn(){
	$("#r_date1").removeAttr("disabled");
	$("#activity").removeAttr("disabled");
	$("#activity").css("border","#FFC107 solid 1px");
	$('#update').remove();
	$("#editForm").append('<div class="mt-3" id="update"><input type="hidden" name="action" value="update"><button class="btn btn-success btn-circle btn-sm" type="submit">送出</button></div>');	
}
</script>
<!-- End Activity Edit -->
<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/assets/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/assets/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
	$.datetimepicker.setLocale('zh');
	$('#r_date1').datetimepicker({
		theme : '', //theme: 'dark',
		timepicker : false, //timepicker:true,
		step : 1, //step: 60 (這是timepicker的預設間隔60分鐘)
		format : 'Y-m-d', //format:'Y-m-d H:i:s',
		maxDate: '<%=defaultDate%>',
	// value:   new Date()
	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	//startDate:	            '2017/07/10',  // 起始日
	//minDate:               '-1970-01-01', // 去除今日(不含)之前
	//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	});
</script>