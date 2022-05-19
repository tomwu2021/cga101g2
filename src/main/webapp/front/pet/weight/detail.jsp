<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% request.setAttribute("title", "PCLUB-寵物體重紀錄"); %>
<%@ page import="java.util.*"%>
<%@ page import="com.pet_weight.model.*"%>
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
	<a class="text-dark mb-1" href='<%=request.getContextPath()%>/pet?memberId=<%=loginId%>&action=profile' >
		<i class="fas fa-arrow-left"></i> 返回
	</a>
	<section class="section dashboard">
		<div class="row">

			<div class="col-lg-12">
				<!-- ALL Weight -->
				<div class="card border-bottom-primary">
					
					<div class="card-body">
						<div class="row">
						<div class="card-title col-lg-12">體重紀錄</div>
						</div>
					<div class='row'>
					<c:if test="${pwChart != '[]'}">
					<%-- Weight Chart --%>
					<jsp:include page="/front/pet/weight/chart.jsp">
						<jsp:param value="<%=petId%>" name="petId"/>
					</jsp:include>
					<%-- End Weight Chart --%>
					</c:if>
					</div>	
					<div class='row'>
						<div class="card-title col-lg-4">
						</div>
						<div class="card-title col-lg-7">
							<button class="btn btn-warning form-btn-circle btn-sm text-dark" id="updateBtn" onclick="updateBtn()">
								<i class="fas fa-edit"></i> 編輯
							</button>
						<!-- Basic Modal -->
		              <button  class="btn btn-danger form-btn-circle btn-sm" data-toggle="modal" data-target="#basicModal" id="deleteBtn">
		                <i class="fas fa-trash-alt"></i> 刪除
		              </button>
		                      <form method="post" action="<%=request.getContextPath()%>/weight">
		              <input type="hidden" name="petId" value="<%=petId%>">
		              <input type="hidden" name="recordId" id="getRecordId">
		              <div class="modal fade" id="basicModal" tabindex="-1">
		                <div class="modal-dialog">
		                  <div class="modal-content">
		                    <div class="modal-header">
		                      <h5 class="modal-title">提示訊息</h5>
		                    </div>
		                    <div class="modal-body">
		                      是否確定刪除此筆紀錄？
		                    </div>
		                    <div class="modal-footer">
		                      <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
		                      <input type="hidden" name="action" value="delete">
		                      
		                      <button type="submit" class="btn btn-danger" id="confirmDelete">確認</button>
		                    </div>
		                  </div>
		                </div>
		              </div>
		                      </form>
						<!-- End Basic Modal-->
						</div>
						<div class="card-title col-lg-1">
							<form method="post" action="<%=request.getContextPath()%>/weight">
							<input type="hidden" name="action" value="goToInsert">
							<input type="hidden" name="petId" value="<%=petId%>">
							<button type="submit" class="btn btn-primary form-btn-circle btn-sm" id="insertBtn">
								<i class="fas fa-plus"></i> 新增
							</button>
							</form>
						</div>
						</div>
						<div class="row">
								
						</div>
						<div class="row">
							<div class="col-lg-6" id="editBox">
							<div id="editBox">
								<%-- Weight Edit --%>
								<jsp:include page="/front/pet/weight/edit.jsp">
									<jsp:param value="<%=petId%>" name="petId"/>
								</jsp:include>
								<%-- End Weight Edit --%>
							</div>
							<div id="submitBtn">
							</div>
							</div>
							<div class="col-lg-6" id="listAll">
							<%-- Weight List --%>
							<jsp:include page="/front/pet/weight/list.jsp">
								<jsp:param value="<%=petId%>" name="petId"/>
							</jsp:include>
							<%-- Weight List --%>
							</div>
						</div>
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
<script src="<%=request.getContextPath()%>/assets/js/bootstrap/bootstrap.bundle.min.js"></script>
<script>
let rId = '${param.recordId}'? '${param.recordId}':$('.allItem:first').attr("id");
		$("#editBox").load("<%=request.getContextPath()%>/weight",
				{action:"one_Display", 
				recordId:rId}, 
				function(data,status){
				if(status=="success"){
					$("#getRecordId").val(rId);
					if('${errorMsgs.recordTime}${errorMsgs.weightRecord}' !== ''){
						alert('${errorMsgs.recordTime}${errorMsgs.weightRecord}');
						$('#updateBtn').click();
						}
					if($('#activity').val()==""){
						$("#updateBtn").attr("disabled","disabled");
						$('#kilogram').hide();
					}
				}
			});	

function getDetail(record){
	rId = record.id;
	$.post("<%=request.getContextPath()%>/weight",
			{action:"one_Display", 
			recordId:rId}, 
			function(data,status){
				if(status=="success"){
					$("#editBox").html(data);
					$("#getRecordId").val(rId);
					$("#updateBtn").removeAttr("disabled");
					$('#kilogram').show();
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

<script>
	$.datetimepicker.setLocale('zh');
	$('#r_date1').datetimepicker({
		theme : '', //theme: 'dark',
		timepicker : false, //timepicker:true,
		step : 1, //step: 60 (這是timepicker的預設間隔60分鐘)
		format : 'Y-m-d', //format:'Y-m-d H:i:s',
		value: '<%=tempTime%>',    // value:   new Date(),
		maxDate: '<%=defaultDate%>',
	//disabledDates:        ['2022/06/08','20/06/09','2017/06/10'], // 去除特定不含
	//startDate:	            '2017/07/10',  // 起始日
	//minDate:               '-1970-01-01', // 去除今日(不含)之前
	//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	});
</script>
</html>