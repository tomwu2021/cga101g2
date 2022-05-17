<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>會員中心</title>

<!-- include <head></head> -->
<%@ include file="/front/layout/head.jsp"%>
<!-- 共用的CSS -->
<%@ include file="/back/layout/commonCSS.jsp"%>
<!-- 共用的CSS end-->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front/pet/style.css">

</head>
<body onload="executeAfterloadedBody()">
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
											<h3 class="h6 text-uppercase mb-0">我的檔案</h3>
										</div>
										<div class="card-body" style="padding-top:30px">
											<div class="row">
												<div class="col-lg-6 mb-5">
													<form class="form-horizontal">
														<div class="form-group row">
															<label class="col-md-3 form-control-label">使用者帳戶</label>
															<div class="col-md-9">
																<label class="col-md-3 form-control-label"
																	style="max-width: 550px">${membersVO.account}</label>
															</div>
														</div>


														<div class="form-group row">
															<label class="col-md-3 form-control-label">姓名</label>
															<div class="col-md-9">
																<label class="col-md-3 form-control-label"
																	style="max-width: 550px">${membersVO.name}</label>
															</div>
														</div>


														<div class="form-group row">
															<label class="col-md-3 form-control-label">手機</label>
															<div class="col-md-9">
																<label class="col-md-3 form-control-label"
																	style="max-width: 550px">${membersVO.phone}</label>
															</div>
														</div>

														<div class="form-group row">
															<label class="col-md-3 form-control-label">地址</label>
															<div class="col-md-9">
																<label class="col-md-3 form-control-label"
																	style="max-width: 550px">${membersVO.address}</label>
															</div>
														</div>
														<div class="form-group row">
															<div class="col-md-9 ml-auto">
																<a
																	href="<%=request.getContextPath()%>/front/member/memberUpdate.jsp"><input
																	type="button" value="修改" class="btn btn-primary"></a>

															</div>
														</div>
													</form>
													<!--! 提交按鈕結束 -->
												</div>
												<div class="col-lg-6 mb-5">
													<div class="form-group row">
														<label class="col-md-3 form-control-label">會員等級</label>
														<div class="col-md-9">
															<label class="col-md-3 form-control-label"
																style="max-width: 550px" id="rankId">${membersVO.rankId}</label>
														</div>
													</div>

													<div class="form-group row">
														<label class="col-md-3 form-control-label">錢包餘額</label>
														<div class="col-md-9">
															<label class="col-md-3 form-control-label"
																style="max-width: 550px">${membersVO.eWalletAmount}</label>
														</div>
													</div>

													<div class="form-group row">
														<label class="col-md-3 form-control-label">紅利金額</label>
														<div class="col-md-9">
															<label class="col-md-3 form-control-label"
																style="max-width: 550px">${membersVO.bonusAmount}</label>
														</div>
													</div>
												</div>
											</div>
										</div>
										<!--! Horizontal Form結束-->

									</div>
						</section>
					</div>
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
		src="<%=request.getContextPath()%>/assets/js/member.js"></script>
</body>

</html>