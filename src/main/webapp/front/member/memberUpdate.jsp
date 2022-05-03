<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<!-- 共用的CSS startr-->
<!-- include <head></head> -->
<%@ include file="/front/layout/head.jsp"%>
<%@include file="/back/layout/commonCSS.jsp"%>
<!-- 共用的CSS end-->

<!-- 額外添加的CSS -->
<!-- 	路徑舉例 -->
<%-- <link rel="stylesheet"href="<%=request.getContextPath()%>/assets/????待討論> --%>
<!-- 額外添加的CSS -->
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
											<h3 class="h6 text-uppercase mb-0">我的檔案</h3>
										</div>
										<div class="card-body">
											<!--                         <p>Lorem ipsum dolor sit amet consectetur.</p> -->
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
															<label class="col-md-3 form-control-label">地址</label>
															<div class="col-md-9">
																<input id="inputHorizontalSuccess"
																	value="${membersVO.address}"
																	placeholder="Email Address"
																	class="form-control form-control-success">
															</div>
														</div>

														<div class="form-group row">
															<label class="col-md-3 form-control-label">手機</label>
															<div class="col-md-9">
																<input id="inputHorizontalSuccess"
																	value="${membersVO.phone}" placeholder="Email Address"
																	class="form-control form-control-success">
															</div>
														</div>




														<!--                           <div class="form-group row"> -->
														<!--                             <label class="col-sm-3 form-control-label">密碼</label> -->
														<!--                             <div class="col-md-9"> -->
														<!--                               <label class="col-sm-9 form-control-label">*****777 <a href="#">變更</a></label> -->
														<!--                               !看會員提交圖片的css css/style.default.css 4195 -->
														<!--                             </div> -->
														<!--                           </div> -->


														<!--! 提交按鈕 -->
														<div class="form-group row">
															<div class="col-md-9 ml-auto">
																<a
																	href="<%=request.getContextPath()%>/front/member/member.jsp"><input
																	type="button" value="儲存" class="btn btn-primary"></a>
															</div>
														</div>
													</form>
													<!--! 提交按鈕結束 -->
												</div>
												<!-- !看會員提交圖片的css css/style.default.css 4195-->
												<div class="col-lg-6 mb-5">
													<div class="form-group row">
														<label class="col-md-3 form-control-label">會員等級</label>
														<div class="col-md-9">
															<label class="col-md-3 form-control-label"
																style="max-width: 550px">${membersVO.rankId}</label>
														</div>
													</div>

													<div class="form-group row">
														<label class="col-md-3 form-control-label">錢包帳戶金額</label>
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


			<!-- 共通的footer start-->
			<%@include file="/back/layout/footer.jsp"%>
			<!-- 共通的footer end-->
		</div>
	</div>


	<!-- 共用的JS -->
	<%@include file="/back/layout/commonJS.jsp"%>
	<!-- 共用的JS -->

	<!-- 額外添加的JS -->
	<!-- 	路徑舉例 -->
	<%-- <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/????待討論.js"> --%>
	<!-- 額外添加的JS -->

</body>

</html>