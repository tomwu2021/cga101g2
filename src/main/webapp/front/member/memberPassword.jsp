<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<!-- 共用的CSS startr-->
<!-- include <head></head> -->
<%@ include file="/front/layout/head.jsp"%>
<%@include file="/back/layout/commonCSS.jsp"%>
<body>
	<!-- 共用的header start-->
	<%@ include file="/front/layout/header.jsp"%>
	<!-- 共用的header end-->

	<!-- 共用的leftnav start-->
	<%@include file="/front/member/layout/leftnav.jsp"%>
	<!-- 共用的leftnav end-->

	<!-- ========內容======== -->
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
											<h3 class="h6 text-uppercase mb-0">我的檔案</h3>
										</div>
										<div class="card-body">
											<div class="row">
												<div class="col-lg-6 mb-5">
													<form class="form-horizontal" method="post"
														action="<%=request.getContextPath()%>/front/member.do">

														<div class="form-group row">
															<label class="col-md-3 form-control-label">舊密碼</label>
															<div class="col-md-9">
																<input name="oldPhone" placeholder="請輸入舊密碼"
																	class="form-control form-control-success" value="${messages.userInput1}"> <font
																	color=red>${messages.errorOldPhone}</font>
															</div>
														</div>

														<div class="form-group row">
															<label class="col-md-3 form-control-label">新密碼</label>
															<div class="col-md-9">
																<input type="password" name="newPhone" placeholder="至少八個字符，至少一個字母和一個數字"
																	class="form-control form-control-success" value="${messages.userInput2}"> <font
																	color=red>${messages.errorNewPhone}</font>
															</div>
														</div>

														<div class="form-group row">
															<label class="col-md-3 form-control-label">確認密碼</label>
															<div class="col-md-9">
																<input type="password" name="checkNewPhone" placeholder="請再次確認密碼"
																	class="form-control form-control-success" > <font
																	color=red>${messages.errorCheckNewPhone}</font>
																	
															</div>
														</div>

														<div class="form-group row">
															<!-- 提交按鈕 -->
															<div class="col-md-9 ml-auto">
																<input type="hidden" name="action"
																	value="updateMemberPassword"> <input type="submit"
																	value="儲存" class="btn btn-primary"><font
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



			<!-- 共通的footer start-->
			<%@include file="/back/layout/footer.jsp"%>
		</div>
	</div>

	<!-- 共用的JS -->
	<%@include file="/back/layout/commonJS.jsp"%>

</body>

</html>