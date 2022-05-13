<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.members.model.*"%>
<%@ page import="com.remind.model.*"%>
<%@ page import="com.remind.service.*"%>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
%>
<%
Integer memberId = session.getAttribute("membersVO")==null ? -999:((MembersVO)session.getAttribute("membersVO")).getMemberId();
%>
<% 
	java.util.Calendar cal = java.util.Calendar.getInstance();
	cal.set(Calendar.MINUTE, 0);
	cal.set(Calendar.SECOND, 0);
	cal.set(Calendar.MILLISECOND,0);
	cal.add(Calendar.HOUR,1);
	java.sql.Timestamp defaultTime = new java.sql.Timestamp(cal.getTime().getTime());
%>
<!-- Activity Edit -->
<form method="post" action="<%=request.getContextPath()%>/remind" id="editForm">
	<div class="row">
		<div class="mb-3 col-lg-9" style="font-size: 2em;">
			<input name="time" id="r_date1" type="text" value="<fmt:formatDate value="${rVO.time}" pattern="yyyy-MM-dd HH:mm" />"  style="border:none;max-width:300px;background:none;" disabled/>
			<input name="remindId" type="hidden" value="${rVO.remindId}">
		</div>

	</div>
	<textarea id="content" name="content" class="form-control" style="border:none;height:40vh;width:96%;background:none;resize:none;" disabled>${rVO.content}</textarea>
	<input name="memberId" type="hidden" value="<%=memberId%>">
</form>
<script>
//進入編輯模式
function updateBtn(){
	$("#r_date1").removeAttr("disabled");
	$("#content").removeAttr("disabled");
	$("#content").css("border","#FFC107 solid 1px");
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
		timepicker : true, //timepicker:true,
		step : 60, //step: 60 (這是timepicker的預設間隔60分鐘)
		format : 'Y-m-d H:i', //format:'Y-m-d H:i:s',
		minDate: '<%=defaultTime%>',
	// value:   new Date()
	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	//startDate:	            '2017/07/10',  // 起始日
	//minDate:               '-1970-01-01', // 去除今日(不含)之前
	//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	});
</script>