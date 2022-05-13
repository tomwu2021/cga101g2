<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>會員錢包使用紀錄</title>
<!-- include <head></head> -->
<%@ include file="/front/layout/head.jsp"%>
<!-- 共用的CSS startr-->
<%@include file="/back/layout/commonCSS.jsp"%>
<!-- 共用的CSS end-->
</head>
<body>
	<!-- 共用的header start-->
	<%@ include file="/front/layout/header.jsp"%>
	<!-- 共用的header end-->

	<!-- 共用的leftnav start-->
	<%@include file="/front/member/layout/leftnav.jsp"%>
	<!-- 共用的leftnav end-->

	<!--! ========內容======== -->
	<div class="page-holder w-100 d-flex flex-wrap">
		<div class="container-fluid px-xl-5">
			<section>
				<div class="page-holder w-100 d-flex flex-wrap">
					<div class="container-fluid px-xl-5">
						<section class="py-5">
							<div class="row">

								<!--! Horizontal Form-->
								<div class="col-lg-12 mb-5">
									<div class="card">
										<div class="card-header">
											<h3 class="h6 text-uppercase mb-0">會員錢包使用紀錄</h3>
										</div>
										<div class="card-body">
											<div class="row">
												<div class="col-lg-12 mb-5">
													<!-- <form class="form-horizontal"> -->
													<div class="form-group row" style="padding-left:40px; width:500px">
														<label class="col-md-3 form-control-label" >錢包餘額 :</label>
														<div class="col-md-9 ">
															<span class="highlight"  >${membersVO.eWalletAmount}</span>元
														</div>
													</div>


													<div class="form-group row" style="padding-left:40px; width:550px">
														<label class="col-md-3 form-control-label" >查詢日期 :</label>
														<div class="col-md-9">
															<input type="date" value="2022-01-01" min="2022-01-01"
																max="2050-01-01" step="1" id="inputDateOne" onclick="choseDate()"> ~ <input type="date"
																value="2020-04-20" min="2022-01-01" max="2050-01-01"
																step="1" id="inputDateTwo" onchange="changeDate()"> &emsp;<input type="submit" value="確定"
																class="btn btn-primary" onclick="selectByDate()">
														</div>
													</div>


												</div>
											</div>

											<div class="form-group row">
												<div class="col-lg-12">
													<div class="card-body">
													
													<div id="show">

													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<!-- </form> -->
							</div>
					</div>
					<!--! Horizontal Form結束-->
				</div>
			</section>
			<!--! Horizontal Form結束-->


			<!-- 共通的footer start-->
			<%@include file="/back/layout/footer.jsp"%>
			<!-- 共通的footer end-->
		</div>
	</div>


	<!-- 共用的JS -->
	<%@include file="/back/layout/commonJS.jsp"%>
	<!-- 共用的JS -->
	
			<script type="text/javascript"
		src="<%=request.getContextPath()%>/assets/js/memberWalletUsedRecord.js"></script>

</body>

</html>