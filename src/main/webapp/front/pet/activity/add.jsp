<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.pet_activity.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<!DOCTYPE html>
<html>
<!-- include <head></head> -->
<%@ include file="/front/layout/head.jsp"%>
<!-- 共用的CSS -->
<%@ include file="/back/layout/commonCSS.jsp"%>
<!-- 自訂的CSS -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/sb-admin-2.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front/pet/style.css">
<body>
	<!-- 共用的header start -->
	<%@ include file="/front/layout/header.jsp"%>
	<!-- 共用的header end -->
	<%--bread crumb area ?? title ?? --%>
	<!-- 共用的side bar start -->
	<%@ include file="/front/member/layout/leftnav.jsp"%>
	<!-- 共用的side bar end -->

	<div class="page-holder w-100 d-flex flex-wrap">
		<div class="container-fluid px-xl-5">
			<section>
				<div class="page-holder w-100 d-flex flex-wrap">
					<div class="container-fluid">
						<section class="py-5">
							<div class="row">
								<div class="col-lg-12">
									<div class="row">
										<div class="col-lg-2 col-md-12"></div>
											<div class="col-lg-10 col-md-12">
	<!-- ============================= Main ============================= -->
	<a class="text-dark mb-1" href='<%=request.getContextPath()%>/activity?action=all_Display&petId=<%=petId%>'>
		<i class="fas fa-arrow-left"></i> 返回
	</a>
	<section class="section dashboard">
		<div class="row">

			<div class="col-lg-12">
				<!-- ALL Activity -->
				<div class="card border-bottom-info">
					
			
					
					<form method="post" action="<%=request.getContextPath()%>/activity" id="editForm">
					<div class="card-body">
						<div class="row">
						<div class="card-title col-lg-11">新增紀錄</div>
						<div class="card-title col-lg-1">
							<input type="hidden" name="action" value="insert">
							<button class="btn btn-success form-btn-circle" type="submit">
							送出 <i class="fas fa-arrow-right"></i>
							</button>
						</div>
						</div>
						<div class="row">
								
						</div>
						<div class="row">
							<div class="col-lg-12 mb-5" id="editBox">

								<div class="row">
									<div class="mb-3 col-lg-9" style="font-size: 2em;">
										<input name="recordTime" id="r_date1" type="text" placeholder="請輸入日期"
												value="${param.recordTime}"  style="border:none;max-width:200px;background:none;"/><label class="btn btn-muted form-btn-circle btn-sm" for="r_date1"><i class="fas fa-calendar text-muted"></i></label>
									</div>
							
								</div>
								<textarea id="activity" name="activity" class="form-control" placeholder="請輸入內容"  style="border:#4680FF 1px solid;height:40vh;width:96%;background:none;resize:none;" autofocus>${param.activity}</textarea>
								<input name="petId" type="hidden" value="<%=petId%>">

							<div id="submitBtn">
							<span style="color:#f33;">${errorMsgs.recordTime}${errorMsgs.activity}</span>
							</div>
							</div>

						</div>
					</div>
					</form>
				</div>
				<!-- End All Activity -->
			</div>

		</div>
	</section>
	<!-- =========================== End Main =========================== -->
										</div>
									</div>
								</div>
							</div>
						</section>
					</div>
				</div>
			</section>
		</div>
	</div>
	</div>
	<%@include file="/front/pet/footer.jsp"%>
	<!-- 共用的JS -->
	<%@include file="/front/layout/commonJS.jsp"%>
	<!-- 自訂的JS -->
</body>
<!--<div class="row justify-content-center" >
<a style="display:block;max-width:40%" href="#">
<img src="../addActivity.png" style=" filter:hue-rotate(180deg) saturate(0.7);"></a></div>
<div>有新活動嗎？快來記錄吧！</div>-->
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

// 	function noDays(date){
// 		// 下迴圈
// 	      if (date.getFullYear() === 2022 && date.getMonth()+1 === 3 && date.getDate() === 3 )  
// 	            return [ false, "closed"]

// 	}
	$.datetimepicker.setLocale('zh');
	$('#r_date1').datetimepicker({
		theme : '', //theme: 'dark',
		timepicker : false, //timepicker:true,
		step : 1, //step: 60 (這是timepicker的預設間隔60分鐘)
		format : 'Y-m-d', //format:'Y-m-d H:i:s',
		value: '<%=tempTime%>',    // value:   new Date(),
		maxDate: '<%=defaultDate%>',
// 		beforeShowDay: noDays
		
	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	//startDate:	            '2017/07/10',  // 起始日
	//minDate:               '-1970-01-01', // 去除今日(不含)之前
	//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	});
</script>
</html>