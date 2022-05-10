<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.remind.model.*"%>
<%@ page import="com.pet.model.*"%>
<%@ page import="com.pet_activity.model.*"%>
<%@ page import="com.pet_weight.model.*"%>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
%>
<%
Integer memberId = (Integer)(session.getAttribute("memberId"));
Integer petId = (Integer)(session.getAttribute("petId"));
%>
<!DOCTYPE html>
<html>
<!-- include <head></head> -->
<%@ include file="/front/layout/head.jsp"%>
<!-- 共用的CSS -->
<%@ include file="/back/layout/commonCSS.jsp"%>
<!-- Morris Chart Styles-->
<link href="assets/css/morris-0.4.3.min.css" rel="stylesheet" />
<link href="assets/css/sb-admin-2.min.css" rel="stylesheet">
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
			
			<!-- Left side columns -->
			<div class="col-lg-8">
				<div class="row">
					<%-- Pet Introduction --%>
					<jsp:include page="/front/pet/intro/channel.jsp">
						<jsp:param value="${pList}" name="pList"/>
					</jsp:include>
					<%-- End Pet Introduction --%>
					
					<%-- Recent Weight --%>
					<jsp:include page="/front/pet/weight/channel.jsp">
						<jsp:param value="${recentWgt}" name="recentWgt"/>
						<jsp:param value="${averageWgt}" name="averageWgt"/>
						<jsp:param value="${pwList}" name="pwList"/>
					</jsp:include>
					<%-- End Recent Weight --%>
				</div>
			</div>
			<!-- End Left side columns -->

			<!-- Right side columns -->
			<div class="col-lg-4">

				<%-- Recent Activity --%>
				<jsp:include page="/front/pet/activity/channel.jsp">
					<jsp:param value="${paList}" name="paList"/>
				</jsp:include>
				<%-- End Recent Activity --%>
				<%-- Recent Remind --%>
				<jsp:include page="/front/pet/remind/channel.jsp">
					<jsp:param value="${rList}" name="rList"/>
				</jsp:include>
				<%-- End Recent Remind --%>

			</div>
			<!-- End Right side columns -->
			
			<!-- Banner -->
			<div class="col-lg-12">
				<div class="row">
					<!-- Albums Card -->
					<div class="col-xxl-4 col-xl-12">


					</div>
					<!-- End Albums Card -->
				</div>
			</div>
			<!-- End Banner Card -->

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
if('${recentWgt.recordTime}'===''){
	$('.weight-card').hide();
}
</script>

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
                data: ${pwList},


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

</html>