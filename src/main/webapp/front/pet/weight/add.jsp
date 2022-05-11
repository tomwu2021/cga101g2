<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.pet_weight.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
%>
<%
Integer petId = (Integer)(session.getAttribute("petId"));
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
	<a class="text-dark mb-1" href='<%=request.getContextPath()%>/weight?action=all_Display&petId=<%=(Integer)session.getAttribute("petId")%>'>
		<i class="fas fa-arrow-left"></i> 返回
	</a>
	<section class="section dashboard">
		<div class="row">

			<div class="col-lg-12">
				<!-- ALL Weight -->
				<div class="card border-bottom-primary">
					
					<div class="card-body">
						<div class="row">
						<div class="card-title col-lg-12">新增體重紀錄</div>
						</div>
					<div class='row'>
					<%-- Weight Chart --%>
					<jsp:include page="/front/pet/weight/chart.jsp">
						<jsp:param value="<%=petId%>" name="petId"/>
					</jsp:include>
					<%-- End Weight Chart --%>
					</div>	
					<form method="post" action="<%=request.getContextPath()%>/weight">
					<div class="row mt-3" id="editForm" style="font-size: 1.6em;">
						<div class="mb-3 col-lg-2" style="display:inline-flex">
						<label class="btn btn-muted form-btn-circle btn-sm" for="r_date1"><i class="fas fa-calendar text-muted"></i></label>
							<input name="recordTime" id="r_date1" type="text" value="${pwVO.recordTime}"  style="border:none;max-width:160px;background:none;"/>
							<input name="recordId" type="hidden" value="${pwVO.recordId}">
						</div>
						<div class="mb-3 col-lg-1">
							<input type="text" id="activity" name="weightRecord" style="border:#4680FF 1px solid;background:none;max-width:150px;" autofocus value='${pwVO.weightRecord}'>
							<input name="petId" type="hidden" value="<%=petId%>">
						</div>
						<div class="mb-3 col-lg-1">
							<span id='kilogram'>&nbsp; kg</span>
						</div>
						<div class="mb-3 col-lg-6">
							<input type="hidden" name="action" value="insert">
							<button class="btn btn-success form-btn-circle" type="submit">
							送出 <i class="fas fa-arrow-right"></i>
							</button>
							<span style='color:#f00;font-size:1em;'>${errorMsgs.weightRecord}${errorMsgs.recordTime}</span>
						</div>
					</div>
					</form>
					</div>
				</div>
				<!-- End All Weight -->
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
<!-- Metis Menu Js -->
<script src="assets/js/jquery.metisMenu.js"></script>
<!-- Morris Chart Js -->
<script src="assets/js/raphael-2.1.0.min.js"></script>
<script src="assets/js/morris.js"></script>
<!-- 自訂的JS -->
<script>
(function ($) {
    "use strict";
    var mainApp = {

        initFunction: function () {
            /*MENU 
            ------------------------------------*/
            $('#main-menu').metisMenu();

            $(window).bind("load resize", function () {
                if ($(this).width() < 768) {
                    $('div.sidebar-collapse').addClass('collapse')
                } else {
                    $('div.sidebar-collapse').removeClass('collapse')
                }
            });
            /* MORRIS LINE CHART
            ----------------------------------------*/
            Morris.Line({
                element: 'morris-line-chart',
                data: ${pwChart},


                xkey: 'recordTime',
                ykeys: ['weightRecord'],
                labels: ['體重(kg)', '紀錄日期'],
                fillOpacity: 0.6,
                hideHover: 'auto',
                behaveLikeLine: true,
                resize: true,
                pointFillColors: ['#ffffff'],
                pointStrokeColors: ['black'],
                lineColors: ['#2249BA'],

            });


            $('.line-chart').cssCharts({ type: "line" });

        },

        initialization: function () {
            mainApp.initFunction();

        }

    }
    // Initializing ///

    $(document).ready(function () {
        mainApp.initFunction();
        $("#sideNav").click(function () {
            if ($(this).hasClass('closed')) {
                $('.navbar-side').animate({ left: '0px' });
                $(this).removeClass('closed');
                $('#page-wrapper').animate({ 'margin-left': '260px' });

            }
            else {
                $(this).addClass('closed');
                $('.navbar-side').animate({ left: '-260px' });
                $('#page-wrapper').animate({ 'margin-left': '0px' });
            }
        });
    });

}(jQuery));
</script>	
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
	$.datetimepicker.setLocale('zh');
	$('#r_date1').datetimepicker({
		theme : '', //theme: 'dark',
		timepicker : false, //timepicker:true,
		step : 1, //step: 60 (這是timepicker的預設間隔60分鐘)
		format : 'Y-m-d', //format:'Y-m-d H:i:s',
		value: new Date(),
		maxDate: new Date(),
	//disabledDates:        ['2022/06/08','20/06/09','2017/06/10'], // 去除特定不含
	//startDate:	            '2017/07/10',  // 起始日
	//minDate:               '-1970-01-01', // 去除今日(不含)之前
	//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	});
</script>
</html>