<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>會員修改基本資料</title>
<!-- include <head></head> -->
<%@ include file="/front/layout/head.jsp"%>
<!-- 共用的CSS -->
<%@ include file="/back/layout/commonCSS.jsp"%>
<!-- 共用的CSS end-->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front/pet/style.css">

<style>
.f3 {
	float: left;
	margin-left: 5px;
	margin-right: 5px;
	margin-bottom: 10px;
	width: 100px;
}

.f4 {
	float: left;
	margin-left: 5px;
	margin-right: 5px;
	margin-bottom: 10px;
	width: 150px;
}

.f13 {
	float: left;
	margin-left: 5px;
	margin-right: 5px;
	width: calc(65% - 10px)
}
</style>

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
																					<h3 class="h6 text-uppercase mb-0">會員修改基本資料</h3>
																				</div>
																				<div class="card-body" style="padding-top: 30px">
																					<div class="row">
																						<div class="col-lg-6 mb-5">
																							<form class="form-horizontal" method="post"
																								action="<%=request.getContextPath()%>/front/member.do">
																								<div class="form-group row">
																									<label class="col-md-3 form-control-label">姓名</label>
																									<div class="col-md-9">
																										<input name="name" value="${membersVO.name}"
																											placeholder="User Name"
																											class="form-control form-control-success"><font
																											color=red>${messages.errorName}</font>
																									</div>
																								</div>

																								<div class="form-group row">
																									<label class="col-md-3 form-control-label">手機</label>
																									<div class="col-md-9">
																										<input name="phone" value="${membersVO.phone}"
																											placeholder="User Phone"
																											class="form-control form-control-success"
																											maxlength="10"><font color=red>${messages.errorPhone}</font>
																									</div>
																								</div>

																								<div class="form-group row">
																									<!-- 																									<div class="col-12 mb-20" id=made> -->
																									<label class="col-md-3 form-control-label">地址</label>
																									<font color=red>${messages.errorAddress}</font>
																									<div id="zipcode3">
																										<div class="f3" data-role="county"></div>
																										<div class="f4" data-role="district"></div>
																									</div>
																									<input type="text" name="address"
																										class="form-control"
																										id="exampleFormControlInput1">
																									<!-- 																									</div> -->
																								</div>

																								<!--! 提交按鈕 -->
																								<div class="form-group row">
																									<div class="col-md-9 ml-auto">
																										<input type="hidden" name="action"
																											value="updateMemberInfo"> <input
																											type="submit" value="儲存"
																											class="btn btn-primary">
																									</div>
																								</div>
																							</form>
																							<!--! 提交按鈕結束 -->
																						</div>
																						<!-- !看會員提交圖片的css css/style.default.css 4195-->
																						<div class="col-lg-6 mb-5"></div>
																					</div>
																				</div>
																				<!--! Horizontal Form結束-->

																			</div>
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
	<script src="https://demeter.5fpro.com/tw/zipcode-selector.js"></script>
	<script
		src="<%=request.getContextPath()%>/assets/js/jquery.twzipcode.min.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/memberUpdate.js"></script>
</body>


</html>