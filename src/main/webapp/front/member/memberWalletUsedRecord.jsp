<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>會員錢包使用紀錄</title>

<!-- include <head></head> -->
<%@ include file="/front/layout/head.jsp"%>
<!-- 共用的CSS -->
<%@ include file="/back/layout/commonCSS.jsp"%>
<!-- 共用的CSS end-->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front/pet/style.css">

</head>
<body>
	<!-- 共用的header start-->
	<%@ include file="/front/layout/header.jsp"%>
	<!-- 共用的header end-->

	<!-- 共用的leftnav start-->
	<%@include file="/front/member/layout/leftnav.jsp"%>
	<!-- 共用的leftnav end-->

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
										<div class="card-body" style="padding-top:30px">
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

		</div>
	</div>
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
<script type="text/javascript"
		src="<%=request.getContextPath()%>/assets/js/memberWalletUsedRecord.js"></script>>
</body>


</html>