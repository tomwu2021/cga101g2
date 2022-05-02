<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.remind.service.*"%>
<%@ page import="com.remind.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
%>
<%
RemindService rSvc = new RemindService();
List<RemindVO> list = rSvc.getByMemberId(Integer.parseInt(request.getParameter("memberId")));// TODO 確認memberId存取scope
pageContext.setAttribute("rList", list);
%>
<!DOCTYPE html>
<html>
<!-- include <head></head> -->
<%@ include file="/front/layout/head.jsp"%>
<!-- 共用的CSS -->
<%@ include file="/back/layout/commonCSS.jsp"%>
<!-- 自訂的CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front/pet/style.css">
<body>
	<!-- 共用的header start -->
	<%@ include file="/front/layout/header.jsp"%>
	<!-- 共用的header end -->
	<%--breadcrumbs area ??--%>
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
	<section class="section dashboard">
		<div class="row">

			<div class="col-lg-12">
				<!-- ALL Remind -->
				<div class="card">
					
					<div class="card-body">
						<h5 class="card-title">提醒事項</h5>
						<div class="row">
							<div class="col-lg-6" >
							<div class="mb-3"></div>
								<div class="activity" style="height:45vh;overflow-y:auto;">
											<%int count = 0; %>
									<c:forEach var="rVO" items="${rList}">
										<!-- Activity Item-->
										<div class="activity-item d-flex">
											<div class="remind-label">${rVO.time}</div>
											<% count+=10;%>
												<div class="activity-content">
												<a id="${rVO.remindId}" class="allItem" onclick="getDetail(this)"> ${rVO.content} </a>
											</div>
										</div>
										<!-- End Activity Item-->
									</c:forEach>
								</div>
							</div>
							<div class="col-lg-6 mb-5" id="detailBox">
								<div id="detailBox">
									<%-- Activity Detail --%>
								</div>
								<div id="submitBtn">
							</div>
							</div>
						</div>
					</div>
				</div>
				<!-- End All Remind -->
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
<script>

const rId = '${param.remindId}'? '${param.remindId}':$('.allItem:first').attr("id");
		
		$("#detailBox").load("/CGA101G2/remind",
				{action:"one_Display", 
				remindId:rId}, 
				function(data,status){
				if(status=="success"){
					$("#detailBox").html(data);
				}
			});	
let getId;

function getDetail(remind){
	getId = remind.id;
	$.post("/CGA101G2/remind",
			{action:"one_Display", 
			remindId:getId}, 
			function(data,status){
				if(status=="success"){
					$("#detailBox").html(data);
				}
			}
	);
}


function goToInsert(remind){

	$.post("/CGA101G2/remind",
			{action:remind.id, 
			memberId:"1",
			time:$('#r_date1').val(),
			content:$("#content").val(),
			remindId:<%=count/10+2%>},
			function(data,status){
				if(status=="success"){
					$("#detailBox").html(data);
				}
			}
	);
}

function goToUpdate(remind){
	
	$.post("/CGA101G2/remind",
			{action:remind.id, 
			memberId:"1",
			time:$('#r_date1').val(),
			content:$("#content").val(),
			remindId:getId},
			function(data,status){
				if(status=="success"){
					$("#detailBox").html(data);
				}
			}
	);
}

function goToDelete(remind){
	
	$.post("/CGA101G2/remind",
			{action:remind.id, 
			remindId:getId},
			function(data,status){
				if(status=="success"){
					$("#detailBox").html(data);
				}
			}
	);
}

</script>
</body>
<!--<div class="row justify-content-center" >
<a style="display:block;max-width:40%" href="#">
<img src="../addActivity.png" style=" filter:hue-rotate(180deg) saturate(0.7);"></a></div>
<div>有新活動嗎？快來記錄吧！</div>-->
<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->
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
<link rel="stylesheet" type="text/css"
	href="/CGA101G2/front/pet/datetimepicker/jquery.datetimepicker.css" />
<script src="/CGA101G2/front/pet/datetimepicker/jquery.js"></script>
<script
	src="/CGA101G2/front/pet/datetimepicker/jquery.datetimepicker.full.js"></script>

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
		value: '<%=tempTime%>',    // value:   new Date(),
		maxDate: '<%=defaultDate%>',
	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	//startDate:	            '2017/07/10',  // 起始日
	//minDate:               '-1970-01-01', // 去除今日(不含)之前
	//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	});
</script>
</html>