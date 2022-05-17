<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>會員修改錢包密碼</title>

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

								<div class="col-lg-12 mb-5">
									<!-- Horizontal Form -->
									<div class="card">
										<div class="card-header">
											<h3 class="h6 text-uppercase mb-0">會員修改錢包密碼</h3>
										</div>
										<div class="card-body" style="padding-top:30px">
											<div class="row">
												<div class="col-lg-6 mb-5">
													<form class="form-horizontal" method="post"
														action="<%=request.getContextPath()%>/front/member.do">

														<div class="form-group row">
															<label class="col-md-3 form-control-label">舊密碼</label>
															<div class="col-md-9">
																<input  maxlength="6" type="password" name="oldWalletPassword" placeholder="請輸入舊密碼"
																	class="form-control form-control-success"
																	value="${messages.userInput1}"> <font color=red>${messages.errorOldoldWalletPassword}</font>
															</div>
														</div>

														<div class="form-group row">
															<label class="col-md-3 form-control-label">新密碼</label>
															<div class="col-md-9">
																<input maxlength="6" type="password" name="newWalletPassword"
																	placeholder="輸入六位數字"
																	class="form-control form-control-success"
																	value="${messages.userInput2}"> <font color=red>${messages.errornewWalletPassword}</font>
															</div>
														</div>

														<div class="form-group row">
															<label class="col-md-3 form-control-label">確認密碼</label>
															<div class="col-md-9">
																<input maxlength="6" type="password" name="checkNewWalletPassword"
																	placeholder="請再次確認密碼"
																	class="form-control form-control-success"> <font
																	color=red>${messages.checkNewWalletPassword}</font>

															</div>
														</div>

														<div class="form-group row">
															<!-- 提交按鈕 -->
															<div class="col-md-9 ml-auto">
																<input maxlength="6" type="hidden" name="action"
																	value="updateMemberWalletPassword"> <input
																	type="submit" value="儲存" class="btn btn-primary"><font
																	color=red>${messages.updatePasswordSuccess}</font>
															</div>
														</div>
													</form>
													<!-- 提交按鈕結束 -->
												</div>
												<!-- !看會員提交圖片的css css/style.default.css 4195-->
												<div class="col-lg-6 mb-5"></div>
											</div>
										</div>
										<!-- Horizontal Form 結束 -->
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
<script type="text/javascript"
		src="<%=request.getContextPath()%>/assets/js/memberWalletPassword.js"></script>
</body>
	
		


</html>